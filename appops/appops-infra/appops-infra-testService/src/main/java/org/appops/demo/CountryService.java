package org.appops.demo;

import java.util.List;

import org.appops.infra.core.annotations.Service;
import org.appops.infra.core.provision.Benchmarked;


@Service
public interface CountryService {
	
	@Benchmarked
	public List<Country> getCountryList() ;
	
	@Benchmarked
	public List<Country> getCountriesFiltered(CountryFilter filter) ;
	
}
