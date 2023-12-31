/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Sammy Guergachi <sguergachi at gmail.com>
 * Created: Nov 10, 2022
 */
CREATE DATABASE QL_BAN_GIAY_ADIDAS Drop database QL_BAN_GIAY_ADIDAS
USE QL_BAN_GIAY_ADIDAS
GO
-- ChucVu
CREATE TABLE ChucVu(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(50) DEFAULT NULL
)
GO
-- CuaHang
CREATE TABLE CuaHang(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(50) DEFAULT NULL,
DiaChi NVARCHAR(100) DEFAULT NULL,
ThanhPho NVARCHAR(50) DEFAULT NULL
)
GO
-- NhanVien
CREATE TABLE NhanVien(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30) DEFAULT NULL,
TenDem NVARCHAR(30) DEFAULT NULL,
Ho NVARCHAR(30) DEFAULT NULL,
GioiTinh NVARCHAR(10) DEFAULT NULL,
NgaySinh DATE DEFAULT NULL,
DiaChi NVARCHAR(100) DEFAULT NULL,
Sdt VARCHAR(30) DEFAULT NULL,
MatKhau VARCHAR(MAX) DEFAULT NULL,
IdCH UNIQUEIDENTIFIER,
IdCV UNIQUEIDENTIFIER,
TrangThai INT DEFAULT 0
)
GO
 
-- KhachHang
CREATE TABLE KhachHang(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
TenDem NVARCHAR(30) DEFAULT NULL,
Ho NVARCHAR(30) DEFAULT NULL,
NgaySinh DATE DEFAULT NULL,
Sdt VARCHAR(30) DEFAULT NULL,
DiaChi NVARCHAR(100) DEFAULT NULL,
MatKhau VARCHAR(MAX) DEFAULT NULL
)
GO
--HoaDon

CREATE TABLE GioHang(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
IdKH UNIQUEIDENTIFIER,
IdNV UNIQUEIDENTIFIER,
Ma VARCHAR(20) UNIQUE,
NgayTao DATE DEFAULT NULL,
NgayThanhToan DATE DEFAULT NULL,
DiaChi NVARCHAR(100) DEFAULT NULL,
Sdt_KH VARCHAR(30) DEFAULT NULL,
TinhTrang INT DEFAULT 0
)
GO
-- SanPham
CREATE TABLE SanPham(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
TrangThai INT DEFAULT 0,
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL
)
GO
-- Size
CREATE TABLE Size(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Size NVARCHAR(30),
TrangThai INT DEFAULT 0,
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL
)
GO
-- MauSac
CREATE TABLE MauSac(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
TrangThai INT DEFAULT 0,
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL
)
GO
-- LoaiSP
CREATE TABLE LoaiSP(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
TrangThai INT DEFAULT 0,
NgayTao DATE DEFAULT NULL,
NgaySua DATE DEFAULT NULL
)
GO

-- Khuyen Mai
CREATE TABLE KhuyenMai(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
MaKM VARCHAR (20) UNIQUE,
TenKM NVARCHAR(30),
PhanTramKM DECIMAL(20,0) DEFAULT 0,
NgayBatDau DATE NOT NULL,
NgayKetThuc DATE NOT NULL,
TrangThai INT DEFAULT 0
)
GO
-- DoiTra
CREATE TABLE DoiTra (
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
MaDT VARCHAR (20) UNIQUE,
TenDT NVARCHAR (30),
NgayMua DATE,
MoTa NVARCHAR (MAX),
TrangThai INT DEFAULT 0
)
-- ChiTietSP
CREATE TABLE ChiTietSP(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
IdSP UNIQUEIDENTIFIER,
IdSize UNIQUEIDENTIFIER,
IdMauSac UNIQUEIDENTIFIER,
IdLoaiSP UNIQUEIDENTIFIER,
AnhSP IMAGE NULL,
NamBH INT DEFAULT NULL,
MoTa NVARCHAR(50) DEFAULT NULL,
SoLuongTon INT,
GiaNhap DECIMAL(20,0) DEFAULT 0,
GiaBan DECIMAL(20,0) DEFAULT 0,
)

GO
-- Hoa Don
CREATE TABLE HoaDon(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
IdKH UNIQUEIDENTIFIER,
IdNV UNIQUEIDENTIFIER,
Ma VARCHAR(20) UNIQUE,
TenNV VARCHAR (50) NULL,
NgayTao DATE DEFAULT NULL,
NgayThanhToan DATE DEFAULT NULL,
GiamGia DECIMAL(20,0) DEFAULT 0,
TinhTrang INT DEFAULT 0,
IdKM UNIQUEIDENTIFIER,
IdDT UNIQUEIDENTIFIER,
DiaChi NVARCHAR(100) DEFAULT NULL,
Sdt_KH VARCHAR(30) DEFAULT NULL,
)
GO
-- HoaDonChiTiet
CREATE TABLE HoaDonChiTiet(
IdHoaDon UNIQUEIDENTIFIER,
IdChiTietSP UNIQUEIDENTIFIER,
SoLuong INT,
DonGia DECIMAL(20,0) DEFAULT 0,
CONSTRAINT PK_HoaDonCT PRIMARY KEY (IdHoaDon,IdChiTietSP),
CONSTRAINT FK1 FOREIGN KEY(IdHoaDon) REFERENCES HoaDon(Id),
CONSTRAINT FK2 FOREIGN KEY(IdChiTietSP) REFERENCES ChiTietSP(Id),
)
GO
CREATE TABLE GioHangChiTiet(
IdGioHang UNIQUEIDENTIFIER,
IdChiTietSP UNIQUEIDENTIFIER,
SoLuong INT,
DonGia DECIMAL(20,0) DEFAULT 0,
DonGiaKhiGiam DECIMAL(20,0) DEFAULT 0,
CONSTRAINT PK_GioHangCT PRIMARY KEY (IdGioHang,IdChiTietSP),
CONSTRAINT FK1_IdGioHang FOREIGN KEY(IdGioHang) REFERENCES GioHang(Id),
CONSTRAINT FK2_IdChiTietSP FOREIGN KEY(IdChiTietSP) REFERENCES ChiTietSP(Id),
)


--TẠO QUAN HỆ GIỮA CÁC BẢNG
--NhanVien - CuaHang
ALTER TABLE NhanVien ADD FOREIGN KEY (IdCH) REFERENCES CuaHang(Id)
--NhanVien - ChucVu
ALTER TABLE NhanVien ADD FOREIGN KEY (IdCV) REFERENCES ChucVu(Id)
-- HoaDon - KhachHang
ALTER TABLE HoaDon ADD FOREIGN KEY (IdKH) REFERENCES KhachHang(Id)
-- GioHang - KhachHang
ALTER TABLE GioHang ADD FOREIGN KEY (IdKH) REFERENCES KhachHang(Id)
-- HoaDon - NhanVien
ALTER TABLE HoaDon ADD FOREIGN KEY (IdNV) REFERENCES NhanVien(Id)
-- ChiTietSP - SanPham
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdSP) REFERENCES SanPham(Id)
-- ChiTietSP - Size
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdSize) REFERENCES Size(Id)
-- ChiTietSP - MauSac
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdMauSac) REFERENCES MauSac(Id)
-- ChiTietSP - LoaiSP
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdLoaiSP) REFERENCES LoaiSP(Id)
-- HoaDon - KhuyenMai
ALTER TABLE HoaDon ADD FOREIGN KEY (IdKM) REFERENCES KhuyenMai(Id)
-- HoaDon - DoiTra
ALTER TABLE HoaDon ADD FOREIGN KEY (IdDT) REFERENCES DoiTra(Id)

