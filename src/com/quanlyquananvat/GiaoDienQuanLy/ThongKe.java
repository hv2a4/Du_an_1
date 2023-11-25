package com.quanlyquananvat.GiaoDienQuanLy;

import com.formdev.flatlaf.util.SwingUtils;
import com.quanlyquananvat.Object.HoaDonObject;
import com.quanlyquananvat.Object.LoaiSanPhamObject;
import com.quanlyquananvat.ThuVienTienIch.MsgBox;
import com.quanlyquananvat.ThuVienTienIch.NumberFormatter;
import com.quanlyquananvat.dao.HoaDonDAO;
import com.quanlyquananvat.dao.LoaiSanPhamDAO;
import com.quanlyquananvat.dao.ThongKeDao;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class ThongKe extends javax.swing.JPanel {

    HoaDonDAO hddao = new HoaDonDAO();
    ThongKeDao tkdao = new ThongKeDao();

    public ThongKe() {
        initComponents();
        init();
    }

    public void init() {
        tblDoanhThu.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        tblDoanhThu.getTableHeader().setOpaque(false);
        tblDoanhThu.getTableHeader().setBackground(new Color(0, 102, 255));
        tblDoanhThu.getTableHeader().setForeground(new Color(0, 0, 0));
        tblDoanhThu.setRowHeight(25);
        fillComBoBoxDoanhThu();
    }

    public void fillComBoBoxDoanhThu() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboDoanhThu.getModel();
        model.removeAllElements();
        try {
            List<Integer> list = hddao.getYear();
            for (Integer integer : list) {
                model.addElement(integer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void getTongDoanhThu() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setRowCount(0);
        Object nam = cboDoanhThu.getSelectedItem();
        try {
            if (nam != null) {
                int layNam = Integer.parseInt(nam.toString());
                List<Object[]> list = tkdao.getTongDoanhThu(layNam);
                for (Object[] objects : list) {
                    model.addRow(new Object[]{
                        objects[0],
                        objects[1],
                        objects[2],
                        objects[3],
                        objects[4],
                        NumberFormatter.format((double) objects[5]),});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.warning(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        shadowRenderer1 = new swing.shadow.ShadowRenderer();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tabbedPaneCustom1 = new com.quanlyquananvat.chucNangGiaoDien.TabbedPaneCustom();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cboDoanhThu = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();

        jMenuItem1.setText("jMenuItem1");

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 71, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thống kê");

        tabbedPaneCustom1.setBackground(new java.awt.Color(242, 242, 242));
        tabbedPaneCustom1.setSelectedColor(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(242, 242, 242));

        tblDoanhThu.setBackground(new java.awt.Color(242, 242, 242));
        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số lượng", "Doanh thu", "DT.Cao nhất", "DT.Thấp nhất", "DT.TB"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoanhThu.setRowHeight(30);
        tblDoanhThu.setShowGrid(false);
        tblDoanhThu.getTableHeader().setReorderingAllowed(false);
        tblDoanhThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoanhThuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDoanhThu);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Năm");

        cboDoanhThu.setBackground(new java.awt.Color(255, 255, 255));
        cboDoanhThu.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        cboDoanhThu.setForeground(new java.awt.Color(0, 0, 0));
        cboDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDoanhThuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(cboDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cboDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        tabbedPaneCustom1.addTab("Doanh thu", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 927, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        tabbedPaneCustom1.addTab("tab2", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDoanhThuActionPerformed
        getTongDoanhThu();
    }//GEN-LAST:event_cboDoanhThuActionPerformed

    private void tblDoanhThuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoanhThuMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            JPopupMenu pop = new JPopupMenu();
            JMenuItem item = new JMenuItem("Hiệu thị dạng biểu đồ");
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BieuDoTongDoanhThu s = new BieuDoTongDoanhThu((JFrame)SwingUtilities.getWindowAncestor(ThongKe.this),true);
                    s.setVisible(true);
                }
            });
            pop.add(item);
            pop.show(tblDoanhThu, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblDoanhThuMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboDoanhThu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private swing.shadow.ShadowRenderer shadowRenderer1;
    private com.quanlyquananvat.chucNangGiaoDien.TabbedPaneCustom tabbedPaneCustom1;
    private javax.swing.JTable tblDoanhThu;
    // End of variables declaration//GEN-END:variables
}
