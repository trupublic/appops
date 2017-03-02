package org.appops.infra.core.artifacts;

import java.util.List;

import org.appops.infra.core.annotations.Operation;
import org.appops.infra.core.annotations.Service;

@Service(name = "countries")
public interface CountryService {

	@Operation(name = "allCountries" )
	public List<Country> getAllCountries();
	
	@Operation(name = "countriesByFilter")
	public List<Country> getCountriesByFilter(Filter filter) ;

}
