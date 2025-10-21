package com.studentapi.dao;

import com.studentapi.model.Course;
import com.studentapi.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public boolean addCourse(Course c) {
        boolean status = false;
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO courses(name, description, duration, image) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setString(2, c.getDescription());
            ps.setString(3, c.getDuration());
            ps.setString(4, c.getImage());
            status = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM courses";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setDescription(rs.getString("description"));
                c.setDuration(rs.getString("duration"));
                c.setImage(rs.getString("image"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Course getCourseById(int id) {
        Course c = null;
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM courses WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Course();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setDescription(rs.getString("description"));
                c.setDuration(rs.getString("duration"));
                c.setImage(rs.getString("image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public boolean updateCourse(Course c) {
        boolean status = false;
        try (Connection con = DBConnection.getConnection()) {
            String sql = "UPDATE courses SET name=?, description=?, duration=?, image=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setString(2, c.getDescription());
            ps.setString(3, c.getDuration());
            ps.setString(4, c.getImage());
            ps.setInt(5, c.getId());
            status = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean deleteCourse(int id) {
        boolean status = false;
        try (Connection con = DBConnection.getConnection()) {
            String sql = "DELETE FROM courses WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            status = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
