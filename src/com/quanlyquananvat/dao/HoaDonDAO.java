package com.quanlyquananvat.dao;

import com.quanlyquananvat.Object.HoaDonChiTietObject;
import com.quanlyquananvat.Object.HoaDonObject;
import com.quanlyquananvat.ThuVienTienIch.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class HoaDonDAO extends DAO<HoaDonObject, Integer> {

    final String Insert_sql = "INSERT INTO hoadon( MaKH, MaNV, NgayTao, PhiGiaoNhanh, MaThanhToan) VALUES ( (select MaKH from KhachHang where TenKH like ? ), ?, ?, ?, ?)";
    final String Udate_sql = "UPDATE hoadon SET MaKH=?, MaNV=?, NgayTao=?, PhiGiaoNhanh=?, MaThanhToan=? WHERE MaHD=?";
    final String Delete_sql = "DELETE FROM hoadon WHERE MaHD=?";
    final String Select_all_sql = "SELECT * FROM hoadon";
    final String Select_ById_sql = "SELECT * FROM hoadon WHERE MaHD=?";
    final String Create_ID = "insert into HoaDon(MaKH,MaNV,NgayTao,PhiGiaoNhanh,MaThanhToan) values(?,?,?,?,?);";
    final String MaHDMoi = "select max(MaHD) as MaHD from HoaDon";
   

    

    @Override
    public void insert(HoaDonObject entiTy) {
        JdbcHelper.update(Insert_sql, entiTy.getMaKH(), entiTy.getMaNV(), entiTy.getNgayTao(),
                entiTy.getPhiGiaoNhanh(), entiTy.getMaThanhToan());
    }

    @Override
    public void update(HoaDonObject entiTy) {
        JdbcHelper.update(Udate_sql, entiTy.getMaKH(), entiTy.getNgayTao(),
                entiTy.getPhiGiaoNhanh(), entiTy.getMaThanhToan(), entiTy.getMaHD());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(Delete_sql, id);
    }

    @Override
    public List<HoaDonObject> selectAll() {
        return (List<HoaDonObject>) selectBySQL(Select_all_sql);
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
}
