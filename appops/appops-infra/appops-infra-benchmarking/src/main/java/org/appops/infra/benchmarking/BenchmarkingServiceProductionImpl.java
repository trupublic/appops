package org.appops.infra.benchmarking;

import com.google.inject.Inject;

public class BenchmarkingServiceProductionImpl extends BenchmarkingServiceImpl implements BenchmarkingService {

	@Inject
	public BenchmarkingServiceProductionImpl(BenchmarkStore store){
		super(store, null);
	}

}
