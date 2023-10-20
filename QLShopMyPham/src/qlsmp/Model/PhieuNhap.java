
package qlsmp.Model;

/**
 *
 * @author My Laptop
 */
public class PhieuNhap {
    private int maPN, maAccount;
    private String ngTao,ngayTao;
    private double tongTien;

    public PhieuNhap() {
    }

    public PhieuNhap(int maPN, int maAccount, String ngTao, String ngayTao, double tongTien) {
        this.maPN = maPN;
        this.maAccount = maAccount;
        this.ngTao = ngTao;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
    }

    public int getMaPN() {
        return maPN;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public int getMaAccount() {
        return maAccount;
    }

    public void setMaAccount(int maAccount) {
        this.maAccount = maAccount;
    }

    public String getNgTao() {
        return ngTao;
    }

    public void setNgTao(String ngTao) {
        this.ngTao = ngTao;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    
}
