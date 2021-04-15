package orm.archivarius;

import orm.archivarius.tables.config.ColumnTableInfo;
import orm.archivarius.tables.config.PkTableInfo;
import orm.archivarius.tables.config.TableInfo;

import java.util.stream.Stream;

public class EntityParser {
    public static Stream<TableInfo> getTablesInfo(Stream<? extends Class<?>> stream){

//TODO: crete a copy of stream
        var streamOfColumn = stream.map(

                c -> {
                    return new ColumnTableInfo(c);
                }
        );

        var streamOfId = stream.map(
                c -> {return new PkTableInfo(c);}
        );

        return Stream.concat(streamOfColumn, streamOfId);
    }
}
