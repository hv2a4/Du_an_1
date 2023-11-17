package com.quanlyquananvat.Object;

public class PhuongThucThanhToanObject {

    private String maThanhToan;
    private String tenPhuongThucThanhToan;

    public PhuongThucThanhToanObject() {
    }

    public PhuongThucThanhToanObject(String maThanhToan, String tenPhuongThucThanhToan) {
        this.maThanhToan = maThanhToan;
        this.tenPhuongThucThanhToan = tenPhuongThucThanhToan;
    }

    public String getMaThanhToan() {
        return maThanhToan;
    }

    public void setMaThanhToan(String maThanhToan) {
        this.maThanhToan = maThanhToan;
    }

    public String getTenPhuongThucThanhToan() {
        return tenPhuongThucThanhToan;
    }

    public void setTenPhuongThucThanhToan(String tenPhuongThucThanhToan) {
        this.tenPhuongThucThanhToan = tenPhuongThucThanhToan;
    }

}
