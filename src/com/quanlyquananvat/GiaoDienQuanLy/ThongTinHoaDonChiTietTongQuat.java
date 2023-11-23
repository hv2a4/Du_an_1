package com.quanlyquananvat.GiaoDienQuanLy;

import com.quanlyquananvat.Object.HoaDonChiTietObject;
import com.quanlyquananvat.ThuVienTienIch.MsgBox;
import com.quanlyquananvat.ThuVienTienIch.Ximge;
import com.quanlyquananvat.dao.HoaDonChiTietDAO;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ThongTinHoaDonChiTietTongQuat extends javax.swing.JDialog {

    private int currentPage = 1; // trang hiện tại
    private int recordsPerPage = 10;// số dòng dữ liệu trên table
    HoaDonChiTietDAO hđctdao = new HoaDonChiTietDAO();

    public ThongTinHoaDonChiTietTongQuat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("CHI TIẾT HÓA ĐƠN");
        setIconImage(Ximge.getAppIcon());
        setLocationRelativeTo(null);
        fillTalbe();
        tblHoaDonChiTiet.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tblHoaDonChiTiet.getTableHeader().setOpaque(false);
        tblHoaDonChiTiet.getTableHeader().setBackground(new Color(0, 102, 255));
        tblHoaDonChiTiet.getTableHeader().setForeground(new Color(0, 0, 0));
        tblHoaDonChiTiet.setRowHeight(25);
    }

    public void fillTalbe() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        model.setRowCount(0);
        try {
            List<HoaDonChiTietObject> list = hđctdao.selectAll();
            int totalRecords = list.size();
            // tính tổng số trang 
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
            // Xác định dòng bắt đầu và dòng kết thúc trên trang hiện tại
            int startIndex = (currentPage - 1) * recordsPerPage;
            int endIndex = Math.min(startIndex + recordsPerPage, totalRecords);

            for (int i = startIndex; i < endIndex; i++) {
                HoaDonChiTietObject e = list.get(i);
                model.addRow(new Object[]{
                    e.getMaHoaDonChiTiet(),
                    e.getMaHoaDon(),
                    e.getMaSanPham(),
                    e.getTenSP(),
                    e.getSoLuong(),
                    e.getGia(),
                    e.getTongTien()
                });
                model.fireTableDataChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.error(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        btnTrangDau = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnLui = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnTien = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnTrangCuoi = new com.quanlyquananvat.chucNangGiaoDien.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thông tin tổng quát về hóa đơn chi tiết");

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HDCT", "Mã HD", "Mã SP", "Tên sản phẩm", "Số lượng", "Giá sản phẩm", "Tổng tiền"
            }
        ));
        tblHoaDonChiTiet.setRowHeight(30);
        tblHoaDonChiTiet.setShowHorizontalLines(true);
        tblHoaDonChiTiet.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblHoaDonChiTiet);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTrangDau, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnTien, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTrangCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLui, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrangDau, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrangCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTien, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
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

    private void btnTrangDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangDauActionPerformed
        if (currentPage > 1) {
            currentPage = 1;
            fillTalbe();
        }
    }//GEN-LAST:event_btnTrangDauActionPerformed

    private void btnLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiActionPerformed
        if (currentPage > 1) {
            currentPage--;
            fillTalbe();
        }
    }//GEN-LAST:event_btnLuiActionPerformed

    private void btnTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienActionPerformed
        int totalPages = (int) Math.ceil((double) hđctdao.selectAll().size() / recordsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            fillTalbe();
        }
    }//GEN-LAST:event_btnTienActionPerformed

    private void btnTrangCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangCuoiActionPerformed
        int totalPages = (int) Math.ceil((double) hđctdao.selectAll().size() / recordsPerPage);
        if (currentPage < totalPages) {
            currentPage = totalPages;
            fillTalbe();
        }
    }//GEN-LAST:event_btnTrangCuoiActionPerformed

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
            java.util.logging.Logger.getLogger(ThongTinHoaDonChiTietTongQuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongTinHoaDonChiTietTongQuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongTinHoaDonChiTietTongQuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongTinHoaDonChiTietTongQuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThongTinHoaDonChiTietTongQuat dialog = new ThongTinHoaDonChiTietTongQuat(new javax.swing.JFrame(), true);
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
    private com.quanlyquananvat.chucNangGiaoDien.Button btnLui;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTien;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTrangCuoi;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnTrangDau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHoaDonChiTiet;
    // End of variables declaration//GEN-END:variables
}
