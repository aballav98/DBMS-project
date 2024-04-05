CREATE VIEW ToppingPopularity AS SELECT T_Name, sum(ToppingCount) AS ToppingCount from  
((SELECT 
T_NAME, 
COUNT(T_NAME)*2 AS ToppingCount
FROM pizza_topping pt
JOIN topping t ON pt.T_ID = t.T_ID
WHERE pt.T_ID IS NOT NULL AND pt.Extra_Topping = True
GROUP BY(T_NAME))

UNION

(SELECT 
T_NAME, 
COUNT(T_NAME) AS ToppingCount
FROM pizza_topping pt
JOIN topping t ON pt.T_ID = t.T_ID
WHERE pt.T_ID IS NOT NULL AND pt.Extra_Topping = False
GROUP BY(T_NAME))
UNION 
(SELECT 
T_NAME, 
0 AS ToppingCount
FROM pizza_topping pt
RIGHT OUTER JOIN topping t ON pt.T_ID = t.T_ID
WHERE pt.T_ID IS NULL 
GROUP BY(T_NAME))) AS A 
GROUP BY T_Name
ORDER BY ToppingCount DESC;

CREATE VIEW ProfitByPizza as SELECT BP_Size AS Size, BP_Crust AS Crust, ROUND(SUM(Pizza_Price) - SUM(Pizza_Cost) - SUM(Discount),2) as Profit,date_format(max(OM),'%m/%Y') AS 'Order Month'
FROM
(SELECT pizza.BP_ID,BP_Size,max(`order`.Ord_Date) as OM, BP_Crust,Pizza_Cost,Pizza_Price, SUM(IF(a.Amount_Off is not null, a.Amount_Off, 0)+IF(b.Percent_off is not null, Pizza_Price*(b.Percent_off)/100, 0)) AS Discount
FROM base_price join pizza on base_price.BP_ID=pizza.BP_ID
LEFT OUTER JOIN pizza_discount ON pizza_discount.Pizza_ID = pizza.Pizza_ID
LEFT OUTER JOIN discount as a on a.Discount_ID = pizza_discount.Discount_ID
JOIN `order` on `order`.Ord_ID = pizza.Ord_ID
LEFT OUTER JOIN order_discount ON `order`.Ord_ID = order_discount.Ord_ID
LEFT OUTER JOIN discount as b on b.Discount_ID = order_discount.Discount_ID
GROUP BY pizza.Pizza_ID) AS DERIVED
GROUP BY BP_ID
Order By Profit;

CREATE VIEW ProfitByOrderType as SELECT Ord_Type AS CustomerType,date_format(max(OM),'%m/%Y') AS 'Order Month', ROUND(SUM(Pizza_Price-Discount),2) AS TotalOrderPrice, Round(SUM(Pizza_Cost),2) AS TotalOrderCost, ROUND(SUM(Pizza_Price-Discount) - SUM(Pizza_Cost),2) AS Profit
FROM
(SELECT  Ord_Type,Ord_Date, Pizza_Cost,Pizza_Price,max(`order`.Ord_Date) as OM, SUM(IF(a.Amount_Off is not null, a.Amount_Off, 0)+IF(b.Percent_off is not null, Pizza_Price*(b.Percent_off)/100, 0)) AS Discount
FROM base_price join pizza on base_price.BP_ID=pizza.BP_ID
LEFT OUTER JOIN pizza_discount ON pizza_discount.Pizza_ID = pizza.Pizza_ID
LEFT OUTER JOIN discount as a on a.Discount_ID = pizza_discount.Discount_ID
JOIN `order` on `order`.Ord_ID = pizza.Ord_ID
LEFT OUTER JOIN order_discount ON `order`.Ord_ID = order_discount.Ord_ID
LEFT OUTER JOIN discount as b on b.Discount_ID = order_discount.Discount_ID
GROUP BY pizza.Pizza_ID) AS DERIVED
GROUP BY Ord_Type
UNION
SELECT "" AS customerType, 'Grand Total' AS 'Order Month', SUM(TotalOrderPrice) AS TotalOrderPrice, SUM(TotalOrderCost) AS TotalOrderCost, SUM(Profit) AS Profit
FROM
(SELECT Ord_Type AS Customer_Type,date_format(max(OM),'%m/%Y') AS 'Order Month', ROUND(SUM(Pizza_Price-Discount),2) AS TotalOrderPrice, Round(SUM(Pizza_Cost),2) AS TotalOrderCost, ROUND(SUM(Pizza_Price-Discount) - SUM(Pizza_Cost),2) AS Profit
FROM
(SELECT  Ord_Type,Ord_Date, Pizza_Cost,Pizza_Price,max(`order`.Ord_Date) as OM, SUM(IF(a.Amount_Off is not null, a.Amount_Off, 0)+IF(b.Percent_off is not null, Pizza_Price*(b.Percent_off)/100, 0)) AS Discount
FROM base_price join pizza on base_price.BP_ID=pizza.BP_ID
LEFT OUTER JOIN pizza_discount ON pizza_discount.Pizza_ID = pizza.Pizza_ID
LEFT OUTER JOIN discount as a on a.Discount_ID = pizza_discount.Discount_ID
JOIN `order` on `order`.Ord_ID = pizza.Ord_ID
LEFT OUTER JOIN order_discount ON `order`.Ord_ID = order_discount.Ord_ID
LEFT OUTER JOIN discount as b on b.Discount_ID = order_discount.Discount_ID
GROUP BY pizza.Pizza_ID) AS D
GROUP BY Ord_Type) AS M;