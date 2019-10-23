package xmlparse;

import xmlparse.element.Book;
import xmlparse.element.ITag;

//单例
public class ElementFactory {
    //储存单例，类加载时进行初始化
    private  static  ElementFactory factory = new ElementFactory();
    private ElementFactory(){
    }
    //获取工厂
    static public ElementFactory getFactory(){
        return factory;
    }

    public ITag createTag(String tagName){
        if("book".equals("book")){
            return new Book();
        }
        return null;
    }





}
