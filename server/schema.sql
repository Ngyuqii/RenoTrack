-- Create database
CREATE DATABASE renotrack;

-- Use database
USE renotrack;

-- Create a table
CREATE TABLE users (
    user_id varchar(50) NOT NULL,
    user_name varchar(50) NOT NULL,
    user_email varchar(50) UNIQUE NOT NULL,
    user_password varchar(255) NOT NULL,
	CONSTRAINT pkid PRIMARY KEY(user_id)
);

-- Retrieve data from table
SELECT * FROM users WHERE user_email = ?;
SELECT user_password FROM users WHERE user_email = ?;

-- Insert data into table
INSERT INTO users (user_id, user_name, user_email, user_password) VALUES (?, ?, ?, ?);