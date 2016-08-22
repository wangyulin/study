package com.wyl.demo.server.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 * 记录方法的执行时间
 * @author wangyulin
 */
public class MethodTimeAdvice implements MethodInterceptor {
	
    protected final Log log = LogFactory.getLog(MethodTimeAdvice.class);
 
    /**
     * 拦截要执行的目标方法
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
    	Object result = invocation.proceed();
//        //用 commons-lang 提供的 StopWatch 计时，Spring 也提供了一个 StopWatch
//        StopWatch clock = new StopWatch();
//        clock.start(); //计时开始
//        clock.stop();  //计时结束
// 
//        //方法参数类型，转换成简单类型
//        Class[] params = invocation.getMethod().getParameterTypes();
//        String[] simpleParams = new String[params.length];
//        for (int i = 0; i < params.length; i++) {
//            simpleParams[i] = params[i].getSimpleName();
//        }
//        System.out.println("-------------> clock.getTime() :"  + clock.getTime());
//        log.debug("Takes:" + clock.getTime() + " ms ["
//                + invocation.getThis().getClass().getName() + "."
//                + invocation.getMethod().getName() + "("+StringUtils.join(simpleParams,",")+")] ");
        return result;
    }
}