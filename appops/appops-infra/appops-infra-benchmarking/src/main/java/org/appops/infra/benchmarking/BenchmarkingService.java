package org.appops.infra.benchmarking;

import java.util.List;

import pyramid.infra.core.annotations.Service;

@Service
public interface BenchmarkingService {
	
	public void logTopic(Benchmark bm) ;
	
	public void logTopicDetailed(String item , int millis, String ref);
	
	public void logTopicBatches(Benchmark[] list);
	
	public List<Benchmark> getBenchmarkList(String path) ;

}
