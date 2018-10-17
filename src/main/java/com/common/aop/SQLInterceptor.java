package com.common.aop;

import com.common.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author fantasy
 * @date 2018/10/16
 * @time 16:54
 */

@Aspect
@Component
public class SQLInterceptor {
    private static final String VERSION = "version";
    private static final String CREATOR = "creator";
    private static final String CREATE_TIME = "createTime";
    private static final String LAST_UPDAT_TIME = "lastUpdateTime";
    private static final String MODIFIER = "modifier";

    /**
     * 获取用户信息
     * @return
     */
    private User getUserInfo(){
        User user = new User();
        user.setCreator("XXX");
        user.setModifier("XXX");
        return user;
    }

    /**
     * insert
     * @param joinPoint
     */
    @Before("execution(* com.common.mapper.*.insert*(..))")
    public void beforeInsert(JoinPoint joinPoint) {
        User user = getUserInfo();
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            Object argument = args[0];
            if (argument instanceof List) {

            } else if (argument instanceof Map) {
                Map map = (Map)argument;
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                map.put(VERSION, 1L);
                map.put(CREATOR, user.getCreator());
                map.put(CREATE_TIME, timestamp);
                map.put(MODIFIER, user.getModifier());
                map.put(LAST_UPDAT_TIME, timestamp);
            } else {
                BeanWrapper beanWrapper = new BeanWrapperImpl(argument);
                injectCreateValue(beanWrapper, user.getCreator());
            }
        }
    }

    /**
     * update
     * @param joinPoint
     */
    @Before("execution(* com.common.mapper.*.update*(..))")
    public void beforeUpdate(JoinPoint joinPoint) {
        User user = getUserInfo();
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            Object argument = args[0];
            if (argument instanceof List) {

            } else if (argument instanceof Map) {
                Map map = (Map)argument;
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                map.put(MODIFIER, user.getModifier());
                map.put(LAST_UPDAT_TIME, timestamp);
            } else {
                BeanWrapper beanWrapper = new BeanWrapperImpl(argument);
                updateCreateValue(beanWrapper, user.getModifier());
            }
        }
    }


    /**
     * 属性注入
     *
     * @param beanWrapper
     * @param
     */
    private void injectCreateValue(BeanWrapper beanWrapper, String createdBy) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        wrappedProperty(beanWrapper, VERSION, 1L);
        wrappedProperty(beanWrapper, CREATOR, createdBy);
        wrappedProperty(beanWrapper, CREATE_TIME, timestamp);
        wrappedProperty(beanWrapper, MODIFIER, createdBy);
        wrappedProperty(beanWrapper, LAST_UPDAT_TIME, timestamp);
    }

    /**
     * 属性注入
     *
     * @param beanWrapper
     * @param
     */
    private void updateCreateValue(BeanWrapper beanWrapper, String updateBy) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        wrappedProperty(beanWrapper, MODIFIER, updateBy);
        wrappedProperty(beanWrapper, LAST_UPDAT_TIME, timestamp);
    }


    /**
     * 注入某个指定的属性
     *
     * @param beanWrapper
     * @param propertyName
     * @param value
     */
    private void wrappedProperty(BeanWrapper beanWrapper, String propertyName, Object value) {
        if (beanWrapper.isWritableProperty(propertyName)) {
            beanWrapper.setPropertyValue(propertyName, value);
        }
    }
}
