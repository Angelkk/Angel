package org.Angel.www.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * 主要功能：获取数据库的连接对象（使Connection能够在多线程环境下工作（从线程不安全---》线程安全））
 * 		
 * */
public class JdbcUtils {

	private final static String URL = "jdbc:mysql://127.0.0.1:3306/attendance_db?characterEncoding=utf8";
	private final static String USER = "root";
	private final static String PASSWORD = "123456";

	// static特性：随着类加载而加载，这要这个类加载，JVM的内存里面就有一个ThreadLocal对象，并且这个ThreadLocal对象永远存在，除非JVM退出
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	static {
		// 注册驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 此方法用于获取数据库连接对象
	 */
	public static Connection getConn() {

		try {
			// 得到当前线程上绑定的连接
			Connection conn = tl.get();
			if (conn == null) { // 代表线程上没有绑定连接
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				tl.set(conn);
			}
			return conn;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void startTransaction() {
		try {
			// 得到当前线程上绑定的连接，并开启事务
			Connection conn = getConn();
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void commitTransaction() {
		try {
			// 得到当前线程上绑定的连接，并提交事务
			Connection conn = tl.get();
			if (conn != null) { // 代表当前线程上绑定了连接，当前线程有连接才提交，当前线程没有连接就不用提交
				conn.commit();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 关闭连接
	public static void closeConnection() {
		try {
			// 得到当前线程上绑定的连接，并关闭该连接
			Connection conn = tl.get();
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			/*
			 * 关闭连接之后，即还给数据库连接池了，还要从ThreadLocal容器里面移除掉这个连接。
			 * 
			 * 如果不移除，会有什么问题？ 有一个线程来执行了转账，ThreadLocal的map集合里面就有一个连接，
			 * 第二个线程又来，ThreadLocal的map集合里面又有一个连接，
			 * 第三个线程又来，ThreadLocal的map集合里面又有一个连接，
			 * 而ThreadLocal又是静态的，即整个应用程序周期范围内都存在，那这个容器就会越来越大，最后导致数据溢出。
			 * 所以静态的东西要慎用！！！
			 */
			tl.remove(); // 千万注意：解除当前线程上绑定的连接(从ThreadLocal容器中移除掉对应当前线程的连接)
		}
	}

}
