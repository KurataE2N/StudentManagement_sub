CREATE TABLE IF NOT EXISTS students
(
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  furigana VARCHAR(50) NOT NULL,
  nickname VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  region VARCHAR(50),
  age INT,
  gender VARCHAR(10),
  remarks TEXT,
  is_deleted boolean
);

CREATE TABLE IF NOT EXISTS students_courses
(
  id INT AUTO_INCREMENT PRIMARY KEY,
  students_id INT NOT NULL,
  course VARCHAR(50) NOT NULL,
  classopen TIMESTAMP,
  classcomp TIMESTAMP
);