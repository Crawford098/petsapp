package org.santana.controller.helpers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RepositoryHelpers {

    public static Map getResultMap(ResultSet result)
    {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        try {
            ResultSetMetaData metaData = result.getMetaData();
            int columnsCount = metaData.getColumnCount();

            while (result.next()) {
                for (int i = 1; i <= columnsCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = result.getObject(i);

                    resultMap.put(columnName, columnValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    public static List getResultList(ResultSet result)
    {
        List<Map> resultList = new ArrayList<>();
        Map<String, Object> resultMap = new LinkedHashMap<>();

        try {
            ResultSetMetaData metaData = result.getMetaData();
            int columnsCount = metaData.getColumnCount();

            while (result.next()) {
                for (int i = 1; i <= columnsCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = result.getObject(i);

                    resultMap.put(columnName, columnValue);
                }
                resultList.add(resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }
}