package com.studentapi.controller;

import com.studentapi.dao.CourseDAO;
import com.studentapi.model.Course;
import com.studentapi.util.FileUploadUtil;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;
import java.io.*;
import java.util.List;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class CourseServlet extends HttpServlet {
    private CourseDAO dao = new CourseDAO();
    private Gson gson = new Gson();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        String id = req.getParameter("id");
        PrintWriter out = res.getWriter();

        if (id != null) {
            Course c = dao.getCourseById(Integer.parseInt(id));
            out.print(gson.toJson(c));
        } else {
            List<Course> list = dao.getAllCourses();
            out.print(gson.toJson(list));
        }
        out.flush();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String uploadPath = getServletContext().getRealPath("") + "uploads";

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String duration = req.getParameter("duration");

        Part filePart = req.getPart("image");
        String imagePath = null;
        if (filePart != null && filePart.getSize() > 0) {
            imagePath = FileUploadUtil.saveFile(uploadPath, filePart);
        }

        Course c = new Course();
        c.setName(name);
        c.setDescription(description);
        c.setDuration(duration);
        c.setImage(imagePath);

        boolean status = dao.addCourse(c);
        res.setContentType("application/json");
        res.getWriter().print("{\"success\":" + status + "}");
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        BufferedReader reader = req.getReader();
        Course c = gson.fromJson(reader, Course.class);
        boolean status = dao.updateCourse(c);
        res.setContentType("application/json");
        res.getWriter().print("{\"success\":" + status + "}");
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean status = dao.deleteCourse(id);
        res.setContentType("application/json");
        res.getWriter().print("{\"success\":" + status + "}");
    }
}
