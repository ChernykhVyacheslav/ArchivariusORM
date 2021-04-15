package orm.archivarius.tables.config;

import java.io.IOException;
import java.util.stream.Stream;

public class EntityParser {
    public static Stream<TableInfo> parse() throws IOException {
        return EntityScanner.getEntityClasses().flatMap(s -> Stream.of(new ColumnTableInfo(s), new PkTableInfo(s)));
    }

    public static Stream<TableInfo> getTablesInfo(Stream<? extends Class<?>> stream) {

        var streamOfColumn = stream.map(
                ColumnTableInfo::new
        );

        var streamOfId = stream.map(
                PkTableInfo::new
        );

        return Stream.concat(streamOfColumn, streamOfId);
    }
}
