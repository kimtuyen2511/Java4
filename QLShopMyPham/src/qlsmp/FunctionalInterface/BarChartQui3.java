package qlsmp.FunctionalInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import qlsmp.DAO.DoanhThuDAO;
import qlsmp.Model.DoanhThu;

/**
 *
 * @author My Laptop
 */
public class BarChartQui3 extends javax.swing.JPanel {

    private DefaultCategoryDataset dataset;
    private JFreeChart chart;
    private CategoryPlot categoryPlot;
    private ChartPanel chartPanel;

    public BarChartQui3() {
        initComponents();
        ShowBarChart();
    }

    void insertDoanhThu() {
        List<DoanhThu> list = new ArrayList<DoanhThu>();
        DoanhThuDAO DTdao = new DoanhThuDAO();
        list = DTdao.selectQUI3();
        for (DoanhThu doanhThu : list) {
            switch (doanhThu.getThang()) {
                case 1:
                    dataset.addValue(doanhThu.getDoanhThu() / 1000000, "Doanh Thu", "Tháng 7");
                    break;
                case 2:
                    dataset.addValue(doanhThu.getDoanhThu() / 1000000, "Doanh Thu", "Tháng 8");
                    break;
                case 3:
                    dataset.addValue(doanhThu.getDoanhThu() / 1000000, "Doanh Thu", "Tháng 9");
                    break;
            }
        }

    }

    void insertChiPhi() {
        List<DoanhThu> list = new ArrayList<DoanhThu>();
        DoanhThuDAO DTdao = new DoanhThuDAO();
        list = DTdao.selectQUI3();
        for (DoanhThu doanhThu : list) {
            switch (doanhThu.getThang()) {
                case 1:
                    dataset.addValue(doanhThu.getChiPhi() / 1000000, "Chi phí", "Tháng 7");
                    break;
                case 2:
                    dataset.addValue(doanhThu.getChiPhi() / 1000000, "Chi phí", "Tháng 8");
                    break;
                case 3:
                    dataset.addValue(doanhThu.getChiPhi() / 1000000, "Chi phí", "Tháng 9");
                    break;
            }
        }

    }

    void insertLoiNhuan() {
        List<DoanhThu> list = new ArrayList<DoanhThu>();
        DoanhThuDAO DTdao = new DoanhThuDAO();
        list = DTdao.selectQUI3();
        for (DoanhThu doanhThu : list) {
            switch (doanhThu.getThang()) {
                case 1:
                    dataset.addValue(doanhThu.getLoiNhuan() / 1000000, "Lợi nhuận", "Tháng 7");
                    break;
                case 2:
                    dataset.addValue(doanhThu.getLoiNhuan() / 1000000, "Lợi nhuận", "Tháng 8");
                    break;
                case 3:
                    dataset.addValue(doanhThu.getLoiNhuan() / 1000000, "Lợi nhuận", "Tháng 9");
                    break;
            }
        }

    }

    public void ShowBarChart() {
        dataset = new DefaultCategoryDataset();
        insertDoanhThu();
        insertChiPhi();
        insertLoiNhuan();
        // creat chart
        chart = ChartFactory.createBarChart3D("Biểu đồ doanh thu Shop Mỹ Phẩm QUÝ 1", "", "triệu VND", dataset, PlotOrientation.VERTICAL, true, true, false);

        //categoryPlot = chart.getCategoryPlot();
        //
        //categoryPlot.setBackgroundPaint(Color.black);
        chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);

        // jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.setPreferredSize(new Dimension(1094, 449));
        jPanel1.add(chartPanel, BorderLayout.CENTER);
        repaint();
        //jPanel1.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1106, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 461, Short.MAX_VALUE)
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
