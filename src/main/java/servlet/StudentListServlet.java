package servlet;

import DAO.SchoolDAO;
import model.Classroom;
import model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student-list")
public class StudentListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SchoolDAO schoolDAO = new SchoolDAO();
        List<Student> students = schoolDAO.getAllStudents();
        List<Classroom> classrooms = schoolDAO.getAllClassrooms();

        request.setAttribute("students", students);
        request.setAttribute("classrooms", classrooms);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/studentList.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchName = request.getParameter("searchName");

        SchoolDAO schoolDAO = new SchoolDAO();
        List<Student> students;

        if (searchName != null && !searchName.isEmpty()) {
            students = schoolDAO.searchStudent(searchName);
        } else {
            students = schoolDAO.getAllStudents();
        }

        List<Classroom> classrooms = schoolDAO.getAllClassrooms();

        request.setAttribute("students", students);
        request.setAttribute("classrooms", classrooms);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/studentList.jsp");
        dispatcher.forward(request, response);
    }
}
