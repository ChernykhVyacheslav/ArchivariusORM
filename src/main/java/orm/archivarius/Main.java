package orm.archivarius;

import orm.archivarius.tables.config.*;
import orm.archivarius.tables.config.EntityParser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        TableGenerator tableGenerator = new TableGenerator(EntityParser.parse());
        tableGenerator.executeDDL();
    }
}
