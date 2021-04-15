package orm.archivarius.tables.config;

import java.io.IOException;
import java.util.stream.Stream;

public class EntityParser {
    public static Stream<TableInfo> parse() throws IOException {
        return EntityScanner.getEntityClasses().flatMap(s -> Stream.of(new ColumnTableInfo(s), new PkTableInfo(s)));
    }
}
