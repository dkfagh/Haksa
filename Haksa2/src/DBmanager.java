import java.sql.*;

public class DBmanager {
	static Connection conn = null;
	static Statement stmt = null;
	
	public DBmanager() {
		try {
//			Oracle 연결
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myoracle", "ora_user", "hong");
			
			
//			MySQL 연결
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb?useSSL=false","hkd","1234");
			stmt = conn.createStatement();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		return conn;
	}
	
	public void closeSet() {
		try {
			if(conn!=null) {conn.close();}
		}catch(Exception ex) {
			ex.printStackTrace();}
	}
}