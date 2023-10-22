package com.example.beproject17.service;

import com.example.beproject17.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final String SUCCESS = "Success";
    private final String ERROR = "Error";

    public Object login(String username, String password) {
        var user = getByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public Object register(User user) {
        // to do
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = """
                    INSERT INTO users(username, password, full_name, email, phone, address, role_id) VALUES (?,?,?,?,?,?,?);
                """;
                ps = cnt.prepareStatement(sql);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFullName());
                ps.setString(4, user.getEmail());
                ps.setString(5, user.getPhone());
                ps.setString(6, user.getAddress());
                ps.setInt(7, user.getRoleId());
                ps.executeUpdate();
            }
            return SUCCESS;
        } catch (Exception e) {
            return String.format("%s: %s", ERROR, e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Object update(User user) {
        // to do
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = """
                        UPDATE users
                        SET password = ?,
                            full_name = ?,
                            email = ?,
                            phone = ?,
                            address = ?,
                            role_id = ?
                        WHERE id =?;
                        """;
                ps = cnt.prepareStatement(sql);
                ps.setString(1, user.getPassword());
                ps.setString(2, user.getFullName());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPhone());
                ps.setString(5, user.getAddress());
                ps.setInt(6, user.getRoleId());
                ps.setInt(7, user.getId());
                ps.executeUpdate();
            }
            return SUCCESS;
        } catch (Exception e) {
            return String.format("%s: %s", ERROR, e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Object delete(int id) {
        // to do
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "UPDATE users SET is_delete = 1 WHERE id = ?";
                ps = cnt.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
            }
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("%s: %s", ERROR, e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "SELECT * FROM users WHERE is_delete = 0;";
                ps = cnt.prepareStatement(sql);
                var rs = ps.executeQuery();
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFullName(rs.getString("full_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setRoleId(rs.getInt("role_id"));
                    user.setIsDeleted(rs.getBoolean("is_delete"));
                    users.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public User getById(Integer id) {
        User user = null;
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "SELECT * FROM users WHERE id = ? AND is_delete = 0;";
                ps = cnt.prepareStatement(sql);
                ps.setInt(1, id);
                user = getUser(ps);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public User getByUsername(String username) {
        User user = null;
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "SELECT * FROM users WHERE username = ? AND is_delete = 0;";
                ps = cnt.prepareStatement(sql);
                ps.setString(1, username);
                user = getUser(ps);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    private User getUser(PreparedStatement ps) {
        User user = null;
        try {
            var rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setRoleId(rs.getInt("role_id"));
                user.setIsDeleted(rs.getBoolean("is_delete"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
