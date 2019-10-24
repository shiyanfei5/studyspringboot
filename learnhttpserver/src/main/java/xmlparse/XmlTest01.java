package xmlparse;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XmlTest01 {

        public static void main(String[] args) throws Exception{

            //获取解析工厂
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //打开识别命名空间的功能
            factory.setNamespaceAware(true);
            //解析工厂获取解析器
            SAXParser parser = factory.newSAXParser();


            //
            //编写Document注册处理器
            XmlProcessHandler pHandler = new XmlProcessHandler();

            parser.parse(
                    Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("test.xml"),
                    pHandler
            );

            pHandler.t();






        }


}
