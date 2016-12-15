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

	/**
	 * Checks for valid username and password
 	 * @param username username for login
	 * @param password password for login
	 * @return true if login info is correct
	 */
	public boolean validateLogin(String username, String password)
	{
		try {
			return userDB.verifyUser(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Checks if new username already exists
	 * @param username username to check
	 * @return true if username isn't already in database
	 */
	public boolean validateNewUser(String username)
	{
		try {
			return userDB.checkDupUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Checks if both password fields match
	 * @param password password from first field
	 * @param confirmPassword password from confirm password field
	 * @return true if both passwords are equal
	 */
	public boolean validateNewPassword(String password, String confirmPassword)
	{
		return password.equals(confirmPassword);
	}

	/**
	 * Creates new user in database
	 * @param username username to add to db
	 * @param password password to add to db
	 * @return true if user is successfully added to db
	 */
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
