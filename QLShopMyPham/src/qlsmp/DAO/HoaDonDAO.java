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
import qlsmp.Model.HoaDon;
import qlsmp.Model.HoaDon;

/**
 *
 * @author My Laptop
 */
public class HoaDonDAO extends ShopMyPhamDAO<HoaDon, String> {

    String SELECT_ALL = "SELECT MaHD, NgayTao, NguoiTao, TongTien, Sale, ThanhTien, MaKH FROM HOADON ";
    String INSERT_SQL = "INSERT INTO HOADON(MaHD,NgayTao, NguoiTao, TongTien, Sale, ThanhTien, MaKH) VALUES(?,?,?,?,?,?,?)";
    String SELECT_BY_ID_SQL = "SELECT NgayTao, NguoiTao, TongTien, Sale, ThanhTien, MaKH FROM SANPHAM WHERE MaHD=?";

    @Override
    public void insert(HoaDon enity) {
        DBHelper.update(INSERT_SQL, enity.getMaHD(), enity.getNgayTao(), enity.getNguoiTao(), enity.getTongTien(), enity.getSale(), enity.getThanhTien(), enity.getMaKH());
    }

    @Override
    public void update(HoaDon enity) {
        //HÓA ĐƠN KHÔNG CHO UPDATE
    }

    @Override
    public void delete(int id) {
        // HÓA ĐƠN KHÔNG CHO XÓA 
    }

    @Override
    public HoaDon selecteByID(int id) {
        List<HoaDon> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
// để tìm kiếm

    @Override
    public List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                HoaDon enity = new HoaDon();
                enity.setMaHD(rs.getInt("MaHD"));
                enity.setNgayTao(rs.getString("NgayTao"));
                enity.setNguoiTao(rs.getString("NguoiTao"));
                enity.setTongTien(rs.getFloat("TongTien"));
                enity.setSale(rs.getDouble("Sale"));
                enity.setThanhTien(rs.getFloat("ThanhTien"));
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
    public HoaDon selectByKeyword(String keyword) {
        Connection con = DBHelper.getDBConnection();
// NULL
        return null;
    }

    @Override
    public List<HoaDon> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        }
    }

    public List<Object[]> getHoaDonTheoNam(String date) {
        String sql = "{CALL sp_HoaDonTheoNam(?)}";
        String[] cols = {"MaHD", "NgayTao", "NguoiTao", "TongTien", "Sale", "ThanhTien", "MaKH"};
        return this.getListOfArray(sql, cols, date);
    }

    public List<Object[]> getHoaDonTheoThang(String date) {
        String sql = "{CALL sp_HoaDonTheoThang(?)}";
        String[] cols = {"MaHD", "NgayTao", "NguoiTao", "TongTien", "Sale", "ThanhTien", "MaKH"};
        return this.getListOfArray(sql, cols, date);
    }

    public List<Object[]> getHoaDonTheoNgay(String date) {
        String sql = "{CALL sp_HoaDonTheoNgay(?)}";
        String[] cols = {"MaHD", "NgayTao", "NguoiTao", "TongTien", "Sale", "ThanhTien", "MaKH"};
        return this.getListOfArray(sql, cols, date);
    }

    public List<Object[]> getHoaDonBHTheoThang(String date) {
        String sql = "{CALL doanhThu_Thang(?)}";
        String[] cols = {"MaHD", "NgayTao", "NguoiTao", "TongTien"};
        return this.getListOfArray(sql, cols, date);
    }

    public List<Object[]> getHoaDonBHTheoNam(String date) {
        String sql = "{CALL doanhThu_Nam(?)}";
        String[] cols = {"MaHD", "NgayTao", "NguoiTao", "TongTien"};
        return this.getListOfArray(sql, cols, date);
    }


    public List<HoaDon> selectNgayNhap(String date) {
        String sql = "SELECT * from HOADON WHERE NgayTao = '";
        sql += date + "'";
        return this.selectBySql(sql);
    }
}
