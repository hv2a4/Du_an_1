package com.quanlyquananvat.GiaoDienQuanLy;

import com.quanlyquananvat.Object.KhachHangObject;
import com.quanlyquananvat.Object.LoaiSanPhamObject;
import com.quanlyquananvat.Object.SanPhamObject;
import com.quanlyquananvat.ThuVienTienIch.Auth;
import com.quanlyquananvat.ThuVienTienIch.MsgBox;
import com.quanlyquananvat.ThuVienTienIch.NumberFormatter;
import com.quanlyquananvat.ThuVienTienIch.XDate;
import com.quanlyquananvat.ThuVienTienIch.Ximge;
import com.quanlyquananvat.dao.LoaiSanPhamDAO;
import com.quanlyquananvat.dao.SanPhamDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.border.DropShadowBorder;

public class SanPham extends javax.swing.JPanel {

    SanPhamDAO spdao = new SanPhamDAO();
    LoaiSanPhamDAO lspdao = new LoaiSanPhamDAO();

    JFileChooser fileChooser = new JFileChooser();
    private int currentPage = 1; // trang hiện tại
    private int recordsPerPage = 10;// số dòng dữ liệu trên table
    int row = 0;

    public SanPham() {
        initComponents();
        init();

    }

    public void init() {
        DropShadowBorder shadowBorder = new DropShadowBorder();
        this.setBorder(shadowBorder);
        tblSanPham.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        tblSanPham.getTableHeader().setOpaque(false);
        tblSanPham.getTableHeader().setBackground(new Color(0, 102, 255));
        tblSanPham.getTableHeader().setForeground(new Color(0, 0, 0));
        tblSanPham.setRowHeight(25);
        dcNgayNhap.setDateFormatString("dd-MM-yyyy");
        fillComBoBox();
        fillTable();
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

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        try {
            List<SanPhamObject> list = spdao.selectAll();
            // lấy số lượng danh sách
            int soLuongDanhSach = list.size();
            //tính tổng số trang
            int tongSoTrang = (int) Math.ceil((double) soLuongDanhSach / recordsPerPage);
            int startIndex = (currentPage - 1) * recordsPerPage;
            lblSoTrang.setText("Trang thứ: " + currentPage + " đến " + tongSoTrang);
            int endIndex = Math.min(startIndex + recordsPerPage, soLuongDanhSach);
            for (int i = startIndex; i < endIndex; i++) {
                SanPhamObject s = list.get(i);
                model.addRow(new Object[]{
                    s.getMaSP(),
                    s.getTenSP(),
                    s.getSoLuong(),
                    NumberFormatter.formatNumber(s.getGiaSP()),
                    s.getNgayNhap()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi không lấy được dữ liệu!");
        }
    }

    public void fillComBoBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiSanPham.getModel();
        model.removeAllElements();
        model.addElement("Chọn loại sản phẩm");
        try {
            List<LoaiSanPhamObject> list = lspdao.selectAll();
            for (LoaiSanPhamObject s : list) {
                model.addElement(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.error(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void updateStatus() {
        boolean edit = this.row >= 0;
        boolean first = this.row == 0;
        boolean last = this.row == tblSanPham.getRowCount() - 1;
        txtMaSanPhaam.setEnabled(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);

    }

    public SanPhamObject getForm() {
        LoaiSanPhamObject lsp = (LoaiSanPhamObject) cboLoaiSanPham.getSelectedItem();
        SanPhamObject sp = new SanPhamObject();

        sp.setMaSP(txtMaSanPhaam.getText());
        sp.setTenSP(txtTenSanPham.getText());

        try {
            double soLuong = Double.parseDouble(txtSoLuong.getText());
            if (soLuong < 0) {
                MsgBox.error(this, "Số lượng không lớn hoặc nhỏ hơn bằng 0!");
                return null;
            }
            sp.setSoLuong((int) soLuong); // Chuyển đổi thành số nguyên
        } catch (NumberFormatException e) {
            e.printStackTrace();
            MsgBox.error(this, "Vui lòng nhập số lượng là một số.");
            return null;
        }

        try {
            double gia = Double.parseDouble(txtGia.getText());
            if (gia < 0) {
                MsgBox.error(this, "Giá không lớn hoặc nhỏ hơn bằng 0!");
                return null;
            }
            sp.setGiaSP(gia);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            MsgBox.error(this, "Vui lòng nhập giá là một số thực.");
            return null;
        }

        if (dcNgayNhap.getDate() == null || dcNgayNhap.getDate().after(XDate.now())) {
            MsgBox.error(this, "Ngày nhập không hợp lệ!");
            return null;
        }

        sp.setNgayNhap(dcNgayNhap.getDate());
        sp.setMoTa(txtMoTa.getText());
        sp.setMaLoaiSanPham(lsp.getMaLoaiSanPham());
        sp.setHinhAnh(lblAnhDaiDien.getToolTipText());

        return sp;
    }

    public void setForm(SanPhamObject spo) {
        txtMaSanPhaam.setText(spo.getMaSP());
        txtTenSanPham.setText(spo.getTenSP());
        txtSoLuong.setText(spo.getSoLuong() + "");
        txtGia.setText(spo.getGiaSP() + "");
        dcNgayNhap.setDate(spo.getNgayNhap());
        txtMoTa.setText(spo.getMoTa());

        LoaiSanPhamObject selectedLoaiSanPham = findLoaiSanPhamById(spo.getMaLoaiSanPham());

        cboLoaiSanPham.setSelectedItem(selectedLoaiSanPham);

        int width = lblAnhDaiDien.getWidth();
        int height = lblAnhDaiDien.getHeight();
        if (spo.getHinhAnh() != null && !spo.getHinhAnh().equals("")) {
            lblAnhDaiDien.setText("");
            lblAnhDaiDien.setIcon(Ximge.read(spo.getHinhAnh(), width, height));
            lblAnhDaiDien.setToolTipText(spo.getHinhAnh());
        }
    }

    // Hàm tìm kiếm đối tượng LoaiSanPhamObject trong ComboBoxModel dựa trên mã loại sản phẩm
    private LoaiSanPhamObject findLoaiSanPhamById(String maLoaiSanPham) {
        // Lấy ComboBoxModel từ ComboBox
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoaiSanPham.getModel();

        // Lấy số lượng phần tử trong ComboBoxModel
        int size = model.getSize();

        // Duyệt qua tất cả các phần tử trong ComboBoxModel
        for (int i = 0; i < size; i++) {
            // Lấy phần tử tại vị trí i
            Object item = model.getElementAt(i);

            // Kiểm tra nếu phần tử là đối tượng LoaiSanPhamObject
            if (item instanceof LoaiSanPhamObject) {
                // Ép kiểu phần tử về LoaiSanPhamObject
                LoaiSanPhamObject loaiSanPham = (LoaiSanPhamObject) item;

                // Kiểm tra xem mã loại sản phẩm của đối tượng có trùng với mã cần tìm hay không
                if (loaiSanPham.getMaLoaiSanPham().equals(maLoaiSanPham)) {
                    // Nếu trùng, trả về đối tượng LoaiSanPhamObject
                    return loaiSanPham;
                }
            }
        }

        // Trả về null nếu không tìm thấy đối tượng LoaiSanPhamObject với mã loại sản phẩm cần tìm
        return null;
    }

    public void edit() {
        int rowCount = tblSanPham.getRowCount();
        if (this.row >= 0 && this.row < rowCount) {
            String maNV = (String) tblSanPham.getValueAt(this.row, 0);
            SanPhamObject nv = spdao.selectById(maNV);
            if (nv != null) {
                TabNhanVien.setSelectedIndex(0);
                setForm(nv);
                updateStatus();
            }
        }
    }

    public void clearnForm() {
        txtMaSanPhaam.setText("");
        txtTenSanPham.setText("");
        txtSoLuong.setText("");
        txtGia.setText("");
        dcNgayNhap.setDate(null);
        txtMoTa.setText("");
        cboLoaiSanPham.setSelectedIndex(0);
        lblAnhDaiDien.setText("Ảnh đại diện");
        this.updateStatus();
        lblAnhDaiDien.setIcon(null);
        row = -1;
    }

    public void insert() {
        String error = hashError();
        if (error != null) {
            MsgBox.warning(this, error);
            return;
        }
        SanPhamObject kh = getForm();
        try {
            spdao.insert(kh);
            this.fillTable();
            this.clearnForm();
            MsgBox.alert(this, "Thêm thông tin thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.error(this, "Thêm thông tin thất bại!");
        }
    }

    public void update() {
        int row = tblSanPham.getSelectedRow();
        if (row < 0) {
            MsgBox.warning(this, "Vui lòng chọn 1 dòng dữ liệu để cập nhật!");
            return;
        }

        SanPhamObject sp = getForm();

        // Kiểm tra dữ liệu đầu vào
        String validationError = validateData(sp);
        if (validationError != null) {
            MsgBox.warning(this, validationError);
            return;
        }

        try {
            // Thực hiện cập nhật trong cơ sở dữ liệu
            spdao.update(sp);

            // Cập nhật bảng và làm sạch form
            this.fillTable();
            this.clearnForm();

            MsgBox.alert(this, "Cập nhật thông tin thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ
            MsgBox.error(this, "Sửa thông tin thất bại: " + e.getMessage());
        }
    }

    private String validateData(SanPhamObject sp) {
        try {
            if (sp.getTenSP().isEmpty() || sp.getSoLuong() < 0 || sp.getGiaSP() < 0 || sp.getNgayNhap() == null) {
                return "Vui lòng nhập đầy đủ thông tin và đảm bảo số lượng và giá là không âm.";
            }
            if (sp.getNgayNhap().after(XDate.now())) {
                return "(Ngày,Tháng,Năm) nhập không được lớn hơn thời gian hiện tại ";
            }
            if (sp.getSoLuong() < 0) {
                return "Số lượng không lớn hoặc nhỏ hơn bằng 0";
            }
            if (sp.getGiaSP() < 0) {
                return "Giá không không lớn hoặc nhỏ hơn bằng 0";
            }
            return null;
        } catch (NumberFormatException e) {
            return "Giá sản phẩm không hợp lệ. Vui lòng nhập một giá trị số hợp lệ.";
        }
    }

    public void delete() {
        if (!Auth.isManager()) {
            MsgBox.error(this, "Bạn không có quyền để sử dụng chức năng này!");
        } else {
            int row = tblSanPham.getSelectedRow();
            if (row < 0) {
                MsgBox.warning(this, "Vui lòng chọn dữ liệu để xóa!");
                return;
            }
            String makh = txtMaSanPhaam.getText();
            try {
                int xoa = JOptionPane.showConfirmDialog(this, "Bạn có chắc chán muốn xóa khách hàng " + makh + " không", "Xóa khách hàng", JOptionPane.YES_NO_OPTION);
                if (xoa == JOptionPane.YES_OPTION) {
                    spdao.delete(makh);
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

    public void tim() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        try {
            String timKiem = txtTimKiem.getText();
            List<SanPhamObject> list = spdao.select_all_sp(timKiem);
            for (SanPhamObject s : list) {
                model.addRow(new Object[]{
                    s.getMaSP(),
                    s.getTenSP(),
                    s.getSoLuong(),
                    NumberFormatter.formatNumber(s.getGiaSP()),
                    s.getNgayNhap()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.error(this, "Lỗi truy vấn dữ liệu");
        }
    }

    private static boolean isMaSanPhamInvalid(String maKhachHang) {
        // Kiểm tra nếu maKhachHang không đúng định dạng "KH" theo sau là 3 chữ số
        return maKhachHang != null && !maKhachHang.matches("SP\\d{3}");
    }

    private boolean kiemTraTrungMa(String maSanPham) {
        List<SanPhamObject> list = spdao.selectAll();
        for (SanPhamObject s : list) {
            if (s.getMaSP().equals(maSanPham)) {
                return true;
            }
        }
        return false;
    }

    public String hashError() {
        String maSanPham = txtMaSanPhaam.getText();
        String tenSanPham = txtTenSanPham.getText();
        String soLuong = txtSoLuong.getText();
        String gia = txtGia.getText();
        Date ngayNhap = dcNgayNhap.getDate();
        int loaiSP = cboLoaiSanPham.getSelectedIndex();
        if (maSanPham.isEmpty() || tenSanPham.isEmpty() || soLuong.isEmpty() || gia.isEmpty() || ngayNhap == null) {
            return "Vui lòng nhập đầy đủ thông tin.";
        }
        if (kiemTraTrungMa(maSanPham)) {
            return "Mã sản phẩm đã tồn tại!";
        }

        if (isMaSanPhamInvalid(maSanPham)) {
            return "Mã sản phẩm không đúng định dạng!";
        }
        if (ngayNhap.after(XDate.now())) {
            return "(Ngày,Tháng,Năm) nhập không được lớn hơn thời gian hiện tại ";
        }
        if (loaiSP == 0) {
            return "Vui lòng chọn loại sản phẩm trước khi sử dụng dụng chức năng!";
        }
        try {
            // Kiểm tra xem soLuong có thể chuyển đổi thành int hay không
            int soLuongInt = Integer.parseInt(soLuong);

            // Kiểm tra xem gia có thể chuyển đổi thành double hay không
            double giaDouble = Double.parseDouble(gia);

            // Kiểm tra xem soLuong và gia có lớn hơn hoặc bằng 0 không
            if (soLuongInt < 0 || giaDouble < 0) {
                return "Số lượng và giá phải lớn hơn hoặc bằng 0.";
            }
        } catch (NumberFormatException e) {
            return "Số lượng phải là số nguyên, và giá phải là số thực.";
        }

        if (lblAnhDaiDien.getIcon() == null) {
            return "Vui lòng chọn ảnh đại diện cho sản phẩm!";
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        TabNhanVien = new com.quanlyquananvat.chucNangGiaoDien.TabbedPaneCustom();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnXoa = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnSua = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnMoi = new com.quanlyquananvat.chucNangGiaoDien.Button();
        txtTenSanPham = new textfield.TextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        border_panel1 = new com.quanlyquananvat.chucNangGiaoDien.Border_panel();
        lblAnhDaiDien = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaSanPhaam = new textfield.TextField();
        jLabel4 = new javax.swing.JLabel();
        dcNgayNhap = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtSoLuong = new textfield.TextField();
        txtGia = new textfield.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTimKiem = new textfield.TextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnTrangDau = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnLui = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnTien = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnTrangCuoi = new com.quanlyquananvat.chucNangGiaoDien.Button();
        lblSoTrang = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cboLoaiSanPham = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));

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

        txtTenSanPham.setForeground(new java.awt.Color(0, 0, 0));
        txtTenSanPham.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Số lượng");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Ngày nhập");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Giá");

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
        jLabel3.setText("Mã sản phẩm");

        txtMaSanPhaam.setForeground(new java.awt.Color(0, 0, 0));
        txtMaSanPhaam.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tên sản phẩm");

        dcNgayNhap.setBackground(new java.awt.Color(255, 255, 255));
        dcNgayNhap.setForeground(new java.awt.Color(0, 0, 0));
        dcNgayNhap.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        dcNgayNhap.setMaxSelectableDate(new java.util.Date(253370743303000L));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Mô tả");

        txtSoLuong.setForeground(new java.awt.Color(0, 0, 0));
        txtSoLuong.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtGia.setForeground(new java.awt.Color(0, 0, 0));
        txtGia.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtMoTa.setBackground(new java.awt.Color(255, 255, 255));
        txtMoTa.setColumns(20);
        txtMoTa.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        txtMoTa.setForeground(new java.awt.Color(0, 0, 0));
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaSanPhaam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(dcNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(127, 127, 127)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(border_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSanPhaam, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(dcNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
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

        tblSanPham.setBackground(new java.awt.Color(255, 255, 255));
        tblSanPham.setForeground(new java.awt.Color(0, 0, 0));
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá ", "Ngày nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblSanPham.setRowHeight(30);
        tblSanPham.getTableHeader().setReorderingAllowed(false);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSanPhamMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

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
                .addGap(12, 12, 12)
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

        jLabel1.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 71, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sản phẩm");

        cboLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cboLoaiSanPham.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        cboLoaiSanPham.setForeground(new java.awt.Color(0, 0, 0));
        cboLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiSanPhamActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 51, 51));
        jLabel10.setText("Loại sản phẩm");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 176.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel7MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(cboLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addContainerGap(578, Short.MAX_VALUE)))
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

    private void lblAnhDaiDienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhDaiDienMouseClicked

    }//GEN-LAST:event_lblAnhDaiDienMouseClicked

    private void lblAnhDaiDienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhDaiDienMousePressed
        if (evt.getClickCount() == 2) {
            chonAnh();
        }
    }//GEN-LAST:event_lblAnhDaiDienMousePressed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        tim();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tblSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMousePressed
        if (evt.getClickCount() == 2) {
            this.row = tblSanPham.rowAtPoint(evt.getPoint());
            edit();
        }
    }//GEN-LAST:event_tblSanPhamMousePressed

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
        int totalPages = (int) Math.ceil((double) spdao.selectAll().size() / recordsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            fillTable();
        }
    }//GEN-LAST:event_btnTienActionPerformed

    private void btnTrangCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangCuoiActionPerformed
        int totalPages = (int) Math.ceil((double) spdao.selectAll().size() / recordsPerPage);
        if (currentPage < totalPages) {
            currentPage = totalPages;
            fillTable();
        }
    }//GEN-LAST:event_btnTrangCuoiActionPerformed

    private void TabNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabNhanVienMouseClicked

    }//GEN-LAST:event_TabNhanVienMouseClicked

    private void cboLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiSanPhamActionPerformed
    private void handleLabel7DoubleClick(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            LoaiSanPham loaiSanPhamDialog = new LoaiSanPham((JFrame) SwingUtilities.getWindowAncestor(SanPham.this), true);
            loaiSanPhamDialog.addDataAddedListener(new DataAddedListener() {
                @Override
                public void onDataAdded() {
                    fillComBoBox();
                }
            });
            loaiSanPhamDialog.setVisible(true);
        }
    }

    private void jLabel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MousePressed
        handleLabel7DoubleClick(evt);
    }//GEN-LAST:event_jLabel7MousePressed


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
    private javax.swing.JComboBox<String> cboLoaiSanPham;
    private com.toedter.calendar.JDateChooser dcNgayNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnhDaiDien;
    private javax.swing.JLabel lblSoTrang;
    private javax.swing.JTable tblSanPham;
    private textfield.TextField txtGia;
    private textfield.TextField txtMaSanPhaam;
    private javax.swing.JTextArea txtMoTa;
    private textfield.TextField txtSoLuong;
    private textfield.TextField txtTenSanPham;
    private textfield.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
