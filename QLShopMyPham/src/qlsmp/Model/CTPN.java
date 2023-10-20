
package qlsmp.Model;

/**
 *
 * @author My Laptop
 */
public class CTPN {
    private int maPN, maCTPN, sl, maSP;
    private double gia;
    private String Nsx;

    public CTPN(int maPN, int maCTPN, int sl, int maSP, double gia,String Nsx) {
        this.maPN = maPN;
        this.maCTPN = maCTPN;
        this.sl = sl;
        this.maSP = maSP;
        this.gia = gia;
        this.Nsx = Nsx;
    }

    public CTPN() {
    }

    public String getNsx() {
        return Nsx;
    }

    public void setNsx(String Nsx) {
        this.Nsx = Nsx;
    }

    
    public int getMaPN() {
        return maPN;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public int getMaCTPN() {
        return maCTPN;
    }

    public void setMaCTPN(int maCTPN) {
        this.maCTPN = maCTPN;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
    
    
}
