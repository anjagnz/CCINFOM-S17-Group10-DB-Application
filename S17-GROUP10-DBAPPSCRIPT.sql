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
	performer_name VARCHAR(100)
);

DROP TABLE IF EXISTS Countries;
CREATE TABLE Countries (
	country_ID VARCHAR(3) PRIMARY KEY,
    country_name VARCHAR(64)
);

DROP TABLE IF EXISTS Cities;
CREATE TABLE Cities (
	city_ID INT PRIMARY KEY AUTO_INCREMENT,
    city_name VARCHAR(64),
    country_ID VARCHAR(3),
    FOREIGN KEY (country_ID) REFERENCES Countries (country_ID)
);

DROP TABLE IF EXISTS Locations;
CREATE TABLE Locations (
	location_ID INT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(100),
    city_ID INT,
    FOREIGN KEY (city_ID) REFERENCES Cities (city_ID) 
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
('Anja', 'Gonzales', 'anja_gonzales@dlsu.edu.ph', 'password1'),
('David', 'Buban', 'david_joseph_buban@dlsu.edu.ph', 'password2'),
('John', 'Teoxon', 'john_teoxon@dlsu.edu.ph', 'password3'),
('Mark', 'Leery', 'm_leery1987@gmail.com', 'password4'),
('Benjamin', 'Gale', 'b_gales1994@gmail.com', 'password5'),
('Stephen', 'King', 'stephen_king@gmail.com', 'password6'),
('Nick', 'Frenkel', 'nick_frenkel@gmail.com', 'password7'),
('Lori', 'Greiner', 'lori_greiner@gmail.com', 'password8'),
('Phoenix', 'Wright', 'phoenix_wright@gmail.com', 'password9'),
('Maya', 'Fey', 'maya_fey@gmail.com', 'password10'),
('Apollo', 'Justice', 'apollo_justice@gmail.com', 'password11'),
('Johnny', 'Depp', 'johnny_depp@gmail.com', 'password12'),
('Arnold', 'Schwarzenegger', 'arnold_schwarzenegger@gmail.com', 'password13'),
('Jim', 'Carrey', 'jim_carrey@gmail.com', 'password14'),
('Emma', 'Watson', 'emma_watson@gmail.com', 'password15'),
('Daniel', 'Radcliffe', 'daniel_radcliffe@gmail.com', 'password16'),
('Chris', 'Evans', 'chris_evans@gmail.com', 'password17'),
('Brad', 'Pitt', 'brad_pitt@gmail.com', 'password18'),
('Morgan', 'Freeman', 'morgan_freeman@gmail.com', 'password19'),
('Angelina', 'Jolie', 'angelina_jolie@gmail.com', 'password20'),
('Ryan', 'Reynolds', 'ryan_reynolds@gmail.com', 'password21'),
('Blake', 'Lively', 'blake_lively@gmail.com', 'password22'),
('Hugh', 'Jackman', 'hugh_jackman@gmail.com', 'password23'),
('Tom', 'Holland', 'tom_holland@gmail.com', 'password24'),
('Zendaya', 'Coleman', 'zendaya_coleman@gmail.com', 'password25');
UNLOCK TABLES;

-- Customer Records (TBD By DJ)

LOCK TABLES Customers WRITE;
INSERT INTO Customers(user_ID)
VALUES
(9), (10), (11), (12), (13), (14), (15), (16), (17), (18), (19), (20), (21), (22), (23), (24), (25);
UNLOCK TABLES;

-- Role Records (TBD By Anja)

LOCK TABLES Employee_Roles WRITE;
INSERT INTO Employee_Roles(role_ID, role_name)
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
(4200, '2008-05-15', 1, 'DBA'),
(4320, '2010-05-19', 2, 'DBA'),
(4750, '2015-05-04', 3, 'DBA'),
(2350, '2016-04-19', 4, 'COG'),
(2350, '2019-01-11', 5, 'MMG'),
(2183, '2009-11-26', 6, 'VMG'),
(2183, '2011-11-28', 7, 'TMG'),
(2456, '2012-09-13', 8, 'FIA');
UNLOCK TABLES;

-- Performer Records (TBD By DJ)

LOCK TABLES Performers WRITE;
INSERT INTO Performers(performer_name)
VALUES
('Michael Jackson'),
('Rihanna'),
('Alicia Keys'),
('Lil Uzi Vert'),
('Megan Thee Stallion'),
('Billie Eilish'),
('Eminem'),
('Anderson .Paak'),
('Kendrick Lamar'),
('Juice WRLD'),
('Sabrina Carpenter'),
('Bruno Mars'),
('The Weeknd'),
('Arctic Monkeys'),
('Laufey'),
('Olivia Rodrigo'),
('wave to earth'),
('Chase Atlantic'),
('The Neighbourhood'),
('Lady Gaga'),
('Adele'),
('Mariah Carey'),
('beabadoobee'),
('Harry Styles'),
('Taylor Swift');
UNLOCK TABLES;

-- Country Records (TBD By Anja)

LOCK TABLES Countries WRITE;
INSERT INTO Countries(country_id, country_name)
VALUES
('USA', 'United States of America'),
('GBR', 'United Kingdom'),
('FRA', 'France'),
('ITA', 'Italy'),
('DEU', 'Germany'),
('GRC', 'Greece'),
('ESP', 'Spain');
UNLOCK TABLES;

-- City Records (TBD By Anja)

LOCK TABLES Cities WRITE;
INSERT INTO Cities(city_name, country_id)
VALUES
('New York', 'USA'),
('London', 'GBR'),
('Paris', 'FRA'),
('Milan', 'ITA'),
('Frankfurt', 'DEU'),
('Athens', 'GRC'),
('Barcelona', 'ESP');
-- Location Records (TBD By Jat)

LOCK TABLES Locations WRITE;
INSERT INTO Locations(street, city_id)
VALUES
('1260 6th Ave', 1),
('Wembley HA9 0WS', 2),
('2 Place de la Porte Maillot', 3),
('Via Pietro Borsieri', 4),
('Kleine Bockenheimer Str. 18a', 5),
('285 W Washington St', 6),
('2124 Broadway', 1),
('Almogavers 122', 7);
UNLOCK TABLES;

-- Venue Records (TBD By Jat)

LOCK TABLES Venues WRITE;
INSERT INTO Venues(venue_name, rental_fee, merchandise_cut_percentage, location_ID)
VALUES
('Radio City Music Hall', 39000, 7.5, 1),
('Wembley Stadium', 54000, 10, 2),
('Palais des Congres de Paris', 80000, 15, 3),
('Blue Note', 43000, 8, 4),
('Jazzkeller', 38000, 7, 5),
('40 Watt Club', 42500, 9, 6),
('The Beacon Theater', 52400, 11, 7),
('Razzmatazz', 38500, 5, 8);
UNLOCK TABLES;

-- Venue Section Records (TBD By Jat)

LOCK TABLES Venue_Sections WRITE;
INSERT INTO Venue_Sections(section_name, capacity, venue_ID)
VALUES
('Standard', 1500, 1),
('Premium', 850, 1),
('Front-Row', 280, 1),
('VIP', 150, 1),

('Standard', 2050, 2),
('Premium', 1800, 2),
('Front-Row', 350, 2),
('VIP', 280, 2),

('Standard', 2250, 3),
('Premium', 1250, 3),
('Front-Row', 580, 3),
('VIP', 120, 3),

('Standard', 1780, 4),
('Premium', 950, 4),
('Front-Row', 200, 4),
('VIP', 100, 4),

('Standard', 1100, 5),
('Premium', 550, 5),
('Front-Row', 280, 5),
('VIP', 150, 5),

('Standard', 1000, 6),
('Premium', 850, 6),
('Front-Row', 200, 6),
('VIP', '60', '6'),

('Standard', 1500, 7),
('Premium', 1050, 7),
('Front-Row', 280, 7),
('VIP', 150, 7),

('Standard', 1000, 8),
('Premium', 770, 8),
('Front-Row', 180, 8),
('VIP', 50, 8);
UNLOCK TABLES;


-- Concert Records (TBD By Anja)

LOCK TABLES Concerts WRITE;
INSERT INTO Concerts(concert_name, concert_date, start_time, end_time, concert_status, performer_ID, venue_ID)
VALUES
('BILLIE EILISH: HIT ME HARD AND SOFT: THE TOUR', '11-13-2024', '19:00:00', '23:00:00', 'COMPLETED', 6, 1),
('Love On Tour', '08-01-2023', '18:00:00', '22:00:00', 'COMPLETED', 24, 2),
('The Pop Out', '06-19-2024', '18:30:00', '22:00:00', 'COMPLETED', 9, 3),
('The Car Tour', '10-19-2023', '18:00:00', '22:30:00', 'COMPLETED', 14, 4),
('After Hours Till Dawn Tour', '11-25-2023', '18:30:00', '23:00:00', 'COMPLETED', 13, 5);
UNLOCK TABLES;

-- Ticket Type Records (TBD By Anja)

LOCK TABLES Ticket_Types WRITE;
INSERT INTO Ticket_Types(type_name, price, quantity_in_stock, concert_ID, section_ID)
VALUES
('General Admission', 80, 0, 1, 1),
('Reserved Seating', 120, 0, 1, 2),
('Front-Row', 200, 0, 1, 3),
('VIP', 300, 0, 1, 4),

('General Admission', 65, 0, 2, 5),
('Reserved Seating', 98, 0, 2, 6),
('Front-Row', 175, 0, 2, 7),
('VIP', 260, 0, 2, 8),

('General Admission', 59, 0, 3, 9),
('Reserved Seating', 89, 0, 3, 10),
('Front-Row', 149, 0, 3, 11),
('VIP', 249, 0, 3, 12),

('General Admission', 72, 0, 4, 13),
('Reserved Seating', 110, 0, 4, 14),
('Front-Row', 180, 0, 4, 15),
('VIP', 275, 0, 4, 16),

('General Admission', 85, 0, 5, 17),
('Reserved Seating', 130, 0, 5, 18),
('Front-Row', 220, 0, 5, 19),
('VIP', 330, 0, 5, 20);
UNLOCK TABLES;

-- Transaction Records (TBD By Anja)

LOCK TABLES Transactions WRITE;
INSERT INTO Transactions(date_of_transaction, customer_ID)
VALUES
('11-01-2024', 1),
('07-02-2023', 2),
('05-09-2024', 3),
('10-03-2024', 4),
('10-31-2023', 5);
UNLOCK TABLES;

-- Ticket Records (TBD By Anja)

LOCK TABLES Tickets WRITE;
INSERT INTO Tickets(ticket_type_ID, transaction_ID)
VALUES
(1, 1), (1, 1), (1, 1), (2, 1), (2, 1), (3, 1), (4, 1),
(1, 2), (1, 2), (2, 2), (2, 2), (2, 2), (3, 2), (3, 2), (4, 2),
(1, 3), (1, 3), (2, 3), (3, 3), (4, 3), (4, 3), (4, 3),
(1, 4), (1, 4), (1, 4), (1, 4), (1, 4), (2, 4), (3, 4), (4, 4),
(1, 5), (2, 5), (2, 5), (2, 5), (3, 5), (3, 5), (3, 5), (4, 5);
UNLOCK TABLES;

-- Merchandise Records (TBD By DJ)

LOCK TABLES Merchandise WRITE;
INSERT INTO Merchandise(item_name, price, quantity_in_stock, concert_ID)
VALUES
('LED Wristband', 250, 10000, 5),
('Poster', 550, 5000, 5),
('T-shirt', 400, 7000, 4),
('Sweater', 750, 3000, 4),
('T-shirt', 400, 7500, 3),
('Poster', 500, 5000, 3),
('Hoodie', 900, 3000, 2),
('Tote Bag', 500, 4000, 2),
('Hoodie', 1000, 3000, 1),
('Crop top', 400, 2500, 1);
UNLOCK TABLES;

-- Merchandise Purchase Records (TBD By DJ)

LOCK TABLES Merchandise_Purchases WRITE;
INSERT INTO Merchandise_Purchases(quantity_bought, item_ID, transaction_ID)
VALUES
();
UNLOCK TABLES;
