/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsmp.Model;

/**
 *
 * @author My Laptop
 */
public class Account {

    private String username, pass;
    boolean vaiTro;
    private int maAccount, maNV;

    public Account() {
    }

    public Account( int maAccount, String username, String pass, boolean vaiTro, int maNV) {
        this.username = username;
        this.pass = pass;
        this.vaiTro = vaiTro;
        this.maAccount = maAccount;
        this.maNV = maNV;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public int getMaAccount() {
        return maAccount;
    }

    public void setMaAccount(int maAccount) {
        this.maAccount = maAccount;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }


}
