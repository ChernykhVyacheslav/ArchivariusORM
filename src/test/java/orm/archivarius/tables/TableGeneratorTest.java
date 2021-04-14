package orm.archivarius.tables;

import org.junit.jupiter.api.Test;
import orm.archivarius.EntityScanner;
import orm.archivarius.database.ConnectionDao;
import orm.archivarius.database.PostgresSQL;
import orm.archivarius.tables.config.ColumnTableInfo;
import orm.archivarius.tables.config.TableGenerator;
import orm.archivarius.tables.config.TableInfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TableGeneratorTest {

    @Test
    void generateTable() throws SQLException, IOException {
        Stream<TableInfo> tableInfoStream = EntityScanner.getEntityClasses()
                .map(c -> (TableInfo) new ColumnTableInfo(c))
                .collect(Collectors.toList()).stream();
        TableGenerator tableGenerator = new TableGenerator(tableInfoStream);
        tableGenerator.executeDDL();
    }

}