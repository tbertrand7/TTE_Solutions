package ctcOffice;

public class OfficeLogin
{
	/** Checks for valid username and password */
	public static boolean checkLogin(String username, String password)
	{
		String tempUser = "admin";
		String tempPassword = "password";
		
		if (username.equals(tempUser) && password.equals(tempPassword))
			return true;
		
		return false;
	}
	
	/** Checks if new username already exists */
	public static boolean checkNewUsername(String username)
	{
		String tempUser = "admin";
		
		if (username.equals(tempUser))
			return false;
		
		return true;
	}
	
	/** Checks if both password fields match */
	public static boolean checkNewPassword(String password, String confirmPassword)
	{
		if (password.equals(confirmPassword))
			return true;
		
		return false;
	}
}
