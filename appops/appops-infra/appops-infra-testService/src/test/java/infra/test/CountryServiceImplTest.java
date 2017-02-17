package infra.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CountryServiceImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCountryList() {
		
		CountryServiceImpl impl = new CountryServiceImpl() ;
		List<Country> list = impl.getCountryList() ;
		
		assertTrue(list.size() == 10);
	}

}
