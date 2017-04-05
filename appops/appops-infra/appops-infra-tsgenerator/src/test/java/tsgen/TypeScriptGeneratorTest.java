package tsgen;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TypeScriptGeneratorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws IOException {
		
		/**
		 * 	Checks for thrown Exception is IOException
		 * 	Checks for required Exception Error Message which is thrown if First argument to main() is empty.  
		 * */
		try {
			TypeScriptGenerator.main(new String[]{null,null});
		} catch (Exception e) {
			assertTrue(e instanceof IOException);
			assertEquals("Need to have annotation class name as a parameter to do conversion", e.getMessage());
		}

		/**
		 *	Checks for thrown Exception is IOException
		 *	Checks for required Exception Error Message which is thrown if Second argument to main is empty.
		 * */
		try {
			TypeScriptGenerator.main(new String[]{".\\",null});
		} catch (Exception e) {
			assertTrue(e instanceof IOException);
			assertEquals("Java to TypeScript generation module name cannot be null or empty", e.getMessage());
		}

		/**
		 * Creates a type Script file and Checks the generated file is created or not.
		 * */
		TypeScriptGenerator.main(new String[]{".\\","JunitTestModule"});

		String resultFile = null;
		File fileDirectory = new File("/home/iternia/git/appops/appops/appops-infra/appops-infra-tsgenerator/");
		File[] allFiles = fileDirectory.listFiles();
		for(File files : allFiles)
			if(files.getName().equals(".\\JunitTestModule.d.ts"))
				resultFile = files.getName();

		assertEquals(".\\JunitTestModule.d.ts", resultFile);
	}
}
