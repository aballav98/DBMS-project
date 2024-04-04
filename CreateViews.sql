CREATE VIEW ToppingPopularity AS
((SELECT T_Name, sum(ToppingCount) AS ToppingCount from  
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
ORDER BY ToppingCount DESC));

CREATE VIEW ProfitByPizza 
AS
(SELECT * FROM
((SELECT BP_SIZE, BP_CRUST, SUM(Pizza_Price - Pizza_Cost) AS Profit, '3/2024' AS 'Order Month'
FROM base_price join pizza on base_price.BP_ID=pizza.BP_ID
join `order` on `order`.Ord_ID=pizza.Ord_ID
WHERE Month(Ord_Date)=3
group by base_price.BP_ID)
UNION
(SELECT BP_SIZE, BP_CRUST, SUM(Pizza_Price - Pizza_Cost) AS Profit, '4/2024' AS 'Order Month'
FROM base_price join pizza on base_price.BP_ID=pizza.BP_ID
join `order` on `order`.Ord_ID=pizza.Ord_ID
WHERE Month(Ord_Date)=4
group by base_price.BP_ID)) AS A
ORDER BY(Profit));

CREATE VIEW ProfitByOrderType
AS
((SELECT Ord_Type AS customerType, '3/2024' AS 'Order Month', SUM(Ord_Price) AS 'TotalOrderPrice', SUM(Ord_Cost) AS 'TotalOrderCost', SUM(Ord_Price)-SUM(Ord_Cost) AS Profit
FROM `order`
WHERE Month(Ord_Date)=3
group by Ord_Type)
union
(SELECT Ord_Type AS customerType, '4/2024' AS 'Order Month', SUM(Ord_Price) AS 'TotalOrderPrice', SUM(Ord_Cost) AS 'TotalOrderCost', SUM(Ord_Price)-SUM(Ord_Cost) AS Profit
FROM `order`
WHERE Month(Ord_Date)=4
group by Ord_Type)
union
(SELECT "" AS customerType, 'Grand Total' AS 'Order Month', SUM(TotalOrderPrice) AS TotalOrderPrice, SUM(TotalOrderCost) AS TotalOrderCost, SUM(Profit) AS Profit
FROM
((SELECT Ord_Type AS customerType, '3/2024' AS 'Order Month', SUM(Ord_Price) AS 'TotalOrderPrice', SUM(Ord_Cost) AS 'TotalOrderCost', SUM(Ord_Price)-SUM(Ord_Cost) AS Profit
FROM `order`
WHERE Month(Ord_Date)=3
group by Ord_Type)
union
(SELECT Ord_Type AS customerType, '4/2024' AS 'Order Month', SUM(Ord_Price) AS 'TotalOrderPrice', SUM(Ord_Cost) AS 'TotalOrderCost', SUM(Ord_Price)-SUM(Ord_Cost) AS Profit
FROM `order`
WHERE Month(Ord_Date)=4
group by Ord_Type)) AS A));










