package com.zw.service;

public class userserviceimp implements userservice{

	@Override
	public String getName(int id) {
		// TODO Auto-generated method stub
		System.out.println("<====调用getName方法====>");
		return "Tom";
	}

	@Override
	public Integer getAge(int id) {
		// TODO Auto-generated method stub
		System.out.println("<====调用getAge方法====>");
		return 10;
	}

}
