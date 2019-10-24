package xmlparse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xmlparse.element.ElementFactory;
import xmlparse.element.ITag;
import xmlparse.element.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlProcessHandler extends DefaultHandler {

    private ElementFactory elementFactory = ElementFactory.getFactory();
    private Map<String , Tag> tagMap;
    private List<Tag> contextTagStack;


    @Override
    public void startDocument() throws SAXException {
        tagMap = new HashMap<String, Tag>();
        contextTagStack = new ArrayList<Tag>();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("结束读取XML");
    }

    /**
     * 主要用于创建Tag
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //进入新的元素，通过栈来记录层次结构，每次新元素先和父类建立关系
        Tag currentTag = elementFactory.createTag(qName);
        tagMap.put(qName,currentTag);   //加入结果集
        Tag parentTag = getLastTag();
        if( parentTag != null){
            parentTag.addSon(currentTag);
        }
        contextTagStack.add( currentTag); //入栈
    }

    /**
     * 主要用于父类的关系构建
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //栈出
        popContextStack();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String res = tagValueProcess(ch,start,length);
        if(res != null){    //存在结果，给当前tag设置值
            getLastTag().setValue(res);
        }

    }


    static private String tagValueProcess(char[] ch, int start,  int len){
        if(len < 1){
            return null;
        }
        char[] chArr = new char[len];
        System.arraycopy(ch,start,chArr,0,len);
        String value = String.valueOf(chArr).trim();
        if(value.length() > 0){
            return value;
        }
        return null;
    }

    private Tag  getLastTag(){
        if(contextTagStack.size() > 0 ){
            return contextTagStack.get( contextTagStack.size()-1);
        } else{
            return null;
        }
    }

    private void  popContextStack(){
        int last = contextTagStack.size();
        if( last > 0 ){
            contextTagStack.remove( last -1);
        }
    }
    public Map<String , Tag> getTagMap(){
        return this.tagMap;
    }
}
