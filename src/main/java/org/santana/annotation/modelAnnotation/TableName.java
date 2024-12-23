package org.santana.annotation.modelAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TableName {
    public String value() default "";
}
