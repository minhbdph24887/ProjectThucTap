create database ProjectDB
use ProjectDB
go

-- bang khuyen mai
create table Promotions(
	ID bigint identity(1, 1) primary key,
	Code varchar(50) not null, --Ma Khuyen Mai
	Name nvarchar(100) not null,
	Persen decimal(20, 3) not null, --Phan Tram Giam
	Quantity int not null,
	StartDate date not null, --Ngay Bat Dau
	EndDate date not null, --Ngay Ket Thuc
	Description nvarchar(100) not null, --Mo Ta
	Status int not null, --Trang Thai; Tồn Tại Là 1, Hết Hạn Là 0, Sắp Ra Mắt Là 2 
)
go

--Khuyen Mai San Pham
create table PromotionsProduct(
	Id bigint identity(1, 1) primary key,
	PromotionsID bigint not null, --ID cua Khuyen Mai
	ProductItemsID bigint not null, --ID cua San Pham
	Status int not null --Tồn Tại Là 1, Hết Hạn Là 0
)
go

-- Chi Tiet San Pham
create table ProductItems(
	ID bigint identity(1, 1) primary key,
	ProductID bigint not null, --ID của San Pham
	ColorID bigint not null, --ID của Mau Sac
	SizeID bigint not null, --ID của Size
	CategoryID bigint not null, --ID của Danh Muc
	Images nvarchar(50) not null, -- Anh San Pham
	AvaiableQuantity int not null, --So Luong Ton
	PurchasePrice decimal(20, 0) not null, --Gia Mua Vao
	CostPrice decimal(20, 0) not null, --Gia Ban
	Status int not null --Tồn Tại Là 1, Hết Hạn Là 0
)
go

-- Bang San Pham
create table Product(
	ID bigint identity(1, 1) primary key,
	Name nvarchar(100) not null,
	Description nvarchar(100) not null, --Mo Ta
	Status int not null --Tồn Tại Là 1, Hết Hạn Là 0
)
go

-- Bang Danh Muc
create table Category(
	ID bigint identity(1, 1) primary key,
	Name nvarchar(100) not null,
	Status int not null --Tồn Tại Là 1, Hết Hạn Là 0
)
go

-- Bang Mau Sac
create table Color(
	ID bigint identity(1, 1) primary key,
	Name nvarchar(100) not null,
	Status int not null --Tồn Tại Là 1, Hết Hạn Là 0
)
go

-- Bang Size
create table Size(
	ID bigint identity(1, 1) primary key,
	Name nvarchar(100) not null,
	Status int not null --Tồn Tại Là 1, Hết Hạn Là 0
)
go

-- Bang Nguoi Dung
create table Account(
	Username varchar(255) primary key,
	Name nvarchar(100) not null,
	NumberPhone varchar(10) not null,
	Sex bit not null, --Gioi Tinh 1 la Nam, 0 la Nu
	Birthday date not null, --Ngay Sinh
	Address nvarchar(200) not null, --Dia Chi
	Password nvarchar(25) not null, --Password
	Status int not null --Tồn Tại Là 1, Hết Hạn Là 0
)
go

-- Bang Phan Quyen
create table Authorities(
	ID bigint identity(1, 1) primary key,
	Username varchar(255) not null,
	RoleID bigint not null
)
go

-- Bang Quyen
create table Role(
	ID bigint identity(1, 1) primary key,
	Name nvarchar(100) not null,
	Status int not null --Tồn Tại Là 1, Hết Hạn Là 0
)
go

-- Bang Hoa Don
create table Bill(
	ID bigint identity(1, 1) primary key,
	Username varchar(255) not null,
	CreateDate date not null, --Ngay Tao
	DateOfPayment date not null, --Ngay Thanh Toan
	TotalAmount decimal(20, 3) not null, --Tong Tien Hoa Don
	MoneyPaidByCustomers decimal(20, 3) not null, --Tien Khach Thanh Toan
	Change decimal(20, 3) not null, --Tien Thua
	DeliveryAddress nvarchar(100) not null, --Dia Chi Giao Hang
	Note nvarchar(200) not null, --Ghi Chu
	Status int not null -- Đã Thanh Toán Là 1, Đã Hủy Là 0
)
go

-- Bang Hoa Don Chi Tiet
create table BillIems(
	ID bigint identity(1, 1) primary key,
	BillID bigint not null, --ID cua Hoa Don
	ProductItemsID bigint not null, --ID cua Chi Tiet San Pham
	Quantity int not null, --So Luong
	Price decimal(20, 3) not null, --Gia San Pham
)
go

-- Bang Gio Hang
create table Cart(
	ID bigint identity(1, 1) primary key,
	Username varchar(255) not null, --Username cua Nguoi Dung
	Description nvarchar(100) not null, --Mo Ta
	Status int not null --Tồn Tại Là 1, Hết Hạn Là 0
)
go

-- Bang Gio Hang Chi Tiet
create table CartItems(
	ID bigint identity(1, 1) primary key,
	CartID bigint not null,
	ProductItemsID bigint not null,
	Quantity int not null,
	Price decimal(20, 3) not null
)
go

-- Bang Khuyen Mai San Pham Noi Voi Bang Khuyen Mai
alter table PromotionsProduct add foreign key (PromotionsID) references Promotions(ID)
go

-- Bang Khuyen Mai San Pham Noi Voi Bang Chi Tiet San Pham
alter table PromotionsProduct add foreign key (ProductItemsID) references ProductItems(ID)
go

-- Bang Chi Tiet San Pham Noi Voi Bang San Pham
alter table ProductItems add foreign key (ProductID) references Product(ID)
go

-- Bang Chi Tiet San Pham Noi Voi Bang Mau Sac
alter table ProductItems add foreign key (ColorID) references Color(ID)
go

-- Bang Chi Tiet San Pham Noi Voi Bang Size
alter table ProductItems add foreign key (SizeID) references Size(ID)
go

-- Bang Chi Tiet San Pham Noi Voi Bang Danh Muc
alter table ProductItems add foreign key (CategoryID) references Category(ID)
go

-- Bang Tai Khoan Noi Voi Bang Phan Quyen
alter table Authorities add foreign key (Username) references Account(Username)
go

-- Bang Quyen Noi Voi Bang Phan Quyen
alter table Authorities add foreign key (RoleID) references Role(ID)
go

-- Bang Nguoi Dung Noi Voi Bang Gio Hang
alter table Cart add foreign key (Username) references Account(Username)
go

-- Bang Nguoi Dung Noi Voi Bang Hoa Don
alter table Bill add foreign key (Username) references Account(Username)
go

-- Bang Gio Hang Noi Voi Bang Gio Hang Chi Tiet
alter table CartItems add foreign key (CartID) references Cart(ID)
go

-- Bang Hoa Don Noi Voi Bang Hoa Don Chi Tiet
alter table BillIems add foreign key (BillID) references Bill(ID)
go

-- Bang Hoa Don Chi Tiet Noi Voi Bang Chi Tiet San Pham
alter table BillIems add foreign key (ProductItemsID) references ProductItems(ID)
go

-- Bang Gio Hang Chi Tiet Noi Voi Bang Chi Tiet San Pham
alter table CartItems add foreign key (ProductItemsID) references ProductItems(ID)
go
