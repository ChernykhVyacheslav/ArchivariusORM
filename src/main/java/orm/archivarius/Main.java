package orm.archivarius;

import orm.archivarius.table.config.Data;
import orm.archivarius.table.config.TableInfo;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        for (TableInfo t : Data.getResult()) {
            System.out.print(t.generateTab());
        }
    }
}
