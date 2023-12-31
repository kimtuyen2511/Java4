/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsmp.Model;

import qlsmp.DAO.*;

/**
 *
 * @author My Laptop
 */
public class HoaDon {

    private int maHD, maKH;
    private float ThanhTien, tongTien;
    private double sale;
    private String ngayTao, NguoiTao;


    public HoaDon() {
    }

    public HoaDon(int maHD, int maKH, float tongTien,  float ThanhTien, double sale, String ngayTao, String NguoiTao) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.tongTien = tongTien;
        this.ThanhTien = ThanhTien;
        this.sale = sale;
        this.ngayTao = ngayTao;
        this.NguoiTao = NguoiTao;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public float getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(float ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNguoiTao() {
        return NguoiTao;
    }

    public void setNguoiTao(String NguoiTao) {
        this.NguoiTao = NguoiTao;
    }

}
