/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsmp.Model;

/**
 *
 * @author My Laptop
 */
public class SanPham {

    private String tensp, thuongHieu, congDung, hinh, thanhPhan, HSD;
    private double gia;

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }
    private int soLuong,loaiSP,maSP;

    public int getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(int loaiSP) {
        this.loaiSP = loaiSP;
    }

    public SanPham() {
    }

    public SanPham(int maSP, String tensp, double gia, String thuongHieu, String congDung, String thanhPhan, String HSD, String hinh, int soLuong,int loaiSP) {
         this.maSP = maSP;
        this.tensp = tensp;
        this.thuongHieu = thuongHieu;
        this.congDung = congDung;
        this.hinh = hinh;
        this.thanhPhan = thanhPhan;
        this.HSD = HSD;
        this.gia = gia;
        this.soLuong = soLuong;
        this.loaiSP = loaiSP;
    }

    public String getThanhPhan() {
        return thanhPhan;
    }

    public void setThanhPhan(String thanhPhan) {
        this.thanhPhan = thanhPhan;
    }

    public String getHSD() {
        return HSD;
    }

    public void setHSD(String HSD) {
        this.HSD = HSD;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public String getCongDung() {
        return congDung;
    }

    public void setCongDung(String congDung) {
        this.congDung = congDung;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

}
