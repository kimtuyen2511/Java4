//HOÀN THÀNH 
package qlsmp.FunctionalInterface;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import qldaotao.utis.Auth;
import qldaotao.utis.XImage;
import static qldaotao.utis.XImage.getApplcon;
import qlsmp.DAO.SanPhamDAO;
import qlsmp.MainInterface.FromMain;
import qlsmp.Model.SanPham;

/**
 *
 * @author My Laptop
 */
public class QLSanPham extends javax.swing.JDialog implements Runnable {

    public List<SanPham> list = new ArrayList<>();
    public SanPhamDAO dao = new SanPhamDAO();
    public int page = 1;
    private int pageTD = 1;
    private int pageCSD = 1;
    private int pageNH = 1;

    private boolean indexInsert = false;

    /**
     * Creates new form QLSanPham
     */
    public QLSanPham(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    void init() {
        this.setIconImage(XImage.getApplcon());
        setLocationRelativeTo(null);
        setTitle("Quản lý sản phẩm");
        this.getContentPane().setBackground(Color.WHITE);
        this.FillPageSP();
        this.clearForm();
        this.updateStatus();
    }

    private void updateStatus() {
        btnThem.setEnabled(indexInsert);
        btnSua.setEnabled(!indexInsert);
        btnXoa.setEnabled(!indexInsert);
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

    public void clearForm() {
        txtSearch.setText(null);
        hinhMD.setIcon(null);
        SanPham sp = new SanPham();
        sp.setLoaiSP(1);
        sp.setHSD("1 năm kể từ ngày sản xuất");
        sp.setHinh("");
        this.setForm(sp);
        indexInsert = true;
        this.updateStatus();
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

    public void FillSP(SanPham sp, JPanel pnel, JLabel hinh, JLabel ten, JLabel gia, JLabel thuongHieu) {
        pnel.setVisible(true);
        setHinhAnh(sp.getHinh(), hinh);
        ten.setText(sp.getTensp());
        gia.setText(sp.getGia() + "");
        thuongHieu.setText(sp.getThuongHieu());
    }

    public void FillPageSP() {
        int vitri = 0;
        list = dao.selectAll();
        for (SanPham sp : list) {
            //System.out.println(vitri);
            vitri++;

            if (vitri == this.page * 8 - 7) {
                FillSP(sp, pnel1, hinh1, ten1, gia1, thuongHieu1);
            } else if (vitri == this.page * 8 - 6) {
                FillSP(sp, pnel2, hinh2, ten2, gia2, thuongHieu2);
            } else if (vitri == this.page * 8 - 5) {
                FillSP(sp, pnel3, hinh3, ten3, gia3, thuongHieu3);
            } else if (vitri == this.page * 8 - 4) {
                FillSP(sp, pnel4, hinh4, ten4, gia4, thuongHieu4);
            } else if (vitri == this.page * 8 - 3) {
                FillSP(sp, pnel5, hinh5, ten5, gia5, thuongHieu5);
            } else if (vitri == this.page * 8 - 2) {
                FillSP(sp, pnel6, hinh6, ten6, gia6, thuongHieu6);
            } else if (vitri == this.page * 8 - 1) {
                FillSP(sp, pnel7, hinh7, ten7, gia7, thuongHieu7);
            } else if (vitri == this.page * 8) {
                FillSP(sp, pnel8, hinh8, ten8, gia8, thuongHieu8);
            }
        }
    }

    public void FillPageTrangDiem() {
        int vitri = 0;
        list = dao.getAllByLoai(1);
        for (SanPham sp : list) {
            //System.out.println(vitri);
            vitri++;
            if (vitri == this.pageTD * 8 - 7) {
                FillSP(sp, pnelTD1, hinhTD1, tenTD1, giaTD1, thuongHieuTD1);
            } else if (vitri == this.pageTD * 8 - 6) {
                FillSP(sp, pnelTD2, hinhTD2, tenTD2, giaTD2, thuongHieuTD2);
            } else if (vitri == this.pageTD * 8 - 5) {
                FillSP(sp, pnelTD3, hinhTD3, tenTD3, giaTD3, thuongHieuTD3);
            } else if (vitri == this.pageTD * 8 - 4) {
                FillSP(sp, pnelTD4, hinhTD4, tenTD4, giaTD4, thuongHieuTD4);
            } else if (vitri == this.pageTD * 8 - 3) {
                FillSP(sp, pnelTD5, hinhTD5, tenTD5, giaTD5, thuongHieuTD5);
            } else if (vitri == this.pageTD * 8 - 2) {
                FillSP(sp, pnelTD6, hinhTD6, tenTD6, giaTD6, thuongHieuTD6);
            } else if (vitri == this.pageTD * 8 - 1) {
                FillSP(sp, pnelTD7, hinhTD7, tenTD7, giaTD7, thuongHieuTD7);
            } else if (vitri == this.pageTD * 8) {
                FillSP(sp, pnelTD8, hinhTD8, tenTD8, giaTD8, thuongHieuTD8);
            }
        }
    }

    public void FillPageChamSocDa() {
        int vitri = 0;
        list = dao.getAllByLoai(2);
        for (SanPham sp : list) {
            // System.out.println(vitri);
            vitri++;
            if (vitri == this.pageCSD * 8 - 7) {
                FillSP(sp, pnelCSD1, hinhCSD1, tenCSD1, giaCSD1, thuongHieuCSD1);
            } else if (vitri == this.pageCSD * 8 - 6) {
                FillSP(sp, pnelCSD2, hinhCSD2, tenCSD2, giaCSD2, thuongHieuCSD2);
            } else if (vitri == this.pageCSD * 8 - 5) {
                FillSP(sp, pnelCSD3, hinhCSD3, tenCSD3, giaCSD3, thuongHieuCSD3);
            } else if (vitri == this.pageCSD * 8 - 4) {
                FillSP(sp, pnelCSD4, hinhCSD4, tenCSD4, giaCSD4, thuongHieuCSD4);
            } else if (vitri == this.pageCSD * 8 - 3) {
                FillSP(sp, pnelCSD5, hinhCSD5, tenCSD5, giaCSD5, thuongHieuCSD5);
            } else if (vitri == this.pageCSD * 8 - 2) {
                FillSP(sp, pnelCSD6, hinhCSD6, tenCSD6, giaCSD6, thuongHieuCSD6);
            } else if (vitri == this.pageCSD * 8 - 1) {
                FillSP(sp, pnelCSD7, hinhCSD7, tenCSD7, giaCSD7, thuongHieuCSD7);
            } else if (vitri == this.pageCSD * 8) {
                FillSP(sp, pnelCSD8, hinhCSD8, tenCSD8, giaCSD8, thuongHieuCSD8);
            }
        }
    }

    public void FillPageNuocHoa() {
        int vitri = 0;
        list = dao.getAllByLoai(3);
        for (SanPham sp : list) {
            // System.out.println(vitri);
            vitri++;
            if (vitri == this.pageNH * 8 - 7) {
                FillSP(sp, pnelNH1, hinhNH1, tenNH1, giaNH1, thuongHieuNH1);
            } else if (vitri == this.pageNH * 8 - 6) {
                FillSP(sp, pnelNH2, hinhNH2, tenNH2, giaNH2, thuongHieuNH2);
            } else if (vitri == this.pageNH * 8 - 5) {
                FillSP(sp, pnelNH3, hinhNH3, tenNH3, giaNH3, thuongHieuNH3);
            } else if (vitri == this.pageNH * 8 - 4) {
                FillSP(sp, pnelNH4, hinhNH4, tenNH4, giaNH4, thuongHieuNH4);
            } else if (vitri == this.pageNH * 8 - 3) {
                FillSP(sp, pnelNH5, hinhNH5, tenNH5, giaNH5, thuongHieuNH5);
            } else if (vitri == this.pageNH * 8 - 2) {
                FillSP(sp, pnelNH6, hinhNH6, tenNH6, giaNH6, thuongHieuNH6);
            } else if (vitri == this.pageNH * 8 - 1) {
                FillSP(sp, pnelNH7, hinhNH7, tenNH7, giaNH7, thuongHieuNH7);
            } else if (vitri == this.pageNH * 8) {
                FillSP(sp, pnelNH8, hinhNH8, tenNH8, giaNH8, thuongHieuNH8);
            }
        }
    }

    public void setForm(SanPham sp) {
        txtTen.setText(sp.getTensp());
        txtGia.setText(sp.getGia() + "");
        txtThuongHieu.setText(sp.getThuongHieu());
        switch (sp.getLoaiSP()) {
            case 1 ->
                cbxLoaiSP.setSelectedItem("Trang điểm");
            case 2 ->
                cbxLoaiSP.setSelectedItem("Chăm sóc da");
            case 3 ->
                cbxLoaiSP.setSelectedItem("Nước hoa");
            default -> {
            }
        }
        System.out.println(sp.getLoaiSP());
        if (sp.getHSD().equalsIgnoreCase("1 năm kể từ ngày sản xuất")) {
            cbxHSD.setSelectedIndex(0);
        } else if (sp.getHSD().equalsIgnoreCase("2 năm kể từ ngày sản xuất")) {
            cbxHSD.setSelectedIndex(1);
        } else {
            cbxHSD.setSelectedIndex(2);
        }
        txtCongDung.setText(sp.getCongDung());
        txtThanhPhan.setText(sp.getThanhPhan());
        if (sp.getHinh() != null) {
            setHinhAnh(sp.getHinh(), hinhMD);
        } else {
            System.out.println("Lỗi ảnh");
        }
        indexInsert = false;
        this.updateStatus();
    }

    public boolean check() {
        String ten = txtTen.getText();
        String gia = txtGia.getText();
        String congDung = txtCongDung.getText();
        String thanhPhan = txtThanhPhan.getText();
        String thuongHieu = txtThuongHieu.getText();

        if (ten.isBlank() || gia.isBlank() || congDung.isBlank() || thanhPhan.isBlank() || thuongHieu.isBlank()) {
            JOptionPane.showMessageDialog(this, "Thông tin sản phẩm chưa đầy đủ");
            return false;
        }
        try {
            double doublegia = Double.parseDouble(txtGia.getText());
            txtGia.setForeground(Color.black);
        } catch (Exception e) {
            txtGia.setForeground(Color.red);
            return false;
        }
        return true;
    }

    public SanPham getForm() {
        SanPham sp = new SanPham();
        sp.setTensp(txtTen.getText());
        sp.setGia(Double.parseDouble(txtGia.getText()));
        sp.setThuongHieu(txtThuongHieu.getText());
        if (cbxLoaiSP.getSelectedIndex() == 0) {
            sp.setLoaiSP(1);
        } else if (cbxLoaiSP.getSelectedIndex() == 1) {
            sp.setLoaiSP(2);
        } else {
            sp.setLoaiSP(3);
        }
        if (cbxHSD.getSelectedIndex() == 0) {
            sp.setHSD("1 năm kể từ ngày sản xuất");
        } else if (cbxLoaiSP.getSelectedIndex() == 1) {
            sp.setHSD("2 năm kể từ ngày sản xuất");
        } else {
            sp.setHSD("3 năm kể từ ngày sản xuất");
        }
        sp.setCongDung(txtCongDung.getText());
        sp.setThanhPhan(txtThanhPhan.getText());
        sp.setHinh(hinhMD.getToolTipText());
        System.out.println(sp.getHinh());
        if (sp.getHinh() == null) {
            sp.setHinh("");
        }
        return sp;
    }

    private void chonAnh() {
        if (FileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = FileChooser.getSelectedFile();
            XImage.save(file); // lưu hình và thư mục logos
            ImageIcon icon = XImage.read(file.getName());
            Image image = icon.getImage(); //trả về hình cho icon
            Image newImg = image.getScaledInstance(190, 152, java.awt.Image.SCALE_SMOOTH); //TẠO MỘT PHIÊN BẢN THU NHỎ, CÓ THỂ ĐIỀU CHỈNH KÍCH THƯỚC
            icon = new ImageIcon(newImg);
            hinhMD.setIcon(icon);
            hinhMD.setToolTipText(file.getName());
        }
    }

    public void update() {
        if (check()) {
            SanPham sp = getForm();
            SanPham spid = dao.selectByKeyword(txtTen.getText());
            sp.setMaSP(spid.getMaSP());
            dao.update(sp);
            JOptionPane.showMessageDialog(this, "Update sản phẩm thành công!");
            this.FillPageSP();
            this.FillPageTrangDiem();
            this.FillPageChamSocDa();
            this.FillPageNuocHoa();
        }
    }

    public void insert() {
        if (check()) {
            dao.insert(getForm());
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
            this.FillPageSP();
            this.FillPageTrangDiem();
            this.FillPageChamSocDa();
            this.FillPageNuocHoa();
        }
    }

    public void delete() {
        SanPham spid = dao.selectByKeyword(txtTen.getText());
        dao.delete(spid.getMaSP());
        JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!");
        this.FillPageSP();
        this.FillPageTrangDiem();
        this.FillPageChamSocDa();
        this.FillPageNuocHoa();
    }

    public void Home() {
        if (Auth.isLogin()) {
            this.dispose();
            FromMain main = new FromMain();
            main.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FileChooser = new javax.swing.JFileChooser();
        PageTrangDiem = new javax.swing.JTabbedPane();
        PanelDSSP = new javax.swing.JPanel();
        pnel2 = new javax.swing.JPanel();
        hinh2 = new javax.swing.JLabel();
        ten2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        gia2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        thuongHieu2 = new javax.swing.JLabel();
        pnel1 = new javax.swing.JPanel();
        hinh1 = new javax.swing.JLabel();
        ten1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        gia1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        thuongHieu1 = new javax.swing.JLabel();
        pnel3 = new javax.swing.JPanel();
        hinh3 = new javax.swing.JLabel();
        ten3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        gia3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        thuongHieu3 = new javax.swing.JLabel();
        pnel4 = new javax.swing.JPanel();
        hinh4 = new javax.swing.JLabel();
        ten4 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        gia4 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        thuongHieu4 = new javax.swing.JLabel();
        pnel8 = new javax.swing.JPanel();
        hinh8 = new javax.swing.JLabel();
        ten8 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        gia8 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        thuongHieu8 = new javax.swing.JLabel();
        pnel5 = new javax.swing.JPanel();
        hinh5 = new javax.swing.JLabel();
        ten5 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        gia5 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        thuongHieu5 = new javax.swing.JLabel();
        pnel6 = new javax.swing.JPanel();
        hinh6 = new javax.swing.JLabel();
        ten6 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        gia6 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        thuongHieu6 = new javax.swing.JLabel();
        pnel7 = new javax.swing.JPanel();
        hinh7 = new javax.swing.JLabel();
        ten7 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        gia7 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        thuongHieu7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnNextDS = new javax.swing.JButton();
        btnPrevDS = new javax.swing.JButton();
        btnLastDS = new javax.swing.JButton();
        btnFirstDS = new javax.swing.JButton();
        PanelTrangDiem = new javax.swing.JPanel();
        pnelTD1 = new javax.swing.JPanel();
        hinhTD1 = new javax.swing.JLabel();
        tenTD1 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        giaTD1 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        thuongHieuTD1 = new javax.swing.JLabel();
        pnelTD2 = new javax.swing.JPanel();
        hinhTD2 = new javax.swing.JLabel();
        tenTD2 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        giaTD2 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        thuongHieuTD2 = new javax.swing.JLabel();
        pnelTD3 = new javax.swing.JPanel();
        hinhTD3 = new javax.swing.JLabel();
        tenTD3 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        giaTD3 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        thuongHieuTD3 = new javax.swing.JLabel();
        pnelTD4 = new javax.swing.JPanel();
        hinhTD4 = new javax.swing.JLabel();
        tenTD4 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        giaTD4 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        thuongHieuTD4 = new javax.swing.JLabel();
        pnelTD5 = new javax.swing.JPanel();
        hinhTD5 = new javax.swing.JLabel();
        tenTD5 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        giaTD5 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        thuongHieuTD5 = new javax.swing.JLabel();
        pnelTD6 = new javax.swing.JPanel();
        hinhTD6 = new javax.swing.JLabel();
        tenTD6 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        giaTD6 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        thuongHieuTD6 = new javax.swing.JLabel();
        pnelTD7 = new javax.swing.JPanel();
        hinhTD7 = new javax.swing.JLabel();
        tenTD7 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        giaTD7 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        thuongHieuTD7 = new javax.swing.JLabel();
        pnelTD8 = new javax.swing.JPanel();
        hinhTD8 = new javax.swing.JLabel();
        tenTD8 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        giaTD8 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        thuongHieuTD8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnFirstTD = new javax.swing.JButton();
        btnNextTD = new javax.swing.JButton();
        btnPrevTD = new javax.swing.JButton();
        btnLastTD = new javax.swing.JButton();
        PanelChamSocDa = new javax.swing.JPanel();
        pnelCSD1 = new javax.swing.JPanel();
        hinhCSD1 = new javax.swing.JLabel();
        tenCSD1 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        giaCSD1 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        thuongHieuCSD1 = new javax.swing.JLabel();
        pnelCSD2 = new javax.swing.JPanel();
        hinhCSD2 = new javax.swing.JLabel();
        tenCSD2 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        giaCSD2 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        thuongHieuCSD2 = new javax.swing.JLabel();
        pnelCSD3 = new javax.swing.JPanel();
        hinhCSD3 = new javax.swing.JLabel();
        tenCSD3 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        giaCSD3 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        thuongHieuCSD3 = new javax.swing.JLabel();
        pnelCSD4 = new javax.swing.JPanel();
        hinhCSD4 = new javax.swing.JLabel();
        tenCSD4 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        giaCSD4 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        thuongHieuCSD4 = new javax.swing.JLabel();
        pnelCSD8 = new javax.swing.JPanel();
        hinhCSD8 = new javax.swing.JLabel();
        tenCSD8 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        giaCSD8 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        thuongHieuCSD8 = new javax.swing.JLabel();
        pnelCSD5 = new javax.swing.JPanel();
        hinhCSD5 = new javax.swing.JLabel();
        tenCSD5 = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        giaCSD5 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        thuongHieuCSD5 = new javax.swing.JLabel();
        pnelCSD6 = new javax.swing.JPanel();
        hinhCSD6 = new javax.swing.JLabel();
        tenCSD6 = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        giaCSD6 = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        thuongHieuCSD6 = new javax.swing.JLabel();
        pnelCSD7 = new javax.swing.JPanel();
        hinhCSD7 = new javax.swing.JLabel();
        tenCSD7 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        giaCSD7 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        thuongHieuCSD7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnFirstCSD = new javax.swing.JButton();
        btnNextCSD = new javax.swing.JButton();
        btnPrevCSD = new javax.swing.JButton();
        btnLastCSD = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        pnelNH1 = new javax.swing.JPanel();
        hinhNH1 = new javax.swing.JLabel();
        tenNH1 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        giaNH1 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        thuongHieuNH1 = new javax.swing.JLabel();
        pnelNH2 = new javax.swing.JPanel();
        hinhNH2 = new javax.swing.JLabel();
        tenNH2 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        giaNH2 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        thuongHieuNH2 = new javax.swing.JLabel();
        pnelNH3 = new javax.swing.JPanel();
        hinhNH3 = new javax.swing.JLabel();
        tenNH3 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        giaNH3 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        thuongHieuNH3 = new javax.swing.JLabel();
        pnelNH4 = new javax.swing.JPanel();
        hinhNH4 = new javax.swing.JLabel();
        tenNH4 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        giaNH4 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        thuongHieuNH4 = new javax.swing.JLabel();
        pnelNH5 = new javax.swing.JPanel();
        hinhNH5 = new javax.swing.JLabel();
        tenNH5 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        giaNH5 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        thuongHieuNH5 = new javax.swing.JLabel();
        pnelNH6 = new javax.swing.JPanel();
        hinhNH6 = new javax.swing.JLabel();
        tenNH6 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        giaNH6 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        thuongHieuNH6 = new javax.swing.JLabel();
        pnelNH7 = new javax.swing.JPanel();
        hinhNH7 = new javax.swing.JLabel();
        tenNH7 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        giaNH7 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        thuongHieuNH7 = new javax.swing.JLabel();
        pnelNH8 = new javax.swing.JPanel();
        hinhNH8 = new javax.swing.JLabel();
        tenNH8 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        giaNH8 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        thuongHieuNH8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnFirstNH = new javax.swing.JButton();
        btnNextNH = new javax.swing.JButton();
        btnPrevNH = new javax.swing.JButton();
        btnLastNH = new javax.swing.JButton();
        PanelThongTinSP = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        hinhMD = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        txtThuongHieu = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCongDung = new javax.swing.JTextArea();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        txtTen = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtThanhPhan = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxLoaiSP = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cbxHSD = new javax.swing.JComboBox<>();
        btnClearForm = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblDongHo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Home = new javax.swing.JMenu();
        MnuQLSP = new javax.swing.JMenu();
        mniSanPham = new javax.swing.JMenuItem();
        mniNhanVien = new javax.swing.JMenuItem();
        mniKho = new javax.swing.JMenuItem();
        mniHoaDon = new javax.swing.JMenuItem();
        mniKhachHang = new javax.swing.JMenuItem();
        MnuThongKe = new javax.swing.JMenu();
        mniDoanhThu = new javax.swing.JMenuItem();
        mnuHeThong = new javax.swing.JMenu();
        mniDangXuat = new javax.swing.JMenuItem();
        mniKetThuc = new javax.swing.JMenuItem();
        mnuTroGiup = new javax.swing.JMenu();
        mniTroGiup = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        PageTrangDiem.setBackground(new java.awt.Color(255, 255, 255));
        PageTrangDiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PageTrangDiemMouseClicked(evt);
            }
        });

        PanelDSSP.setBackground(new java.awt.Color(255, 255, 255));

        pnel2.setBackground(new java.awt.Color(255, 255, 255));
        pnel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnel2MouseClicked(evt);
            }
        });

        hinh2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten2.setText("SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL");

        jLabel8.setText("Giá:");

        gia2.setForeground(new java.awt.Color(255, 51, 51));
        gia2.setText("1200000");

        jLabel10.setText("Thương hiệu: ");

        thuongHieu2.setText("YSL");

        javax.swing.GroupLayout pnel2Layout = new javax.swing.GroupLayout(pnel2);
        pnel2.setLayout(pnel2Layout);
        pnel2Layout.setHorizontalGroup(
            pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel2Layout.createSequentialGroup()
                .addGroup(pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinh2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ten2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gia2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnel2Layout.setVerticalGroup(
            pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(ten2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(gia2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(thuongHieu2))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnel1.setBackground(new java.awt.Color(255, 255, 255));
        pnel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnel1MouseClicked(evt);
            }
        });

        hinh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinh1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten1.setText("SD");

        jLabel14.setText("Giá:");

        gia1.setForeground(new java.awt.Color(255, 51, 51));
        gia1.setText("1200000");

        jLabel16.setText("Thương hiệu: ");

        thuongHieu1.setText("YSL");

        javax.swing.GroupLayout pnel1Layout = new javax.swing.GroupLayout(pnel1);
        pnel1.setLayout(pnel1Layout);
        pnel1Layout.setHorizontalGroup(
            pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ten1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnel1Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(hinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(thuongHieu1))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnel3.setBackground(new java.awt.Color(255, 255, 255));
        pnel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnel3MouseClicked(evt);
            }
        });

        hinh3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten3.setText("SON KEM LÌ TATOUAGE COUTURE VELVET CREAM");

        jLabel20.setText("Giá:");

        gia3.setForeground(new java.awt.Color(255, 51, 51));
        gia3.setText("1200000");

        jLabel22.setText("Thương hiệu: ");

        thuongHieu3.setText("YSL");

        javax.swing.GroupLayout pnel3Layout = new javax.swing.GroupLayout(pnel3);
        pnel3.setLayout(pnel3Layout);
        pnel3Layout.setHorizontalGroup(
            pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel3Layout.createSequentialGroup()
                .addGroup(pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinh3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ten3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel3Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieu3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnel3Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gia3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnel3Layout.setVerticalGroup(
            pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(ten3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(gia3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(thuongHieu3))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnel4.setBackground(new java.awt.Color(255, 255, 255));
        pnel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnel4MouseClicked(evt);
            }
        });

        hinh4.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten4.setText("SON THỎI SATIN LÌ THE BOLD");

        jLabel26.setText("Giá:");

        gia4.setForeground(new java.awt.Color(255, 51, 51));
        gia4.setText("1200000");

        jLabel28.setText("Thương hiệu: ");

        thuongHieu4.setText("YSL");

        javax.swing.GroupLayout pnel4Layout = new javax.swing.GroupLayout(pnel4);
        pnel4.setLayout(pnel4Layout);
        pnel4Layout.setHorizontalGroup(
            pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel4Layout.createSequentialGroup()
                .addGroup(pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinh4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ten4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel4Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieu4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnel4Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gia4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnel4Layout.setVerticalGroup(
            pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(ten4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(gia4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(thuongHieu4))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pnel8.setBackground(new java.awt.Color(255, 255, 255));
        pnel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnel8MouseClicked(evt);
            }
        });

        hinh8.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten8.setText("PHẤN PHỦ SOUFFLE DECLAT");

        jLabel50.setText("Giá:");

        gia8.setForeground(new java.awt.Color(255, 51, 51));
        gia8.setText("1900000");

        jLabel52.setText("Thương hiệu: ");

        thuongHieu8.setText("YSL");

        javax.swing.GroupLayout pnel8Layout = new javax.swing.GroupLayout(pnel8);
        pnel8.setLayout(pnel8Layout);
        pnel8Layout.setHorizontalGroup(
            pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel8Layout.createSequentialGroup()
                .addGroup(pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel8Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinh8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ten8, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel8Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieu8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnel8Layout.createSequentialGroup()
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gia8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnel8Layout.setVerticalGroup(
            pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(ten8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(gia8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(thuongHieu8))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnel5.setBackground(new java.awt.Color(255, 255, 255));
        pnel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnel5MouseClicked(evt);
            }
        });

        hinh5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten5.setText("BẢNG MẮT COUTURE COLOR CLUTCH");

        jLabel104.setText("Giá:");

        gia5.setForeground(new java.awt.Color(255, 51, 51));
        gia5.setText("3800000");

        jLabel106.setText("Thương hiệu: ");

        thuongHieu5.setText("YSL");

        javax.swing.GroupLayout pnel5Layout = new javax.swing.GroupLayout(pnel5);
        pnel5.setLayout(pnel5Layout);
        pnel5Layout.setHorizontalGroup(
            pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel5Layout.createSequentialGroup()
                .addGroup(pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinh5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ten5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel5Layout.createSequentialGroup()
                        .addComponent(jLabel106)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieu5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnel5Layout.createSequentialGroup()
                        .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gia5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnel5Layout.setVerticalGroup(
            pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(ten5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel104)
                    .addComponent(gia5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel106)
                    .addComponent(thuongHieu5))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnel6.setBackground(new java.awt.Color(255, 255, 255));
        pnel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnel6MouseClicked(evt);
            }
        });

        hinh6.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten6.setText("CUSHION EDP LAMÉ COLLECTOR");

        jLabel122.setText("Giá:");

        gia6.setForeground(new java.awt.Color(255, 51, 51));
        gia6.setText("1960000");

        jLabel124.setText("Thương hiệu: ");

        thuongHieu6.setText("YSL");

        javax.swing.GroupLayout pnel6Layout = new javax.swing.GroupLayout(pnel6);
        pnel6.setLayout(pnel6Layout);
        pnel6Layout.setHorizontalGroup(
            pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel6Layout.createSequentialGroup()
                .addGroup(pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel6Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinh6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ten6, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel6Layout.createSequentialGroup()
                        .addComponent(jLabel124)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieu6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnel6Layout.createSequentialGroup()
                        .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gia6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnel6Layout.setVerticalGroup(
            pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(ten6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel122)
                    .addComponent(gia6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel124)
                    .addComponent(thuongHieu6))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnel7.setBackground(new java.awt.Color(255, 255, 255));
        pnel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnel7MouseClicked(evt);
            }
        });

        hinh7.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        ten7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ten7.setText("MASCARA MVEFC WATERPROOF");

        jLabel128.setText("Giá:");

        gia7.setForeground(new java.awt.Color(255, 51, 51));
        gia7.setText("1400000");

        jLabel130.setText("Thương hiệu: ");

        thuongHieu7.setText("YSL");

        javax.swing.GroupLayout pnel7Layout = new javax.swing.GroupLayout(pnel7);
        pnel7.setLayout(pnel7Layout);
        pnel7Layout.setHorizontalGroup(
            pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel7Layout.createSequentialGroup()
                .addGroup(pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel7Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinh7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ten7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnel7Layout.createSequentialGroup()
                        .addComponent(jLabel130)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieu7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnel7Layout.createSequentialGroup()
                        .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gia7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnel7Layout.setVerticalGroup(
            pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinh7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(ten7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel128)
                    .addComponent(gia7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel130)
                    .addComponent(thuongHieu7))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnNextDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/next.png"))); // NOI18N
        btnNextDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextDSActionPerformed(evt);
            }
        });

        btnPrevDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/prev.png"))); // NOI18N
        btnPrevDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevDSActionPerformed(evt);
            }
        });

        btnLastDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/last.png"))); // NOI18N
        btnLastDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastDSActionPerformed(evt);
            }
        });

        btnFirstDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/first.png"))); // NOI18N
        btnFirstDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstDSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnFirstDS)
                .addGap(18, 18, 18)
                .addComponent(btnNextDS)
                .addGap(18, 18, 18)
                .addComponent(btnPrevDS)
                .addGap(18, 18, 18)
                .addComponent(btnLastDS)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFirstDS)
                    .addComponent(btnLastDS)
                    .addComponent(btnPrevDS)
                    .addComponent(btnNextDS))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelDSSPLayout = new javax.swing.GroupLayout(PanelDSSP);
        PanelDSSP.setLayout(PanelDSSPLayout);
        PanelDSSPLayout.setHorizontalGroup(
            PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDSSPLayout.createSequentialGroup()
                .addGroup(PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDSSPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelDSSPLayout.createSequentialGroup()
                                .addComponent(pnel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelDSSPLayout.createSequentialGroup()
                                .addComponent(pnel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDSSPLayout.createSequentialGroup()
                                .addComponent(pnel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelDSSPLayout.createSequentialGroup()
                                .addComponent(pnel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PanelDSSPLayout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        PanelDSSPLayout.setVerticalGroup(
            PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDSSPLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelDSSPLayout.createSequentialGroup()
                        .addGroup(PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pnel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pnel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pnel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pnel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        PageTrangDiem.addTab("Danh Sách", PanelDSSP);

        PanelTrangDiem.setBackground(new java.awt.Color(255, 255, 255));

        pnelTD1.setBackground(new java.awt.Color(255, 255, 255));
        pnelTD1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelTD1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelTD1MouseClicked(evt);
            }
        });

        hinhTD1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenTD1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenTD1.setText("SON MÔI DẠNG LÌ ROUGE PUR COUTURE THE SLIM 2");

        jLabel56.setText("Giá:");

        giaTD1.setForeground(new java.awt.Color(255, 51, 51));
        giaTD1.setText("1200000");

        jLabel58.setText("Thương hiệu: ");

        thuongHieuTD1.setText("YSL");

        javax.swing.GroupLayout pnelTD1Layout = new javax.swing.GroupLayout(pnelTD1);
        pnelTD1.setLayout(pnelTD1Layout);
        pnelTD1Layout.setHorizontalGroup(
            pnelTD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelTD1Layout.createSequentialGroup()
                .addGroup(pnelTD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelTD1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhTD1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelTD1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenTD1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelTD1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelTD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelTD1Layout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuTD1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelTD1Layout.createSequentialGroup()
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaTD1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelTD1Layout.setVerticalGroup(
            pnelTD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelTD1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhTD1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenTD1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelTD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(giaTD1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelTD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(thuongHieuTD1))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelTD2.setBackground(new java.awt.Color(255, 255, 255));
        pnelTD2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelTD2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelTD2MouseClicked(evt);
            }
        });

        hinhTD2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenTD2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenTD2.setText("SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL");

        jLabel62.setText("Giá:");

        giaTD2.setForeground(new java.awt.Color(255, 51, 51));
        giaTD2.setText("1200000");

        jLabel64.setText("Thương hiệu: ");

        thuongHieuTD2.setText("YSL");

        javax.swing.GroupLayout pnelTD2Layout = new javax.swing.GroupLayout(pnelTD2);
        pnelTD2.setLayout(pnelTD2Layout);
        pnelTD2Layout.setHorizontalGroup(
            pnelTD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelTD2Layout.createSequentialGroup()
                .addGroup(pnelTD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelTD2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhTD2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelTD2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenTD2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelTD2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelTD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelTD2Layout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuTD2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelTD2Layout.createSequentialGroup()
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaTD2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelTD2Layout.setVerticalGroup(
            pnelTD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelTD2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhTD2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenTD2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelTD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(giaTD2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelTD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(thuongHieuTD2))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelTD3.setBackground(new java.awt.Color(255, 255, 255));
        pnelTD3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelTD3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelTD3MouseClicked(evt);
            }
        });

        hinhTD3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenTD3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenTD3.setText("SON KEM LÌ TATOUAGE COUTURE VELVET CREAM");

        jLabel68.setText("Giá:");

        giaTD3.setForeground(new java.awt.Color(255, 51, 51));
        giaTD3.setText("1200000");

        jLabel70.setText("Thương hiệu: ");

        thuongHieuTD3.setBackground(new java.awt.Color(255, 255, 255));
        thuongHieuTD3.setText("YSL");

        javax.swing.GroupLayout pnelTD3Layout = new javax.swing.GroupLayout(pnelTD3);
        pnelTD3.setLayout(pnelTD3Layout);
        pnelTD3Layout.setHorizontalGroup(
            pnelTD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelTD3Layout.createSequentialGroup()
                .addGroup(pnelTD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelTD3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhTD3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelTD3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenTD3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelTD3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelTD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelTD3Layout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuTD3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelTD3Layout.createSequentialGroup()
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaTD3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelTD3Layout.setVerticalGroup(
            pnelTD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelTD3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhTD3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenTD3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelTD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(giaTD3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelTD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(thuongHieuTD3))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelTD4.setBackground(new java.awt.Color(255, 255, 255));
        pnelTD4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelTD4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelTD4MouseClicked(evt);
            }
        });

        hinhTD4.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenTD4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenTD4.setText("SON THỎI SATIN LÌ THE BOLD");

        jLabel74.setText("Giá:");

        giaTD4.setForeground(new java.awt.Color(255, 51, 51));
        giaTD4.setText("1200000");

        jLabel76.setText("Thương hiệu: ");

        thuongHieuTD4.setText("YSL");

        javax.swing.GroupLayout pnelTD4Layout = new javax.swing.GroupLayout(pnelTD4);
        pnelTD4.setLayout(pnelTD4Layout);
        pnelTD4Layout.setHorizontalGroup(
            pnelTD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelTD4Layout.createSequentialGroup()
                .addGroup(pnelTD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelTD4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhTD4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelTD4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenTD4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelTD4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelTD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelTD4Layout.createSequentialGroup()
                        .addComponent(jLabel76)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuTD4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelTD4Layout.createSequentialGroup()
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaTD4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelTD4Layout.setVerticalGroup(
            pnelTD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelTD4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhTD4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenTD4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelTD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(giaTD4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelTD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(thuongHieuTD4))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelTD5.setBackground(new java.awt.Color(255, 255, 255));
        pnelTD5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelTD5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelTD5MouseClicked(evt);
            }
        });

        hinhTD5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenTD5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenTD5.setText("BẢNG MẮT COUTURE COLOR CLUTCH");

        jLabel80.setText("Giá:");

        giaTD5.setForeground(new java.awt.Color(255, 51, 51));
        giaTD5.setText("3800000");

        jLabel82.setText("Thương hiệu: ");

        thuongHieuTD5.setText("YSL");

        javax.swing.GroupLayout pnelTD5Layout = new javax.swing.GroupLayout(pnelTD5);
        pnelTD5.setLayout(pnelTD5Layout);
        pnelTD5Layout.setHorizontalGroup(
            pnelTD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelTD5Layout.createSequentialGroup()
                .addGroup(pnelTD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelTD5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhTD5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelTD5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenTD5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelTD5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelTD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelTD5Layout.createSequentialGroup()
                        .addComponent(jLabel82)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuTD5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelTD5Layout.createSequentialGroup()
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaTD5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelTD5Layout.setVerticalGroup(
            pnelTD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelTD5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhTD5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenTD5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelTD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(giaTD5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelTD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(thuongHieuTD5))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelTD6.setBackground(new java.awt.Color(255, 255, 255));
        pnelTD6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelTD6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelTD6MouseClicked(evt);
            }
        });

        hinhTD6.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenTD6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenTD6.setText("CUSHION EDP LAMÉ COLLECTOR");

        jLabel86.setText("Giá:");

        giaTD6.setForeground(new java.awt.Color(255, 51, 51));
        giaTD6.setText("1960000");

        jLabel88.setText("Thương hiệu: ");

        thuongHieuTD6.setText("YSL");

        javax.swing.GroupLayout pnelTD6Layout = new javax.swing.GroupLayout(pnelTD6);
        pnelTD6.setLayout(pnelTD6Layout);
        pnelTD6Layout.setHorizontalGroup(
            pnelTD6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelTD6Layout.createSequentialGroup()
                .addGroup(pnelTD6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelTD6Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhTD6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelTD6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenTD6, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelTD6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelTD6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelTD6Layout.createSequentialGroup()
                        .addComponent(jLabel88)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuTD6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelTD6Layout.createSequentialGroup()
                        .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaTD6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelTD6Layout.setVerticalGroup(
            pnelTD6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelTD6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhTD6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenTD6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelTD6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(giaTD6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelTD6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88)
                    .addComponent(thuongHieuTD6))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelTD7.setBackground(new java.awt.Color(255, 255, 255));
        pnelTD7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelTD7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelTD7MouseClicked(evt);
            }
        });

        hinhTD7.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenTD7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenTD7.setText("MASCARA MVEFC WATERPROOF");

        jLabel92.setText("Giá:");

        giaTD7.setForeground(new java.awt.Color(255, 51, 51));
        giaTD7.setText("1400000");

        jLabel94.setText("Thương hiệu: ");

        thuongHieuTD7.setText("YSL");

        javax.swing.GroupLayout pnelTD7Layout = new javax.swing.GroupLayout(pnelTD7);
        pnelTD7.setLayout(pnelTD7Layout);
        pnelTD7Layout.setHorizontalGroup(
            pnelTD7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelTD7Layout.createSequentialGroup()
                .addGroup(pnelTD7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelTD7Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhTD7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelTD7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenTD7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelTD7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelTD7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelTD7Layout.createSequentialGroup()
                        .addComponent(jLabel94)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuTD7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelTD7Layout.createSequentialGroup()
                        .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaTD7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelTD7Layout.setVerticalGroup(
            pnelTD7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelTD7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhTD7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenTD7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelTD7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel92)
                    .addComponent(giaTD7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelTD7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94)
                    .addComponent(thuongHieuTD7))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelTD8.setBackground(new java.awt.Color(255, 255, 255));
        pnelTD8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelTD8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelTD8MouseClicked(evt);
            }
        });

        hinhTD8.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenTD8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenTD8.setText("PHẤN PHỦ SOUFFLE DECLAT");

        jLabel98.setText("Giá:");

        giaTD8.setForeground(new java.awt.Color(255, 51, 51));
        giaTD8.setText("1900000");

        jLabel100.setText("Thương hiệu: ");

        thuongHieuTD8.setText("YSL");

        javax.swing.GroupLayout pnelTD8Layout = new javax.swing.GroupLayout(pnelTD8);
        pnelTD8.setLayout(pnelTD8Layout);
        pnelTD8Layout.setHorizontalGroup(
            pnelTD8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelTD8Layout.createSequentialGroup()
                .addGroup(pnelTD8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelTD8Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhTD8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelTD8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenTD8, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelTD8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelTD8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelTD8Layout.createSequentialGroup()
                        .addComponent(jLabel100)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuTD8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelTD8Layout.createSequentialGroup()
                        .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaTD8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelTD8Layout.setVerticalGroup(
            pnelTD8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelTD8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhTD8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenTD8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelTD8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98)
                    .addComponent(giaTD8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelTD8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100)
                    .addComponent(thuongHieuTD8))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnFirstTD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/first.png"))); // NOI18N
        btnFirstTD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstTDActionPerformed(evt);
            }
        });

        btnNextTD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/next.png"))); // NOI18N
        btnNextTD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextTDActionPerformed(evt);
            }
        });

        btnPrevTD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/prev.png"))); // NOI18N
        btnPrevTD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevTDActionPerformed(evt);
            }
        });

        btnLastTD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/last.png"))); // NOI18N
        btnLastTD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastTDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnFirstTD)
                .addGap(18, 18, 18)
                .addComponent(btnNextTD)
                .addGap(18, 18, 18)
                .addComponent(btnPrevTD)
                .addGap(18, 18, 18)
                .addComponent(btnLastTD)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLastTD)
                    .addComponent(btnPrevTD)
                    .addComponent(btnNextTD)
                    .addComponent(btnFirstTD))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout PanelTrangDiemLayout = new javax.swing.GroupLayout(PanelTrangDiem);
        PanelTrangDiem.setLayout(PanelTrangDiemLayout);
        PanelTrangDiemLayout.setHorizontalGroup(
            PanelTrangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTrangDiemLayout.createSequentialGroup()
                .addGroup(PanelTrangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelTrangDiemLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelTrangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelTrangDiemLayout.createSequentialGroup()
                                .addComponent(pnelTD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnelTD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnelTD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnelTD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelTrangDiemLayout.createSequentialGroup()
                                .addComponent(pnelTD5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnelTD6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnelTD7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnelTD8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PanelTrangDiemLayout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        PanelTrangDiemLayout.setVerticalGroup(
            PanelTrangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTrangDiemLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(PanelTrangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnelTD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelTD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelTD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelTD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelTrangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnelTD5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelTD6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelTD7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelTD8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        PageTrangDiem.addTab("Trang điểm", PanelTrangDiem);

        PanelChamSocDa.setBackground(new java.awt.Color(255, 255, 255));

        pnelCSD1.setBackground(new java.awt.Color(255, 255, 255));
        pnelCSD1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelCSD1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelCSD1MouseClicked(evt);
            }
        });

        hinhCSD1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenCSD1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenCSD1.setText("KEM CHỐNG NẮNG PURE SHOTS AIRTHIN UV DEFENDER");

        jLabel134.setText("Giá:");

        giaCSD1.setForeground(new java.awt.Color(255, 51, 51));
        giaCSD1.setText("1800000");

        jLabel136.setText("Thương hiệu: ");

        thuongHieuCSD1.setText("YSL");

        javax.swing.GroupLayout pnelCSD1Layout = new javax.swing.GroupLayout(pnelCSD1);
        pnelCSD1.setLayout(pnelCSD1Layout);
        pnelCSD1Layout.setHorizontalGroup(
            pnelCSD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelCSD1Layout.createSequentialGroup()
                .addGroup(pnelCSD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelCSD1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhCSD1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelCSD1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenCSD1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelCSD1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelCSD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelCSD1Layout.createSequentialGroup()
                        .addComponent(jLabel136)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuCSD1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelCSD1Layout.createSequentialGroup()
                        .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaCSD1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelCSD1Layout.setVerticalGroup(
            pnelCSD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelCSD1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhCSD1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenCSD1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelCSD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel134)
                    .addComponent(giaCSD1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelCSD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel136)
                    .addComponent(thuongHieuCSD1))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelCSD2.setBackground(new java.awt.Color(255, 255, 255));
        pnelCSD2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelCSD2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelCSD2MouseClicked(evt);
            }
        });

        hinhCSD2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenCSD2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenCSD2.setText("KEM DƯỠNG ẨM PURE SHOTS PERFECT PLUMPER");

        jLabel140.setText("Giá:");

        giaCSD2.setForeground(new java.awt.Color(255, 51, 51));
        giaCSD2.setText("4000000");

        jLabel142.setText("Thương hiệu: ");

        thuongHieuCSD2.setText("YSL");

        javax.swing.GroupLayout pnelCSD2Layout = new javax.swing.GroupLayout(pnelCSD2);
        pnelCSD2.setLayout(pnelCSD2Layout);
        pnelCSD2Layout.setHorizontalGroup(
            pnelCSD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelCSD2Layout.createSequentialGroup()
                .addGroup(pnelCSD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelCSD2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhCSD2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelCSD2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenCSD2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelCSD2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelCSD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelCSD2Layout.createSequentialGroup()
                        .addComponent(jLabel142)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuCSD2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelCSD2Layout.createSequentialGroup()
                        .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaCSD2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelCSD2Layout.setVerticalGroup(
            pnelCSD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelCSD2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhCSD2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenCSD2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelCSD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel140)
                    .addComponent(giaCSD2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelCSD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel142)
                    .addComponent(thuongHieuCSD2))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelCSD3.setBackground(new java.awt.Color(255, 255, 255));
        pnelCSD3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelCSD3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelCSD3MouseClicked(evt);
            }
        });

        hinhCSD3.setBackground(new java.awt.Color(255, 255, 255));
        hinhCSD3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenCSD3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenCSD3.setText("PURE SHOTS LINES AWAY SERUM");

        jLabel146.setText("Giá:");

        giaCSD3.setForeground(new java.awt.Color(255, 51, 51));
        giaCSD3.setText("3280000");

        jLabel148.setText("Thương hiệu: ");

        thuongHieuCSD3.setText("YSL");

        javax.swing.GroupLayout pnelCSD3Layout = new javax.swing.GroupLayout(pnelCSD3);
        pnelCSD3.setLayout(pnelCSD3Layout);
        pnelCSD3Layout.setHorizontalGroup(
            pnelCSD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelCSD3Layout.createSequentialGroup()
                .addGroup(pnelCSD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelCSD3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhCSD3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelCSD3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenCSD3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelCSD3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelCSD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelCSD3Layout.createSequentialGroup()
                        .addComponent(jLabel148)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuCSD3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelCSD3Layout.createSequentialGroup()
                        .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaCSD3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelCSD3Layout.setVerticalGroup(
            pnelCSD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelCSD3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhCSD3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenCSD3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelCSD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel146)
                    .addComponent(giaCSD3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelCSD3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel148)
                    .addComponent(thuongHieuCSD3))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelCSD4.setBackground(new java.awt.Color(255, 255, 255));
        pnelCSD4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelCSD4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelCSD4MouseClicked(evt);
            }
        });

        hinhCSD4.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenCSD4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenCSD4.setText("Tinh chất chống lão hóa, tái tạo da The Ordinary “Buffet”");

        jLabel152.setText("Giá:");

        giaCSD4.setForeground(new java.awt.Color(255, 51, 51));
        giaCSD4.setText("810000");

        jLabel154.setText("Thương hiệu: ");

        thuongHieuCSD4.setText("Ordinary");

        javax.swing.GroupLayout pnelCSD4Layout = new javax.swing.GroupLayout(pnelCSD4);
        pnelCSD4.setLayout(pnelCSD4Layout);
        pnelCSD4Layout.setHorizontalGroup(
            pnelCSD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelCSD4Layout.createSequentialGroup()
                .addGroup(pnelCSD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelCSD4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhCSD4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelCSD4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenCSD4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelCSD4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelCSD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelCSD4Layout.createSequentialGroup()
                        .addComponent(jLabel154)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuCSD4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelCSD4Layout.createSequentialGroup()
                        .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaCSD4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelCSD4Layout.setVerticalGroup(
            pnelCSD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelCSD4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhCSD4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenCSD4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelCSD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel152)
                    .addComponent(giaCSD4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelCSD4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel154)
                    .addComponent(thuongHieuCSD4))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelCSD8.setBackground(new java.awt.Color(255, 255, 255));
        pnelCSD8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelCSD8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelCSD8MouseClicked(evt);
            }
        });

        hinhCSD8.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenCSD8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenCSD8.setText("Tinh chất tẩy tế bào chết & cấp nước The Ordinary");

        jLabel158.setText("Giá:");

        giaCSD8.setForeground(new java.awt.Color(255, 51, 51));
        giaCSD8.setText("300000");

        jLabel160.setText("Thương hiệu: ");

        thuongHieuCSD8.setText("Ordinary");

        javax.swing.GroupLayout pnelCSD8Layout = new javax.swing.GroupLayout(pnelCSD8);
        pnelCSD8.setLayout(pnelCSD8Layout);
        pnelCSD8Layout.setHorizontalGroup(
            pnelCSD8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelCSD8Layout.createSequentialGroup()
                .addGroup(pnelCSD8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelCSD8Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhCSD8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelCSD8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenCSD8, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelCSD8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelCSD8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelCSD8Layout.createSequentialGroup()
                        .addComponent(jLabel160)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuCSD8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelCSD8Layout.createSequentialGroup()
                        .addComponent(jLabel158, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaCSD8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelCSD8Layout.setVerticalGroup(
            pnelCSD8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelCSD8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhCSD8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenCSD8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelCSD8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel158)
                    .addComponent(giaCSD8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelCSD8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel160)
                    .addComponent(thuongHieuCSD8))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelCSD5.setBackground(new java.awt.Color(255, 255, 255));
        pnelCSD5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelCSD5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelCSD5MouseClicked(evt);
            }
        });

        hinhCSD5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenCSD5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenCSD5.setText("Tinh chất trị mụn The Ordinary Salicylic Acid 2% Solution (BHA)");

        jLabel164.setText("Giá:");

        giaCSD5.setForeground(new java.awt.Color(255, 51, 51));
        giaCSD5.setText("260000");

        jLabel166.setText("Thương hiệu: ");

        thuongHieuCSD5.setText("Ordinary");

        javax.swing.GroupLayout pnelCSD5Layout = new javax.swing.GroupLayout(pnelCSD5);
        pnelCSD5.setLayout(pnelCSD5Layout);
        pnelCSD5Layout.setHorizontalGroup(
            pnelCSD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelCSD5Layout.createSequentialGroup()
                .addGroup(pnelCSD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelCSD5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhCSD5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelCSD5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenCSD5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelCSD5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelCSD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelCSD5Layout.createSequentialGroup()
                        .addComponent(jLabel166)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuCSD5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelCSD5Layout.createSequentialGroup()
                        .addComponent(jLabel164, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaCSD5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelCSD5Layout.setVerticalGroup(
            pnelCSD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelCSD5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhCSD5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenCSD5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelCSD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel164)
                    .addComponent(giaCSD5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelCSD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel166)
                    .addComponent(thuongHieuCSD5))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelCSD6.setBackground(new java.awt.Color(255, 255, 255));
        pnelCSD6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelCSD6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelCSD6MouseClicked(evt);
            }
        });

        hinhCSD6.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenCSD6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenCSD6.setText("Kem dưỡng ẩm The Ordinary");

        jLabel170.setText("Giá:");

        giaCSD6.setForeground(new java.awt.Color(255, 51, 51));
        giaCSD6.setText("250000");

        jLabel172.setText("Thương hiệu: ");

        thuongHieuCSD6.setText("Ordinary");

        javax.swing.GroupLayout pnelCSD6Layout = new javax.swing.GroupLayout(pnelCSD6);
        pnelCSD6.setLayout(pnelCSD6Layout);
        pnelCSD6Layout.setHorizontalGroup(
            pnelCSD6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelCSD6Layout.createSequentialGroup()
                .addGroup(pnelCSD6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelCSD6Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhCSD6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelCSD6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenCSD6, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelCSD6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelCSD6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelCSD6Layout.createSequentialGroup()
                        .addComponent(jLabel172)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuCSD6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelCSD6Layout.createSequentialGroup()
                        .addComponent(jLabel170, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaCSD6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelCSD6Layout.setVerticalGroup(
            pnelCSD6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelCSD6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhCSD6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenCSD6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelCSD6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel170)
                    .addComponent(giaCSD6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelCSD6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel172)
                    .addComponent(thuongHieuCSD6))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelCSD7.setBackground(new java.awt.Color(255, 255, 255));
        pnelCSD7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelCSD7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelCSD7MouseClicked(evt);
            }
        });

        hinhCSD7.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenCSD7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenCSD7.setText("Serum dưỡng tóc The Ordinary");

        jLabel176.setText("Giá:");

        giaCSD7.setForeground(new java.awt.Color(255, 51, 51));
        giaCSD7.setText("580000");

        jLabel178.setText("Thương hiệu: ");

        thuongHieuCSD7.setText("Ordinary");

        javax.swing.GroupLayout pnelCSD7Layout = new javax.swing.GroupLayout(pnelCSD7);
        pnelCSD7.setLayout(pnelCSD7Layout);
        pnelCSD7Layout.setHorizontalGroup(
            pnelCSD7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelCSD7Layout.createSequentialGroup()
                .addGroup(pnelCSD7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelCSD7Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhCSD7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelCSD7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenCSD7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelCSD7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelCSD7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelCSD7Layout.createSequentialGroup()
                        .addComponent(jLabel178)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuCSD7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelCSD7Layout.createSequentialGroup()
                        .addComponent(jLabel176, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaCSD7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelCSD7Layout.setVerticalGroup(
            pnelCSD7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelCSD7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhCSD7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenCSD7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelCSD7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel176)
                    .addComponent(giaCSD7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelCSD7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel178)
                    .addComponent(thuongHieuCSD7))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        btnFirstCSD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/first.png"))); // NOI18N
        btnFirstCSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstCSDActionPerformed(evt);
            }
        });

        btnNextCSD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/next.png"))); // NOI18N
        btnNextCSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextCSDActionPerformed(evt);
            }
        });

        btnPrevCSD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/prev.png"))); // NOI18N
        btnPrevCSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevCSDActionPerformed(evt);
            }
        });

        btnLastCSD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/last.png"))); // NOI18N
        btnLastCSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastCSDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnFirstCSD)
                .addGap(18, 18, 18)
                .addComponent(btnNextCSD)
                .addGap(18, 18, 18)
                .addComponent(btnPrevCSD)
                .addGap(18, 18, 18)
                .addComponent(btnLastCSD)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLastCSD)
                    .addComponent(btnPrevCSD)
                    .addComponent(btnNextCSD)
                    .addComponent(btnFirstCSD))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelChamSocDaLayout = new javax.swing.GroupLayout(PanelChamSocDa);
        PanelChamSocDa.setLayout(PanelChamSocDaLayout);
        PanelChamSocDaLayout.setHorizontalGroup(
            PanelChamSocDaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChamSocDaLayout.createSequentialGroup()
                .addGroup(PanelChamSocDaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelChamSocDaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelChamSocDaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelChamSocDaLayout.createSequentialGroup()
                                .addComponent(pnelCSD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnelCSD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnelCSD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelChamSocDaLayout.createSequentialGroup()
                                .addComponent(pnelCSD5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnelCSD6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnelCSD7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(PanelChamSocDaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnelCSD8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnelCSD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelChamSocDaLayout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        PanelChamSocDaLayout.setVerticalGroup(
            PanelChamSocDaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChamSocDaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(PanelChamSocDaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnelCSD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelCSD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelCSD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelCSD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelChamSocDaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnelCSD8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelCSD5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelCSD6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelCSD7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        PageTrangDiem.addTab("Chăm sóc da", PanelChamSocDa);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        pnelNH1.setBackground(new java.awt.Color(255, 255, 255));
        pnelNH1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelNH1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelNH1MouseClicked(evt);
            }
        });

        hinhNH1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenNH1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenNH1.setText("KEM CHỐNG NẮNG PURE SHOTS AIRTHIN UV DEFENDER");

        jLabel135.setText("Giá:");

        giaNH1.setForeground(new java.awt.Color(255, 51, 51));
        giaNH1.setText("1800000");

        jLabel137.setText("Thương hiệu: ");

        thuongHieuNH1.setText("YSL");

        javax.swing.GroupLayout pnelNH1Layout = new javax.swing.GroupLayout(pnelNH1);
        pnelNH1.setLayout(pnelNH1Layout);
        pnelNH1Layout.setHorizontalGroup(
            pnelNH1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelNH1Layout.createSequentialGroup()
                .addGroup(pnelNH1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelNH1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhNH1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelNH1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenNH1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelNH1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelNH1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelNH1Layout.createSequentialGroup()
                        .addComponent(jLabel137)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuNH1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelNH1Layout.createSequentialGroup()
                        .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaNH1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelNH1Layout.setVerticalGroup(
            pnelNH1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelNH1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhNH1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenNH1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelNH1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel135)
                    .addComponent(giaNH1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelNH1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel137)
                    .addComponent(thuongHieuNH1))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelNH2.setBackground(new java.awt.Color(255, 255, 255));
        pnelNH2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelNH2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelNH2MouseClicked(evt);
            }
        });

        hinhNH2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenNH2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenNH2.setText("KEM CHỐNG NẮNG PURE SHOTS AIRTHIN UV DEFENDER");

        jLabel138.setText("Giá:");

        giaNH2.setForeground(new java.awt.Color(255, 51, 51));
        giaNH2.setText("1800000");

        jLabel139.setText("Thương hiệu: ");

        thuongHieuNH2.setText("YSL");

        javax.swing.GroupLayout pnelNH2Layout = new javax.swing.GroupLayout(pnelNH2);
        pnelNH2.setLayout(pnelNH2Layout);
        pnelNH2Layout.setHorizontalGroup(
            pnelNH2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelNH2Layout.createSequentialGroup()
                .addGroup(pnelNH2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelNH2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhNH2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelNH2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenNH2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelNH2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelNH2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelNH2Layout.createSequentialGroup()
                        .addComponent(jLabel139)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuNH2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelNH2Layout.createSequentialGroup()
                        .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaNH2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelNH2Layout.setVerticalGroup(
            pnelNH2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelNH2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhNH2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenNH2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelNH2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel138)
                    .addComponent(giaNH2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelNH2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel139)
                    .addComponent(thuongHieuNH2))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelNH3.setBackground(new java.awt.Color(255, 255, 255));
        pnelNH3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelNH3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelNH3MouseClicked(evt);
            }
        });

        hinhNH3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenNH3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenNH3.setText("KEM CHỐNG NẮNG PURE SHOTS AIRTHIN UV DEFENDER");

        jLabel141.setText("Giá:");

        giaNH3.setForeground(new java.awt.Color(255, 51, 51));
        giaNH3.setText("1800000");

        jLabel143.setText("Thương hiệu: ");

        thuongHieuNH3.setText("YSL");

        javax.swing.GroupLayout pnelNH3Layout = new javax.swing.GroupLayout(pnelNH3);
        pnelNH3.setLayout(pnelNH3Layout);
        pnelNH3Layout.setHorizontalGroup(
            pnelNH3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelNH3Layout.createSequentialGroup()
                .addGroup(pnelNH3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelNH3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhNH3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelNH3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenNH3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelNH3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelNH3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelNH3Layout.createSequentialGroup()
                        .addComponent(jLabel143)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuNH3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelNH3Layout.createSequentialGroup()
                        .addComponent(jLabel141, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaNH3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelNH3Layout.setVerticalGroup(
            pnelNH3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelNH3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhNH3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenNH3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelNH3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel141)
                    .addComponent(giaNH3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelNH3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel143)
                    .addComponent(thuongHieuNH3))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelNH4.setBackground(new java.awt.Color(255, 255, 255));
        pnelNH4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelNH4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelNH4MouseClicked(evt);
            }
        });

        hinhNH4.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenNH4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenNH4.setText("KEM CHỐNG NẮNG PURE SHOTS AIRTHIN UV DEFENDER");

        jLabel144.setText("Giá:");

        giaNH4.setForeground(new java.awt.Color(255, 51, 51));
        giaNH4.setText("1800000");

        jLabel145.setText("Thương hiệu: ");

        thuongHieuNH4.setText("YSL");

        javax.swing.GroupLayout pnelNH4Layout = new javax.swing.GroupLayout(pnelNH4);
        pnelNH4.setLayout(pnelNH4Layout);
        pnelNH4Layout.setHorizontalGroup(
            pnelNH4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelNH4Layout.createSequentialGroup()
                .addGroup(pnelNH4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelNH4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhNH4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelNH4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenNH4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelNH4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelNH4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelNH4Layout.createSequentialGroup()
                        .addComponent(jLabel145)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuNH4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelNH4Layout.createSequentialGroup()
                        .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaNH4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelNH4Layout.setVerticalGroup(
            pnelNH4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelNH4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhNH4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenNH4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelNH4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel144)
                    .addComponent(giaNH4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelNH4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel145)
                    .addComponent(thuongHieuNH4))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelNH5.setBackground(new java.awt.Color(255, 255, 255));
        pnelNH5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelNH5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelNH5MouseClicked(evt);
            }
        });

        hinhNH5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenNH5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenNH5.setText("KEM CHỐNG NẮNG PURE SHOTS AIRTHIN UV DEFENDER");

        jLabel147.setText("Giá:");

        giaNH5.setForeground(new java.awt.Color(255, 51, 51));
        giaNH5.setText("1800000");

        jLabel149.setText("Thương hiệu: ");

        thuongHieuNH5.setText("YSL");

        javax.swing.GroupLayout pnelNH5Layout = new javax.swing.GroupLayout(pnelNH5);
        pnelNH5.setLayout(pnelNH5Layout);
        pnelNH5Layout.setHorizontalGroup(
            pnelNH5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelNH5Layout.createSequentialGroup()
                .addGroup(pnelNH5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelNH5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhNH5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelNH5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenNH5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelNH5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelNH5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelNH5Layout.createSequentialGroup()
                        .addComponent(jLabel149)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuNH5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelNH5Layout.createSequentialGroup()
                        .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaNH5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelNH5Layout.setVerticalGroup(
            pnelNH5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelNH5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhNH5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenNH5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelNH5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel147)
                    .addComponent(giaNH5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelNH5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel149)
                    .addComponent(thuongHieuNH5))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelNH6.setBackground(new java.awt.Color(255, 255, 255));
        pnelNH6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelNH6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelNH6MouseClicked(evt);
            }
        });

        hinhNH6.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenNH6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenNH6.setText("KEM CHỐNG NẮNG PURE SHOTS AIRTHIN UV DEFENDER");

        jLabel150.setText("Giá:");

        giaNH6.setForeground(new java.awt.Color(255, 51, 51));
        giaNH6.setText("1800000");

        jLabel151.setText("Thương hiệu: ");

        thuongHieuNH6.setText("YSL");

        javax.swing.GroupLayout pnelNH6Layout = new javax.swing.GroupLayout(pnelNH6);
        pnelNH6.setLayout(pnelNH6Layout);
        pnelNH6Layout.setHorizontalGroup(
            pnelNH6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelNH6Layout.createSequentialGroup()
                .addGroup(pnelNH6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelNH6Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhNH6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelNH6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenNH6, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelNH6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelNH6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelNH6Layout.createSequentialGroup()
                        .addComponent(jLabel151)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuNH6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelNH6Layout.createSequentialGroup()
                        .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaNH6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelNH6Layout.setVerticalGroup(
            pnelNH6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelNH6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhNH6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenNH6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelNH6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel150)
                    .addComponent(giaNH6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelNH6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel151)
                    .addComponent(thuongHieuNH6))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelNH7.setBackground(new java.awt.Color(255, 255, 255));
        pnelNH7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelNH7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelNH7MouseClicked(evt);
            }
        });

        hinhNH7.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenNH7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenNH7.setText("KEM CHỐNG NẮNG PURE SHOTS AIRTHIN UV DEFENDER");

        jLabel153.setText("Giá:");

        giaNH7.setForeground(new java.awt.Color(255, 51, 51));
        giaNH7.setText("1800000");

        jLabel155.setText("Thương hiệu: ");

        thuongHieuNH7.setText("YSL");

        javax.swing.GroupLayout pnelNH7Layout = new javax.swing.GroupLayout(pnelNH7);
        pnelNH7.setLayout(pnelNH7Layout);
        pnelNH7Layout.setHorizontalGroup(
            pnelNH7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelNH7Layout.createSequentialGroup()
                .addGroup(pnelNH7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelNH7Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhNH7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelNH7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenNH7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelNH7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelNH7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelNH7Layout.createSequentialGroup()
                        .addComponent(jLabel155)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuNH7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelNH7Layout.createSequentialGroup()
                        .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaNH7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelNH7Layout.setVerticalGroup(
            pnelNH7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelNH7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhNH7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenNH7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelNH7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel153)
                    .addComponent(giaNH7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelNH7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel155)
                    .addComponent(thuongHieuNH7))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnelNH8.setBackground(new java.awt.Color(255, 255, 255));
        pnelNH8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnelNH8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnelNH8MouseClicked(evt);
            }
        });

        hinhNH8.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2));

        tenNH8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tenNH8.setText("KEM CHỐNG NẮNG PURE SHOTS AIRTHIN UV DEFENDER");

        jLabel156.setText("Giá:");

        giaNH8.setForeground(new java.awt.Color(255, 51, 51));
        giaNH8.setText("1800000");

        jLabel157.setText("Thương hiệu: ");

        thuongHieuNH8.setText("YSL");

        javax.swing.GroupLayout pnelNH8Layout = new javax.swing.GroupLayout(pnelNH8);
        pnelNH8.setLayout(pnelNH8Layout);
        pnelNH8Layout.setHorizontalGroup(
            pnelNH8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelNH8Layout.createSequentialGroup()
                .addGroup(pnelNH8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelNH8Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(hinhNH8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnelNH8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tenNH8, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnelNH8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnelNH8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnelNH8Layout.createSequentialGroup()
                        .addComponent(jLabel157)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thuongHieuNH8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnelNH8Layout.createSequentialGroup()
                        .addComponent(jLabel156, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(giaNH8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnelNH8Layout.setVerticalGroup(
            pnelNH8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnelNH8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhNH8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tenNH8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelNH8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel156)
                    .addComponent(giaNH8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnelNH8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel157)
                    .addComponent(thuongHieuNH8))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnFirstNH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/first.png"))); // NOI18N
        btnFirstNH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstNHActionPerformed(evt);
            }
        });

        btnNextNH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/next.png"))); // NOI18N
        btnNextNH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextNHActionPerformed(evt);
            }
        });

        btnPrevNH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/prev.png"))); // NOI18N
        btnPrevNH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevNHActionPerformed(evt);
            }
        });

        btnLastNH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/last.png"))); // NOI18N
        btnLastNH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastNHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnFirstNH)
                .addGap(18, 18, 18)
                .addComponent(btnNextNH)
                .addGap(18, 18, 18)
                .addComponent(btnPrevNH)
                .addGap(18, 18, 18)
                .addComponent(btnLastNH)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLastNH)
                    .addComponent(btnPrevNH)
                    .addComponent(btnNextNH)
                    .addComponent(btnFirstNH))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pnelNH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnelNH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnelNH3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnelNH4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pnelNH5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnelNH6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnelNH7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(201, 201, 201)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(pnelNH8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnelNH4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelNH3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelNH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnelNH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnelNH5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnelNH6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnelNH7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pnelNH8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(40, 40, 40))
        );

        PageTrangDiem.addTab("Nước hoa", jPanel2);

        PanelThongTinSP.setBackground(new java.awt.Color(255, 255, 255));

        txtSearch.setText("SON MÔI DẠNG LÌ ROUGE PUR COUTURE THE SLIM 2");
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(237, 237, 239));
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        hinhMD.setBackground(new java.awt.Color(255, 255, 255));
        hinhMD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SON MÔI ROUGE PUR COUTURE THE SLIM VELVET RADICAL.jpg"))); // NOI18N
        hinhMD.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray));
        hinhMD.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                hinhMDAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        hinhMD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hinhMDMouseClicked(evt);
            }
        });

        jLabel4.setText("Giá:");

        jLabel6.setText("Thương hiệu: ");

        jLabel7.setText("Công dụng: ");

        txtGia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtGia.setForeground(new java.awt.Color(255, 51, 51));
        txtGia.setText("1200000");
        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        txtThuongHieu.setText("YSL");

        txtCongDung.setColumns(10);
        txtCongDung.setRows(2);
        txtCongDung.setText("dưỡng ẩm, trị thâm, làm sáng đều màu môi.\n");
        jScrollPane1.setViewportView(txtCongDung);

        btnXoa.setBackground(new java.awt.Color(204, 255, 204));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(204, 255, 204));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(204, 255, 204));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        txtTen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTen.setText("SON MÔI DẠNG LÌ ROUGE PUR COUTURE THE SLIM 2");
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        txtThanhPhan.setColumns(10);
        txtThanhPhan.setRows(5);
        txtThanhPhan.setText("tinh dầu jojoba, tinh dầu Macadamia bơ và nhiều loại \nvitamin.  Son không chì dưỡng môi tốt, hợp với da nhạy\n cảm.");
        jScrollPane2.setViewportView(txtThanhPhan);

        jLabel9.setText("Thành Phần:");

        jLabel5.setText("Loại sản phẩm:");

        cbxLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trang điểm", "Chăm sóc da", "Nước hoa" }));

        jLabel11.setText("Hạn sử dụng:");

        cbxHSD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 năm kể từ ngày sản xuất", "2 năm kể từ ngày sản xuất", "3 năm kể từ ngày sản xuất" }));

        btnClearForm.setBackground(new java.awt.Color(204, 255, 204));
        btnClearForm.setText("Làm mới");
        btnClearForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFormActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelThongTinSPLayout = new javax.swing.GroupLayout(PanelThongTinSP);
        PanelThongTinSP.setLayout(PanelThongTinSPLayout);
        PanelThongTinSPLayout.setHorizontalGroup(
            PanelThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelThongTinSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelThongTinSPLayout.createSequentialGroup()
                        .addGroup(PanelThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTen)
                            .addGroup(PanelThongTinSPLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelThongTinSPLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnClearForm, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelThongTinSPLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2))
                            .addGroup(PanelThongTinSPLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addComponent(txtThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelThongTinSPLayout.createSequentialGroup()
                                .addGroup(PanelThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelThongTinSPLayout.createSequentialGroup()
                                        .addComponent(cbxLoaiSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbxHSD, 0, 1, Short.MAX_VALUE))
                                    .addComponent(jScrollPane1))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelThongTinSPLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(hinhMD, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134))))
        );
        PanelThongTinSPLayout.setVerticalGroup(
            PanelThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelThongTinSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(hinhMD, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtThuongHieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbxLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cbxHSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(PanelThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelThongTinSPLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelThongTinSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearForm, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblDongHo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDongHo.setText("00:00:00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(1098, Short.MAX_VALUE)
                .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDongHo, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        Home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/home.png"))); // NOI18N
        Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeMouseClicked(evt);
            }
        });
        jMenuBar1.add(Home);

        MnuQLSP.setText("Quản lý");

        mniSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Numbered list.png"))); // NOI18N
        mniSanPham.setText("Quản lý Sản phẩm");
        mniSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSanPhamActionPerformed(evt);
            }
        });
        MnuQLSP.add(mniSanPham);

        mniNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user_24.png"))); // NOI18N
        mniNhanVien.setText("Quản lý Nhân Viên");
        mniNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniNhanVienActionPerformed(evt);
            }
        });
        MnuQLSP.add(mniNhanVien);

        mniKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/boxes.png"))); // NOI18N
        mniKho.setText("Quản lý Kho");
        mniKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKhoActionPerformed(evt);
            }
        });
        MnuQLSP.add(mniKho);

        mniHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/List.png"))); // NOI18N
        mniHoaDon.setText("Quản lý Hóa đơn");
        mniHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHoaDonActionPerformed(evt);
            }
        });
        MnuQLSP.add(mniHoaDon);

        mniKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        mniKhachHang.setText("Quản lý Khách hàng");
        mniKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKhachHangActionPerformed(evt);
            }
        });
        MnuQLSP.add(mniKhachHang);

        jMenuBar1.add(MnuQLSP);

        MnuThongKe.setText("Thống kê");

        mniDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Bar chart.png"))); // NOI18N
        mniDoanhThu.setText("Doanh thu");
        mniDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDoanhThuActionPerformed(evt);
            }
        });
        MnuThongKe.add(mniDoanhThu);

        jMenuBar1.add(MnuThongKe);

        mnuHeThong.setText("Hệ thống");

        mniDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Log out.png"))); // NOI18N
        mniDangXuat.setText("Đăng Xuất");
        mniDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangXuatActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDangXuat);

        mniKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Stop.png"))); // NOI18N
        mniKetThuc.setText("Kết thúc");
        mniKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKetThucActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniKetThuc);

        jMenuBar1.add(mnuHeThong);

        mnuTroGiup.setText("Trợ giúp");

        mniTroGiup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/HuongDan.png"))); // NOI18N
        mniTroGiup.setText("Trợ giúp");
        mniTroGiup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTroGiupActionPerformed(evt);
            }
        });
        mnuTroGiup.add(mniTroGiup);

        jMenuBar1.add(mnuTroGiup);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(PageTrangDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(PanelThongTinSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PageTrangDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelThongTinSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseClicked
        this.Home();
    }//GEN-LAST:event_HomeMouseClicked

    private void mniSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSanPhamActionPerformed

    }//GEN-LAST:event_mniSanPhamActionPerformed

    private void mniNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniNhanVienActionPerformed

    }//GEN-LAST:event_mniNhanVienActionPerformed

    private void mniKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKhoActionPerformed
        QLKho kho = new QLKho();
        kho.setVisible(true);
    }//GEN-LAST:event_mniKhoActionPerformed

    private void mniHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniHoaDonActionPerformed

    private void mniKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKhachHangActionPerformed

    }//GEN-LAST:event_mniKhachHangActionPerformed

    private void mniDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDoanhThuActionPerformed
        FormDoanhThu dt = new FormDoanhThu();
        dt.setVisible(true);
    }//GEN-LAST:event_mniDoanhThuActionPerformed

    private void mniDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangXuatActionPerformed
        FormDangNhap dn = new FormDangNhap();
        dn.setVisible(true);
    }//GEN-LAST:event_mniDangXuatActionPerformed

    private void mniKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKetThucActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mniKetThucActionPerformed

    private void mniTroGiupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTroGiupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniTroGiupActionPerformed

    private void pnel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnel2MouseClicked
        SanPham sp = dao.selectByKeyword(ten2.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnel2MouseClicked

    private void pnel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnel1MouseClicked
        SanPham sp = dao.selectByKeyword(ten1.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnel1MouseClicked

    private void pnel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnel3MouseClicked
        SanPham sp = dao.selectByKeyword(ten3.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnel3MouseClicked

    private void pnel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnel4MouseClicked
        SanPham sp = dao.selectByKeyword(ten4.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnel4MouseClicked

    private void pnel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnel8MouseClicked
        SanPham sp = dao.selectByKeyword(ten8.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnel8MouseClicked

    private void btnFirstDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstDSActionPerformed
        this.clearPageDS(pnel1, pnel2, pnel3, pnel4, pnel5, pnel6, pnel7, pnel8);
        this.page = 1;
        this.FillPageSP();
    }//GEN-LAST:event_btnFirstDSActionPerformed

    private void btnNextDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextDSActionPerformed
        this.clearPageDS(pnel1, pnel2, pnel3, pnel4, pnel5, pnel6, pnel7, pnel8);
        this.page++;
        int maxPage = (int) Math.floor(list.size() / 8) + 1;
        if (this.page > maxPage) {
            this.page = maxPage;
            System.out.println("maxpage" + maxPage);
        }
        this.FillPageSP();
    }//GEN-LAST:event_btnNextDSActionPerformed

    private void btnPrevDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevDSActionPerformed
        this.clearPageDS(pnel1, pnel2, pnel3, pnel4, pnel5, pnel6, pnel7, pnel8);
        this.page--;
        System.out.println("prevp" + page);
        if (this.page < 1) {
            this.page = 1;
        }
        this.FillPageSP();
    }//GEN-LAST:event_btnPrevDSActionPerformed

    private void btnLastDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastDSActionPerformed
        this.clearPageDS(pnel1, pnel2, pnel3, pnel4, pnel5, pnel6, pnel7, pnel8);
        int maxPage = (int) Math.floor(list.size() / 8) + 1;
        System.out.println("size" + list.size());
        System.out.println("max" + maxPage);
        this.page = maxPage;
        this.FillPageSP();
    }//GEN-LAST:event_btnLastDSActionPerformed

    private void pnel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnel5MouseClicked
        SanPham sp = dao.selectByKeyword(ten5.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnel5MouseClicked

    private void pnel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnel6MouseClicked
        SanPham sp = dao.selectByKeyword(ten6.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnel6MouseClicked

    private void pnel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnel7MouseClicked
        SanPham sp = dao.selectByKeyword(ten7.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnel7MouseClicked

    private void pnelTD1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelTD1MouseClicked
        SanPham sp = dao.selectByKeyword(tenTD1.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelTD1MouseClicked

    private void pnelTD2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelTD2MouseClicked
        SanPham sp = dao.selectByKeyword(tenTD2.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelTD2MouseClicked

    private void pnelTD3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelTD3MouseClicked
        SanPham sp = dao.selectByKeyword(tenTD3.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelTD3MouseClicked

    private void pnelTD4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelTD4MouseClicked
        SanPham sp = dao.selectByKeyword(tenTD4.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelTD4MouseClicked

    private void pnelTD5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelTD5MouseClicked
        SanPham sp = dao.selectByKeyword(tenTD5.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelTD5MouseClicked

    private void pnelTD6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelTD6MouseClicked
        SanPham sp = dao.selectByKeyword(tenTD6.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelTD6MouseClicked

    private void pnelTD7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelTD7MouseClicked
        SanPham sp = dao.selectByKeyword(tenTD7.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelTD7MouseClicked

    private void pnelTD8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelTD8MouseClicked
        SanPham sp = dao.selectByKeyword(tenTD8.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelTD8MouseClicked

    private void btnPrevTDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevTDActionPerformed
        this.clearPageDS(pnelTD1, pnelTD2, pnelTD3, pnelTD4, pnelTD5, pnelTD6, pnelTD7, pnelTD8);
        this.pageTD--;
        System.out.println("prevp" + page);
        if (this.pageTD < 1) {
            this.pageTD = 1;
        }
        this.FillPageTrangDiem();
    }//GEN-LAST:event_btnPrevTDActionPerformed

    private void btnNextTDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextTDActionPerformed
        this.clearPageDS(pnelTD1, pnelTD2, pnelTD3, pnelTD4, pnelTD5, pnelTD6, pnelTD7, pnelTD8);
        this.pageTD++;
        int maxPage = (int) Math.floor(list.size() / 8) + 1;
        if (this.pageTD > maxPage) {
            this.pageTD = maxPage;
            System.out.println("maxpage" + maxPage);
        }
        this.FillPageTrangDiem();
    }//GEN-LAST:event_btnNextTDActionPerformed

    private void btnFirstTDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstTDActionPerformed
        this.clearPageDS(pnelTD1, pnelTD2, pnelTD3, pnelTD4, pnelTD5, pnelTD6, pnelTD7, pnelTD8);
        this.pageTD = 1;
        this.FillPageTrangDiem();
    }//GEN-LAST:event_btnFirstTDActionPerformed

    private void btnLastTDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastTDActionPerformed
        this.clearPageDS(pnelTD1, pnelTD2, pnelTD3, pnelTD4, pnelTD5, pnelTD6, pnelTD7, pnelTD8);
        int maxPage = (int) Math.floor(list.size() / 8) + 1;
        this.pageTD = maxPage;
        this.FillPageTrangDiem();
    }//GEN-LAST:event_btnLastTDActionPerformed

    private void pnelCSD1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelCSD1MouseClicked
        SanPham sp = dao.selectByKeyword(tenCSD1.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelCSD1MouseClicked

    private void pnelCSD2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelCSD2MouseClicked
        SanPham sp = dao.selectByKeyword(tenCSD2.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelCSD2MouseClicked

    private void pnelCSD3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelCSD3MouseClicked
        SanPham sp = dao.selectByKeyword(tenCSD3.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelCSD3MouseClicked

    private void pnelCSD4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelCSD4MouseClicked
        SanPham sp = dao.selectByKeyword(tenCSD4.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelCSD4MouseClicked

    private void pnelCSD8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelCSD8MouseClicked
        SanPham sp = dao.selectByKeyword(tenCSD8.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelCSD8MouseClicked

    private void pnelCSD5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelCSD5MouseClicked
        SanPham sp = dao.selectByKeyword(tenCSD5.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelCSD5MouseClicked

    private void pnelCSD6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelCSD6MouseClicked
        SanPham sp = dao.selectByKeyword(tenCSD6.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelCSD6MouseClicked

    private void pnelCSD7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelCSD7MouseClicked
        SanPham sp = dao.selectByKeyword(tenCSD7.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelCSD7MouseClicked

    private void btnPrevCSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevCSDActionPerformed
        this.clearPageDS(pnelCSD1, pnelCSD2, pnelCSD3, pnelCSD4, pnelCSD5, pnelCSD6, pnelCSD7, pnelCSD8);
        this.pageCSD--;
        System.out.println("prevp" + page);
        if (this.pageCSD < 1) {
            this.pageCSD = 1;
        }
        this.FillPageChamSocDa();
    }//GEN-LAST:event_btnPrevCSDActionPerformed

    private void btnNextCSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextCSDActionPerformed
        this.clearPageDS(pnelCSD1, pnelCSD2, pnelCSD3, pnelCSD4, pnelCSD5, pnelCSD6, pnelCSD7, pnelCSD8);
        this.pageCSD++;
        int maxPage = (int) Math.floor(list.size() / 8) + 1;
        if (this.pageCSD > maxPage) {
            this.pageCSD = maxPage;
            System.out.println("maxpage" + maxPage);
        }
        this.FillPageChamSocDa();
    }//GEN-LAST:event_btnNextCSDActionPerformed

    private void btnFirstCSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstCSDActionPerformed
        this.clearPageDS(pnelCSD1, pnelCSD2, pnelCSD3, pnelCSD4, pnelCSD5, pnelCSD6, pnelCSD7, pnelCSD8);
        this.pageCSD = 1;
        this.FillPageChamSocDa();
    }//GEN-LAST:event_btnFirstCSDActionPerformed

    private void btnLastCSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastCSDActionPerformed
        this.clearPageDS(pnelCSD1, pnelCSD2, pnelCSD3, pnelCSD4, pnelCSD5, pnelCSD6, pnelCSD7, pnelCSD8);
        int maxPage = (int) Math.floor(list.size() / 8) + 1;
        this.pageCSD = maxPage;
        this.FillPageChamSocDa();
    }//GEN-LAST:event_btnLastCSDActionPerformed

    private void pnelNH1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelNH1MouseClicked
        SanPham sp = dao.selectByKeyword(tenNH1.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelNH1MouseClicked

    private void pnelNH2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelNH2MouseClicked
        SanPham sp = dao.selectByKeyword(tenNH2.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelNH2MouseClicked

    private void pnelNH3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelNH3MouseClicked
        SanPham sp = dao.selectByKeyword(tenNH3.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelNH3MouseClicked

    private void pnelNH4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelNH4MouseClicked
        SanPham sp = dao.selectByKeyword(tenNH4.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelNH4MouseClicked

    private void pnelNH5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelNH5MouseClicked
        SanPham sp = dao.selectByKeyword(tenNH5.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelNH5MouseClicked

    private void pnelNH6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelNH6MouseClicked
        SanPham sp = dao.selectByKeyword(tenNH6.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelNH6MouseClicked

    private void pnelNH7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelNH7MouseClicked
        SanPham sp = dao.selectByKeyword(tenNH7.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelNH7MouseClicked

    private void pnelNH8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnelNH8MouseClicked
        SanPham sp = dao.selectByKeyword(tenNH8.getText());
        this.setForm(sp);
    }//GEN-LAST:event_pnelNH8MouseClicked

    private void btnFirstNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstNHActionPerformed
        this.clearPageDS(pnelNH1, pnelNH2, pnelNH3, pnelNH4, pnelNH5, pnelNH6, pnelNH7, pnelNH8);
        this.pageNH = 1;
        this.FillPageNuocHoa();
    }//GEN-LAST:event_btnFirstNHActionPerformed

    private void btnPrevNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevNHActionPerformed
        this.clearPageDS(pnelNH1, pnelNH2, pnelNH3, pnelNH4, pnelNH5, pnelNH6, pnelNH7, pnelNH8);
        this.pageNH--;
        System.out.println("prevp" + page);
        if (this.pageNH < 1) {
            this.pageNH = 1;
        }
        this.FillPageNuocHoa();
    }//GEN-LAST:event_btnPrevNHActionPerformed

    private void btnNextNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextNHActionPerformed
        this.clearPageDS(pnelNH1, pnelNH2, pnelNH3, pnelNH4, pnelNH5, pnelNH6, pnelNH7, pnelNH8);
        this.pageNH++;
        int maxPage = (int) Math.floor(list.size() / 8) + 1;
        if (this.pageNH > maxPage) {
            this.pageNH = maxPage;
            System.out.println("maxpage" + maxPage);
        }
        this.FillPageNuocHoa();
    }//GEN-LAST:event_btnNextNHActionPerformed

    private void btnLastNHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastNHActionPerformed
        this.clearPageDS(pnelNH1, pnelNH2, pnelNH3, pnelNH4, pnelNH5, pnelNH6, pnelNH7, pnelNH8);
        int maxPage = (int) Math.floor(list.size() / 8) + 1;
        this.pageNH = maxPage;
        this.FillPageNuocHoa();
    }//GEN-LAST:event_btnLastNHActionPerformed

    private void PageTrangDiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PageTrangDiemMouseClicked
        this.FillPageTrangDiem();
        this.FillPageChamSocDa();
        this.FillPageNuocHoa();
    }//GEN-LAST:event_PageTrangDiemMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        SanPham sp = dao.selectByKeyword(txtSearch.getText());
        this.setForm(sp);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void hinhMDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hinhMDMouseClicked
        chonAnh();
    }//GEN-LAST:event_hinhMDMouseClicked

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        this.update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        this.insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed

    }//GEN-LAST:event_txtTenActionPerformed

    private void hinhMDAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_hinhMDAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_hinhMDAncestorAdded

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Thread clock = new Thread(this);
        clock.start();
    }//GEN-LAST:event_formWindowOpened

    private void btnClearFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFormActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearFormActionPerformed

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
            java.util.logging.Logger.getLogger(QLSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QLSanPham dialog = new QLSanPham(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenu Home;
    private javax.swing.JMenu MnuQLSP;
    private javax.swing.JMenu MnuThongKe;
    private javax.swing.JTabbedPane PageTrangDiem;
    private javax.swing.JPanel PanelChamSocDa;
    private javax.swing.JPanel PanelDSSP;
    private javax.swing.JPanel PanelThongTinSP;
    private javax.swing.JPanel PanelTrangDiem;
    private javax.swing.JButton btnClearForm;
    private javax.swing.JButton btnFirstCSD;
    private javax.swing.JButton btnFirstDS;
    private javax.swing.JButton btnFirstNH;
    private javax.swing.JButton btnFirstTD;
    private javax.swing.JButton btnLastCSD;
    private javax.swing.JButton btnLastDS;
    private javax.swing.JButton btnLastNH;
    private javax.swing.JButton btnLastTD;
    private javax.swing.JButton btnNextCSD;
    private javax.swing.JButton btnNextDS;
    private javax.swing.JButton btnNextNH;
    private javax.swing.JButton btnNextTD;
    private javax.swing.JButton btnPrevCSD;
    private javax.swing.JButton btnPrevDS;
    private javax.swing.JButton btnPrevNH;
    private javax.swing.JButton btnPrevTD;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbxHSD;
    private javax.swing.JComboBox<String> cbxLoaiSP;
    private javax.swing.JLabel gia1;
    private javax.swing.JLabel gia2;
    private javax.swing.JLabel gia3;
    private javax.swing.JLabel gia4;
    private javax.swing.JLabel gia5;
    private javax.swing.JLabel gia6;
    private javax.swing.JLabel gia7;
    private javax.swing.JLabel gia8;
    private javax.swing.JLabel giaCSD1;
    private javax.swing.JLabel giaCSD2;
    private javax.swing.JLabel giaCSD3;
    private javax.swing.JLabel giaCSD4;
    private javax.swing.JLabel giaCSD5;
    private javax.swing.JLabel giaCSD6;
    private javax.swing.JLabel giaCSD7;
    private javax.swing.JLabel giaCSD8;
    private javax.swing.JLabel giaNH1;
    private javax.swing.JLabel giaNH2;
    private javax.swing.JLabel giaNH3;
    private javax.swing.JLabel giaNH4;
    private javax.swing.JLabel giaNH5;
    private javax.swing.JLabel giaNH6;
    private javax.swing.JLabel giaNH7;
    private javax.swing.JLabel giaNH8;
    private javax.swing.JLabel giaTD1;
    private javax.swing.JLabel giaTD2;
    private javax.swing.JLabel giaTD3;
    private javax.swing.JLabel giaTD4;
    private javax.swing.JLabel giaTD5;
    private javax.swing.JLabel giaTD6;
    private javax.swing.JLabel giaTD7;
    private javax.swing.JLabel giaTD8;
    private javax.swing.JLabel hinh1;
    private javax.swing.JLabel hinh2;
    private javax.swing.JLabel hinh3;
    private javax.swing.JLabel hinh4;
    private javax.swing.JLabel hinh5;
    private javax.swing.JLabel hinh6;
    private javax.swing.JLabel hinh7;
    private javax.swing.JLabel hinh8;
    private javax.swing.JLabel hinhCSD1;
    private javax.swing.JLabel hinhCSD2;
    private javax.swing.JLabel hinhCSD3;
    private javax.swing.JLabel hinhCSD4;
    private javax.swing.JLabel hinhCSD5;
    private javax.swing.JLabel hinhCSD6;
    private javax.swing.JLabel hinhCSD7;
    private javax.swing.JLabel hinhCSD8;
    private javax.swing.JLabel hinhMD;
    private javax.swing.JLabel hinhNH1;
    private javax.swing.JLabel hinhNH2;
    private javax.swing.JLabel hinhNH3;
    private javax.swing.JLabel hinhNH4;
    private javax.swing.JLabel hinhNH5;
    private javax.swing.JLabel hinhNH6;
    private javax.swing.JLabel hinhNH7;
    private javax.swing.JLabel hinhNH8;
    private javax.swing.JLabel hinhTD1;
    private javax.swing.JLabel hinhTD2;
    private javax.swing.JLabel hinhTD3;
    private javax.swing.JLabel hinhTD4;
    private javax.swing.JLabel hinhTD5;
    private javax.swing.JLabel hinhTD6;
    private javax.swing.JLabel hinhTD7;
    private javax.swing.JLabel hinhTD8;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JMenuItem mniDangXuat;
    private javax.swing.JMenuItem mniDoanhThu;
    private javax.swing.JMenuItem mniHoaDon;
    private javax.swing.JMenuItem mniKetThuc;
    private javax.swing.JMenuItem mniKhachHang;
    private javax.swing.JMenuItem mniKho;
    private javax.swing.JMenuItem mniNhanVien;
    private javax.swing.JMenuItem mniSanPham;
    private javax.swing.JMenuItem mniTroGiup;
    private javax.swing.JMenu mnuHeThong;
    private javax.swing.JMenu mnuTroGiup;
    private javax.swing.JPanel pnel1;
    private javax.swing.JPanel pnel2;
    private javax.swing.JPanel pnel3;
    private javax.swing.JPanel pnel4;
    private javax.swing.JPanel pnel5;
    private javax.swing.JPanel pnel6;
    private javax.swing.JPanel pnel7;
    private javax.swing.JPanel pnel8;
    private javax.swing.JPanel pnelCSD1;
    private javax.swing.JPanel pnelCSD2;
    private javax.swing.JPanel pnelCSD3;
    private javax.swing.JPanel pnelCSD4;
    private javax.swing.JPanel pnelCSD5;
    private javax.swing.JPanel pnelCSD6;
    private javax.swing.JPanel pnelCSD7;
    private javax.swing.JPanel pnelCSD8;
    private javax.swing.JPanel pnelNH1;
    private javax.swing.JPanel pnelNH2;
    private javax.swing.JPanel pnelNH3;
    private javax.swing.JPanel pnelNH4;
    private javax.swing.JPanel pnelNH5;
    private javax.swing.JPanel pnelNH6;
    private javax.swing.JPanel pnelNH7;
    private javax.swing.JPanel pnelNH8;
    private javax.swing.JPanel pnelTD1;
    private javax.swing.JPanel pnelTD2;
    private javax.swing.JPanel pnelTD3;
    private javax.swing.JPanel pnelTD4;
    private javax.swing.JPanel pnelTD5;
    private javax.swing.JPanel pnelTD6;
    private javax.swing.JPanel pnelTD7;
    private javax.swing.JPanel pnelTD8;
    private javax.swing.JLabel ten1;
    private javax.swing.JLabel ten2;
    private javax.swing.JLabel ten3;
    private javax.swing.JLabel ten4;
    private javax.swing.JLabel ten5;
    private javax.swing.JLabel ten6;
    private javax.swing.JLabel ten7;
    private javax.swing.JLabel ten8;
    private javax.swing.JLabel tenCSD1;
    private javax.swing.JLabel tenCSD2;
    private javax.swing.JLabel tenCSD3;
    private javax.swing.JLabel tenCSD4;
    private javax.swing.JLabel tenCSD5;
    private javax.swing.JLabel tenCSD6;
    private javax.swing.JLabel tenCSD7;
    private javax.swing.JLabel tenCSD8;
    private javax.swing.JLabel tenNH1;
    private javax.swing.JLabel tenNH2;
    private javax.swing.JLabel tenNH3;
    private javax.swing.JLabel tenNH4;
    private javax.swing.JLabel tenNH5;
    private javax.swing.JLabel tenNH6;
    private javax.swing.JLabel tenNH7;
    private javax.swing.JLabel tenNH8;
    private javax.swing.JLabel tenTD1;
    private javax.swing.JLabel tenTD2;
    private javax.swing.JLabel tenTD3;
    private javax.swing.JLabel tenTD4;
    private javax.swing.JLabel tenTD5;
    private javax.swing.JLabel tenTD6;
    private javax.swing.JLabel tenTD7;
    private javax.swing.JLabel tenTD8;
    private javax.swing.JLabel thuongHieu1;
    private javax.swing.JLabel thuongHieu2;
    private javax.swing.JLabel thuongHieu3;
    private javax.swing.JLabel thuongHieu4;
    private javax.swing.JLabel thuongHieu5;
    private javax.swing.JLabel thuongHieu6;
    private javax.swing.JLabel thuongHieu7;
    private javax.swing.JLabel thuongHieu8;
    private javax.swing.JLabel thuongHieuCSD1;
    private javax.swing.JLabel thuongHieuCSD2;
    private javax.swing.JLabel thuongHieuCSD3;
    private javax.swing.JLabel thuongHieuCSD4;
    private javax.swing.JLabel thuongHieuCSD5;
    private javax.swing.JLabel thuongHieuCSD6;
    private javax.swing.JLabel thuongHieuCSD7;
    private javax.swing.JLabel thuongHieuCSD8;
    private javax.swing.JLabel thuongHieuNH1;
    private javax.swing.JLabel thuongHieuNH2;
    private javax.swing.JLabel thuongHieuNH3;
    private javax.swing.JLabel thuongHieuNH4;
    private javax.swing.JLabel thuongHieuNH5;
    private javax.swing.JLabel thuongHieuNH6;
    private javax.swing.JLabel thuongHieuNH7;
    private javax.swing.JLabel thuongHieuNH8;
    private javax.swing.JLabel thuongHieuTD1;
    private javax.swing.JLabel thuongHieuTD2;
    private javax.swing.JLabel thuongHieuTD3;
    private javax.swing.JLabel thuongHieuTD4;
    private javax.swing.JLabel thuongHieuTD5;
    private javax.swing.JLabel thuongHieuTD6;
    private javax.swing.JLabel thuongHieuTD7;
    private javax.swing.JLabel thuongHieuTD8;
    private javax.swing.JTextArea txtCongDung;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextArea txtThanhPhan;
    private javax.swing.JTextField txtThuongHieu;
    // End of variables declaration//GEN-END:variables
}
