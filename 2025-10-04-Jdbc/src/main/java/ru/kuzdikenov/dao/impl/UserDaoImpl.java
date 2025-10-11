package ru.kuzdikenov.dao.impl;

import ru.kuzdikenov.dao.UserDao;
import ru.kuzdikenov.entity.User;
import ru.kuzdikenov.exceptions.UserAlreadyExistsInDatabase;
import ru.kuzdikenov.exceptions.UserNotFoundInDatabase;
import ru.kuzdikenov.util.DatabaseConnectionUtil;
import ru.kuzdikenov.util.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public List<User> getAll() {
        String sql = "select * from users";
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null) {
                while (resultSet.next()) {
                    User user = getUserFromResultSet(resultSet);
                    users.add(user);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return users;
    }

    @Override
    public void save(User user) throws UserAlreadyExistsInDatabase {
        String sql = "insert into users (name, lastname, login, password) values (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getLogin());
            String encryptedPassword = PasswordUtil.encrypt(user.getPassword());
            preparedStatement.setString(4, encryptedPassword);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new UserAlreadyExistsInDatabase();
        }
    }

    @Override
    public User getByLogin(String login) throws UserNotFoundInDatabase{
        String sql = "SELECT * from users where login = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            // one answer because login unique
            resultSet.next();
            User user = getUserFromResultSet(resultSet);
            return user;
        } catch (SQLException e) {
            throw new UserNotFoundInDatabase();
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("lastname"),
                resultSet.getString("login"),
                resultSet.getString("password")
        );
        return user;
    }

}
