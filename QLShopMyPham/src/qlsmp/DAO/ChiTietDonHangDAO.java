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
import qlsmp.Model.ChiTietDonHang;

/**
 *
 * @author My Laptop
 */
public class ChiTietDonHangDAO extends ShopMyPhamDAO<ChiTietDonHang, String> {

    String SELECT_ALL = "SELECT MaCTDH,SL,MaDH,MaSP FROM CTDH ";
    String INSERT_SQL = "INSERT INTO CTDH (SL,MaDH,MaSP) VALUES (?,?,?)";
    String UPDATE_SOLUONG = "UPDATE CTDH SET SL=? WHERE MaCTDH=?";
    String DELETE_SQL = "DELETE CTDH WHERE MaCTDH=?";
    String SELECT_BY_ID_SQL = "SELECT UserName,Pass,ChucVu,MaNV FROM SANPHAM WHERE MaChiTietDonHang=?";
    String SELECT_BY_MADH = "SELECT MaCTDH,SL,MaDH,MaSP FROM CTDH WHERE MaDH=?";

    @Override
    public void insert(ChiTietDonHang enity) {
        DBHelper.update(INSERT_SQL, enity.getSoLuong(), enity.getMaDH(), enity.getMaSP());
    }

    public int selectIDENTITY(String sql, Object... args) {
        int ma = 0;
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                ChiTietDonHang enity = new ChiTietDonHang();
                enity.setMaCTDH(rs.getInt("MaCTDH"));
                ma = enity.getMaCTDH();
                return ma;
            }
            rs.getStatement().getConnection().close();
            return ma;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertReturnCTDH(ChiTietDonHang enity) {
        DBHelper.update(INSERT_SQL, enity.getSoLuong(), enity.getMaDH(), enity.getMaSP());
        String getIdQuery = "SELECT @@IDENTITY AS 'MaCTDH'";
        return this.selectIDENTITY(getIdQuery);
    }

    @Override
    public void update(ChiTietDonHang enity) {
        DBHelper.update(UPDATE_SOLUONG, enity.getSoLuong(), enity.getMaCTDH());
    }

    @Override
    public void delete(int id) {
        DBHelper.update(DELETE_SQL, id);
    }

    @Override
    public ChiTietDonHang selecteByID(int id) {
        List<ChiTietDonHang> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
// để tìm kiếm

    @Override
    public List<ChiTietDonHang> selectBySql(String sql, Object... args) {
        List<ChiTietDonHang> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                ChiTietDonHang enity = new ChiTietDonHang();
                enity.setMaCTDH(rs.getInt("MaCTDH"));
                enity.setSoLuong(rs.getInt("SL"));
                enity.setMaDH(rs.getInt("MaDH"));
                enity.setMaSP(rs.getInt("MaSP"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChiTietDonHang selectByKeyword(String keyword) {
        return null;
    }

    @Override
    public List<ChiTietDonHang> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }

    public List<ChiTietDonHang> selectByMaDH(int ma) {
        return this.selectBySql(SELECT_BY_MADH, ma);
    }

}
