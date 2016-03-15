package pyramid.infra.dispatcher;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;

import java.io.IOException;

import org.junit.Test;

import com.google.common.collect.BiMap;

import pyramid.infra.core.artifacts.CountryService;
import pyramid.infra.core.artifacts.PriviledgedCountry;
import pyramid.infra.core.artifacts.TestLevelOne;
import pyramid.infra.core.artifacts.TestLevelTwo;
import pyramid.infra.core.artifacts.TestServiceImpl;
import pyramid.infra.core.artifacts.TestServiceInterface;


public class InvocableStoreTest {
	
	@Test
	public void testBuildServiceGrammarSuccessfuly() throws IOException{

		InvocableStore store  = new InvocableStore();
		
		store.buildServiceGrammar();
		
		BiMap<String, Invocable> grammar = store.invocableMap;
		
		
		
		assertThat("Number of invocables are currently more than 7 ", grammar.size() > 7);
		assertTrue(grammar.get("testService").equals(new Invocable("testService" , TestServiceInterface.class))) ;
		assertTrue(grammar.get("testOne").equals(new Invocable("testOne" , TestLevelOne.class)));
		assertTrue(grammar.get("testOne/secondLevelService").equals( new Invocable("testOne/secondLevelService",TestLevelTwo.class)));
		assertTrue(grammar.get("countries/PriviledgedCountry" ).equals( new Invocable("countries/PriviledgedCountry" ,PriviledgedCountry.class)));
		assertTrue(grammar.get("countries").equals( new Invocable("countries" , CountryService.class)));
	}
	
}
