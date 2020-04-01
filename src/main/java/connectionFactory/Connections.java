package connectionFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.reflect.Field;

public class Connections {

	public static Connection getConnection() {
		
		String ClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		//String IPAddr = "DESKTOP-LHLA0PA\\MSSQLSERVER01";
		String IPAddr = "DESKTOP-LHLA0PA\\SQLEXPRESS;";
		String DBName="Worlds";
		String DB_URL = "jdbc:sqlserver://" + IPAddr + "DatabaseName=" + DBName + ";integratedSecurity=true" ;
		//Main connServer = new Main();
		Connection con=null;
		try {
			con = dbConnect (DB_URL,ClassName);
			System.out.println("DBConnected");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Connection : " + con);
		return con;
		/*Connection con = null;
		try {
			 //Registering the Driver
	    	Class.forName("com.mysql.jdbc.Driver");  
	        //Getting the connection
	        String mysqlUrl = "jdbc:mysql://localhost:3306/Worlds";
	        con = DriverManager.getConnection(mysqlUrl, "root", "root");
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;*/
	}
	
	public static Connection initialCon() {

		String ClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		//String IPAddr = "DESKTOP-LHLA0PA\\MSSQLSERVER01";
		String IPAddr = "DESKTOP-LHLA0PA\\SQLEXPRESS;";
		String DBName="Worlds";
		String DB_URL = "jdbc:sqlserver://" + IPAddr + "DatabaseName=" + DBName + ";integratedSecurity=true" ;
		//Main connServer = new Main();
		Connection con=null;
		try {
			con = dbConnect (DB_URL,ClassName);
			System.out.println("DBConnected");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Connection : " + con);
		return con;
		
	}
	
	private static Connection dbConnect(String db_connect_string, String className) throws ClassNotFoundException
	{
		java.sql.Connection connection = null;
		System.out.println("Connecting to a selected database...");
		String libpath = System.getProperty("java.library.path");
		libpath = "C:\\Users\\soham\\Downloads\\sqljdbc_8.2\\enu\\auth\\x64\\;" +libpath;
		System.out.println("SQL server path"+libpath);
		System.setProperty("java.library.path",libpath);
		Field sysPathsField;
		try {
			sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
			sysPathsField.setAccessible(true);
			sysPathsField.set(null, null);
		} catch (NoSuchFieldException | SecurityException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			Class.forName(className);
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Native code library failed to load.\n" + e);
			System.exit(1);
		}
		try{
			connection = DriverManager.getConnection(db_connect_string);
			System.out.println("connected");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	} // 
	
}
