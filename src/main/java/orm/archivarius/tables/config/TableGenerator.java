package orm.archivarius.tables.config;

import orm.archivarius.database.ConnectionDao;
import orm.archivarius.database.MySQL;
import orm.archivarius.database.PostgresSQL;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Stream;

public class TableGenerator {
    ConnectionDao connectionDao;

    //id SERIAL PRIMARY KEY,
    private static final String CREATE_TABLE_SQL = "CREATE TABLE %s (%s)";
    private static final String CREATE_PK_SQL = "ALTER TABLE %1$s\n" +
            "ADD CONSTRAINT PK_%1$s PRIMARY KEY (%2$s);";

    PriorityQueue<DdlExpression> ddlPq = new PriorityQueue<>(
            Comparator.comparing(DdlExpression::getPriority)
    );

    public TableGenerator(Stream<TableInfo> tableInfoStream) {
        connectionDao = new ConnectionDao(new PostgresSQL());
        tableInfoStream
                .map(DdlExpression::from)
                .forEachOrdered(ddlPq::add);
    }

    public void executeDDL() throws SQLException {
        Connection con = connectionDao.getConnection();
        var st = con.createStatement();
        while (!ddlPq.isEmpty()) {
            st.execute(ddlPq.poll().ddl);
        }
    }

    static class DdlExpression {
        String ddl;
        int priority;

        public int getPriority() {
            return priority;
        }

        public static DdlExpression from(TableInfo tableInfo) {
            if (tableInfo instanceof ColumnTableInfo) {
                return new DdlExpressionTable((ColumnTableInfo) tableInfo);
            }

            if (tableInfo instanceof PkTableInfo) {
                return new DdlExpressionPk((PkTableInfo) tableInfo);
            }

            if (tableInfo instanceof FkTableInfo) {
                return new DdlExpressionFk((FkTableInfo) tableInfo);
            }

            throw new IllegalArgumentException("Unknown TableInfo subtype class");
        }
    }

    static class DdlExpressionTable extends DdlExpression {
        public DdlExpressionTable(ColumnTableInfo columnTableInfo) {
            priority = 1;
            String tableName = columnTableInfo.getTableName();
            List<String> columnNames = new ArrayList<>();
            columnTableInfo.getColumns().forEach((key, value) ->
                    columnNames.add(key + " " + value + " NOT NULL"));
            ddl = String.format(CREATE_TABLE_SQL,
                    tableName,
                    String.join(",", columnNames));
        }
    }

    static class DdlExpressionPk extends DdlExpression {
        public DdlExpressionPk(PkTableInfo pkTableInfo) {
            priority = 2;
            pkTableInfo.getIdColumns().forEach((key, value) ->
                    ddl = String.format(CREATE_PK_SQL, pkTableInfo.getTableName(), key));
        }
    }

    static class DdlExpressionFk extends DdlExpression {
        public DdlExpressionFk(FkTableInfo fkTableInfo) {
            priority = 3;
            ddl = "alter table add foreign key....//"; // String.format()
        }
    }
}

