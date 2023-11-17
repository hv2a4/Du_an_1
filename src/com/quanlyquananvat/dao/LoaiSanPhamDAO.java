package com.quanlyquananvat.dao;

import com.quanlyquananvat.Object.LoaiSanPhamObject;
import com.quanlyquananvat.ThuVienTienIch.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class LoaiSanPhamDAO extends DAO<LoaiSanPhamObject, String> {

    final String Insert_sql = "INSERT INTO loaisanpham(MaLoaiSanPham, TenLoaiSanPham) VALUES (?, ?)";
    final String Udate_sql = "UPDATE loaisanpham SET TenLoaiSanPham=? WHERE MaLoaiSanPham=?";
    final String Delete_sql = "DELETE FROM loaisanpham WHERE MaLoaiSanPham=?";
    final String Select_all_sql = "SELECT * FROM loaisanpham";
    final String Select_ById_sql = "SELECT * FROM loaisanpham WHERE MaLoaiSanPham=?";

    @Override
    public void insert(LoaiSanPhamObject entiTy) {
        JdbcHelper.update(Insert_sql, entiTy.getMaLoaiSanPham(), entiTy.getTenLoaiSanPham());
    }

    @Override
    public void update(LoaiSanPhamObject entiTy) {
        JdbcHelper.update(Udate_sql, entiTy.getTenLoaiSanPham(), entiTy.getMaLoaiSanPham());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(Delete_sql, id);
    }

    @Override
    public List<LoaiSanPhamObject> selectAll() {
        return (List<LoaiSanPhamObject>) selectBySQL(Select_all_sql);
    }

    @Override
    public LoaiSanPhamObject selectById(String id) {
        List<LoaiSanPhamObject> list = selectBySQL(this.Select_ById_sql, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<LoaiSanPhamObject> selectBySQL(String sql, Object... args) {
        List<LoaiSanPhamObject> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                LoaiSanPhamObject lsp = new LoaiSanPhamObject();
                lsp.setMaLoaiSanPham(rs.getString("MaLoaiSanPham"));
                lsp.setTenLoaiSanPham(rs.getString("TenLoaiSanPham"));
                list.add(lsp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

  
}
