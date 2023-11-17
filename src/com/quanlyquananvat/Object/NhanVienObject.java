package com.quanlyquananvat.Object;

public class NhanVienObject {

    private String MaNV;
    private String TenNV;
    private String MauKhau;
    private String SoDienThoai;
    private String HinhAnh;
    private boolean VaiTro;

    public NhanVienObject() {
    }

    public NhanVienObject(String MaNV, String TenNV, String MauKhau, String SoDienThoai, String HinhAnh, boolean VaiTro) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.MauKhau = MauKhau;
        this.SoDienThoai = SoDienThoai;
        this.HinhAnh = HinhAnh;
        this.VaiTro = VaiTro;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getMauKhau() {
        return MauKhau;
    }

    public void setMauKhau(String MauKhau) {
        this.MauKhau = MauKhau;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public boolean isVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(boolean VaiTro) {
        this.VaiTro = VaiTro;
    }

}
