create database Du_an_1_1
use Du_an_1_1
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
MaLoaiSanPham varchar(10) not null,
MaKhoSP varchar(10) not null
)
go
create table khoSanPham(
MaKhoSP varchar(10) primary key,
TenSP nvarchar(50) not null,
SoLuong int not null,
GiaSP float not null,
NgayNhap date not null,
MoTa nvarchar(225)  null,
HinhAnh varchar(225) not null,
MaLoaiSanPham varchar(10) not null,
);
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

alter table sanPham 
add constraint fk_khoSp_sanpham foreign key (MaKhoSP) references khosanpham(MaKhoSP) ON DELETE NO ACTION;

ALTER TABLE khosanpham
ADD CONSTRAINT FK_KhoSanPham_SanPham FOREIGN KEY (MaLoaiSanPham)
REFERENCES LoaiSanPham (MaLoaiSanPham)
ON DELETE NO ACTION;

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

drop table khoSanPham

-- Thêm dữ liệu cho bảng NhanVien
INSERT INTO NhanVien (MaNV, TenNV, MatKhau, SoDienThoai, HinhAnh, VaiTro)
VALUES
('NV001', N'Nguyễn Văn A', 'password123', '0123456789', 'avatar1.jpg', 1),
('NV002', N'Trần Thị B', 'pass456', '0987654321', 'avatar2.jpg', 0),
('NV003', N'Lê Văn C', 'abc123', '0234567891', 'avatar3.jpg', 1),
('NV004', N'Phạm Thị D', 'xyz789', '0345678912', 'avatar4.jpg', 0),
('NV005', N'Hoàng Văn E', 'pass456', '0456789123', 'avatar5.jpg', 1),
('NV006', N'Mai Thị F', 'abcdef', '0567891234', 'avatar6.jpg', 0),
('NV007', N'Đinh Văn G', '123456', '0678912345', 'avatar7.jpg', 1),
('NV008', N'Dỗ Thị H', 'xyz123', '0789123456', 'avatar8.jpg', 0),
('NV009', N'Trần Văn I', 'abc789', '0891234567', 'avatar9.jpg', 1),
('NV010', N'Nguyễn Thị K', '123abc', '0912345678', 'avatar10.jpg', 0);
-- Thêm dữ liệu cho bảng KhachHang
INSERT INTO KhachHang (MaKH, TenKH, DiaChi, GioiTinh, SoDienThoai, Email, NgaySinh, HinhAnh)
VALUES
('KH001', N'Lê Văn A', N'123 Đường ABC', 1, '0123456789', 'levana@gmail.com', '1990-01-15', 'khachhang1.jpg'),
('KH002', N'Phạm Thị B', N'456 Đường XYZ', 0, '0987654321', 'phamthib@gmail.com', '1995-05-20', 'khachhang2.jpg'),
('KH003', N'Nguyễn Văn C', N'789 Đường LMN', 1, '0234567891', 'nguyenvanc@gmail.com', '1985-09-10', 'khachhang3.jpg'),
('KH004', N'Trần Thị D', N'101 Đường PQR', 0, '0345678912', 'tranthid@gmail.com', '1998-03-25', 'khachhang4.jpg'),
('KH005', N'Hoàng Văn E', N'202 Đường STU', 1, '0456789123', 'hoangve@gmail.com', '1989-07-05', 'khachhang5.jpg'),
('KH006', N'Mai Thị F', N'303 Đường WXY', 0, '0567891234', 'maithif@gmail.com', '1992-11-30', 'khachhang6.jpg'),
('KH007', N'Đinh Văn G', N'404 Đường ZAB', 1, '0678912345', 'dinhvang@gmail.com', '1987-04-12', 'khachhang7.jpg'),
('KH008', N'Dỗ Thị H', N'505 Đường CDE', 0, '0789123456', 'dothih@gmail.com', '1997-08-15', 'khachhang8.jpg'),
('KH009', N'Trần Văn I', N'606 Đường FGH', 1, '0891234567', 'tranvani@gmail.com', '1993-12-20', 'khachhang9.jpg'),
('KH010', N'Nguyễn Thị K', N'707 Đường IJK', 0, '0912345678', 'nguyenthik@gmail.com', '1996-06-22', 'khachhang10.jpg');

