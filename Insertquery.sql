-- topping table

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

-- discount table

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
values('Small', 'Glutten-Free', 4, 2);

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
values('Medium', 'Glutem-Free', 6.25, 3);

INSERT INTO base_price
(BP_Size, BP_Crust, BP_Price, BP_Cost)
values('Small', 'Original', 3, 0.75);