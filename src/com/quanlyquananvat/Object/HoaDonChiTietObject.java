
package com.quanlyquananvat.Object;


public class HoaDonChiTietObject {

    private int maHoaDonChiTiet;
    private int maHoaDon;
    private String maSanPham;
    private String tenSP;
    private int soLuong;
    private double gia;
    private double tongTien;

    public HoaDonChiTietObject(int maHoaDonChiTiet, int maHoaDon, String maSanPham, String tenSP, int soLuong, double gia, double tongTien) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.gia = gia;
        this.tongTien = tongTien;
    }

    public HoaDonChiTietObject() {
    }

    public int getMaHoaDonChiTiet() {
        return maHoaDonChiTiet;
    }

    public void setMaHoaDonChiTiet(int maHoaDonChiTiet) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return this.tongTien+"";
    }

}
