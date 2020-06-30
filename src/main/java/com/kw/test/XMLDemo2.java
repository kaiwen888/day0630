package com.kw.test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.List;

public class XMLDemo2 {

    public static void main(String[] args) throws IOException, DocumentException {
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


            element.element("CODE").setText("1");

        }

        for(Element element : list) {

            element.element("ADDRESS").setText("河南");

            element.element("MADEDATE").setText("2020-04-14 18:29:30.33");

            element.element("PRICE").setText("45");

            element.element("VOL").setText("60%");

        }

        //上述修改只在内存中，还得将内存中的document对象写入硬盘

        OutputFormat format = OutputFormat.createPrettyPrint();

        //指定xml编码

        //format.setEncoding("gbk");    通常并不使使用该函数

        OutputStream os = new FileOutputStream(new File("E:\\result.xml"));

        XMLWriter xmlWriter = new XMLWriter(os,format);

        xmlWriter.write(xmlDocument);

        xmlWriter.close();
    }
}
