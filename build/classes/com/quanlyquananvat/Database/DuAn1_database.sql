create database Du_an_1
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
MoTa nvarchar(225) not null,
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
ADD CONSTRAINT FK_HoaDon_HoaDonChiTiet FOREIGN KEY (MaHoaDon)
REFERENCES HoaDon (MaHD)
ON UPDATE CASCADE
ON DELETE NO ACTION;

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
INSERT INTO KhachHang (MaKH, TenKH, DiaChi, GioiTinh, SoDienThoai, Email, NgaySinh)
VALUES
('KH001', 'Le Minh C', '123 ABC Street', 1, '0123456789', 'leminhc@gmail.com', '1990-05-20'),
('KH002', 'Nguyen Thi D', '456 XYZ Street', 0, '0987654321', 'nguyenthid@gmail.com', '1985-08-15'),
('KH003', 'Tran Van E', '789 QWE Street', 1, '0234567891', 'tranve@gmail.com', '1988-12-10'),
('KH004', 'Hoang Thi F', '101 PQR Street', 0, '0345678912', 'hoangthi@gmail.com', '1995-02-25'),
('KH005', 'Mai Van G', '202 ZXC Street', 1, '0456789123', 'maivan@gmail.com', '1983-07-18'),
('KH006', 'Dinh Thi H', '303 ASD Street', 0, '0567891234', 'dinhthi@gmail.com', '1979-11-30'),
('KH007', 'Do Van I', '404 QAZ Street', 1, '0678912345', 'dovan@gmail.com', '1992-04-05'),
('KH008', 'Tran Thi K', '505 LKJ Street', 0, '0789123456', 'tranthi@gmail.com', '1987-09-15'),
('KH009', 'Nguyen Van L', '606 JKL Street', 1, '0891234567', 'nguyenl@gmail.com', '1980-03-08'),
('KH010', 'Le Van M', '707 WER Street', 0, '0912345678', 'levanm@gmail.com', '1998-06-12');

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

create or alter proc SP_SelectHD (@MaHD int)
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