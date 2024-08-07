﻿	create database Du_an_1
use Du_an_1
-- tạo bảng nhân viên
go
create table NhanVien(
MaNV varchar(10) primary key,
TenNV nvarchar(50) not null,
MatKhau nvarchar(20) not null,
SoDienThoai varchar(12) not null,
HinhAnh varchar(225) not null,
VaiTro bit not null
)
go
-- tạo bảng khách hàng
create table KhachHang(
MaKH varchar(10) primary key,
TenKH nvarchar(50) not null,
DiaChi nvarchar(225) not null,
GioiTinh bit not null,
SoDienThoai varchar(12) not null,
Email varchar(50) not null,
NgaySinh date not null,
HinhAnh nvarchar(225) not null
)
go
-- tạo bảng phương thức thanh toán
create table PhuongThucThanhToan(
MaThanhToan varchar(10) primary key,
TenPTTT nvarchar(50) not null,
)
go
--tạo bảng hóa đơn
create table HoaDon(
MaHD int identity (1,1) primary key,
MaKH varchar(10) null,
MaNV varchar(10) null,
NgayTao date default getdate(),
PhiGiaoNhanh float null,
TongTien float null,
diaChi nvarchar(100) null,
MaThanhToan varchar(10) null
)
go
--tạo bảng hóa đơn chi tiết 
create table HoaDonChiTiet(
MaHDCT int identity(1,1) primary key,
MaHoaDon int not null,
MaSP varchar(10) not null,
TenSanPham nvarchar(50) not null,
SoLuong int not null,
Gia float not null,
TongTien float not null
)
go
-- tạo bảng loại sản phẩm
create table LoaiSanPham(
MaLoaiSanPham varchar(10) primary key,
TenLoaiSanPham nvarchar(50) not null,
)
go
--tạo bảng sản phẩm
create table SanPham(
MaSP varchar(10) primary key,
TenSP nvarchar(50) not null,
SoLuong int not null,
GiaSP float not null,
NgayNhap date not null,
MoTa nvarchar(225)  null,
HinhAnh varchar(225) not null,
MaLoaiSanPham varchar(10) not null
)

-- Thêm khóa ngoại với UPDATE CASCADE và DELETE NO ACTION cho bảng HoaDon
ALTER TABLE HoaDon
ADD CONSTRAINT FK_NhanVien_HoaDon FOREIGN KEY (MaNV)
REFERENCES NhanVien (MaNV)
ON UPDATE CASCADE
ON DELETE NO ACTION;

ALTER TABLE HoaDon
ADD CONSTRAINT FK_KhachHang_HoaDon FOREIGN KEY (MaKH)
REFERENCES KhachHang (MaKH)
ON UPDATE CASCADE
ON DELETE NO ACTION;

ALTER TABLE HoaDon
ADD CONSTRAINT FK_PhuongThucThanhToan_HoaDon FOREIGN KEY (MaThanhToan)
REFERENCES PhuongThucThanhToan (MaThanhToan)
ON UPDATE CASCADE
ON DELETE NO ACTION;

-- Thêm khóa ngoại với UPDATE CASCADE và DELETE NO ACTION cho bảng HoaDonChiTiet
ALTER TABLE HoaDonChiTiet
ADD CONSTRAINT FK_HoaDon_HoaDonChiTiet
FOREIGN KEY (MaHoaDon)
REFERENCES HoaDon(MaHD)
ON DELETE CASCADE;


ALTER TABLE HoaDonChiTiet
ADD CONSTRAINT FK_SanPham_HoaDonChiTiet FOREIGN KEY (MaSP)
REFERENCES SanPham (MaSP)
ON UPDATE CASCADE
ON DELETE NO ACTION;

-- Thêm khóa ngoại với NO ACTION cho bảng SanPham
ALTER TABLE SanPham
ADD CONSTRAINT FK_LoaiSanPham_SanPham FOREIGN KEY (MaLoaiSanPham)
REFERENCES LoaiSanPham (MaLoaiSanPham)
ON DELETE NO ACTION;


