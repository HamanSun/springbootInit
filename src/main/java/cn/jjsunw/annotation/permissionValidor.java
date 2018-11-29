package cn.jjsunw.annotation;

import java.lang.annotation.*;

/**
 * Custom annotations. service filter
 */

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface permissionValidor {
	String description() default "";
}