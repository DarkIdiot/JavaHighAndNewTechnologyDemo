package com.dark.connnection_pool_imitation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月28日 上午11:06:46
 */
public class _Connection implements InvocationHandler {

	 private final static String CLOSE_METHOD_NAME = "close";
	    private Connection conn = null;
	    //数据库的忙状态
	    private boolean inUse = false;
	    //用户最后一次访问该连接方法的时间
	    private long lastAccessTime = System.currentTimeMillis();
	     
	    _Connection(Connection conn, boolean inUse){
	        this.conn = conn;
	        this.inUse = inUse;
	    }
	    /**
	     * Returns the conn.
	     * @return Connection
	     */
	    public Connection getConnection() {
	        //返回数据库连接conn的接管类，以便截住close方法
	        Connection conn2 = (Connection)Proxy.newProxyInstance(
	            conn.getClass().getClassLoader(),
	            conn.getClass().getInterfaces(),this);
	        return conn2;
	    }
	    /**
	     * 该方法真正的关闭了数据库的连接
	     * @throws SQLException
	     */
	    void close() throws SQLException{
	        //由于类属性conn是没有被接管的连接，因此一旦调用close方法后就直接关闭连接
	        conn.close();
	    }
	    /**
	     * Returns the inUse.
	     * @return boolean
	     */
	    public boolean isInUse() {
	        return inUse;
	    }
	    /**
	     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object)
	     */
	    public Object invoke(Object proxy, Method m, Object[] args) 
	        throws Throwable 
	    {
	        Object obj = null;
	        //判断是否调用了close的方法，如果调用close方法则把连接置为无用状态
	        if(CLOSE_METHOD_NAME.equals(m.getName()))
	            {
	        		setInUse(false);
	        		this.clone();
	            }        
	        else
	            obj = m.invoke(conn, args); 
	        //设置最后一次访问时间，以便及时清除超时的连接
	        lastAccessTime = System.currentTimeMillis();
	        return obj;
	    }
	         
	    /**
	     * Returns the lastAccessTime.
	     * @return long
	     */
	    public long getLastAccessTime() {
	        return lastAccessTime;
	    }
	    /**
	     * Sets the inUse.
	     * @param inUse The inUse to set
	     */
	    public void setInUse(boolean inUse) {
	        this.inUse = inUse;
	    }

}
