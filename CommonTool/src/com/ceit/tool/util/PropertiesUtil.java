package com.ceit.tool.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	
	/**
	 * 验证文件名是否是.properties文件
	 * @param fileName
	 *        文件名
	 * @return
	 */
	private static boolean testFileName(String fileName) {
		if(!fileName.matches(".*\\.properties"))
			return false;
		return true;
	}
	
	/**
	 * 向properties文件中添加或者修改属性
	 * @param fileName
	 *        文件名
	 * @param key
	 *        属性名
	 * @param value
	 *        属性值
	 * @param comments
	 *        备注
	 */
	public static void Write(String fileName, String key, String value, String comments) {
		if(!testFileName(fileName))
			return;
		
		// 属性集合对象
		Properties prop = new Properties();
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			// 文件输入流
			fis = new FileInputStream(fileName);  
			// 文件输出流   
	        fos = new FileOutputStream(fileName);
	        
	        // 将属性文件流装载到Properties对象中
			prop.load(fis);  
			
			//添加或者修改属性
			prop.setProperty(key, value);
	        
	        // 将Properties集合保存到流中   
	        prop.store(fos, comments);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();  // 关闭文件输入流
				fos.close();  // 关闭文件输出流
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}
	
	/**
	 * 从properties文件中读取一个属性的值
	 * @param fileName
	 *        文件名
	 * @param key
	 *        属性名
	 * @return
	 *        发生异常或没找到该属性时返回null
	 */
	public static String read(String fileName, String key) {
		if(!testFileName(fileName))
			return null;
		
		// 属性集合对象
		Properties prop = new Properties();
		FileInputStream fis = null;
		String value = null;
		try {
			// 文件输入流
			fis = new FileInputStream(fileName);  
			
	        // 将属性文件流装载到Properties对象中
			prop.load(fis);
			
			value = prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();  // 关闭文件输入流
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
		
		return value;
	}
	
	/**
	 * 获得Properties对象
	 * @param fileName
	 *        文件名
	 * @return
	 */
	public static Properties getProps(String fileName) {
		if(!testFileName(fileName))
			return null;
		
		// 属性集合对象
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			// 文件输入流
			fis = new FileInputStream(fileName);  
			
	        // 将属性文件流装载到Properties对象中
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();  // 关闭文件输入流
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
		
		return prop;
	}
}
