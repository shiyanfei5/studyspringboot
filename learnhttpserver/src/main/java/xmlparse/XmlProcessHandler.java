package xmlparse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlProcessHandler extends DefaultHandler {
    /**
     * {
     *     "tagName": 对象 {
     *         1.parent
     *         2.
     *     },
     *     "tagName":[]
     *     "tagName":[]
     * }
     *
     * @throws SAXException
     */

    @Override
    public void startDocument() throws SAXException {
        System.out.println("开始进入读取XML");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("结束读取XML");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println(attributes.getLength());
        System.out.println(attributes.getLocalName(0));
        System.out.println(attributes.getQName(0));
        System.out.println(attributes.getValue(0));

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(uri+localName+qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

    }
}
