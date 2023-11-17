package com.quanlyquananvat.ThuVienTienIch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Lớp XDate cung cấp các phương thức tiện ích cho việc làm việc với thời gian
 * (Date).
 */
public class XDate {

    private static SimpleDateFormat formater = new SimpleDateFormat();

    /**
     * Chuyển đổi một chuỗi thành đối tượng Date dựa trên một mẫu định dạng cụ
     * thể.
     *
     * @param dateString Chuỗi thời gian cần chuyển đổi.
     * @param pattern Mẫu định dạng thời gian (ví dụ: "dd-MM-yyyy").
     * @return Đối tượng Date tương ứng hoặc null nếu không thể chuyển đổi.
     */
    public static Date toDate(String dateString, String pattern) {
        try {
            formater.applyPattern(pattern);
            return formater.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Chuyển đổi đối tượng Date thành một chuỗi thời gian dựa trên một mẫu định
     * dạng cụ thể.
     *
     * @param date Đối tượng Date cần chuyển đổi.
     * @param pattern Mẫu định dạng thời gian (ví dụ: "dd-MM-yyyy").
     * @return Chuỗi thời gian tương ứng.
     */
    public static String toString(Date date, String pattern) {
        formater.applyPattern(pattern);
        return formater.format(date);
    }

    /**
     * Trả về thời gian hiện tại.
     *
     * @return Đối tượng Date biểu diễn thời gian hiện tại.
     */
    public static Date now() {
        return new Date();
    }

    /**
     * Trừ đi một số ngày từ một đối tượng Date hiện có.
     *
     * @param date Đối tượng Date hiện có.
     * @param days Số ngày cần trừ.
     * @return Đối tượng Date sau khi trừ đi số ngày.
     */
    public static Date subtractDay(Date date, long days) {
        date.setTime(date.getTime() - days * 24 * 60 * 60 * 1000);
        return date;
    }

    /**
     * Bổ sung một số ngày vào một đối tượng Date hiện có.
     *
     * @param date Đối tượng Date hiện có.
     * @param days Số ngày cần bổ sung.
     * @return Đối tượng Date sau khi bổ sung số ngày.
     */
    public static Date addDay(Date date, long days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static String formatJDateChooserDate(Date date) {
        return dateFormat.format(date);
    }

    public static Date parseJDateChooserDate(String dateString) {
        try {
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
