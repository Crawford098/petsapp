package org.santana.repository;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

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
    //Todo: Add dinamic values en sql query
    //Todo: Add dinamic values in preparedStament
    //Todo: Add validtions (check it)
    public boolean save(Object data) {

        boolean result = false;
        String columns = this.getModelColumns();
        String values = this.getModelValues(data);
        String sql = "INSERT INTO pe_" + this.model.getClass() + " (" + columns + ") VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = this.db.prepareStatement(sql);

            preparedStatement.setString(1, "jonathan");
            preparedStatement.setString(2, "prueba@gmail.com");
            preparedStatement.setString(3, "123456");
            preparedStatement.setString(4, LocalDate.now().toString());

            result = (preparedStatement.executeUpdate() != 0);

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return result;
    }

    private String getModelColumns() {
        //Get field list of the class.
        Field[] fields = this.model.getClass().getDeclaredFields();

        String columns = "";

        //Converting the array of fields in a String separed by commas.
        for (Field field : fields) {
            columns += field.getName() + ", ";
        }

        return columns.substring(0, columns.length() - 1);
    }

    private String getModelValues(Object data) {
        Field[] fields = this.model.getClass().getDeclaredFields();

        
        return "";
    }
}
