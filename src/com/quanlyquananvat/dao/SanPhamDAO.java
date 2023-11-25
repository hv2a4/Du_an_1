package com.quanlyquananvat.dao;

import com.quanlyquananvat.Object.LoaiSanPhamObject;
import com.quanlyquananvat.Object.SanPhamObject;
import com.quanlyquananvat.ThuVienTienIch.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class SanPhamDAO extends DAO<SanPhamObject, String> {

    final String Insert_sql = "INSERT INTO sanpham(MaSP, TenSP, SoLuong,GiaSP, NgayNhap, MoTa, HinhAnh, MaLoaiSanPham) VALUES (?, ?,?, ?, ?, ?, ?, ?)";
    final String Udate_sql = "UPDATE sanpham SET TenSP=?,SoLuong = ?, GiaSP=?, NgayNhap=?, MoTa=?, HinhAnh=?, MaLoaiSanPham=? WHERE MaSP=?";
    final String Delete_sql = "DELETE FROM sanpham WHERE MaSP=?";
    final String Select_all_sql = "SELECT * FROM sanpham";
    final String Select_ById_sql = "SELECT * FROM sanpham WHERE MaSP=?";
    final String TenSanPham = "select * from SanPham where TenSP like N'%' + ? + '%'";

    @Override
    public void insert(SanPhamObject entiTy) {
        JdbcHelper.update(Insert_sql, entiTy.getMaSP(), entiTy.getTenSP(), entiTy.getSoLuong(), entiTy.getGiaSP(),
                entiTy.getNgayNhap(), entiTy.getMoTa(), entiTy.getHinhAnh(), entiTy.getMaLoaiSanPham());
    }

    @Override
    public void update(SanPhamObject entiTy) {
        JdbcHelper.update(Udate_sql, entiTy.getTenSP(), entiTy.getSoLuong(), entiTy.getGiaSP(),
                entiTy.getNgayNhap(), entiTy.getMoTa(), entiTy.getHinhAnh(), entiTy.getMaLoaiSanPham(), entiTy.getMaSP());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(Delete_sql, id);
    }

    @Override
    public List<SanPhamObject> selectAll() {
        return (List<SanPhamObject>) selectBySQL(Select_all_sql);
    }

    @Override
    public SanPhamObject selectById(String id) {
        List<SanPhamObject> list = this.selectBySQL(Select_ById_sql, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SanPhamObject> selectBySQL(String sql, Object... args) {
        List<SanPhamObject> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                SanPhamObject sp = new SanPhamObject();
                sp.setMaSP(rs.getString("MaSP"));
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

    public List<SanPhamObject> select_all_lsp(String maLSP) {
        final String Select_all_sanPham = "select * from SanPham where MaLoaiSanPham = ?";
        return selectBySQL(Select_all_sanPham, maLSP);
    }

    public List<SanPhamObject> select_all_sp(String tenSP) {
        return selectBySQL(TenSanPham, tenSP);
    }
}
