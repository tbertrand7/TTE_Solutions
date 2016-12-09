package ctcOffice;

import trackModel.UserDBInteraction;

import java.sql.SQLException;

public class OfficeLogin
{
	private OfficeLoginUI loginUI;
	private CTCOffice ctcOffice;
	private UserDBInteraction userDB;

	public OfficeLogin(CTCOffice ctc)
	{
		try {
			ctcOffice = ctc;
			userDB = new UserDBInteraction();
			loginUI = new OfficeLoginUI(this);
			loginUI.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows OfficeUI upon successful login
	 */
	public void loginSuccess(String username)
	{
		ctcOffice.loginSuccess(username);
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
		try {
			return userDB.checkDupUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
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
