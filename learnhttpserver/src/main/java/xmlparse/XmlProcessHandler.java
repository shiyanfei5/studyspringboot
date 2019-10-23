package xmlparse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xmlparse.element.ITag;

public class XmlProcessHandler extends DefaultHandler {

    private ElementFactory elementFactory = ElementFactory.getFactory();
    private ITag parentTag; //父亲Tag
    private ITag currentTag; //当前Tag

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
    }

    /**
     * 主要用于父类的关系构建
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(parentTag !=null ){
            parentTag.setSon(currentTag);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        char[] c = new char[length];
        System.arraycopy(ch,start,c,0,length);
        System.out.println("开始"+start+"长度"+length+"内容"+String.valueOf(c));
    }
}
