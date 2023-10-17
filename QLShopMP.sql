CREATE DATABASE QLSHOPMYPHAM
USE QLSHOPMYPHAM
GO
/*
CREATE TABLE KHO(
MaKho INT PRIMARY KEY IDENTITY (1,1) NOT NULL,
SoLuong INT,
);
*/
GO

CREATE TABLE LOAISP(
MaLoai INT IDENTITY(1,1) PRIMARY KEY,
TenLoai NVARCHAR (100),
);

GO
CREATE TABLE SANPHAM(
MaSP INT PRIMARY KEY IDENTITY (1,1) NOT NULL,
TenSP NVARCHAR(255),
GiaBan FLOAT,
ThuongHieu NVARCHAR(50),
CongDung NVARCHAR(255),
ThanhPhan NVARCHAR(255),
HSD NVARCHAR(200),
Hinh NVARCHAR(255),
SoLuong INT,
MaLoai INT,
FOREIGN KEY (MaLoai) REFERENCES LOAISP(MaLoai) ON DELETE NO ACTION ON UPDATE CASCADE,
);

GO
CREATE TABLE NHANVIEN(
MaNV INT PRIMARY KEY IDENTITY (1,1),
TenNV NVARCHAR(255),
GioiTinh NVARCHAR (3),
NgaySinh DATE,
DiaChi NVARCHAR(255),
SDT NVARCHAR (11),
CCCD NVARCHAR(12),
Email NVARCHAR(255),
);

GO
CREATE TABLE ACCOUNT(
MaAccount INT IDENTITY (1,1) PRIMARY KEY,
UserName NVARCHAR(255),
Pass NVARCHAR(30),
ChucVu NVARCHAR(500),
MaNV INT,
FOREIGN KEY (MaNV) REFERENCES NHANVIEN(MaNV),
);


GO
CREATE TABLE KHACHHANG(
MaKH INT PRIMARY KEY IDENTITY (1,1),
TenKH NVARCHAR(255),
GioiTinh NVARCHAR (3),
NgaySinh DATE,
DiaChi NVARCHAR(255),
SDT NVARCHAR (11),
EMAIL NVARCHAR(255),
);

GO
CREATE TABLE DONHANG (
MaDH INT IDENTITY (1,1) PRIMARY KEY,
NgayTao DATE,
NguoiTao NVARCHAR(255),
TongTien FLOAT,
Sale FLOAT,
ThanhTien FLOAT,
TrangThai bit,
IDAccount INT,
MaKH INT,
FOREIGN KEY (IDAccount) REFERENCES ACCOUNT(MaAccount),
FOREIGN KEY (MaKH) REFERENCES KHACHHANG(MaKH) ON DELETE NO ACTION ON UPDATE CASCADE,
);

GO

GO
CREATE TABLE CTDH (
MaCTDH INT IDENTITY (1,1) PRIMARY KEY,
SL INT,
MaDH INT,
MaSP INT,
FOREIGN KEY (MaDH) REFERENCES DONHANG(MaDH),
FOREIGN KEY (MaSP) REFERENCES SANPHAM(MaSP),
);
GO
CREATE TABLE HOADON (
MaHD INT UNIQUE,
NgayTao DATE,
NguoiTao NVARCHAR(255),
TongTien FLOAT,
Sale FLOAT,
ThanhTien FLOAT,
MaKH INT,
FOREIGN KEY (MaHD) REFERENCES DONHANG(MaDH),
);




GO
CREATE TABLE PHIEUNHAP(
MaPN INT IDENTITY (1,1) PRIMARY KEY,
NgayTao DATE,
NguoiTao NVARCHAR(255),
ThanhTien FLOAT,
MaAccount INT NULL,
FOREIGN KEY (MaAccount) REFERENCES ACCOUNT(MaAccount),
);

GO
CREATE TABLE CTPN (
MaCTPN INT IDENTITY (1,1) PRIMARY KEY,
SL INT,
GiaNhap FLOAT,
MaPN INT,
MaSP INT,
FOREIGN KEY (MaPN) REFERENCES PHIEUNHAP(MaPN),
FOREIGN KEY (MaSP) REFERENCES SANPHAM(MaSP),
);
GO


