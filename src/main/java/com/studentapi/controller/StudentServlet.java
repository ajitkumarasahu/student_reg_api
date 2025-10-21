package com.studentapi.controller;

import com.studentapi.dao.StudentDAO;
import com.studentapi.model.Student;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

public class StudentServlet extends HttpServlet {
    private StudentDAO dao = new StudentDAO();
    private Gson gson = new Gson();

    // GET: fetch all or one student
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        
        String id = req.getParameter("id");
        if (id != null) {
            Student s = dao.getStudentById(Integer.parseInt(id));
            out.print(gson.toJson(s));
        } else {
            List<Student> list = dao.getAllStudents();
            out.print(gson.toJson(list));
        }
        out.flush();
    }

    // POST: create student
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        BufferedReader reader = req.getReader();
        Student s = gson.fromJson(reader, Student.class);
        boolean status = dao.addStudent(s);
        res.setContentType("application/json");
        res.getWriter().print("{\"User add Successfully\":" + status + "}");
    }

    // PUT: update student
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        BufferedReader reader = req.getReader();
        Student s = gson.fromJson(reader, Student.class);
        boolean status = dao.updateStudent(s);
        res.setContentType("application/json");
        res.getWriter().print("{\"User Upadate Successfully\":" + status + "}");
    }

    // DELETE: remove student
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean status = dao.deleteStudent(id);
        res.setContentType("application/json");
        res.getWriter().print("{\"User Delete Successfully\":" + status + "}");
    }
}
