package orm.archivarius;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        EntityParser.getTablesInfo(EntityScanner.getEntityClasses()).forEach(System.out::println);
    }
}
