package com.quanlyquananvat.MainChayGiaoDien;

import com.quanlyquananvat.GiaoDienQuanLy.GiaoDich;
import com.quanlyquananvat.GiaoDienQuanLy.KhachHang;
import com.quanlyquananvat.GiaoDienQuanLy.NhanVien;
import com.quanlyquananvat.GiaoDienQuanLy.SanPham;
import com.quanlyquananvat.GiaoDienQuanLy.ThongKe;
import com.quanlyquananvat.GiaoDienQuanLy.TrangChu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;

public class ChuyenManHinhController {

    public List<DanhSachQuanLy> listItem = null;
    private JPanel jpnRoot;
    private String kindSelection = "";

    public ChuyenManHinhController(JPanel jpnRoot) {
        this.jpnRoot = jpnRoot;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelection = "Trang Chu";
        jpnItem.setBackground(new Color(0, 179, 60));
        jlbItem.setBackground(new Color(0, 179, 60));

        jpnRoot.removeAll();
        jpnRoot.setLayout(new BorderLayout());
        jpnRoot.add(new TrangChu());
        jpnRoot.validate();
        jpnRoot.repaint();
    }

    public void setEvent(List<DanhSachQuanLy> listItem) {
        this.listItem = listItem;
        for (DanhSachQuanLy item : listItem) {
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node; // nơi lưu trữ các pannel
        private String kind; // phân loại chức năng của nút
        private JPanel jpnItem;
        private JLabel lblItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel lblItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.lblItem = lblItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            switch (kind) {
                case "Trang Chu":
                    node = new TrangChu();
                    break;
                case "khach hang":
                    node = new KhachHang();
                    break;
                case "nhan vien":
                    node = new NhanVien();
                    break;
                case "san pham":
                    node = new SanPham();
                    break;
                case "giao dich":
                    node = new GiaoDich();
                    break;
                case "ThongKe":
                    node = new ThongKe();
                    break;

                default:
                    throw new AssertionError();
            }
            jpnRoot.removeAll();
            jpnRoot.setLayout(new BorderLayout());
            jpnRoot.add(node);
            jpnRoot.validate();
            jpnRoot.repaint();
            setChangeBackGroup(kind);

        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelection = kind;
            jpnItem.setBackground(new Color(0, 179, 60));
            lblItem.setBackground(new Color(0, 179, 60));

        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(0, 179, 60));
            lblItem.setBackground(new Color(0, 179, 60));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelection.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(67,110,238));
                lblItem.setBackground(new Color(67,110,238));
            }
        }

        public void setChangeBackGroup(String kind) {
            for (DanhSachQuanLy item : listItem) {
                if (item.getKind().equalsIgnoreCase(kind)) {
                    item.getJpn().setBackground(new Color(0, 179, 60));
                    item.getJlb().setBackground(new Color(0, 179, 60));
                } else {
                    item.getJpn().setBackground(new Color(67,110,238));
                    item.getJlb().setBackground(new Color(67,110,238));
                }
            }
        }
    }
}
