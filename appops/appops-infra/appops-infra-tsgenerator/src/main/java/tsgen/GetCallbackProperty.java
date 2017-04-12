package tsgen;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetCallbackProperty {

	Properties properties = new Properties();
	static Logger logger;

	public GetCallbackProperty() {
	}

	public String getReturnType(){
		try{
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
			properties.load(inputStream);
			return properties.getProperty("callbackReturnType");
		}catch (IOException e) {
			logger.log(Level.INFO, "Exception occured"+e);		
		}
		return null;
	}

	public String getParameter(){
		try{
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
			properties.load(inputStream);
			return properties.getProperty("callbackArgument");
		}catch (IOException e) {
			logger.log(Level.INFO, "Exception occured"+e);		
		}
		return null;
	}
}