-- Xóa bảng Hóa Đơn Chi Tiết
DROP TABLE HoaDonChiTiet;

-- Xóa bảng Hóa Đơn
DROP TABLE HoaDon;

-- Xóa bảng Sản Phẩm
DROP TABLE SanPham;

-- Xóa bảng Loại Sản Phẩm
DROP TABLE LoaiSanPham;

-- Xóa bảng Phương Thức Thanh Toán
DROP TABLE PhuongThucThanhToan;

-- Xóa bảng Khách Hàng
DROP TABLE KhachHang;

-- Xóa bảng Nhân Viên
DROP TABLE NhanVien;

---------------------------------------------------------------------------------------------------------------------------
-- Thêm dữ liệu cho bảng NhanVien
INSERT INTO NhanVien (MaNV, TenNV, MatKhau, SoDienThoai, HinhAnh, VaiTro)
VALUES
('NV001', 'Nguyen Van A', 'password123', '0123456789', 'avatar1.jpg', 1),
('NV002', 'Tran Thi B', 'pass456', '0987654321', 'avatar2.jpg', 0),
('NV003', 'Le Van C', 'abc123', '0234567891', 'avatar3.jpg', 1),
('NV004', 'Pham Thi D', 'xyz789', '0345678912', 'avatar4.jpg', 0),
('NV005', 'Hoang Van E', 'pass456', '0456789123', 'avatar5.jpg', 1),
('NV006', 'Mai Thi F', 'abcdef', '0567891234', 'avatar6.jpg', 0),
('NV007', 'Dinh Van G', '123456', '0678912345', 'avatar7.jpg', 1),
('NV008', 'Do Thi H', 'xyz123', '0789123456', 'avatar8.jpg', 0),
('NV009', 'Tran Van I', 'abc789', '0891234567', 'avatar9.jpg', 1),
('NV010', 'Nguyen Thi K', '123abc', '0912345678', 'avatar10.jpg', 0);

-- Thêm dữ liệu cho bảng KhachHang
INSERT INTO KhachHang (MaKH, TenKH, DiaChi, GioiTinh, SoDienThoai, Email, NgaySinh, HinhAnh)
VALUES 
('KH001', 'Nguyen Van A', '123 ABC Street, Quan 1, TP.HCM', 1, '0123456789', 'nguyenvana@email.com', '1990-01-15', 'avatar1.jpg'),

('KH002', 'Tran Thi B', '456 XYZ Street, Quan 2, TP.HCM', 0, '0987654321', 'tranthib@email.com', '1985-05-20', 'avatar2.jpg'),

('KH003', 'Le Van C', '789 DEF Street, Quan 3, TP.HCM', 1, '0345678901', 'levanc@email.com', '2000-09-10', 'avatar3.jpg'),

('KH004', 'Pham Thi D', '101 GHI Street, Quan 4, TP.HCM', 0, '0765432109', 'phamthid@email.com', '1998-03-25', 'avatar4.jpg'),

('KH005', 'Vo Van E', '202 JKL Street, Quan 5, TP.HCM', 1, '0234567890', 'vovane@email.com', '1992-11-12', 'avatar5.jpg'),

('KH006', 'Nguyen Thi F', '303 MNO Street, Quan 6, TP.HCM', 0, '0456789012', 'nguyenthif@email.com', '1987-07-05', 'avatar6.jpg'),

('KH007', 'Tran Van G', '404 PQR Street, Quan 7, TP.HCM', 1, '0678901234', 'tranvang@email.com', '1995-02-18', 'avatar7.jpg'),

('KH008', 'Le Thi H', '505 STU Street, Quan 8, TP.HCM', 0, '0890123456', 'lethih@email.com', '1980-12-30', 'avatar8.jpg'),

