package ctcOffice;

public class OfficeLogin
{
	/** Checks for valid username and password */
	public boolean validateLogin(String username, String password)
	{
		String tempUser = "admin";
		String tempPassword = "password";

		//TODO: Validate with user db
		
		if (username.equals(tempUser) && password.equals(tempPassword))
			return true;
		
		return false;
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
		//TODO: CreateUser in DB

		return true;
	}
}
