package cn.jjsunw.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import cn.jjsunw.common.ServiceException;

/**
 * point cut class
 * 
 */
@Aspect
@Component
public class PermissionAspect {

	@Pointcut("@annotation(cn.jjsunw.annotation.permissionValidor)")
	public void permissionAspect() {
	}

	/**
	 * preAdvice. Controller layer exception filter
	 * 
	 * @param joinPoint
	 * 
	 */
	@Before("permissionAspect()")
	public void doBefore(JoinPoint joinPoint) {
		if(true) {
			throw new ServiceException("no permission................");
		}
	}
}