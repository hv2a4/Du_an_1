package com.quanlyquananvat.GiaoDienQuanLy;

import com.quanlyquananvat.Object.KhachHangObject;
import com.quanlyquananvat.Object.NhanVienObject;

import com.quanlyquananvat.ThuVienTienIch.MsgBox;
import com.quanlyquananvat.dao.KhachHangDAO;
import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.border.DropShadowBorder;

public class KhachHang extends javax.swing.JPanel {

    KhachHangDAO khdao = new KhachHangDAO();
    JFileChooser fileChooser = new JFileChooser();
    int row = 0;
    private int currentPage = 1;
    private int recordsPerPage = 10;
    private String sreachByName;

    /**
     * Creates new form KhachHang
     */
    public KhachHang() {
        initComponents();
        init();

    }

    public void init() {
        DropShadowBorder shadowBorder = new DropShadowBorder();
        this.setBorder(shadowBorder);
        tblkhachhang.getTableHeader().setFont(new Font(TOOL_TIP_TEXT_KEY, Font.BOLD, 15));
        tblkhachhang.getTableHeader().setOpaque(false);
        tblkhachhang.getTableHeader().setBackground(new Color(0, 102, 255));
        tblkhachhang.getTableHeader().setForeground(new Color(0, 0, 0));
        tblkhachhang.setRowHeight(25);
        fillTable();
    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblkhachhang.getModel();
        model.setRowCount(0);
        try {
            List<KhachHangObject> list = khdao.selectAll();
            // lấy số lượng danh sách nhân viên
            int totalRecords = list.size();
            // tính tổng số trang 
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
            // Xác định dòng bắt đầu và dòng kết thúc trên trang hiện tại
            int startIndex = (currentPage - 1) * recordsPerPage;
            lblSoTrang.setText("Trang thứ: " + currentPage + " đến " + totalPages);
            int endIndex = Math.min(startIndex + recordsPerPage, totalRecords);

            for (int i = startIndex; i < endIndex; i++) {
                KhachHangObject nvo = list.get(i);
                model.addRow(new Object[]{
                    nvo.getMaKH(),
                    nvo.getTenKH(),
                    nvo.getDiaChi(),
                    nvo.getSoDienThoai(),
                    nvo.getEmail(),
                    nvo.isGioiTinh() ? "Quản lý" : "Khách Hàng"
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.error(this, "Lỗi khi truy vấn dữ liệu!");
        }
    }

public KhachHangObject getForm() {
    KhachHangObject model = new KhachHangObject();
    model.setMaKH(txtKH.getText());
    model.setDiaChi(txtDiaChi.getText());
    model.setTenKH(txttenKH.getText()); // Thiết lập giá trị cho TenKH
     model.setSoDienThoai(txtsdt.getText()); // Thiết lập giá trị cho TenKH
      model.setEmail(txtEmail.getText()); // Thiết lập giá trị cho TenKH
    
    // Xử lý ngoại lệ khi chuyển đổi chuỗi thành ngày
    try {
        Date ngaySinh = jDateChooser1.getDate();
        model.setNgaySinh(ngaySinh);
    } catch (NullPointerException e) {
        // Xử lý nếu ngày sinh chưa được chọn (là null)
        e.printStackTrace(); // Hoặc thực hiện xử lý khác tùy vào yêu cầu của bạn
    }
    // Đặt Giới tính dựa trên nút radio được chọn
    if (rdonam.isSelected()) {
        model.setGioiTinh(true);
    } else if (rdonu.isSelected()) {
        model.setGioiTinh(false);
    } else {
        // Xử lý trường hợp không có nút radio nào được chọn
        // Bạn có thể đặt một giá trị mặc định hoặc hiển thị một thông báo lỗi
    }

    // Các dòng mã khác không thay đổi
    return model;
}


    public void them() {
        KhachHangObject kh = getForm();
        try {
            khdao.insert(kh);
            this.fillTable();
            MsgBox.alert(this, " Thêm mới thành công");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.error(this, "Thêm mới thất bại");
        }
    }
 public void timKiem(String searchType) {
    DefaultTableModel model = (DefaultTableModel) tblkhachhang.getModel();
    model.setRowCount(0);
    try {
        String keyword = txtTimKiem.getText();
        List<KhachHangObject> list = khdao.searchNhanVien(searchType, keyword);
        for (KhachHangObject nvo : list) {
            model.addRow(new Object[]{
                nvo.getMaKH(),
                nvo.getTenKH(),
                nvo.getSoDienThoai(),
                nvo.isGioiTinh() ? "Quản lý" : "Nhân viên"
            });
        }
    } catch (Exception e) {
        MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        e.printStackTrace();
    }
}


    public void sua() {
        KhachHangObject kh = getForm();
        try {
            this.khdao.update(kh);
            this.fillTable();
            TabNhanVien.setSelectedIndex(1);
            MsgBox.alert(this, "Sửa Thành Công!");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.warning(this, "Sửa thất bại ");
        }
    }

    public void edit() {
        int rowCount = tblkhachhang.getRowCount();
        if (this.row >= 0 && this.row < rowCount) {
            String maNV = (String) tblkhachhang.getValueAt(this.row, 0);
            KhachHangObject nv = khdao.selectById(maNV);
            if (nv != null) {
                TabNhanVien.setSelectedIndex(0);
                setForm(nv);
                updateStatus();
            }
        }
    }

    public void setForm(KhachHangObject kh) {
        txtKH.setText(kh.getMaKH());
        txtDiaChi.setText(kh.getDiaChi());
        jDateChooser1.setDate(kh.getNgaySinh());
        txttenKH.setText(kh.getTenKH());
        txtEmail.setText(kh.getEmail());
        txtsdt.setText(kh.getSoDienThoai());
        rdonam.setSelected(kh.isGioiTinh());
        rdonu.setSelected(!kh.isGioiTinh());
    }

    public void updateStatus() {
        boolean edit = this.row >= 0;
        boolean first = this.row == 0;
        boolean last = this.row == tblkhachhang.getRowCount() - 1;
        txtKH.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);

    }

    public void delete() {
        int rowCount = tblkhachhang.getRowCount();
        if (rowCount > 0) {
            String maNV = txtKH.getText().trim();
            if (!maNV.isEmpty()) {
                if (MsgBox.confim(this, "Bạn thực sự muốn xóa nhân viên này!")) {
                    try {
                        khdao.delete(maNV);
                        this.fillTable();

                        MsgBox.alert(this, "Xóa thành công!");
                    } catch (Exception e) {
                        e.printStackTrace();
                        MsgBox.error(this, "Xóa thất bại! " + e.getMessage());
                    }
                }
            } else {
                MsgBox.warning(this, "Vui lòng chọn một nhân viên để xóa!");
            }
        } else {
            MsgBox.warning(this, "Không có dữ liệu để xóa!");
        }
    }

    public void clearnForm() {
        this.setForm(new KhachHangObject());
        buttonGroup1.clearSelection();

        this.updateStatus();

        row = -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        TabNhanVien = new com.quanlyquananvat.chucNangGiaoDien.TabbedPaneCustom();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtDiaChi = new textfield.TextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        rdonam = new com.quanlyquananvat.chucNangGiaoDien.RadioButtonCustom();
        rdonu = new com.quanlyquananvat.chucNangGiaoDien.RadioButtonCustom();
        txtsdt = new textfield.TextField();
        txtEmail = new textfield.TextField();
        txttenKH = new textfield.TextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        txtKH = new textfield.TextField();
        btnXoa = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnThem = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnSua = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnMoi = new com.quanlyquananvat.chucNangGiaoDien.Button();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTimKiem = new textfield.TextField();
        btnTrangDau = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnLui = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnTien = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnTrangCuoi = new com.quanlyquananvat.chucNangGiaoDien.Button();
        lblSoTrang = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblkhachhang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        TabNhanVien.setBackground(new java.awt.Color(242, 242, 242));
        TabNhanVien.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TabNhanVien.setSelectedColor(new java.awt.Color(204, 204, 204));
        TabNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabNhanVienMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel13.setText("Ngày sinh");

        txtDiaChi.setForeground(new java.awt.Color(0, 0, 0));
        txtDiaChi.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel11.setText("Mã khách hàng");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel12.setText("Địa chỉ");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel17.setText("Giới tính");

        buttonGroup1.add(rdonam);
        rdonam.setText("Nam");
        rdonam.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        rdonam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdonamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdonu);
        rdonu.setText("Nữ");
        rdonu.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N

        txtsdt.setForeground(new java.awt.Color(0, 0, 0));
        txtsdt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtEmail.setForeground(new java.awt.Color(0, 0, 0));
        txtEmail.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txttenKH.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel14.setText("Tên khách hàng");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel15.setText("Email");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel16.setText("Số điện thoại");

        txtKH.setForeground(new java.awt.Color(0, 0, 0));
        txtKH.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel15))
                        .addGap(68, 68, 68)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(rdonam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdonu, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 491, Short.MAX_VALUE))
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txttenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(82, 82, 82))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txttenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel16))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdonam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdonu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(34, Short.MAX_VALUE))))
        );

        btnXoa.setBackground(new java.awt.Color(0, 170, 0));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 168.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnXoa.setShadowColor(new java.awt.Color(0, 170, 0));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(0, 170, 0));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 167.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnThem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThem.setShadowColor(new java.awt.Color(0, 170, 0));
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 170, 0));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 166.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnSua.setShadowColor(new java.awt.Color(0, 170, 0));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(0, 170, 0));
        btnMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 169.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnMoi.setShadowColor(new java.awt.Color(0, 170, 0));
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 77, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(307, Short.MAX_VALUE))
        );

        TabNhanVien.addTab("Chi Tiết", jPanel1);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel8.setText("Tìm kiếm");

        txtTimKiem.setBackground(new java.awt.Color(220, 220, 220));
        txtTimKiem.setForeground(new java.awt.Color(0, 0, 0));
        txtTimKiem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

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

        lblSoTrang.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblSoTrang.setText("Trang:");

        tblkhachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại", "Giới tính", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblkhachhang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblkhachhang.setRowHeight(30);
        tblkhachhang.setShowHorizontalLines(true);
        tblkhachhang.getTableHeader().setReorderingAllowed(false);
        tblkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkhachhangMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblkhachhangMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblkhachhang);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSoTrang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 902, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTrangDau, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTien, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTrangCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(240, 240, 240))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSoTrang)
                        .addGap(453, 453, 453))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTrangCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTien, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTrangDau, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(132, 132, 132)))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );

        TabNhanVien.addTab("Danh sách", jPanel2);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 71, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Khách hàng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(TabNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(90, 90, 90))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TabNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        them();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
        clearnForm();
        
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        sua();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clearnForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
           timKiem(sreachByName);
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tblkhachhangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkhachhangMousePressed
        if (evt.getClickCount() == 2) {
            this.row = tblkhachhang.rowAtPoint(evt.getPoint());
            edit();
        }
    }//GEN-LAST:event_tblkhachhangMousePressed

    private void btnTrangDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangDauActionPerformed
        if (currentPage > 1) {
            currentPage = 1;
            fillTable();
        }
    }//GEN-LAST:event_btnTrangDauActionPerformed

    private void btnLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiActionPerformed
        if (currentPage > 1) {
            currentPage = 1;
            fillTable();
        }
    }//GEN-LAST:event_btnLuiActionPerformed

    private void btnTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienActionPerformed
        int totalPages = (int) Math.ceil((double) khdao.selectAll().size() / recordsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            fillTable();
        }
    }//GEN-LAST:event_btnTienActionPerformed

    private void btnTrangCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangCuoiActionPerformed
        int totalPages = (int) Math.ceil((double) khdao.selectAll().size() / recordsPerPage);
        if (currentPage < totalPages) {
            currentPage = totalPages;
            fillTable();
        }
    }//GEN-LAST:event_btnTrangCuoiActionPerformed

    private void TabNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabNhanVienMouseClicked

    }//GEN-LAST:event_TabNhanVienMouseClicked

    private void rdonamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdonamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdonamActionPerformed

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemMouseClicked

    private void tblkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkhachhangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblkhachhangMouseClicked

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.quanlyquananvat.chucNangGiaoDien.TabbedPaneCustom TabNhanVien;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnLui;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnMoi;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnSua;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnThem;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTien;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTrangCuoi;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTrangDau;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblSoTrang;
    private com.quanlyquananvat.chucNangGiaoDien.RadioButtonCustom rdonam;
    private com.quanlyquananvat.chucNangGiaoDien.RadioButtonCustom rdonu;
    private javax.swing.JTable tblkhachhang;
    private textfield.TextField txtDiaChi;
    private textfield.TextField txtEmail;
    private textfield.TextField txtKH;
    private textfield.TextField txtTimKiem;
    private textfield.TextField txtsdt;
    private textfield.TextField txttenKH;
    // End of variables declaration//GEN-END:variables

}
