package org.santana.repository;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import org.santana.annotation.modelAnnotation.PrimaryKey;
import org.santana.annotation.modelAnnotation.TableName;
import org.santana.config.database.MysqlConnections;
import org.santana.controller.helpers.AnnotationHelpers;
import org.santana.model.Model;

public class Repository {

    public Connection db;
    public Model model;

    public Repository(Model model) {
        this.model = model;
        try {
            this.db = MysqlConnections.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map findById(int id) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Field[] modelFields = this.model.getClass().getDeclaredFields();

        String primaryKey = AnnotationHelpers.getAnnotationName(modelFields, PrimaryKey.class);
        String tableName = "pe_" + this.tableName(this.model);
        String query = "SELECT * FROM " + tableName + " WHERE " + primaryKey + " = " + id;

        try (Statement statement = this.db.createStatement()) {
            ResultSet result = statement.executeQuery(query);

            ResultSetMetaData metaData = result.getMetaData();
            int columnsCount = metaData.getColumnCount();

            while (result.next()) {
                for (int i = 1; i <= columnsCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = result.getObject(i);

                    System.out.println(columnName + ": " + columnValue);
                    resultMap.put(columnName, columnValue);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    public void findAll() {

    }

    //Todo: Define the datatable prefix.
    //Todo: Add validations (check it)
    //Todo: Validate if model is empty();
    public boolean save() {//Model dataModel

        boolean result = false;
        String tableName = this.tableName(this.model);
        String columns = this.getModelColumns(this.model);
        String values = this.getModelValues(this.model);
        String sql = "INSERT INTO pe_" + tableName + " (" + columns + ") VALUES (" + values + ")";

        try {
            Statement statement = this.db.createStatement();
            result = (statement.executeUpdate(sql) != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Get model columns in string separed by commas.
     *
     * @param Model Model to insert.
     * @return String of columns separed by Commas.
     */
    private String getModelColumns(Model dataModel) {
        //Get field list of the class.
        Field[] fields = this.model.getClass().getDeclaredFields();
        String columns = "";

        for (Field field : fields) {

            if (dataModel.isPrimaryKey(field)) {
                continue;
            }

            columns += field.getName() + ",";
        }

        return columns.substring(0, columns.length() - 1);
    }

    /**
     * get model values in string separed by commas.
     */
    private String getModelValues(Model dataModel) {

        Field[] fields = dataModel.getClass().getDeclaredFields();
        StringBuilder values = new StringBuilder();

        try {
            for (Field field : fields) {

                if (dataModel.isPrimaryKey(field)) {
                    continue;
                }

                field.setAccessible(true);
                Object value = "'" + field.get(dataModel) + "'";

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

    /**
     * Descripción breve del método.
     *
     * @param String Model Name.
     * @param Model Model.
     * @return Return tableName from de Model.
     * @throws Exception Anottation exeption.
     */
    private String tableName(Model model) {

        String modelName = model.getClass().getSimpleName();
        String tableName = modelName.replaceAll("Model$", "");

        try {
            Field field = model.getClass().getDeclaredField(modelName);

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
}
