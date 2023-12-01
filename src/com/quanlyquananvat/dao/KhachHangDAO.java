package com.quanlyquananvat.dao;

import com.quanlyquananvat.Object.KhachHangObject;
import com.quanlyquananvat.Object.NhanVienObject;
import com.quanlyquananvat.ThuVienTienIch.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class KhachHangDAO extends DAO<KhachHangObject, String> {

    final String Insert_sql = "INSERT INTO KhachHang(MaKH, TenKH, DiaChi, GioiTinh, SoDienThoai, Email, NgaySinh, HinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    final String Update_sql = "UPDATE KhachHang SET TenKH=?, DiaChi=?, GioiTinh=?, SoDienThoai=?, Email=?, NgaySinh=?, HinhAnh=? WHERE MaKH=?";
    final String Delete_sql = "DELETE FROM KhachHang WHERE MaKH=?";
    final String Select_all_sql = "SELECT * FROM KhachHang";
    final String Select_ById_sql = "SELECT * FROM KhachHang WHERE MaKH=?";
    final String Search_name = "select * from KhachHang where TenKH like N'%' + ? + '%'";
    final String select_MaKH = "select MaKH  from KhachHang where TenKH like N'%' + ? + '%'";

    @Override
    public void insert(KhachHangObject entiTy) {
        JdbcHelper.update(Insert_sql, entiTy.getMaKH(), entiTy.getTenKH(), entiTy.getDiaChi(),
                entiTy.isGioiTinh(), entiTy.getSoDienThoai(), entiTy.getEmail(),
                entiTy.getNgaySinh(), entiTy.getHinhAnh());
    }

    @Override
    public void update(KhachHangObject entiTy) {
        JdbcHelper.update(Update_sql, entiTy.getTenKH(), entiTy.getDiaChi(),
                entiTy.isGioiTinh(), entiTy.getSoDienThoai(), entiTy.getEmail(),
                entiTy.getNgaySinh(), entiTy.getHinhAnh(), entiTy.getMaKH());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(Delete_sql, id);
    }

    @Override
    public List<KhachHangObject> selectAll() {
        return (List<KhachHangObject>) selectBySQL(Select_all_sql);
    }

    @Override
    public KhachHangObject selectById(String id) {
        List<KhachHangObject> list = this.selectBySQL(Select_ById_sql, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhachHangObject> selectBySQL(String sql, Object... args) {
        List<KhachHangObject> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                KhachHangObject kh = new KhachHangObject();
                kh.setMaKH(rs.getString("MaKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setGioiTinh(rs.getBoolean("GioiTinh"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setEmail(rs.getString("Email"));
                kh.setNgaySinh(rs.getDate("NgaySinh"));
                kh.setHinhAnh(rs.getString("HinhAnh"));
                list.add(kh);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<KhachHangObject> search_name(String name) {
        return selectBySQL(Search_name, name);
    }

    public String select_MaKH(String tenKhachHang) {
        try {
            ResultSet rs = JdbcHelper.query(select_MaKH, tenKhachHang);
            if (rs.next()) {
                return rs.getString("MaKH");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
