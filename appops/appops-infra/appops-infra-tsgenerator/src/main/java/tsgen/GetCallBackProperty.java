package tsgen;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetCallBackProperty {

	Properties properties = new Properties();
	static Logger logger;

	public GetCallBackProperty() {
	}

	public String getReturnType(){
		try{
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Config.properties");
			properties.load(inputStream);
			return properties.getProperty("callBackReturnType");
		}catch (IOException e) {
			logger.log(Level.INFO, "Exception occured"+e);		
		}
		return null;
	}

	public String getParameter(){
		try{
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Config.properties");
			properties.load(inputStream);
			return properties.getProperty("callBackParameter");
		}catch (IOException e) {
			logger.log(Level.INFO, "Exception occured"+e);		
		}
		return null;
	}
	
}
