package class01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HelloJDBC {


    public static void connect1 (){
        Connection c = null;
        Statement s = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection(
                    "jdbc:mysql://132.232.54.199:3306/studyjpa?characterEncoding=UTF-8",
                    "root",
                    "123456"
            );

            s = c.createStatement();
            s.execute("select 1 from dual;");

        }catch (ClassNotFoundException e){
            System.out.println("加载驱动失败");
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            // 1关闭statement
            if( s!= null){
                try{
                    s.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

            // 2.关闭Connection
            if(c != null ){
                try{
                    c.close();
                } catch ( SQLException e){
                    e.printStackTrace();
                }
            }

        }
    }

    public static void connect2 (){

        try {
            Class.forName("com.mysql.jdbc.Driver");

        }catch (ClassNotFoundException e){
            System.out.println("加载驱动失败");
            e.printStackTrace();
        }

        try(Connection c = DriverManager.getConnection(
                "jdbc:mysql://132.232.54.199:3306/studyjpa?characterEncoding=UTF-8",
                "",
                ""
            );
            Statement s = c.createStatement();
        )
        {

            for(int i = 0; i < 100;i++){
                String sql = "insert into hero(name,hp,damage) values('提莫',313,50)";
                s.execute(sql);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static  void main(String[] args){
        List<String > test = new ArrayList<>();
        test.add("mytest");
        test.add("mytest2");
        test.add("mytest3");
        test.add("mytest4");
        List<String> test2 = test.stream().filter( p -> p.contains("mytest")).collect(Collectors.toList());
        List<String> test3 = test.stream().filter( p -> p.contains("11mytest")).collect(Collectors.toList());
        System.out.println(test2);
        System.out.println(test3);






    }



}
