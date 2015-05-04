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
	private ResultSet rs;

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

	public void save(Player[] playerlist, int amountOfPlayers, int playerNo, GameBoard gb)
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
			sql = "DROP TABLE IF EXISTS Player, Account, Building, Game, Ownable CASCADE;";

			stmt.executeUpdate(sql);

			// ** Laver spiller tabellen **
			sql = "CREATE TABLE Player ("
					+ "ID int,"
					+ "Name varchar(255) PRIMARY KEY,"
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


			// ** Laver konto tabellen **
			sql = "CREATE TABLE Account ("
					+ "Name varchar(255) PRIMARY KEY,"
					+ "Balance int, "
					+ "FOREIGN KEY (Name) REFERENCES Player(Name)"
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


			// ** Laver Ownable tabellen **
			sql = "CREATE TABLE Ownable ("
					+ "ID int PRIMARY KEY,"
					+ "FieldName varchar(255),"
					+ "Owner varchar(255),"
					+ "Pawned tinyint(1)"
					+ ");";

			stmt.executeUpdate(sql);

			// ** Laver Building tabellen **
			sql = "CREATE TABLE Building ("
					+ "ID int PRIMARY KEY,"
					+ "House int,"
					+ "Hotels int,"
					+ "FOREIGN KEY (ID) REFERENCES Ownable(ID)"
					+ ");";

			stmt.executeUpdate(sql);

			for (int i = 0; i < 40; i++)
			{

				if(gb.getField(i).getType() == "Territory" || gb.getField(i).getType() == "Brewery" || gb.getField(i).getType() == "Fleet")
				{
					GOwnable own = (GOwnable) gb.getField(i);
					if(own.isOwned() == true)
					{
						sql = "INSERT INTO Ownable VALUES ("
								+ gb.getField(i).getID() + ", '"
								+ gb.getField(i).getName() + "','"
								+ own.getOwner().toString() + "',"
								+ own.getPawnDB()
								+ ");";
						stmt.executeUpdate(sql);

						if (gb.getField(i).getType() == "Territory")
						{
							GTerritory territory = (GTerritory) own;
							sql = "INSERT INTO Building VALUES ("
									+ gb.getField(i).getID() + ", "
									+ territory.getHouseCount() + ", "
									+ territory.getHotelCount()
									+ ");";
							stmt.executeUpdate(sql);
						}
					}


				}



			}


			// ** Laver Game tabellen **
			sql = "CREATE TABLE Game ("
					+ "PlayerNo int,"
					+ "AmountOfPlayers int"
					+ ");";

			stmt.executeUpdate(sql);

			// ** Indsætter i Game tabellen **
			sql = "INSERT INTO Game VALUES ("
					+ playerNo + ", "
					+ amountOfPlayers
					+ ");";

			stmt.executeUpdate(sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public int loadAmountOfPlayers()
	{
		con = null;
		stmt = null;

		int rtn = 0;

		try
		{
			Class.forName(JDBC_driver);

			//Opretter forbindelse til databasen
			con = DriverManager.getConnection(DB_url + dbName, USER, PASS);

			stmt = con.createStatement();

			sql = "SELECT AmountOfPlayers FROM Game;";
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				rtn = rs.getInt("AmountOfPlayers");
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return rtn;
	}

	public Player[] loadPlayer(int amountOfPlayers, GUIController gui)
	{
		con = null;
		stmt = null;

		Player[] playerlist = new Player[amountOfPlayers + 1];

		try
		{
			Class.forName(JDBC_driver);

			//Opretter forbindelse til databasen
			con = DriverManager.getConnection(DB_url + dbName, USER, PASS);

			stmt = con.createStatement();

			int playerNo = 0;
			String name = "";
			int worth = 0;
			int position = 0;
			int prisonCard = 0;
			int fleets = 0;
			int brewery = 0;
			boolean convict = false;
			boolean alive = false;
			int balance = 0;

			sql = "SELECT PlayerNo FROM Game;";
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				playerNo = rs.getInt("PlayerNo");
			}

			int j = 1;
			for (int i = playerNo; i < amountOfPlayers + 1; i++)
			{
				sql = "SELECT * FROM Player NATURAL JOIN Account WHERE Player.ID = " + i;
				rs = stmt.executeQuery(sql);

				while(rs.next())
				{
					name = rs.getString("Name");
					worth = rs.getInt("worth");		
					position = rs.getInt("Position");
					prisonCard = rs.getInt("PrisonCard");
					fleets = rs.getInt("FleetsOwned");
					brewery = rs.getInt("brewerysOwned");
					convict = rs.getBoolean("convict");
					alive = rs.getBoolean("alive");
					balance = rs.getInt("Balance");
				}
				
				playerlist[j] = new Player(name, worth, position, prisonCard, fleets, brewery, convict, alive);
				playerlist[j].acc.setBalance(balance);
				gui.loadPlayer(name, balance, position);
				j++;
			}

			for (int i = 1; i < playerNo; i++)
			{
				sql = "SELECT * FROM Player NATURAL JOIN Account WHERE Player.ID = " + i;
				rs = stmt.executeQuery(sql);

				while(rs.next())
				{
					name = rs.getString("Name");
					worth = rs.getInt("worth");		
					position = rs.getInt("Position");
					prisonCard = rs.getInt("PrisonCard");
					fleets = rs.getInt("FleetsOwned");
					brewery = rs.getInt("brewerysOwned");
					convict = rs.getBoolean("convict");
					alive = rs.getBoolean("alive");
					balance = rs.getInt("Balance");
				}
				
				playerlist[j] = new Player(name, worth, position, prisonCard, fleets, brewery, convict, alive);
				playerlist[j].acc.setBalance(balance);
				gui.loadPlayer(name, balance, position);
				j++;
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return playerlist;
	}
	
	public void loadgb(GameBoard gb, Player[] playerlist)
	{
		con = null;
		stmt = null;

		try
		{
			Class.forName(JDBC_driver);

			//Opretter forbindelse til databasen
			con = DriverManager.getConnection(DB_url + dbName, USER, PASS);

			stmt = con.createStatement();
			
			sql = "SELECT * FROM Ownable NATURAL LEFT OUTER JOIN Building;";
			rs = stmt.executeQuery(sql);
			
			for(int i = 0; i < 40; i++)
			{
				
				
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}

