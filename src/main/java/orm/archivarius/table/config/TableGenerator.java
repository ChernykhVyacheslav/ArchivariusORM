package orm.archivarius.table.config;

import orm.archivarius.table.repository.impl.AnimalRepositoryImpl;
import orm.archivarius.table.service.AnimalService;
import orm.archivarius.table.service.impl.AnimalServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class TableGenerator {
    private PriorityQueue<DdlExpression> ddlPriorityQueue = new PriorityQueue<DdlExpression>(
            Comparator.comparing(DdlExpression::getPriority)
    );

    TableGenerator(Stream<TableInfo> stream)  {
        stream.map(DdlExpression::from).forEachOrdered(ddlPriorityQueue::add);
    }

    void executeDdl() throws SQLException {
        Connection con = null;
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
        AnimalService animalService = new AnimalServiceImpl(new AnimalRepositoryImpl());

        public DdlExpressionTable(ColumnTableInfo tableInfo) throws SQLException {
            priority = 1;
            ddl = "create table ///";
        }
    }


    static class DdlExpressionPk extends DdlExpression{
        public DdlExpressionPk(PkTableInfo tableInfo){
            priority = 2;
            ddl = "alter table add primary key";
        }
    }

    static class DdlExpressionFk extends DdlExpression{
        public DdlExpressionFk(FkTableInfo tableInfo){
            priority = 3;
            ddl = "alter table add foreign key....//"; // String.format()
        }
    }
}
