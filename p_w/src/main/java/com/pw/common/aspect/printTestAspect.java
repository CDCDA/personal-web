package com.pw.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/***
 * @author cyd
 * @date 2023/7/31 10:04
 * @description <测试切面----打印>
 **/
@Aspect
@Component
public class printTestAspect {
    // 定义一个切入点
    @Pointcut("execution(* com.pw.service.*.*(..))")
    public void print() {
        System.out.println("print操作");
    }

    // 前置通知
    @Before(value = "print()")//代表使用print()这个切入点，print()中的内容不执行
    public void before(JoinPoint jp) {
        //获取方法名
        String name = jp.getSignature().getName();
        System.out.println("[" + name + "]方法开始执行...");
    }

    // 后置通知
    @After(value = "print()")
    public void after(JoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println("[" + name + "]方法执行结束...");
    }

    // 返回通知
    @AfterReturning(value = "print()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        String name = jp.getSignature().getName();
        System.out.println("[" + name + "]方法返回值为：" + result);
    }

    // 异常通知
    @AfterThrowing(value = "print()", throwing = "e")
    public void afterThrowing(JoinPoint jp, Exception e) {
        String name = jp.getSignature().getName();
        System.out.println("[" + name + "]方法出现异常：" + e.getMessage());
    }

    // 环绕通知
    @Around("print()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String name = pjp.getSignature().getName();
        // 计算方法执行时间
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("[" + name + "]方法执行时间为：" + (end - start) + " ms");
        return result;
    }
}
