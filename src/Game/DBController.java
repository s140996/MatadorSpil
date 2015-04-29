package Game;
import java.sql.*;

import Fields.*;
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

	public void save(Player[] playerlist, int amountOfPlayers, GameBoard gb)
	{	
		con = null;
		stmt = null;

		try
		{
			Class.forName(JDBC_driver);

			//Opretter forbindelse til databasen
			con = DriverManager.getConnection(DB_url + dbName, USER, PASS);

			stmt = con.createStatement();

			// ** Sletter spiller tabellen, hvis den eksisterer **
			sql = "DROP TABLE IF EXISTS Player;";

			stmt.executeUpdate(sql);

			// ** Laver spiller tabellen **
			sql = "CREATE TABLE Player ("
					+ "ColorID int,"
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

			// ** Gemmer spiller i DB **
			for (int i = 1; i < amountOfPlayers + 1; i++)
			{
				sql = "INSERT INTO Player VALUES ("
						+ i + ", '" 
						+ playerlist[i].toString() + "', "
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

			// ** Sletter konto tabellen, hvis den eksisterer **
			sql = "DROP TABLE IF EXISTS Account;";

			stmt.executeUpdate(sql);

			// ** Laver konto tabellen **
			sql = "CREATE TABLE Account ("
					+ "Name varchar(255) primary key,"
					+ "Balance int"
					+ ");";

			stmt.executeUpdate(sql);

			// ** Gemmer konto i DB **
			for (int i = 1; i < amountOfPlayers + 1; i++)
			{
				sql = "INSERT INTO Account VALUES ("
						+ "'" 
						+ playerlist[i].toString() + "', "
						+ playerlist[i].acc.getBalance()
						+ ");";

				stmt.executeUpdate(sql);
			}

			// ** Sletter GameBoard tabellen, hvis den eksisterer **
			sql = "DROP TABLE IF EXISTS GameBoard;";

			stmt.executeUpdate(sql);

			// ** Laver GameBoard tabellen **
			sql = "CREATE TABLE GameBoard ("
					+ "ID int primary key,"
					+ "FieldName varchar(255),"
					+ "Owner varchar(255),"
					+ "Pawned tinyint(1),"
					+ "House int,"
					+ "Hotels int"
					+ ");";

			stmt.executeUpdate(sql);

			for (int i = 0; i < 40; i++)
			{

				if(gb.getField(i).getType() == "Territory")
				{
					GTerritory territory = (GTerritory) gb.getField(i);
					GOwnable own= (GOwnable) gb.getField(i);
					if(own.isOwned() == true)
					{
						sql = "INSERT INTO GameBoard VALUES ("
								+ ""
								+ gb.getField(i).getID() + ", '"
								+ gb.getField(i).getName() + "','"
								+ own.getOwner().toString() + "',"
								+ own.getPawnDB() + ","
								+ territory.getHouseCount() + ","
								+ territory.getHotelCount()
								+ ");";
					}
					else
					{
						sql = "INSERT INTO GameBoard VALUES ("
								+ ""
								+ gb.getField(i).getID() + ", '"
								+ gb.getField(i).getName() + "','"
								+ "null',"
								+ own.getPawnDB() + ","
								+ territory.getHouseCount() + ","
								+ territory.getHotelCount()
								+ ");";
					}
				}
				else if (gb.getField(i).getType() != "Territory" && gb.getField(i).getType() != "Brewery" && gb.getField(i).getType() != "Fleet")
				{
					sql = "INSERT INTO GameBoard VALUES ("
							+ ""
							+ gb.getField(i).getID() + ", '"
							+ gb.getField(i).getName() + "','"
							+ "null',"
							+ 0 + ","
							+ 0 + ","
							+ 0
							+ ");";
				}
				else if(gb.getField(i).getType() == "Brewery" || gb.getField(i).getType() == "Fleet")
				{
					GOwnable own= (GOwnable) gb.getField(i);
					if(own.isOwned() == true)
					{
						sql = "INSERT INTO GameBoard VALUES ("
								+ ""
								+ gb.getField(i).getID() + ", '"
								+ gb.getField(i).getName() + "','"
								+ own.getOwner().toString() + "',"
								+ own.getPawnDB() + ","
								+ 0 + ","
								+ 0
								+ ");";	
					}
					else
					{
						sql = "INSERT INTO GameBoard VALUES ("
								+ ""
								+ gb.getField(i).getID() + ", '"
								+ gb.getField(i).getName() + "','"
								+ "null',"
								+ own.getPawnDB() + ","
								+ 0 + ","
								+ 0
								+ ");";
					}
				}


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

