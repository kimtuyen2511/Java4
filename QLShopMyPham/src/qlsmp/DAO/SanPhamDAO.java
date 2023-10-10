/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsmp.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import qlsmp.Model.SanPham;
import qlsmp.DB.DBHelper;

/**
 *
 * @author My Laptop
 */
public class SanPhamDAO extends ShopMyPhamDAO<SanPham, String> {

    String INSERT_SQL = "INSERT INTO SANPHAM(TenSP,GiaBan,ThuongHieu,CongDung, ThanhPhan,HSD, Hinh,MaKho,MaLoai)Values(?,?,?,?,?,?,NULL,NULL,?)";
    String UPDATE_SQL = "UPDATE SANPHAM SET TenSP=?,GiaBan=?,ThuongHieu=?,CongDung=?,ThanhPhan=?,HSD=?,MaLoai=? WHERE MaSP=?";
    String DELETE_SQL = "DELETE FROM SANPHAM WHERE MaSP=?";
    String SELECT_ALL_SQL = "SELECT * FROM SANPHAM";
    String SELECT_BY_ID_SQL = "SELECT * FROM SANPHAM WHERE MaSP=?";

    @Override
    public void insert(SanPham enity) {
        DBHelper.update(INSERT_SQL, enity.getTensp(), enity.getGia(), enity.getThuongHieu(), enity.getCongDung(), enity.getThanhPhan(), enity.getHSD(),enity.getLoaiSP());
    }

    @Override
    public void update(SanPham enity) {
        DBHelper.update(UPDATE_SQL, enity.getTensp(), enity.getGia(), enity.getThuongHieu(), enity.getCongDung(), enity.getThanhPhan(), enity.getHSD(), enity.getLoaiSP(), enity.getMaSP());
    }

    @Override
    public void delete(String id) {
        DBHelper.update(DELETE_SQL, id);
    }

    @Override
    public SanPham selecteByID(String id) {
        List<SanPham> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
// để tìm kiếm

    @Override
    public List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                SanPham enity = new SanPham();
                enity.setMaSP(rs.getInt("MaSP"));
                enity.setTensp(rs.getString("TenSP"));
                enity.setGia(rs.getDouble("GiaBan"));
                enity.setThuongHieu(rs.getString("ThuongHieu"));
                enity.setCongDung(rs.getString("CongDung"));
                enity.setThanhPhan(rs.getString("ThanhPhan"));
                enity.setHSD(rs.getString("HSD"));
                enity.setHinh(rs.getString("Hinh"));
                enity.setLoaiSP(rs.getInt("MaLoai"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SanPham selectByKeyword(String keyword) {
        Connection con = DBHelper.getDBConnection();
        String sql = "SELECT * FROM SANPHAM WHERE TenSP like N'" + keyword + "'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SanPham cd = new SanPham();
                cd.setMaSP(rs.getInt(1));
                cd.setTensp(rs.getString(2));
                cd.setGia(rs.getDouble(3));
                cd.setThuongHieu(rs.getString(4));
                cd.setCongDung(rs.getString(5));
                cd.setThanhPhan(rs.getString(6));
                cd.setHSD(rs.getString(7));
                cd.setHinh(rs.getString(8));
                return cd;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<SanPham> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public List<SanPham> getAllByLoai(int loai) {
        List<SanPham> listSV = new ArrayList<>();
        try {
            Connection con = DBHelper.getDBConnection();
            String sql = "SELECT * FROM SANPHAM WHERE MaLoai=" + loai;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SanPham cd = new SanPham();
                cd.setMaSP(rs.getInt(1));
                cd.setTensp(rs.getString(2));
                cd.setGia(rs.getDouble(3));
                cd.setThuongHieu(rs.getString(4));
                cd.setCongDung(rs.getString(5));
                cd.setThanhPhan(rs.getString(6));
                cd.setHSD(rs.getString(7));
                cd.setHinh(rs.getString(8));
                listSV.add(cd);
            }
        } catch (Exception e) {
            System.out.println("Lỗi Kết Nối");
            e.printStackTrace();
        }
        return listSV;
    }

    public List<SanPham> selectKho() {
        List<SanPham> listSV = new ArrayList<>();
        try {
            Connection con = DBHelper.getDBConnection();
            String sql = "SELECT A.TenSP,A.GiaBan,B.SoLuong,A.Hinh FROM SANPHAM A JOIN KHO B ON  A.MaSP=B.MaKho";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SanPham cd = new SanPham();
                cd.setTensp(rs.getString(1));
                cd.setGia(rs.getDouble(2));
                cd.setSoLuong(rs.getInt(3));
                cd.setHinh(rs.getString(4));
                listSV.add(cd);
            }
        } catch (Exception e) {
            System.out.println("Lỗi Kết Nối");
            e.printStackTrace();
        }
        return listSV;
    }
}
