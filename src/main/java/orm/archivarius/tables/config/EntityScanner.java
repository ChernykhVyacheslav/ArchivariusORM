package orm.archivarius.tables.config;

import orm.archivarius.annotations.Entity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

public class EntityScanner {
    public static Stream<? extends Class<?>> getEntityClasses() throws IOException {
        return Files.walk(Path.of(".")).map(i -> i.toString()).
                filter(i -> i.endsWith("class")).
                map(i -> i.replace("\\", ".").
                        replace("..target.classes.", "").
                        replace(".class", "")
                ).
                filter(i -> i.startsWith(EntityScanner.class.getPackage().getName())).map(i -> {
            try {
                return Class.forName(i);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }).
                filter(i -> i.getAnnotation(Entity.class) != null);
    }
}
