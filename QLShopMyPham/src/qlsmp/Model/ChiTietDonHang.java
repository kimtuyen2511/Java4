/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsmp.Model;

/**
 *
 * @author My Laptop
 */
public class ChiTietDonHang {

    private int SoLuong, MaDH, MaSP, MaCTDH;
    private String ten;
    private int gia;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(int MaCTDH, int SoLuong, int MaDH, int MaSP, String ten, int gia) {
        this.SoLuong = SoLuong;
        this.MaDH = MaDH;
        this.MaSP = MaSP;
        this.MaCTDH = MaCTDH;
        this.ten = ten;
        this.gia = gia;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getMaCTDH() {
        return MaCTDH;
    }

    public void setMaCTDH(int MaCTDH) {
        this.MaCTDH = MaCTDH;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getMaDH() {
        return MaDH;
    }

    public void setMaDH(int MaDH) {
        this.MaDH = MaDH;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

}
