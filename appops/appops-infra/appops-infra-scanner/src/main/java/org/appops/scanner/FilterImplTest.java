package org.appops.scanner;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FilterImplTest {
	/*
	 * @responsibility-Neglects packages and only accepts .class files
	 */
	@Test
	public void acceptsTest() {
		String filename = "/iternia/abc.class";
		String filename1 = "/javax/abc.class";
		FilterImpl filter = new FilterImpl();
		assertTrue(filter.accepts(filename));
	}

	@Test
	public void ignoreScanTest() {
		String packagename1 = "java.";
		String packagename2 = "in.com";
		FilterImpl filter = new FilterImpl();
		assertTrue(filter.ignoreScan(packagename2));
	}

}
