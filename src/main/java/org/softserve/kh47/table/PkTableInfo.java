package org.softserve.kh47.table;

import org.softserve.kh47.annotations.Column;
import org.softserve.kh47.annotations.Id;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PkTableInfo extends TableInfo {
    private List<String> idColumns = new ArrayList<>();

    public PkTableInfo(Class<?> clazz) {
        super(clazz);
        parse(clazz);
    }

    public List<String> getIdColumns() {
        return Collections.unmodifiableList(idColumns);
    }

    private void parse(Class<?> clazz) {
        for (var field : clazz.getDeclaredFields()) {
            parse(field);
        }
    }

    private void parse(Field field) {
        if(field.isAnnotationPresent(Id.class) && field.isAnnotationPresent(Column.class)){
            String columnName = field.getAnnotation(Column.class).value();
            if (columnName.isBlank()) {
                columnName = field.getName();
            }
            idColumns.add(columnName);
        }
    }

}
