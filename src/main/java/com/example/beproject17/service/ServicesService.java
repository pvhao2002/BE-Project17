package com.example.beproject17.service;

import com.example.beproject17.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ServicesService {
    final static String GET_ALL = "SELECT * FROM services WHERE is_delete = 0;";
    final static String INSERT = "INSERT INTO services(service_name, description, image) VALUES (?, ?, ?);";
    final static String UPDATE = """
            UPDATE services
            SET service_name = ?,
                description = ?,
                image = ?
            WHERE id =?;
            """;
    final static String DELETE = "UPDATE services SET is_delete = 1 WHERE id = ?;";
    final static String GET_BY_ID = "SELECT * FROM services WHERE id = ? and is_delete = 0;";

    public Boolean add(Service item) {
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                ps = cnt.prepareStatement(INSERT);
                ps.setString(1, item.getServiceName());
                ps.setString(2, item.getDescription());
                ps.setString(3, item.getImage());
                ps.executeUpdate();
                return true;
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
        return false;
    }

    public Boolean update(Service item) {
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                ps = cnt.prepareStatement(UPDATE);
                ps.setString(1, item.getServiceName());
                ps.setString(2, item.getDescription());
                ps.setString(3, item.getImage());
                ps.setInt(4, item.getId());
                ps.executeUpdate();
                return true;
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
        return false;
    }

    public Boolean delete(Integer id) {
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                ps = cnt.prepareStatement(DELETE);
                ps.setInt(1, id);
                ps.executeUpdate();
                return true;
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
        return false;
    }

    public Service getById(Integer id) {
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                ps = cnt.prepareStatement(GET_BY_ID);
                ps.setInt(1, id);
                var rs = ps.executeQuery();
                if (rs.next()) {
                    var item = new Service();
                    item.setId(rs.getInt("id"));
                    item.setServiceName(rs.getString("service_name"));
                    item.setDescription(rs.getString("description"));
                    item.setImage(rs.getString("image"));
                    item.setIsDeleted(rs.getBoolean("is_delete"));
                    return item;
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
        return null;
    }

    public List<Service> getAll() {
        List<Service> list = new ArrayList<>();
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                ps = cnt.prepareStatement(GET_ALL);
                var rs = ps.executeQuery();
                while (rs.next()) {
                    var item = new Service();
                    item.setId(rs.getInt("id"));
                    item.setServiceName(rs.getString("service_name"));
                    item.setDescription(rs.getString("description"));
                    item.setImage(rs.getString("image"));
                    item.setIsDeleted(rs.getBoolean("is_delete"));
                    list.add(item);
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
        return list;
    }
}
