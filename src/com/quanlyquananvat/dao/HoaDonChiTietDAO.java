package com.quanlyquananvat.dao;

import com.quanlyquananvat.Object.HoaDonChiTietObject;
import com.quanlyquananvat.Object.HoaDonObject;
import com.quanlyquananvat.ThuVienTienIch.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class HoaDonChiTietDAO extends DAO<HoaDonChiTietObject, Integer> {

    final String Insert_sql = "INSERT INTO hoadonchitiet( MaHoaDon, MaSP, TenSanPham, SoLuong, Gia, TongTien) VALUES (?, ?, ?, ?, ?, ?)";
    final String Udate_sql = "UPDATE hoadonchitiet SET MaHoaDon=?, MaSP=?, TenSanPham=?, SoLuong=?, Gia=?, TongTien=? WHERE MaHDCT=?";
    final String Delete_sql = "DELETE FROM hoadonchitiet WHERE MaHDCT=?";
    final String Select_all_sql = "SELECT * FROM hoadonchitiet";
    final String Select_ById_sql = "SELECT * FROM hoadonchitiet WHERE MaHDCT=?";
    final String Select_maHD = "select * from HoaDonChiTiet where MaHoaDon like ?";

    public List<HoaDonChiTietObject> selectByMaHD(int MaHD) {
        return selectBySQL(Select_maHD, MaHD);
    }

    @Override
    public void insert(HoaDonChiTietObject entiTy) {
        JdbcHelper.update(Insert_sql, entiTy.getMaHoaDon(), entiTy.getMaSanPham(), entiTy.getTenSP(),
                entiTy.getSoLuong(), entiTy.getGia(), entiTy.getTongTien());

    }

    @Override
    public void update(HoaDonChiTietObject entiTy) {
        JdbcHelper.update(Udate_sql, entiTy.getMaHoaDon(), entiTy.getMaSanPham(), entiTy.getTenSP(),
                entiTy.getSoLuong(), entiTy.getGia(), entiTy.getTongTien(), entiTy.getMaHoaDonChiTiet());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(Delete_sql, id);
    }

    @Override
    public List<HoaDonChiTietObject> selectAll() {
        return (List<HoaDonChiTietObject>) selectBySQL(Select_all_sql);
    }

    @Override
    public HoaDonChiTietObject selectById(Integer id) {
        List<HoaDonChiTietObject> list = selectBySQL(this.Select_ById_sql, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HoaDonChiTietObject> selectBySQL(String sql, Object... args) {
        List<HoaDonChiTietObject> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HoaDonChiTietObject hdct = new HoaDonChiTietObject();
                hdct.setMaHoaDonChiTiet(rs.getInt("MaHDCT"));
                hdct.setMaHoaDon(rs.getInt("MaHoaDon"));
                hdct.setMaSanPham(rs.getString("MaSP"));
                hdct.setTenSP(rs.getString("TenSanPham"));
                hdct.setSoLuong(rs.getInt("SoLuong"));
                hdct.setGia(rs.getDouble("Gia"));
                hdct.setTongTien(rs.getDouble("TongTien"));
                list.add(hdct);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
