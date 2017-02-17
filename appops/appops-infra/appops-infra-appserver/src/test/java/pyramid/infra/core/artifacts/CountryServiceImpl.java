package pyramid.infra.core.artifacts;

import java.util.Arrays;
import java.util.List;

import pyramid.infra.core.provision.Benchmarked;

public class CountryServiceImpl implements CountryService {

	@Override
	@Benchmarked
	public List<Country> getAllCountries() {
		
		List<Country> list = Arrays.asList( new Country("MH", "West", 50.0, 80.0)); 
		return list;
	}

	@Override
	@Benchmarked
	public List<Country> getCountriesByFilter(Filter filter) {
		List<Country> list = Arrays.asList( new Country("FilteredMH", "FilteredWest", 50.0, 80.0)); 
		return list;
	}

}
