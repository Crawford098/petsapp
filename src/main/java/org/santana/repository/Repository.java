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
    public boolean save(Object data) {

        boolean result = false;
        String tableName = this.tableName(this.model.getClass().getName());
        String columns = this.getModelColumns();
        String values = this.getModelValues();
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
    private String getModelColumns() {
        //Get field list of the class.
        Field[] fields = this.model.getClass().getFields();
        String columns = "";

        //Converting the array of fields in a String separed by commas.
        for (Field field : fields) {
            columns += field.getName() + ", ";
        }

        return columns.substring(0, columns.length() - 1);
    }

    /**
     * get model values in string separed by commas.
     */
    private String getModelValues() {

        Field[] fields = this.model.getClass().getFields();
        String values = "";

        try {
            for (Field field : fields) {

                Object value = field.get(this.model);

                values = values + value + ",";
            }
        } catch (IllegalAccessException | IllegalArgumentException e) {
        }

        return values.substring(0, values.length() - 1);
    }

    private String tableName(String modelName) {

        return modelName.replaceAll("Model$", "");
    }
}
