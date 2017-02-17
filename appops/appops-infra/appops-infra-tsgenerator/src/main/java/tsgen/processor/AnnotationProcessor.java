package tsgen.processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

import jsinterop.annotations.JsType;

/**
 * 
 * @author Debasish.Padhy
 * 
 *         TODO(1) : Currently hard coded to use fixed top level project name -
 *         PyramidG2 TODO(2) : Need to make it more configurable for project
 *         source / resource folders
 * 
 *         You can configure the GWT modules to copy by specifying the module
 *         names in your POM maven compiler configuration. See the samples below
 * 
 *         <compilerArg>-AxmlModuleNames=Dto,GwtBiOffice</compilerArg> <!-- no
 *         trailing gwt.xml / -->
 *         <compilerArg>-AmodulePath=pyramid\\bioffice\\common</compilerArg>
 *         <!-- no trailing or beginning / -->
 */

@SupportedOptions({ "xmlModuleNames", "modulePath" })
@SupportedAnnotationTypes({ "jsinterop.annotations.JsType" })
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class AnnotationProcessor extends AbstractProcessor {

	private ProcessingEnvironment processingEnv = null;

	private String projectName = "pyramidG2";

	private String moduleNames = null;
	private String modulePath = null;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getModuleNames() {
		return moduleNames;
	}

	public void setModuleNames(String moduleNames) {
		this.moduleNames = moduleNames;
	}

	public String getModulePath() {
		return modulePath;
	}

	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}

	public String getProjectRoot() {
		return projectRoot;
	}

	public void setProjectRoot(String projectRoot) {
		this.projectRoot = projectRoot;
	}

	public String getFileSeparator() {
		return fileSeparator;
	}

	public void setFileSeparator(String fileSeparator) {
		this.fileSeparator = fileSeparator;
	}

	private String projectRoot = null;

	private String fileSeparator = null;

	public AnnotationProcessor() {

		projectRoot = System.getProperty("maven.multiModuleProjectDirectory");

		if (projectRoot == null)
			projectRoot = "C:\\Users\\debasish.padhy\\git\\G2\\trunk\\pyramidG2\\pyramid.server\\pyramid.bioffice.common";

		fileSeparator = System.getProperty("file.separator");

	}

	@Override
	public synchronized void init(ProcessingEnvironment env) {

		processingEnv = env;

		moduleNames = env.getOptions().get("xmlModuleNames");
		modulePath = env.getOptions().get("modulePath");

		System.out.println("project Root is - " + projectRoot);

		super.init(env);
	}

	public String getResourcesFolder() {
		return projectRoot + fileSeparator + "src" + fileSeparator + "main" + fileSeparator + "resources"
				+ fileSeparator;
	}

	public String getDestinationResourcesFolder() {
		return getPyramidG2Folder() + "pyramid.server" + fileSeparator + "pyramid.bioffice.gwt" + fileSeparator + "src"
				+ fileSeparator + "main" + fileSeparator + "resources" + fileSeparator;
	}

	public String getSourcesFolder() {
		return projectRoot + fileSeparator + "src" + fileSeparator + "main" + fileSeparator + "java" + fileSeparator;
	}

	public String getDestinationFolder() {
		return getPyramidG2Folder() + "pyramid.server" + fileSeparator + "pyramid.bioffice.gwt" + fileSeparator + "src"
				+ fileSeparator + "main" + fileSeparator + "java" + fileSeparator;
	}

	public String getPyramidG2Folder() {

		if (projectRoot == null) {
			throw new RuntimeException(
					"Cannot locate project root while running Annotation processor - files cannot be copied ");
		}
		// System.getProperty("maven.multiModuleProjectDirectory");

		int ch = projectRoot.lastIndexOf(projectName);

		String str = projectRoot.substring(0, ch + projectName.length()) + fileSeparator;

		return str;
	}

	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {

		Set<? extends Element> elements = env.getRootElements();

		Set<TypeElement> toCopy = new HashSet<>();

		for (Element ele : elements) {
			if (ele.getKind() == ElementKind.CLASS) {
				Annotation ant = ele.getAnnotation(JsType.class);
				if (ant != null) {
					toCopy.add((TypeElement) ele);
				}
			}
		}

		try {
			copyJavaFileToDestination(toCopy);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			copyGwtModuleFilesToDestination();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	private void copyGwtModuleFilesToDestination() throws IOException {

		StringTokenizer tokens = new StringTokenizer(moduleNames, ",");

		// TODO fix this here

		String token;

		while (tokens.hasMoreElements()) {
			token = tokens.nextElement().toString();
			System.out.println("file to be copied is - " + token);
			copyFile(getSourceResourceElementPath(modulePath, token),
					getDestinationResourceElementPath(modulePath, token));
		}

	}

	public String getSourceResourceElementPath(String pack, String module) {
		return getResourcesFolder() + pack + fileSeparator + module + ".gwt.xml";
	}

	public String getDestinationResourceElementPath(String pack, String module) {
		return getDestinationResourcesFolder() + pack + fileSeparator + module + ".gwt.xml";
	}

	public String getSourceElementPath(String pack, String element) {
		String fullPathToType = getSourcesFolder() + pack + fileSeparator + element + ".java";
		return fullPathToType;
	}

	public String getDestinationElementPath(String pack, String element) {
		String fullPathToOutput = getDestinationFolder() + pack + fileSeparator + element + ".java";
		return fullPathToOutput;
	}

	private void copyJavaFileToDestination(Set<TypeElement> toCopy) throws IOException {

		for (TypeElement ele : toCopy) {

			PackageElement pack = (PackageElement) ele.getEnclosingElement();

			final String packagePath = pack.getQualifiedName().toString().replaceAll("\\.", "\\\\");

			copyFile(getSourceElementPath(packagePath, ele.getSimpleName().toString()),
					getDestinationElementPath(packagePath, ele.getSimpleName().toString()));
		}
	}

	public void copyFile(String source, String destination) throws IOException {
		File f = new File(source);
		FileReader reader = new FileReader(f);
		BufferedReader breader = new BufferedReader(reader);

		File newFile = new File(destination);

		if (newFile.exists())
			newFile.delete();

		File parent = newFile.getParentFile();
		if (!parent.exists() && !parent.mkdirs()) {
			throw new IllegalStateException("Couldn't create dir: " + parent);
		}

		newFile.createNewFile();

		FileWriter fWriter = new FileWriter(newFile);
		BufferedWriter writer = new BufferedWriter(fWriter);

		String line;
		while ((line = breader.readLine()) != null) {
			writer.append(line);
			writer.newLine();
			System.out.println("writing line - " + line);
		}

		breader.close();
		writer.close();

	}

	public void printElementDetails(Element ele) {

		String message = ele.getSimpleName() + " is of kind " + ele.getKind() + " enclosed in > "
				+ ele.getEnclosingElement() + " of type > " + ele.getEnclosingElement().getKind();

		System.out.println(message);

		processingEnv.getMessager().printMessage(Kind.NOTE, message, ele);
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}
}
