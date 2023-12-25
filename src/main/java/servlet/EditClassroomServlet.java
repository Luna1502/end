package servlet;

import DAO.SchoolDAO;
import model.Classroom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit-classroom")
public class EditClassroomServlet extends HttpServlet {
    private SchoolDAO schoolDAO;

    public void init() {
        schoolDAO = new SchoolDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int classroomId = Integer.parseInt(request.getParameter("id"));

        Classroom classroom = schoolDAO.getClassroomById(classroomId);

        request.setAttribute("classroom", classroom);

        request.getRequestDispatcher("WEB-INF/pages/editClassroom.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int classroomId = Integer.parseInt(request.getParameter("id"));
        String newName = request.getParameter("name");

        Classroom updatedClassroom = new Classroom(classroomId, newName);
        schoolDAO.updateClassroom(updatedClassroom);

        response.sendRedirect("classrooms");
    }
}
