package cn.jjsunw.annotation;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LogAspect {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String LOG_FORMATTER = "%s.%s - %s";

	@Pointcut("execution(public * cn.jjsunw.controller.*.add*(..))")
	public void addpointcut() {
	}

	@Pointcut("execution(public * cn.jjsunw.controller.*.delete*(..))")
	public void deletepointcut() {
	}

	@Pointcut("execution(public * cn.jjsunw.controller.*.update*(..))")
	public void updatepointcut() {
	}

	@Pointcut("execution(public * cn.jjsunw.controller.*.query*(..))")
	public void querypointcut() {
	}

	@Pointcut("execution(public * cn.jjsunw.controller.*.*(..))")
	public void throwCut() {
	}

	@Before("addpointcut()")
	@AfterThrowing
	public void addLog(JoinPoint jp) {
		log(jp);
	}

	@Before("updatepointcut()")
	public void updateLog(JoinPoint jp) {
		log(jp);
	}

	@Before("deletepointcut()")
	public void deleteLog(JoinPoint jp) {
		log(jp);
	}

	@AfterReturning(pointcut = "querypointcut()", returning = "ret")
	public void queryLog(JoinPoint jp, Object ret) {
		logWithReturning(jp, ret);
	}

	@AfterThrowing(throwing = "ex", pointcut = "throwCut()")
	public void throwLog(Throwable ex) {
		logger.error(ex.getMessage());
	}

	private void log(JoinPoint jp) {
		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		Object[] params = jp.getArgs();
		logger.info(String.format(LOG_FORMATTER, className, methodName, Arrays.toString(params)));
	}

	private void logWithReturning(JoinPoint jp, Object ret) {
		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		Object[] params = jp.getArgs();
		logger.info(String.format(LOG_FORMATTER, className, methodName, Arrays.toString(params)), ret.toString());
	}
}
