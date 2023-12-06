
import com.quanlyquananvat.dao.ThongKeDao;
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

public class LineChartPanel extends JPanel {

    private ThongKeDao tkdao;

    public LineChartPanel() {
        tkdao = new ThongKeDao();
        setLayout(new CardLayout());
    }

    public void displayChart(JPanel jpnItem) {
        try {
            List<Object[]> list = tkdao.getThongKeHoaDon();
            if (list != null) {
                XYSeries series = new XYSeries("Số Lượng Hóa Đơn");

                for (Object[] objects : list) {
                    Date ngayTao = (Date) objects[0];
                    int soLuongHoaDon = (int) objects[1];

                    series.add(ngayTao.getTime(), soLuongHoaDon);
                }

                XYSeriesCollection dataset = new XYSeriesCollection(series);

                JFreeChart chart = ChartFactory.createTimeSeriesChart(
                        "SỐ LƯỢNG HÓA ĐƠN THEO NGÀY", "Ngày", "Số Lượng Hóa Đơn", dataset, false, true, false);

                XYPlot plot = (XYPlot) chart.getPlot();
                DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
                dateAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
                dateAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 1));

                XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
                plot.setRenderer(renderer);

                ChartPanel chartPanel = new ChartPanel(chart);
                chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

                removeAll();
                add(chartPanel);
                validate();
                repaint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Line Chart Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            LineChartPanel lineChartPanel = new LineChartPanel();
            lineChartPanel.displayChart(lineChartPanel);

            frame.getContentPane().add(lineChartPanel);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
