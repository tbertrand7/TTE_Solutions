package trackModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDBInteraction {
	Connection conn = null;

	//When we connect to the database for the first time all data will be pulled, this is to ensure a proper connection is made.
	public UserDBInteraction() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		//Connect to the database for the first time!'
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://107.180.27.178:3306/TTEDB", "TTE", "ttesolutions");

		Statement stmt = conn.createStatement();
		stmt.execute("SELECT * FROM TTEDB.Users");
		stmt.close();
	}

	//addUser will take two strings and insert them into the user database
	//If the user is added it will return true otherwise it will return false
	public boolean addUser(String username, String password) throws SQLException{
		conn = DriverManager.getConnection("jdbc:mysql://107.180.27.178:3306/TTEDB", "TTE", "ttesolutions");
		Statement stmt = conn.createStatement();
		boolean success = stmt.execute("insert into TTEDB.Users (UserName, UserPW) values ('"+username+"', '"+password+"')");
		stmt.close();
		conn.close();
		return success;
	}

	//verifyUser takes a user name and PW combination and checks to see that it exists in the database
	//If there is a match the method will return true, else it will return false
	public boolean verifyUser(String username, String password) throws SQLException{
		conn = DriverManager.getConnection("jdbc:mysql://107.180.27.178:3306/TTEDB", "TTE", "ttesolutions");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM TTEDB.Users where UserName = '"+username+"' and UserPW = '"+password+"'");
		rs.next();
		int cnt = rs.getInt(1);
		stmt.close();
		conn.close();
		return cnt != 0;
	}

	//checkDuplicate takes a user name and checks to see if it exists in the database
	//If there is a match the method will return true, else it will return false
	public boolean checkDupUsername(String username) throws SQLException{
		conn = DriverManager.getConnection("jdbc:mysql://107.180.27.178:3306/TTEDB", "TTE", "ttesolutions");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM TTEDB.Users where UserName = '"+username+"'");
		rs.next();
		int cnt = rs.getInt(1);
		stmt.close();
		conn.close();
		return cnt == 0;
	}
}
