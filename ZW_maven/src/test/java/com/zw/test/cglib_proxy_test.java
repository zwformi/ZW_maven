package com.zw.test;

import com.zw.handler.CglibProxy;
import com.zw.service.userservice;
import com.zw.service.userserviceimp;

import net.sf.cglib.proxy.Enhancer;

public class cglib_proxy_test {
            public static void main(String[] arg){
            	CglibProxy cglibProxy = new CglibProxy();
            	
            	Enhancer enhancer = new Enhancer();
            	enhancer.setSuperclass(userserviceimp.class);
            	enhancer.setCallback(cglibProxy);
            	
            	userservice o = (userservice)enhancer.create();
            	o.getName(1);
            	o.getAge(1);
            	
            }
}
