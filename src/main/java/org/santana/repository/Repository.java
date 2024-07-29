package org.santana.repository;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.santana.annotation.modelAnnotation.TableName;
import org.santana.config.database.MysqlConnections;
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

    public void findById(int $id) {

        // PreparedStatement preparedStatement = this.db.prepareStatement("");
        //todo: probar la base de datos.
    }

    public void findAll() {

    }

    //Todo: Define the datatable prefix.
    //Todo: Add validations (check it)
    public boolean save(Model dataModel) {

        boolean result = false;
        String tableName = this.tableName(dataModel);
        String columns = this.getModelColumns(dataModel);
        String values = this.getModelValues(dataModel);
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
