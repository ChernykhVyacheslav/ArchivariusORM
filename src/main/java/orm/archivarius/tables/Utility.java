package orm.archivarius.tables;

class Utility {
    public static String normalizeName(String name) {
        return name
                .replaceAll("(?<=[a-z])(?=[A-Z])|(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)|((?=[A-Z][a-z]))",
                        "_")
                .toUpperCase();
    }
}
