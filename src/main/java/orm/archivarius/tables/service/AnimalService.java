package orm.archivarius.tables.service;

import orm.archivarius.entities.Animal;

import java.awt.*;
import java.sql.SQLException;
import java.util.Optional;

public interface AnimalService {
    Optional<Animal> find(Integer id) throws SQLException;

    boolean save(Animal t) throws SQLException;

    boolean update(Animal t) throws SQLException;

    boolean delete(Animal t) throws SQLException;

    List findAll() throws SQLException;
}
