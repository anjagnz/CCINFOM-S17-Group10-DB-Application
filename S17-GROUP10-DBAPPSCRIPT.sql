-- Database Creation

CREATE DATABASE IF NOT EXISTS S17_Group10_ConcertDB;
USE S17_Group10_ConcertDB;


-- Table Creation

DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
	user_ID INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    user_email VARCHAR(64),
    user_password VARCHAR(24)
);

DROP TABLE IF EXISTS Customers;
CREATE TABLE Customers (
	customer_ID INT PRIMARY KEY AUTO_INCREMENT,
    user_ID INT,
    FOREIGN KEY (user_ID) REFERENCES Users (user_ID)
);

DROP TABLE IF EXISTS Employee_Roles;
CREATE TABLE Employee_Roles (
	role_ID VARCHAR(3) PRIMARY KEY,
    role_name VARCHAR(100)
);

DROP TABLE IF EXISTS Employees;
CREATE TABLE Employees (
	employee_ID INT PRIMARY KEY AUTO_INCREMENT,
    weekly_salary DOUBLE,
    hire_date DATE,
    user_ID INT,
    FOREIGN KEY (user_ID) REFERENCES Users (user_ID),
	role_ID VARCHAR(3),
    FOREIGN KEY (role_ID) REFERENCES Employee_Roles (role_ID)
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

DROP TABLE IF EXISTS Transactions;
CREATE TABLE Transactions (
	transaction_ID INT PRIMARY KEY AUTO_INCREMENT,
    date_of_transaction TIMESTAMP,
    customer_ID INT,
    FOREIGN KEY (customer_ID) REFERENCES Customers (customer_ID)
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
    ticket_type_ID INT,
    FOREIGN KEY (ticket_type_ID) REFERENCES Ticket_Types (ticket_type_ID),
    transaction_ID INT,
    FOREIGN KEY (transaction_ID) REFERENCES Transactions (transaction_ID)
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
	merchandise_purchase_ID INT PRIMARY KEY AUTO_INCREMENT,
    quantity_bought INT,
    item_ID INT,
    FOREIGN KEY (item_ID) REFERENCES Merchandise (item_ID),
    transaction_ID INT,
    FOREIGN KEY (transaction_ID) REFERENCES Transactions (transaction_ID)
);


-- Pre-Existing Record Insertion

-- User Records (TBD By Jat)

LOCK TABLES Users WRITE;
INSERT INTO Users(first_name, last_name, user_email, user_password)
VALUES
('Anja', 'Gonzalez', 'anja_gonzalez@dlsu.edu.ph', 's5Z+r35Rhn');
('David', 'Buban', 'david_buban@dlsu.edu.ph', 'ZLG:c5(NG!');
('John', 'Teoxon', 'john_teoxon@dlsu.edu.ph', 'Z7dvhLrM#c');
('Mark', 'Leery', 'm_leery1987@gmail.com', 'MA8!C-gv!A');
('Benjamin', 'Gale', 'b_gales1994@gmail.com', 'FERQ538L13');
UNLOCK TABLES;

-- Customer Records (TBD By DJ)

LOCK TABLES Customers WRITE;
INSERT INTO Customers(user_ID)
VALUES
('1000');
('1001');
('1002');
('1003');
('1004');
('1005');
('1006');
('1007');
('1008');
('1009');
('1010');
('1011');
('1012');
('1013');
('1014');
('1015');
('1016');
('1017');
('1018');
('1019');
('1020');
UNLOCK TABLES;

-- Role Records (TBD By Anja)

LOCK TABLES Roles WRITE;
INSERT INTO Roles(role_ID, role_name)
VALUES
('DBA', 'Database Administrator'), -- Has all permissions of other roles and access to user information
('COG', 'Concert Organizer'), -- Handles concerts and their tickets
('TMG', 'Talent Manager'), -- Handles the performers affiliated with the application
('VMG', 'Venue Manager'), -- Handles the venues affiliated with the application
('MMG', 'Merchandise Manager'), -- Handles the merchandise being sold under different concerts
('FIA', 'Financial Analyst');
UNLOCK TABLES;

-- Employee Records (TBD By Jat)

LOCK TABLES Employees WRITE;
INSERT INTO Employees(weekly_salary, hire_date, user_ID, role_ID)
VALUES
('1915', '2008-05-15', '101', 'DBA');
('2081', '2010-05-19', '102', 'COG');
('2760', '2015-05-04', '103', 'TMG');
('2350', '2016-04-19', '104', 'VMG');
('2350', '2019-01-11', '105', 'VMG');
('2350', '2009-03-17', '106', 'VMG');
('2183', '2009-11-26', '107', 'MMG');
('2183', '2011-11-28', '108', 'MMG');
('2456', '2012-09-13', '109', 'FIA');
('2456', '2015-09-30', '110', 'FIA');
UNLOCK TABLES;

-- Performer Records (TBD By DJ)

LOCK TABLES Performers WRITE;
INSERT INTO Performers(performer_name, record_label)
VALUES
('Michael Jackson', 'Sony Records');
('Michael Jackson', 'Epic Records');
('Rihanna', 'Roc Nation');
('Alicia Keys', 'Roc Nation');
('Lil Uzi Vert', 'Roc Nation');
('Megan Thee Stallion', 'Roc Nation');
('Billie Eilish', 'Interscope Records');
('Eminem', 'Shady Records');
('Eminem', 'Aftermath Entertainment');
('Anderson .Paak', 'Aftermath Entertainment');
('Anderson .Paak', 'Apeshit Inc.');
('Kendrick Lamar', 'Interscope Records');
('Kendrick Lamar', 'Aftermath Entertainment');
('Kendrick Lamar', 'Top Dawg Entertainment');
('Juice WRLD', 'Interscope Records');
('Juice WRLD', 'Grade "A" Productions');
('Juice WRLD', 'Polydor Records');

UNLOCK TABLES;

-- Location Records (TBD By Jat)

LOCK TABLES Locations WRITE;
INSERT INTO Locations(street, city)
VALUES
('1260 6th Ave', 'New York');
('Wembley HA9 0WS', 'London');
('2 Place de la Porte Maillot', 'Paris');
('Via Pietro Borsieri', 'Milan');
('Kleine Bockenheimer Str. 18a', 'Frankfurt');
('285 W Washington St', 'Athens');
('2124 Broadway', 'New York');
('Almogavers 122', 'Barcelona');
UNLOCK TABLES;

-- Venue Records (TBD By Jat)

LOCK TABLES Venues WRITE;
INSERT INTO Venues(venue_name, rental_fee, merchandise_cut_percentage, location_ID)
VALUES
('Radio City Music Hall', '39000', '%', '1');
('Wembley Stadium', '54000', '%', '2');
('Palais des Congres de Paris', '80000', '%', '3');
('Blue Note', '43000', '%', '4');
('Jazzkeller', '38000', '%', '5');
('40 Watt Club', '42500', '%', '6');
('The Beacon Theater', '52400', '%', '1');
('Razzmatazz', '38500', '%', '7');
UNLOCK TABLES;

-- Venue Section Records (TBD By Jat)

LOCK TABLES Venue_Sections WRITE;
INSERT INTO Venue_Sections(section_name, capacity, venue_ID)
VALUES
('Standard', '1500', '1');
('Premium', '850', '1');
('Front-Row', '280', '1');
('VIP', '150', '1');

('Standard', '2050', '2');
('Premium', '1800', '2');
('Front-Row', '350', '2');
('VIP', '280', '2');

('Standard', '2250', '3');
('Premium', '1250', '3');
('Front-Row', '580', '3');
('VIP', '120', '3');

('Standard', '1780', '4');
('Premium', '950', '4');
('Front-Row', '200', '4');
('VIP', '100', '4');

('Standard', '1100', '5');
('Premium', '550', '5');
('Front-Row', '280', '5');
('VIP', '150', '5');

('Standard', '1000', '6');
('Premium', '850', '6');
('Front-Row', '200', '6');
('VIP', '60', '6');

('Standard', '1500', '7');
('Premium', '1050', '7');
('Front-Row', '280', '7');
('VIP', '150', '7');

('Standard', '1000', '8');
('Premium', '770', '8');
('Front-Row', '180', '8');
('VIP', '50', '8');
UNLOCK TABLES;

-- Concert Records (TBD By Anja)

LOCK TABLES Concerts WRITE;
INSERT INTO Concerts(concert_name, concert_date, start_time, end_time, concert_status, performer_ID, concert_ID)
VALUES
();
UNLOCK TABLES;

-- Transaction Records (TBD By Anja)

LOCK TABLES Transactions WRITE;
INSERT INTO Transactions(date_of_transaction, customer_ID)
VALUES
();
UNLOCK TABLES;

-- Ticket Type Records (TBD By Anja)

LOCK TABLES Ticket_Types WRITE;
INSERT INTO Ticket_Types(type_name, price, quantity_in_stock, concert_ID, section_ID)
VALUES
();
UNLOCK TABLES;

-- Ticket Records (TBD By Anja)

LOCK TABLES Tickets WRITE;
INSERT INTO Tickets(ticket_type_ID, transaction_ID)
VALUES
();
UNLOCK TABLES;

-- Merchandise Records (TBD By DJ)

LOCK TABLES Merchandise WRITE;
INSERT INTO Merchandise(item_name, price, quantity_in_stock, concert_ID)
VALUES
();
UNLOCK TABLES;

-- Merchandise Purchase Records (TBD By DJ)

LOCK TABLES Merchandise_Purchases WRITE;
INSERT INTO Merchandise_Purchases(quantity_bought, item_ID, transaction_ID)
VALUES
();
UNLOCK TABLES;
