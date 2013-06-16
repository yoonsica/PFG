package com.ceit.tool.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLUtil {
	
	/**
	 * 获取XML文件中某个节点的某个属性值
	 * @param propertyName
	 *        属性的完整路径，如/books/book/@name （book是一个节点，name是book的一个属性）
	 * @param fileName
	 *        XML文件名
	 * @return
	 *        该属性所有值的List
	 */
	public static List<String> getAttrOfNode(String fileName, String propertyName) {
		List<String> list = new ArrayList<String>();
		try{
			SAXReader saxReader = new SAXReader();
	        Document document = saxReader.read(new File(fileName));
	        @SuppressWarnings("unchecked")
			List<Attribute> tempList = document.selectNodes(propertyName);
	        Iterator<Attribute> iter = tempList.iterator();
	        
			while (iter.hasNext()) {
				Attribute attr = iter.next();
				list.add(attr.getValue());
			}
//	        	while(iter.hasNext()) {
//	        		 Element elem = (Element) iter.next();		 
//	        		 list.add(elem);
//	        	 }
		} catch (Exception e) {
			System.err.println("获取节点的属性失败！");
			e.printStackTrace();
		}
		
        return list;
	}
	
	/**
	 * 获取XML文件中某个节点的所有子节点
	 * @param propertyName
	 *        节点的完整路径，如/books/book
	 * @param fileName
	 *        XML文件名
	 * @return
	 *        该属性所有值的List
	 */
	public static List<Element> getElemOfNode(String fileName, String propertyName) {
		List<Element> list = new ArrayList<Element>();
		try{
			SAXReader saxReader = new SAXReader();
	        Document document = saxReader.read(new File(fileName));
	        @SuppressWarnings("unchecked")
			List<Element> tempList = document.selectNodes(propertyName);
	        Iterator<Element> iter = tempList.iterator();
	        
			while (iter.hasNext()) {
				Element elem = iter.next();
				list.add(elem);
			}
		} catch (Exception e) {
			System.err.println("获取节点的属性失败！");
			e.printStackTrace();
		}
		
        return list;
	}
}
