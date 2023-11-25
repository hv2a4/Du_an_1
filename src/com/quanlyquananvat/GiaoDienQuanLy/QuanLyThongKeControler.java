package com.quanlyquananvat.GiaoDienQuanLy;

import com.quanlyquananvat.dao.ThongKeDao;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
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

    public void hienThiBieuDoHinhTron(JPanel jpnItem, JComboBox comboBox) {
        Integer nam = (Integer) comboBox.getSelectedItem();

        List<Object[]> pieChartData = tkdao.getTongDoanhThu(nam);
        DefaultPieDataset dataset = createDataset(pieChartData);

        JFreeChart chart = ChartFactory.createPieChart(
                "THỐNG KÊ DOANH THU", // Tiêu đề của biểu đồ
                dataset, // Dữ liệu cho biểu đồ
                true, // Hiển thị chú thích
                true, // Hỗ trợ chút dựa vào vị trí chuột
                false // Không hiển thị URL
        );

        // Tùy chỉnh biểu đồ hình tròn (Pie Chart)
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setNoDataMessage("No data available");
        plot.setCircular(true);

        // Hiển thị giá trị phần trăm
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {2} ({1})"));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));
        setChartPanel(jpnItem, chartPanel);
    }

    private DefaultPieDataset createDataset(List<Object[]> pieChartData) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (Object[] data : pieChartData) {
            String label = (String) data[0];
            int value = (Integer) data[1];
            dataset.setValue(label, value);
        }

        return dataset;
    }

    private void setChartPanel(JPanel jpnItem, ChartPanel chartPanel) {
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
}
