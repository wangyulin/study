package com.wyl.spring.demo.soundsystem.chapter4;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by wangyulin on 15/01/2017.
 */

@Aspect
public class Audience {

    @Pointcut("execution(* com.wyl.spring.demo.soundsystem.chapter4.Performance.perform(..))")
    public void performance(){}

    @Before( "performance()" )
    public void silenceCellPhones() {
        System.out.println ("Sliencing cell phones");
    }

    @Before( "performance()" )
    public void takeSeats() {
        System.out.println ("Taking seats");
    }

    @AfterReturning( "performance()" )
    public void applause() {
        System.out.println ("CLAP CLAP CLAP!!!");
    }

    @AfterThrowing( "performance()" )
    public void demandRefund() {
        System.out.println ("Demanding a refund");
    }

    @Around ( "performance()" )
    public void watchPerformance(ProceedingJoinPoint jp) {
        try{
            System.out.println ("sliencing cell phone");
            System.out.println ("Taking seats");
            jp.proceed ();
            System.out.println ("CLAP CLAP CLAP !!!");
        } catch (Throwable e) {
            System.err.println ("Demanding a refund");
        }
    }

}
