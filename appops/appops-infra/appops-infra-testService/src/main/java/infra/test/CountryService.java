package infra.test;

import java.util.List;

import pyramid.infra.core.annotations.Service;
import pyramid.infra.core.provision.Benchmarked;


@Service
public interface CountryService {
	
	@Benchmarked
	public List<Country> getCountryList() ;
	
	@Benchmarked
	public List<Country> getCountriesFiltered(CountryFilter filter) ;
	
}
