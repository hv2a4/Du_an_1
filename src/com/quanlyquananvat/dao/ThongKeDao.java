package com.quanlyquananvat.dao;

import com.quanlyquananvat.ThuVienTienIch.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ThongKeDao {

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

    public List<Object[]> getTongDoanhThuLSP() {
        String sql = "{CALL SP_TongDoanhThuSanPham}";
        String col[] = {"tenLoaiSanPham", "TongDoanhThu"};
        return getListOfArray(sql, col);
    }

}
