package orm.archivarius.tables.config;

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
