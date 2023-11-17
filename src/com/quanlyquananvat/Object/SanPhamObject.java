package com.quanlyquananvat.Object;

import java.util.Date;

public class SanPhamObject {

    private String maSP;
    private String tenSP;
    private int soLuong;
    private double giaSP;
    private Date ngayNhap;
    private String moTa;
    private String hinhAnh;
    private String maLoaiSanPham;

    public SanPhamObject() {
    }

    public SanPhamObject(String maSP, String tenSP, int soLuong, double giaSP, Date ngayNhap, String moTa, String hinhAnh, String maLoaiSanPham) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaSP = giaSP;
        this.ngayNhap = ngayNhap;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
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

    public double getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(double giaSP) {
        this.giaSP = giaSP;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(String maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

}
