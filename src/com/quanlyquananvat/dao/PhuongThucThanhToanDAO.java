package com.quanlyquananvat.dao;

import com.quanlyquananvat.Object.KhachHangObject;
import com.quanlyquananvat.Object.PhuongThucThanhToanObject;
import java.util.List;
import com.quanlyquananvat.ThuVienTienIch.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PhuongThucThanhToanDAO extends DAO<PhuongThucThanhToanObject, String> {

    final String Insert_sql = "INSERT INTO phuongthucthanhtoan(MaThanhToan, TenPTTT) VALUES (?, ?)";
    final String Udate_sql = " UPDATE phuongthucthanhtoan SET TenPTTT=? WHERE MaThanhToan=?";
    final String Delete_sql = " DELETE FROM phuongthucthanhtoan WHERE MaThanhToan=?";
    final String Select_all_sql = "SELECT * FROM phuongthucthanhtoan";
    final String Select_ById_sql = "SELECT * FROM phuongthucthanhtoan WHERE MaThanhToan=?";

    @Override
    public void insert(PhuongThucThanhToanObject entiTy) {
        JdbcHelper.update(Insert_sql, entiTy.getMaThanhToan(), entiTy.getTenPhuongThucThanhToan());
    }

    @Override
    public void update(PhuongThucThanhToanObject entiTy) {
        JdbcHelper.update(Udate_sql, entiTy.getTenPhuongThucThanhToan(), entiTy.getMaThanhToan());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(Delete_sql, id);
    }

    @Override
    public List<PhuongThucThanhToanObject> selectAll() {
        return (List<PhuongThucThanhToanObject>) selectBySQL(Select_all_sql);
    }

    @Override
    public PhuongThucThanhToanObject selectById(String id) {
        List<PhuongThucThanhToanObject> list = this.selectBySQL(Select_ById_sql, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<PhuongThucThanhToanObject> selectBySQL(String sql, Object... args) {
        List<PhuongThucThanhToanObject> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                PhuongThucThanhToanObject pt = new PhuongThucThanhToanObject();
                pt.setMaThanhToan(rs.getString("MaThanhToan"));
                pt.setTenPhuongThucThanhToan(rs.getString("TenPTTT"));
                list.add(pt);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
