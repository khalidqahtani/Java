--CREATE TABlE Users(
--
--ID_User bigint IDENTITY(1,1) primary key,
--ID int UNIQUE,
--First_name varchar(11),Last_name varchar(11),
--User_name varchar(12),
--Birthday  varchar(28),
--Gender varchar(14),
--Email varchar(44),
--Mobile int,
--Isdelete BIT,
--The_position int
--
--);
--
--CREATE TABlE Event(
--
--ID_Event bigint IDENTITY(1,1) primary key,
--NAMES_Event varchar(12),
--Tybe_Event varchar(12),
--C_Event varchar(18),
--Time_event varchar (18),
--E_Stret varchar(12),
--E_City varchar(12),
--E_Date DATE ,
--E_Time varchar(25),
--Capacity int,
--Edelete BIT,
--Eimprove BIT,
--ID_User bigint
--
--);
--
--CREATE TABlE Ticket(
--
--T_ID bigint IDENTITY(1,1) primary key,
--Tdelete BIT,
--ID_User bigint,
--ID_Event bigint
--
--
--);
--
--CREATE TABlE Roles(
--
--The_position int primary key,
--Name_User varchar(11)
--
--);
--
--CREATE TABLE Rate(
--The_rate int primary key,
--Rdelete BIT,
--ID_User bigint,
--ID_Event bigint
--
--);
--
--ALTER TABLE Users ADD FOREIGN KEY (The_position) REFERENCES Roles(The_position);
--ALTER TABLE Ticket ADD FOREIGN KEY (ID_User) REFERENCES Users(ID_User);
--ALTER TABLE Ticket ADD FOREIGN KEY (ID_Event) REFERENCES Event(ID_Event);
--ALTER TABLE Event ADD FOREIGN KEY (ID_User) REFERENCES Users(ID_User);
--ALTER TABLE Rate ADD FOREIGN KEY (ID_User) REFERENCES Users (ID_User);
--ALTER TABLE Rate ADD FOREIGN KEY (ID_Event) REFERENCES Event(ID_Event);
