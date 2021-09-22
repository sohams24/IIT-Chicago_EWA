README instruction for Assignment-3 CSP-584:


1)  Place the "Assignment_3_soham" folder in the directory "C:\apache-tomcat-7.0.34\webapps".
2)  Install MySQL db and MySQL workbench on your system.
3)  Install MongoDB version 3.2.2.
4)  Open file "Assignment_3_soham\SQL_Queries.sql" in MySQL workbench and run the script to create the tables.
5)  Create a "data" and "db" folder inside C drive as "c:\data\db".
6)  To start Mongo DB server process, locate the “mongod.exe” stored in "C:\ProgramFiles\MongoDB\Server\3.2\bin" and click it.
7)  To start Mongo shell, locate the “mongo.exe” stored in "C:\Program Files\MongoDB\Server\3.2\bin" and click it.
8)  Create a database by entering command "use bestdealmongo" in Mongo shell.
9)  Create a collection manully by entering command "db.createCollection(myReviews)" in Mongo shell.
10)  Open file "Assignment_3_soham\WEB-INF\classes\MongoDBDataStoreUtilities.java" and set the database name and collection name on line 18 and 19 respectively.
11)  Open file "Assignment_3_soham\WEB-INF\classes\MySqlDataStoreUtilities.java" and set the port number, database name, username, and password on line 15.
12) Start the TOMCAT server.
13) Make sure MySQL server and MongoDB server are running.
14) Open browser and hit URL "http://localhost/Assignment_3_soham".


