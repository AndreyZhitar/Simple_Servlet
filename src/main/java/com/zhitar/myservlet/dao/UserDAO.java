package com.zhitar.myservlet.dao;

import com.zhitar.myservlet.model.User;
import com.zhitar.myservlet.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO() {
        this.connection = DbUtil.getConnection();
    }

    public void addUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement("insert into usersdb values (DEFAULT , ?, ?, ?, ?)")){
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, new Date(user.getDob().getTime()));
            statement.setString(4, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        try (PreparedStatement statement = connection.prepareStatement("delete from usersdb where id = ?")){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement("update usersdb set firstname = ?, lastname = ?, dob = ?, email = ? where id = ?")){
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, new Date(user.getDob().getTime()));
            statement.setString(4, user.getEmail());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery("select * FROM usersdb");
            while (set.next()) {
                User user = new User();
                user.setId(set.getInt(1));
                user.setFirstName(set.getString(2));
                user.setLastName(set.getString(3));
                user.setDob(set.getDate(4));
                user.setEmail(set.getString(5));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(int id) {
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement("select * from usersdb where id = ?")){
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                user.setId(set.getInt(1));
                user.setFirstName(set.getString(2));
                user.setLastName(set.getString(3));
                user.setDob(set.getDate(4));
                user.setEmail(set.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