('KH009', 'Pham Van I', '606 VWX Street, Quan 9, TP.HCM', 1, '0987654321', 'phamvani@email.com', '1993-06-08', 'avatar9.jpg'),

('KH010', 'Vo Thi K', '707 YZA Street, Quan 10, TP.HCM', 0, '0123456789', 'vothik@email.com', '1983-04-02', 'avatar10.jpg');

-- Thêm dữ liệu cho bảng PhuongThucThanhToan
INSERT INTO PhuongThucThanhToan (MaThanhToan, TenPTTT)
VALUES
('TT001', N'Tiền mặt'),
('TT002', N'Chuyển khoản');

-- Thêm dữ liệu cho bảng HoaDon
INSERT INTO HoaDon (MaKH, MaNV, PhiGiaoNhanh,TongTien,DiaChi, MaThanhToan)
VALUES
('KH001', 'NV001', 10.5,30, N'Cần thơ','TT001'),
('KH002', 'NV002', 15.2,40, N'Thành phố Hồ Chí Minh','TT002'),
('KH003', 'NV003', 8.0,43.4,N'Hà nội','TT001'),
('KH004', 'NV004', 12.3,53,N'Kiên giang' ,'TT002'),
('KH005', 'NV005', 5.5, 23,N'Vĩnh Long','TT001'),
('KH006', 'NV006', 20.0, 53,N'Sóc Trăng','TT002'),
('KH007', 'NV007', 18.7, 63,N'Thanh Hóa','TT001'),
('KH008', 'NV008', 14.9,73,N'Trà Vinh', 'TT002'),
('KH009', 'NV009', 9.2, 33,N'Hải phòng','TT001'),
('KH010', 'NV010', 11.0,53,N'Đắc nông', 'TT002');

-- Thêm dữ liệu cho bảng HoaDonChiTiet
INSERT INTO HoaDonChiTiet (MaHoaDon, MaSP, TenSanPham, SoLuong, Gia, TongTien)
VALUES
(1, 'SP001', N'Bánh tráng trộn', 2, 5.5, 11),
(1, 'SP002', N'Nước ngọt', 3, 1.8, 5.4)
-- Thêm các dòng còn lại tương tự

-- Thêm dữ liệu cho bảng LoaiSanPham
INSERT INTO LoaiSanPham (MaLoaiSanPham, TenLoaiSanPham)
VALUES
('LSP001', N'Đồ ăn vặt'),
('LSP002', N'Đồ uống');

-- Thêm dữ liệu cho bảng SanPham
INSERT INTO SanPham (MaSP, TenSP, SoLuong, GiaSP, NgayNhap, MoTa, HinhAnh, MaLoaiSanPham)
VALUES
('SP001', N'Bánh tráng trộn', 100, 2.5, '2018-03-10', N'Bánh tráng trộn cay nồng', 'banhtrangtron.jpg', 'LSP001'),
('SP002', N'Nước ngọt', 200, 0.6, '2017-05-15', N'Nước ngọt lê', 'nuocngot.jpg', 'LSP002')
-- Thêm các dòng còn lại tương tự
-- Thêm dữ liệu cho bảng HoaDonChiTiet (tiếp theo)
INSERT INTO HoaDonChiTiet (MaHoaDon, MaSP, TenSanPham, SoLuong, Gia, TongTien)
VALUES
(2, 'SP003', N'Bánh mì que', 1, 2.0, 2.0),
(2, 'SP004', N'Khoai tây chiên', 2, 3.5, 7.0),
(3, 'SP001', N'Bánh tráng trộn', 1, 5.5, 5.5),
(3, 'SP005', N'Trà sữa', 1, 3.0, 3.0),
(4, 'SP006', N'Bánh bao', 3, 2.0, 6.0),
(4, 'SP007', N'Coca Cola', 2, 1.5, 3.0),
(5, 'SP008', N'Bánh flan', 1, 4.0, 4.0),
(5, 'SP009', N'Pepsi', 2, 1.8, 3.6),
(6, 'SP010', N'Hamburger', 1, 6.0, 6.0),
(6, 'SP011', N'Sinh tố dâu', 2, 4.0, 8.0);

