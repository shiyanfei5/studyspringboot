package main.java.xmlparse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XmlTest01 {

        public static void main(String[] args) throws Exception{

            //获取解析工厂
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //解析工厂获取解析器
            SAXParser parser = factory.newSAXParser();

            //
            //编写Document注册处理器
            PHandler pHandler = new PHandler();
            parser.parse(
                    Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("test.xml"),
                    pHandler
            );






        }

        static private class PHandler extends DefaultHandler {

            @Override
            public void startDocument() throws SAXException {
                super.startDocument();
            }

            @Override
            public void endDocument() throws SAXException {
                super.endDocument();
            }

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                super.startElement(uri, localName, qName, attributes);
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                super.endElement(uri, localName, qName);
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                super.characters(ch, start, length);
            }
        }


}
