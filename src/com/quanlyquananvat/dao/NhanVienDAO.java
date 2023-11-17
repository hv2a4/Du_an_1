package com.quanlyquananvat.dao;

import com.quanlyquananvat.Object.NhanVienObject;
import com.quanlyquananvat.ThuVienTienIch.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class NhanVienDAO extends DAO<NhanVienObject, String> {

    final String Insert_sql = "INSERT INTO nhanvien(MaNV, TenNV, MatKhau, SoDienThoai, HinhAnh, VaiTro) VALUES (?, ?, ?, ?, ?, ?)";
    final String Udate_sql = "UPDATE NhanVien SET TenNV=?, MatKhau=?, SoDienThoai=?, HinhAnh=?, VaiTro=? WHERE MaNV=?";
    final String Delete_sql = "DELETE FROM nhanvien WHERE MaNV=?";
    final String Select_all_sql = "select * from NhanVien";
    final String Select_ById_sql = "SELECT * FROM nhanvien WHERE MaNV=?";
    final String select_ById_CheckOut = "SELECT * FROM nhanvien WHERE tennv LIKE N'%' + ? + '%'";

    @Override
    public void insert(NhanVienObject entiTy) {
        JdbcHelper.update(Insert_sql, entiTy.getMaNV(), entiTy.getTenNV(),
                entiTy.getMauKhau(), entiTy.getSoDienThoai(), entiTy.getHinhAnh(), entiTy.isVaiTro());
    }

    @Override
    public void update(NhanVienObject entity) {
        JdbcHelper.update(Udate_sql, entity.getTenNV(),
                entity.getMauKhau(), entity.getSoDienThoai(), entity.getHinhAnh(), entity.isVaiTro(), entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(Delete_sql, id);
    }

    @Override
    public List<NhanVienObject> selectAll() {
        return (List<NhanVienObject>) selectBySQL(Select_all_sql);
    }

    @Override
    public NhanVienObject selectById(String id) {
        List<NhanVienObject> list = this.selectBySQL(Select_ById_sql, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVienObject> selectBySQL(String sql, Object... args) {
        List<NhanVienObject> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NhanVienObject nv = new NhanVienObject();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setMauKhau(rs.getString("MatKhau"));
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setHinhAnh(rs.getString("HinhAnh"));
                nv.setVaiTro(rs.getBoolean("VaiTro"));
                list.add(nv);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<NhanVienObject> sreachByNameStaff(String name) {
        return selectBySQL(select_ById_CheckOut, name);
    }
}
