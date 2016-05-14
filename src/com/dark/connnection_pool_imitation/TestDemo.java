package com.dark.connnection_pool_imitation;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;
import javax.sql.DataSource;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月28日 上午10:57:48
 */
public class TestDemo {
	public static void main(String[] args) {
		String name = "pool";
		String driver = " sun.jdbc.odbc.JdbcOdbcDriver ";
		String url = "jdbc:odbc:datasource";
		ConnectionParam param = new ConnectionParam(driver,url,"root","joe-black");
		param.setMinConnection(1);
		param.setMaxConnection(5);
		param.setTimeoutValue(20000);
		try {
			ConnectionFactory.bind(name, param);
		} catch (NameAlreadyBoundException | ClassNotFoundException
				| IllegalAccessException | InstantiationException
				| SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("bind datasource ok.");
		//以上代码是用来登记一个连接池对象，该操作可以在程序初始化只做一次即可
		//以下开始就是使用者真正需要写的代码
		DataSource ds = null;
		try {
			ds = ConnectionFactory.lookup(name);
		} catch (NameNotFoundException e1) {
			e1.printStackTrace();
		}
		try{
		    for(int i=0;i<10;i++){
		        Connection conn = ds.getConnection();
		        try{
//		            testSQL(conn, sql);
		        }finally{
		            try{
		                conn.close();
		            }catch(Exception e){}
		        }
		    }
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
		    try {
				ConnectionFactory.unbind(name);
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
		    System.out.println("unbind datasource ok.");
		    System.exit(0);
		}
	}
}
