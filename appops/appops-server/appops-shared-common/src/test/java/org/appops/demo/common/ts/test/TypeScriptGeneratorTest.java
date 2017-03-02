package org.appops.demo.common.ts.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import tsgen.TypeScriptGenerator;

public class TypeScriptGeneratorTest {

	//@Test
	public void testGeneration() throws IOException{
		
		final File f = new File(TypeScriptGeneratorTest.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		
		TypeScriptGenerator.main(new String[] { jsinterop.annotations.JsType.class.getCanonicalName() , f.getParent() , "JsTestHost" });
		
		File output = new File(f.getParent() + "JsTestHost.d.ts") ;
		
		assertTrue(output.exists()) ;
		
		output.delete();	

	}
}
