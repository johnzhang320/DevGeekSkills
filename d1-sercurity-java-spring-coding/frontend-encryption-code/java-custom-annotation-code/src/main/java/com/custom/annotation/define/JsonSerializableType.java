package com.custom.annotation.define;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
/*
 * This is Class Level Annotation
 * As we can see, our first annotation has runtime visibility, and we can apply it to types (classes). 
 * Moreover, it has no methods, and thus serves as a simple marker to mark classes that can be serialized into JSON.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JsonSerializableType {

}
