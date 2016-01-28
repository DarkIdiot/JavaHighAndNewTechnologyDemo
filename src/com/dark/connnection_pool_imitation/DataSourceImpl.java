package com.dark.connnection_pool_imitation;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * @author idiot
 * @version 1.0
 * @date 2016��1��28�� ����11:03:58
 */
public class DataSourceImpl implements DataSource {

	private PrintWriter out;

	private ConnectionParam connParam;
	private Set<_Connection> conns = new HashSet<_Connection>();

	private String user;

	private String password;

	private int loginTimeout;

	public DataSourceImpl(ConnectionParam param) {
		this.connParam = param;
		user = param.getUser();
		password = param.getPassword();
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return this.out;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		this.out = out;
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		this.loginTimeout = seconds;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return this.loginTimeout;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		try {
			return iface.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = getFreeConnection(0);
		if (conn == null) {
			// �ж��Ƿ񳬹����������,����������������
			// ��ȴ�һ��ʱ��鿴�Ƿ��п�������,�����׳��쳣�����û��޿�������
			if (getConnectionCount() >= connParam.getMaxConnection())
				conn = getFreeConnection(connParam.getWaitTime());
			else {// û�г��������������»�ȡһ�����ݿ������
				connParam.setUser(user);
				connParam.setPassword(password);
				Connection conn2 = DriverManager.getConnection(
						connParam.getUrl(), user, password);
				// ����Ҫ���ص����Ӷ���
				_Connection _conn = new _Connection(conn2, true);
				synchronized (conns) {
					conns.add(_conn);
				}
				conn = _conn.getConnection();
			}
		}
		return conn;
	}

	private int getConnectionCount() {
		return conns.size();
	}

	protected synchronized Connection getFreeConnection(long nTimeout)
			throws SQLException {
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
			// �ȴ�nTimeout�����Ա㿴�Ƿ��п�������
			try {
				Thread.sleep(nTimeout);
			} catch (Exception e) {
			}
			conn = getFreeConnection(0);
			if (conn == null)
				throw new SQLException("û�п��õ����ݿ�����");
		}
		return conn;
	}

	/**
	 * �رո����ӳ��е��������ݿ�����
	 * 
	 * @return int ���ر��ر����ӵĸ���
	 * @throws SQLException
	 */
	public int close() throws SQLException {
		int cc = 0;
		SQLException excp = null;
		Iterator iter = conns.iterator();
		while (iter.hasNext()) {
			try {
				((_Connection) iter.next()).close();
				cc++;
			} catch (Exception e) {
				if (e instanceof SQLException)
					excp = (SQLException) e;
			}
		}
		if (excp != null)
			throw excp;
		return cc;
	}
	
	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		try {
			Class.forName(connParam.getDriver());
		} catch (ClassNotFoundException e) {
			System.err.println("�Ҳ������������� ����������ʧ�ܣ�");
			e.printStackTrace();
		}
		return DriverManager.getConnection(connParam.getUrl(), username,
				password);
	}
}
