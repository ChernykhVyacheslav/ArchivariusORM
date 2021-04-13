package org.softserve.kh47.Tables;

import java.util.List;

class PkTableInfo extends TableInfo {

    public PkTableInfo(Class<?> clazz) {
        super(clazz);
    }

    List<String> getIdColumns() {
        return List.of("");
        // TODO
    }
}
