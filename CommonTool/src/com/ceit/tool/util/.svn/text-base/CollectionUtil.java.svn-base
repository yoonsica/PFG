package com.ceit.tool.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * 用于实例化集合类型（List，Map等）的对象，
 * 使用静态工厂方法不需要在等号右边重新输入泛型的实现类型，
 * 直接调用无参数的静态工厂方法就能实例化集合类型的对象。
 * @author xy
 * @date 2012-10-25
 *
 */
public class CollectionUtil {
	
	/**
	 * 生成ArrayList类型的实例
	 * @param <K>
	 * @return
	 */
	public static <K> ArrayList<K> newInstanceArrayList(){
		return new ArrayList<K>();
	}
	
	/**
	 * 生成LinkedList类型的实例
	 * @param <K>
	 * @return
	 */
	public static <K> LinkedList<K> newInstanceLinkedList(){
		return new LinkedList<K>();
	}
	
	/**
	 * 生成HashMap类型的实例
	 * @param <K>
	 * @param <V>
	 * @return
	 */
	public static <K,V> HashMap<K,V> newInstanceHashMap(){
		return new HashMap<K,V>();
	}
	
	/**
	 * 生成LinkedHashMap类型的实例
	 * @param <K>
	 * @param <V>
	 * @return
	 */
	public static <K,V> LinkedHashMap<K,V> newInstanceLinkedHashMap(){
		return new LinkedHashMap<K,V>();
	}

}
