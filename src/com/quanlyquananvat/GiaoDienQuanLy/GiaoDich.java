package com.quanlyquananvat.GiaoDienQuanLy;

import com.quanlyquananvat.Object.GiaoDichObject;
import com.quanlyquananvat.Object.HoaDonChiTietObject;
import com.quanlyquananvat.Object.HoaDonObject;
import com.quanlyquananvat.Object.KhachHangObject;
import com.quanlyquananvat.Object.LoaiSanPhamObject;
import com.quanlyquananvat.Object.SanPhamObject;
import com.quanlyquananvat.ThuVienTienIch.Auth;
import com.quanlyquananvat.ThuVienTienIch.MsgBox;
import com.quanlyquananvat.ThuVienTienIch.XDate;
import com.quanlyquananvat.dao.HoaDonChiTietDAO;
import com.quanlyquananvat.dao.HoaDonDAO;
import com.quanlyquananvat.dao.KhachHangDAO;
import com.quanlyquananvat.dao.LoaiSanPhamDAO;
import com.quanlyquananvat.dao.SanPhamDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.SwingXUtilities;

public class GiaoDich extends javax.swing.JPanel {

    HoaDonChiTietDAO hdctdao = new HoaDonChiTietDAO();
    SanPhamDAO spdao = new SanPhamDAO();
    LoaiSanPhamDAO lspdao = new LoaiSanPhamDAO();
    HoaDonDAO hddao = new HoaDonDAO();
    KhachHangDAO khdao = new KhachHangDAO();

    int row = -1;

    public GiaoDich() {
        initComponents();
        editTable();
        init();
    }

    public void editTable() {

        tblHoaDonChiTiet.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tblHoaDonChiTiet.getTableHeader().setOpaque(false);
        tblHoaDonChiTiet.getTableHeader().setBackground(new Color(0, 102, 255));
        tblHoaDonChiTiet.getTableHeader().setForeground(new Color(0, 0, 0));
        tblHoaDonChiTiet.setRowHeight(25);

        tbtHoaDon.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tbtHoaDon.getTableHeader().setOpaque(false);
        tbtHoaDon.getTableHeader().setBackground(new Color(0, 102, 255));
        tbtHoaDon.getTableHeader().setForeground(new Color(0, 0, 0));
        tbtHoaDon.setRowHeight(25);

        tblSanPham.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        tblSanPham.getTableHeader().setOpaque(false);
        tblSanPham.getTableHeader().setBackground(new Color(0, 102, 255));
        tblSanPham.getTableHeader().setForeground(new Color(0, 0, 0));
        tblSanPham.setRowHeight(25);

        cboLoaiSanPham.setBorder(null);
        cboLoaiSanPham.setBackground(new Color(153, 255, 255));

    }

    public void init() {
        
        fillComboboxLoaiSanPham();

    }