-- Thêm dữ liệu cho bảng LoaiSanPham (tiếp theo)
INSERT INTO LoaiSanPham (MaLoaiSanPham, TenLoaiSanPham)
VALUES
('LSP003', N'Kẹo'),
('LSP004', N'Mì gói');

-- Thêm dữ liệu cho bảng SanPham (tiếp theo)
INSERT INTO SanPham (MaSP, TenSP, SoLuong, GiaSP, NgayNhap, MoTa, HinhAnh, MaLoaiSanPham)
VALUES
('SP003', N'Bánh mì que', 50, 1.8, '2019-08-25', N'Bánh mì que giòn tan', 'banhmi.jpg', 'LSP003'),
('SP004', N'Khoai tây chiên', 30, 2.5, '2020-01-15', N'Khoai tây chiên ngon', 'khoaitay.jpg', 'LSP004'),
('SP005', N'Trà sữa', 40, 3.2, '2018-11-10', N'Trà sữa thơm ngon', 'trasua.jpg', 'LSP002'),
('SP006', N'Bánh bao', 20, 1.5, '2021-03-02', N'Bánh bao nhân thịt', 'banhbao.jpg', 'LSP001'),
('SP007', N'Coca Cola', 60, 1.0, '2019-05-20', N'Nước ngọt Coca Cola', 'cocacola.jpg', 'LSP002'),
('SP008', N'Bánh flan', 25, 3.5, '2022-07-12', N'Bánh flan thạch', 'banhflan.jpg', 'LSP001'),
('SP009', N'Pepsi', 35, 1.2, '2020-09-08', N'Nước ngọt Pepsi', 'pepsi.jpg', 'LSP002'),
('SP010', N'Hamburger', 15, 5.0, '2019-12-05', N'Bánh hamburger thịt bò', 'hamburger.jpg', 'LSP004'),
('SP011', N'Sinh tố dâu', 28, 4.2, '2023-02-18', N'Sinh tố dâu tươi', 'sinhtodau.jpg', 'LSP002');


create or alter proc SP_SelectHD1 (@MaHD int)
as
begin 
select HoaDon.MaHD as MaHD,
NgayTao as ngayTao,
PhiGiaoNhanh as phiVanChuyen,
TenSanPham as tenSanPham,
SoLuong as soLuong,
Gia as giaSP,
hoadon.TongTien as tongTien
from HoaDon 
inner join HoaDonChiTiet on HoaDon.MaHD = HoaDonChiTiet.MaHoaDon 
where hoadon.MaHD = @MaHD
end

go
create or alter proc SP_DoanhThu1(@Nam nvarchar(20))
as
begin 
select 
HoaDonChiTiet.TenSanPham as tenSanPham,
count(*) as soLuongSanPham,
SUM(HoaDon.TongTien) as tongDoanhThu,
MAX(HoaDon.TongTien) as DoanhThuCao,
MIN(HoaDon.TongTien) as DoanhThuIt,
AVG(HoaDon.TongTien) as DoanhThuTB
from SanPham
inner join HoaDonChiTiet on SanPham.MaSP = HoaDonChiTiet.MaSP
inner join HoaDon on HoaDon.MaHD = HoaDonChiTiet.MaHoaDon
where YEAR(NgayTao) = @Nam 
Group by HoaDonChiTiet.TenSanPham
end

go
create or alter proc SP_TongDoanhThuSanPham1
as
begin
	SELECT 
	lsp.TenLoaiSanPham AS tenLoai,
	SUM(hdct.SoLuong) AS TongSoLuongBan
	FROM HoaDonChiTiet hdct
	JOIN SanPham sp ON hdct.MaSP = sp.MaSP
	JOIN LoaiSanPham lsp ON sp.MaLoaiSanPham = lsp.MaLoaiSanPham
	GROUP BY lsp.TenLoaiSanPham
	ORDER BY TongSoLuongBan DESC;