-- Thêm dữ liệu cho bảng PhuongThucThanhToan
INSERT INTO PhuongThucThanhToan (MaThanhToan, TenPTTT)
VALUES
('TT001', N'Tiền mặt'),
('TT002', N'Chuyển khoản')

-- Thêm dữ liệu cho bảng HoaDon
INSERT INTO HoaDon (MaKH, MaNV, PhiGiaoNhanh, TongTien, diaChi, MaThanhToan)
VALUES
('KH001', 'NV001', 10.5, 150.75, N'123 Đường ABC', 'TT001'),
('KH002', 'NV002', 8.0, 200.0, N'456 Đường XYZ', 'TT002'),
('KH003', 'NV003', 12.0, 180.50, N'789 Đường LMN', 'TT001'),
('KH004', 'NV004', 15.0, 220.25, N'101 Đường PQR', 'TT002'),
('KH005', 'NV005', 9.5, 120.0, N'202 Đường STU', 'TT001'),
('KH006', 'NV006', 11.0, 198.75, N'303 Đường WXY', 'TT002'),
('KH007', 'NV007', 13.5, 185.50, N'404 Đường ZAB', 'TT001'),
('KH008', 'NV008', 7.0, 90.25, N'505 Đường CDE', 'TT002'),
('KH009', 'NV009', 14.5, 205.0, N'606 Đường FGH', 'TT001'),
('KH010', 'NV010', 6.5, 110.75, N'707 Đường IJK', 'TT002');

-- Tiếp tục thêm dữ liệu cho các bảng khác tương tự...
-- Thêm dữ liệu cho bảng LoaiSanPham
-- Thêm dữ liệu cho bảng SanPham
INSERT INTO SanPham (MaSP, TenSP, SoLuong, GiaSP, NgayNhap, MoTa, HinhAnh, MaLoaiSanPham, MaKhoSP)
VALUES
('SP001', N'Bánh Mì', 100, 3.5, '2023-01-01', N'Bánh mì ngon', 'banhmi.jpg', 'LSP001', 'KSP001'),
('SP002', N'Phở Bò', 80, 8.0, '2023-01-02', N'Phở bò hảo hạng', 'phobo.jpg', 'LSP001', 'KSP001'),
('SP003', N'Bánh Bao Nhân Thịt', 50, 5.0, '2023-01-03', N'Bánh bao nhân thịt thơm ngon', 'banhbaonhanthit.jpg', 'LSP001', 'KSP002'),
('SP004', N'Bánh Tráng Nướng', 120, 4.5, '2023-01-04', N'Bánh tráng nướng thơm lừng', 'banhtrangnuong.jpg', 'LSP001', 'KSP002'),
('SP005', N'Soda Chanh', 200, 1.0, '2023-01-05', N'Soda chanh tươi lạnh', 'sodachanh.jpg', 'LSP005', 'KSP003'),
('SP006', N'Gimbap', 30, 4.0, '2023-01-06', N'Gimbap hàn quốc thơm ngon', 'gimbap.jpg', 'LSP004', 'KSP004'),
('SP007', N'Bánh Tráng Muối', 60, 0.5, '2023-01-07', N'Bánh tráng muối giòn tan', 'banhtrangmuoi.jpg', 'LSP001', 'KSP003'),
('SP008', N'Nước Dừa Tươi', 150, 3.5, '2023-01-08', N'Nước dừa tươi ngon mát', 'nuocduatuoi.jpg', 'LSP005', 'KSP004'),
('SP009', N'Bánh Gạo Trộn', 20, 2.0, '2023-01-09', N'Bánh gạo trộn độc đáo', 'banhgaotron.jpg', 'LSP001', 'KSP007'),
('SP010', N'Bánh Tráng Muối', 100, 4.5, '2023-01-10', N'Bánh tráng muối thơm ngon', 'banhtrangmuoi2.jpg', 'LSP001', 'KSP008');


