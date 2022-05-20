package com.cg.payment.config.aop;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Point cut that matches all repositories, services and Web REST endpoints.
	 */
	  @Pointcut("within(@org.springframework.stereotype.Repository *)" +
		        " || within(@org.springframework.stereotype.Service *)" +
		        " || within(@org.springframework.web.bind.annotation.RestController *)")
	  public void springBeanPointcut() {
		// Method is empty as this is just a Pointcut, the implementations are in the
		// advices.
	  }

	/**
	 * Point cut that matches all Spring beans in the application's main packages.
	 */
	  @Pointcut("within(com.cg.payment.controller..*)" +
	        " || within(com.cg.payment.repository..*)" +
	        " || within(com.cg.payment.service..*)")
	 public void applicationPackagePointcut() {
		// Method is empty as this is just a Pointcut, the implementations are in the
		// advices.
	 }

	  /**
	  * Advice that logs methods throwing exceptions.
	  *
	  * @param joinPoint join point for advice
	  * @param e   exception
	  */
	  @AfterThrowing(pointcut ="applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
	  public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		  log.error("Exception in {}.{}() with cause = {}",
		  joinPoint.getSignature().getDeclaringTypeName(),
		  joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() :
		  "NULL"); }	  

	  /**
	  * Advice that logs when a method is entered and exited.
	 *
	 * @param joinPoint join point for advice
	 * @return result
	 * @throws Throwable throws IllegalArgumentException
	 */
	 @Around("applicationPackagePointcut() && springBeanPointcut()")
	 public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		if (log.isDebugEnabled()) {
			log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
		}
		try {
			Instant start = Instant.now();
			Object result = joinPoint.proceed();
			Instant finish = Instant.now();
			long timeElapsed = Duration.between(start, finish).toMillis();
			if (log.isDebugEnabled()) {
				log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
						joinPoint.getSignature().getName(), result);
				log.info("Time Taken =" + new SimpleDateFormat("mm:ss:SSS").format(new Date(timeElapsed)));
			}
			return result;
		} catch (IllegalArgumentException e) {
			log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
			throw e;
		}
	}

}
