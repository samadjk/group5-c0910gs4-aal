
--DB creation
CREATE DATABASE AutoAncillariesLimited
GO

USE AutoAncillariesLimited
GO

--Table creation
CREATE TABLE Account
(
	LoginName varchar(30) PRIMARY KEY NOT NULL,
	Password varchar(30) NOT NULL
	
)
GO

CREATE TABLE Customer
(
	CustomerId int PRIMARY KEY IDENTITY,
	LoginName varchar(30) FOREIGN KEY REFERENCES Account(LoginName),
	CustomerName varchar(30) NOT NULL,
	DOB datetime NOT NULL,
	[Address] varchar(50) NOT NULL,
	Email varchar(50) NOT NULL,
	City varchar(20) NOT NULL,
	Cell varchar(11) NOT NULL,
	BankName varchar(20) NOT NULL,
	BankAccNo varchar(16) NOT NULL
)
GO


CREATE TABLE Company
(
	CompanyId int PRIMARY KEY IDENTITY,
	CompanyName varchar(20) NOT NULL,
	[Address] varchar(50) NOT NULL,
	Cell varchar(11) NOT NULL
)
GO

CREATE TABLE Factory(
	FactoryID int PRIMARY KEY IDENTITY,
	CompanyID int FOREIGN KEY REFERENCES Company(CompanyId),
	FactoryName nvarchar(50) NOT NULL,
	Description nvarchar(500) NOT NULL
)
GO

CREATE TABLE Warehouse
(
	WarehouseId int PRIMARY KEY IDENTITY,
	FactoryID int FOREIGN KEY REFERENCES Factory(FactoryID),
	CompanyId int FOREIGN KEY REFERENCES Company(CompanyId),
	WarehouseName varchar(20) NOT NULL,
	Location varchar(30) NOT NULL
)
GO

CREATE TABLE Category(
	CategoryID int PRIMARY KEY IDENTITY,
	WareHouseID int FOREIGN KEY REFERENCES Warehouse(WarehouseId),
	CategoryName nvarchar(50) NOT NULL,
	Description nvarchar(500) NULL
)


CREATE TABLE BrandVehicle(
	BrandID int primary key,
	BrandVehiclename nvarchar(50) NOT NULL,
)
GO

CREATE TABLE Products
(
	ProductId int PRIMARY KEY IDENTITY,
	CategoryID int FOREIGN KEY REFERENCES Category(CategoryID),
	BrandID int FOREIGN KEY REFERENCES BrandVehicle(BrandID),
	WarehouseId int FOREIGN KEY REFERENCES Warehouse(WarehouseID),
	ProductName varchar(30) NOT NULL,
	Quantity int NOT NULL,
	Description varchar(100) NOT NULL,
	Picture varchar(20),
	Feature varchar(100),
	Price int NOT NULL,
	Rating varchar(15)
)
GO

CREATE TABLE Ship
(
	ShipId int PRIMARY KEY IDENTITY,
	ShipVia varchar(30) NOT NULL,
	ShipPrice money
)
GO

CREATE TABLE OrderDetail
(
	OrderId int PRIMARY KEY IDENTITY,
	CustomerId int FOREIGN KEY REFERENCES Customer(CustomerId),
	ProductId int FOREIGN KEY REFERENCES Products(ProductId),
	ShipId int FOREIGN KEY REFERENCES Ship(ShipId),
	Total money
)
GO







--- Alter ----

ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_Products_Category] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Category] ([CategoryID])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_Products_Category]
GO

ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_Products_Company] FOREIGN KEY([BrandID])
REFERENCES [dbo].[BrandVehicle] ([BrandID])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_Products_Company]
GO

ALTER TABLE [dbo].[Category]  WITH CHECK ADD  CONSTRAINT [FK_Category_WareHouse] FOREIGN KEY([WareHouseID])
REFERENCES [dbo].[WareHouse] ([WareHouseID])
GO
ALTER TABLE [dbo].[Category] CHECK CONSTRAINT [FK_Category_WareHouse]
GO

ALTER TABLE [dbo].[Factory]  WITH CHECK ADD  CONSTRAINT [FK_Factory_Company] FOREIGN KEY([CompanyID])
REFERENCES [dbo].[Company] ([CompanyID])
GO
ALTER TABLE [dbo].[Factory] CHECK CONSTRAINT [FK_Factory_Company]
GO

ALTER TABLE [dbo].[WareHouse]  WITH CHECK ADD  CONSTRAINT [FK_WareHouse_Factory] FOREIGN KEY([FactoryID])
REFERENCES [dbo].[Factory] ([FactoryID])
GO
ALTER TABLE [dbo].[WareHouse] CHECK CONSTRAINT [FK_WareHouse_Factory]


--Insert values (All table except Customer n Order)
INSERT INTO Account
VALUES('admin','123')
GO

INSERT INTO Account
VALUES('hieudq','hieudq')
GO

INSERT INTO Account
VALUES('trongnq','trongnq')
GO

INSERT INTO Account
VALUES('baonq','baonq')
GO

--------------------------------------
INSERT INTO Company
VALUES('Aptech','hanoi','09123456789')
GO

INSERT INTO Company
VALUES('Sky','halong','0933323899')
GO

INSERT INTO Company
VALUES('Vietnamcar','haiphong','033333333')
GO

-----------------------------------
INSERT INTO Factory
VALUES(1,'HHN','asdasdasdasdsa')
GO

-----------------------------------


INSERT INTO Warehouse
VALUES(1,1,'Warehouse1','Vietnam')
GO

INSERT INTO Warehouse
VALUES(1,1,'Warehouse2','NewZealand')
GO

INSERT INTO Warehouse
VALUES(1,1,'Warehouse3','India')
GO

INSERT INTO Warehouse
VALUES(1,1,'Warehouse4','England')
GO
-----------------------------------
INSERT INTO Category
VALUES(1,'Driver','sssssss')
GO

INSERT INTO Category
VALUES(1,'Hate','aaaaaaaaaaa')
GO




-----------------------------------
INSERT INTO BrandVehicle
VALUES(1,'Audi')
GO



------------------------------------------
INSERT INTO Products
VALUES(2,1,1,'Mercedes C200','15','The old model of Mercedes','','Best 4- wheeler car','30000','5 stars')
GO

INSERT INTO Products
VALUES(2,1,1,'Mercedes E200','9','One choose of Mercedes series','','Best 4- wheeler car','24000','4.5 stars')
GO

INSERT INTO Products
VALUES(3,1,1,'BMW x6','2','The best car series 2009','','Very good, comfortable','52000','5 stars')
GO

INSERT INTO Products
VALUES(3,1,3,'Mercedes E500','11','Model- classic','','Some boss used it to ride','11200','5 stars')
GO

INSERT INTO Products
VALUES(2,1,3,'CBR6','3','The best 2-wheeler car','','Using it to riding','105000','4 stars')
GO

INSERT INTO Products
VALUES(2,1,3,'Harleys','1','The golden best motorbike','','Very good, very good','11500','5 stars')
GO

INSERT INTO Products
VALUES(3,1,3,'Sky car','1','The car Sky uses to make the first place','','Light speed, very comfortable','13000','5 stars')
GO

INSERT INTO Products
VALUES(2,1,4,'House','1','Placed at 143 Chua Hang Street','','266m2, cool to live there','300000','4 stars')
GO

------------------------------------------
INSERT INTO Ship
VALUES('Ground','15')
GO

INSERT INTO Ship
VALUES('Air','20')
GO

INSERT INTO Ship
VALUES('Other','25')
GO