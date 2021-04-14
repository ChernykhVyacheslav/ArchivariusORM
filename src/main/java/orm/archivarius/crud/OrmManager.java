package orm.archivarius.crud;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class OrmManager {

    private static final String INSERT_USERS_SQL = "INSERT INTO %$1s" +
            "  (%$2s) VALUES " +
            " (%$3s);";
    /**
     * Find by id your Entity
     * @param id
     * @return
     * @throws SQLException
     */
    <T> Optional<T> find(Integer id, Class<T> tClass) throws SQLException;

    /**
     * save entity
     * @param t
     * @return
     * @throws SQLException
     */
    <T> boolean save(T t) throws SQLException;

    /**
     * update entity
     * @param t
     * @return
     * @throws SQLException
     */
    <T> boolean update(T t) throws SQLException;

    /**
     * delete entity
     * @param t
     * @return
     * @throws SQLException
     */
    <T> boolean delete(T t) throws SQLException;

    /**
     * return a List of Entities
     * @return
     * @throws SQLException
     */
    <T> List<T> findAll() throws SQLException;

    private static class MapperRowToObject {
        static Object parse(Map<String, Object>) {

        }
    }

    private static class MapperObjectToRow {
        static Map<String, Object> parse(Object o) {

        }
    }

//    public void insertRecord() throws SQLException {
//        System.out.println(INSERT_USERS_SQL);
//        // Step 1: Establishing a Connection
//        try (Connection connection = DriverManager.getConnection(url, user, password);
//
//             // Step 2:Create a statement using connection object
//             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
//            preparedStatement.setInt(1, 1);
//            preparedStatement.setString(2, "Tony");
//            preparedStatement.setString(3, "tony@gmail.com");
//            preparedStatement.setString(4, "US");
//            preparedStatement.setString(5, "secret");
//
//            System.out.println(preparedStatement);
//            // Step 3: Execute the query or update query
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // Step 4: try-with-resource statement will auto close the connection.
//    }
}
