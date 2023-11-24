package com.quanlyquananvat.Object;

import java.util.Date;

public class HoaDonObject {

    private int MaHD;
    private String MaKH;
    private String MaNV;
    private Date ngayTao;
    private double phiGiaoNhanh;
    private double tongTien;
    private String diaChi;
    private String maThanhToan;

    public HoaDonObject() {
    }

    public HoaDonObject(int MaHD, String MaKH, String MaNV, Date ngayTao, double phiGiaoNhanh, double tongTien, String diaChi, String maThanhToan) {
        this.MaHD = MaHD;
        this.MaKH = MaKH;
        this.MaNV = MaNV;
        this.ngayTao = ngayTao;
        this.phiGiaoNhanh = phiGiaoNhanh;
        this.tongTien = tongTien;
        this.diaChi = diaChi;
        this.maThanhToan = maThanhToan;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public double getPhiGiaoNhanh() {
        return phiGiaoNhanh;
    }

    public void setPhiGiaoNhanh(double phiGiaoNhanh) {
        this.phiGiaoNhanh = phiGiaoNhanh;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaThanhToan() {
        return maThanhToan;
    }

    public void setMaThanhToan(String maThanhToan) {
        this.maThanhToan = maThanhToan;
    }

    @Override
    public String toString() {
        return this.MaHD + "";
    }

}
