// HOÀN THÀNH
package qlsmp.FunctionalInterface;

import java.awt.Color;
import java.awt.Image;
import java.text.NumberFormat;
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
import qldaotao.utis.XImage;
import qlsmp.DAO.AccountDAO;
import qlsmp.DAO.ChiTietDonHangDAO;
import qlsmp.DAO.DonHangDAO;
import qlsmp.DAO.HoaDonDAO;
import qlsmp.DAO.KhachHangDAO;
import qlsmp.DAO.KhoDAO;
import qlsmp.DAO.SanPhamDAO;
import qlsmp.Model.Account;
import qlsmp.Model.ChiTietDonHang;
import qlsmp.Model.DonHang;
import qlsmp.Model.HoaDon;
import qlsmp.Model.KhachHang;
import qlsmp.Model.SanPham;

/**
 *
 * @author My Laptop
 */
public class QLDonHang extends javax.swing.JFrame implements Runnable {

    List<DonHang> list = new ArrayList<DonHang>();
    List<SanPham> listSP = new ArrayList<>();
    DonHangDAO dao = new DonHangDAO();
    int page = 1;
    int pageSP = 1;
    String[] colums = {"Mã chi tiết sản phẩm", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá"};
    DefaultTableModel model = new DefaultTableModel(colums, 0);

    int maDH = 0;

    /**
     * Creates new form QLDonHang
     */
    public QLDonHang() {
        initComponents();
        init();
    }

    void init() {
        this.setIconImage(XImage.getApplcon());
        setLocationRelativeTo(null);
        setTitle("Trang Đơn hàng");
        this.getContentPane().setBackground(Color.WHITE);
        model.setRowCount(0);
        tblList.setModel(model);

        this.clearForm();
        this.clearPageDH(pnel1, pnel2, pnel3, pnel4, pnel5, pnel6, pnel7, pnel8);
        this.clearPageSP(pnelSP1, pnelSP2, pnelSP3, pnelSP4, pnelSP5, pnelSP6, pnelSP7, pnelSP8);
        this.FillDonHang();
        this.FillPageSP();
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
//clear trang

    public void clearPageDH(JPanel pnel1, JPanel pnel2, JPanel pnel3, JPanel pnel4, JPanel pnel5, JPanel pnel6, JPanel pnel7, JPanel pnel8) {
        pnel1.setVisible(false);
        pnel2.setVisible(false);
        pnel3.setVisible(false);
        pnel4.setVisible(false);
        pnel5.setVisible(false);
        pnel6.setVisible(false);
        pnel7.setVisible(false);
        pnel8.setVisible(false);
    }

    public void clearPageSP(JPanel pnel1, JPanel pnel2, JPanel pnel3, JPanel pnel4, JPanel pnel5, JPanel pnel6, JPanel pnel7, JPanel pnel8) {
        pnel1.setVisible(false);
        pnel2.setVisible(false);
        pnel3.setVisible(false);
        pnel4.setVisible(false);
        pnel5.setVisible(false);
        pnel6.setVisible(false);
        pnel7.setVisible(false);
        pnel8.setVisible(false);
    }

    public void FillDH(DonHang dh, JPanel pnel, JLabel maDH, JLabel thanhTien, JLabel nguoiTao, JLabel Sale, JLabel ThoiGian) {
        pnel.setVisible(true);
        maDH.setText(String.valueOf(dh.getMaDH()));
        thanhTien.setText(String.valueOf(dh.getThanhTien()));
        nguoiTao.setText(dh.getNguoiTao());

        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        String formattedNumber = percentFormat.format(dh.getSale());
        Sale.setText(formattedNumber);
        //  Sale.setText(dh.getSale()+"");
        ThoiGian.setText(dh.getNgayTao());
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

    public void FillDonHang() {
        int vitri = 0;
        list = dao.selectAll();
        for (DonHang dh : list) {
            vitri++;
            if (vitri == this.page * 8 - 7) {
                FillDH(dh, pnel1, lblMaDH1, lblThanhTien1, lblNguoiTao1, lblSale1, lblThoiGian1);
            } else if (vitri == this.page * 8 - 6) {
                FillDH(dh, pnel2, lblMaDH2, lblThanhTien2, lblNguoiTao2, lblSale2, lblThoiGian2);
            } else if (vitri == this.page * 8 - 5) {
                FillDH(dh, pnel3, lblMaDH3, lblThanhTien3, lblNguoiTao3, lblSale3, lblThoiGian3);
            } else if (vitri == this.page * 8 - 4) {
                FillDH(dh, pnel4, lblMaDH4, lblThanhTien4, lblNguoiTao4, lblSale4, lblThoiGian4);
            } else if (vitri == this.page * 8 - 3) {
                FillDH(dh, pnel5, lblMaDH5, lblThanhTien5, lblNguoiTao5, lblSale5, lblThoiGian5);
            } else if (vitri == this.page * 8 - 2) {
                FillDH(dh, pnel6, lblMaDH6, lblThanhTien6, lblNguoiTao6, lblSale6, lblThoiGian6);
            } else if (vitri == this.page * 8 - 1) {
                FillDH(dh, pnel7, lblMaDH7, lblThanhTien7, lblNguoiTao7, lblSale7, lblThoiGian7);
            } else if (vitri == this.page * 8) {
                FillDH(dh, pnel8, lblMaDH8, lblThanhTien8, lblNguoiTao8, lblSale8, lblThoiGian8);
            }
        }
    }

    public void FillPageSP() {
        int vitri = 0;
//        List<SanPham> list = new ArrayList<>();
        KhoDAO KHOdao = new KhoDAO();
        listSP = KHOdao.selectAll();
        for (SanPham sp : listSP) {
            //System.out.0intln(vitri);
            vitri++;
            if (vitri == this.pageSP * 8 - 7) {
                FillSP(sp, pnelSP1, hinh1, ten1, gia1, SlKho1, SnerSl1);
            } else if (vitri == this.pageSP * 8 - 6) {
                FillSP(sp, pnelSP2, hinh2, ten2, gia2, SlKho2, SnerSl2);
            } else if (vitri == this.pageSP * 8 - 5) {
                FillSP(sp, pnelSP3, hinh3, ten3, gia3, SlKho3, SnerSl3);
            } else if (vitri == this.pageSP * 8 - 4) {
                FillSP(sp, pnelSP4, hinh4, ten4, gia4, SlKho4, SnerSl4);
            } else if (vitri == this.pageSP * 8 - 3) {
                FillSP(sp, pnelSP5, hinh5, ten5, gia5, SlKho5, SnerSl5);
            } else if (vitri == this.pageSP * 8 - 2) {
                FillSP(sp, pnelSP6, hinh6, ten6, gia6, SlKho6, SnerSl6);
            } else if (vitri == this.pageSP * 8 - 1) {
                FillSP(sp, pnelSP7, hinh7, ten7, gia7, SlKho7, SnerSl7);
            } else if (vitri == this.pageSP * 8) {
                FillSP(sp, pnelSP8, hinh8, ten8, gia8, SlKho8, SnerSl8);
            }
        }

    }

// clear xét thông tin đơn hàng thêm form
    public void clearForm() {
        lblNgayTao.setText(null);
        lblNguoiTao.setText(null);
        lblTongTien.setText(String.valueOf(0));
        txtKhuyenMai.setText(String.valueOf(0));
        lblThanhTien.setText(String.valueOf(0));
        lblTenKH.setText(null);
        txtSdtKhach.setText(null);
    }
// duyệt chi tiết đơn hàng hiện lên table

    private boolean checkExitSP(int ma) {
        for (int row = 0; row < tblList.getRowCount(); row++) {
            int maSP = (Integer) tblList.getValueAt(row, 1);
            if (ma == maSP) {
                //  tblList.sets
                return true;
            }
        }
        return false;
    }

    private void AddToTable(SanPham sp, int madh, JSpinner sner, JLabel slKho) {

        SanPhamDAO spdao = new SanPhamDAO();
        KhoDAO KHOdao = new KhoDAO();
        ChiTietDonHang ctdh = new ChiTietDonHang();
        ChiTietDonHangDAO ctdhDao = new ChiTietDonHangDAO();

        //thêm vào chi tiết đơn hàng
        if ((int) sner.getValue() != 0 && checkExitSP(sp.getMaSP()) == false) {
            ctdh.setMaDH(madh);
            ctdh.setMaSP(sp.getMaSP());
            ctdh.setSoLuong((int) sner.getValue());
            ctdhDao.insert(ctdh);
            model.setRowCount(0);
            tblList.setModel(model);
            List<ChiTietDonHang> list = new ArrayList<>();
            list = ctdhDao.selectByMaDH(madh);

            for (ChiTietDonHang chiTietDonHang : list) {
                SanPham sp1 = spdao.selecteByID(chiTietDonHang.getMaSP());
                fillToTable(chiTietDonHang, sp1);
            }

            // update lại số lượng sp trong kho
            int slnew = sp.getSoLuong() - (int) sner.getValue();
            sp.setSoLuong(slnew);

            KHOdao.update(sp);
            FillPageSP();
            this.upstatic(sp.getSoLuong(), sner, slKho);
            // update lại form đơn hàng
            int tongTien = 0;
            for (int row = 0; row < tblList.getRowCount(); row++) {
                int sl = (Integer) tblList.getValueAt(row, 3);
                double gia = (Double) tblList.getValueAt(row, 4);
                tongTien += sl * gia;
            }
            lblTongTien.setText(tongTien + "");
            DonHang dh = getForm();
            if (dh != null) {
                this.setForm(dh);
                dh.setMaDH(maDH);
                dao.update(dh);
                this.FillDonHang();
            }
        }
    }

    private void deleteRow() {
        if (tblList.getSelectedRowCount() == 1) {
            // update sản phẩm khi xóa
            KhoDAO KHOdao = new KhoDAO();
            int row = tblList.getSelectedRow();
            int sl = (int) tblList.getValueAt(row, 3);

            int maSP = (int) tblList.getValueAt(row, 1);

            SanPham sp = KHOdao.selecteByID(maSP);
            int slnew = sp.getSoLuong() + sl;
            // sp.setMaSP(maSP);
            sp.setSoLuong(slnew);
            sp.setMaSP(maSP);
            KHOdao.update(sp);
            FillPageSP();

            //update hóa đơn khi xóa
            int tongTien = 0;
            for (int row1 = 0; row1 < tblList.getRowCount(); row1++) {
                int soluong = (Integer) tblList.getValueAt(row1, 3);
                double gia = (Double) tblList.getValueAt(row1, 4);
                tongTien += soluong * gia;
            }
            lblTongTien.setText(tongTien + "");
            int maCTHD = (int) tblList.getValueAt(row, 0);
            ChiTietDonHangDAO ctdhDao = new ChiTietDonHangDAO();
            ctdhDao.delete(maCTHD);
            model.removeRow(row);
            tblList.setModel(model);

            DonHang dh = getForm();
            if (dh != null) {
                this.setForm(dh);
                dh.setMaDH(maDH);
                dao.update(dh);
                this.FillDonHang();
            }
        }
    }

    public void fillToTable(ChiTietDonHang ctdh, SanPham sp) {
        model.addRow(new Object[]{ctdh.getMaCTDH(), ctdh.getMaSP(), sp.getTensp(), ctdh.getSoLuong(), sp.getGia()});
        tblList.setModel(model);
    }
// get_set form

    public DonHang getForm() {
        DonHang hd = new DonHang();
        hd.setNguoiTao(lblNguoiTao.getText());
        hd.setNgayTao(lblNgayTao.getText());
        hd.setTongTien(Integer.parseInt(lblTongTien.getText()));
        hd.setSale(Double.parseDouble(txtKhuyenMai.getText()));
        hd.setThanhTien(Integer.parseInt(lblThanhTien.getText()));
        AccountDAO Accdao = new AccountDAO();
        Account acc = Accdao.selectByKeyword(hd.getNguoiTao());
        hd.setMaAccount(acc.getMaAccount());
        KhachHangDAO khdao = new KhachHangDAO();
        KhachHang kh = khdao.selectByKeyword(txtSdtKhach.getText());
        if (kh == null) {
            return null;
        } else {
            hd.setMaKH(kh.getMaKH());
            return hd;
        }
    }

    public void setForm(DonHang dh) {
        //xét lên table
        ChiTietDonHang ctdh = new ChiTietDonHang();
        ChiTietDonHangDAO ctdhDao = new ChiTietDonHangDAO();
        List<ChiTietDonHang> listCtdh = ctdhDao.selectByMaDH(dh.getMaDH());

        for (ChiTietDonHang chiTietDonHang : listCtdh) {
            KhoDAO khoDao = new KhoDAO();
            SanPham sp = khoDao.selecteByID(chiTietDonHang.getMaSP());
            fillToTable(chiTietDonHang, sp);
        }
        //xét lên form đơn hàng
        lblNguoiTao.setText(dh.getNguoiTao());
        lblNgayTao.setText(dh.getNgayTao());
        txtKhuyenMai.setText(dh.getSale() + "");
        lblTongTien.setText(dh.getTongTien() + "");
        lblThanhTien.setText(dh.getThanhTien() + "");

        KhachHangDAO khdao = new KhachHangDAO();
        KhachHang kh = khdao.selecteByID(dh.getMaKH());
        if (kh != null) {
            txtSdtKhach.setText(kh.getSDT());
            lblTenKH.setText(kh.getTenKH());
        }
    }

    public void edit(int id) {
        DonHang dh = dao.selecteByID(id);
        this.setForm(dh);
        tabs.setSelectedIndex(1);
    }

    //THANH TOÁN NÈ
    public void ThanhToan() {
        DonHang dh = getForm();
        if (dh != null) {
            dh.setTrangThai(true);
            dh.setMaDH(maDH);

            HoaDon hd = new HoaDon();
            hd.setMaHD(dh.getMaDH());
            hd.setNgayTao(dh.getNgayTao());
            hd.setNguoiTao(dh.getNguoiTao());
            hd.setTongTien(dh.getTongTien());
            hd.setSale(dh.getSale());
            hd.setThanhTien(dh.getThanhTien());
            hd.setMaKH(dh.getMaKH());

            HoaDonDAO hddao = new HoaDonDAO();
            hddao.insert(hd);

            dao.update(dh);
            JOptionPane.showMessageDialog(this, "Thanh toán thành công");
            this.setForm(new DonHang());
            this.FillDonHang();
            tabs.setSelectedIndex(0);

        }
    }

    // Xóa đơn hàng nè
    public void DeleteDonHang(int maDH) {
        ChiTietDonHangDAO ctdhDao = new ChiTietDonHangDAO();
        KhoDAO KHOdao = new KhoDAO();
        for (int row1 = 0; row1 < tblList.getRowCount(); row1++) {
            int soluong = (Integer) tblList.getValueAt(row1, 3);
            int maSP = (int) tblList.getValueAt(row1, 1);
            int maCTDH = (int) tblList.getValueAt(row1, 0);
            SanPham sp = KHOdao.selecteByID(maSP);
            int slnew = sp.getSoLuong() + soluong;
            sp.setSoLuong(slnew);
            sp.setMaSP(maSP);
            KHOdao.update(sp);
            FillPageSP();
            ctdhDao.delete(maCTDH);
        }
        dao.delete(maDH);
        FillDonHang();
        JOptionPane.showMessageDialog(this, "Xóa đơn hàng thành công");
        tabs.setSelectedIndex(0);
    }
// Điều hướng

    private void First() {
        this.clearPageDH(pnel1, pnel2, pnel3, pnel4, pnel5, pnel6, pnel7, pnel8);
        this.page = 1;
        this.FillDonHang();
    }

    private void Next() {
        this.clearPageDH(pnel1, pnel2, pnel3, pnel4, pnel5, pnel6, pnel7, pnel8);
        this.page++;
        int maxPage = (int) Math.floor(list.size() / 8) + 1;
        if (this.page > maxPage) {
            this.page = maxPage;
            System.out.println("maxpage" + maxPage);
        }
        this.FillDonHang();
    }

    private void Prev() {
        this.clearPageDH(pnel1, pnel2, pnel3, pnel4, pnel5, pnel6, pnel7, pnel8);
        this.page--;
        System.out.println("prevp" + page);
        if (this.page < 1) {
            this.page = 1;
        }
        this.FillDonHang();
    }

    private void Last() {
        this.clearPageDH(pnel1, pnel2, pnel3, pnel4, pnel5, pnel6, pnel7, pnel8);
        int maxPage = (int) Math.floor(list.size() / 8) + 1;
        System.out.println("size" + list.size());
        System.out.println("max" + maxPage);
        this.page = maxPage;
        this.FillDonHang();
    }

    private void FirstSP() {
        this.clearPageSP(pnelSP1, pnelSP2, pnelSP3, pnelSP4, pnelSP5, pnelSP6, pnelSP7, pnelSP8);
        this.pageSP = 1;
        this.FillPageSP();
    }

    private void NextSP() {
        this.clearPageSP(pnelSP1, pnelSP2, pnelSP3, pnelSP4, pnelSP5, pnelSP6, pnelSP7, pnelSP8);
        this.pageSP++;
        int maxPage = (int) Math.floor(listSP.size() / 8) + 1;
        if (this.pageSP > maxPage) {
            this.pageSP = maxPage;
            System.out.println("maxpage" + maxPage);
        }
        this.FillPageSP();
    }

    private void PrevSP() {
        this.clearPageSP(pnelSP1, pnelSP2, pnelSP3, pnelSP4, pnelSP5, pnelSP6, pnelSP7, pnelSP8);
        this.pageSP--;
        System.out.println("prevp" + pageSP);
        if (this.pageSP < 1) {
            this.pageSP = 1;
        }
        this.FillPageSP();
    }

    private void LastSP() {
        this.clearPageSP(pnelSP1, pnelSP2, pnelSP3, pnelSP4, pnelSP5, pnelSP6, pnelSP7, pnelSP8);
        int maxPage = (int) Math.floor(listSP.size() / 8) + 1;
        System.out.println("size" + listSP.size());
        System.out.println("max" + maxPage);
        this.pageSP = maxPage;
        this.FillPageSP();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblDongHo = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btnLastDH = new javax.swing.JButton();
        btnPrevDH = new javax.swing.JButton();
        btnNextDH = new javax.swing.JButton();
        btnFirstDH = new javax.swing.JButton();
        pnel1 = new javax.swing.JPanel();
        lblMaDH1 = new javax.swing.JLabel();
        lblThoiGian1 = new javax.swing.JLabel();
        lblThanhTien1 = new javax.swing.JLabel();
        lblNguoiTao1 = new javax.swing.JLabel();
        lblSale1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnel2 = new javax.swing.JPanel();
        lblMaDH2 = new javax.swing.JLabel();
        lblThoiGian2 = new javax.swing.JLabel();
        lblThanhTien2 = new javax.swing.JLabel();
        lblNguoiTao2 = new javax.swing.JLabel();
        lblSale2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnel3 = new javax.swing.JPanel();
        lblMaDH3 = new javax.swing.JLabel();
        lblThoiGian3 = new javax.swing.JLabel();
        lblThanhTien3 = new javax.swing.JLabel();
        lblNguoiTao3 = new javax.swing.JLabel();
        lblSale3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pnel5 = new javax.swing.JPanel();
        lblMaDH5 = new javax.swing.JLabel();
        lblThoiGian5 = new javax.swing.JLabel();
        lblThanhTien5 = new javax.swing.JLabel();
        lblNguoiTao5 = new javax.swing.JLabel();
        lblSale5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pnel4 = new javax.swing.JPanel();
        lblMaDH4 = new javax.swing.JLabel();
        lblThoiGian4 = new javax.swing.JLabel();
        lblThanhTien4 = new javax.swing.JLabel();
        lblNguoiTao4 = new javax.swing.JLabel();
        lblSale4 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        pnel6 = new javax.swing.JPanel();
        lblMaDH6 = new javax.swing.JLabel();
        lblThoiGian6 = new javax.swing.JLabel();
        lblThanhTien6 = new javax.swing.JLabel();
        lblNguoiTao6 = new javax.swing.JLabel();
        lblSale6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pnel7 = new javax.swing.JPanel();
        lblMaDH7 = new javax.swing.JLabel();
        lblThoiGian7 = new javax.swing.JLabel();
        lblThanhTien7 = new javax.swing.JLabel();
        lblNguoiTao7 = new javax.swing.JLabel();
        lblSale7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pnel8 = new javax.swing.JPanel();
        lblMaDH8 = new javax.swing.JLabel();
        lblThoiGian8 = new javax.swing.JLabel();
        lblThanhTien8 = new javax.swing.JLabel();
        lblNguoiTao8 = new javax.swing.JLabel();
        lblSale8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnInsertDonHang = new javax.swing.JButton();
        tbnXoaDH = new javax.swing.JButton();
        btnDeleteRow = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        lblDonHang = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
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
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        pnelSP1 = new javax.swing.JPanel();
        hinh1 = new javax.swing.JLabel();
        ten1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        gia1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        SnerSl1 = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        SlKho1 = new javax.swing.JLabel();
        btnThem1 = new javax.swing.JButton();
        pnelSP2 = new javax.swing.JPanel();
        hinh2 = new javax.swing.JLabel();
        ten2 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        gia2 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        SnerSl2 = new javax.swing.JSpinner();
        jLabel22 = new javax.swing.JLabel();
        SlKho2 = new javax.swing.JLabel();
        btnThem2 = new javax.swing.JButton();
        pnelSP3 = new javax.swing.JPanel();
        hinh3 = new javax.swing.JLabel();
        ten3 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        gia3 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        SnerSl3 = new javax.swing.JSpinner();
        jLabel25 = new javax.swing.JLabel();
        SlKho3 = new javax.swing.JLabel();
        btnThem3 = new javax.swing.JButton();
        pnelSP4 = new javax.swing.JPanel();
        hinh4 = new javax.swing.JLabel();
        ten4 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        gia4 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        SnerSl4 = new javax.swing.JSpinner();
        jLabel28 = new javax.swing.JLabel();
        SlKho4 = new javax.swing.JLabel();
        btnThem4 = new javax.swing.JButton();
        pnelSP5 = new javax.swing.JPanel();
        hinh5 = new javax.swing.JLabel();
        ten5 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        gia5 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        SnerSl5 = new javax.swing.JSpinner();
        jLabel31 = new javax.swing.JLabel();
        SlKho5 = new javax.swing.JLabel();
        btnThem5 = new javax.swing.JButton();
        pnelSP6 = new javax.swing.JPanel();
        hinh6 = new javax.swing.JLabel();
        ten6 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        gia6 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        SnerSl6 = new javax.swing.JSpinner();
        jLabel34 = new javax.swing.JLabel();
        SlKho6 = new javax.swing.JLabel();
        btnThem6 = new javax.swing.JButton();
        pnelSP7 = new javax.swing.JPanel();
        hinh7 = new javax.swing.JLabel();
        ten7 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        gia7 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        SnerSl7 = new javax.swing.JSpinner();
        jLabel37 = new javax.swing.JLabel();
        SlKho7 = new javax.swing.JLabel();
        btnThem7 = new javax.swing.JButton();
        pnelSP8 = new javax.swing.JPanel();
        hinh8 = new javax.swing.JLabel();
        ten8 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        gia8 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        SnerSl8 = new javax.swing.JSpinner();
        jLabel40 = new javax.swing.JLabel();
        SlKho8 = new javax.swing.JLabel();
        btnThem8 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        btnLastSP = new javax.swing.JButton();
        btnPrevSP = new javax.swing.JButton();
        btnFirstSP = new javax.swing.JButton();
        btnNextSP = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lblDongHo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDongHo.setText("00:00:00");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(1008, Short.MAX_VALUE)
                .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDongHo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.setBackground(new java.awt.Color(255, 255, 255));
        tabs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabsMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        btnLastDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/last.png"))); // NOI18N
        btnLastDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastDHActionPerformed(evt);
            }
        });

        btnPrevDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/prev.png"))); // NOI18N
        btnPrevDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevDHActionPerformed(evt);
            }
        });

        btnNextDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/next.png"))); // NOI18N
        btnNextDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextDHActionPerformed(evt);
            }
        });

        btnFirstDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/first.png"))); // NOI18N
        btnFirstDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstDHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnFirstDH)
                .addGap(18, 18, 18)
                .addComponent(btnNextDH)
                .addGap(18, 18, 18)
                .addComponent(btnPrevDH)
                .addGap(18, 18, 18)
                .addComponent(btnLastDH)
                .addGap(14, 14, 14))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFirstDH)
                    .addComponent(btnNextDH)
                    .addComponent(btnPrevDH)
                    .addComponent(btnLastDH))
                .addContainerGap())
        );

        pnel1.setBackground(new java.awt.Color(255, 255, 255));
        pnel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        pnel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnel1MousePressed(evt);
            }
        });

        lblMaDH1.setBackground(new java.awt.Color(149, 170, 243));
        lblMaDH1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblMaDH1.setForeground(new java.awt.Color(0, 0, 51));
        lblMaDH1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaDH1.setText("1");
        lblMaDH1.setOpaque(true);

        lblThoiGian1.setText("45 phút");

        lblThanhTien1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblThanhTien1.setForeground(new java.awt.Color(255, 51, 51));
        lblThanhTien1.setText("120000 ");

        lblNguoiTao1.setText("ThanhHieu");

        lblSale1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSale1.setForeground(new java.awt.Color(255, 51, 51));
        lblSale1.setText("2%");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/discount.png"))); // NOI18N

        javax.swing.GroupLayout pnel1Layout = new javax.swing.GroupLayout(pnel1);
        pnel1.setLayout(pnel1Layout);
        pnel1Layout.setHorizontalGroup(
            pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel1Layout.createSequentialGroup()
                .addComponent(lblMaDH1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNguoiTao1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnel1Layout.createSequentialGroup()
                        .addComponent(lblThanhTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addGap(12, 12, 12)
                .addGroup(pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSale1)
                    .addComponent(lblThoiGian1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnel1Layout.setVerticalGroup(
            pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMaDH1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblThanhTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSale1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThoiGian1)
                    .addComponent(lblNguoiTao1))
                .addContainerGap())
        );

        pnel2.setBackground(new java.awt.Color(255, 255, 255));
        pnel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        pnel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnel2MousePressed(evt);
            }
        });

        lblMaDH2.setBackground(new java.awt.Color(149, 170, 243));
        lblMaDH2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblMaDH2.setForeground(new java.awt.Color(0, 0, 51));
        lblMaDH2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaDH2.setText("2");
        lblMaDH2.setOpaque(true);

        lblThoiGian2.setText("45 phút");

        lblThanhTien2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblThanhTien2.setForeground(new java.awt.Color(255, 51, 51));
        lblThanhTien2.setText("120000");

        lblNguoiTao2.setText("ThanhHieu");

        lblSale2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSale2.setForeground(new java.awt.Color(255, 51, 51));
        lblSale2.setText("2%");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/discount.png"))); // NOI18N

        javax.swing.GroupLayout pnel2Layout = new javax.swing.GroupLayout(pnel2);
        pnel2.setLayout(pnel2Layout);
        pnel2Layout.setHorizontalGroup(
            pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel2Layout.createSequentialGroup()
                .addComponent(lblMaDH2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNguoiTao2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnel2Layout.createSequentialGroup()
                        .addComponent(lblThanhTien2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                        .addComponent(jLabel6)))
                .addGap(12, 12, 12)
                .addGroup(pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSale2)
                    .addComponent(lblThoiGian2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnel2Layout.setVerticalGroup(
            pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMaDH2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblThanhTien2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSale2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThoiGian2)
                    .addComponent(lblNguoiTao2))
                .addContainerGap())
        );

        pnel3.setBackground(new java.awt.Color(255, 255, 255));
        pnel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        pnel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnel3MousePressed(evt);
            }
        });

        lblMaDH3.setBackground(new java.awt.Color(149, 170, 243));
        lblMaDH3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblMaDH3.setForeground(new java.awt.Color(0, 0, 51));
        lblMaDH3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaDH3.setText("3");
        lblMaDH3.setOpaque(true);

        lblThoiGian3.setText("45 phút");

        lblThanhTien3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblThanhTien3.setForeground(new java.awt.Color(255, 51, 51));
        lblThanhTien3.setText("120000");

        lblNguoiTao3.setText("ThanhHieu");

        lblSale3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSale3.setForeground(new java.awt.Color(255, 51, 51));
        lblSale3.setText("2%");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/discount.png"))); // NOI18N

        javax.swing.GroupLayout pnel3Layout = new javax.swing.GroupLayout(pnel3);
        pnel3.setLayout(pnel3Layout);
        pnel3Layout.setHorizontalGroup(
            pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel3Layout.createSequentialGroup()
                .addComponent(lblMaDH3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNguoiTao3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnel3Layout.createSequentialGroup()
                        .addComponent(lblThanhTien3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                        .addComponent(jLabel12)))
                .addGap(12, 12, 12)
                .addGroup(pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSale3)
                    .addComponent(lblThoiGian3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnel3Layout.setVerticalGroup(
            pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMaDH3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblThanhTien3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSale3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThoiGian3)
                    .addComponent(lblNguoiTao3))
                .addContainerGap())
        );

        pnel5.setBackground(new java.awt.Color(255, 255, 255));
        pnel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        pnel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnel5MousePressed(evt);
            }
        });

        lblMaDH5.setBackground(new java.awt.Color(149, 170, 243));
        lblMaDH5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblMaDH5.setForeground(new java.awt.Color(0, 0, 51));
        lblMaDH5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaDH5.setText("5");
        lblMaDH5.setOpaque(true);

        lblThoiGian5.setText("45 phút");

        lblThanhTien5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblThanhTien5.setForeground(new java.awt.Color(255, 51, 51));
        lblThanhTien5.setText("120000");

        lblNguoiTao5.setText("ThanhHieu");

        lblSale5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSale5.setForeground(new java.awt.Color(255, 51, 51));
        lblSale5.setText("2%");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/discount.png"))); // NOI18N

        javax.swing.GroupLayout pnel5Layout = new javax.swing.GroupLayout(pnel5);
        pnel5.setLayout(pnel5Layout);
        pnel5Layout.setHorizontalGroup(
            pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel5Layout.createSequentialGroup()
                .addComponent(lblMaDH5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNguoiTao5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnel5Layout.createSequentialGroup()
                        .addComponent(lblThanhTien5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)))
                .addGap(12, 12, 12)
                .addGroup(pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSale5)
                    .addComponent(lblThoiGian5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnel5Layout.setVerticalGroup(
            pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMaDH5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblThanhTien5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSale5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThoiGian5)
                    .addComponent(lblNguoiTao5))
                .addContainerGap())
        );

        pnel4.setBackground(new java.awt.Color(255, 255, 255));
        pnel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        pnel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnel4MousePressed(evt);
            }
        });

        lblMaDH4.setBackground(new java.awt.Color(149, 170, 243));
        lblMaDH4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblMaDH4.setForeground(new java.awt.Color(0, 0, 51));
        lblMaDH4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaDH4.setText("4");
        lblMaDH4.setOpaque(true);

        lblThoiGian4.setText("45 phút");

        lblThanhTien4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblThanhTien4.setForeground(new java.awt.Color(255, 51, 51));
        lblThanhTien4.setText("120000");

        lblNguoiTao4.setText("ThanhHieu");

        lblSale4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSale4.setForeground(new java.awt.Color(255, 51, 51));
        lblSale4.setText("2%");

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/discount.png"))); // NOI18N

        javax.swing.GroupLayout pnel4Layout = new javax.swing.GroupLayout(pnel4);
        pnel4.setLayout(pnel4Layout);
        pnel4Layout.setHorizontalGroup(
            pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel4Layout.createSequentialGroup()
                .addComponent(lblMaDH4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNguoiTao4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnel4Layout.createSequentialGroup()
                        .addComponent(lblThanhTien4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                        .addComponent(jLabel18)))
                .addGap(12, 12, 12)
                .addGroup(pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSale4)
                    .addComponent(lblThoiGian4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnel4Layout.setVerticalGroup(
            pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMaDH4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addGroup(pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblThanhTien4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSale4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThoiGian4)
                    .addComponent(lblNguoiTao4))
                .addContainerGap())
        );

        pnel6.setBackground(new java.awt.Color(255, 255, 255));
        pnel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        pnel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnel6MousePressed(evt);
            }
        });

        lblMaDH6.setBackground(new java.awt.Color(149, 170, 243));
        lblMaDH6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblMaDH6.setForeground(new java.awt.Color(0, 0, 51));
        lblMaDH6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaDH6.setText("6");
        lblMaDH6.setOpaque(true);

        lblThoiGian6.setText("45 phút");

        lblThanhTien6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblThanhTien6.setForeground(new java.awt.Color(255, 51, 51));
        lblThanhTien6.setText("120000");

        lblNguoiTao6.setText("ThanhHieu");

        lblSale6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSale6.setForeground(new java.awt.Color(255, 51, 51));
        lblSale6.setText("2%");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/discount.png"))); // NOI18N

        javax.swing.GroupLayout pnel6Layout = new javax.swing.GroupLayout(pnel6);
        pnel6.setLayout(pnel6Layout);
        pnel6Layout.setHorizontalGroup(
            pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel6Layout.createSequentialGroup()
                .addComponent(lblMaDH6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNguoiTao6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnel6Layout.createSequentialGroup()
                        .addComponent(lblThanhTien6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                        .addComponent(jLabel9)))
                .addGap(12, 12, 12)
                .addGroup(pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSale6)
                    .addComponent(lblThoiGian6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnel6Layout.setVerticalGroup(
            pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMaDH6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblThanhTien6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSale6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThoiGian6)
                    .addComponent(lblNguoiTao6))
                .addContainerGap())
        );

        pnel7.setBackground(new java.awt.Color(255, 255, 255));
        pnel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        pnel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnel7MousePressed(evt);
            }
        });

        lblMaDH7.setBackground(new java.awt.Color(149, 170, 243));
        lblMaDH7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblMaDH7.setForeground(new java.awt.Color(0, 0, 51));
        lblMaDH7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaDH7.setText("7");
        lblMaDH7.setOpaque(true);

        lblThoiGian7.setText("45 phút");

        lblThanhTien7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblThanhTien7.setForeground(new java.awt.Color(255, 51, 51));
        lblThanhTien7.setText("120000");

        lblNguoiTao7.setText("ThanhHieu");

        lblSale7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSale7.setForeground(new java.awt.Color(255, 51, 51));
        lblSale7.setText("2%");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/discount.png"))); // NOI18N

        javax.swing.GroupLayout pnel7Layout = new javax.swing.GroupLayout(pnel7);
        pnel7.setLayout(pnel7Layout);
        pnel7Layout.setHorizontalGroup(
            pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel7Layout.createSequentialGroup()
                .addComponent(lblMaDH7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNguoiTao7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnel7Layout.createSequentialGroup()
                        .addComponent(lblThanhTien7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                        .addComponent(jLabel10)))
                .addGap(12, 12, 12)
                .addGroup(pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSale7)
                    .addComponent(lblThoiGian7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnel7Layout.setVerticalGroup(
            pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMaDH7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblThanhTien7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSale7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThoiGian7)
                    .addComponent(lblNguoiTao7))
                .addContainerGap())
        );

        pnel8.setBackground(new java.awt.Color(255, 255, 255));
        pnel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        pnel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnel8MousePressed(evt);
            }
        });

        lblMaDH8.setBackground(new java.awt.Color(149, 170, 243));
        lblMaDH8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblMaDH8.setForeground(new java.awt.Color(0, 0, 51));
        lblMaDH8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaDH8.setText("8");
        lblMaDH8.setOpaque(true);

        lblThoiGian8.setText("45 phút");

        lblThanhTien8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblThanhTien8.setForeground(new java.awt.Color(255, 51, 51));
        lblThanhTien8.setText("120000");

        lblNguoiTao8.setText("ThanhHieu");

        lblSale8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSale8.setForeground(new java.awt.Color(255, 51, 51));
        lblSale8.setText("2%");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/discount.png"))); // NOI18N

        javax.swing.GroupLayout pnel8Layout = new javax.swing.GroupLayout(pnel8);
        pnel8.setLayout(pnel8Layout);
        pnel8Layout.setHorizontalGroup(
            pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel8Layout.createSequentialGroup()
                .addComponent(lblMaDH8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNguoiTao8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnel8Layout.createSequentialGroup()
                        .addComponent(lblThanhTien8, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                        .addComponent(jLabel11)))
                .addGap(12, 12, 12)
                .addGroup(pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSale8)
                    .addComponent(lblThoiGian8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnel8Layout.setVerticalGroup(
            pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMaDH8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblThanhTien8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSale8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThoiGian8)
                    .addComponent(lblNguoiTao8))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("ĐƠN HÀNG ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pnel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pnel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pnel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pnel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(465, 465, 465)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(459, 459, 459)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(432, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        tabs.addTab("Đơn hàng", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnInsertDonHang.setBackground(new java.awt.Color(204, 255, 204));
        btnInsertDonHang.setText("Thanh Toán");
        btnInsertDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertDonHangActionPerformed(evt);
            }
        });

        tbnXoaDH.setBackground(new java.awt.Color(204, 255, 204));
        tbnXoaDH.setText("Xóa đơn hàng");
        tbnXoaDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnXoaDHActionPerformed(evt);
            }
        });

        btnDeleteRow.setBackground(new java.awt.Color(204, 255, 204));
        btnDeleteRow.setText("Xóa");
        btnDeleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRowActionPerformed(evt);
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
        jScrollPane1.setViewportView(tblList);

        lblDonHang.setBackground(new java.awt.Color(255, 255, 255));

        jLabel46.setText("Người tạo hóa đơn:");

        jLabel41.setText("Ngày tạo:");

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
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jLabel41)
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(65, 65, 65)
                            .addComponent(btnInsertDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(tbnXoaDH, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(108, 108, 108)
                                    .addComponent(btnDeleteRow, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeleteRow)
                .addGap(18, 18, 18)
                .addComponent(lblDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbnXoaDH, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setText("Tìm kiếm");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        pnelSP1.setBackground(new java.awt.Color(255, 255, 255));
        pnelSP1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        hinh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten1.setText("SON MÔI DẠNG LÌ ROUGE PUR COUTURE THE SLIM 2");

        jLabel16.setText("Giá:");

        gia1.setForeground(new java.awt.Color(255, 51, 51));
        gia1.setText("1200000");

        jLabel17.setText("Số lượng");

        jLabel19.setText("Kho còn:");

        SlKho1.setText("39");

        btnThem1.setBackground(new java.awt.Color(204, 255, 204));
        btnThem1.setText("Thêm");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnelSP1Layout = new javax.swing.GroupLayout(pnelSP1);
        pnelSP1.setLayout(pnelSP1Layout);
        pnelSP1Layout.setHorizontalGroup(
            pnelSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelSP1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelSP1Layout.createSequentialGroup()
                        .addComponent(ten1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnelSP1Layout.createSequentialGroup()
                        .addGroup(pnelSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnelSP1Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SlKho1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(pnelSP1Layout.createSequentialGroup()
                                .addGroup(pnelSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnelSP1Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addGap(26, 26, 26)
                                        .addComponent(SnerSl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnelSP1Layout.createSequentialGroup()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(gia1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnelSP1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(pnelSP1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnelSP1Layout.setVerticalGroup(
            pnelSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelSP1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ten1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(gia1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(SnerSl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnelSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(SlKho1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem1)
                .addContainerGap())
        );

        pnelSP2.setBackground(new java.awt.Color(255, 255, 255));
        pnelSP2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        hinh2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten2.setText("SON MÔI DẠNG LÌ ROUGE PUR COUTURE THE SLIM 2");

        jLabel20.setText("Giá:");

        gia2.setForeground(new java.awt.Color(255, 51, 51));
        gia2.setText("1200000");

        jLabel21.setText("Số lượng");

        jLabel22.setText("Kho còn:");

        SlKho2.setText("39");

        btnThem2.setBackground(new java.awt.Color(204, 255, 204));
        btnThem2.setText("Thêm");
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnelSP2Layout = new javax.swing.GroupLayout(pnelSP2);
        pnelSP2.setLayout(pnelSP2Layout);
        pnelSP2Layout.setHorizontalGroup(
            pnelSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelSP2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelSP2Layout.createSequentialGroup()
                        .addComponent(ten2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnelSP2Layout.createSequentialGroup()
                        .addGroup(pnelSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnelSP2Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SlKho2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(pnelSP2Layout.createSequentialGroup()
                                .addGroup(pnelSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnelSP2Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(26, 26, 26)
                                        .addComponent(SnerSl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnelSP2Layout.createSequentialGroup()
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(gia2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnelSP2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hinh2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(pnelSP2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnThem2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnelSP2Layout.setVerticalGroup(
            pnelSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelSP2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ten2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(gia2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(SnerSl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnelSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(SlKho2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem2)
                .addContainerGap())
        );

        pnelSP3.setBackground(new java.awt.Color(255, 255, 255));
        pnelSP3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        hinh3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten3.setText("SON MÔI DẠNG LÌ ROUGE PUR COUTURE THE SLIM 2");

        jLabel23.setText("Giá:");

        gia3.setForeground(new java.awt.Color(255, 51, 51));
        gia3.setText("1200000");

        jLabel24.setText("Số lượng");

        jLabel25.setText("Kho còn:");

        SlKho3.setText("39");

        btnThem3.setBackground(new java.awt.Color(204, 255, 204));
        btnThem3.setText("Thêm");
        btnThem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnelSP3Layout = new javax.swing.GroupLayout(pnelSP3);
        pnelSP3.setLayout(pnelSP3Layout);
        pnelSP3Layout.setHorizontalGroup(
            pnelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelSP3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelSP3Layout.createSequentialGroup()
                        .addComponent(ten3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnelSP3Layout.createSequentialGroup()
                        .addGroup(pnelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnelSP3Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SlKho3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(pnelSP3Layout.createSequentialGroup()
                                .addGroup(pnelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnelSP3Layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addGap(26, 26, 26)
                                        .addComponent(SnerSl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnelSP3Layout.createSequentialGroup()
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(gia3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(pnelSP3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnThem3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnelSP3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hinh3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        pnelSP3Layout.setVerticalGroup(
            pnelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelSP3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ten3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(gia3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(SnerSl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnelSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(SlKho3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem3)
                .addContainerGap())
        );

        pnelSP4.setBackground(new java.awt.Color(255, 255, 255));
        pnelSP4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        hinh4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh4.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten4.setText("SON MÔI DẠNG LÌ ROUGE PUR COUTURE THE SLIM 2");

        jLabel26.setText("Giá:");

        gia4.setForeground(new java.awt.Color(255, 51, 51));
        gia4.setText("1200000");

        jLabel27.setText("Số lượng");

        jLabel28.setText("Kho còn:");

        SlKho4.setText("39");

        btnThem4.setBackground(new java.awt.Color(204, 255, 204));
        btnThem4.setText("Thêm");
        btnThem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnelSP4Layout = new javax.swing.GroupLayout(pnelSP4);
        pnelSP4.setLayout(pnelSP4Layout);
        pnelSP4Layout.setHorizontalGroup(
            pnelSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelSP4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelSP4Layout.createSequentialGroup()
                        .addComponent(ten4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnelSP4Layout.createSequentialGroup()
                        .addGroup(pnelSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnelSP4Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SlKho4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(pnelSP4Layout.createSequentialGroup()
                                .addGroup(pnelSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnelSP4Layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addGap(26, 26, 26)
                                        .addComponent(SnerSl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnelSP4Layout.createSequentialGroup()
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(gia4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(pnelSP4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnThem4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnelSP4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hinh4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        pnelSP4Layout.setVerticalGroup(
            pnelSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelSP4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ten4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(gia4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(SnerSl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnelSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(SlKho4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem4)
                .addContainerGap())
        );

        pnelSP5.setBackground(new java.awt.Color(255, 255, 255));
        pnelSP5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        hinh5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten5.setText("SON MÔI DẠNG LÌ ROUGE PUR COUTURE THE SLIM 2");

        jLabel29.setText("Giá:");

        gia5.setForeground(new java.awt.Color(255, 51, 51));
        gia5.setText("1200000");

        jLabel30.setText("Số lượng");

        jLabel31.setText("Kho còn:");

        SlKho5.setText("39");

        btnThem5.setBackground(new java.awt.Color(204, 255, 204));
        btnThem5.setText("Thêm");
        btnThem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnelSP5Layout = new javax.swing.GroupLayout(pnelSP5);
        pnelSP5.setLayout(pnelSP5Layout);
        pnelSP5Layout.setHorizontalGroup(
            pnelSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelSP5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelSP5Layout.createSequentialGroup()
                        .addComponent(ten5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnelSP5Layout.createSequentialGroup()
                        .addGroup(pnelSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnelSP5Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SlKho5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(pnelSP5Layout.createSequentialGroup()
                                .addGroup(pnelSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnelSP5Layout.createSequentialGroup()
                                        .addComponent(jLabel30)
                                        .addGap(26, 26, 26)
                                        .addComponent(SnerSl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnelSP5Layout.createSequentialGroup()
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(gia5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(pnelSP5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnThem5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnelSP5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hinh5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        pnelSP5Layout.setVerticalGroup(
            pnelSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelSP5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ten5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(gia5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(SnerSl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnelSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(SlKho5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem5)
                .addContainerGap())
        );

        pnelSP6.setBackground(new java.awt.Color(255, 255, 255));
        pnelSP6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        hinh6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh6.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten6.setText("SON MÔI DẠNG LÌ ROUGE PUR COUTURE THE SLIM 2");

        jLabel32.setText("Giá:");

        gia6.setForeground(new java.awt.Color(255, 51, 51));
        gia6.setText("1200000");

        jLabel33.setText("Số lượng");

        jLabel34.setText("Kho còn:");

        SlKho6.setText("39");

        btnThem6.setBackground(new java.awt.Color(204, 255, 204));
        btnThem6.setText("Thêm");
        btnThem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnelSP6Layout = new javax.swing.GroupLayout(pnelSP6);
        pnelSP6.setLayout(pnelSP6Layout);
        pnelSP6Layout.setHorizontalGroup(
            pnelSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelSP6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelSP6Layout.createSequentialGroup()
                        .addComponent(ten6, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnelSP6Layout.createSequentialGroup()
                        .addGroup(pnelSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnelSP6Layout.createSequentialGroup()
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SlKho6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(pnelSP6Layout.createSequentialGroup()
                                .addGroup(pnelSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnelSP6Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addGap(26, 26, 26)
                                        .addComponent(SnerSl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnelSP6Layout.createSequentialGroup()
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(gia6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(pnelSP6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnThem6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnelSP6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hinh6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        pnelSP6Layout.setVerticalGroup(
            pnelSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelSP6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ten6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(gia6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(SnerSl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnelSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(SlKho6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem6)
                .addContainerGap())
        );

        pnelSP7.setBackground(new java.awt.Color(255, 255, 255));
        pnelSP7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        hinh7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh7.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten7.setText("SON MÔI DẠNG LÌ ROUGE PUR COUTURE THE SLIM 2");

        jLabel35.setText("Giá:");

        gia7.setForeground(new java.awt.Color(255, 51, 51));
        gia7.setText("1200000");

        jLabel36.setText("Số lượng");

        jLabel37.setText("Kho còn:");

        SlKho7.setText("39");

        btnThem7.setBackground(new java.awt.Color(204, 255, 204));
        btnThem7.setText("Thêm");
        btnThem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnelSP7Layout = new javax.swing.GroupLayout(pnelSP7);
        pnelSP7.setLayout(pnelSP7Layout);
        pnelSP7Layout.setHorizontalGroup(
            pnelSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelSP7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelSP7Layout.createSequentialGroup()
                        .addComponent(ten7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnelSP7Layout.createSequentialGroup()
                        .addGroup(pnelSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnelSP7Layout.createSequentialGroup()
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SlKho7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(pnelSP7Layout.createSequentialGroup()
                                .addGroup(pnelSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnelSP7Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addGap(26, 26, 26)
                                        .addComponent(SnerSl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnelSP7Layout.createSequentialGroup()
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(gia7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(pnelSP7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnThem7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnelSP7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hinh7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        pnelSP7Layout.setVerticalGroup(
            pnelSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelSP7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ten7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(gia7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(SnerSl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnelSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(SlKho7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem7)
                .addContainerGap())
        );

        pnelSP8.setBackground(new java.awt.Color(255, 255, 255));
        pnelSP8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        hinh8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh8.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten8.setText("SON MÔI DẠNG LÌ ROUGE PUR COUTURE THE SLIM 2");

        jLabel38.setText("Giá:");

        gia8.setForeground(new java.awt.Color(255, 51, 51));
        gia8.setText("1200000");

        jLabel39.setText("Số lượng");

        jLabel40.setText("Kho còn:");

        SlKho8.setText("39");

        btnThem8.setBackground(new java.awt.Color(204, 255, 204));
        btnThem8.setText("Thêm");
        btnThem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnelSP8Layout = new javax.swing.GroupLayout(pnelSP8);
        pnelSP8.setLayout(pnelSP8Layout);
        pnelSP8Layout.setHorizontalGroup(
            pnelSP8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelSP8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelSP8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelSP8Layout.createSequentialGroup()
                        .addComponent(ten8, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnelSP8Layout.createSequentialGroup()
                        .addGroup(pnelSP8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnelSP8Layout.createSequentialGroup()
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SlKho8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(pnelSP8Layout.createSequentialGroup()
                                .addGroup(pnelSP8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnelSP8Layout.createSequentialGroup()
                                        .addComponent(jLabel39)
                                        .addGap(26, 26, 26)
                                        .addComponent(SnerSl8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnelSP8Layout.createSequentialGroup()
                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(gia8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(pnelSP8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnThem8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnelSP8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hinh8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        pnelSP8Layout.setVerticalGroup(
            pnelSP8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelSP8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ten8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelSP8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(gia8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelSP8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(SnerSl8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnelSP8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(SlKho8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem8)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        btnLastSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/last.png"))); // NOI18N
        btnLastSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastSPActionPerformed(evt);
            }
        });

        btnPrevSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/prev.png"))); // NOI18N
        btnPrevSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevSPActionPerformed(evt);
            }
        });

        btnFirstSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/first.png"))); // NOI18N
        btnFirstSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstSPActionPerformed(evt);
            }
        });

        btnNextSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/next.png"))); // NOI18N
        btnNextSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnFirstSP)
                .addGap(18, 18, 18)
                .addComponent(btnNextSP)
                .addGap(18, 18, 18)
                .addComponent(btnPrevSP)
                .addGap(18, 18, 18)
                .addComponent(btnLastSP)
                .addGap(14, 14, 14))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNextSP)
                    .addComponent(btnFirstSP)
                    .addComponent(btnPrevSP)
                    .addComponent(btnLastSP))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnelSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnelSP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(pnelSP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnelSP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnelSP5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnelSP6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnelSP7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnelSP8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnelSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnelSP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnelSP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pnelSP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnelSP5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelSP6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelSP7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelSP8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabs.addTab("Chi tiết đơn hàng", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(0, 3, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tabs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLastDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastDHActionPerformed
        this.Last();
    }//GEN-LAST:event_btnLastDHActionPerformed

    private void btnPrevDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevDHActionPerformed
        this.Prev();
    }//GEN-LAST:event_btnPrevDHActionPerformed

    private void btnNextDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextDHActionPerformed
        this.Next();
    }//GEN-LAST:event_btnNextDHActionPerformed

    private void btnFirstDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstDHActionPerformed
        this.First();
    }//GEN-LAST:event_btnFirstDHActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Thread clock = new Thread(this);
        clock.start();
    }//GEN-LAST:event_formWindowOpened

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnLastSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastSPActionPerformed
        this.LastSP();
    }//GEN-LAST:event_btnLastSPActionPerformed

    private void btnPrevSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevSPActionPerformed
        this.PrevSP();
    }//GEN-LAST:event_btnPrevSPActionPerformed

    private void btnNextSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextSPActionPerformed
        this.NextSP();
    }//GEN-LAST:event_btnNextSPActionPerformed

    private void btnFirstSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstSPActionPerformed
        this.FirstSP();
    }//GEN-LAST:event_btnFirstSPActionPerformed

    private void pnel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnel1MousePressed
        if (evt.getClickCount() == 2) {
            this.edit(Integer.parseInt(lblMaDH1.getText()));
            maDH = Integer.parseInt(lblMaDH1.getText());
        }
    }//GEN-LAST:event_pnel1MousePressed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        KhoDAO KHOdao = new KhoDAO();
        SanPham sp = KHOdao.selectByKeyword(ten1.getText());
        this.AddToTable(sp, maDH, SnerSl1, SlKho1);
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnThem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem8ActionPerformed
        KhoDAO KHOdao = new KhoDAO();
        SanPham sp = KHOdao.selectByKeyword(ten8.getText());
        this.AddToTable(sp, maDH, SnerSl8, SlKho8);
    }//GEN-LAST:event_btnThem8ActionPerformed

    private void pnel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnel3MousePressed
        if (evt.getClickCount() == 2) {
            this.edit(Integer.parseInt(lblMaDH3.getText()));
            maDH = Integer.parseInt(lblMaDH3.getText());
        }
    }//GEN-LAST:event_pnel3MousePressed

    private void pnel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnel2MousePressed
        if (evt.getClickCount() == 2) {
            this.edit(Integer.parseInt(lblMaDH2.getText()));
            maDH = Integer.parseInt(lblMaDH2.getText());
        }
    }//GEN-LAST:event_pnel2MousePressed

    private void pnel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnel4MousePressed
        if (evt.getClickCount() == 2) {
            this.edit(Integer.parseInt(lblMaDH4.getText()));
            maDH = Integer.parseInt(lblMaDH4.getText());
        }
    }//GEN-LAST:event_pnel4MousePressed

    private void pnel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnel5MousePressed
        if (evt.getClickCount() == 2) {
            this.edit(Integer.parseInt(lblMaDH5.getText()));
            maDH = Integer.parseInt(lblMaDH5.getText());
        }
    }//GEN-LAST:event_pnel5MousePressed

    private void pnel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnel6MousePressed
        if (evt.getClickCount() == 2) {
            this.edit(Integer.parseInt(lblMaDH6.getText()));
            maDH = Integer.parseInt(lblMaDH6.getText());
        }
    }//GEN-LAST:event_pnel6MousePressed

    private void pnel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnel7MousePressed
        if (evt.getClickCount() == 2) {
            this.edit(Integer.parseInt(lblMaDH7.getText()));
            maDH = Integer.parseInt(lblMaDH7.getText());
        }
    }//GEN-LAST:event_pnel7MousePressed

    private void pnel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnel8MousePressed
        if (evt.getClickCount() == 2) {
            this.edit(Integer.parseInt(lblMaDH8.getText()));
            maDH = Integer.parseInt(lblMaDH8.getText());
        }
    }//GEN-LAST:event_pnel8MousePressed

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        KhoDAO KHOdao = new KhoDAO();
        SanPham sp = KHOdao.selectByKeyword(ten2.getText());
        this.AddToTable(sp, maDH, SnerSl2, SlKho2);
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void btnThem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem3ActionPerformed
        KhoDAO KHOdao = new KhoDAO();
        SanPham sp = KHOdao.selectByKeyword(ten3.getText());
        this.AddToTable(sp, maDH, SnerSl3, SlKho3);
    }//GEN-LAST:event_btnThem3ActionPerformed

    private void btnThem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem4ActionPerformed
        KhoDAO KHOdao = new KhoDAO();
        SanPham sp = KHOdao.selectByKeyword(ten4.getText());
        this.AddToTable(sp, maDH, SnerSl4, SlKho4);
    }//GEN-LAST:event_btnThem4ActionPerformed

    private void btnThem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem5ActionPerformed
        KhoDAO KHOdao = new KhoDAO();
        SanPham sp = KHOdao.selectByKeyword(ten5.getText());
        this.AddToTable(sp, maDH, SnerSl5, SlKho5);
    }//GEN-LAST:event_btnThem5ActionPerformed

    private void btnThem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem6ActionPerformed
        KhoDAO KHOdao = new KhoDAO();
        SanPham sp = KHOdao.selectByKeyword(ten6.getText());
        this.AddToTable(sp, maDH, SnerSl6, SlKho6);
    }//GEN-LAST:event_btnThem6ActionPerformed

    private void btnThem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem7ActionPerformed
        KhoDAO KHOdao = new KhoDAO();
        SanPham sp = KHOdao.selectByKeyword(ten7.getText());
        this.AddToTable(sp, maDH, SnerSl7, SlKho7);
    }//GEN-LAST:event_btnThem7ActionPerformed

    private void btnDeleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRowActionPerformed
        this.deleteRow();
    }//GEN-LAST:event_btnDeleteRowActionPerformed

    private void btnInsertDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertDonHangActionPerformed
        this.ThanhToan();
    }//GEN-LAST:event_btnInsertDonHangActionPerformed

    private void txtKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhuyenMaiActionPerformed
        double sale = Double.parseDouble(txtKhuyenMai.getText());
        System.out.println(sale);
        DonHang dh = getForm();
        if (dh != null) {
            this.setForm(dh);
        }
    }//GEN-LAST:event_txtKhuyenMaiActionPerformed

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

    private void tabsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabsMouseClicked
        model.setRowCount(0);
        tblList.setModel(model);
        clearForm();
    }//GEN-LAST:event_tabsMouseClicked

    private void tbnXoaDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnXoaDHActionPerformed
        this.DeleteDonHang(maDH);
    }//GEN-LAST:event_tbnXoaDHActionPerformed

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
            java.util.logging.Logger.getLogger(QLDonHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLDonHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLDonHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLDonHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLDonHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JButton btnDeleteRow;
    private javax.swing.JButton btnFirstDH;
    private javax.swing.JButton btnFirstSP;
    private javax.swing.JButton btnInsertDonHang;
    private javax.swing.JButton btnLastDH;
    private javax.swing.JButton btnLastSP;
    private javax.swing.JButton btnNextDH;
    private javax.swing.JButton btnNextSP;
    private javax.swing.JButton btnPrevDH;
    private javax.swing.JButton btnPrevSP;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
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
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbl;
    private javax.swing.JPanel lblDonHang;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblMaDH1;
    private javax.swing.JLabel lblMaDH2;
    private javax.swing.JLabel lblMaDH3;
    private javax.swing.JLabel lblMaDH4;
    private javax.swing.JLabel lblMaDH5;
    private javax.swing.JLabel lblMaDH6;
    private javax.swing.JLabel lblMaDH7;
    private javax.swing.JLabel lblMaDH8;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblNguoiTao;
    private javax.swing.JLabel lblNguoiTao1;
    private javax.swing.JLabel lblNguoiTao2;
    private javax.swing.JLabel lblNguoiTao3;
    private javax.swing.JLabel lblNguoiTao4;
    private javax.swing.JLabel lblNguoiTao5;
    private javax.swing.JLabel lblNguoiTao6;
    private javax.swing.JLabel lblNguoiTao7;
    private javax.swing.JLabel lblNguoiTao8;
    private javax.swing.JLabel lblSale1;
    private javax.swing.JLabel lblSale2;
    private javax.swing.JLabel lblSale3;
    private javax.swing.JLabel lblSale4;
    private javax.swing.JLabel lblSale5;
    private javax.swing.JLabel lblSale6;
    private javax.swing.JLabel lblSale7;
    private javax.swing.JLabel lblSale8;
    private javax.swing.JLabel lblSdtKhachHang;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblThanhTien1;
    private javax.swing.JLabel lblThanhTien2;
    private javax.swing.JLabel lblThanhTien3;
    private javax.swing.JLabel lblThanhTien4;
    private javax.swing.JLabel lblThanhTien5;
    private javax.swing.JLabel lblThanhTien6;
    private javax.swing.JLabel lblThanhTien7;
    private javax.swing.JLabel lblThanhTien8;
    private javax.swing.JLabel lblThoiGian1;
    private javax.swing.JLabel lblThoiGian2;
    private javax.swing.JLabel lblThoiGian3;
    private javax.swing.JLabel lblThoiGian4;
    private javax.swing.JLabel lblThoiGian5;
    private javax.swing.JLabel lblThoiGian6;
    private javax.swing.JLabel lblThoiGian7;
    private javax.swing.JLabel lblThoiGian8;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnel1;
    private javax.swing.JPanel pnel2;
    private javax.swing.JPanel pnel3;
    private javax.swing.JPanel pnel4;
    private javax.swing.JPanel pnel5;
    private javax.swing.JPanel pnel6;
    private javax.swing.JPanel pnel7;
    private javax.swing.JPanel pnel8;
    private javax.swing.JPanel pnelSP1;
    private javax.swing.JPanel pnelSP2;
    private javax.swing.JPanel pnelSP3;
    private javax.swing.JPanel pnelSP4;
    private javax.swing.JPanel pnelSP5;
    private javax.swing.JPanel pnelSP6;
    private javax.swing.JPanel pnelSP7;
    private javax.swing.JPanel pnelSP8;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblList;
    private javax.swing.JButton tbnXoaDH;
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
    // End of variables declaration//GEN-END:variables
}
