package tsgen.jackson.module.conf.typename;

public class WithEnclosingClassTSTypeNamingStrategy extends SimpleJacksonTSTypeNamingStrategy {

	@Override
	public String getName(Class<?> rawClass) {
		String className = rawClass.getName();
		return className.substring(className.lastIndexOf('.') + 1);
	}

}
