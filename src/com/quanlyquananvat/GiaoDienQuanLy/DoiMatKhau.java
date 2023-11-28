/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.quanlyquananvat.GiaoDienQuanLy;

import com.quanlyquananvat.Object.NhanVienObject;
import com.quanlyquananvat.ThuVienTienIch.Auth;
import com.quanlyquananvat.ThuVienTienIch.MsgBox;
import com.quanlyquananvat.ThuVienTienIch.Ximge;
import com.quanlyquananvat.dao.NhanVienDAO;
import java.awt.Color;

/**
 *
 * @author pc
 */
public class DoiMatKhau extends javax.swing.JDialog {

    /**
     * Creates new form DoiMatKhau
     */
    public DoiMatKhau(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    public void init() {
        setLocationRelativeTo(null);
        btnHuy.setBackground(new java.awt.Color(255, 0, 0)); // Màu đỏ
        btnXacNhan.setBackground(new java.awt.Color(0, 255, 0)); // Màu xanh lá cây
        btnHuy.setForeground(new java.awt.Color(255, 255, 255)); // Màu trắng
        btnXacNhan.setForeground(new java.awt.Color(255, 255, 255)); // Màu đen
        setTitle("ĐỔI MẬT KHẨU");
        txtTaiKhoan.setText(Auth.user.getMaNV());
        fillImage();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        avtAnhDaiDien = new com.quanlyquananvat.chucNangGiaoDien.ImageAvatar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnHuy = new com.quanlyquananvat.chucNangGiaoDien.Button();
        btnXacNhan = new com.quanlyquananvat.chucNangGiaoDien.Button();
        txtTaiKhoan = new textfield.TextField();
        txtMatKhauCu = new textfield.PasswordField();
        txtMatKhauMoi = new textfield.PasswordField();
        txtXacNhanMatKhau = new textfield.PasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(54, 54, 54));

        avtAnhDaiDien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 1.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Đổi mật khẩu");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tài khoản:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mật khẩu cũ:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Mật khẩu mới:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Xác nhận mật khẩu:");

        btnHuy.setForeground(new java.awt.Color(0, 0, 0));
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/huy.png"))); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnHuy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnXacNhan.setForeground(new java.awt.Color(0, 0, 0));
        btnXacNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/xacNhan.png"))); // NOI18N
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnXacNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXacNhanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXacNhanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnXacNhanMousePressed(evt);
            }
        });
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        txtTaiKhoan.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtTaiKhoan.setShadowColor(new java.awt.Color(255, 255, 255));

        txtMatKhauCu.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtMatKhauCu.setShadowColor(new java.awt.Color(255, 255, 255));

        txtMatKhauMoi.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtMatKhauMoi.setShadowColor(new java.awt.Color(255, 255, 255));
        txtMatKhauMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauMoiActionPerformed(evt);
            }
        });

        txtXacNhanMatKhau.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtXacNhanMatKhau.setShadowColor(new java.awt.Color(255, 255, 255));
        txtXacNhanMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtXacNhanMatKhauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtMatKhauCu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(98, 98, 98)
                                    .addComponent(avtAnhDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel2)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(118, 118, 118)
                                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(avtAnhDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
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
    public void fillImage() {
        int width = avtAnhDaiDien.getWidth();
        int height = avtAnhDaiDien.getHeight();
        if (Auth.user.getHinhAnh() != null && !Auth.user.getHinhAnh().equals("")) {
            // Thực hiện các thao tác liên quan đến hình ảnh ở đây

            avtAnhDaiDien.setIcon(Ximge.read(Auth.user.getHinhAnh(), width, height));
            avtAnhDaiDien.setToolTipText(Auth.user.getHinhAnh());
        }
    }
    NhanVienDAO nvdao = new NhanVienDAO();

    private void xacNhanMatKhau() {
        String tenTaiKhoan = txtTaiKhoan.getText();
        String matKhauCu = new String(txtMatKhauCu.getPassword());
        String matKhauMoi = new String(txtMatKhauMoi.getPassword());
        String xacNhanMatKhau = new String(txtXacNhanMatKhau.getPassword());

        if (tenTaiKhoan == null || tenTaiKhoan.isEmpty()) {
            MsgBox.error(this, "Vui lòng nhập tên đăng nhập!");
        } else if (matKhauCu == null || matKhauCu.isEmpty()) {
            MsgBox.error(this, "Vui lòng nhập mật khẩu cũ!");
        } else if (matKhauMoi == null || matKhauMoi.isEmpty()) {
            MsgBox.error(this, "Vui lòng nhập mật khẩu mới!");
        } else if (xacNhanMatKhau == null || xacNhanMatKhau.isEmpty()) {
            MsgBox.error(this, "Vui lòng xác nhận mật khẩu!");
        } else if (!tenTaiKhoan.equalsIgnoreCase(Auth.user.getMaNV())) {
            MsgBox.error(this, "Sai tên đăng nhập!");
        } else if (!matKhauCu.equals(Auth.user.getMauKhau())) {
            MsgBox.error(this, "Sai mật khẩu!");
        } else if (matKhauMoi.equals(Auth.user.getMauKhau())) {
            MsgBox.error(this, "Mật khẩu mới phải khác mật khẩu cũ!");
        } else if (!matKhauMoi.equals(xacNhanMatKhau)) {
            MsgBox.error(this, "Xác nhận mật khẩu không đúng");
        } else {
            Auth.user.setMauKhau(matKhauMoi);
            nvdao.update(Auth.user);
            MsgBox.alert(this, "Đổi mật khẩu thành công");
            this.dispose();
        }
    }

    private void txtMatKhauMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauMoiActionPerformed

    private void txtXacNhanMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtXacNhanMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtXacNhanMatKhauActionPerformed

    private void btnXacNhanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXacNhanMousePressed
        if (btnXacNhan.isSelected()) {
            btnXacNhan.setBackground(new Color(255, 128, 0)); // Đổi màu nền khi bấm
        } else {
            btnXacNhan.setBackground(new Color(0, 255, 0));
        }
    }//GEN-LAST:event_btnXacNhanMousePressed

    private void btnXacNhanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXacNhanMouseExited

    }//GEN-LAST:event_btnXacNhanMouseExited

    private void btnXacNhanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXacNhanMouseEntered

    }//GEN-LAST:event_btnXacNhanMouseEntered

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        xacNhanMatKhau();
    }//GEN-LAST:event_btnXacNhanActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DoiMatKhau dialog = new DoiMatKhau(new javax.swing.JFrame(), true);
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
    private com.quanlyquananvat.chucNangGiaoDien.ImageAvatar avtAnhDaiDien;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnHuy;
    private com.quanlyquananvat.chucNangGiaoDien.Button btnXacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private textfield.PasswordField txtMatKhauCu;
    private textfield.PasswordField txtMatKhauMoi;
    private textfield.TextField txtTaiKhoan;
    private textfield.PasswordField txtXacNhanMatKhau;
    // End of variables declaration//GEN-END:variables
}
