/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlyquananvat.ThuVienTienIch;

import com.quanlyquananvat.Object.NhanVienObject;

/**
 *
 * @author pc
 */
public class Auth {

    // đối tượng chứa thông tin người sử dụng sau khi đăng nhập
    public static NhanVienObject user = null;

    //xóa thông tin của người dùng sao khi có yêu cầu đăng nhập
    public static void clear() {
        Auth.user = null;
    }

    //kiểm tra xem đăng nhập hay chưa
    //return đăng nhập hay chưa 
    public static boolean isLogin() {
        return Auth.user != null;
    }

    // Kiểm tra vai trò của bạn có phải là nhân viên hay quản lý
    public static boolean isManager() {
        return Auth.isLogin() && user.isVaiTro();
    }
    
}
