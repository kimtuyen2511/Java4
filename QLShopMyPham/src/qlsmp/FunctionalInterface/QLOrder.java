//HOÀN THÀNH
package qlsmp.FunctionalInterface;

import java.awt.Color;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import qldaotao.utis.Auth;
import qldaotao.utis.XImage;
import qlsmp.DAO.AccountDAO;
import qlsmp.DAO.ChiTietDonHangDAO;
import qlsmp.DAO.DonHangDAO;
import qlsmp.DAO.KhachHangDAO;
import qlsmp.DAO.KhoDAO;
import qlsmp.Model.Account;
import qlsmp.Model.ChiTietDonHang;
import qlsmp.Model.DonHang;
import qlsmp.Model.KhachHang;
import qlsmp.Model.SanPham;

/**
 *
 * @author My Laptop
 */
public class QLOrder extends javax.swing.JDialog implements Runnable {

    List<SanPham> list = new ArrayList<SanPham>();
    KhoDAO KHOdao = new KhoDAO();
    AccountDAO Accdao = new AccountDAO();
    int page = 1;
    String[] colums = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá"};
    DefaultTableModel model = new DefaultTableModel(colums, 0);

    public QLOrder(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();

    }

    void init() {
        this.setIconImage(XImage.getApplcon());
        setLocationRelativeTo(null);
        setTitle("Trang Order sản phẩm");
        this.getContentPane().setBackground(Color.WHITE);

        this.FillPageSP();
        model.setRowCount(0);
        tblList.setModel(model);
        this.clearForm();
    }

    public void SetNgayTao() {
        try {
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            lblNgayTao.setText(dateFormat.format(now));
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Date now = new Date();
                SimpleDateFormat fomater = new SimpleDateFormat("hh:mm:ss");
                lblDongHo.setText(fomater.format(now));
                Thread.sleep(1000);
            } catch (Exception e) {
                break;
            }
        }
    }
// xét sp lên khung

    public void setHinhAnh(String path, JLabel hinh) {
        if (path != null) {
            ImageIcon icon = XImage.read(path);
            Image image = icon.getImage();
            Image image2 = image.getScaledInstance(111, 93, Image.SCALE_SMOOTH);
            hinh.setToolTipText(path);
            hinh.setIcon(new ImageIcon(image2));
        } else {
            hinh.setIcon(null);
        }
    }

    public void FillSP(SanPham sp, JPanel pnel, JLabel hinh, JLabel ten, JLabel gia, JLabel SlKho, JSpinner sner) {
        pnel.setVisible(true);
        setHinhAnh(sp.getHinh(), hinh);
        ten.setText(sp.getTensp());
        gia.setText(sp.getGia() + "");
        this.upstatic(sp.getSoLuong(), sner, SlKho);
    }

    public void FillPageSP() {
        int vitri = 0;
        list = KHOdao.selectAll();
        for (SanPham sp : list) {
            //System.out.println(vitri);
            vitri++;
            if (vitri == this.page * 8 - 7) {
                FillSP(sp, pnel1, hinh1, ten1, gia1, SlKho1, SnerSl1);
            } else if (vitri == this.page * 8 - 6) {
                FillSP(sp, pnel2, hinh2, ten2, gia2, SlKho2, SnerSl2);
            } else if (vitri == this.page * 8 - 5) {
                FillSP(sp, pnel3, hinh3, ten3, gia3, SlKho3, SnerSl3);
            } else if (vitri == this.page * 8 - 4) {
                FillSP(sp, pnel4, hinh4, ten4, gia4, SlKho4, SnerSl4);
            } else if (vitri == this.page * 8 - 3) {
                FillSP(sp, pnel5, hinh5, ten5, gia5, SlKho5, SnerSl5);
            } else if (vitri == this.page * 8 - 2) {
                FillSP(sp, pnel6, hinh6, ten6, gia6, SlKho6, SnerSl6);
            } else if (vitri == this.page * 8 - 1) {
                FillSP(sp, pnel7, hinh7, ten7, gia7, SlKho7, SnerSl7);
            } else if (vitri == this.page * 8) {
                FillSP(sp, pnel8, hinh8, ten8, gia8, SlKho8, SnerSl8);
            }
        }
    }

    public void clearPageDS(JPanel pnel1, JPanel pnel2, JPanel pnel3, JPanel pnel4, JPanel pnel5, JPanel pnel6, JPanel pnel7, JPanel pnel8) {
        pnel1.setVisible(false);
        pnel2.setVisible(false);
        pnel3.setVisible(false);
        pnel4.setVisible(false);
        pnel5.setVisible(false);
        pnel6.setVisible(false);
        pnel7.setVisible(false);
        pnel8.setVisible(false);
    }

    public void search(String tensp) {
        this.clearPageDS(pnel1, pnel2, pnel3, pnel4, pnel5, pnel6, pnel7, pnel8);
        SanPham sp = KHOdao.selectByKeyword(tensp);
        FillSP(sp, pnel1, hinh1, ten1, gia1, SlKho1, SnerSl1);
    }

    private void upstatic(int slKho, JSpinner sner, JLabel lblkho) {
        // đặt giới hạn cho cái jspinner
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, slKho, 1);
        sner.setModel(spinnerModel);
        ((JSpinner.DefaultEditor) sner.getEditor()).getTextField().setEditable(false);
        // check hiển thị số lượng sp
        if (slKho < 10 && slKho > 0) {
            lblkho.setForeground(Color.red);
        } else if (slKho == 0) {
            lblkho.setForeground(Color.red);
            //sner.setEnabled(false);
        } else {
            lblkho.setForeground(Color.black);
        }
        lblkho.setText(slKho + "");
    }
