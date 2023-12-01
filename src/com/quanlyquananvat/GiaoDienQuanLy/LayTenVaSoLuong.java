package com.quanlyquananvat.GiaoDienQuanLy;

public class LayTenVaSoLuong {

    String tenSanPham;
    int soLuong;
    double giaSp;

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(double giaSp) {
        this.giaSp = giaSp;
    }

    public LayTenVaSoLuong(String tenSanPham, int soLuong, double giaSp) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaSp = giaSp;
    }

    public LayTenVaSoLuong() {
    }

}
