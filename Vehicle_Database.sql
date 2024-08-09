create database testing;
use testing;
create table if not exists Vehicles(id int,name varchar(25),type varchar(25),seats int, startDate Date , endDate Date); 
select * from vehicles;
drop table vehicles;
CREATE TABLE if not exists Bookings
(
	customerName VARCHAR(100),
	customerEmail VARCHAR(100),
	customerPhone VARCHAR(20),
    vehicleID INT,
    startDate DATE,
    endDate DATE
);
select * from Bookings;