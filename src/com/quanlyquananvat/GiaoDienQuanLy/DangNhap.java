/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.quanlyquananvat.GiaoDienQuanLy;

import com.quanlyquananvat.Object.NhanVienObject;
import com.quanlyquananvat.ThuVienTienIch.Auth;
import com.quanlyquananvat.ThuVienTienIch.MsgBox;
import com.quanlyquananvat.dao.NhanVienDAO;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

/**
 *
 * @author pc
 */
public class DangNhap extends javax.swing.JDialog {

    /**
     * Creates new form DangNhap
     */
    public DangNhap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        btnDangNhap.setBorderPainted(false);
        btnDangNhap.setContentAreaFilled(false);
        setLocationRelativeTo(null);
        txtTaiKhoan.setText("NV001");
        txtMatKhau.setText("123");
        
    }
    
    NhanVienDAO entity = new NhanVienDAO();
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        border_panel1 = new com.quanlyquananvat.chucNangGiaoDien.Border_panel();
        jLabel3 = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        border_panel2 = new com.quanlyquananvat.chucNangGiaoDien.Border_panel();
        jLabel4 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        panelDangNhap = new com.quanlyquananvat.chucNangGiaoDien.Border_panel();
        btnDangNhap = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        ckbXemPass = new com.quanlyquananvat.chucNangGiaoDien.JCheckBoxCustom();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ĐĂNG NHẬP");

        border_panel1.setBackground(new java.awt.Color(221, 221, 221));
        border_panel1.setForeground(new java.awt.Color(221, 221, 221));
        border_panel1.setRoundBottomLeft(25);
        border_panel1.setRoundBottomRight(25);
        border_panel1.setRoundTopLeft(25);
        border_panel1.setRoundTopRight(25);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 2.png"))); // NOI18N

        txtTaiKhoan.setBackground(new java.awt.Color(221, 221, 221));
        txtTaiKhoan.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtTaiKhoan.setForeground(new java.awt.Color(0, 0, 0));
        txtTaiKhoan.setBorder(null);
        txtTaiKhoan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTaiKhoanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTaiKhoanFocusLost(evt);
            }
        });

        javax.swing.GroupLayout border_panel1Layout = new javax.swing.GroupLayout(border_panel1);
        border_panel1.setLayout(border_panel1Layout);
        border_panel1Layout.setHorizontalGroup(
            border_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(border_panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTaiKhoan)
                .addContainerGap())
        );
        border_panel1Layout.setVerticalGroup(
            border_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, border_panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTaiKhoan)
                .addContainerGap())
        );

        border_panel2.setBackground(new java.awt.Color(221, 221, 221));
        border_panel2.setForeground(new java.awt.Color(221, 221, 221));
        border_panel2.setRoundBottomLeft(25);
        border_panel2.setRoundBottomRight(25);
        border_panel2.setRoundTopLeft(25);
        border_panel2.setRoundTopRight(25);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/image 3.png"))); // NOI18N

        txtMatKhau.setBackground(new java.awt.Color(221, 221, 221));
        txtMatKhau.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        txtMatKhau.setForeground(new java.awt.Color(0, 0, 0));
        txtMatKhau.setBorder(null);
        txtMatKhau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMatKhauFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMatKhauFocusLost(evt);
            }
        });

        javax.swing.GroupLayout border_panel2Layout = new javax.swing.GroupLayout(border_panel2);
        border_panel2.setLayout(border_panel2Layout);
        border_panel2Layout.setHorizontalGroup(
            border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(border_panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );
        border_panel2Layout.setVerticalGroup(
            border_panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, border_panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelDangNhap.setBackground(new java.awt.Color(0, 255, 0));
        panelDangNhap.setRoundBottomLeft(40);
        panelDangNhap.setRoundBottomRight(40);
        panelDangNhap.setRoundTopLeft(40);
        panelDangNhap.setRoundTopRight(40);
        panelDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelDangNhapMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelDangNhapMousePressed(evt);
            }
        });

        btnDangNhap.setBackground(new java.awt.Color(0, 255, 0));
        btnDangNhap.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnDangNhap.setText("Đăng nhập");
        btnDangNhap.setBorder(null);
        btnDangNhap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDangNhapMouseEntered(evt);
            }
        });
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDangNhapLayout = new javax.swing.GroupLayout(panelDangNhap);
        panelDangNhap.setLayout(panelDangNhapLayout);
        panelDangNhapLayout.setHorizontalGroup(
            panelDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDangNhapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelDangNhapLayout.setVerticalGroup(
            panelDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDangNhapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlyquananvat/Icon/icons8-close-30.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        ckbXemPass.setForeground(new java.awt.Color(0, 0, 0));
        ckbXemPass.setText("Xem mật khẩu");
        ckbXemPass.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ckbXemPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbXemPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckbXemPass, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(border_panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(border_panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(44, 44, 44))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(border_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(border_panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ckbXemPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    public void dangNhap() {
        String maNV = txtTaiKhoan.getText();
        String matKhau = new String(txtMatKhau.getPassword());
        NhanVienObject nv = entity.selectById(maNV);
        if (nv == null) {
            MsgBox.alert(this, "Sai tên đăng nhập");
        } else {
            if (!nv.getMauKhau().equals(matKhau)) {
                MsgBox.alert(this, "Sai mật khẩu");
            } else {
                Auth.user = nv;
                this.dispose();
                
            }
        }
    }
    
    public void init() {
        if (ckbXemPass.isSelected()) {
            txtMatKhau.setEchoChar((char) 0);
        } else {
            txtMatKhau.setEchoChar('*');
        }
    }
    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void panelDangNhapMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDangNhapMouseExited
        panelDangNhap.setBackground(new Color(0, 255, 0));
        btnDangNhap.setBackground(new Color(0, 255, 0));
    }//GEN-LAST:event_panelDangNhapMouseExited

    private void panelDangNhapMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDangNhapMousePressed

    }//GEN-LAST:event_panelDangNhapMousePressed

    private void btnDangNhapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangNhapMouseEntered
        panelDangNhap.setBackground(new Color(0, 200, 0));
        btnDangNhap.setBackground(new Color(0, 200, 0));
    }//GEN-LAST:event_btnDangNhapMouseEntered

    private void txtTaiKhoanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTaiKhoanFocusGained

    }//GEN-LAST:event_txtTaiKhoanFocusGained

    private void txtTaiKhoanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTaiKhoanFocusLost

    }//GEN-LAST:event_txtTaiKhoanFocusLost

    private void txtMatKhauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMatKhauFocusGained

    }//GEN-LAST:event_txtMatKhauFocusGained

    private void txtMatKhauFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMatKhauFocusLost

    }//GEN-LAST:event_txtMatKhauFocusLost

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        dangNhap();
    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void ckbXemPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbXemPassActionPerformed
        init();
    }//GEN-LAST:event_ckbXemPassActionPerformed

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
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DangNhap dialog = new DangNhap(new javax.swing.JFrame(), true);
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
    private com.quanlyquananvat.chucNangGiaoDien.Border_panel border_panel1;
    private com.quanlyquananvat.chucNangGiaoDien.Border_panel border_panel2;
    private javax.swing.JButton btnDangNhap;
    private com.quanlyquananvat.chucNangGiaoDien.JCheckBoxCustom ckbXemPass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private com.quanlyquananvat.chucNangGiaoDien.Border_panel panelDangNhap;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
