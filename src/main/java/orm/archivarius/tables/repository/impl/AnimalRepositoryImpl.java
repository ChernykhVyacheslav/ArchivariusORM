package orm.archivarius.tables.repository.impl;

import orm.archivarius.database.ConnectionDao;
import orm.archivarius.database.MySQL;
import orm.archivarius.logger.ProgramLogger;
import orm.archivarius.tables.repository.CrudRepository;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AnimalRepositoryImpl implements CrudRepository {
    private Connection connection = null;
    //TODO: parse our stream an get suitable/relevant data for the QUERY
    //Uncomment
    //private static final String FIND_BY_ID  = "SELECT * FROM ? WHERE id  = ?;";


    public AnimalRepositoryImpl() throws SQLException {
        ConnectionDao connectionDao =  new ConnectionDao(new MySQL());
        connection = connectionDao.getConnection();
    }

    @Override
    public Optional<Object> find(Object id) throws SQLException {
        Integer result = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ProgramLogger.getProgramLogger().addLogInfo("Repository impl method getOne --> " + id);
        try {
            connection.setAutoCommit(false);
            //TODO: uncomment when it'll be ready
            //ps = connection.prepareStatement();
            //ps.setLong(1,id);
            result = ps.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            ProgramLogger.getProgramLogger().addLogInfo("SQLException was thrown");
        } finally {
            connection.close();
        }
        ProgramLogger.getProgramLogger().addLogInfo("Repository method setUserRoleAdmin update rows --> " + result.toString());
        return Optional.empty();
    }

    @Override
    public boolean save(Object o) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Object o) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Object o) throws SQLException {
        return false;
    }

    @Override
    public List findAll() throws SQLException {
        return null;
    }
}
