INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Pepperoni', 1.25, 0.2, 100, 50, 2, 2.75, 3.5, 4.5);

INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Sausage', 1.25, 0.15, 100, 50, 2.5, 3, 3.5, 4.25);
 
 
 
INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Chicken', 1.75, 0.25, 56, 25, 1.5, 2, 2.25, 3);
 
 INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Green Pepper', 0.5, 0.02, 79, 25, 1, 1.5, 2, 2.5);
 
INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Onion', 0.5, 0.02, 85, 25, 1, 1.5, 2, 2.75);
 
INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Roma Tomato', 0.75, 0.03, 86, 10, 2, 3, 3.5, 4.5);
 
 INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Mushrooms', 0.75, 0.1, 52, 50, 1.5, 2, 2.5, 3);
 
 INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Black Olives', 0.6, 0.1, 39, 25, 0.75, 1, 1.5, 2);
 
INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Pineapple', 1, 0.25, 15, 0, 1, 1.25, 1.75, 2);
 
INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Jalapenos', 0.5, 0.05, 64, 0, 0.5, 0.75, 1.25, 1.75);
 
 INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Banana Peppers', 0.5, 0.05, 36, 0, 0.6, 1, 1.3, 1.75);
 
 INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Regular Cheese', 0.5, 0.12, 250, 50, 2, 3.5, 5, 7);
 
 INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Four Cheese Blend', 1, 0.15, 150, 25, 2, 3.5, 5, 7);
 
 INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Feta Cheese', 1.5, 0.18, 75, 0, 1.75, 3, 4, 5.5);

INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Goat Cheese', 1.5, 0.2, 54, 0, 1.6, 2.75, 4, 5.5);
 
 INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Bacon', 1.5, 0.25, 89, 0, 1, 1.5, 2,3); 
 
INSERT INTO topping
 (T_Name, T_Price, T_Cost, Curr_Inv_Level, Min_Inv_Level, T_Personal,  T_Medium,  T_Large, T_XLarge)
 values('Ham', 1.5, 0.15, 78, 25, 2, 2.5, 3.25, 4);


INSERT INTO discount
(Discount_Name,Percent_Off,Amount_Off)
values('Employee', 15, NULL);

INSERT INTO discount
(Discount_Name,Percent_Off,Amount_Off)
values('Lunch Special Medium', NULL, 1);

INSERT INTO discount
(Discount_Name,Percent_Off,Amount_Off)
values('Lunch Special Large', NULL, 2);

INSERT INTO discount
(Discount_Name,Percent_Off,Amount_Off)
values('Specialty Pizza', NULL, 1.50);

INSERT INTO discount
(Discount_Name,Percent_Off,Amount_Off)
values('Happy Hour', 10, NULL);

INSERT INTO discount
(Discount_Name,Percent_Off,Amount_Off)
values('Gameday Special', 20, NULL);

-- base price

INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('Small', 'Thin', 3, 0.5);

INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('Small', 'Original', 3, 0.75);

INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('Small', 'Pan', 3.5, 1);

INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('Small', 'Gluten-Free', 4, 2);

INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('Medium', 'Thin', 5, 1);

INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('Medium', 'Original', 5, 1.5);

INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('Medium', 'Pan', 6, 2.25);

INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('Medium', 'Gluten-Free', 6.25, 3);


INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('Large', 'Thin', 8, 1.25);

INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('Large', 'Original', 8, 2);


INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('Large', 'Pan', 9, 3);


INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('Large', 'Gluten-Free', 9.5, 4);

INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('XLarge', 'Thin', 10, 2);


INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('XLarge', 'Original', 10, 3);

INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('XLarge', 'Pan', 11.5, 4.5);


INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('XLarge', 'Gluten-Free', 12.5, 6);



INSERT INTO `order`
(Ord_Date, Ord_Time, Ord_Type, Ord_Price, Ord_Cost)
values ('2024-03-05', '12:03', 'dine-in', 20.75, 3.68);

INSERT INTO pizza 
(Pizza_Cost, Pizza_Price, Pizza_State, BP_ID, Ord_ID)
VALUES (3.68, 20.75, 'completed', 9, 1);

INSERT INTO pizza_discount 
VALUES (3,1);

INSERT INTO dine_in
 VALUES (1, 21);



INSERT INTO pizza_topping 
values (1, 1 , False);


INSERT INTO pizza_topping 
values (1, 2 , False);


