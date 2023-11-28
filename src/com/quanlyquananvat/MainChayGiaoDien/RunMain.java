package com.quanlyquananvat.MainChayGiaoDien;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.quanlyquananvat.GiaoDienQuanLy.DangNhap;
import com.quanlyquananvat.GiaoDienQuanLy.DataAddedListener;
import com.quanlyquananvat.GiaoDienQuanLy.DoiMatKhau;
import com.quanlyquananvat.GiaoDienQuanLy.GiaoDich;
import com.quanlyquananvat.ThuVienTienIch.Auth;
import com.quanlyquananvat.ThuVienTienIch.MsgBox;
import com.quanlyquananvat.ThuVienTienIch.Ximge;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.UIManager;

public class RunMain extends javax.swing.JFrame {

   

    public RunMain() {
        initComponents();
        init();
    }

    public void init() {
        setTitle("Hệ thống quản lý quán ăn vặt");
        setIconImage(Ximge.getAppIcon());
        setLocationRelativeTo(null);
        startClock();
        startDateTimeUpdater();
        ChuyenManHinhController chuyenManHinhController = new ChuyenManHinhController(jPanelTrungTam);
        chuyenManHinhController.setView(PannelManHinhChinh, lblManHinhChinh);
        List<DanhSachQuanLy> list = new ArrayList<>();
        list.add(new DanhSachQuanLy("Trang Chu", PannelManHinhChinh, lblManHinhChinh));
        list.add(new DanhSachQuanLy("khach hang", PanelKhachHang, lblKhachHnag));
        list.add(new DanhSachQuanLy("nhan vien", PanelNhanVien, lblNhanVien));
        list.add(new DanhSachQuanLy("san pham", PanelSanPham, lblSanPham));
        list.add(new DanhSachQuanLy("giao dich", PanelGiaoDich, lblGiaoDich));
        list.add(new DanhSachQuanLy("ThongKe", PanelThongKe, lblThongKe));
        chuyenManHinhController.setEvent(list);
        if (!Auth.isLogin()) {
            dangNhap();
        }
        updateUserInfo();
        setMenuBar(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelChucNang = new javax.swing.JPanel();
        PannelManHinhChinh = new javax.swing.JPanel();
        lblManHinhChinh = new javax.swing.JLabel();
        PanelKhachHang = new javax.swing.JPanel();
        lblKhachHnag = new javax.swing.JLabel();
        PanelNhanVien = new javax.swing.JPanel();
        lblNhanVien = new javax.swing.JLabel();
        PanelSanPham = new javax.swing.JPanel();
        lblSanPham = new javax.swing.JLabel();
        PanelGiaoDich = new javax.swing.JPanel();
        lblGiaoDich = new javax.swing.JLabel();
        PanelThongKe = new javax.swing.JPanel();
        lblThongKe = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblDayMonthYear1 = new javax.swing.JLabel();
        lblClockd = new javax.swing.JLabel();
        lblClock = new javax.swing.JLabel();
        lblDayMonthYear = new javax.swing.JLabel();
        txtTaiKhoanName = new javax.swing.JLabel();
        txtPhanQuan = new javax.swing.JLabel();
        jPanelTrungTam = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        HeThong = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        TaiKhoan = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));

        jPanelChucNang.setBackground(new java.awt.Color(230, 230, 230));

        PannelManHinhChinh.setBackground(new java.awt.Color(67, 110, 238));

        lblManHinhChinh.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblManHinhChinh.setForeground(new java.awt.Color(255, 255, 255));
        lblManHinhChinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 83.png"))); // NOI18N
        lblManHinhChinh.setText("Màn hình chính");

