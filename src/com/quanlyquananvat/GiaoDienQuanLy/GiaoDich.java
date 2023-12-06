package com.quanlyquananvat.GiaoDienQuanLy;

import com.quanlyquananvat.Object.GiaoDichObject;
import com.quanlyquananvat.Object.HoaDonChiTietObject;
import com.quanlyquananvat.Object.HoaDonObject;
import com.quanlyquananvat.Object.HuyDonObject;
import com.quanlyquananvat.Object.InHoaDonObject;
import com.quanlyquananvat.Object.KhachHangObject;
import com.quanlyquananvat.Object.LoaiSanPhamObject;
import com.quanlyquananvat.Object.SanPhamObject;
import com.quanlyquananvat.ThuVienTienIch.Auth;
import com.quanlyquananvat.ThuVienTienIch.MsgBox;
import com.quanlyquananvat.ThuVienTienIch.NumberFormatter;
import com.quanlyquananvat.ThuVienTienIch.XDate;
import com.quanlyquananvat.dao.HoaDonChiTietDAO;
import com.quanlyquananvat.dao.HoaDonDAO;
import com.quanlyquananvat.dao.KhachHangDAO;
import com.quanlyquananvat.dao.LoaiSanPhamDAO;
import com.quanlyquananvat.dao.NhanVienDAO;
import com.quanlyquananvat.dao.SanPhamDAO;
import com.sun.source.tree.DoWhileLoopTree;
import com.sun.source.tree.IfTree;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.SwingXUtilities;

public class GiaoDich extends javax.swing.JPanel {

    private Map<String, Integer> phiGiaoNhanhMap;
    HoaDonChiTietDAO hdctdao = new HoaDonChiTietDAO();
    SanPhamDAO spdao = new SanPhamDAO();
    LoaiSanPhamDAO lspdao = new LoaiSanPhamDAO();
    HoaDonDAO hddao = new HoaDonDAO();
    KhachHangDAO khdao = new KhachHangDAO();

    int row = 0;

    public GiaoDich() {
        initComponents();
        khoiTaoDuLieu();
        editTable();
        init();
        getMaHD();
    }

    public void editTable() {

        tblHoaDonChiTiet.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tblHoaDonChiTiet.getTableHeader().setOpaque(false);
        tblHoaDonChiTiet.getTableHeader().setBackground(new Color(0, 102, 255));
        tblHoaDonChiTiet.getTableHeader().setForeground(new Color(0, 0, 0));
        tblHoaDonChiTiet.setRowHeight(25);

        tblHoaDon.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tblHoaDon.getTableHeader().setOpaque(false);
        tblHoaDon.getTableHeader().setBackground(new Color(0, 102, 255));
        tblHoaDon.getTableHeader().setForeground(new Color(0, 0, 0));
        tblHoaDon.setRowHeight(25);

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
        capNhatComboBox();
        txtPhiGiaoNhanh.setEnabled(false);
        txtTongTien.setEnabled(false);
//        txtKhachHang.setEnabled(false);
        txtMaNhanVien1.setEnabled(false);
        txtMaHoaDon.setEnabled(false);
        fillTableHoaDon();
    }

    public void fillTableHoaDonChiTiet(int maHD) {
        DefaultTableModel model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        model.setRowCount(0);
        try {
            double tongtienHD = 0;
            List<HoaDonChiTietObject> list = hdctdao.selectByMaHD(maHD);
            for (HoaDonChiTietObject modelHD : list) {
                tongtienHD += modelHD.getTongTien();
                model.addRow(new Object[]{
                    modelHD.getMaHoaDonChiTiet(),
                    modelHD.getMaSanPham(),
                    modelHD.getTenSP(),
                    modelHD.getSoLuong(),
                    modelHD.getGia(),
                    NumberFormatter.formatCurrency(modelHD.getTongTien())
                });
                HuyDonObject.setMaSP(modelHD.getMaSanPham());
                HuyDonObject.setSoLuong(modelHD.getSoLuong());
                HuyDonObject.setMaHDCT(modelHD.getMaHoaDonChiTiet());
            }
            // Đặt giá trị tổng tiền sau khi đã thêm tất cả dòng
            txtTongTien.setText(NumberFormatter.formatCurrency(tongtienHD));
            GiaoDichObject.setTongTien(tongtienHD);
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.warning(this, "Lỗi truy vấn dữ liệu");
        }
    }

