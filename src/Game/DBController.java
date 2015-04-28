package Game;
import java.sql.*;

public class DBController {

	private String JDBC_driver = "com.mysql.jdbc.Driver";
	private String DB_url = "jdbc:mysql://localhost/";
	private String USER = "root";
	private String PASS = "";

	public DBController() 
	{
		Connection con = null;
		Statement stmt = null;

		try 
		{
		Class.forName(JDBC_driver);

		//Opretter forbindelse til databasen
		con = DriverManager.getConnection(DB_url, USER, PASS);

		stmt = con.createStatement();

		String sql = "CREATE DATABASE IF NOT EXISTS Matador";
		stmt.executeUpdate(sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void save()
	{
		
	}
	
	public void load()
	{
		
	}
	
}

