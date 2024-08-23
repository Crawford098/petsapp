package org.santana.model.core;

import java.lang.reflect.Field;

public interface IModel {

/**
 * Verify if the field is a model primaryKey.
 *
 * @param Field Model Field.
 * @return Return true if the given field is has Primarykey Annotation.
 */
    public boolean isPrimaryKey(Field field);
}
