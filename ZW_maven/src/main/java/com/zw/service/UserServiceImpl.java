package com.zw.service;

import com.zw.dao.UserDao_Impl;

public class UserServiceImpl implements UserService_Impl {
	private UserDao_Impl userdao;
	public void setUserdao(UserDao_Impl userdao) {
		this.userdao = userdao;
	}
	@Override
	public void getUser() {
		// TODO Auto-generated method stub
		userdao.getUser();
	}
	public void add() {
		// TODO Auto-generated method stub
		System.out.println("同意出租");
	}
	

}
