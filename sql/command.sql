CREATE DATABASE IF NOT EXISTS kona;
USE kona;

CREATE TABLE Classrooms
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Students
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL,
    dob          DATE         NOT NULL,
    address      VARCHAR(255) NOT NULL,
    phone        VARCHAR(20)  NOT NULL,
    classroom_id INT,
    FOREIGN KEY (classroom_id) REFERENCES Classrooms (id)
);


