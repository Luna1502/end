package DAO;

import model.Classroom;
import model.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SchoolDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/kona";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "123456";

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Students")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                int classroomId = resultSet.getInt("classroom_id");

                Classroom classroom = getClassroomById(classroomId);

                Student student = new Student(id, name, email, dob, address, phone, classroom);
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public void addStudent(Student student) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Students (name, email, dob, address, phone, classroom_id) VALUES (?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setDate(3, Date.valueOf(student.getDob()));
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getPhone());
            preparedStatement.setInt(6, student.getClassroom().getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int studentId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Students WHERE id = ?")) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Students SET name = ?, email = ?, dob = ?, address = ?, phone = ?, classroom_id = ? WHERE id = ?")) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setDate(3, Date.valueOf(student.getDob()));
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getPhone());
            preparedStatement.setInt(6, student.getClassroom().getId());
            preparedStatement.setInt(7, student.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getStudentById(int studentId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Students WHERE id = ?")) {

            preparedStatement.setInt(1, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    LocalDate dob = resultSet.getDate("dob").toLocalDate();
                    String address = resultSet.getString("address");
                    String phone = resultSet.getString("phone");
                    int classroomId = resultSet.getInt("classroom_id");

                    Classroom classroom = getClassroomById(classroomId);

                    return new Student(id, name, email, dob, address, phone, classroom);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Classroom> getAllClassrooms() {
        List<Classroom> classrooms = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Classrooms")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Classroom classroom = new Classroom(id, name);
                classrooms.add(classroom);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classrooms;
    }

    public void addClassroom(Classroom classroom) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Classrooms (name) VALUES (?)")) {

            preparedStatement.setString(1, classroom.getName());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Classroom getClassroomById(int classroomId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Classrooms WHERE id = ?")) {

            preparedStatement.setInt(1, classroomId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    return new Classroom(id, name);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateClassroom(Classroom classroom) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Classrooms SET name = ? WHERE id = ?")) {

            preparedStatement.setString(1, classroom.getName());
            preparedStatement.setInt(2, classroom.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> searchStudent(String searchName) {
        List<Student> students = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Students WHERE name LIKE ?")) {

            preparedStatement.setString(1, "%" + searchName + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    LocalDate dob = resultSet.getDate("dob").toLocalDate();
                    String address = resultSet.getString("address");
                    String phone = resultSet.getString("phone");
                    int classroomId = resultSet.getInt("classroom_id");

                    Classroom classroom = getClassroomById(classroomId);

                    Student student = new Student(id, name, email, dob, address, phone, classroom);
                    students.add(student);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }
}
