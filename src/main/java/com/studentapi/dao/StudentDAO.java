package com.studentapi.dao;

import com.studentapi.model.Student;
import com.studentapi.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // CREATE
    public boolean addStudent(Student s) {
        boolean result = false;
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO bcastudent (name, email, mob, course, fathername, mothername, city, pin) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getMob());
            ps.setString(4, s.getCourse());
            ps.setString(5, s.getFathername());
            ps.setString(6, s.getMothername());
            ps.setString(7, s.getCity());
            ps.setString(8, s.getPin());
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // READ all
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM bcastudent";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setEmail(rs.getString("email"));
                s.setMob(rs.getString("mob"));
                s.setCourse(rs.getString("course"));
                s.setFathername(rs.getString("fathername"));
                s.setMothername(rs.getString("mothername"));
                s.setCity(rs.getString("city"));
                s.setPin(rs.getString("pin"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // READ by ID
    public Student getStudentById(int id) {
        Student s = null;
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM bcastudent WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setEmail(rs.getString("email"));
                s.setMob(rs.getString("mob"));
                s.setCourse(rs.getString("course"));
                s.setFathername(rs.getString("fathername"));
                s.setMothername(rs.getString("mothername"));
                s.setCity(rs.getString("city"));
                s.setPin(rs.getString("pin"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    // UPDATE
    public boolean updateStudent(Student s) {
        boolean result = false;
        try (Connection con = DBConnection.getConnection()) {
            String sql = "UPDATE bcastudent SET name=?, email=?, mob=?, course=?, fathername=?, mothername=?, city=?, pin=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getMob());
            ps.setString(4, s.getCourse());
            ps.setString(5, s.getFathername());
            ps.setString(6, s.getMothername());
            ps.setString(7, s.getCity());
            ps.setString(8, s.getPin());
            ps.setInt(9, s.getId());
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // DELETE
    public boolean deleteStudent(int id) {
        boolean result = false;
        try (Connection con = DBConnection.getConnection()) {
            String sql = "DELETE FROM bcastudent WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