    public void fillTableHoaDonChiTiet(int maHD) {
        DefaultTableModel model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        model.setRowCount(0);
        try {
            List<HoaDonChiTietObject> list = hdctdao.selectByMaHD(maHD);
            for (HoaDonChiTietObject modelHD : list) {
                model.addRow(new Object[]{
                    modelHD.getMaSanPham(),
                    modelHD.getTenSP(),
                    modelHD.getSoLuong(),
                    modelHD.getGia()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.warning(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public void fillTableSanPham() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        try {
            LoaiSanPhamObject lsp = (LoaiSanPhamObject) cboLoaiSanPham.getSelectedItem();
            List<SanPhamObject> listSP = spdao.select_all_lsp(lsp.getMaLoaiSanPham());
            for (SanPhamObject sp : listSP) {
                model.addRow(new Object[]{
                    sp.getMaSP(),
                    sp.getTenSP(),
                    sp.getSoLuong(),
                    sp.getGiaSP()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.warning(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public void fillComboboxLoaiSanPham() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiSanPham.getModel();
        model.removeAllElements();
        try {
            List<LoaiSanPhamObject> categoryList = lspdao.selectAll();
            for (LoaiSanPhamObject category : categoryList) {
                model.addElement(category);
            }
            this.fillTableSanPham();
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.warning(this, "Lỗi truy vấn dữ liệu");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnTrangDau = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnLui = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnTien = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnTrangCuoi = new com.quanlyquananvat.chucNangGiaoDien.Button();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tabbedPaneCustom1 = new com.quanlyquananvat.chucNangGiaoDien.TabbedPaneCustom();
        jPanel2 = new javax.swing.JPanel();
        border_panel2 = new com.quanlyquananvat.chucNangGiaoDien.Border_panel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMaHoaDon = new textfield.TextField();
        txtTenSanPham = new textfield.TextField();
        jLabel13 = new javax.swing.JLabel();
        rdoTienMat = new com.quanlyquananvat.chucNangGiaoDien.RadioButtonCustom();
        rdoChuyenKhoanr = new com.quanlyquananvat.chucNangGiaoDien.RadioButtonCustom();
        txtPhiGiaoNhanh = new textfield.TextField();
        btnTaoDon = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnThanhToan = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnHuyDon = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnInDon = new com.quanlyquananvat.chucNangGiaoDien.Button();
        txtMaNhanVien1 = new textfield.TextField();
        jLabel14 = new javax.swing.JLabel();
        cboDiaChi1 = new javax.swing.JComboBox<>();
        txtKhachHang = new textfield.TextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbtHoaDon = new javax.swing.JTable();
        btnTrangDau1 = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnLui1 = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnTien1 = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnTrangCuoi1 = new com.quanlyquananvat.chucNangGiaoDien.Button();
        jLabel4 = new javax.swing.JLabel();
        tabGiaoDich = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnThemSanPham = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnTrangDau2 = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnLui2 = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnTien2 = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnTrangCuoi3 = new com.quanlyquananvat.chucNangGiaoDien.Button();
        cboLoaiSanPham = new javax.swing.JComboBox<>();

        btnTrangDau.setBackground(new java.awt.Color(67, 110, 238));
        btnTrangDau.setForeground(new java.awt.Color(255, 255, 255));
        btnTrangDau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 159.png"))); // NOI18N
        btnTrangDau.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTrangDau.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTrangDau.setShadowColor(new java.awt.Color(67, 110, 238));
        btnTrangDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangDauActionPerformed(evt);
            }
        });

        btnLui.setBackground(new java.awt.Color(67, 110, 238));
        btnLui.setForeground(new java.awt.Color(255, 255, 255));
        btnLui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 157.png"))); // NOI18N
        btnLui.setToolTipText("");
        btnLui.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnLui.setShadowColor(new java.awt.Color(67, 110, 238));
        btnLui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuiActionPerformed(evt);
            }
        });

        btnTien.setBackground(new java.awt.Color(67, 110, 238));
        btnTien.setForeground(new java.awt.Color(255, 255, 255));
        btnTien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 156.png"))); // NOI18N
        btnTien.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTien.setShadowColor(new java.awt.Color(67, 110, 238));
        btnTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTienActionPerformed(evt);
            }
        });

        btnTrangCuoi.setBackground(new java.awt.Color(67, 110, 238));
        btnTrangCuoi.setForeground(new java.awt.Color(255, 255, 255));
        btnTrangCuoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 158.png"))); // NOI18N
        btnTrangCuoi.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTrangCuoi.setShadowColor(new java.awt.Color(67, 110, 238));
        btnTrangCuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangCuoiActionPerformed(evt);
            }
        });

        jMenuItem1.setText("jMenuItem1");

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 71, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Giao dịch");

        tabbedPaneCustom1.setBackground(new java.awt.Color(242, 242, 242));
        tabbedPaneCustom1.setForeground(new java.awt.Color(0, 0, 0));
        tabbedPaneCustom1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tabbedPaneCustom1.setSelectedColor(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(242, 242, 242));

