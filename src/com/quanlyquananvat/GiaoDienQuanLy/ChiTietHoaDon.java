package com.quanlyquananvat.GiaoDienQuanLy;

import com.quanlyquananvat.Object.GiaoDichObject;
import com.quanlyquananvat.ThuVienTienIch.Auth;
import com.quanlyquananvat.ThuVienTienIch.MsgBox;
import com.quanlyquananvat.ThuVienTienIch.NumberFormatter;
import com.quanlyquananvat.dao.HoaDonChiTietDAO;
import com.quanlyquananvat.dao.HoaDonDAO;
import com.quanlyquananvat.dao.InHoaDonDAO;
import java.awt.Graphics2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JOptionPane;

public class ChiTietHoaDon extends javax.swing.JDialog {

    Vector<LayTenVaSoLuong> vector = new Vector<>();
    InHoaDonDAO hdeAO = new InHoaDonDAO();
    HoaDonDAO hddao = new HoaDonDAO();
    HoaDonChiTietDAO hddctdao = new HoaDonChiTietDAO();
    SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
    int row = -1;
    Date s = new Date();

    public ChiTietHoaDon(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lblHD.setText("Mã hóa đơn: " + GiaoDichObject.getMaHD() + "");
        lblNhanVien.setText("Nhân viên: " + Auth.user.getMaNV());
        lblKhachHang.setText("Khách hàng: " + GiaoDichObject.getMaKH());
        lblThoiGian.setText("Thời gian: " + d.format(s));
        setLocationRelativeTo(null);
        fillTableHoaDon();
        tatBillOrder.setEditable(false);
    }
    double tongTien = 0;
    double phiGiaoHang = 0;

