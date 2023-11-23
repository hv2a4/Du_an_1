package com.quanlyquananvat.dao;

import com.quanlyquananvat.ThuVienTienIch.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InHoaDonDAO {

    public List<Object[]> getListOfArray(String sql, String[] col, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                Object[] values = new Object[col.length];
                for (int i = 0; i < col.length; i++) {

                    values[i] = rs.getObject(col[i]);
                }
                list.add(values);
            }
            rs.getStatement().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Object[]> getThongTinSanPham(Integer maHD) {
        String sql = "{CALL SP_SelectHD(?)}";
        String col[] = {"MaHD", "ngayTao", "phiVanChuyen", "tenSanPham", "soLuong","giaSP", "tongTien"};
        return getListOfArray(sql, col, maHD);
    }
}
