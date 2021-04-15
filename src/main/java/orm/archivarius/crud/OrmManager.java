package orm.archivarius.crud;

import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrmManager {

    private static final String INSERT_USERS_SQL = "INSERT INTO %$1s" +
            "  (%$2s) VALUES " +
            " (%$3s);";
    private final static String PATTERN_READ_ALL = "SELECT * FROM ?";
    private Connection connection;

    public OrmManager(Connection connection){
        this.connection = connection;
    }

    /**
     * Find by id your Entity
     * @param id
     * @return
     * @throws SQLException
     */
    <T> Optional<T> find(Integer id, Class<T> tClass) throws SQLException, ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Method is empty");
    }

    /**
     * save entity
     * @param t
     * @return
     * @throws SQLException
     */
    <T> boolean save(T t) throws SQLException, ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Method is empty");
    }

    /**
     * update entity
     * @param t
     * @return
     * @throws SQLException
     */
    <T> boolean update(T t) throws SQLException, ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Method is empty");
    }

    /**
     * delete entity
     * @param t
     * @return
     * @throws SQLException
     */
    <T> boolean delete(T t) throws SQLException, ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Method is empty");
    }

    /**
     * return a List of Entities
     * @return
     * @throws SQLException
     */
    public <T> List<T> findAll(T entity) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        java.util.List<T> list = new ArrayList<>();
        String query = PATTERN_READ_ALL;
        try(Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()){
                Constructor constructor = Arrays.stream(entity.getClass().getConstructors()).filter(constructor1 -> constructor1.getParameterCount()==0).findFirst().get();
                T instance = (T) constructor.newInstance();
                var fields = instance.getClass().getDeclaredFields();
                for(int i = 0; i<fields.length; i++){
                    //biMap.get(
                    fields[i].setAccessible(true);
                    fields[i].set(instance, resultSet.getObject(fields[i].getName(),fields[i].getType()));
                }
                list.add(instance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static class MapperRowToObject {
        static Object parse(Map<String, Object> map) throws ExecutionControl.NotImplementedException {
            throw new ExecutionControl.NotImplementedException("");
        }
    }

    private static class MapperObjectToRow {
        static Map<String, Object> parse(Object o) throws ExecutionControl.NotImplementedException {
            throw new ExecutionControl.NotImplementedException("");
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
