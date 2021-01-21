package backend.model.access;

 
/**
* Class that represent host address to db
*/ 
public class RSQAddress 
{	
	String address;  
	String port; 
	String dbname; 

	final String dbdrivertype = "jdbc"; 
	final String dbdrivername = "oracle";
	final String dbparam= "thin";
	
	
	public RSQAddress(String address, String port, String dbname) 
	{ 
		this.address = address;  
		this.port = port;  	
		this.dbname = dbname; 		
	}


	public String getAddress() 
	{ 
		return address; 
	}

	public String getPort() 
	{ 
		return port; 
	}

	public String getDBName() 
	{ 
		return dbname; 
	}

	public String toString() 
	{ 
                // jdbc:oracle:thin:@myhost:1521:orcl
		return (dbdrivertype + ":" + dbdrivername +  ":" + dbparam + ":@"
                                        + address + ":" + port + ":" + dbname);
	} 

}
