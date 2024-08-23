package org.santana.repository;

import java.util.Map;

public interface IRepository {

/**
 * Searches for a record in the database using its primary key.
 *
 * @param id The primary key of the record to search for.
 * @return A `Map<String, Object>` containing the record's data.
 *         If the record does not exist, returns an empty `Map`.
 */
public Map findById(int id);

public Map findAll();

public boolean save();

public int update();

public boolean delete();
}