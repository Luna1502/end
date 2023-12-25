package servlet;

import DAO.SchoolDAO;
import model.Classroom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-classroom")
public class AddClassroomServlet extends HttpServlet {
    private SchoolDAO schoolDAO;

    public void init() {
        schoolDAO = new SchoolDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/addClassroom.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String classroomName = request.getParameter("classroomName");

        Classroom newClassroom = new Classroom(0, classroomName);

        schoolDAO.addClassroom(newClassroom);

        request.setAttribute("classrooms", schoolDAO.getAllClassrooms());

        request.getRequestDispatcher("/studentList.jsp").forward(request, response);
    }
}

