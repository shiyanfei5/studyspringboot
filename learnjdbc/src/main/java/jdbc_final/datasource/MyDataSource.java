package jdbc_final.datasource;


import jdbc_final.exception.DaoException;

import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

public class MyDataSource {
    //jdbc账号密码
    private static String url;
    private static String user;
    private static String password;

    //配置项
    private static int initCount ;      //初始化处数量
    private static int maxIdleCount ;   //最大数量,控制close时的还回
    private static int minIdleCount ;   //最小数量，控制get时的获取

    //当前池中连接数量
    private static int currentCount;
    private final static LinkedList<Connection> connectionsPool = new LinkedList<>(); //存放连接

    static {
        //获取配置项
        try{
            InputStream in = MyDataSource.class.getClassLoader().
                    getResourceAsStream("JdbcConfig.properties");
            Properties props = new Properties();

            props.load(in);
            MyDataSource.initCount = Integer.parseInt( props.getProperty("dataSource.initCount") );
            MyDataSource.maxIdleCount = Integer.parseInt( props.getProperty("dataSource.maxIdleCount") );
            MyDataSource.minIdleCount = Integer.parseInt( props.getProperty("dataSource.minIdleCount") );
            MyDataSource.url = props.getProperty("jdbc.url");
            MyDataSource.user = props.getProperty("jdbc.user");
            MyDataSource.password = props.getProperty("jdbc.password");
            //首次启动加载驱动，注册只DriverManager
            Class.forName(props.getProperty("jdbc.driver"));
            System.out.println("----------------驱动注册成功-------------------");
            //初始化连接池
            for(int i = 0; i < MyDataSource.initCount; i ++){
                MyDataSource.connectionsPool.addLast(   createProxyConnection() );
            }
            MyDataSource.currentCount = MyDataSource.initCount;
            System.out.println("----------------连接池初始化成功-------------------");

        } catch (Exception e){ //转换为运行时异常
            throw  new DaoException(e.getMessage(),e);
        }

    }


    //原生创建连接
    private static Connection createRowConnection() throws SQLException{
        return DriverManager.getConnection(
                MyDataSource.url,
                MyDataSource.user,
                MyDataSource.password
        );
    }

    public  Connection getConnection() throws SQLException{
        synchronized (connectionsPool){
            if(currentCount > 0){       //连接池中存在连接，从连接池里拿
                currentCount--;
                if(currentCount < minIdleCount){
                    connectionsPool.addLast( createProxyConnection());
                    currentCount++;
                }
                return connectionsPool.removeFirst();
            }
            else{
                //连接池中不存在连接,那么就再生成连接。比如第11个
                //无需走连接池
                return createProxyConnection();
            }

        }

    }

    private static Connection createProxyConnection() throws SQLException{
        final Connection realCon = createRowConnection();
        Connection proxyCon = (Connection) Proxy.newProxyInstance(
                realCon.getClass().getClassLoader(),
                realCon.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //仅仅处理close调用的情况
                        if("close".equals(method.getName())){
                            //小于，则还回该对象
                            if(MyDataSource.currentCount < MyDataSource.maxIdleCount){
                                MyDataSource.connectionsPool.addLast( (Connection) proxy );
                                MyDataSource.currentCount++;
                                System.out.println("close归还到连接池");
                                return 1;   //连接不断
                            }
                            //大于时，即无需入池，按照目标对象处理
                            System.out.println("连接池已满，直接释放");
                        }

                        return method.invoke(realCon,args);

                    }
                }
        );

        System.out.println("----------------新创建代理连接-------------------");
        return proxyCon;

    }


}
