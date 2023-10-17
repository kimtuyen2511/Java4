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
import qlsmp.Model.DonHang;
import qlsmp.Model.ChiTietDonHang;

/**
 *
 * @author My Laptop
 */
public class DonHangDAO extends ShopMyPhamDAO<DonHang, String> {

    String SELECT_ALL = "SELECT MaDH,NgayTao,NguoiTao,TongTien,Sale,ThanhTien,TrangThai,IDAccount,MaKH FROM DONHANG where TrangThai=0 ";
    String UPDATE_SQL = "UPDATE DONHANG SET TongTien=?, Sale=?, ThanhTien=?,MaKH=?,TrangThai=? WHERE MaDH=?";
    String INSERT_SQL = "INSERT INTO DONHANG(NgayTao,NguoiTao,TongTien,Sale,ThanhTien,TrangThai,IDAccount,MaKH) VALUES(?,?,?,?,?,0,?,?)";
    String DELETE_SQL = "DELETE DONHANG WHERE MaDH=?";
    String SELECT_BY_ID_SQL = "SELECT MaDH,NgayTao,NguoiTao,TongTien,Sale,ThanhTien,TrangThai,IDAccount,MaKH FROM DONHANG WHERE MaDH=?";
    String SELECT_DONHANG_NEW = "SELECT TOP 1 MaDH,NgayTao,NguoiTao,TongTien,Sale,ThanhTien,TrangThai,IDAccount,MaKH FROM DONHANG ORDER BY MaDH DESC";

    @Override
    public void insert(DonHang enity) {
        DBHelper.update(INSERT_SQL, enity.getNgayTao(), enity.getNguoiTao(), enity.getTongTien(), enity.getSale(), enity.getThanhTien(), enity.getMaAccount(), enity.getMaKH());
    }

    @Override
    public void update(DonHang enity) {
        DBHelper.update(UPDATE_SQL, enity.getTongTien(), enity.getSale(), enity.getThanhTien(), enity.getMaKH(), enity.isTrangThai(), enity.getMaDH());
    }

    @Override
    public void delete(int id) {
        DBHelper.update(DELETE_SQL, id);
    }

    @Override
    public DonHang selecteByID(int id) {
        List<DonHang> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
// để tìm kiếm

    @Override
    public List<DonHang> selectBySql(String sql, Object... args) {
        List<DonHang> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                DonHang enity = new DonHang();
                enity.setMaDH(rs.getInt("MaDH"));
                enity.setNgayTao(rs.getString("NgayTao"));
                enity.setNguoiTao(rs.getString("NguoiTao"));
                enity.setTongTien(rs.getInt("TongTien"));
                enity.setSale(rs.getDouble("Sale"));
                enity.setThanhTien(rs.getInt("ThanhTien"));
                enity.setMaAccount(rs.getInt("IDAccount"));
                enity.setTrangThai(rs.getBoolean("TrangThai"));
                enity.setMaKH(rs.getInt("MaKH"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DonHang selectByKeyword(String keyword) {
        Connection con = DBHelper.getDBConnection();
        String sql = SELECT_ALL + " WHERE MaDH like N'" + keyword + "'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                DonHang enity = new DonHang();
//                enity.setMaDH(rs.getInt("MaDH"));
//                enity.setNgayTao(rs.getString("NgayTao"));
//                enity.setNguoiTao(rs.getString("NguoiTao"));
//                enity.setTongTien(rs.getInt("TongTien"));
//                enity.setSale(rs.getDouble("Sale"));
//                enity.setThanhTien(rs.getInt("ThanhTien"));
//                enity.setMaAccount(rs.getInt("IDAccount"));
                return enity;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<DonHang> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }

    public DonHang selectDonHangNew() {
        Connection con = DBHelper.getDBConnection();
        String sql = SELECT_DONHANG_NEW;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                DonHang enity = new DonHang();
                enity.setMaDH(rs.getInt("MaDH"));
                enity.setNgayTao(rs.getString("NgayTao"));
                enity.setNguoiTao(rs.getString("NguoiTao"));
                enity.setTongTien(rs.getInt("TongTien"));
                enity.setSale(rs.getDouble("Sale"));
                enity.setThanhTien(rs.getInt("ThanhTien"));
                enity.setMaAccount(rs.getInt("IDAccount"));
                return enity;
            }
        } catch (Exception e) {
        }
        return null;
    }
}
