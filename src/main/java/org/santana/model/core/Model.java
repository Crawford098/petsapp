package org.santana.model.core;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import org.santana.annotation.modelAnnotation.PrimaryKey;
import org.santana.annotation.modelAnnotation.TableName;
import static org.santana.controller.helpers.AnnotationHelpers.getAnnotationField;
import static org.santana.controller.helpers.AnnotationHelpers.getAnnotationName;
import static org.santana.controller.helpers.StringHelper.trimL;

public class Model implements IModel {

    public Model() {

    }

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

    public String primarykeyName() {
        Field[] modelFields = this.getClass().getDeclaredFields();
        return getAnnotationName(modelFields, PrimaryKey.class);
    }

    /**
     * Get database`s table name.
     *
     * @return Return tableName from de Model.
     * @throws Exception Anottation exception.
     */
    public String tableName() {
        String modelName = this.getClass().getSimpleName();
        String tableName = modelName.replaceAll("Model$", "").toLowerCase();

        try {
            Field[] fields = this.getClass().getDeclaredFields();
            Field field = getAnnotationField(fields, TableName.class);

            TableName tableAnnotation = field.getAnnotation(TableName.class);

            if (!tableAnnotation.value().isEmpty()) {
                tableName = tableAnnotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tableName;
    }

    /**
     * Get model columns in string separed by commas.
     *
     * @return String
     */
    public String getModelColumns(boolean propertieWithValue) {
        Field[] fields = this.getClass().getDeclaredFields();
        String columns = "";

        for (Field field : fields) {

            if (this.isPrimaryKey(field)) {
                continue;
            }

            try {
                if (propertieWithValue) {
                    if (field.get(this) != null) {
                        columns += field.getName() + ",";
                    }

                } else {
                    columns += field.getName() + ",";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return trimL(columns);
    }

    /**
     * Get model columns in string separed by commas.
     *
     * @return String
     */
    public String getModelColumns() {
        return this.getModelColumns(false);
    }

    /**
     * Get model values in string separed by commas.
     *
     * @return String
     */
    public String getModelValues(boolean propertieWithValue) {
        Field[] fields = this.getClass().getDeclaredFields();
        StringBuilder values = new StringBuilder();

        try {

            for (Field field : fields) {

                if (this.isPrimaryKey(field)) {
                    continue;
                }

                field.setAccessible(true);
                Object value = "";

                if (propertieWithValue) {
                    if (field.get(this) != null) {
                        value = "'" + field.get(this) + "'";
                    }

                } else {
                    value = "'" + field.get(this) + "'";
                }

                values.append(value).append(",");
            }

        } catch (IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        if (values.length() > 0) {
            values.setLength(values.length() - 1);
        }

        return values.toString();
    }

    public String getModelValues() {
        return this.getModelValues(false);
    }

    public Map<String, Object> getPropertiesWithValue() {
        Map<String, Object> resultMap = this.getPropertyMap(true);
        return resultMap;
    }

    public Map<String, Object> getAllProperties() {
        Map<String, Object> resultMap = this.getPropertyMap(false);
        return resultMap;
    }

    private Map<String, Object> getPropertyMap(boolean withValue) {

        Field[] fields = this.getClass().getDeclaredFields();
        Map<String, Object> resultMap = new LinkedHashMap<>();

        for (Field field : fields) {

            if (this.isPrimaryKey(field)) {
                continue;
            }

            try {
                field.setAccessible(true);

                var value = field.get(this);

                if (withValue) {
                    if (value != null) {
                        resultMap.put(field.getName(), value);
                    }
                } else {
                    resultMap.put(field.getName(), value);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return resultMap;
    }
}
