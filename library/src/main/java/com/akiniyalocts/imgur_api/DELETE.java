package com.akiniyalocts.imgur_api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import retrofit.http.RestMethod;

/**
 * Retrofit DELETE Annotation does not support Delete Actions with Body
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RestMethod(value = "DELETE", hasBody = true)
public @interface DELETE {
    String value();
}