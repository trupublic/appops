package pyramid.bioffice.common.performance;

import org.apache.commons.lang3.time.StopWatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerformanceMonitor implements IPerformanceMonitor{
	
	private StopWatch stopWatch;
	private Logger logger;
	private String operation;
	
	
	public PerformanceMonitor(String opertaion, Logger logger) {
		this.logger = logger;
		this.operation = opertaion;
		stopWatch = new StopWatch();
		stopWatch.start();
	}

	public void close() {		
		this.stopWatch.stop();
		if (this.logger != null)
			logger.log(Level.INFO, operation + " took " + this.stopWatch.getTime());
	}
}
