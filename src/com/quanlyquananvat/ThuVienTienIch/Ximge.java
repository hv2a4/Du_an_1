/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlyquananvat.ThuVienTienIch;

import com.quanlyquananvat.Object.NhanVienObject;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Ximge {

    public static Image getAppIcon() {
        File f = new File("src\\Logo\\image 82.png");
        return new ImageIcon(f.getAbsolutePath()).getImage();
    }

    public static void save(File src) {
        File file = new File("Logo", src.getName());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(file.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static ImageIcon read(String fileName, int width, int height) {
        File path = new File("Logo", fileName);
        ImageIcon originalIcon = new ImageIcon(path.getAbsolutePath());
        Image img = originalIcon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

}
