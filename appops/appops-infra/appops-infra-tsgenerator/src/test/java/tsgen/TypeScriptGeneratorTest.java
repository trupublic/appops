package tsgen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;


public class TypeScriptGeneratorTest {

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
		TypeScriptGenerator.main(new String[]{".\\","ModuleFirstTsgenerator12"});
		String resultFile = null;
		File fileDirectory = new File("/home/iternia/git/appops/appops/appops-infra/appops-infra-tsgenerator/");
		File[] allFiles = fileDirectory.listFiles();
		for(File files : allFiles)
			if(files.getName().equals(".\\ModuleFirstTsgenerator12.d.ts"))
				resultFile = files.getName();

		assertEquals(".\\ModuleFirstTsgenerator12.d.ts", resultFile);

		/**
		 * Checks for the content of the file for expected methods from TestClass.java
		 * Also checks for a non-expected method for false condition.
		 * */
		assertTrue("Checking file content for sayHello()",testContent(resultFile, "sayHello"));
		assertTrue("Checking file content for sayBye()",testContent(resultFile, "sayBye"));
		assertFalse("Checking file content for sayNothing()",testContent(resultFile, "sayNothing()"));
	}
	
	/**
	 * @param resultFile : Type Script Generated file name
	 * 		  methodName : Method name as content which is expected or unexpected in the TypeScript File
	 * @return true if content matches 
	 * 		   false if content does not match
	 * */
	public boolean testContent(String resultFile, String methodName) throws IOException{
		String nextLine;
		BufferedReader inputReader = new BufferedReader(new FileReader(resultFile));
		while ((nextLine = inputReader.readLine()) != null){
			if(nextLine.contains(methodName)) 		    
				return true;
		}
		return false;
	}
}
