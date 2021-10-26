package com.donnu.oop_laba3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect
{
    @Before("execution(public Jewel putInWarehouse(Jewel))")
    public void beforePutInWarehouse(){
        System.out.println("beforePutInWarehouse: вызван метод putInWarehouse" );
    }

    @AfterReturning(pointcut = "execution(public Jewel putInWarehouse(Jewel))", returning = "jewel")
    public void afterReturningPutInWarehouse(Jewel jewel){
        System.out.printf("afterReturningPutInWarehouse: новый товар добавлен на склад - %s, материал - %s\n", jewel.getType(), jewel.getMaterial());
    }

    @AfterThrowing(pointcut = "execution( public Jewel getJewel(int))", throwing = "exception")
    public void afterThrowingGetJewel(Exception exception){
        System.out.printf("afterThrowingGetJewel: выброшено исключение %s\n", exception.getMessage() );
    }

    @After( "execution( public Jewel getJewel(int))")
    public void afterGetJewel(){
        System.out.println("afterGetJewel: метод получения товара отработал\n");
    }
    @Around("execution(public Jewel getJewel(int))")
    public Object aroundGetJewel(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        System.out.printf("Signature метода GetJewel: %s\n", methodSignature);
        System.out.printf("Method = %s\n", methodSignature.getMethod());
        System.out.printf("Method name = %s\n", methodSignature.getName());
        System.out.printf("Returned type - %s\n", methodSignature.getReturnType());
        if(methodSignature.getName().equals("getJewel")){
            System.out.println("Выполняется Advice @Around для метода getJewel\n");
        }
        System.out.println("aroundGetJewel: попытка получить товар со склада\n");
        Object target = null;
        Object[] args = proceedingJoinPoint.getArgs();
        try{
            target = proceedingJoinPoint.proceed();
            System.out.printf("Взят товар со склада.\n Товар - %s\n", args[0]);
        }catch (Exception e){
            if(args.length>0){
                System.out.printf("Нет товара на складе под номером - %s\n", args[0]);
            }
            System.out.println(e.getMessage());
        }
        return  target;
    }


}
