package com.dark.connnection_pool_imitation;

import java.sql.SQLException;
import java.util.Hashtable;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;
import javax.sql.DataSource;

/**
 * 连接池类厂，该类常用来保存多个数据源名称合数据库连接池对应的哈希
 * @author idiot
 * @version 1.0
 * @date 2016年1月28日 上午10:04:41
 */
public class ConnectionFactory
{
    //该哈希表用来保存数据源名和连接池对象的关系表
    static Hashtable connectionPools = null;
    static{
        connectionPools = new Hashtable(2,0.75F);
    } 
    /**
     * 从连接池工厂中获取指定名称对应的连接池对象
     * @param name    连接池对象对应的名称
     * @return DataSource   返回名称对应的连接池对象
     * @throws NameNotFoundException    无法找到指定的连接池
     */
    public static DataSource lookup(String name) 
        throws NameNotFoundException
    {
        Object ds = null;
        ds = connectionPools.get(name);
        if(ds == null || !(ds instanceof DataSource))
            throw new NameNotFoundException(name);
        return (DataSource)ds;
    }
    /**
     * 将指定的名字和数据库连接配置绑定在一起并初始化数据库连接池
     * @param name      对应连接池的名称
     * @param param 连接池的配置参数，具体请见类ConnectionParam
     * @return DataSource   如果绑定成功后返回连接池对象
     * @throws NameAlreadyBoundException    一定名字name已经绑定则抛出该异常
     * @throws ClassNotFoundException       无法找到连接池的配置中的驱动程序类
     * @throws IllegalAccessException       连接池配置中的驱动程序类有误
     * @throws InstantiationException       无法实例化驱动程序类
     * @throws SQLException             无法正常连接指定的数据库
     */
    public static DataSource bind(String name, ConnectionParam param)
        throws NameAlreadyBoundException,ClassNotFoundException,
                IllegalAccessException,InstantiationException,SQLException
    {
        DataSource source = null;
        try{
        	source = lookup(name);
            throw new NameAlreadyBoundException(name);
        }catch(NameNotFoundException e){
            source = new DataSourceImpl(param);
            connectionPools.put(name, source);
        }
        return source;
    }
    /**
     * 重新绑定数据库连接池
     * @param name      对应连接池的名称
     * @param param 连接池的配置参数，具体请见类ConnectionParam
     * @return DataSource   如果绑定成功后返回连接池对象
     * @throws NameAlreadyBoundException    一定名字name已经绑定则抛出该异常
     * @throws ClassNotFoundException       无法找到连接池的配置中的驱动程序类
     * @throws IllegalAccessException       连接池配置中的驱动程序类有误
     * @throws InstantiationException       无法实例化驱动程序类
     * @throws SQLException             无法正常连接指定的数据库
     */
    public static DataSource rebind(String name, ConnectionParam param)
        throws NameAlreadyBoundException,ClassNotFoundException,
                IllegalAccessException,InstantiationException,SQLException
    {
        try{
            unbind(name);
        }catch(NameNotFoundException e){
        	e.printStackTrace();
        }
        return bind(name, param);
    }
    /**
     * 删除一个数据库连接池对象
     * @param name
     * @throws NameNotFoundException
     */
    public static void unbind(String name) throws NameNotFoundException
    {
        DataSource dataSource = lookup(name);
        if(dataSource instanceof DataSourceImpl){
            DataSourceImpl dsi = (DataSourceImpl)dataSource;
            try{
                dsi.close();
            }catch(SQLException e){
            	e.printStackTrace();
            }finally{
                dsi = null;
            }
        }
        connectionPools.remove(name);
    }
     
}