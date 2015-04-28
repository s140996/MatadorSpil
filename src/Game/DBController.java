package Game;
import java.sql.*;

public class DBController {

	private static String JDBC_driver = "com.mysql.jdbc.Driver";
	private static String DB_url = "jdbc:mysql://localhost/";
	private static String USER = "root";
	private static String PASS = "";

	public static void main(String[] args) throws Exception {

		Connection con = null;
		Statement stmt = null;

		try{
			//STEP 2: Register JDBC driver
			Class.forName(JDBC_driver);

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			con = DriverManager.getConnection(DB_url, USER, PASS);

			//STEP 4: Execute a query
			System.out.println("Creating database...");
			stmt = con.createStatement();

			String sql = "CREATE DATABASE Matador";
			stmt.executeUpdate(sql);
			System.out.println("Database created successfully...");
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");
	}
}