// xét sản phẩm lên table

    private boolean checkExitSP(int ma) {
        for (int row = 0; row < tblList.getRowCount(); row++) {
            int maSP = (Integer) tblList.getValueAt(row, 0);
            if (ma == maSP) {
                return true;
            }
        }
        return false;
    }

    private void AddToTable(SanPham sp, JSpinner sner, JLabel slKho) {
        if ((int) sner.getValue() != 0 && checkExitSP(sp.getMaSP()) == false) {
            model.addRow(new Object[]{sp.getMaSP(), sp.getTensp(), (int) sner.getValue(), sp.getGia()});
            tblList.setModel(model);

            int slnew = sp.getSoLuong() - (int) sner.getValue();
            sp.setSoLuong(slnew);

            KHOdao.update(sp);
            this.upstatic(sp.getSoLuong(), sner, slKho);
            this.setForm();
        }
    }

    private void deleteRow() {
        int row = tblList.getSelectedRow();
        int sl = (int) tblList.getValueAt(row, 2);
        int maSP = (int) tblList.getValueAt(row, 0);

        SanPham sp = KHOdao.selecteByID(maSP);
        int slnew = sp.getSoLuong() + sl;
        System.out.println(slnew);
        sp.setMaSP(maSP);
        sp.setSoLuong(slnew);
        KHOdao.update(sp);
        FillPageSP();
        model.removeRow(row);
        tblList.setModel(model);
        this.setForm();
    }
// xét thông tin đơn hàng thêm form

    public void clearForm() {
        lblNguoiTao.setText(Auth.user.getUsername());
        SetNgayTao();
        lblTongTien.setText(String.valueOf(0));
        txtKhuyenMai.setText(String.valueOf(0));
        lblThanhTien.setText(String.valueOf(0));
        txtSdtKhach.setText(null);
        lblTenKH.setText(null);
    }

    public void setForm() {
        DonHang hd = new DonHang();
        lblNguoiTao.setText(Auth.user.getUsername());
        SetNgayTao();
        int tongTien = 0;
        for (int row = 0; row < tblList.getRowCount(); row++) {
            int sl = (Integer) tblList.getValueAt(row, 2);
            double gia = (Double) tblList.getValueAt(row, 3);
            tongTien += sl * gia;
        }
        hd.setTongTien(tongTien);
        hd.setSale(Double.parseDouble(txtKhuyenMai.getText()));
        lblTongTien.setText(hd.getTongTien() + "");
        txtKhuyenMai.setText(hd.getSale() + "");
        lblThanhTien.setText(hd.getThanhTien() + "");
        if (!txtSdtKhach.getText().isBlank()) {
            KhachHangDAO khdao = new KhachHangDAO();
            KhachHang kh = khdao.selectByKeyword(txtSdtKhach.getText());
            if (kh != null) {
                txtSdtKhach.setText(kh.getSDT());
                lblTenKH.setText(kh.getTenKH());
            }
        }
    }

    public List<ChiTietDonHang> getSPToTable(int maDH) {
        List<ChiTietDonHang> list = new ArrayList<>();
        ChiTietDonHang ctdh = new ChiTietDonHang();
        for (int row = 0; row < tblList.getRowCount(); row++) {
            int maSP = (Integer) tblList.getValueAt(row, 0);
            int sl = (Integer) tblList.getValueAt(row, 2);
            ctdh.setMaSP(maSP);
            ctdh.setSoLuong(sl);
            ctdh.setMaDH(maDH);
            list.add(ctdh);
        }
        return list;
    }

    public DonHang getForm() {
        DonHang hd = new DonHang();
        hd.setNguoiTao(lblNguoiTao.getText());
        hd.setNgayTao(lblNgayTao.getText());
        hd.setTongTien(Integer.parseInt(lblTongTien.getText()));
        hd.setSale(Double.parseDouble(txtKhuyenMai.getText()));
        hd.setThanhTien(Integer.parseInt(lblThanhTien.getText()));
        Account acc = Accdao.selectByKeyword(hd.getNguoiTao());
        hd.setMaKH(page);
        hd.setMaAccount(acc.getMaAccount());
        KhachHangDAO khdao = new KhachHangDAO();
        KhachHang kh = khdao.selectByKeyword(txtSdtKhach.getText());
        if (kh == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng số điện thoại");
            return null;
        } else {
            hd.setMaKH(kh.getMaKH());
            return hd;
        }
    }
