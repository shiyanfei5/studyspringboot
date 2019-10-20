package main.com.syf.reflect;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TestT<T>{

    public List<String> list = new ArrayList<>();
    public List<T> list2 ;
    public TestT(){
       try {
           Type[] a = this.getClass().getTypeParameters();
           for(Type item : a) {
                System.out.println(item.getTypeName());
           }
           Type b = this.getClass().getGenericSuperclass();
           System.out.println(b.getTypeName());


       } catch (Exception e){
           e.printStackTrace();
       }
    }

    public static void main(String[] args){
        TestT<String > a = new TestT<>();
    }

}