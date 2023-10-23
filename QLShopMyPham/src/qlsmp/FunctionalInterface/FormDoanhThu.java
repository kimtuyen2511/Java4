package qlsmp.FunctionalInterface;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import qlsmp.DAO.HoaDonDAO;
import qlsmp.DAO.PhieuNhapDAO;
import qlsmp.Model.HoaDon;
import qlsmp.Model.PhieuNhap;

/**
 *
 * @author My Laptop
 */
public class FormDoanhThu extends javax.swing.JFrame {

    HoaDonDAO HDdao = new HoaDonDAO();
    List<HoaDon> listHd = new ArrayList<>();
    String[] columsBH = {"Mã hóa đơn", "Ngày tạo", "Người tạo", "Tổng tiền"};
    DefaultTableModel modelBH = new DefaultTableModel(columsBH, 0);

    PhieuNhapDAO PNdao = new PhieuNhapDAO();
    List<PhieuNhap> listPN = new ArrayList<>();
    String[] columsNH = {"Mã Phiếu nhập", "Ngày nhận hàng", "Nhân viên nhận hàng", "Tổng tiền", "Nhân viên nhập"};
    DefaultTableModel modelNH = new DefaultTableModel(columsNH, 0);

    public FormDoanhThu() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        cbxBH = new javax.swing.JComboBox<>();
        btnLocBH = new javax.swing.JButton();
        txtYeuCauLocBH = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDonBan = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonNhap = new javax.swing.JTable();
        txtYeuCauLocNH = new javax.swing.JTextField();
        cboNhapHang = new javax.swing.JComboBox<>();
        btnLocNH = new javax.swing.JButton();
        pnelDoanhThu = new javax.swing.JPanel();
        btnLocChart = new javax.swing.JButton();
        cbxChart1 = new javax.swing.JComboBox<>();
        cbxChart2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tabs.setBackground(new java.awt.Color(255, 255, 255));
        tabs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabsMouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        cbxBH.setBackground(new java.awt.Color(204, 255, 204));
        cbxBH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng", "Năm" }));

