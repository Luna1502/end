<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Student List</title>

    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2>Student List</h2>
    <form action="search-student" method="get" class="form-inline mb-3">

        <div class="form-group mr-2">
            <input type="text" class="form-control" id="searchName" name="searchName" placeholder="Search by Name">
        </div>
        <button type="submit" class="btn btn-primary">Search</button>

        <a href="addStudent.jsp" class="btn btn-primary ml-auto">Add New Student</a>

        <a href="addClassroom.jsp" class="btn btn-primary ml-2">Add New Classroom</a>
    </form>

    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Date of Birth</th>
            <th>Address</th>
            <th>Phone Number</th>
            <th>Classroom</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.email}</td>
                <td>${student.dob}</td>
                <td>${student.address}</td>
                <td>${student.phone}</td>
                <td>${student.classroom.name}</td>
                <td>
                    <!-- Thêm nút "Edit" và "Delete" với Bootstrap buttons -->
                    <a href="editStudent.jsp?id=${student.id}" class="btn btn-warning btn-sm">Edit</a>
                    <a href="delete-student?id=${student.id}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this student?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
