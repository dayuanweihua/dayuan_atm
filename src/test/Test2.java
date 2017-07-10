package test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Test2 {
	public static void main(String[] args) {
		 Logger myLogger = Logger.getLogger("myLogger");   
	      
	     //Get an instance of the childLogger   
	     Logger mySonLogger = Logger.getLogger("myLogger.mySonLogger");   
	     //Load the proerties using the PropertyConfigurator   
	     //PropertyConfigurator.configure("log4j.properties");   
	  
	     //Log Messages using the Parent Logger   
	     myLogger.debug("Thie is a log message from the " + myLogger.getName());   
	     myLogger.info("Thie is a log message from the " + myLogger.getName());   
	     myLogger.warn("Thie is a log message from the " +  myLogger.getName());   
	     myLogger.error("Thie is a log message from the " + myLogger.getName());   
	     myLogger.fatal("Thie is a log message from the " + myLogger.getName());
	}
}
