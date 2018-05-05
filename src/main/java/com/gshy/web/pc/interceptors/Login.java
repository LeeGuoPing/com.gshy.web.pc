package com.gshy.web.pc.interceptors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bj58.wf.mvc.annotation.Interceptor;
import com.bj58.wf.mvc.annotation.Interceptor.InterceptorType;


@Interceptor(value = LoginInterceptor.class, type = InterceptorType.ACTION)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
    int order() default 1;
}
