package com.quanlyquananvat.GiaoDienQuanLy;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.data.xy.XYDataset;

public class LineChart {
//
//    public static void displayChart(JPanel jpnItem, List<Object[]> data) {
//        SwingUtilities.invokeLater(() -> {
//            try {
//                XYSeries series = new XYSeries("Số Lượng Hóa Đơn");
//
//                for (Object[] objects : data) {
//                    Date ngayTao = (Date) objects[0];
//                    int soLuongHoaDon = (int) objects[1];
//
//                    series.add(ngayTao.getTime(), soLuongHoaDon);
//                }
//
//                XYSeriesCollection dataset = new XYSeriesCollection(series);
//
//                JFreeChart chart = ChartFactory.createTimeSeriesChart(
//                        "SỐ LƯỢNG HÓA ĐƠN THEO NGÀY", "Ngày", "Số Lượng Hóa Đơn", dataset, false, true, false);
//
//                XYPlot plot = (XYPlot) chart.getPlot();
//                DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
//                dateAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
//                dateAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 1));
//
//                XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
//                plot.setRenderer(renderer);
//
//                ChartPanel chartPanel = new ChartPanel(chart);
//                chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));
//
//                setChartPanel(jpnItem, chartPanel);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    private static void setChartPanel(JPanel jpnItem, ChartPanel chartPanel) {
//        jpnItem.removeAll();
//        jpnItem.setLayout(new CardLayout());
//        jpnItem.add(chartPanel);
//        jpnItem.validate();
//        jpnItem.repaint();
//    }

    public static void displayChart(JPanel jpnItem, List<Object[]> data) {
        SwingUtilities.invokeLater(() -> {
            try {
                XYSeries series = new XYSeries("Số Lượng Hóa Đơn");
                XYSeries totalRevenueSeries = new XYSeries("Tổng Doanh Thu");

                for (Object[] objects : data) {
                    Date ngayTao = (Date) objects[0];
                    int soLuongHoaDon = (int) objects[1];
                    double tongDoanhThu = (double) objects[2]; // Chỉnh kiểu dữ liệu tùy vào dữ liệu thực tế

                    series.add(ngayTao.getTime(), soLuongHoaDon);
                    totalRevenueSeries.add(ngayTao.getTime(), tongDoanhThu);
                }

                XYSeriesCollection dataset = new XYSeriesCollection();
                dataset.addSeries(series);
                dataset.addSeries(totalRevenueSeries);

                JFreeChart chart = ChartFactory.createTimeSeriesChart(
                        "SỐ LƯỢNG HÓA ĐƠN VÀ TỔNG DOANH THU THEO NGÀY", "Ngày", "Số Lượng Hóa Đơn và Tổng Doanh Thu", dataset, false, true, false);

                XYPlot plot = (XYPlot) chart.getPlot();
                DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
                dateAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
                dateAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 1));

                XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
                plot.setRenderer(renderer);

                // Hiển thị giá trị tại mỗi điểm của biểu đồ
                renderer.setBaseItemLabelsVisible(true);
                renderer.setBaseItemLabelGenerator(new XYItemLabelGenerator() {
                    @Override
                    public String generateLabel(XYDataset dataset, int series, int item) {
                        Number y = dataset.getY(series, item);
                        return y.toString();
                    }
                });

                ChartPanel chartPanel = new ChartPanel(chart);
                chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

                setChartPanel(jpnItem, chartPanel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void setChartPanel(JPanel jpnItem, ChartPanel chartPanel) {
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
}
