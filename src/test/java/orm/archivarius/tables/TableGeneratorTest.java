package orm.archivarius.tables;

import org.junit.jupiter.api.Test;
import orm.archivarius.tables.config.*;

import java.io.IOException;
import java.sql.SQLException;

class TableGeneratorTest {

    @Test
    void generateTable() throws SQLException, IOException {
        TableGenerator tableGenerator = new TableGenerator(EntityParser.parse());
        tableGenerator.executeDDL();
    }

}