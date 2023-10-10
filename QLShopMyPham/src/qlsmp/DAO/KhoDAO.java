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
import qlsmp.Model.SanPham;

/**
 *
 * @author My Laptop
 */
public class KhoDAO extends ShopMyPhamDAO<SanPham, String> {

    String SELECT_ALL = "SELECT A.TenSP,A.GiaBan,B.SoLuong,A.Hinh FROM SANPHAM A JOIN KHO B ON  A.MaSP=B.MaKho";

    public void insert(SanPham enity) {
    }

    @Override
    public void update(SanPham enity) {
    }

    @Override
    public void delete(String id) {
    }

    @Override
    public SanPham selecteByID(String id) {
        return null;
    }
// để tìm kiếm

    @Override
    public List<SanPham> selectBySql(String sql, Object... args) {
                List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                SanPham enity = new SanPham();
                enity.setTensp(rs.getString("TenSP"));
                enity.setGia(rs.getDouble("GiaBan"));
                enity.setSoLuong(rs.getInt("SoLuong"));
                enity.setHinh(rs.getString("Hinh"));
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
        String sql = SELECT_ALL+" WHERE TenSP like N'" + keyword + "'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SanPham cd = new SanPham();
                cd.setTensp(rs.getString(1));
                cd.setGia(rs.getDouble(2));
                cd.setSoLuong(rs.getInt(3));
                cd.setHinh(rs.getString(4));
                return cd;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<SanPham> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }

}
