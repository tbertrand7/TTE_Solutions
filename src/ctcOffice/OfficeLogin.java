package ctcOffice;

import trackModel.UserDBInteraction;

import java.sql.SQLException;

public class OfficeLogin
{
	private UserDBInteraction userDB;

	public OfficeLogin()
	{
		try {
			userDB = new UserDBInteraction();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Checks for valid username and password */
	public boolean validateLogin(String username, String password)
	{
		try {
			return userDB.verifyUser(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/** Checks if new username already exists */
	public boolean validateNewUser(String username)
	{
		String tempUser = "admin";

		//TODO: Check DB for existing user

		if (username.equals(tempUser))
			return false;
		
		return true;
	}
	
	/** Checks if both password fields match */
	public boolean validateNewPassword(String password, String confirmPassword)
	{
		return password.equals(confirmPassword);
	}

	public boolean createNewUser(String username, String password)
	{
		try {
			return userDB.addUser(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
