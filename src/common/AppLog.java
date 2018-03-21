package common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import model.AllBooking;

public class AppLog {
	private  FileHandler fileHandler;  
	private SimpleDateFormat simpleDateFormat;
    	final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    	
    	private static AppLog instance;
    	
    	public static AppLog getLogger() {
    		if(instance == null) {
    			synchronized(AppLog.class) { // lock block
    			instance  = new AppLog("BookingLog","yyyy-MM-DD HH:mm:ss");
    			}
    		}
    		return instance;
    	}
	 public AppLog(String filePath,String dateformat) {
			super();
			// create a  formatter
			simpleDateFormat = new SimpleDateFormat(dateformat);
		
	        try {
	        	    // set the LogLevel to Info,
				logger.setLevel(Level.INFO);
				fileHandler = new FileHandler(filePath+ simpleDateFormat.format(Calendar.getInstance().getTime()) + ".log");
			
				// add formatter to File Handler
				fileHandler.setFormatter(new SimpleFormatter(){
		            @Override
		            public String format(LogRecord record) {
		              String operation="";
		              String message="";
		            	if (record.getParameters() !=null) {
		            		
		            		operation= (String) record.getParameters()[0];
		            		message= (String) record.getParameters()[1];
		            	}
		            	
		                return  "Info recorded at ||" 
		                	     
		                        + simpleDateFormat.format(Calendar.getInstance().getTime()) + " For operation : "+ operation 
		                        + " || " 
		                        + message + "\n";
		            }
		        });
		        logger.addHandler(fileHandler);
		        
		        logger.info("The Appication star at " + simpleDateFormat.format(Calendar.getInstance().getTime()));
				
				
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			
		}
	
	 public void AddToLog(String message,String operation) {
		 synchronized(AppLog.class) { // lock block
		 logger.log(Level.OFF, "{0},{1}", new Object[]{operation,message});
		 }
	        
	    }

}
