package com.dark.connnection_pool_imitation;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月28日 上午11:03:58
 */
public class DataSourceImpl implements DataSource {

	private ConnectionParam connParam;
	private ConcurrentLinkedQueue<_Connection> conns;

	public DataSourceImpl(ConnectionParam param) {
		this.connParam = param;
		this.conns = new ConcurrentLinkedQueue<>();
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		if (connParam.getUser() != null && connParam.getPassword() != null) {
			return getConnection(connParam.getUser(), connParam.getPassword());
		} else {
			if (connParam.getUser() == null) {
				throw new NullPointerException("com.dark.connnection_pool_imitation.DataSourceImpl#connParam.user is null.");
			}
			if (connParam.getPassword() == null) {
				throw new NullPointerException("com.dark.connnection_pool_imitation.DataSourceImpl#connParam.password is null.");
			}
			return null;
		}
	}
	
	@Override
	public Connection getConnection(String user, String password) throws SQLException {
		Connection conn = getFreeConnection(0);
		if (conn == null) {
			// 判断是否超过最大连接数,如果超过最大连接数
			// 则等待一定时间查看是否有空闲连接,否则抛出异常告诉用户无可用连接
			if (getConnectionCount() >= connParam.getMaxConnection())
				conn = getFreeConnection(connParam.getWaitTime());
			else {// 没有超过连接数，重新获取一个数据库的连接
				connParam.setUser(user);
				connParam.setPassword(password);
				Connection conn2 = DriverManager.getConnection(
						connParam.getUrl(), user, password);
				// 代理将要返回的连接对象
				_Connection _conn = new _Connection(conn2, true);
				conns.add(_conn);
				conn = _conn.getConnection();
			}
		}
		return conn;
	}

	protected synchronized Connection getFreeConnection(long nTimeout) throws SQLException {
		Connection conn = null;
		Iterator iter = conns.iterator();
		while (iter.hasNext()) {
			_Connection _conn = (_Connection) iter.next();
			if (!_conn.isInUse()) {
				conn = _conn.getConnection();
				_conn.setInUse(true);
				break;
			}
		}
		if (conn == null && nTimeout > 0) {
			// 等待nTimeout毫秒以便看是否有空闲连接
				try {
					Thread.sleep(nTimeout);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			conn = getFreeConnection(0);
			if (conn == null)
				throw new SQLException("没有可用的数据库连接");
		}
		return conn;
	}

	/**
	 * 关闭该连接池中的所有数据库连接
	 * 
	 * @return int 返回被关闭连接的个数
	 * @throws SQLException
	 */
	public int close() throws SQLException {
		int cc = 0;
		Iterator iter = conns.iterator();
		while (iter.hasNext()) {
			((_Connection) iter.next()).close();
			cc++;
		}
		return cc;
	}

	private int getConnectionCount() {
		return conns.size();
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
