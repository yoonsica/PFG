package com.ceit.tool.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodUtil {
	/**
	 * 使用属性名来获得属性值
	 * @param obj
	 * @param field
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object getValueByPropertyName(Object obj,String field) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
	
			Class clazz = obj.getClass();
			PropertyDescriptor pd = new PropertyDescriptor(field, clazz);
			Method getMethod = pd.getReadMethod();// 获得get方法
			Object o = getMethod.invoke(obj);
			return o;
		
	}
	/**根据属性名来设置属性值
	 * 
	 * @param obj
	 * @param field
	 * @param value
	 * @throws IntrospectionException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void setValueByPropertyName(Object obj,String field,Object value) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
		
		Class clazz = obj.getClass();
		PropertyDescriptor pd = new PropertyDescriptor(field, clazz);
		Method setMethod = pd.getWriteMethod();// 获得get方法
		Class<?>[] cla=setMethod.getParameterTypes();
		String type = cla[0].getName();
		//if(type.equals("java.long.String"))
		//Object o = setMethod.invoke(obj,value);
		if(type.equals("int")){
			setMethod.invoke(obj,Integer.parseInt(value.toString()));
		}else if(type.equals("float")){
			setMethod.invoke(obj,Float.parseFloat(value.toString()));
		}else if(type.equals("java.lang.String")){
			setMethod.invoke(obj,value.toString());
		}
	
}

}