--INSERT DỮ LIỆU
--KHO
SELECT * FROM KHO
INSERT INTO KHO
VALUES
(3),
(123),
(43),
(63),
(23),
(3),
(73),
(30),
(32),
(23),
(11),
(12),
(23),
(34),
(43),
(4),
(6),
(3),
(13),
(73),
(93),
(23),
(3),
(13),
(43),
(53)

--LOẠI SẢN PHẨM
SELECT * FROM LOAISP
INSERT INTO LOAISP
VALUES
(N'Trang điểm'),
(N'Chăm sóc da'),
(N'Nước hoa')

DELETE LOAISP
--SẢN PHẨM
SELECT * FROM SANPHAM
INSERT INTO SANPHAM
VALUES	
--lIST TRANG ĐIỂM
(N'SON ROUGE PUR COUTURE THE SLIM 2',1200000,N'YSL',N'dưỡng ẩm, trị thâm, làm sáng đều màu môi.',N'tinh dầu jojoba, tinh dầu Macadamia bơ và nhiều loại vitamin.  Son không chì dưỡng môi tốt, hợp với da nhạy cảm.',N'2 năm kể từ ngày sản xuất',N'SON ROUGE PUR COUTURE THE SLIM 2.jpg',1,1),
(N'SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL',1200000,N'YSL',N'dưỡng ẩm, trị thâm, làm sáng đều màu môi.',N'tinh dầu jojoba, tinh dầu Macadamia bơ và nhiều loại vitamin.  Son không chì dưỡng môi tốt, hợp với da nhạy cảm.',N'2 năm kể từ ngày sản xuất',N'SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg',2,1),
(N'SON KEM LÌ TATOUAGE COUTURE VELVET CREAM',1200000,N'YSL',N'dưỡng ẩm, trị thâm, làm sáng đều màu môi.',N'tinh dầu jojoba, tinh dầu Macadamia bơ và nhiều loại vitamin.  Son không chì dưỡng môi tốt, hợp với da nhạy cảm.',N'2 năm kể từ ngày sản xuất',N'SON KEM LÌ TATOUAGE COUTURE VELVET CREAM.jpg',3,1),
(N'SON THỎI SATIN LÌ THE BOLD',1200000,N'YSL',N'dưỡng ẩm, trị thâm, làm sáng đều màu môi.',N'tinh dầu jojoba, tinh dầu Macadamia bơ và nhiều loại vitamin.  Son không chì dưỡng môi tốt, hợp với da nhạy cảm.',N'2 năm kể từ ngày sản xuất',N'SON THỎI SATIN LÌ THE BOLD.jpg',4,1),
(N'BẢNG MẮT COUTURE COLOR CLUTCH',3800000,N'YSL',N'Làm đẹp',NULL,N'2 năm kể từ ngày sản xuất',N'BẢNG MẮT COUTURE COLOR CLUTCH.jpg',5,1),
(N'CUSHION EDP LAMÉ COLLECTOR',1960000,N'YSL',N'Làm đẹp',NULL,N'2 năm kể từ ngày sản xuất',N'CUSHION EDP LAMÉ COLLECTOR.jpg',6,1),
(N'MASCARA MVEFC WATERPROOF',1400000,N'YSL',N'Làm đẹp',NULL,N'2 năm kể từ ngày sản xuất',N'MASCARA MVEFC WATERPROOF.jpg',7,1),
(N'PHẤN PHỦ SOUFFLE DECLAT',1900000,N'YSL',N'Làm đẹp',NULL,N'2 năm kể từ ngày sản xuất',N'PHẤN PHỦ SOUFFLE DECLAT.jpg',8,1),
-- LIST CHĂM DA
(N'KEM CHỐNG NẮNG PURE SHOTS AIRTHIN UV DEFENDER',1200000,N'YSL',N'chống các UV, SPS 50+.',NULL,N'2 năm kể từ ngày sản xuất',N'KEM CHỐNG NẮNG PURE SHOTS AIRTHIN UV DEFENDER.jpg',1,2),
(N'KEM DƯỠNG ẨM PURE SHOTS PERFECT PLUMPER',1200000,N'YSL',N'dưỡng ẩm, trị thâm, làm sáng đều màu da.',NULL,N'2 năm kể từ ngày sản xuất',N'KEM DƯỠNG ẨM PURE SHOTS PERFECT PLUMPER.jpg',2,2),
(N'PURE SHOTS LINES AWAY SERUM',1200000,N'YSL',N'tinh chất phục hồi da, cấp ẩm, đều màu da.',NULL,N'2 năm kể từ ngày sản xuất',N'PURE SHOTS LINES AWAY SERUM.jpg',3,2),
(N'Tinh chất chống lão hóa, tái tạo da The Ordinary “Buffet”',1200000,N'Ordinary',N'dưỡng ẩm, chống lão hóa, tái tạo da',NULL,N'2 năm kể từ ngày sản xuất',N'Tinh chất chống lão hóa, tái tạo da The Ordinary “Buffet”.jpg',4,2),
(N'Tinh chất trị mụn The Ordinary Salicylic Acid 2% Solution (BHA)',1200000,N'Ordinary',NULL,NULL,N'2 năm kể từ ngày sản xuất',N'Tinh chất trị mụn The Ordinary Salicylic Acid 2% Solution (BHA).jpg',5,2),
(N'Kem dưỡng ẩm The Ordinary',1200000,N'Ordinary',NULL,NULL,N'2 năm kể từ ngày sản xuất',N'Kem dưỡng ẩm The Ordinary Natural Moisturizing Factor + HA.jpg',6,2),
(N'Serum dưỡng tóc The Ordinary',1200000,N'Ordinary',NULL,NULL,N'2 năm kể từ ngày sản xuất',N'Serum dưỡng tóc The Ordinary.jpg',7,2),
(N'Tinh chất tẩy tế bào chết & cấp nước The Ordinary',1200000,N'Ordinary',NULL,NULL,N'2 năm kể từ ngày sản xuất',N'Tinh chất tẩy tế bào chết & cấp nước The Ordinary.jpg',8,2),
--LIST NƯỚC HOA
(N'TÊN NƯỚC HOA1',200000,N'YSL',N'Tạo mùi hương lôi cuốn',NULL,N'3 năm kể từ ngày sản xuất',N'SON ROUGE PUR COUTURE THE SLIM 2.jpg',1,3),
(N'TÊN NƯỚC HOA2',200000,N'YSL',N'Tạo mùi hương lôi cuốn',NULL,N'3 năm kể từ ngày sản xuất',N'SON ROUGE PUR COUTURE THE SLIM 2.jpg',2,3),
(N'TÊN NƯỚC HOA3',200000,N'YSL',N'Tạo mùi hương lôi cuốn',NULL,N'3 năm kể từ ngày sản xuất',N'SON ROUGE PUR COUTURE THE SLIM 2.jpg',3,3),
(N'TÊN NƯỚC HOA4',200000,N'YSL',N'Tạo mùi hương lôi cuốn',NULL,N'3 năm kể từ ngày sản xuất',N'SON ROUGE PUR COUTURE THE SLIM 2.jpg',4,3),
(N'TÊN NƯỚC HOA5',200000,N'YSL',N'Tạo mùi hương lôi cuốn',NULL,N'3 năm kể từ ngày sản xuất',N'SON ROUGE PUR COUTURE THE SLIM 2.jpg',5,3),
(N'TÊN NƯỚC HOA6',200000,N'YSL',N'Tạo mùi hương lôi cuốn',NULL,N'3 năm kể từ ngày sản xuất',N'SON ROUGE PUR COUTURE THE SLIM 2.jpg',6,3),
(N'TÊN NƯỚC HOA7',200000,N'YSL',N'Tạo mùi hương lôi cuốn',NULL,N'3 năm kể từ ngày sản xuất',N'SON ROUGE PUR COUTURE THE SLIM 2.jpg',7,3),
(N'TÊN NƯỚC HOA8',200000,N'YSL',N'Tạo mùi hương lôi cuốn',NULL,N'3 năm kể từ ngày sản xuất',N'SON ROUGE PUR COUTURE THE SLIM 2.jpg',8,3),
(N'TÊN NƯỚC HOA9',200000,N'YSL',N'Tạo mùi hương lôi cuốn',NULL,N'3 năm kể từ ngày sản xuất',N'SON ROUGE PUR COUTURE THE SLIM 2.jpg',9,3),
(N'TÊN NƯỚC HOA10',200000,N'YSL',N'Tạo mùi hương lôi cuốn',NULL,N'3 năm kể từ ngày sản xuất',N'SON ROUGE PUR COUTURE THE SLIM 2.jpg',10,3)