end

go
create or alter proc sp_tonKho 
as
begin 
SELECT
    SanPham.TenSP AS TenSanPham,
    SanPham.SoLuong AS SoLuongTonKho,
    HoaDonChiTiet.TenSanPham AS TenSanPhamHoaDon,
    COUNT(*) AS SoLuongBan,
    SUM(HoaDon.TongTien) AS DoanhThu
FROM
    SanPham
JOIN
    HoaDonChiTiet ON SanPham.MaSP = HoaDonChiTiet.MaSP
JOIN
    HoaDon ON HoaDon.MaHD = HoaDonChiTiet.MaHoaDon
GROUP BY
    SanPham.TenSP,
    SanPham.SoLuong,
    HoaDonChiTiet.TenSanPham;

end

exec sp_tonKho

create or alter proc sp_tongDoanhThuSanPham
as
begin
    SELECT  
        SP.TenSP AS tenSP,
        SUM(HDCT.SoLuong) AS SoLuongBanRa,
        SP.SoLuong AS SoLuongTonKho,
        AVG(HDCT.Gia) AS GiaBanTrungBinh,
        SUM(HDCT.SoLuong * HDCT.Gia) AS TongDoanhThu 
    FROM SanPham SP
    INNER JOIN HoaDonChiTiet HDCT 
        ON SP.MaSP = HDCT.MaSP
    GROUP BY    
        SP.TenSP,
        SP.SoLuong
end

exec sp_tongDoanhThuSanPham


CREATE OR ALTER PROCEDURE ThongKeHoaDon
AS
BEGIN
   SELECT
    CONVERT(date, HoaDon.NgayTao) AS NgayTao,
    COUNT(HoaDon.MaHD) AS SoLuongHoaDon,
    SUM(HoaDon.TongTien) AS TongDoanhThu
	FROM
	  HoaDon
	GROUP BY
	 CONVERT(date, HoaDon.NgayTao)
	ORDER BY
	 CONVERT(date, HoaDon.NgayTao);
END;


exec ThongKeHoaDon

create or alter proc psp_PhuongThucThanhToan_tienMat
as
begin
SELECT PhuongThucThanhToan.TenPTTT AS tenPTTT, 
COUNT(HoaDon.MaHD) AS SoLuongHoaDon,
SUM(TongTien) AS TongDoanhThu
FROM PhuongThucThanhToan
LEFT JOIN HoaDon ON PhuongThucThanhToan.MaThanhToan = HoaDon.MaThanhToan
where PhuongThucThanhToan.MaThanhToan = 'TT001'
GROUP BY PhuongThucThanhToan.TenPTTT;
end

exec psp_PhuongThucThanhToan_tienMat

create or alter proc psp_PhuongThucThanhToan_ChuyenKhoan
as
begin
SELECT PhuongThucThanhToan.TenPTTT AS tenPTTT, 
COUNT(HoaDon.MaHD) AS SoLuongHoaDon,
SUM(TongTien) AS TongDoanhThu
FROM PhuongThucThanhToan
LEFT JOIN HoaDon ON PhuongThucThanhToan.MaThanhToan = HoaDon.MaThanhToan
where PhuongThucThanhToan.MaThanhToan = 'TT002'
GROUP BY PhuongThucThanhToan.TenPTTT;
end

exec psp_PhuongThucThanhToan_ChuyenKhoan

