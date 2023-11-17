/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlyquananvat.dao;

import com.quanlyquananvat.Object.KhachHangObject;
import com.quanlyquananvat.Object.NhanVienObject;
import com.quanlyquananvat.ThuVienTienIch.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author pc
 */
public class KhachHangDAO extends DAO<KhachHangObject, String> {

    final String Insert_sql = "INSERT INTO khachhang(MaKH, TenKH, DiaChi, GioiTinh, SoDienThoai, Email, NgaySinh) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final String Udate_sql = " UPDATE khachhang SET TenKH=?, DiaChi=?, GioiTinh=?, SoDienThoai=?, Email=?, NgaySinh=? WHERE MaKH=?";
    final String Delete_sql = " DELETE FROM khachhang WHERE MaKH=?";
    final String Select_all_sql = "select * from KhachHang";
    final String Select_ById_sql = "SELECT * FROM khachhang WHERE MaKH=?";

    @Override
    public void insert(KhachHangObject entiTy) {
        JdbcHelper.update(Insert_sql, entiTy.getMaKH(), entiTy.getTenKH(), entiTy.getDiaChi(),
                entiTy.isGioiTinh(), entiTy.getSoDienThoai(), entiTy.getEmail(), entiTy.getNgaySinh());
    }

    @Override
    public void update(KhachHangObject entiTy) {
        JdbcHelper.update(Udate_sql, entiTy.getTenKH(), entiTy.getDiaChi(),
                entiTy.isGioiTinh(), entiTy.getSoDienThoai(), entiTy.getEmail(), entiTy.getNgaySinh(), entiTy.getMaKH());
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
                list.add(kh);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
