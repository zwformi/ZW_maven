package com.zw.factory;

import com.zw.bean.Person;

public class UserFactory {
	 public static Person newInstance(String name,Integer age){
	        return new Person();
	    }
}
