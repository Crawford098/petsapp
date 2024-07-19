package org.santana.repository;

public interface IRepository {

void findById();

void findAll();

boolean save();

int update();

boolean delete();
}