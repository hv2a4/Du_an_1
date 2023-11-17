/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlyquananvat.ThuVienTienIch;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class MsgBox {

    /*
    Hiển thị thông báo cho người dùng
    parent là cửa sổ chứa thông báo 
    message là thông báo
     */

    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Hệ thống quản lý đào tạo", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void error(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
    }

    public static void warning(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Cảnh báo", JOptionPane.WARNING_MESSAGE);
    }

    /*
    hiện thị thông báo yêu cầu xác nhận 
    parent là cửa số thông báo
    message là câu hỏi yes / no 
    return là kết quả nhận được true / false
     */
    public static boolean confim(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, "Hệ thống quản lý đào tạo", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    /*Thông báo nhập dữ liệu*/
    public static String prompt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message, "Hệ thống quản lý đào tạo", JOptionPane.INFORMATION_MESSAGE);
    }
}
