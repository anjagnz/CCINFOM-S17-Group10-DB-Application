-- Database Creation

CREATE DATABASE IF NOT EXISTS S17_Group10_ConcertDB;
USE S17_Group10_ConcertDB;


-- Table Creation

DROP TABLE IF EXISTS Roles;
CREATE TABLE Roles (
	role_ID VARCHAR(3) PRIMARY KEY,
    role_name VARCHAR(100)
);

DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
	user_ID INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    user_email VARCHAR(64),
    user_password VARCHAR(24),
    role_ID VARCHAR(3),
    FOREIGN KEY (role_ID) REFERENCES Roles (role_ID)
);

DROP TABLE IF EXISTS Performers;
CREATE TABLE Performers (
	performer_ID INT PRIMARY KEY AUTO_INCREMENT,
	performer_name VARCHAR(100),
    record_label VARCHAR(100)
);

DROP TABLE IF EXISTS Locations;
CREATE TABLE Locations (
	location_ID INT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(100),
    city VARCHAR(100)
);

DROP TABLE IF EXISTS Venues;
CREATE TABLE Venues (
	venue_ID INT PRIMARY KEY AUTO_INCREMENT,
    venue_name VARCHAR(100),
    rental_fee DOUBLE,
    merchandise_cut_percentage DOUBLE,
    location_ID INT,
    FOREIGN KEY (location_ID) REFERENCES Locations (location_ID)
);

DROP TABLE IF EXISTS Venue_Sections;
CREATE TABLE Venue_Sections (
	section_ID INT PRIMARY KEY AUTO_INCREMENT,
    section_name VARCHAR(100),
    capacity INT,
    venue_ID INT,
    FOREIGN KEY (venue_ID) REFERENCES Venues (venue_ID)
);

DROP TABLE IF EXISTS Concerts;
CREATE TABLE Concerts (
	concert_ID INT PRIMARY KEY AUTO_INCREMENT,
    concert_name VARCHAR(100),
    concert_date DATE,
    start_time TIME,
    end_time TIME,
    concert_status VARCHAR(24),
    performer_ID INT,
    FOREIGN KEY (performer_ID) REFERENCES Performers (performer_ID),
    venue_ID INT,
    FOREIGN KEY (venue_ID) REFERENCES Venues (venue_ID)
);

DROP TABLE IF EXISTS Ticket_Types;
CREATE TABLE Ticket_Types (
	ticket_type_ID INT PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(50),
    price DOUBLE,
    quantity_in_stock INT,
    concert_ID INT,
    FOREIGN KEY (concert_ID) REFERENCES Concerts (concert_ID),
    section_ID INT,
    FOREIGN KEY (section_ID) REFERENCES Venue_Sections (section_ID)
);

DROP TABLE IF EXISTS Tickets;
CREATE TABLE Tickets (
	ticket_ID INT PRIMARY KEY AUTO_INCREMENT,
    date_of_purchase TIMESTAMP,
    user_ID INT,
    FOREIGN KEY (user_ID) REFERENCES Users (user_ID),
    ticket_type_ID INT,
    FOREIGN KEY (ticket_type_ID) REFERENCES Ticket_Types (ticket_type_ID)
);

DROP TABLE IF EXISTS Merchandise;
CREATE TABLE Merchandise (
	item_ID INT PRIMARY KEY AUTO_INCREMENT,
    item_name VARCHAR(100),
    price DOUBLE,
    quantity_in_stock INT,
    concert_ID INT,
    FOREIGN KEY (concert_ID) REFERENCES Concerts (concert_ID)
);

DROP TABLE IF EXISTS Merchandise_Purchases;
CREATE TABLE Merchandise_Purchases (
	purchase_ID INT PRIMARY KEY AUTO_INCREMENT,
    date_of_purchase TIMESTAMP,
    user_ID INT,
    FOREIGN KEY (user_ID) REFERENCES Users (user_ID)
);

DROP TABLE IF EXISTS Purchase_Details;
CREATE TABLE Purchase_Details (
	purchase_details_ID INT PRIMARY KEY AUTO_INCREMENT,
    quantity_bought INT,
    item_ID INT,
    FOREIGN KEY (item_ID) REFERENCES Merchandise (item_ID),
    purchase_ID INT,
    FOREIGN KEY (purchase_ID) REFERENCES Merchandise_Purchases (purchase_ID)
);


-- Pre-Existing Record Insertion

-- Role Records (TBD By Anja)

LOCK TABLES Roles WRITE;
INSERT INTO Roles(role_ID, role_name)
VALUES
('DBA', 'Database Administrator'), -- Has all permissions of other roles
('COG', 'Concert Organizer'), -- Handles concerts and their tickets
('TMG', 'Talent Manager'), -- Handles the performers affiliated with the application
('VMG', 'Venue Manager'), -- Handles the venues affiliated with the application
('MMG', 'Merchandise Manager'), -- Handles the merchandise being sold under different concerts
('FIA', 'Financial Analyst'), -- Handles the financial reports
('CUS', 'Customer'); -- No permissions to backend information
UNLOCK TABLES;

-- User Records (TBD By Jat)

-- Performer Records (TBD By DJ)

-- Location Records (TBD By Jat)

-- Venue Records (TBD By Jat)

-- Venue Section Records (TBD By Jat)

-- Concert Records (TBD By Anja)

-- Ticket Type Records (TBD By Anja)

-- Ticket Records (TBD By Anja)

-- Merchandise Records (TBD By DJ)

-- Merchandise Purchase Records (TBD By DJ)

-- Merchandise Purchase Details Records (TBD By DJ)

