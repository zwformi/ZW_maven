package com.zw.bean;

public class Person {
    private String name;
    private Integer age; 
    
/*	public Person(String name2, Integer age2) {
		// TODO Auto-generated constructor stub
		System.out.println("有参构造方法！！");
		this.name=name2;
		this.age=age2;
	}*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
    
    public void info(){
    	System.out.println("大家好呀！");
    	System.out.println("我是"+this.getName()+",年龄"+this.getAge());
    }
	
	
}
