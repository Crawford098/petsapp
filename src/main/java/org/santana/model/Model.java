package org.santana.model;

import java.lang.reflect.Field;

import org.santana.annotation.modelAnnotation.PrimaryKey;

public class Model implements IModel {

    // todo: finish this function.
    @Override
    public boolean isPrimaryKey(Field field) {
        return (field.isAnnotationPresent(PrimaryKey.class));
    }
}
