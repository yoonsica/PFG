package com.vic.ico.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AddChartHandler implements InvocationHandler{

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		method.invoke(proxy, args);
		return null;
	}

}