--KHÁCH HÀNG
SELECT * FROM KHACHHANG
INSERT INTO KHACHHANG
VALUES
(N'Phạm Trí Phúc',N'Nam','2004-11-25',N'Cà Mau',0791234212,N'phucptpc05587@fpt.edu.vn'),
(N'Dương Văn Kha',N'Nam','2004-05-28',N'Hậu Giang',0791234211,N'khadv05684@fpt.edu.vn'),
(N'Phạm Minh Đức',N'Nam','2004-11-25',N'Cà Mau',0791234213,N'ducpmpc05567@fpt.edu.vn'),
(N'Trần Ngọc Anh Thư',N'Nữ','2004-11-25',N'Cà Mau',0791234214,N'thutnapc05583@fpt.edu.vn'),
(N'Hồ Thị Trúc Ly',N'Nữ','2004-11-25',N'Cà Mau',0791234216,N'lyhttpc05588@fpt.edu.vn'),
(N'Hồ Trúc Ly',N'Nữ','2004-11-25',N'Cà Mau',0791234215,N'lytpc05587@fpt.edu.vn'),
(N'Phan Văn Khánh',N'Nam','2004-11-25',N'Cà Mau',0791234217,N'khanhpvpc04226@fpt.edu.vn'),
(N'Đặng Vĩnh Kỳ',N'Nam','2004-11-25',N'Cà Mau',0791234218,N'kydvpc05475@fpt.edu.vn'),
(N'Nguyễn Vũ Đầy',N'Nam','2004-11-25',N'Cà Mau',0791234219,N'daynvpc05484@fpt.edu.vn'),
(N'Võ Nhựt Duy',N'Nam','2004-11-25',N'Cà Mau',0791234222,N'duyvnpc05487@fpt.edu.vn'),
(N'Lê Tuấn Kiệt',N'Nam','2004-11-25',N'Cà Mau',0791234232,N'kietltpc05503@fpt.edu.vn'),
(N'Đinh Văn Phát',N'Nam','2004-11-25',N'Cà Mau',0791234242,N'phatdvpc05504@fpt.edu.vn'),
(N'Trần Văn Nghĩa',N'Nam','2004-11-25',N'Cà Mau',0791234252,N'nghiatvpc05510@fpt.edu.vn'),
(N'Trần Tô Phước Triều',N'Nam','2004-11-25',N'Cà Mau',0791234262,N'trieuttppc05514@fpt.edu.vn'),
(N'Nguyễn Minh Triệu',N'Nam','2004-11-25',N'Cà Mau',0791234272,N'trieunmpc05519@fpt.edu.vn'),
(N'Trần Nguyễn Đông Duy',N'Nam','2004-11-25',N'Cà Mau',0791234282,N'duytndpc05535@fpt.edu.vn'),
(N'Tống Bình Phú',N'Nam','2004-11-25',N'Cà Mau',0791234292,N'phutbpc05547@fpt.edu.vn'),
(N'Nguyễn Tuấn Khôi',N'Nam','2004-11-25',N'Cà Mau',0791235212,N'khointpc05548@fpt.edu.vn'),
(N'Nguyễn Minh Khanh',N'Nam','2004-11-25',N'Cà Mau',0791236212,N'khanhnmpc05586@fpt.edu.vn'),
(N'Thạch Hoài An',N'Nam','2004-11-25',N'Cà Mau',0791237212,N'anthpc05573@fpt.edu.vn')


