package orm.archivarius.table.config;

import orm.archivarius.annotations.Column;
import orm.archivarius.annotations.Id;

import java.lang.reflect.Field;
import java.sql.JDBCType;
import java.util.*;

public class PkTableInfo extends TableInfo {
    private Map<String, JDBCType> idColumns = new HashMap<>();

    public PkTableInfo(Class<?> clazz) {
        super(clazz);
        parse(clazz);
    }

    //should return List<String>
    public Map<String, JDBCType> getIdColumns(){
        return Collections.unmodifiableMap(idColumns);
    }

    private void parse(Class<?> clazz){
        for(var field : clazz.getDeclaredFields()){
            parse(field);
        }
    }

    private void parse(Field field){
        if (field.isAnnotationPresent(Column.class) && field.isAnnotationPresent(Id.class)){
            String columnName = field.getAnnotation(Column.class).value();
            if(columnName.isBlank()){
                columnName = field.getName();
            }
            idColumns.put(columnName, javaToSQLType.getOrDefault(field.getType(), JDBCType.NULL));
        } else if(field.isAnnotationPresent(Id.class) && !field.isAnnotationPresent(Column.class)){
            idColumns.put(getTableInfo()+"_id", javaToSQLType.getOrDefault(field.getType(), JDBCType.NULL));
        }
    }

    @Override
    public Map<String, Object> generateTab() {
        res.put("Primary_Key", getIdColumns());
        return res;
    }
}
