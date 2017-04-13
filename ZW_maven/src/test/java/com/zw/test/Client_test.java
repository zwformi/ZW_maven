package com.zw.test;

import com.zw.handler.ProxyInvocationHandler;
import com.zw.service.UserServiceImpl;

public class Client_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//真实用户
		UserServiceImpl userService = new UserServiceImpl();
		ProxyInvocationHandler pih=new ProxyInvocationHandler();
	    pih.setTarget(userService);
	    //代理用户
	    UserServiceImpl proxy=(UserServiceImpl)pih.getProxy(); 
	    
	    /*公共的方法*/
	    proxy.add();
	    
	}

}
