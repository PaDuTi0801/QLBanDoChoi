CREATE DATABASE QLBANDOCHOI
GO
USE QLBANDOCHOI
GO


CREATE TABLE NhanVien (
    MaNV VARCHAR(5) PRIMARY KEY,
    TenNV NVARCHAR(255),
	 Email VARCHAR(100),
	  SoDienThoai VARCHAR(10),
    DiaChi NVARCHAR(255),
	MatKhau NVARCHAR(255),
	Quyen NVARCHAR(50),
	Isactive BIT DEFAULT 1
);
CREATE TABLE MauSac (
    MaMau INT PRIMARY KEY,
    TenMau VARCHAR(255),
    MoTa NVARCHAR(255),
	Isactive BIT DEFAULT 1
);
CREATE TABLE LoaiSanPham (
    MaLoai INT PRIMARY KEY,
    TenLoai NVARCHAR(255),
    MoTa NVARCHAR(255),
	Isactive BIT DEFAULT 1
);

CREATE TABLE XuatXu (
    MaXuatXu INT PRIMARY KEY,
    TenQuocGia NVARCHAR(255),
    MoTa NVARCHAR(255),
	Isactive BIT DEFAULT 1
);
CREATE TABLE SanPham (
    MaSP INT PRIMARY KEY,
    TenSP NVARCHAR(255),
    GiaNhap MONEY,
	GiaBan MONEY,
    SoLuongTon INT,
	MaMau INT,
	MaLoai INT,
	MaXuatXu INT,
	MaVach VARCHAR(10),
	Hinh NVARCHAR(255),
	Isactive BIT DEFAULT 1,
	  FOREIGN KEY (MaMau) REFERENCES MauSac(MaMau),
    FOREIGN KEY (MaLoai) REFERENCES LoaiSanPham(MaLoai),
	   FOREIGN KEY (MaXuatXu) REFERENCES XuatXu(MaXuatXu),
);

CREATE TABLE KhachHang (
    MaKH INT PRIMARY KEY,
    TenKH NVARCHAR(255),
	 SoDienThoai VARCHAR(10),
    DiaChi NVARCHAR(255),
   Isactive BIT DEFAULT 1
);
CREATE TABLE HoaDon (
    MaHD INT PRIMARY KEY,
    MaNV VARCHAR(5),
    MaKH INT,
    NgayLap DATETIME,
	ThanhToan BIT DEFAULT 0,
    TongTien MONEY,
	LyDoHuyDon NVARCHAR(255),
	Isactive BIT DEFAULT 1
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH)
);

CREATE TABLE HoaDonChiTiet (
	MaHDCT INT PRIMARY KEY,
    MaHD INT,
    MaSP INT,
    SoLuong INT,
    DonGia MONEY,
	Isactive BIT DEFAULT 1
);

CREATE TABLE LichSuThaoTac (
    MaLSTT INT PRIMARY KEY,
    MaNV VARCHAR(5),
    ThoiGian DATETIME,
    ThaoTac NVARCHAR(255),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
);
INSERT INTO NhanVien (MaNV, TenNV, Email, SoDienThoai, DiaChi, MatKhau,Quyen) 
VALUES ('NV001', N'Nguyễn Văn A', 'nva@example.com', '0123456789', N'123 Đường ABC, Quận XYZ, Thành phố HCM', 'password123','QuanLy'),
       ('NV002', N'Trần Thị B', 'ttb@example.com', '0987654321', N'456 Đường DEF, Quận UVW, Thành phố HCM', 'abc123','NhanVien');

INSERT INTO MauSac (MaMau, TenMau, MoTa)
VALUES (1, N'Đỏ', N'Màu đỏ rực rỡ'),
       (2, N'Xanh', N'Màu xanh dương mát mắt');

INSERT INTO LoaiSanPham (MaLoai, TenLoai, MoTa)
VALUES (1, N'Đồ chơi cho trẻ em', N'Đồ chơi dành cho trẻ em từ 3 tuổi trở lên'),
       (2, N'Đồ chơi thông minh', N'Đồ chơi kích thích trí tuệ và tư duy');
INSERT INTO XuatXu (MaXuatXu, TenQuocGia, MoTa)
VALUES (1, N'Trung Quốc', N'Hàng nhập khẩu từ Trung Quốc'),
       (2, N'Nhật Bản', N'Sản phẩm chất lượng từ Nhật Bản');
INSERT INTO SanPham (MaSP, TenSP, GiaNhap, GiaBan, SoLuongTon, MaMau, MaLoai, MaXuatXu ,MaVach, Hinh) 
VALUES (1, N'Búp bê Barbie', 100000, 150000, 50, 1, 1, 1,'8123456909', 'barbie.jpg'),
       (2, N'Rubik 3x3', 50000, 80000, 100, 2, 2, 2,'8123456908', 'rubik.jpg');
INSERT INTO KhachHang (MaKH, TenKH, SoDienThoai, DiaChi) 
VALUES (1, N'khách lẻ', '0000000000', N'789 Đường XYZ, Quận DEF, Thành phố HCM'),
       (2, N'Lê Văn D', '0987654321', N'321 Đường UVW, Quận HIJ, Thành phố HCM');
INSERT INTO HoaDon (MaHD, MaNV, MaKH, NgayLap, TongTien) 
VALUES (1, 'NV001', 1, '2024-04-19 08:00:00', 300000),
       (2, 'NV002', 2, '2024-04-18 10:30:00', 200000);
INSERT INTO HoaDonChiTiet (MaHDCT, MaHD, MaSP, SoLuong, DonGia) 
VALUES (1, 1, 1, 2, 150000),
       (2, 1, 2, 3, 240000);
INSERT INTO LichSuThaoTac (MaLSTT, MaNV, ThoiGian, ThaoTac) 
VALUES (1, 'NV001', '2024-04-19 08:15:00', N'Đăng nhập hệ thống'),
       (2, 'NV002', '2024-04-18 10:35:00', N'Thêm sản phẩm mới');



SELECT * FROM NHANVIEN WHERE ISACTIVE =1
 update nhanvien set matkhau='d404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db'