--NHÂN VIÊN
SELECT * FROM NHANVIEN
INSERT INTO NHANVIEN
VALUES
(N'Nguyễn Thanh Hiếu',N'Nam','2003-08-11',N'Cà Mau',N'0797932451',N'092132123123',N'thanhhieu@gmail.com'),
(N'Nguyễn Minh Tâm',N'Nam','2003-08-11',N'Cà Mau',N'0797932452',N'092132123124',N'minhtam@gmail.com'),
(N'Nguyễn Trần Lượng',N'Nam','2003-08-11',N'Cà Mau',N'0797932453',N'092132123125',N'tranluong@gmail.com'),
(N'Nguyễn Khánh Tâm',N'Nam','2003-08-11',N'Cà Mau',N'0797932454',N'092132123126',N'khanhtam@gmail.com'),
(N'Nguyễn Tuấn Huy',N'Nam','2003-08-11',N'Cà Mau',N'0797932455',N'092132123127',N'tuanhuy@gmail.com')
SELECT * FROM NHANVIEN A JOIN ACCOUNT B ON A.MaNV=B.MaAccount 

--ACCOUNT
SELECT * FROM ACCOUNT
INSERT INTO ACCOUNT
VALUES
(N'ThanhHieu',N'thanhhieu123','Nhân Viên',1),
(N'Minhtam',N'minhtam123','Nhân Viên',2),
(N'TranLuong',N'tranluong123','Nhân Viên',3),
(N'KhanhTam',N'khanhtam123','Nhân Viên',4),
(N'TuanHuy',N'tuanhuy123','Nhân Viên',5)

