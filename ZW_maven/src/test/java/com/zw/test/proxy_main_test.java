package com.zw.test;

import java.lang.reflect.Proxy;

import com.zw.handler.MyInvocationHandler;
import com.zw.service.userservice;
import com.zw.service.userserviceimp;

public class proxy_main_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		userservice userService = new userserviceimp(); 
		MyInvocationHandler invocationHandler  = new MyInvocationHandler(userService);
		userservice userproxy = (userservice)Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(), invocationHandler);
		
		
		System.out.println(userproxy.getName(1));  
		System.out.println(userproxy.getAge(1)); 
	}

}