    public void fillTableHoaDon() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        try {
            List<Object[]> list = hdeAO.getThongTinSanPham(GiaoDichObject.getMaHD());

            for (Object[] objects : list) {
                model.addRow(new Object[]{
                    objects[0],
                    objects[1],
                    objects[3],
                    objects[2],
                    objects[4],
                    objects[5],
                    objects[6],});
                tongTien = Double.parseDouble(objects[6].toString());
                phiGiaoHang = Double.parseDouble(objects[2].toString());
            }
        } catch (Exception e) {
            MsgBox.error(this, "Không truy vấn được bảng người học này!");
        }
    }

    public void inHoadon() {
        try {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss -> dd/MM/yyy");
            tatBillOrder.append("                                     Quán ăn vặt ninh kiểu\n");
            tatBillOrder.append("                Đường Hai Bà Trưng, Quận Ninh Kiều,TP Cần Thơ.\n");
            tatBillOrder.append("                                         +84901672362\n");
            tatBillOrder.append("-----------------------------------------------------------------------------------\n");
            DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
            String tenSanPham = tblHoaDon.getValueAt(row, 2).toString();
            String soLuong = tblHoaDon.getValueAt(row, 4).toString();
            String GiaSanPham = tblHoaDon.getValueAt(row, 5).toString();
            String PhiVanChuyen = tblHoaDon.getValueAt(row, 3).toString();
            String tongTien = tblHoaDon.getValueAt(row, 6).toString();

            tatBillOrder.append("Tên Sản Phẩm: " + tenSanPham + "\n");
            tatBillOrder.append("Số Lượng: " + soLuong + "\n");
            tatBillOrder.append("Giá Sản Phẩm: " + GiaSanPham + "\n");
            tatBillOrder.append("Phí Vận Chuyển: " + PhiVanChuyen + "\n");
            tatBillOrder.append("Tổng Tiền: " + tongTien + "\n");
            tatBillOrder.append("Thời gian: " + dateFormat.format(date) + "\n");
            tatBillOrder.append("-----------------------------------------------------------------------------------\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printAndSaveInvoice() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu hóa đơn");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)", "txt"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                // Lấy đường dẫn được chọn
                String filePath = fileChooser.getSelectedFile().getPath();

                // Đối tượng FileWriter với true để append vào nếu file đã tồn tại
                FileWriter fileWriter = new FileWriter(filePath, true);
                // Đối tượng BufferedWriter để cung cấp bộ đệm cho việc ghi
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                // Lấy thông tin từ bảng
                DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
                String tenSanPham = model.getValueAt(row, 2).toString();
                String soLuong = model.getValueAt(row, 4).toString();
                String giaSanPham = model.getValueAt(row, 5).toString();
                String phiVanChuyen = model.getValueAt(row, 3).toString();
                String tongTien = model.getValueAt(row, 6).toString();

                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss -> dd/MM/yyy");
                tatBillOrder.append("                                     Quán ăn vặt ninh kiểu\n");
                tatBillOrder.append("                Đường Hai Bà Trưng, Quận Ninh Kiều,TP Cần Thơ.\n");
                tatBillOrder.append("                                         +84901672362\n");
                tatBillOrder.append("-----------------------------------------------------------------------------------\n");
                tatBillOrder.append("Tên Sản Phẩm: " + tenSanPham + "\n");
                tatBillOrder.append("Số Lượng: " + soLuong + "\n");
                tatBillOrder.append("Giá Sản Phẩm: " + giaSanPham + "\n");
                tatBillOrder.append("Phí Vận Chuyển: " + phiVanChuyen + "\n");
                tatBillOrder.append("Tổng Tiền: " + tongTien + "\n");
                tatBillOrder.append("Thời gian: " + dateFormat.format(date) + "\n");
                tatBillOrder.append("-----------------------------------------------------------------------------------\n");

                // Lưu vào file
                bufferedWriter.write("                                     Quán ăn vặt ninh kiểu\n");
                bufferedWriter.write("                Đường Hai Bà Trưng, Quận Ninh Kiều,TP Cần Thơ.\n");
                bufferedWriter.write("                                         +84901672362\n");
                bufferedWriter.write("-----------------------------------------------------------------------------------\n");
                bufferedWriter.write("Tên Sản Phẩm: " + tenSanPham + "\n");
                bufferedWriter.write("Số Lượng: " + soLuong + "\n");
                bufferedWriter.write("Giá Sản Phẩm: " + giaSanPham + "\n");
                bufferedWriter.write("Phí Vận Chuyển: " + phiVanChuyen + "\n");
                bufferedWriter.write("Tổng Tiền: " + tongTien + "\n");
                bufferedWriter.write("Thời gian: " + dateFormat.format(date) + "\n");
                bufferedWriter.write("-----------------------------------------------------------------------------------\n");

                // Đóng BufferedWriter để giải phóng tài nguyên
                bufferedWriter.close();

                System.out.println("Đã in và lưu hóa đơn vào file " + filePath);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        lblHD = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        lblKhachHang = new javax.swing.JLabel();
        lblThoiGian = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tatBillOrder = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();

        jLabel5.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 71, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Danh sách khách hàng");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        jMenuItem4.setText("jMenuItem4");
        jPopupMenu4.add(jMenuItem4);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));

        lblHD.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblHD.setForeground(new java.awt.Color(0, 0, 0));
        lblHD.setText("Mã hóa đơn:             ");

        lblNhanVien.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(0, 0, 0));
        lblNhanVien.setText("Nhân viên:");

        lblKhachHang.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblKhachHang.setForeground(new java.awt.Color(0, 0, 0));
        lblKhachHang.setText("Khách hàng:");

        lblThoiGian.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblThoiGian.setForeground(new java.awt.Color(0, 0, 0));
        lblThoiGian.setText("Thời gian:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Chi tiết hóa đơn");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel7.setBackground(new java.awt.Color(51, 51, 255));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/Logo.png"))); // NOI18N
        jLabel7.setText("Cửa hàng bán đồ ăn");

        tatBillOrder.setColumns(20);
        tatBillOrder.setRows(5);
        tatBillOrder.setBorder(null);
        jScrollPane1.setViewportView(tatBillOrder);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Ngày tạo", "Tên sản phẩm ", "Phí vận chuyển", "Số lượng", "Giá sản phẩm", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHoaDonMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 467, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                        .addComponent(lblThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(399, 399, 399)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lblHD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNhanVien))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)))
                .addGap(5, 5, 5)
                .addComponent(lblKhachHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThoiGian)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

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

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            JPopupMenu pop = new JPopupMenu();
//            JMenuItem item = new JMenuItem("Xuất hóa đơn");
            JMenuItem itom = new JMenuItem("In hóa đơn");
