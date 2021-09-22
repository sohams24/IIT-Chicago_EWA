//Script to load csv file into Neo4j and generate Nodes and Edges
LOAD CSV WITH HEADERS FROM
'file:///transactions.csv' AS line
WITH line
MERGE (customer:Customer {id:line.loginId,name: line.Customer_Name,age:line.Customer_Age,occupation:line.Customer_Occupation})
MERGE (product:Product {id:line.Product_ID,name: line.Product_Name})
MERGE (manufacturer:Manufacturer {name:line.Manufacturer})
MERGE (category:Category {name:line.Category})
MERGE (order:Order {id:line.Order_ID, order_date:line.Order_Date, exp_del_date: line.Expected_Delivery_Date})
CREATE (delivery:Delivery {id:line.Delivery_Tracking_ID, type: line.Delivery_Type, date: line.Actual_Delivery_Date, returned:line.Order_Returned, on_time:line.Order_Delivered_on_Time})
MERGE (zip:ZipCode {id:line.Delivery_Zip_Code})

MERGE (customer)-[:PLACED]->(order)
CREATE (order)-[:FOR]->(product)
MERGE (product)-[:BELONGS_TO]->(category)
MERGE (product)-[:MANUFACTURED_BY]->(manufacturer)
CREATE (delivery)-[:FOR]->(product)
CREATE (delivery)-[:FOR]->(order)
CREATE (customer)-[d:TO]->(delivery)
CREATE (delivery)-[d1:TO]->(zip)
CREATE (delivery)-[t:TO]->(customer)

SET d.rating =  TOINTEGER(line.Review_Rating)
SET d.returned = CASE line.Order_Returned WHEN "1" THEN true ELSE false END
SET d1.on_time = CASE line.Order_Delivered_on_Time WHEN "1" THEN true ELSE false END
SET t.disputed = CASE line.Transaction_Status WHEN "0" THEN true ELSE false END
;


/////////////////////////Queries//////////////////////////////

//Query 1
MATCH (del:Delivery)-[d:TO]->(ZipCode)
WHERE d.on_time = true
WITH ZipCode, count(*) AS `Late Deliveries`
RETURN ZipCode.id AS `ZipCode`,`Late Deliveries`

//Query 2
MATCH (d:Delivery)-[t:TO]->(Customer)
WHERE t.disputed = true
WITH d AS `Delivery`, Customer AS `Customer`
MATCH (Delivery)-[:TO]->(ZipCode)
RETURN ZipCode.id AS `Zip`, count(Delivery) AS `number_of_disputed_transactions`,collect(Customer.id) AS `Customers`

//Query 3
MATCH (d:Delivery)-[t:TO]->(Customer)
WHERE t.disputed = true
WITH Customer, count(*) AS number_of_disputed_transactions
WHERE number_of_disputed_transactions > 0
RETURN Customer.name AS Customer, number_of_disputed_transactions

//Query 4
MATCH (c:Customer)-[d:TO]->(Delivery)
WHERE d.returned = true
WITH c, count(*) AS number_of_returned_orders
WHERE number_of_returned_orders > 0
WITH c, number_of_returned_orders
ORDER BY number_of_returned_orders DESC LIMIT 3
RETURN c.name AS Customer, number_of_returned_orders

//Query 5
MATCH (d1:Delivery)-[:TO]->(ZipCode)
MATCH (Customer)-[d:TO]->(d1)
WHERE d.returned = true AND d.rating = 1
MATCH (d1)-[:FOR]->(p1:Product)
MATCH (p1)-[:BELONGS_TO]->(Category)
RETURN ZipCode.id AS `ZipCode`,Category.name AS `Category`,count(d1) AS `number_of_returned_orders`
