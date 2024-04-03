
drop database Pizza;
Create database Pizza;
use Pizza;
Show tables;

CREATE TABLE topping (
  T_ID TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  T_Name VARCHAR(30) NOT NULL,
   T_Price DECIMAL(4,2) NOT NULL,
  T_Cost DECIMAL(4,2) NOT NULL, 
   Curr_Inv_Level INT NOT NULL,
    Min_Inv_Level INT NOT NULL,
  T_Personal INT  NOT NULL,
  T_Medium INT NOT NULL,
  T_Large INT NOT NULL,
  T_XLarge INT NOT NULL  
);


CREATE TABLE discount (
  Discount_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  Discount_Name VARCHAR(30) NOT NULL,
  Percent_Off DECIMAL(5,2),
  Amount_Off DECIMAL(5,2)
);


create table customer(
Cus_ID int not null primary key AUTO_INCREMENT,
Cus_Fname varchar(20) not null,
Cus_Lname varchar(20) not null,
Cus_PhoneNumber varchar(10) not null,
Cus_Email_Id varchar(15) not null,
Cus_Street_Address varchar(20) not null,
Cus_State char(2) not null,
Cus_ZipCode varchar(6) not null
);


CREATE TABLE `order` (
  Ord_ID int not null primary key AUTO_INCREMENT,
  Ord_Time time not null,
  Ord_Type varchar(10) not null,
  Ord_Cost decimal(5,2) not null,
  Ord_Price decimal(5,2) not null
);

CREATE TABLE base_price (
  BP_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  BP_Size INT NOT NULL,
  BP_Crust varchar(20) NOT NULL,
  BP_Price DECIMAL(4,2),
  BP_Cost DECIMAL(4,2)
);



CREATE TABLE pick_up (
  Ord_ID INT NOT NULL,
  Cus_ID INT NOT NULL ,
  PRIMARY KEY (Ord_ID),
  FOREIGN KEY (Ord_ID) REFERENCES `order` (Ord_ID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (Cus_ID) REFERENCES customer (Cus_ID) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE dine_in (
  Ord_ID INT NOT NULL,
  Table_No TINYINT NOT NULL,
  PRIMARY KEY (Ord_ID),
  FOREIGN KEY (Ord_ID) REFERENCES `order`(Ord_ID) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE delivery (
  Ord_ID INT NOT NULL,
  Cus_ID INT  NULL,
  PRIMARY KEY (Ord_ID),
  FOREIGN KEY (Ord_ID) REFERENCES `order` (Ord_ID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (Cus_ID) REFERENCES customer (Cus_ID) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE pizza (
  Pizza_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  Pizza_Cost DECIMAL(5,2) NOT NULL,
  Pizza_Price DECIMAL(5,2) NOT NULL,
  Pizza_State VARCHAR(10) NOT NULL,
  BP_ID INT NULL,
  Ord_ID INT NULL,
  FOREIGN KEY(BP_ID) REFERENCES base_price(BP_ID),
  FOREIGN KEY(Ord_ID) REFERENCES `order`(Ord_ID) 
);


CREATE TABLE order_discount (
 Discount_ID INT NOT NULL AUTO_INCREMENT,
  Ord_ID INT NOT NULL,
  PRIMARY KEY(Discount_ID, Ord_ID),
  FOREIGN KEY(Discount_ID) REFERENCES discount(Discount_ID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(Ord_ID) REFERENCES `order`(Ord_ID) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE pizza_discount (
 Discount_ID INT NOT NULL AUTO_INCREMENT,
  Pizza_ID INT NOT NULL,
  PRIMARY KEY(Discount_ID, Pizza_ID),
  FOREIGN KEY(Discount_ID) REFERENCES discount(Discount_ID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(Pizza_ID) REFERENCES pizza(Pizza_ID) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE pizza_topping (
  Pizza_ID INT NOT NULL AUTO_INCREMENT,
  T_ID TINYINT NOT NULL,
  Extra_Topping varchar(15) NULL,
  PRIMARY KEY(Pizza_ID, T_ID),
  FOREIGN KEY(Pizza_ID) REFERENCES pizza(Pizza_ID) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(T_ID) REFERENCES topping(T_ID) ON UPDATE CASCADE ON DELETE CASCADE 
);

















