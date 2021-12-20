package com.example.demo.aspect;

import com.example.demo.model.DateRange;
import com.example.demo.model.contract.IDateRangeFiltered;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
public class DateRangeSetterAspect {

    /**
     * The pointcut expression describes that this advice will be applied to:
     * classes WITHIN the package containing all the Spring Controllers
     * where the EXECUTED methods of these Controllers are returning a class which implements IDateRangeFiltered
     * finally, map the method ARGS by convenience
     */
    @Around("within(com.example.demo.controller.*) && " +
            "execution(public com.example.demo.model.contract.IDateRangeFiltered+ *(..)) &&" +
            "args(start, end)"
    )
    public Object setDateRange(ProceedingJoinPoint joinPoint, Instant start, Instant end) throws Throwable {

        DateRange appliedDateRange = new DateRange(start, end);
        IDateRangeFiltered returnedObject = ((IDateRangeFiltered) joinPoint.proceed());
        returnedObject.setDateRange(appliedDateRange);

        return returnedObject;
    }
}