-- Thêm dữ liệu cho bảng KhoSanPham
-- Thêm dữ liệu cho bảng HoaDon
INSERT INTO LoaiSanPham (MaLoaiSanPham, TenLoaiSanPham)
VALUES
('LSP001', N'Bánh Ngọt'),
('LSP002', N'Kem'),
('LSP003', N'Tiện Lợi'),
('LSP004', N'Đồ Ăn Nhanh'),
('LSP005', N'Nước Uống');

INSERT INTO KhoSanPham (MaKhoSP, TenSP, SoLuong, GiaSP, NgayNhap, MoTa, HinhAnh, MaLoaiSanPham)
VALUES('KSP001', N'Bánh Bao Nhân Thịt', 50, 5.0, '2023-01-01', N'Bánh bao nhân thịt thơm ngon', 'banhbaonhanthit.jpg', 'LSP001'),
('KSP002', N'Kem Dâu', 30, 2.5, '2023-01-02', N'Kem dâu tươi mát', 'kemdau.jpg', 'LSP002'),
('KSP003', N'Chips Khoai Tây', 80, 1.5, '2023-01-03', N'Chips khoai tây giòn tan', 'chipskhoaitay.jpg', 'LSP004'),
('KSP004', N'Bánh Mì Sandwich', 40, 3.0, '2023-01-04', N'Bánh mì sandwich hấp dẫn', 'banhmisandwich.jpg', 'LSP001'),
('KSP005', N'Soda Chanh', 60, 1.0, '2023-01-05', N'Soda chanh tươi lạnh', 'sodachanh.jpg', 'LSP005'),
('KSP006', N'Gimbap', 25, 4.0, '2023-01-06', N'Gimbap hàn quốc thơm ngon', 'gimbap.jpg', 'LSP004'),
('KSP007', N'Bánh Tráng Muối', 70, 0.5, '2023-01-07', N'Bánh tráng muối giòn tan', 'banhtrangmuoi.jpg', 'LSP001'),
('KSP008', N'Nước Dừa Tươi', 20, 3.5, '2023-01-08', N'Nước dừa tươi ngon mát', 'nuocduatuoi.jpg','LSP001'),
('KSP009', N'Bánh Gạo Trộn', 45, 2.0, '2023-01-09', N'Bánh gạo trộn độc đáo', 'banhgaotron.jpg', 'LSP001'),
('KSP010', N'Bánh Tráng Nướng', 55, 4.5, '2023-01-10', N'Bánh tráng nướng thơm lừng', 'banhtrangnuong.jpg', 'LSP001');
-- Thêm dữ liệu cho bảng HoaDonChiTiet
INSERT INTO HoaDonChiTiet (MaHoaDon, MaSP, TenSanPham, SoLuong, Gia, TongTien)
VALUES
(1, 'SP001', N'Bánh Bao Nhân Thịt', 2, 5.0, 10.0),
(2, 'SP002', N'Kem Dâu', 3, 2.5, 7.5),
(3, 'SP003', N'Chips Khoai Tây', 1, 1.5, 1.5),
(4, 'SP004', N'Bánh Mì Sandwich', 4, 3.0, 12.0),
(5, 'SP005', N'Soda Chanh', 2, 1.0, 2.0),
(6, 'SP006', N'Gimbap', 1, 4.0, 4.0),
(7, 'SP007', N'Bánh Tráng Muối', 5, 0.5, 2.5),
(8, 'SP008', N'Nước Dừa Tươi', 2, 3.5, 7.0),
(9, 'SP009', N'Bánh Gạo Trộn', 3, 2.0, 6.0),
(10, 'SP010', N'Bánh Tráng Nướng', 1, 4.5, 4.5);


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
--where YEAR(NgayTao) = @Nam 
Group by HoaDonChiTiet.TenSanPham
end

go
create or alter proc SP_TongDoanhThuSanPham1
as
begin
	select 
	LoaiSanPham.TenLoaiSanPham as tenLoaiSP,
	count(SanPham.SoLuong) as tongSoLuong
	from SanPham
	inner join LoaiSanPham on SanPham.MaLoaiSanPham = LoaiSanPham.MaLoaiSanPham
	group by LoaiSanPham.TenLoaiSanPham
end

select * from khosanpham