        btnLocBH.setText("Lọc");
        btnLocBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocBHActionPerformed(evt);
            }
        });

        txtYeuCauLocBH.setText("Nhập ngày tháng năm");

        tblHoaDonBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Ngày tạo", "Người tạo", "Tổng tiền"
            }
        ));
        jScrollPane1.setViewportView(tblHoaDonBan);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(cbxBH, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtYeuCauLocBH, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLocBH, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtYeuCauLocBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnLocBH, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        tabs.addTab("Hóa đơn bán hàng", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tblHoaDonNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu nhập", "Ngày nhận hàng", "Nhân viên nhận đơn hàng", "Tổng tiền", "Nhân viên nhập"
            }
        ));
        jScrollPane2.setViewportView(tblHoaDonNhap);

        txtYeuCauLocNH.setText("Nhập ngày tháng năm");

        cboNhapHang.setBackground(new java.awt.Color(204, 255, 204));
        cboNhapHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng", "Năm" }));

        btnLocNH.setText("Lọc");
        btnLocNH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocNHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(cboNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtYeuCauLocNH, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnLocNH, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(298, 298, 298)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLocNH)
                    .addComponent(txtYeuCauLocNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabs.addTab("Hóa đơn nhập hàng", jPanel4);

        pnelDoanhThu.setOpaque(false);
        pnelDoanhThu.setLayout(new java.awt.BorderLayout());

        btnLocChart.setText("Lọc");
        btnLocChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocChartActionPerformed(evt);
            }
        });

        cbxChart1.setBackground(new java.awt.Color(204, 255, 204));
        cbxChart1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quý", "Năm" }));
        cbxChart1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxChart1MouseClicked(evt);
            }
        });
        cbxChart1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxChart1ActionPerformed(evt);
            }
        });

        cbxChart2.setBackground(new java.awt.Color(204, 255, 204));
        cbxChart2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxChart2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnelDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(cbxChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(cbxChart2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLocChart, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(298, 298, 298)))
                .addContainerGap())
            .addComponent(tabs, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnelDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLocChart)
                    .addComponent(cbxChart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxChart2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setPnelDoanhThu(new BarChartNam());
    }//GEN-LAST:event_formWindowOpened

    private void cbxChart1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxChart1ActionPerformed
        checkFillCbx();
    }//GEN-LAST:event_cbxChart1ActionPerformed

    private void cbxChart1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxChart1MouseClicked
        checkFillCbx();
    }//GEN-LAST:event_cbxChart1MouseClicked

    private void cbxChart2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxChart2ActionPerformed

    }//GEN-LAST:event_cbxChart2ActionPerformed

    private void btnLocChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocChartActionPerformed
        if (cbxChart2.getSelectedItem().equals("Quý 1")) {
            setPnelDoanhThu(new BarChartQui1());
        } else if (cbxChart2.getSelectedItem().equals("Quý 2")) {
            setPnelDoanhThu(new BarChartQui2());
        } else if (cbxChart2.getSelectedItem().equals("Quý 3")) {
            setPnelDoanhThu(new BarChartQui3());
        } else if (cbxChart2.getSelectedItem().equals("Quý 4")) {
            setPnelDoanhThu(new BarChartQui4());
        } else if (cbxChart2.getSelectedItem().equals("2022")) {
            setPnelDoanhThu(new BarChartNam());
        }
    }//GEN-LAST:event_btnLocChartActionPerformed

    private void tabsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabsMouseClicked
        fillHoaDonNhap(listPN = PNdao.selectAll());
        fillHoaDonBan(listHd = HDdao.selectAll());
    }//GEN-LAST:event_tabsMouseClicked

    private void btnLocNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocNHActionPerformed
        locHoaDonNhapHang();
    }//GEN-LAST:event_btnLocNHActionPerformed

    private void btnLocBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocBHActionPerformed
        locHoaDonBanHang();
    }//GEN-LAST:event_btnLocBHActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormDoanhThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDoanhThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDoanhThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDoanhThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDoanhThu().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLocBH;
    private javax.swing.JButton btnLocChart;
    private javax.swing.JButton btnLocNH;
    private javax.swing.JComboBox<String> cboNhapHang;
    private javax.swing.JComboBox<String> cbxBH;
    private javax.swing.JComboBox<String> cbxChart1;
    private javax.swing.JComboBox<String> cbxChart2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnelDoanhThu;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblHoaDonBan;
    private javax.swing.JTable tblHoaDonNhap;
    private javax.swing.JTextField txtYeuCauLocBH;
    private javax.swing.JTextField txtYeuCauLocNH;
    // End of variables declaration//GEN-END:variables

    void init() {
        setLocationRelativeTo(null);
        setTitle("Trang Doanh Thu");
        this.getContentPane().setBackground(Color.WHITE);
        fillHoaDonNhap(listPN = PNdao.selectAll());
        fillHoaDonBan(listHd = HDdao.selectAll());
        txtYeuCauLocBH.setText("");
        txtYeuCauLocNH.setText("");
        checkFillCbx();
        
//        setColumnWidths(50,150,150,800);
    }

    public void setColumnWidths(int... widths) {
        TableColumnModel columnModel = tblHoaDonBan.getColumnModel();
        for (int i = 0; i < widths.length; i++) {
            if (i < columnModel.getColumnCount()) {
                columnModel.getColumn(i).setMaxWidth(widths[i]);
            } else {
                break;
            }
        }
    }

    public void setPnelDoanhThu(JComponent com) {
        pnelDoanhThu.removeAll();
        pnelDoanhThu.add(com);
        pnelDoanhThu.repaint();
        pnelDoanhThu.revalidate();
    }

    void fillHoaDonBan(List<HoaDon> list) {
        modelBH.setRowCount(0);
        tblHoaDonBan.setModel(modelBH);
        for (HoaDon hd : list) {
            modelBH.addRow(new Object[]{hd.getMaHD(), hd.getNgayTao(), hd.getNguoiTao(), hd.getTongTien()});
            tblHoaDonBan.setModel(modelBH);
        }
    }

    void fillHoaDonNhap(List<PhieuNhap> list) {
        modelNH.setRowCount(0);
        tblHoaDonNhap.setModel(modelNH);
        for (PhieuNhap pn : list) {
            modelNH.addRow(new Object[]{pn.getMaPN(), pn.getNgayTao(), pn.getNgTao(), pn.getTongTien(), pn.getMaAccount()});
            tblHoaDonNhap.setModel(modelNH);
        }
    }

    boolean checkdate(String date) {
        if (date.isBlank()) {
            JOptionPane.showMessageDialog(this, "Nhập vào năm-tháng-ngày cần tìm");
            return false;
        }
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.parse(date);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

//    void LocBanHangTheoNgay() {
//        if (checkdate(txtYeuCauLocBH.getText())) {
//            listHd = HDdao.selectNgayNhap(txtYeuCauLocBH.getText());
//            fillHoaDonBan(listHd);
//        }
//    }
//
//    void LocNhapHangTheoNgay() {
//        if (checkdate(txtYeuCauLocNH.getText())) {
//            listPN = PNdao.selectNgayNhap(txtYeuCauLocNH.getText());
//            fillHoaDonNhap(listPN);
//        }
//    }
//    void LocBanHangTheoThang() {
//    }
//
//    void LocNhapHangTheoThang() {
//
//    }
    //Biểu đồ
    void checkFillCbx() {
        cbxChart2.removeAllItems();
        if (cbxChart1.getSelectedIndex() == 0) {
            String[] item = {"Quý 1", "Quý 2", "Quý 3", "Quý 4"};
            for (String string : item) {
                cbxChart2.addItem(string);
            }
        } else {
            String[] item = {"2022"};
            for (String string : item) {
                cbxChart2.addItem(string);
            }
        }
    }

    void LocChart() {
    }

    private void locHoaDonBanHang() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDonBan.getModel();
        model.setRowCount(0);
        String keyword = txtYeuCauLocBH.getText();
        String locHoaDon = cbxBH.getSelectedItem().toString();

        if (!keyword.isBlank()) {
            if (locHoaDon.equals("Tháng")) {
                List<Object[]> list = HDdao.getHoaDonBHTheoThang(keyword);
                for (Object[] row : list) {
                    double tongTien = (double) row[3];
                    model.addRow(new Object[]{row[0], row[1], row[2], tongTien});
                }
            } else if (locHoaDon.equalsIgnoreCase("Năm")) {
                List<Object[]> list = HDdao.getHoaDonBHTheoNam(keyword);

                for (Object[] row : list) {
                    double tongTien = (double) row[3];

                    model.addRow(new Object[]{row[0], row[1], row[2], tongTien});
                }
            }
        } else {
            fillHoaDonBan(listHd);
        }
    }

    private void locHoaDonNhapHang() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDonNhap.getModel();
        model.setRowCount(0);
        String keyword = txtYeuCauLocNH.getText();
        String locHoaDon = cboNhapHang.getSelectedItem().toString();

        if (!keyword.isBlank()) {
            if (locHoaDon.equalsIgnoreCase("Tháng")) {
                List<Object[]> list = PNdao.getHoaDonNHTheoThang(keyword);

                for (Object[] row : list) {
                    model.addRow(new Object[]{row[0], row[1], row[2], row[3], row[4]});
                }
            } else if (locHoaDon.equalsIgnoreCase("Năm")) {
                List<Object[]> list = PNdao.getHoaDonNHTheoNam(keyword);

                for (Object[] row : list) {
                    model.addRow(new Object[]{row[0], row[1], row[2], row[3], row[4]});
                }
            }
        } else {
            fillHoaDonNhap(listPN);
        }
    }

    public void selecTab(int index) {
        tabs.setSelectedIndex(index);
    }
}
