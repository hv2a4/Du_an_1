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
        String sql = "{CALL SP_TongDoanhThuSanPham1}";
        String col[] = {"tenLoai", "TongSoLuongBan"};
        return getListOfArray(sql, col);
    }

    public List<Object[]> getTongDoanhThu(Integer nam) {
        String sql = "{CALL SP_DoanhThu(?)}";
        String col[] = {"tenSanPham", "soLuongSanPham", "tongDoanhThu", "DoanhThuCao", "DoanhThuIt", "DoanhThuTB"};
        return getListOfArray(sql, col, nam);
    }

    public List<Object[]> getTonKho() {
        String sql = "{CALL sp_tonKho}";
        String col[] = {"TenSanPham", "SoLuongTonKho", "TenSanPhamHoaDon", "SoLuongBan", "DoanhThu"};
        return getListOfArray(sql, col);
    }

    public List<Object[]> getSanPham() {
        String sql = "{CALL sp_tongDoanhThuSanPham}";
        String col[] = {"tenSP", "SoLuongBanRa", "SoLuongTonKho", "GiaBanTrungBinh", "TongDoanhThu"};
        return getListOfArray(sql, col);
    }

    public List<Object[]> getThongKeHoaDon() {
        String sql = "{CALL ThongKeHoaDon}";
        String col[] = {"NgayTao", "SoLuongHoaDon", "TongDoanhThu"};
        return getListOfArray(sql, col);
    }

    public List<Object[]> getTienMat() {
        String sql = "{CALL psp_PhuongThucThanhToan_tienMat}";
        String col[] = {"tenPTTT", "SoLuongHoaDon", "TongDoanhThu"};
        return getListOfArray(sql, col);
    }

    public List<Object[]> getChuyenKhoan() {
        String sql = "{CALL psp_PhuongThucThanhToan_ChuyenKhoan}";
        String col[] = {"tenPTTT", "SoLuongHoaDon", "TongDoanhThu"};
        return getListOfArray(sql, col);
    }

    public List<Object[]> getDataTienMat() {
        String sql = "{CALL sp_tkTienMat}";
        String col[] = {"manv", "makh", "SoLuongHoaDon", "TongDoanhThu"};
        return getListOfArray(sql, col);
    }

    public List<Object[]> getDataChuyenKhoan() {
        String sql = "{CALL sp_tkChuyenKhoan}";
        String col[] = {"manv", "makh", "SoLuongHoaDon", "TongDoanhThu"};
        return getListOfArray(sql, col);
    }

    public List<Object[]> getHoaDonTheoKhachHang() {
        String sql = "{CALL sp_hoaDonTheoKhachHangh}";
        String col[] = {"tenkhachhang", "tensanpham", "SoLuongHoaDon", "TongDoanhThu"};
        return getListOfArray(sql, col);
    }
    public List<Object[]> getThongKeSanPham() {
        String sql = "{CALL sp_doanhThuSanPham}";
        String col[] = {"tensp", "SoLuongHoaDon", "TongDoanhThu", "doanhThuTB"};
        return getListOfArray(sql, col);
    }
    public List<Object[]> getSoLuongSanPhamTonKho() {
        String sql = "{CALL sp_sanPhamTonKho}";
        String col[] = {"TenSP", "SoLuongTonKho", "TenLoaiSanPham", "GiaSP"};
        return getListOfArray(sql, col);
    }
    public List<Object[]> getSoLuongDaBan() {
        String sql = "{CALL GetProductSalesInfo}";
        String col[] = {"tenSP", "soLuong", "ngayTao", "SoLuongDaBan"};
        return getListOfArray(sql, col);
    }

    public static void main(String[] args) {
        ThongKeDao s = new ThongKeDao();
        List<Object[]> list = s.getSoLuongDaBan();
        for (Object[] objects : list) {
            System.out.println("" + objects[0]);
            System.out.println("" + objects[1]);
            System.out.println("" + objects[2]);
            System.out.println("" + objects[3]);
            System.out.println("");
        }
    }

}
