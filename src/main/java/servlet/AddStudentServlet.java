package servlet;

import DAO.SchoolDAO;
import model.Student;
import model.Classroom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
    private SchoolDAO schoolDAO;

    public void init() {
        schoolDAO = new SchoolDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy danh sách lớp học từ cơ sở dữ liệu
        request.setAttribute("classrooms", schoolDAO.getAllClassrooms());

        request.getRequestDispatcher("WEB-INF/pages/addStudent.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        int classroomId = Integer.parseInt(request.getParameter("classroomId"));

        Classroom classroom = schoolDAO.getClassroomById(classroomId);
        Student newStudent = new Student(0, name, email, dob, address, phone, classroom);

        schoolDAO.addStudent(newStudent);

        response.sendRedirect(request.getContextPath() + "/students");
    }
}