INSERT INTO pizza_topping 
values (1, 12 , True);






INSERT INTO `order`
(Ord_Date, Ord_Time, Ord_Type, Ord_Price, Ord_Cost)
values ('2024-04-03', '12:05', 'dine-in', 19.78, 4.63);

INSERT INTO pizza 
(Pizza_Cost, Pizza_Price, Pizza_State, BP_ID, Ord_ID)
VALUES ( 3.23, 12.85, 'completed', 7, 2);

INSERT INTO pizza_discount VALUES (2,2);
INSERT INTO pizza_discount VALUES (4,2);


INSERT INTO pizza_topping 
values (2, 14 , False);

INSERT INTO pizza_topping 
values (2, 8 , False);

INSERT INTO pizza_topping 
values (2, 6, False);

INSERT INTO pizza_topping 
values (2, 7, False);

INSERT INTO pizza_topping 
values (2, 11 , False);


INSERT INTO pizza 
(Pizza_Cost, Pizza_Price, Pizza_State, BP_ID, Ord_ID)
VALUES (1.40, 6.93, 'completed', 2, 2);

INSERT INTO pizza_topping 
values (3, 12, False);

INSERT INTO pizza_topping 
values (3, 3 , False);

INSERT INTO pizza_topping 
values (3, 11 , False);

INSERT INTO dine_in VALUES 
(2, 4);




INSERT INTO `order`
(Ord_Date, Ord_Time, Ord_Type, Ord_Price, Ord_Cost)
values ('2024-03-03', '9:30', 'Pick-Up', 89.28, 19.8);


INSERT INTO pizza 
(Pizza_Cost, Pizza_Price, Pizza_State, BP_ID, Ord_ID)
VALUES (3.30, 14.88, 'completed', 10, 3);

INSERT INTO pizza_topping 
values (4, 12, False);

INSERT INTO pizza_topping 
values (4, 1 , False);




INSERT INTO customer 
(Cus_Fname, Cus_Lname, Cus_PhoneNumber, Cus_Email_Id, 
Cus_Street_Address, Cus_State, Cus_ZipCode)
VALUES ('Andrew', 'Wilkes-Krier', 8642545861, null,
 '115 Party Blvd', 'SC', '29621');
 
 INSERT INTO customer 
(Cus_Fname, Cus_Lname, Cus_PhoneNumber, Cus_Email_Id, 
Cus_Street_Address, Cus_State, Cus_ZipCode)
VALUES ('Matt', 'Engers', 8644749953, null,
 null, null, null);
 
 INSERT INTO customer 
(Cus_Fname, Cus_Lname, Cus_PhoneNumber, Cus_Email_Id, 
Cus_Street_Address,  Cus_State, Cus_ZipCode)
VALUES ('Frank', 'Turner', 8642328944, null,
 '6745 Wessex St Anderson' ,'SC' , '29621');
 
 
 INSERT INTO customer 
(Cus_Fname, Cus_Lname, Cus_PhoneNumber, Cus_Email_Id, 
Cus_Street_Address, Cus_State, Cus_ZipCode)
VALUES ('Milo', 'Auckerman', 8648785679, null,
 '8879 Suburban Home, Anderson,' ,'SC' , '29621');
 
 
INSERT INTO pick_up 
VALUES (3,1);

INSERT INTO pizza 
(Pizza_Cost, Pizza_Price, Pizza_State, BP_ID, Ord_ID)
VALUES (3.30, 14.88, 'completed', 10, 3);


INSERT INTO pizza_topping 
values (5, 12, False);

INSERT INTO pizza_topping 
values (5, 1 , False);

INSERT INTO pizza 
(Pizza_Cost, Pizza_Price, Pizza_State, BP_ID, Ord_ID)
VALUES (3.30, 14.88, 'completed', 10, 3);


INSERT INTO pizza_topping 
values (6, 12, False);

INSERT INTO pizza_topping 
values (6, 1 , False);


INSERT INTO pizza 
(Pizza_Cost, Pizza_Price, Pizza_State, BP_ID, Ord_ID)
VALUES (3.30, 14.88, 'completed', 10, 3);


INSERT INTO pizza_topping 
values (7, 12, False);

INSERT INTO pizza_topping 
values (7, 1 , False);

INSERT INTO pizza 
(Pizza_Cost, Pizza_Price, Pizza_State, BP_ID, Ord_ID)
VALUES (3.30, 14.88, 'completed', 10, 3);


INSERT INTO pizza_topping 
values (8, 12, False);

