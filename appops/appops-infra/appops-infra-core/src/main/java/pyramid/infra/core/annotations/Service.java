package pyramid.infra.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Debasish.Padhy
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
	
	/**
	 * Defaults to fully canonical name of class. Do provide an alternative if you need friendly name
	 * @return 
	 */
	public String name() default Service.DEFAULTNAME ;

	public Class<?> parent() default Service.NULLPARENT.class;
	
	public static final class NULLPARENT {}
	
	public static final String DEFAULTNAME = "" ;

}