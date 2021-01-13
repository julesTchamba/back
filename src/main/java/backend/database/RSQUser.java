package backend.database;

/**
* Class that define a user of the database
*/
public class RSQUser
{
	String username; //le username sera le courriel
	String password; 

	public RSQUser(String username, String password)
	{
		this.username = username; 
		this.password = password; 
	}

	public String getPassword() 
	{ 
		return password; 
	}

	public void setPassword(String password) 
	{ 
		this.password = password; 
		
	}

	public String getUsername() 
	{ 
		return username; 
	}

	public void setUsername(String password) 
	{ 
		this.username = username; 
		
	}
}
