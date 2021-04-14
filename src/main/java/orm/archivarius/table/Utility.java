package orm.archivarius.table;

class Utility {
    public static String normalizeName(String name) {
        return name
                .replaceAll("(?<=[a-z])(?=[A-Z])|(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)",
                        "_")
                .toUpperCase();
    }
}