        javax.swing.GroupLayout PannelManHinhChinhLayout = new javax.swing.GroupLayout(PannelManHinhChinh);
        PannelManHinhChinh.setLayout(PannelManHinhChinhLayout);
        PannelManHinhChinhLayout.setHorizontalGroup(
            PannelManHinhChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PannelManHinhChinhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblManHinhChinh, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addContainerGap())
        );
        PannelManHinhChinhLayout.setVerticalGroup(
            PannelManHinhChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PannelManHinhChinhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblManHinhChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelKhachHang.setBackground(new java.awt.Color(67, 110, 238));

        lblKhachHnag.setBackground(new java.awt.Color(255, 255, 255));
        lblKhachHnag.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblKhachHnag.setForeground(new java.awt.Color(255, 255, 255));
        lblKhachHnag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 84.png"))); // NOI18N
        lblKhachHnag.setText("Khách Hàng");
        lblKhachHnag.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblKhachHnag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKhachHnagMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelKhachHangLayout = new javax.swing.GroupLayout(PanelKhachHang);
        PanelKhachHang.setLayout(PanelKhachHangLayout);
        PanelKhachHangLayout.setHorizontalGroup(
            PanelKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblKhachHnag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelKhachHangLayout.setVerticalGroup(
            PanelKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblKhachHnag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelNhanVien.setBackground(new java.awt.Color(67, 110, 238));
        PanelNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelNhanVienMouseClicked(evt);
            }
        });

        lblNhanVien.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 85.png"))); // NOI18N
        lblNhanVien.setText("  Nhân viên");
        lblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNhanVienMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelNhanVienLayout = new javax.swing.GroupLayout(PanelNhanVien);
        PanelNhanVien.setLayout(PanelNhanVienLayout);
        PanelNhanVienLayout.setHorizontalGroup(
            PanelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelNhanVienLayout.setVerticalGroup(
            PanelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelSanPham.setBackground(new java.awt.Color(67, 110, 238));

        lblSanPham.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 86.png"))); // NOI18N
        lblSanPham.setText(" Sản phẩm");
        lblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelSanPhamLayout = new javax.swing.GroupLayout(PanelSanPham);
        PanelSanPham.setLayout(PanelSanPhamLayout);
        PanelSanPhamLayout.setHorizontalGroup(
            PanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelSanPhamLayout.setVerticalGroup(
            PanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelGiaoDich.setBackground(new java.awt.Color(67, 110, 238));

        lblGiaoDich.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblGiaoDich.setForeground(new java.awt.Color(255, 255, 255));
        lblGiaoDich.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 191.png"))); // NOI18N
        lblGiaoDich.setText(" Giao dịch");
        lblGiaoDich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGiaoDichMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelGiaoDichLayout = new javax.swing.GroupLayout(PanelGiaoDich);
        PanelGiaoDich.setLayout(PanelGiaoDichLayout);
        PanelGiaoDichLayout.setHorizontalGroup(
            PanelGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGiaoDichLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGiaoDich, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelGiaoDichLayout.setVerticalGroup(
            PanelGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGiaoDichLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGiaoDich, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelThongKe.setBackground(new java.awt.Color(67, 110, 238));

        lblThongKe.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblThongKe.setForeground(new java.awt.Color(255, 255, 255));
        lblThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 88.png"))); // NOI18N
        lblThongKe.setText(" Thống kê");
        lblThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThongKeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelThongKeLayout = new javax.swing.GroupLayout(PanelThongKe);
        PanelThongKe.setLayout(PanelThongKeLayout);
        PanelThongKeLayout.setHorizontalGroup(
            PanelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelThongKeLayout.setVerticalGroup(
            PanelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Tài khoản:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Phân quyền: ");

        lblDayMonthYear1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lblDayMonthYear1.setForeground(new java.awt.Color(0, 0, 0));
        lblDayMonthYear1.setText("Ngày:");

        lblClockd.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lblClockd.setForeground(new java.awt.Color(0, 0, 0));
        lblClockd.setText("Giờ:");

        lblClock.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lblClock.setForeground(new java.awt.Color(0, 0, 0));
        lblClock.setText("00:00:00");

        lblDayMonthYear.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lblDayMonthYear.setForeground(new java.awt.Color(0, 0, 0));
        lblDayMonthYear.setText("dd-MM-yyyy");

        txtTaiKhoanName.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtTaiKhoanName.setForeground(new java.awt.Color(0, 0, 0));
        txtTaiKhoanName.setText("...................");

        txtPhanQuan.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtPhanQuan.setForeground(new java.awt.Color(0, 0, 0));
        txtPhanQuan.setText("..................");

        javax.swing.GroupLayout jPanelChucNangLayout = new javax.swing.GroupLayout(jPanelChucNang);
        jPanelChucNang.setLayout(jPanelChucNangLayout);
        jPanelChucNangLayout.setHorizontalGroup(
            jPanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PannelManHinhChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelGiaoDich, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelChucNangLayout.createSequentialGroup()
                        .addGroup(jPanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelChucNangLayout.createSequentialGroup()
                                .addComponent(lblDayMonthYear1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDayMonthYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelChucNangLayout.createSequentialGroup()
                                .addComponent(lblClockd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblClock, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelChucNangLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTaiKhoanName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelChucNangLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPhanQuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelChucNangLayout.setVerticalGroup(
            jPanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PannelManHinhChinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(PanelKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(PanelNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(PanelSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(PanelGiaoDich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(PanelThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTaiKhoanName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPhanQuan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDayMonthYear1)
                    .addComponent(lblDayMonthYear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClockd)
                    .addComponent(lblClock))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanelTrungTam.setBackground(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout jPanelTrungTamLayout = new javax.swing.GroupLayout(jPanelTrungTam);
        jPanelTrungTam.setLayout(jPanelTrungTamLayout);
        jPanelTrungTamLayout.setHorizontalGroup(
            jPanelTrungTamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 932, Short.MAX_VALUE)
        );
        jPanelTrungTamLayout.setVerticalGroup(
            jPanelTrungTamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTrungTam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelChucNang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelTrungTam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setBorder(null);
        jMenuBar1.setForeground(new java.awt.Color(0, 0, 0));
        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        HeThong.setForeground(new java.awt.Color(0, 0, 0));
        HeThong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 80.png"))); // NOI18N
        HeThong.setText("Hệ thống");
        HeThong.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        HeThong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HeThongMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HeThongMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HeThongMousePressed(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jMenuItem1.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/icons8-close-30.png"))); // NOI18N
        jMenuItem1.setText("Kết thúc");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        HeThong.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jMenuItem2.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/icons8-show-password-28.png"))); // NOI18N
        jMenuItem2.setText("Đổi mật khẩu");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        HeThong.add(jMenuItem2);

        jMenuBar1.add(HeThong);

        TaiKhoan.setForeground(new java.awt.Color(0, 0, 0));
        TaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 81.png"))); // NOI18N
        TaiKhoan.setText("Tài khoản");
        TaiKhoan.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        TaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TaiKhoanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TaiKhoanMouseExited(evt);
            }
        });

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jMenuItem3.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/icons8-log-out-29.png"))); // NOI18N
        jMenuItem3.setText("Đăng xuất");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenuItem3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jMenuItem3KeyReleased(evt);
            }
        });
        TaiKhoan.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem4.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jMenuItem4.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/icons8-life-coach-29.png"))); // NOI18N
        jMenuItem4.setText("Giới thiệu");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        TaiKhoan.add(jMenuItem4);

        jMenuBar1.add(TaiKhoan);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void startClock() {
        Thread clockThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Date now = new Date();
                        SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm:ss a");
                        lblClock.setText(formatDate.format(now));
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        clockThread.start();
    }

    public void dangNhap() {
        new DangNhap(this, true).setVisible(true);
    }

    public void updateTime() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateTime = dateFormat.format(now);
        lblDayMonthYear.setText(currentDateTime);
    }

    public void startDateTimeUpdater() {
        Thread dateTime = new Thread(() -> {
            while (true) {
                try {
                    updateTime();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        dateTime.start();
    }

    private void HeThongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeThongMousePressed

    }//GEN-LAST:event_HeThongMousePressed

    private void HeThongMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeThongMouseEntered

    }//GEN-LAST:event_HeThongMouseEntered

    private void HeThongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeThongMouseExited

    }//GEN-LAST:event_HeThongMouseExited

    private void TaiKhoanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoanMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TaiKhoanMouseEntered

    private void TaiKhoanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoanMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_TaiKhoanMouseExited

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        doiMatKhau();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        dangXuat();
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    public void dangXuat() {
        this.setVisible(false);
        Auth.clear();
        new DangNhap(this, true).setVisible(true);
        this.setVisible(true);
        updateUserInfo();
    }
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        String path = "src\\HTML\\gioiThieu.html";
        File htmlFile = new File(path);

        try {
            if (htmlFile.exists()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
            } else {
                MsgBox.warning(this, "Không tìm thấy tệp!");
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened


    }//GEN-LAST:event_formWindowOpened

    private void lblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhanVienMouseClicked
        if (!Auth.isLogin()) {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
        if (!Auth.isManager()) {
            MsgBox.warning(this, "Bạn không có quyền sử xem chức năng quản lý này!");
            return;
        }
    }//GEN-LAST:event_lblNhanVienMouseClicked

    private void PanelNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelNhanVienMouseClicked
        if (!Auth.isManager()) {
            MsgBox.warning(this, "Bạn không có quyền sử xem chức năng quản lý này!");
            return;
        }
    }//GEN-LAST:event_PanelNhanVienMouseClicked

    private void lblKhachHnagMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhachHnagMouseClicked
        if (!Auth.isLogin()) {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }//GEN-LAST:event_lblKhachHnagMouseClicked

    private void lblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseClicked
        if (!Auth.isLogin()) {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }//GEN-LAST:event_lblSanPhamMouseClicked

    private void lblGiaoDichMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGiaoDichMouseClicked
        if (!Auth.isLogin()) {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }//GEN-LAST:event_lblGiaoDichMouseClicked

    private void lblThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongKeMouseClicked
        if (!Auth.isLogin()) {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }//GEN-LAST:event_lblThongKeMouseClicked

    private void jMenuItem3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenuItem3KeyReleased
        dangXuat();
    }//GEN-LAST:event_jMenuItem3KeyReleased
    public void doiMatKhau() {
        if (Auth.isLogin()) {
            this.dispose();
            new DoiMatKhau(this, true).setVisible(true);
            this.setVisible(true);
            updateUserInfo();
        } else {
            MsgBox.warning(this, "Vui lòng đăng nhập!");
        }
    }

    private void updateUserInfo() {
        if (Auth.isLogin()) {
            // Nếu đã đăng nhập, cập nhật thông tin người dùng trên giao diện
            txtTaiKhoanName.setText(Auth.user.getMaNV());
            txtPhanQuan.setText(Auth.user.isVaiTro() ? "Quản lý" : "Nhân viên");
        } else {
            // Nếu chưa đăng nhập, đặt giá trị mặc định hoặc hiển thị thông báo tùy ý
            txtTaiKhoanName.setText("Chưa có tài khoản");
            txtPhanQuan.setText("Chưa có phân quyền");
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RunMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RunMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RunMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RunMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        FlatLaf.registerCustomDefaultsSource("com.quanlyquananvat.style");
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RunMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu HeThong;
    private javax.swing.JPanel PanelGiaoDich;
    private javax.swing.JPanel PanelKhachHang;
    private javax.swing.JPanel PanelNhanVien;
    private javax.swing.JPanel PanelSanPham;
    private javax.swing.JPanel PanelThongKe;
    private javax.swing.JPanel PannelManHinhChinh;
    private javax.swing.JMenu TaiKhoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelChucNang;
    private javax.swing.JPanel jPanelTrungTam;
    private javax.swing.JLabel lblClock;
    private javax.swing.JLabel lblClockd;
    private javax.swing.JLabel lblDayMonthYear;
    private javax.swing.JLabel lblDayMonthYear1;
    private javax.swing.JLabel lblGiaoDich;
    private javax.swing.JLabel lblKhachHnag;
    private javax.swing.JLabel lblManHinhChinh;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JLabel txtPhanQuan;
    private javax.swing.JLabel txtTaiKhoanName;
    // End of variables declaration//GEN-END:variables
}
