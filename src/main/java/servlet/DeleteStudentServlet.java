package servlet;

import DAO.SchoolDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-student")
public class DeleteStudentServlet extends HttpServlet {
    private SchoolDAO schoolDAO;

    public void init() {
        schoolDAO = new SchoolDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("id"));

        schoolDAO.deleteStudent(studentId);

        response.sendRedirect(request.getContextPath() + "/students");
    }
}