create or alter proc sp_tkTienMat
as
begin
SELECT MaNV AS manv,
MaKH AS makh, 
COUNT(HoaDon.MaHD) AS SoLuongHoaDon,
SUM(HoaDon.TongTien) AS TongDoanhThu
FROM PhuongThucThanhToan
INNER JOIN HoaDon ON HoaDon.MaThanhToan = PhuongThucThanhToan.MaThanhToan
INNER JOIN HoaDonChiTiet ON HoaDon.MaHD = HoaDonChiTiet.MaHoaDon
where PhuongThucThanhToan.MaThanhToan = 'TT001'
GROUP BY MaNV, MaKH;

end

create or alter proc sp_tkChuyenKhoan
as
begin
SELECT MaNV AS manv,
MaKH AS makh, 
COUNT(HoaDon.MaHD) AS SoLuongHoaDon,
SUM(HoaDon.TongTien) AS TongDoanhThu
FROM PhuongThucThanhToan
INNER JOIN HoaDon ON HoaDon.MaThanhToan = PhuongThucThanhToan.MaThanhToan
INNER JOIN HoaDonChiTiet ON HoaDon.MaHD = HoaDonChiTiet.MaHoaDon
where PhuongThucThanhToan.MaThanhToan = 'TT002'
GROUP BY MaNV, MaKH;

end

exec sp_tkChuyenKhoan

create or alter proc sp_hoaDonTheoKhachHangh
as 
begin
SELECT
    KhachHang.TenKH as tenkhachhang,
    SanPham.TenSP as tensanpham,
    COUNT(HoaDon.MaHD) AS SoLuongHoaDon,
    SUM(HoaDon.TongTien) AS TongDoanhThu
FROM
    HoaDon
INNER JOIN HoaDonChiTiet ON HoaDon.MaHD = HoaDonChiTiet.MaHoaDon
INNER JOIN KhachHang ON KhachHang.MaKH = HoaDon.MaKH
INNER JOIN SanPham ON SanPham.MaSP = HoaDonChiTiet.MaSP
GROUP BY
    KhachHang.TenKH,
    SanPham.TenSP;

end

exec sp_hoaDonTheoKhachHangh

create or alter proc sp_doanhThuSanPham
as
begin
SELECT
    SanPham.TenSP as tensp,
    COUNT(HoaDon.MaHD) AS SoLuongHoaDon,
    SUM(HoaDonChiTiet.TongTien) AS TongDoanhThu,
	AVG(HoaDon.TongTien) as doanhThuTB
FROM
    SanPham
INNER JOIN HoaDonChiTiet ON SanPham.MaSP = HoaDonChiTiet.MaSP
INNER JOIN HoaDon ON HoaDonChiTiet.MaHoaDon = HoaDon.MaHD
WHERE
    HoaDon.MaThanhToan IS NOT NULL
GROUP BY
    SanPham.TenSP ;

end

exec sp_doanhThuSanPham

create or alter proc sp_sanPhamTonKho
as
begin
SELECT
    SanPham.TenSP,
    SanPham.SoLuong AS SoLuongTonKho,
    LoaiSanPham.TenLoaiSanPham,
    SanPham.GiaSP
FROM
    SanPham
INNER JOIN
    LoaiSanPham ON SanPham.MaLoaiSanPham = LoaiSanPham.MaLoaiSanPham;
end

exec sp_sanPhamTonKho

-- Create a stored procedure
CREATE OR ALTER PROCEDURE GetProductSalesInfo
AS
BEGIN
    SELECT 
        SanPham.TenSP as tenSP,
        SanPham.SoLuong as soLuong,
        HoaDon.NgayTao as ngayTao,
        SUM(HoaDonChiTiet.SoLuong) as SoLuongDaBan
    FROM 
        HoaDonChiTiet
    JOIN 
        SanPham ON HoaDonChiTiet.MaSP = SanPham.MaSP
    JOIN 
        HoaDon ON HoaDonChiTiet.MaHoaDon = HoaDon.MaHD
    GROUP BY  
        SanPham.TenSP,
        HoaDon.NgayTao,
        SanPham.SoLuong;
END;

exec GetProductSalesInfo