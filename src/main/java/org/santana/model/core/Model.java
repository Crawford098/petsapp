package org.santana.model.core;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import org.santana.annotation.modelAnnotation.PrimaryKey;
import org.santana.annotation.modelAnnotation.TableName;

public class Model implements IModel {

    public Model() {

    }

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

//todoProbar
    public String tableName() {
        String modelName = this.getClass().getSimpleName();
        String tableName = modelName.replaceAll("Model$", "");

        try {
            Field field = this.getClass().getDeclaredField(modelName);

            if (field.isAnnotationPresent(TableName.class)) {
                TableName tableAnnotation = field.getAnnotation(TableName.class);

                if (!tableAnnotation.tableName().isEmpty()) {
                    tableName = tableAnnotation.tableName();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tableName.toLowerCase();
    }


    public Map<String, Object> getPropertiesWithValue() {

        Field[] fields = this.getClass().getDeclaredFields();
        Map<String, Object> resultMap = new LinkedHashMap<>();

        for (Field field : fields) {

            if (this.isPrimaryKey(field)) {
                continue;
            }

            try {
                field.setAccessible(true);

                String name = field.getName();
                var value = field.get(this);
                if (value != null) {
                    resultMap.put(field.getName(), value);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return resultMap;
    }
}
