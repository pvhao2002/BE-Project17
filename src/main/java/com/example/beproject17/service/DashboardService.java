package com.example.beproject17.service;

import com.example.beproject17.model.Dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DashboardService {
    static final String GET_DASHBOARD = """
            SELECT
                (SELECT COUNT(*) FROM users WHERE is_delete = 0) AS total_user,
                (SELECT COUNT(*) FROM appointment) AS total_appointment,
                (SELECT COUNT(*) FROM services WHERE is_delete = 0) AS total_service,
                (SELECT COUNT(*) FROM medical_record) AS total_medical_record;
            """;

    public Dashboard getDashboard() {
        Dashboard dashboard = null;
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                ps = cnt.prepareStatement(GET_DASHBOARD);
                var rs = ps.executeQuery();
                if (rs.next()) {
                    dashboard = new Dashboard();
                    dashboard.setTotalUser(rs.getInt("total_user"));
                    dashboard.setTotalAppointment(rs.getInt("total_appointment"));
                    dashboard.setTotalService(rs.getInt("total_service"));
                    dashboard.setTotalMedicalRecord(rs.getInt("total_medical_record"));
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
        return dashboard;
    }
}
