package com.quanlyquananvat.dao;

import com.quanlyquananvat.Object.HoaDonChiTietObject;
import com.quanlyquananvat.Object.KhoSanPhamObject;
import com.quanlyquananvat.Object.SanPhamObject;
import com.quanlyquananvat.ThuVienTienIch.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class KhoSanPhamDao extends DAO<KhoSanPhamObject, String> {

    final String Insert_sql_khoSanPham = "INSERT INTO khoSanPham (MaKhoSP, TenSP, SoLuong, GiaSP, NgayNhap, MoTa, HinhAnh, MaLoaiSanPham) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    final String Update_sql_khoSanPham = "UPDATE khoSanPham SET TenSP=?, SoLuong=?, GiaSP=?, NgayNhap=?, MoTa=?, HinhAnh=?, MaLoaiSanPham=? WHERE MaKhoSP=?";
    final String Delete_sql_khoSanPham = "DELETE FROM khoSanPham WHERE MaKhoSP=?";
    final String Select_all_sql_khoSanPham = "SELECT * FROM khoSanPham";
    final String Select_ById_sql_khoSanPham = "SELECT * FROM khoSanPham WHERE MaKhoSP=?";
    final String Select_maHD_sql_khoSanPham = "SELECT * FROM khoSanPham WHERE MaLoaiSanPham like ?";
    final String TenSanPham = "select * from khoSanPham where TenSP like N'%' + ? + '%'";

    @Override
    public void insert(KhoSanPhamObject entiTy) {
        JdbcHelper.update(Insert_sql_khoSanPham, entiTy.getMaKSP(), entiTy.getTenSP(), entiTy.getSoLuong(),
                entiTy.getGiaSP(), entiTy.getNgayNhap(), entiTy.getMoTa(), entiTy.getHinhAnh(), entiTy.getMaLoaiSanPham());
    }

    @Override
    public void update(KhoSanPhamObject entiTy) {
        JdbcHelper.update(Update_sql_khoSanPham, entiTy.getTenSP(), entiTy.getSoLuong(),
                entiTy.getGiaSP(), entiTy.getNgayNhap(), entiTy.getMoTa(), entiTy.getHinhAnh(),
                entiTy.getMaLoaiSanPham(), entiTy.getMaKSP());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(Delete_sql_khoSanPham, id);
    }

    @Override
    public List<KhoSanPhamObject> selectAll() {
        return (List<KhoSanPhamObject>) selectBySQL(Select_all_sql_khoSanPham);
    }

    @Override
    public KhoSanPhamObject selectById(String id) {
        List<KhoSanPhamObject> list = selectBySQL(this.Select_ById_sql_khoSanPham, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhoSanPhamObject> selectBySQL(String sql, Object... args) {
        List<KhoSanPhamObject> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                KhoSanPhamObject sp = new KhoSanPhamObject();
                sp.setMaKSP(rs.getString("MaKhoSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGiaSP(rs.getDouble("GiaSP"));
                sp.setNgayNhap(rs.getDate("NgayNhap"));
                sp.setMoTa(rs.getString("MoTa"));
                sp.setHinhAnh(rs.getString("HinhAnh"));
                sp.setMaLoaiSanPham(rs.getString("MaLoaiSanPham"));
                list.add(sp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<KhoSanPhamObject> select_all_sp(String tenSP) {
        return selectBySQL(TenSanPham, tenSP);
    }
}
