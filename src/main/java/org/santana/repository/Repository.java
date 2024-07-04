package org.santana.repository;

import java.sql.Connection;

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

    public void findById() {
        
    //todo: probar la base de datos.
    }

    public void findAll() {

    }

}
