/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsmp.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import qlsmp.DB.DBHelper;
import qlsmp.Model.Account;

/**
 *
 * @author My Laptop
 */
public class AccountDAO extends ShopMyPhamDAO<Account, String> {

    String SELECT_ALL = "SELECT MaAccount,UserName,Pass,ChucVu,MaNV FROM ACCOUNT ";
    // String UPDATE_SOLUONG = "UPDATE SANPHAM SET SoLuong=? WHERE MaSP=?";
    String SELECT_BY_ID_SQL = "SELECT UserName,Pass,ChucVu,MaNV FROM SANPHAM WHERE MaAccount=?";

    @Override
    public void insert(Account enity) {
    }

    @Override
    public void update(Account enity) {
        //DBHelper.update(UPDATE_SOLUONG, enity.getSoLuong(), enity.getMaSP());
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public Account selecteByID(int id) {
        List<Account> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
// để tìm kiếm

    @Override
    public List<Account> selectBySql(String sql, Object... args) {
        List<Account> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                Account enity = new Account();
                enity.setMaAccount(rs.getInt("MaAccount"));
                enity.setUsername(rs.getString("UserName"));
                enity.setPass(rs.getString("Pass"));
                enity.setVaiTro(rs.getBoolean("ChucVu"));
                enity.setMaNV(rs.getInt("MaNV"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account selectByKeyword(String keyword) {
        Connection con = DBHelper.getDBConnection();
        String sql = SELECT_ALL + " WHERE UserName like N'" + keyword + "'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Account enity = new Account();
                enity.setMaAccount(rs.getInt("MaAccount"));
                enity.setUsername(rs.getString("UserName"));
                enity.setPass(rs.getString("Pass"));
                enity.setVaiTro(rs.getBoolean("ChucVu"));
                enity.setMaNV(rs.getInt("MaNV"));
                return enity;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<Account> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }

}
