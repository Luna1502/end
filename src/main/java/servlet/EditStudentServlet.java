package servlet;

import DAO.SchoolDAO;
import model.Classroom;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {
    private SchoolDAO schoolDAO;

    public void init() {
        schoolDAO = new SchoolDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("id"));

        Student student = schoolDAO.getStudentById(studentId);
        List<Classroom> classrooms = schoolDAO.getAllClassrooms();

        request.setAttribute("student", student);
        request.setAttribute("classrooms", classrooms);

        request.getRequestDispatcher("WEB-INF/pages/editStudent.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        int classroomId = Integer.parseInt(request.getParameter("classroomId"));

        Classroom classroom = schoolDAO.getClassroomById(classroomId);

        Student updatedStudent = new Student(studentId, name, email, null, null, null, classroom);
        schoolDAO.updateStudent(updatedStudent);

        response.sendRedirect("students");
    }
}
