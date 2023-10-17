/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsmp.Model;

import javax.swing.ImageIcon;

/**
 *
 * @author My Laptop
 */
public class DonHang {

    private int maDH, tongTien, thanhTien, maAccount,maKH;
    private String ngayTao, nguoiTao;
    private double Sale;
    private boolean TrangThai;
    public DonHang() {
    }

    public DonHang(int maDH, int tongTien, int thanhTien, int maAccount, int maKH, String ngayTao, String nguoiTao, double Sale,boolean TrangThai) {
        this.maDH = maDH;
        this.tongTien = tongTien;
        this.thanhTien = thanhTien;
        this.maAccount = maAccount;
        this.maKH = maKH;
        this.ngayTao = ngayTao;
        this.nguoiTao = nguoiTao;
        this.Sale = Sale;
         this.TrangThai = TrangThai;
        
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getMaDH() {
        return maDH;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getThanhTien() {
        thanhTien = (int) (tongTien - tongTien * Sale);
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getMaAccount() {
        return maAccount;
    }

    public void setMaAccount(int maAccount) {
        this.maAccount = maAccount;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public double getSale() {
        return Sale;
    }

    public void setSale(double Sale) {
        this.Sale = Sale;
    }

}
