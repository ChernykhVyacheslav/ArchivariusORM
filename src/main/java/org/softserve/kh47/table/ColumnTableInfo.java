package org.softserve.kh47.table;

import org.softserve.kh47.annotations.Column;

import java.lang.reflect.Field;
import java.sql.JDBCType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ColumnTableInfo extends TableInfo {
    private Map<String, JDBCType> columns = new LinkedHashMap<>();

    public ColumnTableInfo(Class<?> clazz) {
        super(clazz);
        parse(clazz);
    }

    public Map<String, JDBCType> getColumns() {
        return Collections.unmodifiableMap(columns);
    }

    private void parse(Class<?> clazz) {
        for (var field : clazz.getDeclaredFields()) {
            parse(field);
        }
    }

    private void parse(Field field) {
        var columnAnnotation = field.getAnnotation(Column.class);
        if (columnAnnotation != null) {
            String columnName = columnAnnotation.value();
            if (columnName.isBlank()) {
                columnName = field.getName();
            }

            var sqlType = javaToSqlType.getOrDefault(field.getType(), JDBCType.NULL);
            columns.put(columnName, sqlType);
        }
    }

}