//            item.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    printAndSaveInvoice();
//                }
//            });
            itom.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = tblHoaDon.getRowCount();
                    for (int i = 0; i < row; i++) {
                        Object tenSanPham = tblHoaDon.getValueAt(i, 2);
                        Object soLuong = tblHoaDon.getValueAt(i, 4);
                        Object giaSanPham = tblHoaDon.getValueAt(i, 5);
                        LayTenVaSoLuong item = new LayTenVaSoLuong();
                        item.setTenSanPham(tenSanPham.toString());
                        item.setSoLuong(Integer.parseInt(soLuong.toString()));
                        item.setGiaSp(Double.parseDouble(giaSanPham.toString()));
                        vector.add(item);
                    }
                    inHoaDon();
                }
            });
//            pop.add(item);
            pop.add(itom);
            pop.show(tblHoaDon, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked
//    public void inHoaDon() {
//        tatBillOrder.setText("");
//        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
//
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss -> dd/MM/yyyy");
//
//        // Sử dụng StringBuilder để xây dựng chuỗi hóa đơn
//        StringBuilder billContent = new StringBuilder();
//        billContent.append("                                  Quán ăn vặt ninh kiểu\n");
//        billContent.append("              Đường Hai Bà Trưng, Quận Ninh Kiều, TP Cần Thơ.\n");
//        billContent.append("                                      +84901672362\n");
//        billContent.append("-----------------------------------------------------------------------------------\n");
//        billContent.append(String.format("%-25s%-8s%-20s%-10s\n", "Tên Sản Phẩm", "SL", "Giá", "Thành Tiền"));
//        billContent.append("-----------------------------------------------------------------------------------\n");
//
//        // Duyệt qua tất cả các sản phẩm trong vector và thêm thông tin vào StringBuilder
//        for (LayTenVaSoLuong tenSanPham : vector) {
//            billContent.append(String.format("%-25s%-8s%-20s%-10s\n", tenSanPham.getTenSanPham(), tenSanPham.soLuong, tenSanPham.giaSp, NumberFormatter.format(tenSanPham.getSoLuong() * tenSanPham.getGiaSp())));
//        }
//
//        // Lấy thông tin từ hàng được chọn
//        int row = tblHoaDon.getSelectedRow();
//        String phiVanChuyen = model.getValueAt(row, 3).toString();
//        double giaoHangNhanh = GiaoDichObject.getGiaoHangNhanh();
//
//        // Thêm thông tin phí và tổng tiền vào StringBuilder
//        billContent.append("-----------------------------------------------------------------------------------\n");
//        billContent.append(String.format("%-30s%-20s\n", "Phí địa chỉ vận chuyển:", phiVanChuyen));
//        billContent.append(String.format("%-30s%-20s\n", "Phí giao nhanh:", giaoHangNhanh));
//        billContent.append(String.format("%-30s%-20s\n", "Tổng Tiền:", tongTien));
//        billContent.append(String.format("%-30s%-20s\n", "Thời gian:", dateFormat.format(date)));
//        billContent.append("-----------------------------------------------------------------------------------\n");
//
//        // Sử dụng StringBuilder để hiển thị nội dung chuỗi vào JTextArea
//        tatBillOrder.append(billContent.toString());
//
//        // Gọi phương thức in hóa đơn
//        printInvoice();
//    }

    public void inHoaDon() {
        tatBillOrder.setText("");
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss -> dd/MM/yyyy");
        // Duyệt qua tất cả các sản phẩm trong vector và in thông tin hóa đơn cho mỗi sản phẩm
        tatBillOrder.append("                                  Quán ăn vặt ninh kiểu\n");
        tatBillOrder.append("              Đường Hai Bà Trưng, Quận Ninh Kiều, TP Cần Thơ.\n");
        tatBillOrder.append("                                      +84901672362\n");
        tatBillOrder.append("-----------------------------------------------------------------------------------\n");
        tatBillOrder.append("Tên Sản Phẩm	         SL	   Giá	  Tổng tiền\n"
                + "-----------------------------------------------------------------------------------\n");
        for (LayTenVaSoLuong tenSanPham : vector) {
            tatBillOrder.append("" + tenSanPham.getTenSanPham() + "	         " + tenSanPham.soLuong + "	   " + tenSanPham.giaSp + "                  " + (NumberFormatter.format(tenSanPham.getSoLuong() * tenSanPham.getGiaSp())) + "\n");
        }

        // Lấy thông tin từ hàng được chọn (thông tin giống nhau cho tất cả sản phẩm trong vector)
        int row = tblHoaDon.getSelectedRow();
        String phiVanChuyen = model.getValueAt(row, 3).toString();
        double giaoHangNhanh = 0;
        giaoHangNhanh = GiaoDichObject.getGiaoHangNhanh();
        // In thông tin chung cho tất cả sản phẩm trong vector
        tatBillOrder.append("-----------------------------------------------------------------------------------\n");
        tatBillOrder.append("Phí địa chỉ vận chuyển: " + phiVanChuyen + "\n");
        tatBillOrder.append("Phí giao nhanh: " + giaoHangNhanh + "\n");
        tatBillOrder.append("Tổng Tiền: " + (tongTien) + "\n");
        tatBillOrder.append("Thời gian: " + dateFormat.format(date) + "\n");
        tatBillOrder.append("-----------------------------------------------------------------------------------\n");
        printInvoice();
    }

    private void printInvoice() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            // Kích thước trang in
            int pageWidth = (int) pageFormat.getImageableWidth();
            int pageHeight = (int) pageFormat.getImageableHeight();

            // Margin
            int marginLeft = (int) pageFormat.getImageableX() + 100;
            int marginTop = (int) pageFormat.getImageableY() + 100;

            // Dòng hiện tại để in
            int currentLine = 0;

            // Vòng lặp để in từng dòng
            for (String line : tatBillOrder.getText().split("\n")) {
                graphics.drawString(line, marginLeft, marginTop + currentLine * graphics.getFontMetrics().getHeight());
                currentLine++;
            }

            return Printable.PAGE_EXISTS;
        });

        try {
            boolean doPrint = printerJob.printDialog();
            if (doPrint) {
                printerJob.print();
            }
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
//    private Font smallFont; // Đặt biến Font ở ngoài
//    private void printInvoice() {
//        PrinterJob printerJob = PrinterJob.getPrinterJob();
//        printerJob.setPrintable(
//                (graphics, pageFormat, pageIndex) -> {
//                    if (pageIndex > 0) {
//                        return Printable.NO_SUCH_PAGE;
//                    }
//
//                    // Kích thước trang in
//                    int pageWidth = (int) pageFormat.getImageableWidth();
//                    int pageHeight = (int) pageFormat.getImageableHeight();
//
//                    // Kiểm tra nếu kích thước trang in lớn hơn giấy 58mm, điều chỉnh tỷ lệ
//                    double scale = 1.0;
//                    if (pageWidth > 58) {
//                        scale = 58.0 / pageWidth;
//                    }
//
//                    // Tạo ma trận biến đổi để thay đổi tỷ lệ
//                    Graphics2D g2d = (Graphics2D) graphics;
//                    g2d.scale(scale, scale);
//
//                    // Margin và vị trí dòng hiện tại để in
//                    int marginLeft = (int) (pageFormat.getImageableX() + 10 * scale);
//                    int marginTop = (int) (pageFormat.getImageableY() + 10 * scale);
//
//                    // Dòng hiện tại để in
//                    int currentLine = 0;
//
//                    // Vòng lặp để in từng dòng
//                    for (String line : tatBillOrder.getText().split("\n")) {
//                        graphics.drawString(line, marginLeft, marginTop + currentLine * graphics.getFontMetrics().getHeight());
//                        currentLine++;
//                    }
//
//                    return Printable.PAGE_EXISTS;
//                }
//        );
//
//        try {
//            PageFormat pageFormat = printerJob.defaultPage();
//            Paper paper = new Paper();
//            // Đặt kích thước giấy thành 58mm
//            paper.setSize(58 * 72.0 / 25.4, paper.getHeight());
//            pageFormat.setPaper(paper);
//
//            boolean doPrint = printerJob.printDialog();
//            if (doPrint) {
//                printerJob.print();
//            }
//            System.out.println("Kích thước giấy: " + pageFormat.getImageableWidth() + " x " + pageFormat.getImageableHeight());
//
//        } catch (PrinterException e) {
//            e.printStackTrace();
//        }
//    }
//private void printInvoice() {
//    PrinterJob printerJob = PrinterJob.getPrinterJob();
//
//    printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
//        if (pageIndex > 0) {
//            return Printable.NO_SUCH_PAGE;
//        }
//
//        // Kích thước trang in
//        int pageWidth = (int) pageFormat.getImageableWidth();
//        int pageHeight = (int) pageFormat.getImageableHeight();
//
//        // Kiểm tra nếu kích thước trang in lớn hơn giấy 48mm, điều chỉnh tỷ lệ
//        double scale = 1.0;
//        if (pageWidth > 48) {
//            scale = 48.0 / pageWidth;
//        }
//
//        // In thông tin debug
//        System.out.println("Chiều Rộng Có Thể In (trước khi thu nhỏ): " + pageFormat.getImageableWidth());
//        System.out.println("Chiều Dài Có Thể In (trước khi thu nhỏ): " + pageFormat.getImageableHeight());
//        System.out.println("Tỷ Lệ: " + scale);
//
//        // Tạo ma trận biến đổi để thay đổi tỷ lệ
//        Graphics2D g2d = (Graphics2D) graphics;
//        g2d.scale(scale, scale);
//
//        // Margin và vị trí dòng hiện tại để in
//        int marginLeft = (int) (pageFormat.getImageableX() + 10 * scale);
//        int marginTop = (int) (pageFormat.getImageableY() + 10 * scale);
//
//        // Dòng hiện tại để in
//        int currentLine = 0;
//
//        // Vòng lặp để in từng dòng
//        for (String line : tatBillOrder.getText().split("\n")) {
//            graphics.drawString(line, marginLeft, marginTop + currentLine * graphics.getFontMetrics().getHeight());
//            currentLine++;
//        }
//
//        return Printable.PAGE_EXISTS;
//    });
//
//    try {
//        PageFormat pageFormat = printerJob.defaultPage();
//        Paper paper = new Paper();
//
//        // Sử dụng kích thước giấy từ PageFormat
//        paper.setSize(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());
//        pageFormat.setPaper(paper);
//
//        boolean doPrint = printerJob.printDialog();
//        if (doPrint) {
//            printerJob.print();
//        }
//        System.out.println("Kích thước giấy: " + pageFormat.getImageableWidth() + " x " + pageFormat.getImageableHeight());
//
//    } catch (PrinterException e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(null, "Lỗi in: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//    }
//}


    private void tblHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMousePressed
        row = tblHoaDon.getSelectedRow();
        if (evt.getClickCount() == 2) {

        }
    }//GEN-LAST:event_tblHoaDonMousePressed

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
            java.util.logging.Logger.getLogger(ChiTietHoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChiTietHoaDon dialog = new ChiTietHoaDon(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblHD;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblThoiGian;
    private javax.swing.JTextArea tatBillOrder;
    private javax.swing.JTable tblHoaDon;
    // End of variables declaration//GEN-END:variables
}
