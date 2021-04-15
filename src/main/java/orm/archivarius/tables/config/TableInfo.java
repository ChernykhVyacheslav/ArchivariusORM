package orm.archivarius.tables.config;

import orm.archivarius.annotations.Entity;

import java.sql.JDBCType;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class TableInfo {
    protected Map<String, Object> res = new LinkedHashMap<>();
    protected static Map<Class<?>, JDBCType> javaToSQLType = new HashMap<>();
    protected String tableName;

    static{
        javaToSQLType.put(int.class, JDBCType.INTEGER);
        javaToSQLType.put(long.class, JDBCType.BIGINT);
        javaToSQLType.put(float.class, JDBCType.FLOAT);
        javaToSQLType.put(Integer.class, JDBCType.INTEGER);
        javaToSQLType.put(Long.class, JDBCType.BIGINT);
        javaToSQLType.put(String.class, JDBCType.VARCHAR);
    }

    public TableInfo(Class<?> clazz) {
        var entityAnnotation = clazz.getAnnotation(Entity.class);
        tableName = entityAnnotation.value();
        if (tableName.isBlank()) {
            tableName = clazz.getSimpleName();
        }
    }

    public String getTableName() {
        return tableName;
    }
    public Map<String, Object> generateTab(){ return res; }
}
