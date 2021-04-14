package org.softserve.kh47;

import org.softserve.kh47.entity_scanner.EntityScanner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Class<?>> l = EntityScanner.getEntityClasses().collect(Collectors.toList());

    }
}
