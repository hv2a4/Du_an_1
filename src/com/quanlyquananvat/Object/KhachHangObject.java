package com.quanlyquananvat.Object;

import java.util.Date;

public class KhachHangObject {

    private String MaKH;
    private String TenKH;
    private String DiaChi;
    private String SoDienThoai;
    private boolean GioiTinh;
    private String Email;
    private Date NgaySinh;
    private String hinhAnh;

    public KhachHangObject(String MaKH, String TenKH, String DiaChi, String SoDienThoai, boolean GioiTinh, String Email, Date NgaySinh, String hinhAnh) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
        this.GioiTinh = GioiTinh;
        this.Email = Email;
        this.NgaySinh = NgaySinh;
        this.hinhAnh = hinhAnh;
    }

    public KhachHangObject() {
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return this.TenKH;
    }

}
