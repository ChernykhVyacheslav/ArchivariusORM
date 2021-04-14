package orm.archivarius.table.repository;

import java.awt.*;
import java.sql.SQLException;
import java.util.Optional;

public interface CrudRepository<T, Integer> {
    /**
     * Find by id your Entity
     * @param id
     * @return
     * @throws SQLException
     */
    Optional<T> find(Integer id) throws SQLException;

    /**
     * save entity
     * @param t
     * @return
     * @throws SQLException
     */
    boolean save(T t) throws SQLException;

    /**
     * update entity
     * @param t
     * @return
     * @throws SQLException
     */
    boolean update(T t) throws SQLException;

    /**
     * delete entity
     * @param t
     * @return
     * @throws SQLException
     */
    boolean delete(T t) throws SQLException;

    /**
     * return a List of Entities
     * @return
     * @throws SQLException
     */
    List findAll() throws SQLException;
}
