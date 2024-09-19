package org.santana.repository.core;

import java.util.List;
import java.util.Map;

import org.santana.model.core.Model;

public interface IRepository {

    /**
     * Searches for a record in the database using its primary key.
     *
     * @param id The primary key of the record to search for.
     * @return A `Map<String, Object>` containing the record's data. If the
     * record does not exist, returns an empty `Map`.
     */
    public Map findById(int id);

    public List findAll();

    public Map save(Model model);

    public Map updateById(Model model, int id);

    public Map delete(int Id);
}
