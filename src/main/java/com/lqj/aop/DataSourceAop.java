package com.lqj.aop;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.lqj.datasource.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @Author lqj
 * @Date 2022-11-10 9:16
 */
@Aspect
@Component
@Slf4j
public class DataSourceAop {

    @Pointcut("execution (* com.lqj.controller.*.*(..))")
    public void sourceSwitch() {}



    @Around("sourceSwitch()")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) {

        //获取到所有的参数值的数组
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //获取方法的所有参数名称的字符串数组
        String[] parameterNames = methodSignature.getParameterNames();

        Integer index = null;
        for (int i = 0 ; i < parameterNames.length ; i++){
            if("dataSourceName".equals(parameterNames[i])){
                index = i;
                break;
            }
        }

        if(index != null){
            String dataSourceName = (String)args[index];
            //增加数据源
            if(DynamicDataSource.dataSourcesMap.get(dataSourceName) == null){
                DruidDataSource druidDataSource = new DruidDataSource();
                druidDataSource.setUrl("jdbc:mysql://localhost:3306/"+dataSourceName+"?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&useAffectedRows=true");
                druidDataSource.setUsername("root");
                druidDataSource.setPassword("root");
                DynamicDataSource.dataSourcesMap.put(dataSourceName, druidDataSource);
            }
            //切换数据源
            DynamicDataSource.setDataSource(dataSourceName);
        }

        try {
            joinPoint.proceed();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        //重置默认数据域
        DynamicDataSource.clear();

    }

}
