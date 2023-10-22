package com.example.beproject17.service;

import com.example.beproject17.model.Appointment;
import com.example.beproject17.model.Service;
import com.example.beproject17.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    final static String INSERT = """
            INSERT INTO appointment(user_id, 
            service_id, 
            appointment_date, 
            description, 
            full_name, 
            email, 
            phone) 
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;
    final static String UPDATE = """
            UPDATE appointment
            SET status = ?
            WHERE id = ?;
            """;
    final static String GET_ALL = """
            SELECT a.*, u.username, s.service_name
            FROM appointment a
            INNER JOIN users u ON u.id = a.user_id AND u.is_delete = 0
            INNER JOIN services s ON s.id = a.service_id AND s.is_delete = 0;
            """;

    final static String GET_ALL_MY_APPOINTMENT = """
            SELECT a.*, u.username, s.service_name
            FROM appointment a
            INNER JOIN users u ON u.id = a.user_id AND u.is_delete = 0
            INNER JOIN services s ON s.id = a.service_id AND s.is_delete = 0
            WHERE a.user_id = ?;
            """;

    public List<Appointment> getAllMyAppointment(Integer userId) {
        List<Appointment> list = new ArrayList<>();
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                ps = cnt.prepareStatement(GET_ALL_MY_APPOINTMENT);
                ps.setInt(1, userId);
                var rs = ps.executeQuery();
                while (rs.next()) {
                    var item = new Appointment();
                    item.setId(rs.getInt("id"));
                    item.setUserId(rs.getInt("user_id"));
                    item.setEmail(rs.getString("email"));
                    item.setFullName(rs.getString("full_name"));
                    item.setPhone(rs.getString("phone"));
                    item.setServiceId(rs.getInt("service_id"));
                    item.setAppointmentDate(rs.getDate("appointment_date"));
                    item.setStatus(rs.getString("status"));
                    item.setDescription(rs.getString("description"));
                    var user = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    item.setUser(user);
                    var service = new Service();
                    service.setId(rs.getInt("service_id"));
                    service.setServiceName(rs.getString("service_name"));
                    item.setService(service);
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

    public Boolean update(String status, Integer id) {
        try (var cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                var ps = cnt.prepareStatement(UPDATE);
                ps.setString(1, status);
                ps.setInt(2, id);
                ps.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean add(Appointment item) {
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                var ps = cnt.prepareStatement(INSERT);
                ps.setInt(1, item.getUserId());
                ps.setInt(2, item.getServiceId());
                ps.setDate(3, item.getAppointmentDate());
                ps.setString(4, item.getDescription());
                ps.setString(5, item.getFullName());
                ps.setString(6, item.getEmail());
                ps.setString(7, item.getPhone());
                ps.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Appointment> getAll() {
        List<Appointment> list = new ArrayList<>();
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                ps = cnt.prepareStatement(GET_ALL);
                var rs = ps.executeQuery();
                while (rs.next()) {
                    var item = new Appointment();
                    item.setId(rs.getInt("id"));
                    item.setUserId(rs.getInt("user_id"));
                    item.setEmail(rs.getString("email"));
                    item.setFullName(rs.getString("full_name"));
                    item.setPhone(rs.getString("phone"));
                    item.setServiceId(rs.getInt("service_id"));
                    item.setAppointmentDate(rs.getDate("appointment_date"));
                    item.setStatus(rs.getString("status"));
                    item.setDescription(rs.getString("description"));
                    var user = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    item.setUser(user);
                    var service = new Service();
                    service.setId(rs.getInt("service_id"));
                    service.setServiceName(rs.getString("service_name"));
                    item.setService(service);
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
