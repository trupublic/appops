package org.appops.infra.benchmarking;

import java.util.ArrayList;
import java.util.List;

public class BenchmarkingServiceNoopImpl implements BenchmarkingService{

	@Override
	public void logTopic(Benchmark bm) {
		// Nothing to do here
	}

	@Override
	public void logTopicDetailed(String item, int millis, String ref) {
		// Nothing to do here
	}

	@Override
	public void logTopicBatches(Benchmark[] list) {
		// Nothing to do here
	}

	@Override
	public List<Benchmark> getBenchmarkList(String path) {
		
		ArrayList<Benchmark> noopBenches = new  ArrayList<>();
		
		Benchmark benchmark = new Benchmark("noopbench - mark returned" , 0 , "NoopBenchmarkImpl");
		
		noopBenches.add(benchmark);
		
		return noopBenches;
	}

}
