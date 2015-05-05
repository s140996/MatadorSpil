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

//			sql = "DROP DATABASE " + this.dbName + ";";
//			
//			stmt.executeUpdate(sql);
			
			sql = "CREATE DATABASE IF NOT EXISTS " + this.dbName;

			stmt.executeUpdate(sql);

			Class.forName(JDBC_driver);

			//Opretter forbindelse til databasen
			con = DriverManager.getConnection(DB_url + dbName, USER, PASS);

			stmt = con.createStatement();

			// ** Laver spiller tabellen **
			sql = "CREATE TABLE IF NOT EXISTS Player ("
					+ "ID int,"
					+ "Name varchar(255) PRIMARY KEY,"
					+ "Position int,"
					+ "PrisonCard int,"
					+ "worth int,"
					+ "fleetsOwned int,"
					+ "brewerysOwned int,"
					// ** TINYINT(1) ** 0 = false ** 1 = true **
					+ "lastPosition tinyint(1),"
					+ "convict tinyint(1),"
					+ "alive tinyint(1)"
					+ ");";

			stmt.executeUpdate(sql);

			// ** Laver konto tabellen **
			sql = "CREATE TABLE IF NOT EXISTS Account ("
					+ "Name varchar(255) PRIMARY KEY,"
					+ "Balance int, "
					+ "FOREIGN KEY (Name) REFERENCES Player(Name)"
					+ ");";

			stmt.executeUpdate(sql);

			// ** Laver Ownable tabellen **
			sql = "CREATE TABLE IF NOT EXISTS Ownable ("
					+ "ID int PRIMARY KEY,"
					+ "FieldName varchar(255),"
					+ "Owner varchar(255),"
					+ "Pawned tinyint(1)"
					+ ");";

			stmt.executeUpdate(sql);

			// ** Laver Building tabellen **
			sql = "CREATE TABLE IF NOT EXISTS Building ("
					+ "ID int PRIMARY KEY,"
					+ "House int,"
					+ "Hotels int,"
					+ "FOREIGN KEY (ID) REFERENCES Ownable(ID)"
					+ ");";

			stmt.executeUpdate(sql);

			// ** Laver Game tabellen **
			sql = "CREATE TABLE IF NOT EXISTS Game ("
					+ "PlayerNo int,"
					+ "AmountOfPlayers int"
					+ ");";

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

			// ** Sletter tabellerne, hvis de eksisterer **

			sql = "SET FOREIGN_KEY_CHECKS = 0;";

			stmt.executeUpdate(sql);

			sql = "DELETE FROM Player;";

			stmt.executeUpdate(sql);

			sql = "DELETE FROM Account;";

			stmt.executeUpdate(sql);

			sql = "DELETE FROM Building;";

			stmt.executeUpdate(sql);

			sql = "DELETE FROM Ownable;";

			stmt.executeUpdate(sql);

			sql = "DELETE FROM Game;";

			stmt.executeUpdate(sql);

			sql = "SET FOREIGN_KEY_CHECKS = 1;";

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
						+ playerlist[i].getLastPosition() + ", "
						+ playerlist[i].getConvictDB() + ", "
						+ playerlist[i].getAliveDB()
						+ ");";

				stmt.executeUpdate(sql);
			}


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
			boolean lastPos = false;
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
					lastPos = rs.getBoolean("lastPosition");
					convict = rs.getBoolean("convict");
					alive = rs.getBoolean("alive");
					balance = rs.getInt("Balance");
				}

				playerlist[j] = new Player(name, worth, position, prisonCard, fleets, brewery, lastPos, convict, alive);
				playerlist[j].acc.setBalance(balance);
				gui.loadPlayer(name, balance, position);
				j++;
			}

			for (int i = 1; i < playerNo; i++)
			{
				sql = "SELECT * FROM Player NATURAL JOIN Account WHERE ID = " + i;
				rs = stmt.executeQuery(sql);

				while(rs.next())
				{
					name = rs.getString("Name");
					worth = rs.getInt("worth");		
					position = rs.getInt("Position");
					prisonCard = rs.getInt("PrisonCard");
					fleets = rs.getInt("FleetsOwned");
					brewery = rs.getInt("brewerysOwned");
					lastPos = rs.getBoolean("lastPosition");
					convict = rs.getBoolean("convict");
					alive = rs.getBoolean("alive");
					balance = rs.getInt("Balance");
				}

				playerlist[j] = new Player(name, worth, position, prisonCard, fleets, brewery, lastPos, convict, alive);
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

	public void loadgb(GameBoard gb, Player[] playerlist, GUIController gui, int amountOfPlayers)
	{
		con = null;
		stmt = null;

		try
		{
			Class.forName(JDBC_driver);

			//Opretter forbindelse til databasen
			con = DriverManager.getConnection(DB_url + dbName, USER, PASS);

			stmt = con.createStatement();

			for(int i = 0; i < 40; i++)
			{
				String owner = "";
				boolean pawned = false;
				int house = 0;
				int hotel = 0;

				sql = "SELECT * FROM Ownable NATURAL LEFT OUTER JOIN Building WHERE ID = " + gb.getField(i).getID() + ";";
				rs = stmt.executeQuery(sql);

				if(gb.getField(i).getType() == "Territory" || gb.getField(i).getType() == "Brewery" || gb.getField(i).getType() == "Fleet")
				{
					GOwnable field = (GOwnable) gb.getField(i);

					while(rs.next())
					{
						owner = rs.getString("Owner");
						pawned = rs.getBoolean("Pawned");
					}

					field.setPawn(pawned);

					for (int j = 1; j < amountOfPlayers + 1; j++)
					{ 
						if (playerlist[j].toString().equals(owner))
						{
							field.setOwner(playerlist[j]);
							gui.setOwner(field.getID(), playerlist[j].toString());
						}
					}

				}

				if(gb.getField(i).getType() == "Territory")
				{
					GTerritory territory = (GTerritory) gb.getField(i);

					while(rs.previous())
					{
						house = rs.getInt("House");
						hotel = rs.getInt("Hotels");
					}

					territory.setHotel(hotel);
					gui.setHotel(territory.getID(), territory.getHotel());
					if (territory.getHotel() == false)
					{
						territory.setHouse(house);
						gui.setHouses(territory.getID(), house);
					}

				}

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean isThereASavedGame()
	{
		con = null;
		stmt = null;

		boolean rtn = false;
		
		try
		{
			Class.forName(JDBC_driver);

			//Opretter forbindelse til databasen
			con = DriverManager.getConnection(DB_url + dbName, USER, PASS);

			stmt = con.createStatement();

			sql = "SELECT * FROM Player";

			rs = stmt.executeQuery(sql);

			if (!rs.next())
			{
				rtn = false;
			}
			else
			{
				rtn = true;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return rtn;
	}

}

