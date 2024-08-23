package org.santana.controller.helpers;

import java.lang.reflect.Field;

public class AnnotationHelpers
{
    //Return the first field where annotation is present.
    public static String getAnnotationName(Field[] fields, Class anotationClass) {
        for (Field field : fields) {
            if (field.isAnnotationPresent(anotationClass)) {
                return field.getName();
            }
        }

        return null;
    }

    public static Field getAnnotationField(Field[] fields, Class anotationClass) {
        for (Field field : fields) {
            if (field.isAnnotationPresent(anotationClass)) {
                return field;
            }
        }

        return null;
    }
}
