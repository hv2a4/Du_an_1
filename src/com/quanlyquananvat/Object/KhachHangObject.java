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

    public KhachHangObject() {
    }

    public KhachHangObject(String MaKH, String TenKH, String DiaChi, String SoDienThoai, boolean GioiTinh, String Email, Date NgaySinh) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
        this.GioiTinh = GioiTinh;
        this.Email = Email;
        this.NgaySinh = NgaySinh;
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

    @Override
    public String toString() {
        return this.TenKH;
    }

}
