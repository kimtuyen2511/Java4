
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

    String SELECT_ALL = "SELECT MaSP, TenSP,GiaBan,SoLuong,Hinh FROM SANPHAM ";
    String UPDATE_SOLUONG = "UPDATE SANPHAM SET SoLuong=? WHERE MaSP=?";
    String SELECT_BY_ID_SQL = "SELECT MaSP, TenSP,GiaBan,SoLuong,Hinh FROM SANPHAM WHERE MaSP=?";

    @Override
    public void insert(SanPham enity) {
        
    }

    @Override
    public void update(SanPham enity) {
        DBHelper.update(UPDATE_SOLUONG, enity.getSoLuong(), enity.getMaSP());
    }

    @Override
    public void delete(int id) {
        
    }

    @Override
    public SanPham selecteByID(int id) {
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
        String sql = SELECT_ALL + " WHERE TenSP like N'" + keyword + "'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SanPham cd = new SanPham();
                cd.setMaSP(rs.getInt(1));
                cd.setTensp(rs.getString(2));
                cd.setGia(rs.getDouble(3));
                cd.setSoLuong(rs.getInt(4));
                cd.setHinh(rs.getString(5));
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
