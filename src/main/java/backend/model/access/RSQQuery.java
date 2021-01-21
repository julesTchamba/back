package backend.model.access;


/**
* Class that define a Query object that is used to send a SQL query  
* to the database
* 
*/ 

public class RSQQuery extends Object
{	
	String query = new String(); 

	public RSQQuery()
	{
	}

	public void setString(String query) 
	{ 
		this.query = query; 
	} 
	
	public String toString() 
	{ 
		return new String(query); 
	}
	
}
