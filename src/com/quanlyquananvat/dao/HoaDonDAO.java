package com.quanlyquananvat.dao;

import com.quanlyquananvat.Object.HoaDonChiTietObject;
import com.quanlyquananvat.Object.HoaDonObject;
import com.quanlyquananvat.ThuVienTienIch.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class HoaDonDAO extends DAO<HoaDonObject, Integer> {

    final String Insert_sql = "INSERT INTO hoadon( MaKH, MaNV, NgayTao, PhiGiaoNhanh,TongTien,DiaChi, MaThanhToan) VALUES ( (select MaKH from KhachHang where TenKH like ? ), ?,?, ?,?, ?, ?)";
    final String Udate_sql = "update HoaDon set MaKH = ? ,MaNV = ?,NgayTao = ?,PhiGiaoNhanh = ?,TongTien = ?,diaChi = ?,MaThanhToan = ? where MaHD = ?";
    final String Delete_sql = "DELETE FROM hoadon WHERE MaHD=?";
    final String Select_all_sql = "SELECT * FROM hoadon";
    final String Select_ById_sql = "SELECT * FROM hoadon WHERE MaHD=?";
    final String Create_ID = "insert into HoaDon(MaKH,MaNV,NgayTao,PhiGiaoNhanh,MaThanhToan) values(?,?,?,?,?);";
    final String MaHDMoi = "select max(MaHD) as MaHD from HoaDon";
    final String SELECT_MAKH = "select top 1 MaKH from KhachHang where TenKH like ?";
    final String ten_MaKH = "select TenKH from KhachHang where MaKH = ?";
    final String Select_year_byHoaDon = "select distinct YEAR(NgayTao) as nam from HoaDon order by YEAR(NgayTao) desc";

    // Tổng số lượng hóa đơn đã đặt thành công
    public int getSoLuongHoaDon() {
        final String hoaDonDztThanhCong = "SELECT COUNT(*) AS TongSoLuongHoaDon FROM HoaDon WHERE MaThanhToan IS NOT NULL;";
        try {
            ResultSet rs = JdbcHelper.query(hoaDonDztThanhCong);
            if (rs.next()) {
                return rs.getInt("TongSoLuongHoaDon");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Thông báo lỗi để có thể theo dõi và sửa lỗi
        }
        return 0;
    }
    
    // tổng doanh thu
    public double getTongDoanhThu() {
        String tongDoanhThu = "SELECT SUM(TongTien) AS TongDoanhThu FROM HoaDon WHERE MaThanhToan IS NOT NULL;";
        try {
            ResultSet rs = JdbcHelper.query(tongDoanhThu);
            if (rs.next()) {
                return rs.getDouble("TongDoanhThu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void insert(HoaDonObject entiTy) {
        JdbcHelper.update(Insert_sql, entiTy.getMaKH(), entiTy.getMaNV(), entiTy.getNgayTao(),
                entiTy.getPhiGiaoNhanh(), entiTy.getTongTien(), entiTy.getDiaChi(), entiTy.getMaThanhToan());
    }

    @Override
    public void update(HoaDonObject entiTy) {
        JdbcHelper.update(Udate_sql, entiTy.getMaKH(), entiTy.getMaNV(), entiTy.getNgayTao(), entiTy.getPhiGiaoNhanh(),
                entiTy.getTongTien(), entiTy.getDiaChi(), entiTy.getMaThanhToan(), entiTy.getMaHD());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(Delete_sql, id);
    }

    @Override
    public List<HoaDonObject> selectAll() {
        return selectBySQL(Select_all_sql);
    }

    @Override
    public HoaDonObject selectById(Integer id) {
        List<HoaDonObject> list = this.selectBySQL(Select_ById_sql, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HoaDonObject> selectBySQL(String sql, Object... args) {
        List<HoaDonObject> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HoaDonObject hd = new HoaDonObject();
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setMaKH(rs.getString("MaKH"));
                hd.setMaNV(rs.getString("MaNV"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setPhiGiaoNhanh(rs.getDouble("PhiGiaoNhanh"));
                hd.setTongTien(rs.getDouble("TongTien"));
                hd.setDiaChi(rs.getString("DiaChi"));
                hd.setMaThanhToan(rs.getString("MaThanhToan"));
                list.add(hd);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void create_ID(HoaDonObject entiTy) {
        JdbcHelper.update(Insert_sql, entiTy.getMaHD(), entiTy.getMaKH(), entiTy.getNgayTao(),
                entiTy.getPhiGiaoNhanh(), entiTy.getMaThanhToan());
    }

    public List<HoaDonObject> hoaDonMoi() {
        List<HoaDonObject> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(MaHDMoi);
            while (rs.next()) {
                HoaDonObject hd = new HoaDonObject();
                hd.setMaHD(rs.getInt("MaHD"));
                list.add(hd);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public String layMaKhachHang(String tenKhachHang) {
        try {
            ResultSet rs = JdbcHelper.query(SELECT_MAKH, "%" + tenKhachHang + "%");
            if (rs.next()) {
                return rs.getString("MaKH");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ""; // hoặc giá trị mặc định khác tùy vào logic của bạn
    }

    public String layTenKhachHang(String tenKH) {
        try {
            ResultSet rs = JdbcHelper.query(ten_MaKH, tenKH);
            if (rs.next()) {
                return rs.getString("TenKH");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public List<Integer> getYear() {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(Select_year_byHoaDon);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
