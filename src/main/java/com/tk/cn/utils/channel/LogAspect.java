package com.tk.cn.utils.channel;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * <p>Title: </p>
 * <p>Description: 配置日志</p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月11日 下午6:41:11
 */
@Component
@Aspect
public class LogAspect {

	@Pointcut("execution(* com.tk.cn.utils.kmtchannel.service.impl.*.*(..))")
	private void allMethod(){
		
	}
	
	// 针对指定的切入点表达式选择的切入点应用前置通知  
    @Before("execution(* com.tk.cn.utils.kmtchannel.service.impl.*.*(..))")  
    public void before(JoinPoint call) {  
        String className = call.getTarget().getClass().getName();  
        String methodName = call.getSignature().getName();  
        System.out.println("【方法前置通知】:" + className + "类的" + methodName  
                + "方法开始了...");  
    }  
  
    // 访问命名切入点来应用后置通知  
    @AfterReturning("allMethod()")  
    public void afterReturn() {  
        System.out.println("【方法后置通知】:方法正常结束...");  
    }  
  
    // 应用最终通知  
    @After("allMethod()")  
    public void after() {  
        System.out.println("【方法最终通知】:不管方法有没有正常执行完成,一定会返回的...");  
    }  
  
    // 应用异常抛出后通知  
    @AfterThrowing("allMethod()")  
    public void afterThrowing() {  
        System.out.println("【方法异常抛出后】:方法执行时出现异常...");  
    }  
  

}
