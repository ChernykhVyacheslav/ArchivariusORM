package orm.archivarius.table.config;

import orm.archivarius.annotations.Column;

import java.lang.reflect.Field;
import java.sql.JDBCType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ColumnTableInfo extends TableInfo {
    private Map<String, JDBCType> columns = new LinkedHashMap<>();

    public ColumnTableInfo(Class<?> c) throws ClassNotFoundException {
        super(c);
        parse(c);
    }

    public Map<String, JDBCType> getColumns(){
        return Collections.unmodifiableMap(columns);
    }

    private void parse(Class<?> c) throws ClassNotFoundException {
        for(var field: c.getDeclaredFields()){
            parse(field);
        }
    }

    private void parse(Field f){
        var columnAnnotation = f.getAnnotation(Column.class);
        if(columnAnnotation != null) {
            String columnName = columnAnnotation.value();
            if(columnName.isBlank()){
                columnName = f.getName();
            }
            var sqlType = javaToSQLType.getOrDefault(f.getType(), JDBCType.OTHER);
            columns.put(columnName, sqlType);
        }
    }

//    @Override
//    public Map<String, JDBCType> generateTab() {
////        res.put("Columns", getColumns());
//        return getColumns();
//    }
}