// insert tất cả sp trong bảng vào ctdh r insert đơn hàng

    public void InsertDonHang() {
        int max = tblList.getRowCount();
        System.out.println(max);
        DonHangDAO DHdao = new DonHangDAO();
        DonHang dh = getForm();
        if (dh != null) {
            DHdao.insert(dh);
            DonHang dhNew = DHdao.selectDonHangNew();

            List<ChiTietDonHang> listCTDH = getSPToTable(dhNew.getMaDH());
            ChiTietDonHangDAO ctdhDao = new ChiTietDonHangDAO();
            for (ChiTietDonHang ctdh : listCTDH) {
                ctdhDao.insert(ctdh);
            }
            model.setRowCount(0);
            tblList.setModel(model);
            JOptionPane.showMessageDialog(this, "Thêm đơn hàng thành công");
        }
    }

    // hủy đơn hàng
    public void huyDonHang() {
        for (int row1 = 0; row1 < tblList.getRowCount(); row1++) {
            int soluong = (Integer) tblList.getValueAt(row1, 2);
            int maSP = (int) tblList.getValueAt(row1, 0);
            SanPham sp = KHOdao.selecteByID(maSP);
            int slnew = sp.getSoLuong() + soluong;
            sp.setSoLuong(slnew);
            sp.setMaSP(maSP);
            KHOdao.update(sp);
            FillPageSP();
        }
        model.setRowCount(0);
        tblList.setModel(model);
        clearForm();
    }
