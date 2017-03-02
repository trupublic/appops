package org.appops.infra.benchmarking;

import org.appops.infra.core.annotations.ServiceModule;
import org.appops.infra.core.provision.Benchmarked;
import org.appops.infra.core.provision.DevelopmentImpl;
import org.appops.infra.core.provision.NoopImpl;
import org.appops.infra.core.provision.ProductionImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;

@ServiceModule
public class BenchmarkingModule extends AbstractModule {


	@Override
	protected void configure() {
		bind(BenchmarkingService.class).to(BenchmarkingServiceImpl.class);
		bind(BenchmarkingService.class).annotatedWith(DevelopmentImpl.class).to(BenchmarkingServiceImpl.class); 
		bind(BenchmarkingService.class).annotatedWith(ProductionImpl.class).to(BenchmarkingServiceProductionImpl.class); 
		bind(BenchmarkingService.class).annotatedWith(NoopImpl.class).to(BenchmarkingServiceNoopImpl.class);
		bind(BenchmarkStore.class).in(Singleton.class);
		
		Benchmarker bm = new Benchmarker();
		this.requestInjection(bm);
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(Benchmarked.class), bm);
		bind(Ref.class).in(Singleton.class);

	}

}