        border_panel2.setBackground(new java.awt.Color(217, 217, 217));
        border_panel2.setRoundBottomLeft(20);
        border_panel2.setRoundBottomRight(20);
        border_panel2.setRoundTopLeft(20);
        border_panel2.setRoundTopRight(20);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Mã hóa đơn");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Mã nhân viên");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Tên sản phẩm");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Phí giao nhanh");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Phương thức thanh toán");

        txtMaHoaDon.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        txtMaHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHoaDonActionPerformed(evt);
            }
        });

        txtTenSanPham.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Địa chỉ");

        buttonGroup1.add(rdoTienMat);
        rdoTienMat.setForeground(new java.awt.Color(0, 0, 0));
        rdoTienMat.setText("Tiền mặt");
        rdoTienMat.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N

        buttonGroup1.add(rdoChuyenKhoanr);
        rdoChuyenKhoanr.setForeground(new java.awt.Color(0, 0, 0));
        rdoChuyenKhoanr.setText("Chuyển khoản");
        rdoChuyenKhoanr.setFocusable(false);
        rdoChuyenKhoanr.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N

        txtPhiGiaoNhanh.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N

        btnTaoDon.setBackground(new java.awt.Color(167, 228, 254));
        btnTaoDon.setForeground(new java.awt.Color(0, 0, 0));
        btnTaoDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 166.png"))); // NOI18N
        btnTaoDon.setText("Tạo đơn");
        btnTaoDon.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnTaoDon.setRippleColor(new java.awt.Color(255, 255, 255));
        btnTaoDon.setShadowColor(new java.awt.Color(153, 153, 153));
        btnTaoDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoDonActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(167, 228, 254));
        btnThanhToan.setForeground(new java.awt.Color(0, 0, 0));
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 199.png"))); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnThanhToan.setRippleColor(new java.awt.Color(255, 255, 255));
        btnThanhToan.setShadowColor(new java.awt.Color(153, 153, 153));

        btnHuyDon.setBackground(new java.awt.Color(167, 228, 254));
        btnHuyDon.setForeground(new java.awt.Color(0, 0, 0));
        btnHuyDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 201.png"))); // NOI18N
        btnHuyDon.setText("Hủy đơn");
        btnHuyDon.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnHuyDon.setRippleColor(new java.awt.Color(255, 255, 255));
        btnHuyDon.setShadowColor(new java.awt.Color(153, 153, 153));

        btnInDon.setBackground(new java.awt.Color(167, 228, 254));
        btnInDon.setForeground(new java.awt.Color(0, 0, 0));
        btnInDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 202.png"))); // NOI18N
        btnInDon.setText("In đơn");
        btnInDon.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnInDon.setRippleColor(new java.awt.Color(255, 255, 255));
        btnInDon.setShadowColor(new java.awt.Color(153, 153, 153));

        txtMaNhanVien1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tên KH");

        cboDiaChi1.setBackground(new java.awt.Color(255, 255, 255));
        cboDiaChi1.setForeground(new java.awt.Color(0, 0, 0));

        txtKhachHang.setComponentPopupMenu(jPopupMenu2);
        txtKhachHang.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        txtKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtKhachHangMouseClicked(evt);
                txtKhachHangMa(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtKhachHangMousePressed(evt);
            }
        });
        txtKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout border_panel2Layout = new javax.swing.GroupLayout(border_panel2);
        border_panel2.setLayout(border_panel2Layout);
        border_panel2Layout.setHorizontalGroup(
            border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(border_panel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(border_panel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnTaoDon, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuyDon, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnInDon, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(29, Short.MAX_VALUE))
                    .addGroup(border_panel2Layout.createSequentialGroup()
                        .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(border_panel2Layout.createSequentialGroup()
                                .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(67, 67, 67)
                                .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(border_panel2Layout.createSequentialGroup()
                                        .addComponent(txtPhiGiaoNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cboDiaChi1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(border_panel2Layout.createSequentialGroup()
                                        .addComponent(txtMaNhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(border_panel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(rdoTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdoChuyenKhoanr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        border_panel2Layout.setVerticalGroup(
            border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(border_panel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(border_panel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel9)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, border_panel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaNhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(txtPhiGiaoNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDiaChi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(rdoTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoChuyenKhoanr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnHuyDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnInDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTaoDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        tblHoaDonChiTiet.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tblHoaDonChiTiet.setForeground(new java.awt.Color(0, 0, 0));
        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonChiTiet.setRowHeight(30);
        tblHoaDonChiTiet.setShowHorizontalLines(true);
        tblHoaDonChiTiet.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblHoaDonChiTiet);
        if (tblHoaDonChiTiet.getColumnModel().getColumnCount() > 0) {
            tblHoaDonChiTiet.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblHoaDonChiTiet.getColumnModel().getColumn(1).setPreferredWidth(120);
        }

        tbtHoaDon.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tbtHoaDon.setForeground(new java.awt.Color(0, 0, 0));
        tbtHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Tên khách hàng", "Tên nhân viên", "Thành tiền"
            }
        ));
        tbtHoaDon.setRowHeight(30);
        tbtHoaDon.setShowHorizontalLines(true);
        tbtHoaDon.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tbtHoaDon);
        if (tbtHoaDon.getColumnModel().getColumnCount() > 0) {
            tbtHoaDon.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbtHoaDon.getColumnModel().getColumn(1).setPreferredWidth(120);
        }

        btnTrangDau1.setBackground(new java.awt.Color(67, 110, 238));
        btnTrangDau1.setForeground(new java.awt.Color(255, 255, 255));
        btnTrangDau1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 159.png"))); // NOI18N
        btnTrangDau1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTrangDau1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTrangDau1.setShadowColor(new java.awt.Color(67, 110, 238));

        btnLui1.setBackground(new java.awt.Color(67, 110, 238));
        btnLui1.setForeground(new java.awt.Color(255, 255, 255));
        btnLui1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 157.png"))); // NOI18N
        btnLui1.setToolTipText("");
        btnLui1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnLui1.setShadowColor(new java.awt.Color(67, 110, 238));

        btnTien1.setBackground(new java.awt.Color(67, 110, 238));
        btnTien1.setForeground(new java.awt.Color(255, 255, 255));
        btnTien1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 156.png"))); // NOI18N
        btnTien1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTien1.setShadowColor(new java.awt.Color(67, 110, 238));

        btnTrangCuoi1.setBackground(new java.awt.Color(67, 110, 238));
        btnTrangCuoi1.setForeground(new java.awt.Color(255, 255, 255));
        btnTrangCuoi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 158.png"))); // NOI18N
        btnTrangCuoi1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTrangCuoi1.setShadowColor(new java.awt.Color(67, 110, 238));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Thông tin hóa đơn chi tiết");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addComponent(btnTrangDau1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLui1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTrangCuoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(border_panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(border_panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLui1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrangDau1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrangCuoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabbedPaneCustom1.addTab("Hóa đơn", jPanel2);

        tabGiaoDich.setBackground(new java.awt.Color(242, 242, 242));

        tblSanPham.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tblSanPham.setForeground(new java.awt.Color(0, 0, 0));
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setRowHeight(30);
        tblSanPham.setShowHorizontalLines(true);
        tblSanPham.getTableHeader().setReorderingAllowed(false);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSanPhamMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(120);
        }

        jLabel2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Thông tin sản phẩm");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Loại sản phẩm");

        btnThemSanPham.setBackground(new java.awt.Color(0, 204, 0));
        btnThemSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnThemSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/icons8-add-product-30.png"))); // NOI18N
        btnThemSanPham.setText("Thêm sản phẩm");
        btnThemSanPham.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnThemSanPham.setShadowColor(new java.awt.Color(51, 153, 0));
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        btnTrangDau2.setBackground(new java.awt.Color(67, 110, 238));
        btnTrangDau2.setForeground(new java.awt.Color(255, 255, 255));
        btnTrangDau2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 159.png"))); // NOI18N
        btnTrangDau2.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTrangDau2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTrangDau2.setShadowColor(new java.awt.Color(67, 110, 238));

        btnLui2.setBackground(new java.awt.Color(67, 110, 238));
        btnLui2.setForeground(new java.awt.Color(255, 255, 255));
        btnLui2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 157.png"))); // NOI18N
        btnLui2.setToolTipText("");
        btnLui2.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnLui2.setShadowColor(new java.awt.Color(67, 110, 238));

        btnTien2.setBackground(new java.awt.Color(67, 110, 238));
        btnTien2.setForeground(new java.awt.Color(255, 255, 255));
        btnTien2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 156.png"))); // NOI18N
        btnTien2.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTien2.setShadowColor(new java.awt.Color(67, 110, 238));

        btnTrangCuoi3.setBackground(new java.awt.Color(67, 110, 238));
        btnTrangCuoi3.setForeground(new java.awt.Color(255, 255, 255));
        btnTrangCuoi3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 158.png"))); // NOI18N
        btnTrangCuoi3.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTrangCuoi3.setShadowColor(new java.awt.Color(67, 110, 238));

        cboLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cboLoaiSanPham.setForeground(new java.awt.Color(0, 0, 0));
        cboLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabGiaoDichLayout = new javax.swing.GroupLayout(tabGiaoDich);
        tabGiaoDich.setLayout(tabGiaoDichLayout);
        tabGiaoDichLayout.setHorizontalGroup(
            tabGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabGiaoDichLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabGiaoDichLayout.createSequentialGroup()
                        .addComponent(btnTrangDau2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLui2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnTien2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTrangCuoi3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                        .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabGiaoDichLayout.createSequentialGroup()
                        .addGroup(tabGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(tabGiaoDichLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(cboLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabGiaoDichLayout.setVerticalGroup(
            tabGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabGiaoDichLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(tabGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnLui2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTrangDau2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTien2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTrangCuoi3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        tabbedPaneCustom1.addTab("Sản phẩm", tabGiaoDich);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHoaDonActionPerformed

    private void btnTrangDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangDauActionPerformed

    }//GEN-LAST:event_btnTrangDauActionPerformed

    private void btnLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiActionPerformed

    }//GEN-LAST:event_btnLuiActionPerformed

    private void btnTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienActionPerformed

    }//GEN-LAST:event_btnTienActionPerformed

    private void btnTrangCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangCuoiActionPerformed

    }//GEN-LAST:event_btnTrangCuoiActionPerformed

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        addSanPham();
    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void cboLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSanPhamActionPerformed
        fillTableSanPham();
    }//GEN-LAST:event_cboLoaiSanPhamActionPerformed

    private void btnTaoDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoDonActionPerformed
        taoDon();
    }//GEN-LAST:event_btnTaoDonActionPerformed

    private void txtKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtKhachHangMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem menuItem = new JMenuItem("chọn khách hàng");
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DanhSachKhachHang myDialog = new DanhSachKhachHang((JFrame) SwingUtilities.getWindowAncestor(GiaoDich.this), true);
                    myDialog.setVisible(true);
                    txtKhachHang.setText(GiaoDichObject.getSelectValue());
                }
            });
            popupMenu.add(menuItem);
            popupMenu.show(txtKhachHang, evt.getX(), evt.getY());
        }

    }//GEN-LAST:event_txtKhachHangMouseClicked


    private void txtKhachHangMa(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtKhachHangMa
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKhachHangMa

    private void txtKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtKhachHangMousePressed

    }//GEN-LAST:event_txtKhachHangMousePressed

    private void txtKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhachHangActionPerformed

    }//GEN-LAST:event_txtKhachHangActionPerformed

    private void tblSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMousePressed
        if (evt.getClickCount() == 2) {
           this.row = tblSanPham.rowAtPoint(evt.getPoint());
           edit();
        }
    }//GEN-LAST:event_tblSanPhamMousePressed

    private void edit() {
        int soLuong = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập số lượng !"));
        String maSP = (String) tblSanPham.getValueAt(this.row, 0);
        String tenSP = (String)tblSanPham.getValueAt(row, 1);
        double giaSP = (double)tblSanPham.getValueAt(row, 3);
        double tongTien = (soLuong * giaSP);
        int soLuongSP = (int) tblSanPham.getValueAt(row, 2);
        SanPhamObject sp = spdao.selectById(maSP);
        sp.setSoLuong(soLuongSP - soLuong);
        spdao.update(sp);
        if (sp != null) {
            HoaDonChiTietObject hdct = new HoaDonChiTietObject();
            hdct.setMaHoaDon(Integer.parseInt(txtMaHoaDon.getText()));
            hdct.setMaSanPham(maSP);
            hdct.setGia(giaSP);
            hdct.setTenSP(tenSP);
            hdct.setSoLuong(soLuong);
            hdct.setTongTien(tongTien);
            hdctdao.insert(hdct);
            tabbedPaneCustom1.setSelectedIndex(0);
            this.fillTableHoaDonChiTiet(Integer.parseInt(txtMaHoaDon.getText()));
            this.fillTableSanPham();
        }
        
    }

    public void addSanPham() {

    }

    public void taoDon() {
        HoaDonObject hdo = getForm();
        txtMaHoaDon.setText(hddao.hoaDonMoi().get(0) + "");
        txtMaNhanVien1.setText(Auth.user.getMaNV());
        hdo.setMaNV(Auth.user.getMaNV());
        hddao.insert(hdo);

    }

    private HoaDonObject getForm() {
        HoaDonObject hdo = new HoaDonObject();
        hdo.setMaKH(txtKhachHang.getText());
        hdo.setNgayTao(XDate.now());
        hdo.setPhiGiaoNhanh(0);
        String phuongThuc = "";
        if (rdoTienMat.isSelected()) {
            phuongThuc = "TT001";
            hdo.setMaThanhToan(phuongThuc);
        } else {
            phuongThuc = "TT002";
            hdo.setMaThanhToan(phuongThuc);
        }
        return hdo;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.quanlyquananvat.chucNangGiaoDien.Border_panel border_panel2;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnHuyDon;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnInDon;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnLui;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnLui1;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnLui2;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTaoDon;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnThanhToan;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnThemSanPham;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTien;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTien1;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTien2;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTrangCuoi;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTrangCuoi1;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTrangCuoi3;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTrangDau;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTrangDau1;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTrangDau2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboDiaChi1;
    private javax.swing.JComboBox<String> cboLoaiSanPham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private com.quanlyquananvat.chucNangGiaoDien.RadioButtonCustom rdoChuyenKhoanr;
    private com.quanlyquananvat.chucNangGiaoDien.RadioButtonCustom rdoTienMat;
    private javax.swing.JPanel tabGiaoDich;
    private com.quanlyquananvat.chucNangGiaoDien.TabbedPaneCustom tabbedPaneCustom1;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tbtHoaDon;
    private textfield.TextField txtKhachHang;
    private textfield.TextField txtMaHoaDon;
    private textfield.TextField txtMaNhanVien1;
    private textfield.TextField txtPhiGiaoNhanh;
    private textfield.TextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables
}