    private void fillTableHoaDon(int maHD) {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        try {
            HoaDonObject modelHD = hddao.selectById(maHD);
            if (modelHD != null) {
                model.addRow(new Object[]{
                    modelHD.getMaHD(),
                    txtKhachHang.getText(),
                    modelHD.getMaNV(),});
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jPopupMenu5 = new javax.swing.JPopupMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tabbedPaneCustom1 = new com.quanlyquananvat.chucNangGiaoDien.TabbedPaneCustom();
        jPanel2 = new javax.swing.JPanel();
        border_panel2 = new com.quanlyquananvat.chucNangGiaoDien.Border_panel();
        jLabel16 = new javax.swing.JLabel();
        txtKhachHang = new textfield.TextField();
        txtMaNhanVien1 = new textfield.TextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtMaHoaDon = new textfield.TextField();
        rdoChuyenKhoanr1 = new com.quanlyquananvat.chucNangGiaoDien.RadioButtonCustom();
        rdoTienMat = new com.quanlyquananvat.chucNangGiaoDien.RadioButtonCustom();
        jLabel18 = new javax.swing.JLabel();
        ckeGiaoNhanh = new com.quanlyquananvat.chucNangGiaoDien.JCheckBoxCustom();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cboDiaChi1 = new javax.swing.JComboBox<>();
        txtPhiGiaoNhanh = new textfield.TextField();
        btnTaoDon = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnThanhToan = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnHuyDon = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnInDon = new com.quanlyquananvat.chucNangGiaoDien.Button();
        jLabel5 = new javax.swing.JLabel();
        txtTongTien = new textfield.TextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        tabGiaoDich = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
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

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseClicked(evt);
            }
        });

        jMenuItem4.setText("jMenuItem4");

