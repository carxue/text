package com.test;

 import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class WordToHtml {

	String filepath = "C:/test/";
	         final String imagepath = "C:/test/image/";
	          String fileName = "滕王阁序2003.doc";
	          String htmlName = "滕王阁序2003.html";
	          final String file = filepath + fileName;
	          InputStream input = new FileInputStream(new File(file));
	          HWPFDocument wordDocument = new HWPFDocument(input);
	          WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
	          //设置图片存放的位置
	          wordToHtmlConverter.setPicturesManager(new PicturesManager() {
	              public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
	                File imgPath = new File(imagepath);
	                 if(!imgPath.exists()){//图片目录不存在则创建
	                     imgPath.mkdirs();
	                 }
	                 File file = new File(imagepath + suggestedName);
	                 try {
	                     OutputStream os = new FileOutputStream(file);
	                    os.write(content);
	                    os.close();
	                } catch (FileNotFoundException e) {
	                     e.printStackTrace();
	                 } catch (IOException e) {
	                     e.printStackTrace();
	                 }
	                 return imagepath + suggestedName;
	             }
	         });
	         
	         //解析word文档
	         wordToHtmlConverter.processDocument(wordDocument);
	         Document htmlDocument = wordToHtmlConverter.getDocument();
	         
	         File htmlFile = new File(filepath + htmlName);
	         OutputStream outStream = new FileOutputStream(htmlFile);
	         
	         //也可以使用字符数组流获取解析的内容
	 //        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
	 //        OutputStream outStream = new BufferedOutputStream(baos);
	 
	         DOMSource domSource = new DOMSource(htmlDocument);
	         StreamResult streamResult = new StreamResult(outStream);
	 
	         TransformerFactory factory = TransformerFactory.newInstance();
	         Transformer serializer = factory.newTransformer();
	         serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
	         serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	         serializer.setOutputProperty(OutputKeys.METHOD, "html");
	         
	         serializer.transform(domSource, streamResult);
	 
	         //也可以使用字符数组流获取解析的内容
	 //        String content = baos.toString();
	 //        System.out.println(content);
	 //        baos.close();
	         outStream.close();
}
