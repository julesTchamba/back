package backend.database;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class RSQLog
{	
	static private Level levelSevere = null; 
	static private Level levelWarning = null; 
	static private Level levelInfo = null; 
	static private Level levelConfig = null; 
	static private Level levelFine = null; 
	static private Level levelFiner = null; 
	static private Level levelFinest = null; 

    private static Logger logger = Logger.getLogger("global");
    private static FileHandler fh;
    private static String fileName;
	     
	public RSQLog()
	{
	}

	public static void createLogFile(String path) {
		fileName = path;
		File file = new File(fileName); //initialize File object and passing path as argument
		boolean result;
		try
		{
			result = file.createNewFile();  //creates a new file
			if(result)      // test if successfully created a new file
			{
				System.out.println("file created "+file.getCanonicalPath()); //returns the path string
			}
			else
			{
				System.out.println("File already exist at location: "+file.getCanonicalPath());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();    //prints exception if any
		}
	}

	static public void init()
	{ 
		try { 
			fh = new FileHandler(fileName);
			XMLFormatter xf = new XMLFormatter();  
			fh.setFormatter(xf);
    		logger.getLogger("global").addHandler(fh);
    	}
    	catch (Exception e) 
    	{ 
    		System.err.println(e);
    	} 
	} 
	
	static public void message(Level level, String msg ) 
	{ 
		logger.log(level, msg); 
	} 
	
	static public void message(int level, String msg ) 
	{ 
		Level l = null; 

		switch (level) { 
			case 6 :   l = Level.SEVERE; 
								break;		
			case 5 :   l = Level.WARNING;  
								break;		
			case 4 :   l = Level.INFO; 
								break;		
			case 3 :   l = Level.CONFIG; 
								break;		
			case 2 :   l = Level.FINE; 
								break;		
			case 1 :   l = Level.FINER; 
								break;		
			case 0 :   l = Level.FINEST; 
								break;		
			default : 			
								System.err.println("(RSQLog) Error log level invalid");
								return; 
		} 

		message(l, msg); 
		
	} 
}

