package org.santana.repository;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
        String tableName = this.tableName(dataModel.getClass().getName());
        String columns = this.getModelColumns(dataModel);
        String values = this.getModelValues(dataModel);
        String sql = "INSERT INTO pe_" + tableName + " (" + columns + ") VALUES (" + values + ")";

        try {
            Statement statement = this.db.createStatement();
            result = (statement.executeUpdate(sql) != 0);

        } catch (SQLException e) {
        }

        return result;
    }

    /**
     * get model columns in string separed by commas.
     */
    private String getModelColumns(Model dataModel) {
        //Get field list of the class.
        Field[] fields = this.model.getClass().getDeclaredFields();
        String columns = "";

        //Converting the array of fields in a String separed by commas.
        for (Field field : fields) {
            columns += field.getName() + ", ";
        }
        System.out.println(columns);
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
                Object value = field.get(dataModel);

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

    private String tableName(String modelName) {

        return modelName.replaceAll("Model$", "");
    }
}
