package ru.bender.testdb.start;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionalAspect {

//    @Pointcut("execution(* *.insert(..))")
//    @Pointcut("target(public int ru.bender.testdb.interfaces.AuthorDAO.insert(..))")
    @Pointcut("@annotation(ru.bender.testdb.annotations.testpointcut)")
    private void justPointCut() {

    }

    @Before("justPointCut()")
    public void isActualTransactionActive(JoinPoint joinPoint) {
        System.out.println(joinPoint.getTarget().getClass().getName());
//        System.out.println("from aspect - " + TransactionSynchronizationManager.isActualTransactionActive());
//        System.out.println("from aspect - " + this);
    }

}
