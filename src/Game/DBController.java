package Game;
import java.sql.*;

public class DBController {

	private String JDBC_driver = "com.mysql.jdbc.Driver";
	private String DB_url = "jdbc:mysql://localhost:3306/";
	private String USER = "root";
	private String PASS = "";
	private String dbName = "MatadorGrp4";
	private Connection con;
	private Statement stmt;

	public DBController() 
	{
		con = null;
		stmt = null;

		try 
		{
		Class.forName(JDBC_driver);

		//Opretter forbindelse til databasen
		con = DriverManager.getConnection(DB_url, USER, PASS);

		stmt = con.createStatement();

		String sql = "CREATE DATABASE IF NOT EXISTS " + this.dbName;		
		stmt.executeUpdate(sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void save()
	{	
		con = null;
		stmt = null;
		
		try
		{
		Class.forName(JDBC_driver);

		//Opretter forbindelse til databasen
		con = DriverManager.getConnection(DB_url + dbName, USER, PASS);

		stmt = con.createStatement();
		String sql = "DROP TABLE IF EXISTS Player;"
				+ ""
				+"CREATE TABLE Player ("
				+ "Name varchar(255) primary key,"
				+ "Position int,"
				+ "Color varchar(255),"
				+ "PrisonCard int,"
				+ "worth int,"
				+ "fleetsOwned int,"
				+ "brewerysOwned int,"
				+ "convict boolean,"
				+ "alive boolean,"
				+ ");";
		
		stmt.executeUpdate(sql);
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void load()
	{
		
	}
	
}

