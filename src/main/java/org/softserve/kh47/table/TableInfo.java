package org.softserve.kh47.table;

import org.softserve.kh47.annotations.Entity;
import org.softserve.kh47.annotations.Table;

import java.sql.JDBCType;
import java.util.HashMap;
import java.util.Map;

abstract class TableInfo {
    protected static Map<Class<?>, JDBCType> javaToSqlType = new HashMap<>();
    protected String tableName;

    static {
        javaToSqlType.put(int.class, JDBCType.INTEGER);
        javaToSqlType.put(Integer.class, JDBCType.INTEGER);
        javaToSqlType.put(long.class, JDBCType.BIGINT);
        javaToSqlType.put(Long.class, JDBCType.BIGINT);
        javaToSqlType.put(String.class, JDBCType.VARCHAR);
    }

    public TableInfo(Class<?> clazz) {
        if(clazz.isAnnotationPresent(Table.class) && clazz.isAnnotationPresent(Entity.class)){
            tableName = clazz.getAnnotation(Table.class).value();
            if (tableName.isBlank()) {
                tableName = clazz.getSimpleName();
            }
        }
    }

    public String getTableName() {
        return tableName;
    }
}
