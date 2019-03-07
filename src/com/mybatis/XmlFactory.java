package com.mybatis;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlFactory {
	public static void main(String[] args) throws Exception {
		String path = "D:\\aa.xml";
		XmlFactory.createXml(path);
		readXml(path,XmlFactory.addXmlElement(path));
	}
	
	private static long Num(long number){
		if(number==1)
			return 1;
		else{
			return number*Num(number-1);
		}
	}
	
	private static void digui(Node node){
		int i=0;
		List<Element> elements = ((Element)node).elements();
		for(Element e:elements){
			System.out.println((i++)+"-"+e.getName()+":"+e.getTextTrim());
			digui(e);
		}
	}
	
	public static void readXml(String path,String xmlStr) throws IOException {
//		InputStream is = null;
		XMLWriter writer = null;
		try{
//			is = new FileInputStream(path);
			Document document = DocumentHelper.createDocument();
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(new ByteArrayInputStream(xmlStr.getBytes()));
			Element root = document.getRootElement();
			digui(root);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
	
	private static String getCurTime(){
		DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS");
		return df.format(new Date());
	}

	public static void createXml(String path) throws IOException {
		File file = new File(path);
		if (!file.exists())
			file.createNewFile();
		OutputStream fos = new FileOutputStream(file, false);
		fos.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes());
		fos.write("<information>\n".getBytes());
		fos.write("</information>".getBytes());
		fos.flush();
		fos.close();
	}

	public static String addXmlElement(String path) throws Exception{
		
		String xmlStr = null;
		InputStream is = null;
		XMLWriter writer = null;
		try{
			is = new FileInputStream(path);
			Document document = DocumentHelper.createDocument();
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(is);
			
			Element root = document.getRootElement();
			
			root.addElement("company").addText("北京宝润兴业");
			root.addElement("create_time").addText(XmlFactory.getCurTime());
			root.addElement("update_time").addText(XmlFactory.getCurTime());
			
			
			//
			Element data = root.addElement("data");
			Element info = data.addElement("person");
			info.addAttribute("id", "1");
			Element name = info.addElement("name");
			name.addText("薛奎");
			Element age = info.addElement("age");
			age.addText("20");
			Element address = info.addElement("address");
			address.addElement("province").addText("湖北");
			address.addElement("city").addText("襄阳");
			//
			Element info1 = data.addElement("person");
			info1.addAttribute("id", "2");
			Element name1 = info1.addElement("name");
			name1.addText("lsnow");
			Element age1 = info1.addElement("age");
			age1.addText("22");
			Element address1 = info1.addElement("address");
			address1.addElement("province").addText("湖北");
			address1.addElement("city").addText("仙桃");
			
			
			
			org.dom4j.io.OutputFormat format=OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");//这就是已经设置了输出格式或编码集
			format.setSuppressDeclaration(true);
			format.setIndent(true);//是否缩进
			format.setIndent("   ");//以空格方式缩进
			format.setNewlines(true);//设置是否换行
		    //实例化MLWriter对象
			writer =new XMLWriter(new FileOutputStream(path,false), format);
	        writer.write(document);xmlStr = document.asXML();
	        writer.flush(); 
        }finally{
        	if(null!=writer)
        		writer.close();
        	if(null!=is)
        		is.close();
        }
		return xmlStr;
		
	}
}
