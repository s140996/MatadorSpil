package Game;
import java.sql.*;

public class DBController {

	public static void main(String[] args) throws Exception {
		//load driveren
		Class.forName("com.mysql.jdbc.Driver");
		//opret forbindelse til databasen "test2"
		Connection con= DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/Matador","root","");

		//opret sætningsobjekt
		Statement s= con.createStatement();


	}
}
