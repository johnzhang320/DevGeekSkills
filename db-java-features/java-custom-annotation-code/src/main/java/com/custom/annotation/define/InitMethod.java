package com.custom.annotation.define;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
/*
 * This is method level annotation
 * to mark the method which before serializing an object to a JSON string, we want to execute some method to initialize an object.
 *  For that reason, we’re going to create an annotation to mark this method:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InitMethod {
	
}
