package latihanresponsi.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO {

    public boolean addCandidate(Candidate candidate) {
        String sql = "INSERT INTO candidate (name, role, writing_test, coding_test, interview_test, final_score, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Koneksi gagal, tidak bisa menambah data.");
                return false;
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, candidate.getName());
            pstmt.setString(2, candidate.getRole());
            pstmt.setDouble(3, candidate.getWritingScore());
            pstmt.setDouble(4, candidate.getCodingScore());
            pstmt.setDouble(5, candidate.getInterviewScore());
            pstmt.setDouble(6, candidate.calculateFinalScore());
            pstmt.setString(7, candidate.determineStatus());
            
            pstmt.executeUpdate();
            System.out.println("Data berhasil ditambahkan.");
            return true;
            }
        } catch (SQLException e) {
            System.err.println("Gagal menambah data: " + e.getMessage());
            return false;
        }
    }

    public List<Candidate> getAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        String sql = "SELECT * FROM candidate";
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Koneksi gagal, tidak bisa mengambil data.");
                return candidates;
            }
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String role = rs.getString("role");
                double writingTest = rs.getDouble("writing_test");
                double codingTest = rs.getDouble("coding_test");
                double interviewTest = rs.getDouble("interview_test");
                
                Candidate candidate;
                if (role.equalsIgnoreCase("Android Developer")) {
                    candidate = new AndroidDeveloper(id, name, writingTest, codingTest, interviewTest);
                } else {
                    candidate = new WebDeveloper(id, name, writingTest, codingTest, interviewTest);
                }
                candidates.add(candidate);
            }
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil data: " + e.getMessage());
        }
        return candidates;
    }

    public boolean updateCandidate(Candidate candidate) {
        String sql = "UPDATE candidate SET name=?, role=?, writing_test=?, coding_test=?, interview_test=?, final_score=?, status=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Koneksi gagal, tidak bisa mengupdate data.");
                return false;
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, candidate.getName());
            pstmt.setString(2, candidate.getRole());
            pstmt.setDouble(3, candidate.getWritingScore());
            pstmt.setDouble(4, candidate.getCodingScore());
            pstmt.setDouble(5, candidate.getInterviewScore());
            pstmt.setDouble(6, candidate.calculateFinalScore());
            pstmt.setString(7, candidate.determineStatus());
            pstmt.setInt(8, candidate.getId());
            
            pstmt.executeUpdate();
            System.out.println("Data berhasil diupdate.");
            return true;
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengupdate data: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteCandidate(int id) {
        String sql = "DELETE FROM candidate WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Koneksi gagal, tidak bisa menghapus data.");
                return false;
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Data berhasil dihapus.");
            return true;
            }
        } catch (SQLException e) {
            System.err.println("Gagal menghapus data: " + e.getMessage());
            return false;
        }
    }
}