--ĐƠN HÀNG
SELECT * FROM DONHANG
INSERT INTO DONHANG
VALUES
('2023-10-08',N'ThanhHieu',5700000,0.02,5586000,0,1,4),
('2023-10-09',N'TranLuong',5700000,0.02,5586000,0,3,1),
('2023-10-10',N'TranLuong',5700000,0.02,5586000,0,3,2),
('2023-10-11',N'ThanhHieu',5700000,0.02,5586000,0,1,3),
('2023-10-12',N'TuanHuy',5700000,0.02,5586000,0,5,5),
('2023-10-13',N'TuanHuy',5700000,0.02,5586000,0,5,4)
-- PHIẾU NHẬP

--CHI TIẾT ĐƠN HÀNG
SELECT * FROM CTDH
INSERT INTO CTDH
VALUES
(1,1,1),
(1,1,3),
(1,1,6),
(1,1,7),
(1,2,1),
(1,2,3),
(1,2,6),
(1,2,7),
(1,3,1),
(1,3,3),
(1,3,6),
(1,3,7),
(1,4,1),
(1,4,3),
(1,4,6),
(1,4,7),
(1,5,1),
(1,5,3),
(1,5,6),
(1,5,7),
(1,6,1),
(1,6,3),
(1,6,6),
(1,6,7)

SELECT B.MaSP,A.TenSP,B.SL,A.GiaBan FROM SANPHAM A JOIN CTDH B ON A.MaSP=B.MaSP WHERE MaDH=2
-- mua hàng 
-- Thêm
--viết trc hết là insert vào bảng ĐƠN HÀNG, sau đó truy xuất đến mã đơn hàng vừa thêm, r insert (SL,MaxDH,MaSP) vào CTDH
-- Xóa
-- xóa ctdh, r xóa đơn hàng

-- HÓA ĐƠN
SELECT * FROM HOADON
INSERT INTO HOADON
VALUES
('2023-10-08',N'ThanhHieu',5700000,0.02,5586000,4),
('2023-10-09',N'TranLuong',5700000,0.02,5586000,1),
('2023-10-10',N'TranLuong',5700000,0.02,5586000,2),
('2023-10-11',N'ThanhHieu',5700000,0.02,5586000,3),
('2023-10-12',N'TuanHuy',5700000,0.02,5586000,5),
('2023-10-13',N'TuanHuy',5700000,0.02,5586000,4)
-- PHIẾU NHẬP
SELECT * FROM PHIEUNHAP
INSERT INTO PHIEUNHAP
VALUES
('2023-10-08',N'ThanhHieu',5586000,1),
('2023-11-08',N'TranLuong',5586000,3),
('2023-12-08',N'TranLuong',5586000,3),
('2023-1-08',N'Minhtam',5586000,2),
('2023-2-08',N'TuanHuy',5586000,5),
('2023-3-08',N'TuanHuy',5586000,5),
('2023-4-08',N'ThanhHieu',5586000,1),
('2023-5-08',N'KhanhTam',5586000,4),
('2023-6-08',N'KhanhTam',5586000,4),
('2023-8-08',N'Minhtam',5586000,2)

