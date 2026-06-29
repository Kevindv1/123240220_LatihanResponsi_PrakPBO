CREATE DATABASE IF NOT EXISTS recruit_db;
USE recruit_db;

CREATE TABLE IF NOT EXISTS candidate (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    writing_test DOUBLE NOT NULL,
    coding_test DOUBLE NOT NULL,
    interview_test DOUBLE NOT NULL,
    final_score DOUBLE NOT NULL,
    status VARCHAR(50) NOT NULL
);
