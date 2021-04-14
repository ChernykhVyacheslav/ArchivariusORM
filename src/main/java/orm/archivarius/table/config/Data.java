package orm.archivarius.table.config;

import orm.archivarius.EntityScanner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Data {
    public static List<TableInfo> getResult() throws IOException {
        return EntityScanner.getEntityClasses().flatMap(s -> {
            try {
                return Stream.of(new ColumnTableInfo(s), new PkTableInfo(s));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }
}
