package org.appops.infra.benchmarking;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.Inject;

public class Benchmarker implements MethodInterceptor {

	private BenchmarkingService service = null;
	private Ref ref = null ;
	
	public Benchmarker() {

	}
	
	@Inject 
	public void setRef(Ref r){
		ref = r ; 
	}

	@Inject
	public void setBenchmarkingService(BenchmarkingService serv) {
		service = serv;
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {

		long start = System.nanoTime();
		Object result = invocation.proceed();
		long timeTaken = System.nanoTime() - start;
		
		String mName = invocation.getMethod().getDeclaringClass().getCanonicalName() + "."
				+ invocation.getMethod().getName();
		
		Benchmark benchmark = new Benchmark( ref.getRefString(), timeTaken, mName);
		
		service.logTopic(benchmark);

		return result;
	}
}