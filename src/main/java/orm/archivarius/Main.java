package orm.archivarius;

import orm.archivarius.table.config.*;
import orm.archivarius.tables.config.EntityScanner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        TableGenerator tableGenerator = new TableGenerator(getResult());
        tableGenerator.executeDdl();
    }

    public static Stream<TableInfo> getResult() throws IOException {
        return EntityScanner.getEntityClasses().flatMap(s -> {
            try {
                return Stream.of(new ColumnTableInfo(s), new PkTableInfo(s));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
