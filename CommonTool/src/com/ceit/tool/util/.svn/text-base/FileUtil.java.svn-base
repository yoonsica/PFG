package com.ceit.tool.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.regex.Pattern;

public class FileUtil {
	
	/**
	 * 获得上级路径
	 * @return
	 * @throws IOException
	 */
	public static String getSuperiorPath() throws IOException {
		File directory = new File("..");  //设定为当前文件夹 
//		return System.getProperty("user.dir");//user.dir指定了当前的路径
		return directory.getCanonicalPath();  //获取当前路径 
	}
	/**
	 * 获取当前路径
	 * @return
	 */
	public static String getCurrentPath() {
		File directory = new File("");  //设定为当前文件夹 
		return directory.getAbsolutePath();  //获取绝对路径 
	}
	/**
	 * 判断文件名是否合法（不能为空且不能包含以下字符：/\:?*|<>"）
	 * @param name
	 *        文件名
	 * @return
	 */
	public static boolean isNameLegal(String name) {
		if(name.indexOf(".") == -1)
			return false;
		if(name.lastIndexOf(".") == 0 || name.lastIndexOf(".") == (name.length() - 1))
			return false;
		String[] strs = {"/", "\\", ":", "?", "*",  "|", "<", ">", "\""};
		for(String s : strs)
			if(name.contains(s))
				return false;
		return true;
	}
	
	/**
	 * 判断文件类型是否符合要求
	 * @param name
	 *        文件名
	 * @param types
	 *        需要的类型
	 * @return
	 */
	public static boolean isTypeRight(String name, String...types) {
		//找到最后一个.的位置，则后面的就是该文件的类型
		int index = name.lastIndexOf(".");
		String type = name.substring(index + 1);
		for(String t : types)
			if(type.equals(t))
				return true;;
		return false;
	}
	
	/**
	 * 给定的路径是否合法
	 * @param path
	 *        完整路径
	 * @return
	 */
	public static boolean isPathLegal(String path) {
		return new File(path).isDirectory();
	}
	
	/**
	 * 查找一个文件在某个路径下是否存在
	 * @param path 
	 *        完整路径
	 * @param name
	 *        文件名
	 * @return
	 */
	public static boolean find(String path, String name) {
		String fileName = path + File.separator + name;
		File f = new File(fileName);
		return f.exists();
	}
	
	/**
	 * 查找一个文件是否存在
	 * @param fileName
	 *        文件的完整名称，包括路径及名称
	 * @return
	 */
	public static boolean find(String fileName) {
		File f = new File(fileName);
		return f.exists();
	}
	
	public static boolean rename(String path, String oldName, String newName) {
		String oldFileName = path + File.separator + oldName;
		
		File oldFile = new File(oldFileName);
		if(oldFile.exists()) {
			String newFileName = path + File.separator + newName;
			File newFile = new File(newFileName);
			if(newFile.exists())
				return false;
			oldFile.renameTo(newFile);
			return true;
		} else
			return false;
	}
	/**
	 * 删除文件
	 * @param path
	 *        文件的路径
	 * @param name
	 *        文件名
	 * @return
	 */
	public static boolean delete(String path, String name) {
		String fileName = path + File.separator + name;
		File f = new File(fileName);
		if(f.exists()) {
			f.delete();
			return true;
		} else
			return false;
	}
	
	/**
	 * 把文件复制到目标路径
	 * @param path
	 *        原路径
	 * @param name
	 *        文件名
	 * @param destPath
	 *        目标路径
	 * @return
	 * @throws IOException
	 */
	public static boolean copy(String path, String name, String destPath) throws IOException {
		String oldFileName = path + File.separator + name;
		String newFileName = destPath + File.separator + name;
		File oldFile = new File(oldFileName);
		File newFile = new File(newFileName);
		
		if(oldFile.exists()) {
			//若新的路径已存在该文件，则返回false
			if(newFile.exists())
				return false;
			
			//把文件复制到新的位置
			InputStream is = new FileInputStream(oldFile);
			OutputStream os = new FileOutputStream(newFile);
			if((is != null) && (os != null)) {
				int c = 0;
				while((c = is.read()) != -1) {
					os.write(c);
				}
			}
			os.close();
			is.close();
			
			return true;
		} else
			return false;
	}
	
