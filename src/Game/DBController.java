package Game;
import java.sql.*;

import Player.Player;

public class DBController {

	private String JDBC_driver = "com.mysql.jdbc.Driver";
	private String DB_url = "jdbc:mysql://localhost:3306/";
	private String USER = "root";
	private String PASS = "";
	private String dbName = "MatadorGrp4";
	private Connection con;
	private Statement stmt;
	private String sql;

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

			sql = "CREATE DATABASE IF NOT EXISTS " + this.dbName;

			stmt.executeUpdate(sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void save(Player[] playerlist)
	{	
		con = null;
		stmt = null;

		try
		{
			Class.forName(JDBC_driver);

			//Opretter forbindelse til databasen
			con = DriverManager.getConnection(DB_url + dbName, USER, PASS);

			stmt = con.createStatement();

			sql = "DROP TABLE IF EXISTS Player;";

			stmt.executeUpdate(sql);

			sql = "CREATE TABLE Player ("
					+ "ID int,"
					+ "Name varchar(255) primary key,"
					+ "Position int,"
					+ "PrisonCard int,"
					+ "worth int,"
					+ "fleetsOwned int,"
					+ "brewerysOwned int,"
					// ** TINYINT(1) ** 0 = false ** 1 = true **
					+ "convict tinyint(1),"
					+ "alive tinyint(1)"
					+ ");";

			stmt.executeUpdate(sql);

			for (int i = 1; i < playerlist.length + 1; i++)
			{
				sql = "INSERT INTO Player VALUES ("
						+ i
						+ ", '" + playerlist[i].toString() + "', "
						+ playerlist[i].getPosition() + ", "
						+ playerlist[i].getPrisonCard() + ", "
						+ playerlist[i].getWorth() + ", "
						+ playerlist[i].getFleetsOwned() + ", "
						+ playerlist[i].getBrewerysOwned() + ", "
						+ playerlist[i].getConvictDB() + ", "
						+ playerlist[i].getAliveDB()
						+ ");";

				stmt.executeUpdate(sql);
			}

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

