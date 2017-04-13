package com.zw.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyInvocationHandler implements InvocationHandler{
	 //目标对象--真实对象
    private Object target;
    public void setTarget(Object target) {
        this.target = target;
    }
    /**
     * 生成代理类:
     */
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
     
    /**
     * proxy--是代理类
     * method--代理类的实例(proxy)调用的处理程序(add)的方法对象
     * 比如调用proxy.add():此处method.getName就是add
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) 
            throws Throwable {
    	seeHouse();
        log(method.getName());
        fare();
        Object result=method.invoke(target, args);
        return result;
    }
    public void log(String methodName){
        System.out.println("执行"+methodName+"方法");
    }
    //看房
    @SuppressWarnings("unused")
	private void seeHouse(){
        System.out.println("带房客看房");
    }
    //收中介费
    @SuppressWarnings("unused")
	private void fare(){
        System.out.println("收取中介费");
    }
	
	
	
}
