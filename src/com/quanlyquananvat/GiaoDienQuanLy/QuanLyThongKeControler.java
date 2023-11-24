package com.quanlyquananvat.GiaoDienQuanLy;

import com.quanlyquananvat.dao.ThongKeDao;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class QuanLyThongKeControler {

    ThongKeDao tkdao = new ThongKeDao();

    public void setDataToChar(JPanel jpnItem) {
        List<Object[]> list = tkdao.getTongDoanhThuLSP();
        if (list != null) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Object[] objects : list) {
                String tenLoaiSanPham = (String) objects[0];
                Object tongDoanhThuObject = objects[1];

                if (tongDoanhThuObject instanceof Number) {
                    Number tongDoanhThu = (Number) tongDoanhThuObject;
                    dataset.addValue(tongDoanhThu, "Danh thu loại sản phẩm", tenLoaiSanPham);
                } else if (tongDoanhThuObject instanceof String) {
                    try {
                        Double tongDoanhThu = Double.parseDouble((String) tongDoanhThuObject);
                        dataset.addValue(tongDoanhThu, "Danh thu loại sản phẩm", tenLoaiSanPham);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }

            JFreeChart chart = ChartFactory.createBarChart(
                    "THỐNG KÊ DOANH THU", "Tên loại sản phẩm", "Tổng danh thu", dataset);

            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            BarRenderer renderer = (BarRenderer) plot.getRenderer();

                       // Hiển thị giá trị trực tiếp trên đỉnh của cột
            renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            renderer.setBaseItemLabelsVisible(true);

            // Đặt vị trí của nhãn trực tiếp trên đỉnh của cột
            ItemLabelPosition positivePosition = new ItemLabelPosition(
                    ItemLabelAnchor.CENTER, TextAnchor.BOTTOM_CENTER, TextAnchor.BOTTOM_CENTER, 0.0
            );
            renderer.setPositiveItemLabelPositionFallback(positivePosition);

            ItemLabelPosition negativePosition = new ItemLabelPosition(
                    ItemLabelAnchor.CENTER, TextAnchor.TOP_CENTER, TextAnchor.TOP_CENTER, 0.0
            );
            renderer.setNegativeItemLabelPositionFallback(negativePosition);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));
            jpnItem.removeAll();
            jpnItem.setLayout(new CardLayout());
            jpnItem.add(chartPanel);
            jpnItem.validate();
            jpnItem.repaint();
        }
    }
}
