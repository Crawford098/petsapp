package org.santana.repository.core;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.santana.annotation.modelAnnotation.PrimaryKey;
import org.santana.config.database.MysqlConnections;
import org.santana.controller.helpers.AnnotationHelpers;
import org.santana.controller.helpers.RepositoryHelpers;
import static org.santana.controller.helpers.StringHelper.trimL;
import org.santana.model.core.Model;

public class Repository implements IRepository{

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

    @Override
    public Map findById(int id) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Field[] modelFields = this.model.getClass().getDeclaredFields();

        String primaryKey = AnnotationHelpers.getAnnotationName(modelFields, PrimaryKey.class);
        String tableName = "pe_" + this.model.tableName();
        String query = "SELECT * FROM " + tableName + " WHERE " + primaryKey + " = " + id;

        try (Statement statement = this.db.createStatement()) {
            ResultSet result = statement.executeQuery(query);
            resultMap = RepositoryHelpers.getResultMap(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @Override
    public List findAll() {
        List<Map> resultList = new ArrayList<>();
        String tableName = "pe_" + this.model.tableName();
        String query = "SELECT * FROM " + tableName;

        try (Statement statement = this.db.createStatement()) {
            ResultSet result = statement.executeQuery(query);
            resultList = RepositoryHelpers.getResultList(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public Map save(Model model) {
        Map<String, Integer> result = new LinkedHashMap<>();
        String tableName = model.tableName();
        String columns = model.getModelColumns();
        Map<String, Object> columnWithValues = model.getAllProperties();

        String preparedValues = "";

        for (String key : columnWithValues.keySet()) {
            preparedValues += " ?,";
        }

        String sql = "INSERT INTO pe_" + tableName + " (" + columns + ") VALUES (" + trimL(preparedValues) + ")";

        try {
            PreparedStatement ps = this.db.prepareStatement(sql);
            int counter = 1;

            for (Object value : columnWithValues.values()) {
                ps.setObject(counter, value);
                counter++;
            }

            result.put("Result", ps.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Map updateById(Model data, int id) {
        int rowsUpdated = 0;
        String tableName = "pe_" + data.tableName();
        String primaryKey = data.primarykeyName();
        Map<String, Object> columnWithValues = data.getPropertiesWithValue();
        String columns = "";

        for (String key : columnWithValues.keySet()) {
            columns += key + " = ?,";
        }

        String sql = "UPDATE " + tableName + " SET " + trimL(columns) + " WHERE " + primaryKey + " = " + id;

        try {
            PreparedStatement ps = this.db.prepareStatement(sql);

            int counter = 1;

            for (Object value : columnWithValues.values()) {
                ps.setObject(counter, value);
                counter++;
            }

            rowsUpdated = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Integer> result = new HashMap<>();
        result.put("result", rowsUpdated);
        return result;
    }

    @Override
    public Map delete(int id) {

        return new HashMap<>();
    }
}
