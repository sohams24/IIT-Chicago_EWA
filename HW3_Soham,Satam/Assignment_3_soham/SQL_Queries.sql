CREATE DATABASE BestDealSQL;

USE BestDealSQL;

CREATE TABLE Registration(
	userName VARCHAR(40) NOT NULL,
    password VARCHAR(40) NOT NULL,
    userType VARCHAR(40) NOT NULL,
    street VARCHAR(40),
	city VARCHAR(40),
	state VARCHAR(40),
	zip VARCHAR(40),
    PRIMARY KEY(userName)
);

CREATE TABLE Stores(
	storeId VARCHAR(40) NOT NULL,
    street VARCHAR(40) NOT NULL,
    city VARCHAR(40) NOT NULL,
    state VARCHAR(40) NOT NULL,
    zip VARCHAR(40) NOT NULL,
    PRIMARY KEY(storeId)
);

CREATE TABLE ProductDetails(
	productId VARCHAR(40) NOT NULL,
    productName VARCHAR(255) NOT NULL,
    productType VARCHAR(40) NOT NULL,
    productPrice DOUBLE NOT NULL,
    productImage VARCHAR(40),
    productManufacturer VARCHAR(40) NOT NULL,
    productCondition VARCHAR(40) NOT NULL,
    productDiscount DOUBLE,
    productRebate DOUBLE,
    PRIMARY KEY(productId)
);

CREATE TABLE ProductAccessories(
	productId varchar(20),
    accessoryId  varchar(20),
    PRIMARY KEY(productId,accessoryId),
    FOREIGN KEY(productId) REFERENCES ProductDetails(productId),
    FOREIGN KEY(accessoryId) REFERENCES ProductDetails(productId)
);

CREATE TABLE CustomerOrders(
	orderId INTEGER NOT NULL,
	userName VARCHAR(40) NOT NULL,
	productId VARCHAR(40) NOT NULL,
	orderPrice DOUBLE,
    discount DOUBLE,
    finalPrice DOUBLE,
    quantity INTEGER DEFAULT 1,
    totalCost DOUBLE,
    rebate DOUBLE,
    finalCost DOUBLE,
    creditCardNo VARCHAR(40),
    pickup BOOLEAN DEFAULT false,
    shippingCost DOUBLE,
    orderDate DATE NOT NULL,
    receiveDate DATE NOT NULL,
    street VARCHAR(40),
    city VARCHAR(40),
    state VARCHAR(40),
    zip VARCHAR(40),
    storeId VARCHAR(40),
	PRIMARY KEY(OrderId,userName,productId),
    FOREIGN KEY(userName) REFERENCES registration(userName) ON UPDATE CASCADE ON DELETE NO ACTION,
    FOREIGN KEY(storeId) REFERENCES Stores(storeId) ON UPDATE CASCADE ON DELETE NO ACTION
);