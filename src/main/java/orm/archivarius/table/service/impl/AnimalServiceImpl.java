package orm.archivarius.table.service.impl;

import orm.archivarius.entities.Animal;
import orm.archivarius.logger.ProgramLogger;
import orm.archivarius.table.repository.CrudRepository;
import orm.archivarius.table.service.AnimalService;

import java.awt.*;
import java.sql.SQLException;
import java.util.Optional;

public class AnimalServiceImpl implements AnimalService {
    CrudRepository repository;
    public AnimalServiceImpl(CrudRepository repository){
        this.repository = repository;
    }

    @Override
    public Optional<Animal> find(Integer id) throws SQLException {
        ProgramLogger.getProgramLogger().addLogInfo("Service method ==> FIND BY ID");
        return repository.find(id);
    }

    @Override
    public boolean save(Animal t) throws SQLException {
        ProgramLogger.getProgramLogger().addLogInfo("Service method ==> SAVE");
        return repository.save(t);
    }

    @Override
    public boolean update(Animal t) throws SQLException {
        ProgramLogger.getProgramLogger().addLogInfo("Service method ==> UPDATE");
        return repository.update(t);
    }

    @Override
    public boolean delete(Animal t) throws SQLException {
        ProgramLogger.getProgramLogger().addLogInfo("Service method ==> DELETE");
        return repository.delete(t);
    }

    @Override
    public List findAll() throws SQLException {
        ProgramLogger.getProgramLogger().addLogInfo("Service method ==> FIND ALL");
        return repository.findAll();
    }
}
