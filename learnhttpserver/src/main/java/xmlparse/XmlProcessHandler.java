package xmlparse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xmlparse.element.ITag;
import xmlparse.element.Tag;

import java.util.*;


public class XmlProcessHandler extends DefaultHandler {

    private ElementFactory elementFactory;
    private ITag currentTag; //当前Tag
    private Map<String, List<ITag>> tagMap;
    private List<ITag> tagStack; //只要addDepth为true，表示没有闭合，层数增加

    XmlProcessHandler(){
        tagMap = new HashMap<String, List<ITag>>();
        elementFactory =  ElementFactory.getFactory();
        tagStack = new ArrayList<ITag>();
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("开始进入读取XML");
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

        currentTag  = elementFactory.createTag(qName);
        if(tagStack.size()>0){
            //存在爸爸并设置关联关系
            tagStack.get(tagStack.size()-1).setSon(currentTag);
        }
        tagStack.add(currentTag);
        tagMapPut(qName,currentTag);
    }

    /**
     * 主要用于父类的关系构建
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //元素结尾进行出栈，通过栈表示深度
        tagStack.remove(tagStack.size()-1);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = this.valueProcess(ch,start,length);
        if(value != null ){
            currentTag.setValue(value);
        }

    }

    /**
     * 结果拷贝+字符串处理..剔除空值
     * @param ch
     * @param start
     * @param length
     * @return
     */
    private String valueProcess(char[] ch, int start, int length){
        if(length<1){
            return  null;
        }
        char[] chArr = new char[length];
        System.arraycopy(ch,start,chArr,0,length);
        String str = String.valueOf(chArr).trim() ;
        return str.length()>0?str:null;

    }

    private void tagMapPut(String key,ITag tag){
        List<ITag> tagList = tagMap.get(key);
        if(tagList == null){
            List<ITag> l = new ArrayList<ITag>();
            l.add(tag);
            tagMap.put(key, l);
        } else{
            tagList.add(tag);
        }
    }

    public void getResultMap(){
        System.out.println(tagMap);
    }

}
