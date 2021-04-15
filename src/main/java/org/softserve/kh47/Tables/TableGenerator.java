package org.softserve.kh47.Tables;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class TableGenerator {

    private static final String CREATE_TABLE_SQL = "CREATE TABLE %s (%s)";

    PriorityQueue<DdlExpression> ddlPq = new PriorityQueue<>(
            Comparator.comparing(DdlExpression::getPriority)
    );

    TableGenerator(Stream<TableInfo> tableInfoStream) {
        tableInfoStream
                .map(DdlExpression::from)
                .forEachOrdered(ddlPq::add);
    }

    void executeDDL() throws SQLException {
        Connection con = null;
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
            // TODO
            priority = 1;
            String tableName = columnTableInfo.getTableName();
            List<String> columnNames = new ArrayList<>();
            columnTableInfo.getColumns().forEach((key, value) -> columnNames.add(key + " " + value));
            ddl = String.format("CREATE TABLE %s (%s)",
                    tableName,
                    String.join(",", columnNames));
        }
    }

    static class DdlExpressionPk extends DdlExpression {
        public DdlExpressionPk(PkTableInfo pkTableInfo) {
            // TODO
            String tableName = pkTableInfo.getTableName();
            List<String> idColumnNames = pkTableInfo.getIdColumns();

            ddl = String.format("ALTER TABLE %1$s\n" +
                            "ADD CONSTRAINT PK_%1$s PRIMARY KEY (%2$s);",
                    tableName,
                    String.join(",", idColumnNames));
            priority = 2;
        }
    }

    static class DdlExpressionFk extends DdlExpression {
        public DdlExpressionFk(FkTableInfo fkTableInfo) {
            // TODO
            priority = 3;
        }
    }
}

