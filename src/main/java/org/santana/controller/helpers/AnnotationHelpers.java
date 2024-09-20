package org.santana.controller.helpers;

import java.lang.reflect.Field;

public class AnnotationHelpers {

    /**
     * Return the name of first field where annotation is present.
     *
     * @param fields A Field List.
     * @param anotationClass The class of the anotation.
     * @return String.
     */
    public static String getAnnotationName(Field[] fields, Class anotationClass) {
        for (Field field : fields) {
            if (field.isAnnotationPresent(anotationClass)) {
                return field.getName();
            }
        }

        return null;
    }

    /**
     * Return the first field where annotation is present.
     *
     * @param fields A Field List.
     * @param anotationClass The class of the anotation.
     * @return Field.
     */
    public static Field getAnnotationField(Field[] fields, Class anotationClass) {
        for (Field field : fields) {
            if (field.isAnnotationPresent(anotationClass)) {
                return field;
            }
        }

        return null;
    }
}
