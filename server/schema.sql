-- Create database
CREATE DATABASE renotrack;

-- Use database
USE renotrack;

-- Users Table
-- 1.1 Create a table
CREATE TABLE users (
    user_id varchar(50) NOT NULL,
    user_name varchar(50) NOT NULL,
    user_email varchar(50) UNIQUE NOT NULL,
    user_password varchar(255) NOT NULL,
	CONSTRAINT pkid PRIMARY KEY(user_id)
);

-- 1.2 Retrieve data from table
SELECT * FROM users WHERE user_email = ?;
SELECT user_password FROM users WHERE user_email = ?;

-- 1.3 Insert data into table
INSERT INTO users (user_id, user_name, user_email, user_password) VALUES (?, ?, ?, ?);

-- Events Table
-- 2.1 Create a table
CREATE TABLE events (
  id int NOT NULL AUTO_INCREMENT,
  user_id varchar(50) NOT NULL,
  event_id int NOT NULL,
  subject varchar(255) NOT NULL,
  start_time varchar(255) NOT NULL,
  end_time varchar(255) NOT NULL,
  description varchar(255),
  location varchar(255),
  CONSTRAINT pkid PRIMARY KEY(id)
);

-- 2.2 Retrieve data from table
SELECT * FROM events WHERE user_id = ?;

-- 2.3 Insert data into table
INSERT INTO events (user_id, event_id, subject, start_time, end_time, description, location) VALUES (?, ?, ?, ?, ?, ?, ?);

-- 2.4 Update exisitng data in table
UPDATE events SET subject = ?, start_time = ?, end_time = ?, description = ?, location = ? WHERE user_id = ? AND event_id = ?;

-- 2.5 Delete data from table
DELETE FROM events WHERE user_id = ? AND event_id = ?;
