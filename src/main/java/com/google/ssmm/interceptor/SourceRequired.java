package com.google.ssmm.interceptor;

import com.google.ssmm.common.SourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xuhan on 16-11-17.
 */
@Target(value ={ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SourceRequired {
    SourceType[] sources();
}
