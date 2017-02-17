package tsgen;

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
		TypeScriptGenerator.main(new String[]{".\\" , "MyTestModule"});;

	}

}