        jMenuItem5.setText("jMenuItem5");
        jMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem5MouseClicked(evt);
            }
        });

        jMenuItem6.setText("jMenuItem6");

        jMenuItem7.setText("jMenuItem7");
        jPopupMenu5.add(jMenuItem7);

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 71, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/icons8-transaction-70.png"))); // NOI18N
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

        jLabel16.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Tên khách hàng");

        txtKhachHang.setComponentPopupMenu(jPopupMenu2);
        txtKhachHang.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        txtKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtKhachHangMouseClicked(evt);
            }
        });

        txtMaNhanVien1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Mã nhân viên");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Mã hóa đơn");

        txtMaHoaDon.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N

        buttonGroup1.add(rdoChuyenKhoanr1);
        rdoChuyenKhoanr1.setForeground(new java.awt.Color(0, 0, 0));
        rdoChuyenKhoanr1.setText("Chuyển khoản");
        rdoChuyenKhoanr1.setFocusable(false);
        rdoChuyenKhoanr1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        rdoChuyenKhoanr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChuyenKhoanr1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoTienMat);
        rdoTienMat.setForeground(new java.awt.Color(0, 0, 0));
        rdoTienMat.setText("Tiền mặt");
        rdoTienMat.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Phương thức thanh toán");

        ckeGiaoNhanh.setForeground(new java.awt.Color(0, 0, 0));
        ckeGiaoNhanh.setText("Giao hàng nhanh+(20.000vnd)");
        ckeGiaoNhanh.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ckeGiaoNhanh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ckeGiaoNhanhItemStateChanged(evt);
            }
        });
        ckeGiaoNhanh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ckeGiaoNhanhKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Vận chuyển");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Phí giao hàng");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Địa chỉ");

        cboDiaChi1.setBackground(new java.awt.Color(255, 255, 255));
        cboDiaChi1.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        cboDiaChi1.setForeground(new java.awt.Color(0, 0, 0));
        cboDiaChi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDiaChi1ActionPerformed(evt);
            }
        });

        txtPhiGiaoNhanh.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        txtPhiGiaoNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhiGiaoNhanhActionPerformed(evt);
            }
        });

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
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuyDon.setBackground(new java.awt.Color(167, 228, 254));
        btnHuyDon.setForeground(new java.awt.Color(0, 0, 0));
        btnHuyDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 201.png"))); // NOI18N
        btnHuyDon.setText("Hủy đơn");
        btnHuyDon.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnHuyDon.setRippleColor(new java.awt.Color(255, 255, 255));
        btnHuyDon.setShadowColor(new java.awt.Color(153, 153, 153));
        btnHuyDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyDonActionPerformed(evt);
            }
        });

        btnInDon.setBackground(new java.awt.Color(167, 228, 254));
        btnInDon.setForeground(new java.awt.Color(0, 0, 0));
        btnInDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 202.png"))); // NOI18N
        btnInDon.setText("Mới");
        btnInDon.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnInDon.setRippleColor(new java.awt.Color(255, 255, 255));
        btnInDon.setShadowColor(new java.awt.Color(153, 153, 153));
        btnInDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInDonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tổng tiền");

        txtTongTien.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        txtTongTien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTongTienKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout border_panel2Layout = new javax.swing.GroupLayout(border_panel2);
        border_panel2.setLayout(border_panel2Layout);
        border_panel2Layout.setHorizontalGroup(
            border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(border_panel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(border_panel2Layout.createSequentialGroup()
                        .addComponent(rdoTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(rdoChuyenKhoanr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(border_panel2Layout.createSequentialGroup()
                        .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, border_panel2Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, border_panel2Layout.createSequentialGroup()
                                    .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel17))
                                    .addGap(23, 23, 23)
                                    .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                        .addComponent(txtMaNhanVien1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(18, 18, 18)
                        .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(border_panel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(border_panel2Layout.createSequentialGroup()
                                .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel15))
                                .addGap(18, 18, 18)
                                .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPhiGiaoNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboDiaChi1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(border_panel2Layout.createSequentialGroup()
                                .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(ckeGiaoNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(border_panel2Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(btnTaoDon, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHuyDon, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInDon, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        border_panel2Layout.setVerticalGroup(
            border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(border_panel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(border_panel2Layout.createSequentialGroup()
                        .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtMaNhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel5)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(border_panel2Layout.createSequentialGroup()
                        .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cboDiaChi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtPhiGiaoNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ckeGiaoNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoChuyenKhoanr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tblHoaDonChiTiet.setBackground(new java.awt.Color(242, 242, 242));
        tblHoaDonChiTiet.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tblHoaDonChiTiet.setForeground(new java.awt.Color(0, 0, 0));
        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HDCT ", "Mã sản phẩm", "Tên SP", "Số lượng", "Giá", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonChiTiet.setComponentPopupMenu(jPopupMenu1);
        tblHoaDonChiTiet.setRowHeight(30);
        tblHoaDonChiTiet.getTableHeader().setReorderingAllowed(false);
        tblHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChiTietMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHoaDonChiTietMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDonChiTiet);
        if (tblHoaDonChiTiet.getColumnModel().getColumnCount() > 0) {
            tblHoaDonChiTiet.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblHoaDonChiTiet.getColumnModel().getColumn(1).setPreferredWidth(120);
        }

        tblHoaDon.setBackground(new java.awt.Color(242, 242, 242));
        tblHoaDon.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tblHoaDon.setForeground(new java.awt.Color(0, 0, 0));
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Mã nhân viên", "Tên khách hàng", "Phương thức TT", "Phí giao hàng", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setRowHeight(30);
        tblHoaDon.setShowGrid(false);
        tblHoaDon.getTableHeader().setReorderingAllowed(false);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHoaDonMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(tblHoaDon);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Thông tin hóa đơn chi tiết");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(border_panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane4)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(border_panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPaneCustom1.addTab("Hóa đơn", jPanel2);

        tabGiaoDich.setBackground(new java.awt.Color(242, 242, 242));

        tblSanPham.setBackground(new java.awt.Color(242, 242, 242));
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
                        .addGroup(tabGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(tabGiaoDichLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(cboLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabGiaoDichLayout.createSequentialGroup()
                .addContainerGap(235, Short.MAX_VALUE)
                .addComponent(btnTrangDau2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLui2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnTien2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTrangCuoi3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(199, 199, 199))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(tabGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnLui2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTrangDau2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTien2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTrangCuoi3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

    private void btnTrangDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangDauActionPerformed

    }//GEN-LAST:event_btnTrangDauActionPerformed

    private void btnLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiActionPerformed

    }//GEN-LAST:event_btnLuiActionPerformed

    private void btnTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienActionPerformed

    }//GEN-LAST:event_btnTienActionPerformed

    private void btnTrangCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangCuoiActionPerformed

    }//GEN-LAST:event_btnTrangCuoiActionPerformed

    private void cboLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSanPhamActionPerformed
        fillTableSanPham();
    }//GEN-LAST:event_cboLoaiSanPhamActionPerformed

    private void tblSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMousePressed
        if (evt.getClickCount() == 2) {
            this.row = tblSanPham.rowAtPoint(evt.getPoint());
            edit();

        }
    }//GEN-LAST:event_tblSanPhamMousePressed

    private void txtPhiGiaoNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhiGiaoNhanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhiGiaoNhanhActionPerformed

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

    private void btnTaoDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoDonActionPerformed
        taoDon();
    }//GEN-LAST:event_btnTaoDonActionPerformed

    private void cboDiaChi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDiaChi1ActionPerformed
        layGiaTri();
    }//GEN-LAST:event_cboDiaChi1ActionPerformed

    private void rdoChuyenKhoanr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChuyenKhoanr1ActionPerformed

    }//GEN-LAST:event_rdoChuyenKhoanr1ActionPerformed

    private void ckeGiaoNhanhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ckeGiaoNhanhKeyReleased

    }//GEN-LAST:event_ckeGiaoNhanhKeyReleased

    private void ckeGiaoNhanhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ckeGiaoNhanhItemStateChanged
        layGiaTri();
    }//GEN-LAST:event_ckeGiaoNhanhItemStateChanged

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        thanhToan();

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnHuyDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyDonActionPerformed
        huyDon();
    }//GEN-LAST:event_btnHuyDonActionPerformed

    private void huyDon() {
        String maHDText = txtMaHoaDon.getText().trim();

        // Kiểm tra xem mã hóa đơn có được nhập hay không
        if (maHDText.isEmpty()) {
            MsgBox.warning(this, "Vui lòng nhập mã hóa đơn!");
            return; // Dừng hàm nếu không có mã hóa đơn
        }

        try {
            int maHD = Integer.parseInt(maHDText);

            // Khởi tạo danh sách sản phẩm cần cập nhật
            List<SanPhamObject> sanPhamCapNhat = new ArrayList<>();

            // Thêm sản phẩm cần cập nhật vào danh sách
            List<HoaDonChiTietObject> list = hdctdao.selectByMaHD(maHD);
            for (HoaDonChiTietObject modelHD : list) {
                String maSpHD = modelHD.getMaSanPham();
                int soLuongHD = modelHD.getSoLuong();

                // Kiểm tra xem sản phẩm có tồn tại trong danh sách cần cập nhật không
                boolean daCoTrongDanhSach = false;
                for (SanPhamObject sp : sanPhamCapNhat) {
                    if (sp.getMaSP().equals(maSpHD)) {
                        sp.setSoLuong(sp.getSoLuong() + soLuongHD);
                        daCoTrongDanhSach = true;
                        break;
                    }
                }

                // Nếu chưa có trong danh sách, thêm sản phẩm vào danh sách cần cập nhật
                if (!daCoTrongDanhSach) {
                    SanPhamObject spHD = spdao.selectSanPhamById(maSpHD);
                    if (spHD != null) {
                        spHD.setSoLuong(spHD.getSoLuong() + soLuongHD);
                        sanPhamCapNhat.add(spHD);
                    }
                }
            }

            // Thực hiện xóa đơn
            hddao.delete(maHD);

            // Duyệt qua danh sách sản phẩm cần cập nhật và thực hiện cập nhật
            for (SanPhamObject sp : sanPhamCapNhat) {
                spdao.update(sp);
            }

            // Cập nhật bảng hóa đơn
            fillTableHoaDon();
            // Cập nhật bảng sản phẩm
            fillTableSanPham();

            // Xóa các thông tin trên form
            clearnForm();

            MsgBox.alert(this, "Hủy đơn thành công!");
        } catch (NumberFormatException e) {
            // Xử lý khi không thể chuyển đổi thành số
            MsgBox.warning(this, "Mã hóa đơn không hợp lệ!");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.warning(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    private void clearnForm() {
        txtMaHoaDon.setText("");
        txtKhachHang.setText("");
        txtMaNhanVien1.setText("");
        buttonGroup1.clearSelection();
        txtPhiGiaoNhanh.setText("");
        ckeGiaoNhanh.setSelected(false);
        txtTongTien.setText("");
        cboDiaChi1.setSelectedIndex(0);
        DefaultTableModel model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        model.setRowCount(0);
    }
    private void tblHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMousePressed
        if (evt.getClickCount() == 2) {

        }
    }//GEN-LAST:event_tblHoaDonMousePressed

    private void btnInDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInDonActionPerformed
        clearnForm();
    }//GEN-LAST:event_btnInDonActionPerformed

    private void jMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseClicked

    }//GEN-LAST:event_jMenuItem3MouseClicked

    private void tblHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem menuItem = new JMenuItem("Xen thông tin chi tiết");
            JMenuItem menuItem1 = new JMenuItem("Xóa sản phẩm đã thêm");
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ThongTinHoaDonChiTietTongQuat myDialog = new ThongTinHoaDonChiTietTongQuat((JFrame) SwingUtilities.getWindowAncestor(GiaoDich.this), true);
                    myDialog.setVisible(true);

                }
            });
            menuItem1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lấy hàng được chọn
                    int selectedRow = tblHoaDonChiTiet.getSelectedRow();
                    if (selectedRow != -1) {
                        maHDCT = (int) tblHoaDonChiTiet.getValueAt(selectedRow, 0);
                        // Gọi hàm xóa hóa đơn
                        xoaHoaDon();
                    } else {
                        MsgBox.warning(GiaoDich.this, "Vui lòng chọn hóa đơn chi tiết cần xóa!");
                    }
                }
            }
            );

            popupMenu.add(menuItem);
            popupMenu.add(menuItem1);
            popupMenu.show(tblHoaDonChiTiet, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblHoaDonChiTietMouseClicked
    int maHDCT = 0; // Đảm bảo khởi tạo giá trị ban đầu

    public void xoaHoaDon() {
        // Kiểm tra xem đã chọn hóa đơn chi tiết chưa
        if (maHDCT == 0) {
            MsgBox.warning(this, "Vui lòng chọn hóa đơn chi tiết cần xóa!");
            return;
        }

        try {
            String maSp = HuyDonObject.getMaSP();
            int soLuongHuy = HuyDonObject.getSoLuong();

            // Lấy thông tin hóa đơn chi tiết từ CSDL
            HoaDonChiTietObject hdct = hdctdao.select_HoaDonChiTietObject(maHDCT);

            // Kiểm tra xem hóa đơn chi tiết có tồn tại không
            if (hdct == null) {
                MsgBox.warning(this, "Hóa đơn chi tiết không tồn tại!");
                return;
            }

            // Lấy thông tin sản phẩm từ hóa đơn chi tiết
            SanPhamObject sp1 = spdao.selectSanPhamById(hdct.getMaSanPham());

            // Kiểm tra xem sản phẩm có tồn tại không
            if (sp1 == null) {
                MsgBox.warning(this, "Sản phẩm không tồn tại!");
                return;
            }

            // Cập nhật số lượng của sản phẩm
            sp1.setSoLuong(sp1.getSoLuong() + soLuongHuy);

            spdao.update(sp1);
            this.fillTableSanPham();
            // Xóa hóa đơn chi tiết
            hdctdao.delete(maHDCT);
            // Xóa thành công, cập nhật bảng hóa đơn chi tiết
            this.fillTableHoaDonChiTiet(Integer.parseInt(txtMaHoaDon.getText()));
            MsgBox.alert(this, "Xóa hóa đơn chi tiết thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.error(this, "Lỗi truy vấn dữ liệu!");
        }
    }


    private void jMenuItem5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem5MouseClicked


    }//GEN-LAST:event_jMenuItem5MouseClicked

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        row = tblHoaDon.getSelectedRow();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtTongTienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTongTienKeyReleased
        layGiaTri();
    }//GEN-LAST:event_txtTongTienKeyReleased

    private void tblHoaDonChiTietMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMousePressed

    }//GEN-LAST:event_tblHoaDonChiTietMousePressed
    public int selectedMaHD = -1;
    public String selectTenKH = "";

    public void getMaHD() {
        tblHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(row);
                if (row != -1) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        JPopupMenu popupMenu = new JPopupMenu();
                        JMenuItem menuItem = new JMenuItem("In hóa đơn");
                        menuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ChiTietHoaDon myDialog = new ChiTietHoaDon((JFrame) SwingUtilities.getWindowAncestor(GiaoDich.this), true);
                                myDialog.setVisible(true);

                            }
                        });
                        popupMenu.add(menuItem);
                        popupMenu.show(tblHoaDon, e.getX(), e.getY());
                    }
                    selectedMaHD = (int) tblHoaDon.getValueAt(row, 0);
                    GiaoDichObject.setMaHD(selectedMaHD);
                    selectTenKH = (String) tblHoaDon.getValueAt(row, 2);
                    GiaoDichObject.setMaKH(khdao.select_MaKH(selectTenKH));
                }
            }
        });

    }

    public HoaDonObject getHoaDon() {
        HoaDonObject hd = new HoaDonObject();
        String MaKH = hddao.layMaKhachHang(txtKhachHang.getText());
        hd.setMaKH(MaKH);
        hd.setMaNV(Auth.user.getMaNV());
        String ptttt = "";
        if (rdoTienMat.isSelected()) {
            ptttt += "TT001";
        } else {
            ptttt += "TT002";
        }
        hd.setNgayTao(XDate.now());
        hd.setMaThanhToan(ptttt);
        hd.setDiaChi(cboDiaChi1.getSelectedItem().toString());
        hd.setPhiGiaoNhanh(Double.parseDouble(NumberFormatter.replaceCommaWithDot(txtPhiGiaoNhanh.getText())));
        hd.setTongTien(Double.parseDouble(NumberFormatter.replaceCommaWithDot(txtTongTien.getText())));
        System.out.println(hd.toString());
        return hd;
    }

    public void thanhToan() {
        String maHD1 = txtMaHoaDon.getText();
        String maNV = txtMaNhanVien1.getText();
        String maKhachHang = txtKhachHang.getText();
        if (maHD1.isEmpty() || maNV.isEmpty() || maKhachHang.isEmpty()) {
            MsgBox.error(this, "Vui lòng tạo hóa đơn trước khi thanh toán!");
            return;
        }
        int diaChi = cboDiaChi1.getSelectedIndex();
        if (diaChi == 0) {
            MsgBox.warning(this, "Vui lòng chọn địa chỉ để thực hiện thanh toán!");
            return;
        }
        try {
            int maHD = Integer.parseInt(txtMaHoaDon.getText());
            HoaDonObject hd = getHoaDon();
            hd.setMaHD(maHD);
            hddao.update(hd);
            this.fillTableHoaDon();
            this.clearnForm();
            MsgBox.alert(this, "Thanh toán thành công !");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.error(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    private void edit() {
        try {
            if (txtMaHoaDon.getText().isEmpty()) {
                MsgBox.warning(this, "Vui lòng tạo hóa đơn trước khi thêm sản phẩm");
                return;
            }
            // Nhập số lượng từ người dùng
            String soLuongStr = JOptionPane.showInputDialog(this, "Nhập số lượng!");

            // Kiểm tra xem người dùng đã nhập hay chưa
            if (soLuongStr == null || soLuongStr.trim().isEmpty()) {
                return;
            }
            int soLuong = 0;
            try {
                soLuong = Integer.parseInt(soLuongStr);

                // Kiểm tra xem số lượng có là số nguyên dương hay không
                if (soLuong <= 0) {
                    MsgBox.alert(this, "Số lượng phải là một số nguyên dương");
                    return;
                }

                // Lấy thông tin sản phẩm từ dòng đã chọn
                String maSP = (String) tblSanPham.getValueAt(row, 0);
                String tenSP = (String) tblSanPham.getValueAt(row, 1);
                double giaSP = (double) tblSanPham.getValueAt(row, 3);
                int soLuongSP = (int) tblSanPham.getValueAt(row, 2);

                // Kiểm tra xem số lượng có đủ để bán không
                if (soLuong > soLuongSP) {
                    MsgBox.alert(this, "Số lượng không đủ để bán");
                    return;
                }

                // Lấy thông tin sản phẩm từ cơ sở dữ liệu
                SanPhamObject sp = spdao.selectById(maSP);

                // Cập nhật số lượng của sản phẩm trong cơ sở dữ liệu
                if (sp != null) {
                    sp.setSoLuong(soLuongSP - soLuong);
                    spdao.update(sp);

                    // Tạo đối tượng HoaDonChiTietObject và thêm vào cơ sở dữ liệu
                    HoaDonChiTietObject hdct = new HoaDonChiTietObject();
                    hdct.setMaHoaDon(Integer.parseInt(txtMaHoaDon.getText()));
                    hdct.setMaSanPham(maSP);
                    hdct.setGia(giaSP);
                    hdct.setTenSP(tenSP);
                    hdct.setSoLuong(soLuong);
                    hdct.setTongTien(soLuong * giaSP);
                    hdctdao.insert(hdct);

                    // Cập nhật giao diện
                    tabbedPaneCustom1.setSelectedIndex(0);
                    this.fillTableHoaDonChiTiet(Integer.parseInt(txtMaHoaDon.getText()));
                    this.fillTableSanPham();
                } else {
                    MsgBox.alert(this, "Không tìm thấy sản phẩm trong cơ sở dữ liệu");
                }
            } catch (NumberFormatException ex) {
                MsgBox.alert(this, "Số lượng phải là một số nguyên");
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.warning(this, "Đã xảy ra lỗi khi thực hiện thao tác");
        }
    }

    public void khoiTaoDuLieu() {
        phiGiaoNhanhMap = new HashMap<>();
        phiGiaoNhanhMap.put("Hà Nội", 20);
        phiGiaoNhanhMap.put("TP.Hồ Chí Minh", 25);
        phiGiaoNhanhMap.put("Hải Phòng", 18);
        phiGiaoNhanhMap.put("Đà Nẵng", 22);
        phiGiaoNhanhMap.put("Hưng Yên", 21);
        phiGiaoNhanhMap.put("Bắc Ninh", 19);
        phiGiaoNhanhMap.put("Thái Bình", 17);
        phiGiaoNhanhMap.put("Nam Định", 18);
        phiGiaoNhanhMap.put("Ninh Bình", 20);
        phiGiaoNhanhMap.put("Vĩnh Phúc", 23);
        phiGiaoNhanhMap.put("Bắc Giang", 19);
        phiGiaoNhanhMap.put("Nghệ An", 26);
        phiGiaoNhanhMap.put("Hà Tĩnh", 24);
        phiGiaoNhanhMap.put("Quảng Bình", 27);
        phiGiaoNhanhMap.put("Quảng Trị", 25);
        phiGiaoNhanhMap.put("Thừa Thiên Huế", 23);
        phiGiaoNhanhMap.put("Quảng Nam", 22);
        phiGiaoNhanhMap.put("Quảng Ngãi", 24);
        phiGiaoNhanhMap.put("Bình Định", 26);
        phiGiaoNhanhMap.put("Phú Yên", 28);
        phiGiaoNhanhMap.put("Khánh Hòa", 30);
        phiGiaoNhanhMap.put("Ninh Thuận", 32);
        phiGiaoNhanhMap.put("Bình Thuận", 35);
        phiGiaoNhanhMap.put("Kon Tum", 38);
        phiGiaoNhanhMap.put("Gia Lai", 40);
        phiGiaoNhanhMap.put("Đắk Lắk", 42);
        phiGiaoNhanhMap.put("Đắk Nông", 45);
        phiGiaoNhanhMap.put("Lâm Đồng", 48);
        phiGiaoNhanhMap.put("Bình Phước", 50);
        phiGiaoNhanhMap.put("Tây Ninh", 52);
        phiGiaoNhanhMap.put("Bình Dương", 55);
        phiGiaoNhanhMap.put("Đồng Nai", 58);
        phiGiaoNhanhMap.put("Bà Rịa - Vũng Tàu", 60);
        phiGiaoNhanhMap.put("Long An", 62);
        phiGiaoNhanhMap.put("Tiền Giang", 64);
        phiGiaoNhanhMap.put("Bến Tre", 66);
        phiGiaoNhanhMap.put("Trà Vinh", 68);
        phiGiaoNhanhMap.put("Vĩnh Long", 70);
        phiGiaoNhanhMap.put("Đồng Tháp", 72);
        phiGiaoNhanhMap.put("An Giang", 74);
        phiGiaoNhanhMap.put("Kiên Giang", 76);
        phiGiaoNhanhMap.put("Cần Thơ", 10);
        phiGiaoNhanhMap.put("Hậu Giang", 39);
        phiGiaoNhanhMap.put("Sóc Trăng", 82);
        phiGiaoNhanhMap.put("Bạc Liêu", 12);
        phiGiaoNhanhMap.put("Cà Mau", 23);
        // Thêm các tỉnh thành khác vào đây với giá phí tương ứng
    }

    private void capNhatComboBox() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Chọn tỉnh giao hàng");
        for (String tinhThanh : phiGiaoNhanhMap.keySet()) {
            model.addElement(tinhThanh);
        }
        cboDiaChi1.setModel(model);
    }

    public HoaDonObject getForm() {
        HoaDonObject hdo = new HoaDonObject();
        hdo.setMaKH(txtKhachHang.getText());
        hdo.setNgayTao(XDate.now());
        hdo.setPhiGiaoNhanh(0);
        hdo.setDiaChi(null);
        hdo.setTongTien(0);
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

    public void taoDon() {
        if (txtKhachHang.getText().isEmpty()) {
            MsgBox.warning(this, "Vui lòng chọn khách hàng trước khi tạo hóa đơn!");
            return;
        }
        if (!isPhuongThucThanhToanSelected()) {
            MsgBox.warning(this, "Vui lòng chọn phương thức thanh toán trước khi tạo đơn!");
            return;
        }
        String diaChi = (String) cboDiaChi1.getSelectedItem();
        HoaDonObject hdo = getForm();

        HoaDonObject maHoaDon = hddao.hoaDonMoi().get(0);
        txtMaNhanVien1.setText(Auth.user.getMaNV());
        hdo.setMaNV(Auth.user.getMaNV());
        hddao.insert(hdo);
        txtMaHoaDon.setText(hddao.hoaDonMoi().get(0) + "");
        this.fillTableHoaDon();

    }

    public void fillTableHoaDon() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        List<HoaDonObject> list = hddao.selectAll();

        try {
            for (HoaDonObject e : list) {
                String tenPhuongThuc = "";

                // Kiểm tra xem e.getMaThanhToan() có null không
                if (e.getMaThanhToan() != null) {
                    // Sử dụng equalsIgnoreCase() mà không gặp lỗi
                    if (e.getMaThanhToan().equalsIgnoreCase("TT001")) {
                        tenPhuongThuc += "Tiền mặt";
                    } else {
                        tenPhuongThuc += "Chuyển khoản";
                    }
                }
                model.addRow(new Object[]{
                    e.getMaHD(),
                    e.getMaNV(),
                    hddao.layTenKhachHang(e.getMaKH()),
                    tenPhuongThuc,
                    e.getPhiGiaoNhanh(),
                    e.getTongTien()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.error(this, "Lỗi truy vấn dữ liệu bảng hóa đơn!");
        }
    }

    private boolean isPhuongThucThanhToanSelected() {
        // Kiểm tra xem có phương thức thanh toán nào được chọn hay không
        return rdoTienMat.isSelected() || rdoChuyenKhoanr1.isSelected();
    }

    public void layGiaTri() {
        String phiGiaoHang = (String) cboDiaChi1.getSelectedItem();
        Integer phiGiaoNhanhInteger = phiGiaoNhanhMap.get(phiGiaoHang);
        int phiGiaoNhanh = (phiGiaoNhanhInteger != null) ? phiGiaoNhanhInteger : 0;
        if ("Chọn tỉnh giao hàng".equalsIgnoreCase(phiGiaoHang)) {
            txtPhiGiaoNhanh.setText("");
            txtTongTien.setText("");
            return;
        }

        double tongTien = 0;

        // Nếu chọn giao nhanh, cộng thêm 20 vào tổng tiền
        if (ckeGiaoNhanh.isSelected()) {
            tongTien += 20;
            GiaoDichObject.setGiaoHangNhanh(tongTien);
        }

        // Tính tổng tiền từ tất cả các HoaDonChiTietObject trong danh sách
        double tongTienHoaDonChiTiet = 0;
        tongTienHoaDonChiTiet = hdctdao.getLatestTotal();
        // Format giá trị phiGiaoNhanh thành chuỗi có định dạng
        String formattedPhiGiaoNhanh = NumberFormatter.formatNumber(phiGiaoNhanh);

        txtPhiGiaoNhanh.setText(formattedPhiGiaoNhanh);

        txtTongTien.setText(GiaoDichObject.getTongTien() + "");
        double tongTen2 = 0;
        for (int i = 0; i < tblHoaDonChiTiet.getRowCount(); i++) {
            tongTen2 += Double.parseDouble(NumberFormatter.replaceCommaWithDot(tblHoaDonChiTiet.getValueAt(i, 3).toString()));
        }

        // Hiển thị giá trị tổng tiền hiển thị
        txtTongTien.setText(NumberFormatter.formatCurrency(GiaoDichObject.getTongTien() + phiGiaoNhanh + tongTien));

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.quanlyquananvat.chucNangGiaoDien.Border_panel border_panel2;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnHuyDon;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnInDon;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnLui;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnLui2;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTaoDon;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnThanhToan;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTien;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTien2;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTrangCuoi;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTrangCuoi3;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTrangDau;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTrangDau2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboDiaChi1;
    private javax.swing.JComboBox<String> cboLoaiSanPham;
    private com.quanlyquananvat.chucNangGiaoDien.JCheckBoxCustom ckeGiaoNhanh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JPopupMenu jPopupMenu5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private com.quanlyquananvat.chucNangGiaoDien.RadioButtonCustom rdoChuyenKhoanr1;
    private com.quanlyquananvat.chucNangGiaoDien.RadioButtonCustom rdoTienMat;
    private javax.swing.JPanel tabGiaoDich;
    private com.quanlyquananvat.chucNangGiaoDien.TabbedPaneCustom tabbedPaneCustom1;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblSanPham;
    private textfield.TextField txtKhachHang;
    private textfield.TextField txtMaHoaDon;
    private textfield.TextField txtMaNhanVien1;
    private textfield.TextField txtPhiGiaoNhanh;
    private textfield.TextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
