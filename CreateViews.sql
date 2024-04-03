CREATE VIEW ToppingPopularity AS (SELECT T_Name, sum(ToppingCount) AS ToppingCount from  
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
ORDER BY ToppingCount DESC);





