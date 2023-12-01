package com.quanlyquananvat.GiaoDienQuanLy;

import com.quanlyquananvat.Object.KhachHangObject;
import com.quanlyquananvat.Object.NhanVienObject;
import com.quanlyquananvat.ThuVienTienIch.Auth;
import com.quanlyquananvat.ThuVienTienIch.MsgBox;
import com.quanlyquananvat.ThuVienTienIch.XDate;
import com.quanlyquananvat.ThuVienTienIch.Ximge;
import com.quanlyquananvat.dao.KhachHangDAO;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.border.DropShadowBorder;

public class KhachHang extends javax.swing.JPanel {

    KhachHangDAO khadao = new KhachHangDAO();
    JFileChooser fileChooser = new JFileChooser();
    private int currentPage = 1; // trang hiện tại
    private int recordsPerPage = 10;// số dòng dữ liệu trên table

    public KhachHang() {
        initComponents();
        init();
    }
    int row = 0;

    public void init() {
        DropShadowBorder shadowBorder = new DropShadowBorder();
        this.setBorder(shadowBorder);
        tblKhachHang.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        tblKhachHang.getTableHeader().setOpaque(false);
        tblKhachHang.getTableHeader().setBackground(new Color(0, 102, 255));
        tblKhachHang.getTableHeader().setForeground(new Color(0, 0, 0));
        tblKhachHang.setRowHeight(25);
        dcNgaySinh.setDateFormatString("dd-MM-yyyy");
        fillTable();
    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            List<KhachHangObject> list = khadao.selectAll();
            // lấy số lượng danh sách
            int soLuongDanhSach = list.size();
            //tính tổng số trang
            int tongSoTrang = (int) Math.ceil((double) soLuongDanhSach / recordsPerPage);
            int startIndex = (currentPage - 1) * recordsPerPage;
            lblSoTrang.setText("Trang thứ: " + currentPage + " đến " + tongSoTrang);
            int endIndex = Math.min(startIndex + recordsPerPage, soLuongDanhSach);
            for (int i = startIndex; i < endIndex; i++) {
                KhachHangObject s = list.get(i);
                model.addRow(new Object[]{
                    s.getMaKH(),
                    s.getTenKH(),
                    s.getSoDienThoai(),
                    format.format(s.getNgaySinh()),
                    s.getEmail(),
                    s.isGioiTinh() ? "Nam" : "Nữ",});
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.error(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void updateStatus() {
        boolean edit = this.row >= 0;
        boolean first = this.row == 0;
        boolean last = this.row == tblKhachHang.getRowCount() - 1;
        txtMaKhachHang.setEnabled(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);

    }

    public void chonAnh() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Kiểm tra định dạng của file ảnh
            if (!isImageFile(file)) {
                MsgBox.alert(this, "Hãy chọn file ảnh hợp lệ");
                return;
            }

            // Tiếp tục xử lý ảnh nếu định dạng là hợp lệ
            Ximge.save(file);
            ImageIcon icon = Ximge.read(file.getName(), lblAnhDaiDien.getWidth(), lblAnhDaiDien.getHeight());
            if (icon != null) {
                lblAnhDaiDien.setIcon(icon);
                lblAnhDaiDien.setText("");
                lblAnhDaiDien.setToolTipText(file.getName());
            } else {
                MsgBox.alert(this, "Hãy chọn logo hợp lệ");
            }
        }
    }

    private boolean isImageFile(File file) {
        // Kiểm tra định dạng file ảnh theo extension
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        // Các định dạng ảnh phổ biến (có thể mở rộng theo nhu cầu của bạn)
        String[] allowedExtensions = {"jpg", "jpeg", "png", "gif", "bmp"};

        // Kiểm tra xem extension của file có trong danh sách cho phép hay không
        for (String allowedExtension : allowedExtensions) {
            if (extension.equals(allowedExtension)) {
                return true;
            }
        }

        return false;
    }

    public KhachHangObject getForm() {
        KhachHangObject kh = new KhachHangObject();
        kh.setMaKH(txtMaKhachHang.getText());
        kh.setTenKH(txtTenKhachHang.getText());
        kh.setDiaChi(txtDiaChi.getText());
        kh.setEmail(txtEmail.getText());
        kh.setGioiTinh(rdoNam.isSelected());
        kh.setSoDienThoai(txtSoDienThoai.getText());
        kh.setNgaySinh(dcNgaySinh.getDate());
        kh.setHinhAnh(lblAnhDaiDien.getToolTipText());
        return kh;
    }

    public void setForm(KhachHangObject kh) {
        txtMaKhachHang.setText(kh.getMaKH());
        txtTenKhachHang.setText(kh.getTenKH());
        txtDiaChi.setText(kh.getDiaChi());
        txtEmail.setText(kh.getEmail());
        txtSoDienThoai.setText(kh.getSoDienThoai());
        dcNgaySinh.setDate(kh.getNgaySinh());
        rdoNam.setSelected(kh.isGioiTinh());
        rdoNu.setSelected(!kh.isGioiTinh());

        int width = lblAnhDaiDien.getWidth();
        int height = lblAnhDaiDien.getHeight();
        if (kh.getHinhAnh() != null && !kh.getHinhAnh().equals("")) {
            lblAnhDaiDien.setText("");
            lblAnhDaiDien.setIcon(Ximge.read(kh.getHinhAnh(), width, height));
            lblAnhDaiDien.setToolTipText(kh.getHinhAnh());
        }
    }

    public void edit() {
        int rowCount = tblKhachHang.getRowCount();
        if (this.row >= 0 && this.row < rowCount) {
            String maNV = (String) tblKhachHang.getValueAt(this.row, 0);
            KhachHangObject nv = khadao.selectById(maNV);
            if (nv != null) {
                TabNhanVien.setSelectedIndex(0);
                setForm(nv);
                updateStatus();
            }
        }

    }

    public void clearnForm() {
        this.setForm(new KhachHangObject());
        buttonGroup1.clearSelection();
        lblAnhDaiDien.setText("Ảnh đại diện");
        this.updateStatus();
        lblAnhDaiDien.setIcon(null);
        row = -1;
    }

    public void insert() {
        // Kiểm tra các thông tin khác
        String error = hashErrol();
        if (error != null) {
            MsgBox.warning(this, error);
            return;
        }
        KhachHangObject kh = getForm();
        try {
            khadao.insert(kh);
            this.fillTable();
            this.clearnForm();
            MsgBox.alert(this, "Thêm thông tin thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.error(this, "Thêm thông tin thất bại!");
        }
    }

    public void update() {
        int selectedRow = tblKhachHang.getSelectedRow();
        if (selectedRow < 0) {
            MsgBox.warning(this, "Vui lòng chọn 1 dòng dữ liệu để cập nhật!");
            return;
        }

        KhachHangObject kh = getForm();

        // Kiểm tra dữ liệu hợp lệ trước khi cập nhật
        String validationError = validateData(kh);
        if (validationError != null) {
            MsgBox.warning(this, validationError);
            return;
        }

        try {
            // Thực hiện cập nhật trong cơ sở dữ liệu
            khadao.update(kh);

            // Cập nhật bảng và làm sạch form
            this.fillTable();
            this.clearnForm();

            MsgBox.alert(this, "Cập nhật thông tin thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.error(this, "Cập nhật thông tin thất bại!");
        }
    }

    private String validateData(KhachHangObject kh) {
        // Kiểm tra và trả về thông báo lỗi nếu có lỗi, ngược lại trả về null
        if (isMaKhachHangInvalid(kh.getMaKH())) {
            return "Lỗi định dạng mã khách hàng không hợp lệ!";
        }

        if (kh.getTenKH().isEmpty() || kh.getDiaChi().isEmpty() || kh.getEmail().isEmpty()
                || kh.getNgaySinh() == null || kh.getSoDienThoai().isEmpty()) {
            return "Vui lòng nhập đầy đủ thông tin!";
        }
        if (!isValidEmail(kh.getEmail())) {
            return "Email không hợp lệ!";
        }
        if (!isValidPhoneNumberVN(kh.getSoDienThoai())) {
            return "Số điện thoại không hợp lệ!";
        }
        // Kiểm tra giới tính
        if (buttonGroup1.getSelection() == null) {
            // Nếu giới tính là false
            return "Vui lòng chọn giới tính!";
        }
        if (isSoDienThoai(kh.getSoDienThoai())) {
            return "Số điện thoại đã tồn tại";
        }
        // Nếu không có lỗi, trả về null
        return null;
    }

    public void timKiem() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            String timKiem = txtTimKiem.getText();
            List<KhachHangObject> list = khadao.search_name(timKiem);
            for (KhachHangObject s : list) {
                model.addRow(new Object[]{
                    s.getMaKH(),
                    s.getTenKH(),
                    s.getSoDienThoai(),
                    format.format(s.getNgaySinh()),
                    s.getEmail(),
                    s.isGioiTinh() ? "Nam" : "Nữ",});
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.error(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public void delete() {
        int row = tblKhachHang.getSelectedRow();
        if (row < 0) {
            MsgBox.warning(this, "Vui lòng chọn 1 dòng dữ liệu để có thể sử dụng chức năng này!");
            return;
        }
        String makh = txtMaKhachHang.getText();
        if (makh == null || makh.trim().isEmpty()) {
            MsgBox.warning(this, "Vui lòng chọn một khách hàng để xóa.");
            return;
        }
        if (!Auth.isManager()) {
            MsgBox.error(this, "Bạn không có quyền để sử dụng chức năng này!");
        } else {

            try {
                int xoa = JOptionPane.showConfirmDialog(this, "Bạn có chắc chán muốn xóa khách hàng " + makh + " không", "Xóa khách hàng", JOptionPane.YES_NO_OPTION);
                if (xoa == JOptionPane.YES_OPTION) {
                    khadao.delete(makh);
                    this.fillTable();
                    this.clearnForm();
                    MsgBox.alert(this, "Xóa thông tin thành công!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                MsgBox.error(this, "Xóa thông tin thất bại!!");
            }
        }

    }

    private static boolean isMaKhachHangInvalid(String maKhachHang) {
        // Kiểm tra nếu maKhachHang không đúng định dạng "KH" theo sau là 3 chữ số
        return maKhachHang != null && !maKhachHang.matches("KH\\d{3}");
    }

    private boolean isSoDienThoai(String soDienThoai) {
        List<KhachHangObject> kh = khadao.selectAll();
        for (KhachHangObject khachHangObject : kh) {
            if (khachHangObject.getSoDienThoai().equals(soDienThoai)) {
                return true;
            }
        }
        return false;
    }

    public boolean isMaKhachHangDuplicated(String maKhachHang) {
        List<KhachHangObject> danhSachKhachHang = khadao.selectAll();
        for (KhachHangObject kh : danhSachKhachHang) {
            if (kh.getMaKH().equals(maKhachHang)) {
                return true; // Mã bị trùng
            }
        }
        return false; // Mã không trùng
    }

    private static boolean isValidEmail(String email) {
        // Biểu thức chính quy để kiểm tra email không hợp lệ
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isValidPhoneNumberVN(String phoneNumber) {
        // Biểu thức chính quy cho số điện thoại Việt Nam
        String phoneNumberRegex = "^(032|033|034|035|036|037|038|039|096|097|098|086|083|084|085|081|082|088|091|094|070|079|077|076|078|090|093|089|056|058|092|059|099)[0-9]{7}$";
        return phoneNumber.matches(phoneNumberRegex);
    }

    public String hashErrol() {
        String maKhachHang = txtMaKhachHang.getText();
        String tenKhachHang = txtTenKhachHang.getText();
        String diaChi = txtDiaChi.getText();
        String email = txtEmail.getText();
        Date ngaySinh = dcNgaySinh.getDate();
        String soDienThoai = txtSoDienThoai.getText();

        // Kiểm tra ảnh
        if (lblAnhDaiDien.getIcon() == null) {
            return "Vui lòng chọn ảnh đại diện!";
        }

        // Kiểm tra mã khách hàng
        if (isMaKhachHangInvalid(maKhachHang)) {
            return "Lỗi định dạng mã khách hàng không hợp lệ!";
        }
        if (isMaKhachHangDuplicated(maKhachHang)) {
            return "Mã khách hàng đã tồn tại. Vui lòng chọn mã khác!";
        }

        // Kiểm tra các thông tin khác
        if (maKhachHang.isEmpty() || tenKhachHang.isEmpty() || diaChi.isEmpty()
                || email.isEmpty() || soDienThoai.isEmpty()) {
            return "Vui lòng nhập đầy đủ thông tin!";
        }
        if (ngaySinh == null) {
            return "Vui lòng nhập đầy đủ thông tin!";
        }
        if (!isValidEmail(email)) {
            return "Email không hợp lệ!";
        }
        if (!isValidPhoneNumberVN(soDienThoai)) {
            return "Số điện thoại không hợp lệ!";
        }
        if (isSoDienThoai(soDienThoai)) {
            return "Số điện thoại đã được đăng ký";
        }
        // Kiểm tra giới tính
        if (buttonGroup1.getSelection() == null) {
            return "Vui lòng chọn giới tính!";
        }

        return null; // Trả về null nếu không có lỗi
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TabNhanVien = new com.quanlyquananvat.chucNangGiaoDien.TabbedPaneCustom();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnXoa = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnSua = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnMoi = new com.quanlyquananvat.chucNangGiaoDien.Button();
        txtTenKhachHang = new textfield.TextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rdoNam = new com.quanlyquananvat.chucNangGiaoDien.RadioButtonCustom();
        rdoNu = new com.quanlyquananvat.chucNangGiaoDien.RadioButtonCustom();
        border_panel1 = new com.quanlyquananvat.chucNangGiaoDien.Border_panel();
        lblAnhDaiDien = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaKhachHang = new textfield.TextField();
        jLabel4 = new javax.swing.JLabel();
        dcNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtSoDienThoai = new textfield.TextField();
        txtDiaChi = new textfield.TextField();
        txtEmail = new textfield.TextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTimKiem = new textfield.TextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        btnTrangDau = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnLui = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnTien = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnTrangCuoi = new com.quanlyquananvat.chucNangGiaoDien.Button();
        lblSoTrang = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 71, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Khách hàng");

        TabNhanVien.setBackground(new java.awt.Color(242, 242, 242));
        TabNhanVien.setForeground(new java.awt.Color(0, 0, 0));
        TabNhanVien.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TabNhanVien.setSelectedColor(new java.awt.Color(204, 204, 204));
        TabNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabNhanVienMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(242, 242, 242));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        btnThem.setBackground(new java.awt.Color(0, 170, 0));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 167.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnThem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThem.setShadowColor(new java.awt.Color(0, 170, 0));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

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

        txtTenKhachHang.setForeground(new java.awt.Color(0, 0, 0));
        txtTenKhachHang.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Địa chỉ");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Ngày sinh");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Email");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Giới tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setForeground(new java.awt.Color(0, 0, 0));
        rdoNam.setText("Nam");
        rdoNam.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N

        buttonGroup1.add(rdoNu);
        rdoNu.setForeground(new java.awt.Color(0, 0, 0));
        rdoNu.setText("Nữ");
        rdoNu.setFocusable(false);
        rdoNu.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N

        border_panel1.setBackground(new java.awt.Color(217, 217, 217));
        border_panel1.setRoundBottomLeft(25);
        border_panel1.setRoundBottomRight(25);
        border_panel1.setRoundTopLeft(25);
        border_panel1.setRoundTopRight(25);

        lblAnhDaiDien.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblAnhDaiDien.setForeground(new java.awt.Color(51, 51, 51));
        lblAnhDaiDien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnhDaiDien.setText("Ảnh đại diện");
        lblAnhDaiDien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhDaiDienMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAnhDaiDienMousePressed(evt);
            }
        });

        javax.swing.GroupLayout border_panel1Layout = new javax.swing.GroupLayout(border_panel1);
        border_panel1.setLayout(border_panel1Layout);
        border_panel1Layout.setHorizontalGroup(
            border_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(border_panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnhDaiDien, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addContainerGap())
        );
        border_panel1Layout.setVerticalGroup(
            border_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(border_panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnhDaiDien, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Mã khách hàng");

        txtMaKhachHang.setForeground(new java.awt.Color(0, 0, 0));
        txtMaKhachHang.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tên khách hàng");

        dcNgaySinh.setBackground(new java.awt.Color(255, 255, 255));
        dcNgaySinh.setForeground(new java.awt.Color(0, 0, 0));
        dcNgaySinh.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        dcNgaySinh.setMaxSelectableDate(new java.util.Date(253370743303000L));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Số điện thoại");

        txtSoDienThoai.setForeground(new java.awt.Color(0, 0, 0));
        txtSoDienThoai.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtDiaChi.setForeground(new java.awt.Color(0, 0, 0));
        txtDiaChi.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtEmail.setForeground(new java.awt.Color(0, 0, 0));
        txtEmail.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(194, 194, 194))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(border_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(dcNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(border_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(dcNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );

        TabNhanVien.addTab("Cập nhật", jPanel2);

        jPanel3.setBackground(new java.awt.Color(242, 242, 242));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tìm kiếm");

        txtTimKiem.setBackground(new java.awt.Color(220, 220, 220));
        txtTimKiem.setForeground(new java.awt.Color(0, 0, 0));
        txtTimKiem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        tblKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        tblKhachHang.setForeground(new java.awt.Color(0, 0, 0));
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Số điện thoại ", "Ngày sinh", "Email", "Giới tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblKhachHang.setRowHeight(30);
        tblKhachHang.getTableHeader().setReorderingAllowed(false);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKhachHangMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(5).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(5).setHeaderValue("Giới tính");
        }

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
        lblSoTrang.setForeground(new java.awt.Color(0, 0, 0));
        lblSoTrang.setText("Trang:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addComponent(btnTrangDau, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(btnTien, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTrangCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblSoTrang)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSoTrang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLui, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrangDau, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrangCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTien, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        TabNhanVien.addTab("Danh sách", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TabNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TabNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clearnForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        timKiem();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tblKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMousePressed
        if (evt.getClickCount() == 2) {
            this.row = tblKhachHang.rowAtPoint(evt.getPoint());
            edit();
        }
    }//GEN-LAST:event_tblKhachHangMousePressed

    private void btnTrangDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangDauActionPerformed
        if (currentPage > 1) {
            currentPage = 1;
            fillTable();
        }
    }//GEN-LAST:event_btnTrangDauActionPerformed

    private void btnLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiActionPerformed
        if (currentPage > 1) {
            currentPage--;
            fillTable();
        }
    }//GEN-LAST:event_btnLuiActionPerformed

    private void btnTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienActionPerformed
        int totalPages = (int) Math.ceil((double) khadao.selectAll().size() / recordsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            fillTable();
        }
    }//GEN-LAST:event_btnTienActionPerformed

    private void btnTrangCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangCuoiActionPerformed
        int totalPages = (int) Math.ceil((double) khadao.selectAll().size() / recordsPerPage);
        if (currentPage < totalPages) {
            currentPage = totalPages;
            fillTable();
        }
    }//GEN-LAST:event_btnTrangCuoiActionPerformed

    private void TabNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabNhanVienMouseClicked

    }//GEN-LAST:event_TabNhanVienMouseClicked

    private void lblAnhDaiDienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhDaiDienMouseClicked
    }//GEN-LAST:event_lblAnhDaiDienMouseClicked

    private void lblAnhDaiDienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhDaiDienMousePressed
        if (evt.getClickCount() == 2) {
            chonAnh();
        }
    }//GEN-LAST:event_lblAnhDaiDienMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.quanlyquananvat.chucNangGiaoDien.TabbedPaneCustom TabNhanVien;
    private com.quanlyquananvat.chucNangGiaoDien.Border_panel border_panel1;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnLui;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnMoi;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnSua;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnThem;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTien;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTrangCuoi;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTrangDau;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dcNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnhDaiDien;
    private javax.swing.JLabel lblSoTrang;
    private com.quanlyquananvat.chucNangGiaoDien.RadioButtonCustom rdoNam;
    private com.quanlyquananvat.chucNangGiaoDien.RadioButtonCustom rdoNu;
    private javax.swing.JTable tblKhachHang;
    private textfield.TextField txtDiaChi;
    private textfield.TextField txtEmail;
    private textfield.TextField txtMaKhachHang;
    private textfield.TextField txtSoDienThoai;
    private textfield.TextField txtTenKhachHang;
    private textfield.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
