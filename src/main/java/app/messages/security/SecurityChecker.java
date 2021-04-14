package app.messages.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityChecker {
	
	private final static Log log = LogFactory.getLog(SecurityChecker.class);
	
	@Pointcut("@annotation(app.messages.security.SecurityCheck)")
	public void checkMethodSecurity() {}
	
	@Around("checkMethodSecurity()")
	public Object checkSecurity(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("Checking method security...");
		// TODO - Security Check
		Object result = joinPoint.proceed();
		return result;
	}
}
