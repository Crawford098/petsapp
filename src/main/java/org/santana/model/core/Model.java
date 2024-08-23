package org.santana.model.core;

import java.lang.reflect.Field;

import org.santana.annotation.modelAnnotation.PrimaryKey;

public class Model implements IModel {

    // todo: finish this function.
    @Override
    public boolean isPrimaryKey(Field field) {
        return (field.isAnnotationPresent(PrimaryKey.class));
    }

    public String primaryKeyValue() {
        String anotationValue = null;
        if (this.getClass().isAnnotationPresent(PrimaryKey.class)) {
            anotationValue = this.getClass().getAnnotation(PrimaryKey.class).value();
        }
        return anotationValue;
    }
}
