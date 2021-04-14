package orm.archivarius.table.config;

import orm.archivarius.database.ConnectionDao;
import orm.archivarius.database.MySQL;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class TableGenerator {
    Connection connection;
    ConnectionDao connectionDao;

    private PriorityQueue<DdlExpression> ddlPriorityQueue = new PriorityQueue<DdlExpression>(
            Comparator.comparing(DdlExpression::getPriority)
    );

    public TableGenerator(Stream<TableInfo> stream)  {
        stream.map(DdlExpression::from).forEachOrdered(ddlPriorityQueue::add);
        connection = null;
        connectionDao = new ConnectionDao(new MySQL());
    }

    public void executeDdl() throws SQLException {
        Connection con = connectionDao.getConnection();
        var st = con.createStatement();
        while(!ddlPriorityQueue.isEmpty()){
            var ddExpression = ddlPriorityQueue.poll();
            st.execute(ddExpression.ddl);
        }
    }

    static abstract class DdlExpression {
        String ddl;
        int priority;

        public int getPriority() {
            return priority;
        }

        static DdlExpression from(TableInfo tableInfo){
            if(tableInfo instanceof ColumnTableInfo){
                try {
                    return new DdlExpressionTable((ColumnTableInfo) tableInfo);
                } catch (SQLException throwables) {
                    throwables.getCause();
                }
            }
            if(tableInfo instanceof PkTableInfo){
                return new DdlExpressionPk((PkTableInfo) tableInfo);
            }
            if(tableInfo instanceof FkTableInfo){
                return new DdlExpressionFk((FkTableInfo) tableInfo);
            }
            throw new IllegalArgumentException("Unknown TableInfo class");
        }
    }

    static class DdlExpressionTable extends DdlExpression{
        //AnimalService animalService = new AnimalServiceImpl(new AnimalRepositoryImpl());

        public DdlExpressionTable(ColumnTableInfo tableInfo) throws SQLException {
            priority = 1;
            ddl = String.format("CREATE TABLE %s (", tableInfo.getTableInfo());
            for (Map.Entry entry : tableInfo.getColumns().entrySet()) {
                    ddl += entry.getKey() + " ";
                if(entry.getValue() == JDBCType.VARCHAR){
                    ddl += entry.getValue() + "(200),";
                } else {
                    ddl += entry.getValue() +",";
                }
            }
            ddl = ddl.substring(0, ddl.length() -1);
            ddl += ");";
        }
    }


    static class DdlExpressionPk extends DdlExpression{
        public DdlExpressionPk(PkTableInfo tableInfo){
            priority = 2;
            tableInfo.getIdColumns().forEach((key, value) -> ddl = "ALTER TABLE " + tableInfo.getTableInfo() + " ADD PRIMARY KEY("+key+");");
        }
    }

    static class DdlExpressionFk extends DdlExpression{
        public DdlExpressionFk(FkTableInfo tableInfo){
            priority = 3;
            ddl = "alter table add foreign key....//"; // String.format()
        }
    }
}