INSERT INTO pizza_topping 
values (8, 1 , False);

INSERT INTO pizza 
(Pizza_Cost, Pizza_Price, Pizza_State, BP_ID, Ord_ID)
VALUES (3.30, 14.88, 'completed', 10, 3);


INSERT INTO pizza_topping 
values (9, 12, False);

INSERT INTO pizza_topping 
values (9, 1 , False);



INSERT INTO `order`
(Ord_Date, Ord_Time, Ord_Type, Ord_Price, Ord_Cost)
values ('2024-04-20', '7:11', 'delivery', 86.19, 23.62);


INSERT INTO pizza 
(Pizza_Cost, Pizza_Price, Pizza_State, BP_ID, Ord_ID)
VALUES (9.19, 27.94, 'completed', 14, 4);


INSERT INTO pizza_topping 
values (10, 1 , False);


INSERT INTO pizza_topping 
values (10, 2 , False);


INSERT INTO pizza_topping 
values (10, 13 , False);

INSERT INTO pizza 
(Pizza_Cost, Pizza_Price, Pizza_State, BP_ID, Ord_ID)
VALUES (6.25, 31.50, 'completed', 14, 4);


INSERT INTO pizza_topping 
values (11, 17 , True);

INSERT INTO pizza_discount VALUES(4,11);


INSERT INTO pizza_topping 
values (11, 9 , True);




INSERT INTO pizza_topping 
values (11, 13 , False);


INSERT INTO pizza 
(Pizza_Cost, Pizza_Price, Pizza_State, BP_ID, Ord_ID)
VALUES (8.18, 26.75, 'completed', 14, 4);


INSERT INTO pizza_topping 
values (12, 3 , False);


INSERT INTO pizza_topping 
values (12, 16 , False);


INSERT INTO pizza_topping 
values (12, 13 , False);

INSERT INTO delivery
VALUES (4,1);

INSERT INTO order_discount VALUES(6,4);




INSERT INTO `order`
(Ord_Date, Ord_Time, Ord_Type, Ord_Price, Ord_Cost)
values ('2024-03-02', '5:30', 'Pick-Up', 27.45, 7.88);

INSERT INTO pizza 
(Pizza_Cost, Pizza_Price, Pizza_State, BP_ID, Ord_ID)
VALUES (7.88, 27.45, 'completed', 16, 5);

INSERT INTO pizza_topping 
values (13, 4 , False);

INSERT INTO pizza_topping 
values (13, 5 , False);

INSERT INTO pizza_topping 
values (13, 6 , False);

INSERT INTO pizza_topping 
values (13, 7 , False);

INSERT INTO pizza_topping 
values (13, 8 , False);

INSERT INTO pizza_topping 
values (13, 15 , False);


INSERT INTO pick_up 
VALUES (5,2);

INSERT INTO pizza_discount VALUES (4,13);

INSERT INTO `order`
(Ord_Date, Ord_Time, Ord_Type, Ord_Price, Ord_Cost)
values ('2024-03-02', '6:17', 'delivery', 20.81, 3.19);

INSERT INTO pizza 
(Pizza_Cost, Pizza_Price, Pizza_State, BP_ID, Ord_ID)
VALUES (3.19, 20.81, 'completed', 9, 6);

INSERT INTO pizza_topping 
values (14, 3 , False);

INSERT INTO pizza_topping 
values (14, 4 , False);

INSERT INTO pizza_topping 
values (14, 5 , False);

INSERT INTO pizza_topping 
values (14, 7 , False);

INSERT INTO pizza_topping 
values (14, 13 , True);


INSERT INTO delivery
VALUES (6,3);




INSERT INTO `order`
(Ord_Date, Ord_Time, Ord_Type, Ord_Price, Ord_Cost)
values ('2024-04-13', '8:32', 'delivery', 32.25, 5.25);

INSERT INTO pizza 
(Pizza_Cost, Pizza_Price, Pizza_State, BP_ID, Ord_ID)
VALUES (2, 13, 'completed', 9, 7);

INSERT INTO pizza_topping 
values (15, 13 , True);


INSERT INTO pizza 
(Pizza_Cost, Pizza_Price, Pizza_State, BP_ID, Ord_ID)
VALUES (3.25, 19.25, 'completed', 9, 7);



INSERT INTO pizza_topping 
values (16, 12 , False);

INSERT INTO pizza_topping 
values (16, 1, True);

INSERT INTO delivery
VALUES (7,4);

INSERT INTO order_discount VALUES (1,7);







 