package pyramid.infra.core.artifacts;

import java.util.List;

import pyramid.infra.core.annotations.Operation;
import pyramid.infra.core.annotations.Service;

@Service(name = "countries")
public interface CountryService {

	@Operation(name = "allCountries" )
	public List<Country> getAllCountries();
	
	@Operation(name = "countriesByFilter")
	public List<Country> getCountriesByFilter(Filter filter) ;

}
