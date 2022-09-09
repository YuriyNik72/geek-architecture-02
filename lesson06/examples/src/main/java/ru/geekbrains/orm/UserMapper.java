package ru.geekbrains.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserMapper {

    private final Connection conn;

    private final PreparedStatement selectUser;

    private final PreparedStatement insertUser;

    private final PreparedStatement updateUser;

    private final PreparedStatement deleteUser;

    private final PreparedStatement selectIdByUsername;

    private final Map<Long, User> identityMap = new HashMap<>();

    public UserMapper(Connection conn) {
        this.conn = conn;
        try {
            this.selectUser = conn.prepareStatement("select id, username, password from users where id = ?");
            this.updateUser = conn.prepareStatement("update users set username = ?, password = ? where = ?");
            this.insertUser = conn.prepareStatement("insert into users(username, password) values (?,?)");
            this.deleteUser = conn.prepareStatement("delete from users dhere id = ?");
            this.selectIdByUsername = conn.prepareStatement("select id from users where username = ?");
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Optional<User> findById(long id) {
        User user = identityMap.get(id);
        if (user != null) {
            return Optional.of(user);
        }
        try {
            selectUser.setLong(1, id);
            ResultSet rs = selectUser.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
                identityMap.put(id, user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    public void update(User user) {
        if (!isInIdentityMap(user)){
            throw new RuntimeException("Пользователя" + user + " не существует");
        }
        try {
            updateUser.setString(1, user.getLogin());
            updateUser.setString(2, user.getPassword());
            updateUser.setLong(3, user.getId());
            updateUser.executeUpdate();
        } catch (SQLException e){
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(e);
            }
                throw new IllegalStateException(e);
            }
            identityMap.put(user.getId(), user);

    }

    public void insert(User user) {
        if (isInIdentityMap(user)) {
            throw new RuntimeException("Пользователь с id=" + user.getId() + " уже существует");
        }

        try {
            insertUser.setString(1, user.getLogin());
            insertUser.setString(2, user.getPassword());
            insertUser.executeUpdate();

            selectIdByUsername.setString(1, user.getLogin());
            ResultSet rs = selectIdByUsername.executeQuery();
            if (rs.next()){
                long newId = rs.getLong(1);
                user.setId((int) newId);
            }


        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new IllegalStateException(e);
        }
        identityMap.put(user.getId(), user);
    }

    public void delete(User user) {
        if (!isInIdentityMap(user)) {
            throw new RuntimeException("Пользователя " + user + " не существует!");
        }

        try {
            deleteUser.setLong(1, user.getId());
            deleteUser.executeUpdate();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new IllegalStateException(e);
        }
        identityMap.remove(user.getId(), user);
    }

    private boolean isInIdentityMap(User user) {
        return identityMap.containsKey(user.getId());
    }
}