--CHI TIẾT PHIẾU NHẬP
SELECT * FROM CTPN
INSERT INTO CTPN
VALUES
(1,1100000,1,1),
(1,1100000,1,3),
(1,1800000,1,6),
(1,1300000,1,7),
(1,1100000,2,1),
(1,1100000,2,3),
(1,1800000,2,6),
(1,1300000,2,7),
(1,1100000,3,1),
(1,1100000,3,3),
(1,1800000,3,6),
(1,1300000,3,7),
(1,1100000,4,1),
(1,1100000,4,3),
(1,1800000,4,6),
(1,1300000,4,7),
(1,1100000,5,1),
(1,1100000,5,3),
(1,1800000,5,6),
(1,1300000,5,7),
(1,1100000,6,1),
(1,1100000,6,3),
(1,1800000,6,6),
(1,1300000,6,7),
(1,1100000,7,1),
(1,1100000,7,3),
(1,1800000,7,6),
(1,1300000,7,7),
(1,1100000,8,1),
(1,1100000,8,3),
(1,1800000,8,6),
(1,1300000,8,7),
(1,1100000,9,1),
(1,1100000,9,3),
(1,1800000,9,6),
(1,1300000,9,7),
(1,1100000,10,1),
(1,1100000,10,3),
(1,1800000,10,6),
(1,1300000,10,7)

SELECT A.MaSP,B.TenSP,A.SL,A.GiaNhap,A.MaPN FROM CTPN A JOIN SANPHAM B ON A.MaSP=B.MaSP
ORDER BY A.MaPN

select * from SANPHAM
---Kho
SELECT A.TenSP,A.GiaBan,B.SoLuong,A.Hinh FROM SANPHAM A JOIN KHO B ON  A.MaSP=B.MaKho
--delete sản phẩm
INSERT INTO SANPHAM
VALUES	
(N'test',1900000,N'YSL',N'Làm đẹp',NULL,N'2 năm kể từ ngày sản xuất',N'PHẤN PHỦ SOUFFLE DECLAT.jpg',8,1)

delete SANPHAM WHERE MaSP=1002
--
SELECT * FROM ACCOUNT
--
SELECT A.MaSP,A.TenSP,A.GiaBan,A.ThuongHieu,A.CongDung,A.ThanhPhan,A.HSD,A.Hinh,B.SoLuong,A.MaLoai 
FROM SANPHAM A JOIN KHO B ON A.MaKho =B.MaKho

SELECT MaAccount,UserName,Pass,ChucVu,MaNV FROM ACCOUNT
UPDATE SANPHAM SET SoLuong=52 WHERE MaSP=1


CREATE TABLE DONHANG (
MaDH INT IDENTITY (1,1) PRIMARY KEY,
NgayTao DATE,
NguoiTao NVARCHAR(255),
TongTien FLOAT,
Sale FLOAT,
ThanhTien FLOAT,
IDAccount INT,
FOREIGN KEY (IDAccount) REFERENCES ACCOUNT(MaAccount),
);

GO

GO

CREATE TABLE CTDH (
MaCTDH INT IDENTITY (1,1) PRIMARY KEY,
SL INT,
MaDH INT,
MaSP INT,
FOREIGN KEY (MaDH) REFERENCES DONHANG(MaDH),
FOREIGN KEY (MaSP) REFERENCES SANPHAM(MaSP),
);

SELECT * FROM DONHANG
SELECT * FROM CTDH
-- insert thêm dô thôi k cần cũng được
INSERT INTO DONHANG
VALUES
(GETDATE(),'KhanhTam',24000000,0,24000000,0,4,10),
(GETDATE(),'TuanHuy',24000000,0,24000000,0,5,11),
(GETDATE(),'TranLuong',24000000,0,24000000,0,3,11),
(GETDATE(),'KhanhTam',24000000,0,24000000,0,4,12),
(GETDATE(),'TuanHuy',24000000,0,24000000,0,5,13),
(GETDATE(),'KhanhTam',24000000,0,24000000,0,4,14),
(GETDATE(),'TuanHuy',24000000,0,24000000,0,5,15),
(GETDATE(),'TranLuong',24000000,0,24000000,0,3,16),
(GETDATE(),'KhanhTam',24000000,0,24000000,0,4,17),
(GETDATE(),'TuanHuy',24000000,0,24000000,0,5,18)
