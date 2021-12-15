package cs544.team1.auth.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


//@Aspect
//@Component
public class RoleAdvice {

   // @Before("execution(* cs544.team1.controller.*.*(..))")
    public void tracebeforemethod(JoinPoint joinpoint) {

        System.out.println("before execution of method "+joinpoint.getSignature().getName());
    }


//    @Around("execution(* *.*(..))")
//    public Object profile(ProceedingJoinPoint call) throws Throwable {
//        StopWatch clock = new StopWatch("");
//        clock.start(call.toShortString());
//
//        Object object = call.proceed();
//
//        clock.stop();
//
//        System.out.println(clock.prettyPrint());
//
//        return object;
//    }
}
