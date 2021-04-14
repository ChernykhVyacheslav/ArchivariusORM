package orm.archivarius.tables.config;

import orm.archivarius.annotations.Column;

import java.lang.reflect.Field;
import java.sql.JDBCType;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ColumnTableInfo extends TableInfo {
    private static Map<Class<?>, JDBCType> javaToSqlType = new HashMap<>();
    static {
        javaToSqlType.put(int.class, JDBCType.INTEGER);
        javaToSqlType.put(Integer.class, JDBCType.INTEGER);
        javaToSqlType.put(long.class, JDBCType.BIGINT);
        javaToSqlType.put(Long.class, JDBCType.BIGINT);
        javaToSqlType.put(String.class, JDBCType.VARCHAR);
    }

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
        };
    }

    private void parse(Field field) {
        var columnAnnotation = field.getAnnotation(Column.class);
        if (columnAnnotation != null) {
            String columnName = columnAnnotation.value();
            if (columnName.isBlank()) {
                columnName = field.getName();
            }
            var sqlType = javaToSqlType.getOrDefault(
                    field.getType(),
                    JDBCType.OTHER); // TODO:
            columns.put(columnName, sqlType);
        }
        // TODO: process single Id annotation as well
    }
}
