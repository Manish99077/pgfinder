package dao;

import Model.Bookings;
import DataBaseConnection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingsDAO {
    Connection conn = DBConnection.getConnection();

    public boolean addBooking(Bookings booking) {
        String sql = "INSERT INTO bookings (pg_id, tenant_id, status) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, booking.getPgId());
            ps.setInt(2, booking.getTenantId());
            ps.setString(3, booking.getStatus());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Bookings> getBookingsByTenantId(int tenantId) {
        List<Bookings> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE tenant_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tenantId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Bookings(
                        rs.getInt("id"),
                        rs.getInt("pg_id"),
                        rs.getInt("tenant_id"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Bookings> getBookingsForOwner(int ownerId) {
        List<Bookings> list = new ArrayList<>();
        String sql = "SELECT b.* FROM bookings b JOIN pg_listings p ON b.pg_id = p.id WHERE p.owner_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ownerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Bookings(
                        rs.getInt("id"),
                        rs.getInt("pg_id"),
                        rs.getInt("tenant_id"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Bookings> getAllBookings() {
        List<Bookings> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Bookings(
                        rs.getInt("id"),
                        rs.getInt("pg_id"),
                        rs.getInt("tenant_id"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Bookings getBookingById(int id) {
        String sql = "SELECT * FROM bookings WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Bookings(
                        rs.getInt("id"),
                        rs.getInt("pg_id"),
                        rs.getInt("tenant_id"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateBooking(Bookings booking) {
        String sql = "UPDATE bookings SET status=? WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, booking.getStatus());
            ps.setInt(2, booking.getId());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBooking(int id) {
        String sql = "DELETE FROM bookings WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}