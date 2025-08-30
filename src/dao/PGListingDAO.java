package dao;

import Model.PGListings;
import DataBaseConnection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PGListingDAO {
    Connection conn = DBConnection.getConnection();

    public boolean addPGListing(PGListings pg) {
        String sql = "INSERT INTO pg_listings (owner_id, title, city, rent, description) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pg.getOwnerId());
            ps.setString(2, pg.getTitle());
            ps.setString(3, pg.getCity());
            ps.setDouble(4, pg.getRent());
            ps.setString(5, pg.getDescription());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public PGListings getPGListingById(int id) {
        String sql = "SELECT * FROM pg_listings WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new PGListings(
                    rs.getInt("id"),
                    rs.getInt("owner_id"),
                    rs.getString("title"),
                    rs.getString("city"),
                    rs.getDouble("rent"),
                    rs.getString("description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePGListing(PGListings pg) {
        String sql = "UPDATE pg_listings SET title=?, city=?, rent=?, description=? WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pg.getTitle());
            ps.setString(2, pg.getCity());
            ps.setDouble(3, pg.getRent());
            ps.setString(4, pg.getDescription());
            ps.setInt(5, pg.getId());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePGListing(int id) {
        String sql = "DELETE FROM pg_listings WHERE id = ?";
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

    public List<PGListings> getAllPGListings() {
        List<PGListings> list = new ArrayList<>();
        String sql = "SELECT * FROM pg_listings";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new PGListings(
                    rs.getInt("id"),
                    rs.getInt("owner_id"),
                    rs.getString("title"),
                    rs.getString("city"),
                    rs.getDouble("rent"),
                    rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<PGListings> getPGListingsByOwnerId(int ownerId) {
        List<PGListings> list = new ArrayList<>();
        String sql = "SELECT * FROM pg_listings WHERE owner_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ownerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new PGListings(
                    rs.getInt("id"),
                    rs.getInt("owner_id"),
                    rs.getString("title"),
                    rs.getString("city"),
                    rs.getDouble("rent"),
                    rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}