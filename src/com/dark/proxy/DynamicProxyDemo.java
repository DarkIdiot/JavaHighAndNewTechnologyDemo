package com.dark.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月27日 下午4:52:53
 */
public class DynamicProxyDemo {
	public static void main(String[] args) {
		IWork work = new Work();
		DynamicProxy dp = new DynamicProxy();
		IWork proxy = (IWork)dp.bind(work);
		try {
			proxy.doWork("Tom");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class DynamicProxy implements InvocationHandler {
	private Object delegate;
	private Logger log = Logger.getLogger(this.getClass().getName());
	public Object bind(Object delegate) {
		this.delegate = delegate;
		return Proxy.newProxyInstance(
				this.delegate.getClass().getClassLoader(), this.delegate
						.getClass().getInterfaces(), this);
		
	}

	/**
	 * 被代理类执行方法之前会执行该方法。
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		try {
			log.log(Level.INFO,args[0] + " is beginning to work...");
			Object result = method.invoke(this.delegate, args);
			log.log(Level.INFO,args[0] + " had worked a few minutes before... ");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.log(Level.SEVERE, args[0] + " has encountered to some problems...");
			return null;
		}finally{
			log.log(Level.INFO, args[0] + " is relaxing...");
		}
	}
}