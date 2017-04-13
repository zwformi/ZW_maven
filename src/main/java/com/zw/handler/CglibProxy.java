package com.zw.handler;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor{

	@Override
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		// TODO Auto-generated method stub
		  System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");  
		         System.out.println(method.getName());  
		         Object o1 = methodProxy.invokeSuper(o, args);  
		         System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");  
		         return o1;  
	}


}
