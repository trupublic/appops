package org.appops.infra.benchmarking;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;

public class BenchmarkingServiceImpl implements BenchmarkingService {
	
	private BenchmarkStore bmStore = null ;
	private Ref refObj;
	private static final Logger logger = Logger.getLogger(BenchmarkingServiceImpl.class.getSimpleName());
	
	@Inject
	public BenchmarkingServiceImpl(BenchmarkStore store, Ref ref){
		bmStore = store ;
		refObj = ref ;
	}

	@Override
	public void logTopic(Benchmark bm) {
		
		List<Benchmark> bmSet = bmStore.get(refObj.getRefString()) ;
		
		if (bmSet == null){
			bmSet = new ArrayList<Benchmark>() ;
		}
		
		bmSet.add(bm);
		
		bmStore.put(refObj.getRefString(), bmSet) ;
		
		logger.log(Level.INFO , "Benchmark added - " + bm.toString());
	
	}
	
	@Override
	public void logTopicDetailed(String item, int millis, String ref) {
		Benchmark bm = new Benchmark(item , millis, ref) ;
		logTopic(bm);
	}

	@Override
	public void logTopicBatches(Benchmark[] list) {
		for ( Benchmark bm : list){
			logTopic(bm);
		}
	}

	@Override
	public List<Benchmark> getBenchmarkList(String path) {

		if (bmStore.get(path) == null)
			return null ;
		
		for (String p : bmStore.keySet()){
			logger.log(Level.INFO, "path in benchmark store > "  + p);
		}
		
		for (Benchmark bm : bmStore.get(path)){
			logger.log(Level.ALL, "benchmark entry - " + bm.toString());
		}
		
		
		return bmStore.get(path);
		
	}
	
}
