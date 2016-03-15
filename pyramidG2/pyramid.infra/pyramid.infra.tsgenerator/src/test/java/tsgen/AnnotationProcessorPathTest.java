package tsgen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tsgen.processor.AnnotationProcessor;

public class AnnotationProcessorPathTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

		String pack = "a.b.c";
		String packagePath = pack.replaceAll("\\.", "\\\\");

		AnnotationProcessor processor = new AnnotationProcessor();

		processor.setModuleNames("Dto,Common");
		processor.setModulePath("pyramid\\bioffice\\common");

		System.out.println(packagePath);
		System.out.println("getResourcesFolder " + " > " + processor.getResourcesFolder());
		System.out.println("getDestinationResourcesFolder " + " > " + processor.getDestinationResourcesFolder());
		System.out.println("getDestinationResourceElementPath" + " > "
				+ processor.getDestinationResourceElementPath("pyramid\\bioffice\\common", "Dto"));
		System.out.println("getSourceResourceElementPath" + " > "
				+ processor.getSourceResourceElementPath("pyramid\\bioffice\\common", "Dto"));
		System.out.println("getPyramidG2Folder" + " > " + processor.getPyramidG2Folder());
		System.out.println("getDestinationFolder" + " > " + processor.getDestinationFolder());
		System.out.println("getSourcesFolder" + " > " + processor.getSourcesFolder());
		System.out.println("getSourceElementPath" + " > "
				+ processor.getSourceElementPath("pyramid.bioffice.common.dto", "Country"));
		System.out.println("getDestinationElementPath" + " > "
				+ processor.getDestinationElementPath("pyramid.bioffice.common.dto", "Country"));

	}

}