	/**
	 * 把文件移至目标路径
	 * @param path
	 *        原路径
	 * @param name
	 *        文件名
	 * @param destPath
	 *        目标路径
	 * @return
	 * @throws IOException
	 */
	public static boolean move(String path, String name, String destPath) throws IOException {
		if(move(path, name, destPath)) {
			//删除原文件
			delete(path, name);
			return true;
		} else
			return false;
	}
	
	/**
	 * 用平台无关的分隔符连接路径和文件
	 * @param path
	 *        路径名
	 * @param name
	 *        文件名
	 * @return
	 */
	public static String combine(String path, String name) { 
		StringBuilder sbud = new StringBuilder();
		sbud.append(path).append(File.separator).append(name);
		return sbud.toString();
	}
	
	/**
	 * 获得一个目录里的所有文件(包括隐藏文件)
	 * @param pathName
	 *        路径名
	 * @return
	 *        文件的集合
	 */
	public static File[] fileList(String pathName) {
		File path = new File(pathName);
		File[] fileList = path.listFiles();
		return fileList;
	}
	
	/**
	 * 获得一个目录里的经过筛选后的文件
	 * @param pathName
	 *        路径名
	 * @param regex
	 *        正则表达式
	 * @return
	 */
	public static File[] fileList(String pathName, final String regex) {
		File path = new File(pathName);
		File[] files = path.listFiles(new FileFilter() {
			/**
			 * 查询出了几个文件就循环几次
			 */
			@Override
			public boolean accept(File pathname) {
				Pattern p = Pattern.compile(regex);
				return p.matcher(pathname.getName()).matches();
			}
		});
		return files;
	}
	
	/**
	 * 获得一个目录里的经过筛选后的文件
	 * @param pathName
	 *        路径名
	 * @param regex
	 *        正则表达式
	 * @param flags
	 *        匹配的标志
	 * @return
	 */
	public static File[] fileList(String pathName, final String regex, final int flags) {
		File path = new File(pathName);
		File[] files = path.listFiles(new FileFilter() {
			/**
			 * 查询出了几个文件就循环几次
			 */
			@Override
			public boolean accept(File pathname) {
				Pattern p = Pattern.compile(regex, flags);
				return p.matcher(pathname.getName()).matches();
			}
		});
		return files;
	}
	
	public static void readBinary(String fileName) throws Exception {
		FileChannel fc = new FileInputStream(new File(fileName)).getChannel();
		IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
		while(ib.hasRemaining()) {
			System.out.print((char) ib.get());
		}
	}
	
	/**
	 * 读取txt文本信息
	 * @param fileName
	 *        完整文件名
	 * @param charsetName
	 *        字符集名称
	 * @return
	 * @throws Exception
	 */
	public static String readText(String fileName, String charsetName) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)), charsetName)); 
		StringBuilder sbud = new StringBuilder();
		String line;
		while((line = in.readLine())!=null) {
			sbud.append(line).append("\n");
		}
		return sbud.toString();
	}
	
	/**
	 * 读取Sql语句并将其转换成java能直接读取的stringbuilder.append()类型
	 * @param fileName
	 *        完整文件名
	 * @param sbudName
	 *        java中使用的StringBuilder的实例名
	 * @param charsetName
	 *        字符集名称
	 * @return
	 * @throws Exception
	 */
	public static String readSql(String fileName, String sbudName, String charsetName) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)), charsetName)); 
		StringBuilder sbud = new StringBuilder();
		String line;
		while((line = in.readLine())!=null) {
			if(line.equals(""))
				sbud.append("\n");
			else
				sbud.append(sbudName).append(".append(\"").append(line.replaceAll("\"", "\\\\\"")).append(" \");\n");
		}
		return sbud.toString().replaceAll("'\\d{4}-\\d{2}-\\d{2}'", ":queryDate");
	}
	
	public static String transJQuery(String oldFilename, String newFilename, String charsetName) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(oldFilename)), charsetName)); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(newFilename))));
		StringBuilder sbud = new StringBuilder();
		String line;
		if((line = in.readLine())!=null) {
			if(line.equals(""))
				sbud.append("\n");
			else {
				line.replaceAll("^\\d+ *", "");
				bw.write(line);
			}
		}
		in.close();
		return "";
	}
	
}