// Điều hướng

    private void First() {
        this.clearPageDS(pnel1, pnel2, pnel3, pnel4, pnel5, pnel6, pnel7, pnel8);
        this.page = 1;
        this.FillPageSP();
    }

    private void Next() {
        this.clearPageDS(pnel1, pnel2, pnel3, pnel4, pnel5, pnel6, pnel7, pnel8);
        this.page++;
        int maxPage = (int) Math.floor(list.size() / 8) + 1;
        if (this.page > maxPage) {
            this.page = maxPage;
            System.out.println("maxpage" + maxPage);
        }
        this.FillPageSP();
    }

    private void Prev() {
        this.clearPageDS(pnel1, pnel2, pnel3, pnel4, pnel5, pnel6, pnel7, pnel8);
        this.page--;
        System.out.println("prevp" + page);
        if (this.page < 1) {
            this.page = 1;
        }
        this.FillPageSP();
    }

    private void Last() {
        this.clearPageDS(pnel1, pnel2, pnel3, pnel4, pnel5, pnel6, pnel7, pnel8);
        int maxPage = (int) Math.floor(list.size() / 8) + 1;
        System.out.println("size" + list.size());
        System.out.println("max" + maxPage);
        this.page = maxPage;
        this.FillPageSP();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FileChooser = new javax.swing.JFileChooser();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnInsertDonHang = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        lblDonHang = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JLabel();
        txtKhuyenMai = new javax.swing.JTextField();
        lblTongTien = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        lblNguoiTao = new javax.swing.JLabel();
        lblSdtKhachHang = new javax.swing.JLabel();
        txtSdtKhach = new javax.swing.JTextField();
        lbl = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        btnDeleteSP = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        pnel1 = new javax.swing.JPanel();
        hinh1 = new javax.swing.JLabel();
        ten1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        gia1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        SnerSl1 = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        SlKho1 = new javax.swing.JLabel();
        btnThem1 = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        pnel8 = new javax.swing.JPanel();
        hinh2 = new javax.swing.JLabel();
        ten2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        gia2 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        SnerSl8 = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        SlKho8 = new javax.swing.JLabel();
        btnThem8 = new javax.swing.JButton();
        pnel6 = new javax.swing.JPanel();
        hinh3 = new javax.swing.JLabel();
        ten3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        gia3 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        SnerSl6 = new javax.swing.JSpinner();
        jLabel22 = new javax.swing.JLabel();
        SlKho6 = new javax.swing.JLabel();
        btnThem6 = new javax.swing.JButton();
        pnel3 = new javax.swing.JPanel();
        hinh5 = new javax.swing.JLabel();
        ten5 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        gia5 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        SnerSl3 = new javax.swing.JSpinner();
        jLabel28 = new javax.swing.JLabel();
        SlKho3 = new javax.swing.JLabel();
        btnThem3 = new javax.swing.JButton();
        pnel5 = new javax.swing.JPanel();
        hinh7 = new javax.swing.JLabel();
        ten7 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        gia7 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        SnerSl5 = new javax.swing.JSpinner();
        jLabel34 = new javax.swing.JLabel();
        SlKho5 = new javax.swing.JLabel();
        btnThem5 = new javax.swing.JButton();
        pnel2 = new javax.swing.JPanel();
        hinh8 = new javax.swing.JLabel();
        ten8 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        gia8 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        SnerSl2 = new javax.swing.JSpinner();
        jLabel37 = new javax.swing.JLabel();
        SlKho2 = new javax.swing.JLabel();
        btnThem2 = new javax.swing.JButton();
        pnel7 = new javax.swing.JPanel();
        hinh6 = new javax.swing.JLabel();
        ten6 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        gia6 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        SnerSl7 = new javax.swing.JSpinner();
        jLabel40 = new javax.swing.JLabel();
        SlKho7 = new javax.swing.JLabel();
        btnThem7 = new javax.swing.JButton();
        pnel4 = new javax.swing.JPanel();
        hinh4 = new javax.swing.JLabel();
        ten4 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        gia4 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        SnerSl4 = new javax.swing.JSpinner();
        jLabel43 = new javax.swing.JLabel();
        SlKho4 = new javax.swing.JLabel();
        btnThem4 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btnLastDS = new javax.swing.JButton();
        btnPrevDS = new javax.swing.JButton();
        btnNextDS = new javax.swing.JButton();
        btnFirstDS = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblDongHo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnInsertDonHang.setBackground(new java.awt.Color(204, 255, 204));
        btnInsertDonHang.setText("Thêm đơn hàng");
        btnInsertDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertDonHangActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(204, 255, 204));
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"SP001", "SON ROUGE PUR COUTURE THE SLIM 2", "1", "1200000"},
                {"SP003", "SON KEM LÌ TATOUAGE COUTURE VELVET CREAM", "1", "1200000"},
                {"SP008", "PHẤN PHỦ SOUFFLE DECLAT", "1", "1900000"},
                {"SP007", "MASCARA MVEFC WATERPROOF", "1", "1400000"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá"
            }
        ));
        tblList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblListMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblList);

        lblDonHang.setBackground(new java.awt.Color(255, 255, 255));

        jLabel46.setText("Người tạo hóa đơn:");

        jLabel12.setText("Ngày tạo:");

        jLabel13.setText("Tổng tiền:");

        jLabel44.setText("Khuyến mãi:");

        jLabel45.setText("Thành tiền:");

        lblThanhTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblThanhTien.setForeground(new java.awt.Color(255, 0, 51));
        lblThanhTien.setText("5586000");

        txtKhuyenMai.setText("2%");
        txtKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKhuyenMaiActionPerformed(evt);
            }
        });

        lblTongTien.setText("5700000");

        lblNgayTao.setText("Hôm nay");

        lblNguoiTao.setText("Trần A");

        lblSdtKhachHang.setText("Số điện thoại khách hàng:");

        txtSdtKhach.setText("0794256222");
        txtSdtKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdtKhachActionPerformed(evt);
            }
        });

        lbl.setText("Tên Khách hàng:");

        lblTenKH.setText("Nguyễn Văn A");

        javax.swing.GroupLayout lblDonHangLayout = new javax.swing.GroupLayout(lblDonHang);
        lblDonHang.setLayout(lblDonHangLayout);
        lblDonHangLayout.setHorizontalGroup(
            lblDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblDonHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lblDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblDonHangLayout.createSequentialGroup()
                        .addGroup(lblDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSdtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lblDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSdtKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(lblDonHangLayout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lblDonHangLayout.createSequentialGroup()
                        .addGroup(lblDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(lblDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(lblDonHangLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(lblDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblDonHangLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        lblDonHangLayout.setVerticalGroup(
            lblDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblDonHangLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(lblDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(lblNguoiTao))
                .addGap(18, 18, 18)
                .addGroup(lblDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblNgayTao))
                .addGap(18, 18, 18)
                .addGroup(lblDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblTongTien))
                .addGap(22, 22, 22)
                .addGroup(lblDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txtKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lblDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(lblThanhTien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lblDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSdtKhachHang)
                    .addComponent(txtSdtKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lblDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl)
                    .addComponent(lblTenKH))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        btnDeleteSP.setBackground(new java.awt.Color(204, 255, 204));
        btnDeleteSP.setText("Xóa");
        btnDeleteSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(btnInsertDonHang)
                        .addGap(50, 50, 50)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(btnDeleteSP, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeleteSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        pnel1.setBackground(new java.awt.Color(255, 255, 255));
        pnel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        hinh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten1.setText("SON MÔI DẠNG LÌ ROUGE PUR COUTURE THE SLIM 2");

        jLabel14.setText("Giá:");

        gia1.setForeground(new java.awt.Color(255, 51, 51));
        gia1.setText("1200000");

        jLabel16.setText("Số lượng");

        jLabel17.setText("Kho còn:");

        SlKho1.setText("39");

        btnThem1.setBackground(new java.awt.Color(204, 255, 204));
        btnThem1.setText("Thêm");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnel1Layout = new javax.swing.GroupLayout(pnel1);
        pnel1.setLayout(pnel1Layout);
        pnel1Layout.setHorizontalGroup(
            pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel1Layout.createSequentialGroup()
                        .addComponent(ten1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnel1Layout.createSequentialGroup()
                        .addGroup(pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel1Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SlKho1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(pnel1Layout.createSequentialGroup()
                                .addGroup(pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnel1Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(26, 26, 26)
                                        .addComponent(SnerSl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(gia1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(pnel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnel1Layout.setVerticalGroup(
            pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ten1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(gia1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(SnerSl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(SlKho1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(btnThem1)
                .addContainerGap())
        );

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        pnel8.setBackground(new java.awt.Color(255, 255, 255));
        pnel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        hinh2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten2.setText("PHẤN PHỦ SOUFFLE DECLAT");

        jLabel15.setText("Giá:");

        gia2.setForeground(new java.awt.Color(255, 51, 51));
        gia2.setText("1900000");

        jLabel18.setText("Số lượng");

        jLabel19.setText("Kho còn:");

        SlKho8.setText("39");

        btnThem8.setBackground(new java.awt.Color(204, 255, 204));
        btnThem8.setText("Thêm");
        btnThem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnel8Layout = new javax.swing.GroupLayout(pnel8);
        pnel8.setLayout(pnel8Layout);
        pnel8Layout.setHorizontalGroup(
            pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel8Layout.createSequentialGroup()
                        .addComponent(ten2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnel8Layout.createSequentialGroup()
                        .addGroup(pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SlKho8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(pnel8Layout.createSequentialGroup()
                                .addGroup(pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnel8Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(26, 26, 26)
                                        .addComponent(SnerSl8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnel8Layout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(gia2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hinh2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(pnel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnThem8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnel8Layout.setVerticalGroup(
            pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ten2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(gia2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(SnerSl8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(SlKho8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem8)
                .addContainerGap())
        );

        pnel6.setBackground(new java.awt.Color(255, 255, 255));
        pnel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        hinh3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten3.setBackground(new java.awt.Color(0, 153, 102));
        ten3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten3.setText("CUSHION EDP LAMÉ COLLECTOR");

        jLabel20.setText("Giá:");

        gia3.setForeground(new java.awt.Color(255, 51, 51));
        gia3.setText("1960000");

        jLabel21.setText("Số lượng");

        jLabel22.setText("Kho còn:");

        SlKho6.setForeground(new java.awt.Color(255, 0, 51));
        SlKho6.setText("Hết");

        btnThem6.setBackground(new java.awt.Color(204, 255, 204));
        btnThem6.setText("Thêm");
        btnThem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnel6Layout = new javax.swing.GroupLayout(pnel6);
        pnel6.setLayout(pnel6Layout);
        pnel6Layout.setHorizontalGroup(
            pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel6Layout.createSequentialGroup()
                        .addComponent(ten3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnel6Layout.createSequentialGroup()
                        .addGroup(pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SlKho6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(pnel6Layout.createSequentialGroup()
                                .addGroup(pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnel6Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(26, 26, 26)
                                        .addComponent(SnerSl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnel6Layout.createSequentialGroup()
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(gia3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hinh3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(pnel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnThem6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnel6Layout.setVerticalGroup(
            pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ten3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(gia3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(SnerSl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(SlKho6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem6)
                .addContainerGap())
        );

        pnel3.setBackground(new java.awt.Color(255, 255, 255));
        pnel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        hinh5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten5.setText("SON KEM LÌ TATOUAGE COUTURE VELVET CREAM");

        jLabel26.setText("Giá:");

        gia5.setForeground(new java.awt.Color(255, 51, 51));
        gia5.setText("1200000");

        jLabel27.setText("Số lượng");

        jLabel28.setText("Kho còn:");

        SlKho3.setText("39");

        btnThem3.setBackground(new java.awt.Color(204, 255, 204));
        btnThem3.setText("Thêm");
        btnThem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnel3Layout = new javax.swing.GroupLayout(pnel3);
        pnel3.setLayout(pnel3Layout);
        pnel3Layout.setHorizontalGroup(
            pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel3Layout.createSequentialGroup()
                        .addComponent(ten5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addGroup(pnel3Layout.createSequentialGroup()
                        .addGroup(pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel3Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SlKho3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(pnel3Layout.createSequentialGroup()
                                .addGroup(pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnel3Layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addGap(26, 26, 26)
                                        .addComponent(SnerSl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnel3Layout.createSequentialGroup()
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(gia5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hinh5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(pnel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnThem3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnel3Layout.setVerticalGroup(
            pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ten5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(gia5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(SnerSl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(SlKho3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem3)
                .addContainerGap())
        );

        pnel5.setBackground(new java.awt.Color(255, 255, 255));
        pnel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        hinh7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh7.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten7.setText("BẢNG MẮT COUTURE COLOR CLUTCH");

        jLabel32.setText("Giá:");

        gia7.setForeground(new java.awt.Color(255, 51, 51));
        gia7.setText("3800000");

        jLabel33.setText("Số lượng");

        jLabel34.setText("Kho còn:");

        SlKho5.setText("39");

        btnThem5.setBackground(new java.awt.Color(204, 255, 204));
        btnThem5.setText("Thêm");
        btnThem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnel5Layout = new javax.swing.GroupLayout(pnel5);
        pnel5.setLayout(pnel5Layout);
        pnel5Layout.setHorizontalGroup(
            pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel5Layout.createSequentialGroup()
                        .addComponent(ten7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnel5Layout.createSequentialGroup()
                        .addGroup(pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SlKho5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(pnel5Layout.createSequentialGroup()
                                .addGroup(pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnel5Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addGap(26, 26, 26)
                                        .addComponent(SnerSl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnel5Layout.createSequentialGroup()
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(gia7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hinh7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(pnel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnThem5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnel5Layout.setVerticalGroup(
            pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ten7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(gia7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(SnerSl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(SlKho5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(btnThem5)
                .addContainerGap())
        );

        pnel2.setBackground(new java.awt.Color(255, 255, 255));
        pnel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        hinh8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh8.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten8.setText("SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL");

        jLabel35.setText("Giá:");

        gia8.setForeground(new java.awt.Color(255, 51, 51));
        gia8.setText("1200000");

        jLabel36.setText("Số lượng");

        jLabel37.setText("Kho còn:");

        SlKho2.setForeground(new java.awt.Color(255, 0, 51));
        SlKho2.setText("Hết");

        btnThem2.setBackground(new java.awt.Color(204, 255, 204));
        btnThem2.setText("Thêm");
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnel2Layout = new javax.swing.GroupLayout(pnel2);
        pnel2.setLayout(pnel2Layout);
        pnel2Layout.setHorizontalGroup(
            pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel2Layout.createSequentialGroup()
                        .addComponent(ten8, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnel2Layout.createSequentialGroup()
                        .addGroup(pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel2Layout.createSequentialGroup()
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SlKho2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(pnel2Layout.createSequentialGroup()
                                .addGroup(pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnel2Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addGap(26, 26, 26)
                                        .addComponent(SnerSl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnel2Layout.createSequentialGroup()
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(gia8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hinh8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(pnel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnThem2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnel2Layout.setVerticalGroup(
            pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ten8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(gia8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(SnerSl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(SlKho2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem2)
                .addContainerGap())
        );

        pnel7.setBackground(new java.awt.Color(255, 255, 255));
        pnel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        hinh6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh6.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten6.setText("MASCARA MVEFC WATERPROOF");

        jLabel38.setText("Giá:");

        gia6.setForeground(new java.awt.Color(255, 51, 51));
        gia6.setText("1400000");

        jLabel39.setText("Số lượng");

        jLabel40.setText("Kho còn:");

        SlKho7.setText("39");

        btnThem7.setBackground(new java.awt.Color(204, 255, 204));
        btnThem7.setText("Thêm");
        btnThem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnel7Layout = new javax.swing.GroupLayout(pnel7);
        pnel7.setLayout(pnel7Layout);
        pnel7Layout.setHorizontalGroup(
            pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel7Layout.createSequentialGroup()
                        .addComponent(ten6, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnel7Layout.createSequentialGroup()
                        .addGroup(pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SlKho7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(pnel7Layout.createSequentialGroup()
                                .addGroup(pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnel7Layout.createSequentialGroup()
                                        .addComponent(jLabel39)
                                        .addGap(26, 26, 26)
                                        .addComponent(SnerSl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnel7Layout.createSequentialGroup()
                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(gia6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hinh6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(pnel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnThem7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnel7Layout.setVerticalGroup(
            pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ten6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(gia6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(SnerSl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(SlKho7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem7)
                .addContainerGap())
        );

        pnel4.setBackground(new java.awt.Color(255, 255, 255));
        pnel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        hinh4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh4.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten4.setText("SON THỎI SATIN LÌ THE BOLD");

        jLabel41.setText("Giá:");

        gia4.setForeground(new java.awt.Color(255, 51, 51));
        gia4.setText("1200000");

        jLabel42.setText("Số lượng");

        jLabel43.setText("Kho còn:");

        SlKho4.setText("39");

        btnThem4.setBackground(new java.awt.Color(204, 255, 204));
        btnThem4.setText("Thêm");
        btnThem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnel4Layout = new javax.swing.GroupLayout(pnel4);
        pnel4.setLayout(pnel4Layout);
        pnel4Layout.setHorizontalGroup(
            pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel4Layout.createSequentialGroup()
                        .addComponent(ten4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnel4Layout.createSequentialGroup()
                        .addGroup(pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel4Layout.createSequentialGroup()
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SlKho4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(pnel4Layout.createSequentialGroup()
                                .addGroup(pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnel4Layout.createSequentialGroup()
                                        .addComponent(jLabel42)
                                        .addGap(26, 26, 26)
                                        .addComponent(SnerSl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnel4Layout.createSequentialGroup()
                                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(gia4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnel4Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(btnThem4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hinh4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        pnel4Layout.setVerticalGroup(
            pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ten4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(gia4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(SnerSl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(SlKho4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem4)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        btnLastDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/last.png"))); // NOI18N
        btnLastDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastDSActionPerformed(evt);
            }
        });

        btnPrevDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/prev.png"))); // NOI18N
        btnPrevDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevDSActionPerformed(evt);
            }
        });

        btnNextDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/next.png"))); // NOI18N
        btnNextDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextDSActionPerformed(evt);
            }
        });

        btnFirstDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/first.png"))); // NOI18N
        btnFirstDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstDSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnFirstDS)
                .addGap(18, 18, 18)
                .addComponent(btnNextDS)
                .addGap(18, 18, 18)
                .addComponent(btnPrevDS)
                .addGap(18, 18, 18)
                .addComponent(btnLastDS)
                .addGap(14, 14, 14))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFirstDS)
                    .addComponent(btnNextDS)
                    .addComponent(btnPrevDS)
                    .addComponent(btnLastDS))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 34, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pnel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pnel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(pnel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblDongHo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDongHo.setText("00:00:00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDongHo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/home.png"))); // NOI18N
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Quản lý");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Thống kê");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Hệ thống");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Trợ giúp");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnLastDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastDSActionPerformed
        this.Last();
    }//GEN-LAST:event_btnLastDSActionPerformed

    private void btnPrevDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevDSActionPerformed
        this.Prev();
    }//GEN-LAST:event_btnPrevDSActionPerformed

    private void btnNextDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextDSActionPerformed
        this.Next();
    }//GEN-LAST:event_btnNextDSActionPerformed

    private void btnFirstDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstDSActionPerformed
        this.First();
    }//GEN-LAST:event_btnFirstDSActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.search(txtSearch.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Thread clock = new Thread(this);
        clock.start();
    }//GEN-LAST:event_formWindowOpened

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        SanPham sp = KHOdao.selectByKeyword(ten1.getText());
        System.out.println(ten1.getText());
        this.AddToTable(sp, SnerSl1, SlKho1);
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        SanPham sp = KHOdao.selectByKeyword(ten2.getText());
        this.AddToTable(sp, SnerSl2, SlKho2);
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void btnThem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem3ActionPerformed
        SanPham sp = KHOdao.selectByKeyword(ten3.getText());
        this.AddToTable(sp, SnerSl3, SlKho3);
    }//GEN-LAST:event_btnThem3ActionPerformed

    private void btnThem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem4ActionPerformed
        SanPham sp = KHOdao.selectByKeyword(ten4.getText());
        this.AddToTable(sp, SnerSl4, SlKho4);
    }//GEN-LAST:event_btnThem4ActionPerformed

    private void btnThem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem5ActionPerformed
        SanPham sp = KHOdao.selectByKeyword(ten5.getText());
        this.AddToTable(sp, SnerSl5, SlKho5);
    }//GEN-LAST:event_btnThem5ActionPerformed

    private void btnThem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem6ActionPerformed
        SanPham sp = KHOdao.selectByKeyword(ten6.getText());
        this.AddToTable(sp, SnerSl6, SlKho6);
    }//GEN-LAST:event_btnThem6ActionPerformed

    private void btnThem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem7ActionPerformed
        SanPham sp = KHOdao.selectByKeyword(ten7.getText());
        this.AddToTable(sp, SnerSl7, SlKho7);
    }//GEN-LAST:event_btnThem7ActionPerformed

    private void btnThem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem8ActionPerformed
        SanPham sp = KHOdao.selectByKeyword(ten8.getText());
        this.AddToTable(sp, SnerSl8, SlKho8);
    }//GEN-LAST:event_btnThem8ActionPerformed

    private void btnDeleteSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSPActionPerformed
        this.deleteRow();
    }//GEN-LAST:event_btnDeleteSPActionPerformed

    private void txtKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhuyenMaiActionPerformed
        double sale = Double.parseDouble(txtKhuyenMai.getText());
        System.out.println(sale);
        this.setForm();
    }//GEN-LAST:event_txtKhuyenMaiActionPerformed

    private void btnInsertDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertDonHangActionPerformed
        InsertDonHang();
    }//GEN-LAST:event_btnInsertDonHangActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.huyDonHang();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void tblListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMousePressed

    }//GEN-LAST:event_tblListMousePressed

    private void txtSdtKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdtKhachActionPerformed
        KhachHangDAO KHdao = new KhachHangDAO();
        KhachHang kh = KHdao.selectByKeyword(txtSdtKhach.getText());
        if (kh != null) {
            txtSdtKhach.setForeground(Color.black);
            lblTenKH.setText(kh.getTenKH());
            lblTenKH.setForeground(Color.black);
        } else {
            txtSdtKhach.setForeground(Color.red);
            lblTenKH.setText("KHÔNG TÌM THẤY!");
            lblTenKH.setForeground(Color.red);
        }
    }//GEN-LAST:event_txtSdtKhachActionPerformed

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
            java.util.logging.Logger.getLogger(QLOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QLOrder dialog = new QLOrder(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser FileChooser;
    private javax.swing.JLabel SlKho1;
    private javax.swing.JLabel SlKho2;
    private javax.swing.JLabel SlKho3;
    private javax.swing.JLabel SlKho4;
    private javax.swing.JLabel SlKho5;
    private javax.swing.JLabel SlKho6;
    private javax.swing.JLabel SlKho7;
    private javax.swing.JLabel SlKho8;
    private javax.swing.JSpinner SnerSl1;
    private javax.swing.JSpinner SnerSl2;
    private javax.swing.JSpinner SnerSl3;
    private javax.swing.JSpinner SnerSl4;
    private javax.swing.JSpinner SnerSl5;
    private javax.swing.JSpinner SnerSl6;
    private javax.swing.JSpinner SnerSl7;
    private javax.swing.JSpinner SnerSl8;
    private javax.swing.JButton btnDeleteSP;
    private javax.swing.JButton btnFirstDS;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnInsertDonHang;
    private javax.swing.JButton btnLastDS;
    private javax.swing.JButton btnNextDS;
    private javax.swing.JButton btnPrevDS;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnThem2;
    private javax.swing.JButton btnThem3;
    private javax.swing.JButton btnThem4;
    private javax.swing.JButton btnThem5;
    private javax.swing.JButton btnThem6;
    private javax.swing.JButton btnThem7;
    private javax.swing.JButton btnThem8;
    private javax.swing.JLabel gia1;
    private javax.swing.JLabel gia2;
    private javax.swing.JLabel gia3;
    private javax.swing.JLabel gia4;
    private javax.swing.JLabel gia5;
    private javax.swing.JLabel gia6;
    private javax.swing.JLabel gia7;
    private javax.swing.JLabel gia8;
    private javax.swing.JLabel hinh1;
    private javax.swing.JLabel hinh2;
    private javax.swing.JLabel hinh3;
    private javax.swing.JLabel hinh4;
    private javax.swing.JLabel hinh5;
    private javax.swing.JLabel hinh6;
    private javax.swing.JLabel hinh7;
    private javax.swing.JLabel hinh8;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl;
    private javax.swing.JPanel lblDonHang;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblNguoiTao;
    private javax.swing.JLabel lblSdtKhachHang;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnel1;
    private javax.swing.JPanel pnel2;
    private javax.swing.JPanel pnel3;
    private javax.swing.JPanel pnel4;
    private javax.swing.JPanel pnel5;
    private javax.swing.JPanel pnel6;
    private javax.swing.JPanel pnel7;
    private javax.swing.JPanel pnel8;
    private javax.swing.JTable tblList;
    private javax.swing.JLabel ten1;
    private javax.swing.JLabel ten2;
    private javax.swing.JLabel ten3;
    private javax.swing.JLabel ten4;
    private javax.swing.JLabel ten5;
    private javax.swing.JLabel ten6;
    private javax.swing.JLabel ten7;
    private javax.swing.JLabel ten8;
    private javax.swing.JTextField txtKhuyenMai;
    private javax.swing.JTextField txtSdtKhach;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
