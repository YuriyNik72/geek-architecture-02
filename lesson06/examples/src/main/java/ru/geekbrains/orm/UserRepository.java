package ru.geekbrains.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository {

    private final Connection conn;

    private final UserMapper mapper;

    private final UnitOfWork unitOfWork;

    public UserRepository(Connection conn) {
        this.conn = conn;
        this.mapper = new UserMapper(conn);
        this.unitOfWork = new UnitOfWork(mapper);
    }

    public Optional<User> findById(long id) {
        return mapper.findById(id);
    }

    public void beginTransaction() {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void insert(User user) {
        unitOfWork.registerNew(user);
    }

    public void update(User user) {
        unitOfWork.registerUpdate(user);
    }

    public void delete(User user) {
        unitOfWork.registerDelete(user);
    }

    public void commitTransaction() {
        unitOfWork.commit();
        try {
            conn.commit();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void rollbackTransaction() {

    }
}
