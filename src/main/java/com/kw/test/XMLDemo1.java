package com.kw.test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class XMLDemo1 {

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        //创建dom4j解析器对象

        SAXReader saxReader = new SAXReader();

        //通过字节流加载硬盘中的xml文件到内存

        Document xmlDocument = saxReader.read(new FileInputStream(new File("E:\\result.xml")));

        //获取根节点

        Element rootElement = xmlDocument.getRootElement();

        //获取根节点写所有CONTET字节点形成的集合

        List<Element> list = rootElement.elements("CONTENT");

        List<Element> Mlist = rootElement.elements("MEG");

        //迭代

        for(Element element : Mlist) {


            String CODE = element.element("CODE").getText().trim();

            System.out.println(CODE);

        }

        for(Element element : list) {


            String CARDNO = element.element("CARDNO").getText().trim();

            String ADDRESS = element.element("ADDRESS").getText().trim();

            String MADEDATE = element.element("MADEDATE").getText().trim();

            String PRICE = element.element("PRICE").getText().trim();


            String VOL = element.element("VOL").getText().trim();



            //将来我们可以将上述属性值封装到JavaBean对象中的所有属性中，

            //加入到List<JavaBean>集合中

            System.out.println(CARDNO);
            System.out.println(ADDRESS);
            System.out.println(MADEDATE);
            System.out.println(PRICE);
            System.out.println(VOL);

            System.out.println("-------");

        }
    }
}
