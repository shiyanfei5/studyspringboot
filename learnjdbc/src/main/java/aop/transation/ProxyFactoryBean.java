package aop.transation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactoryBean {

    private TransationManager transationManager = new TransationManager();




    public Object getProxy( final Object target){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object o  = null;
                        try{
                            transationManager.beginTransation();
                            o = method.invoke(target,args);
                            transationManager.commit();
                        } catch (Exception e){
                            transationManager.rollback();   //发生异常回滚
                            e.printStackTrace();
                        } finally {
                            transationManager.release();    //发生异常也释放
                        }
                        return o;
                    }
                }
        );


    }



}
