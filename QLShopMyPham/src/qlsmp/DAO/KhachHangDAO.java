package qlsmp.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import qlsmp.DB.DBHelper;
import qlsmp.Model.KhachHang;

/**
 *
 * @author My Laptop
 */
public class KhachHangDAO extends ShopMyPhamDAO<KhachHang, String> {

    String SELECT_ALL = "SELECT MaKH,TenKH,GioiTinh,NgaySinh,DiaChi,SDT,EMAIL FROM KHACHHANG ";
    String UPDATE_SQL = "UPDATE KHACHHANG SET TenKH=?, GioiTinh=?, NgaySinh=?,DiaChi=?,SDT=?,EMAIL=? WHERE MaKH=?";
    String INSERT_SQL = "INSERT INTO KHACHHANG(TenKH,GioiTinh,NgaySinh,DiaChi,SDT,EMAIL) VALUES(?,?,?,?,?,?)";
    String DELETE_SQL = "DELETE KHACHHANG WHERE MaKH=?";
    String SELECT_BY_ID_SQL = "SELECT MaKH,TenKH,GioiTinh,NgaySinh,DiaChi,SDT,EMAIL FROM KHACHHANG WHERE MaKH=?";

    @Override
    public void insert(KhachHang enity) {
        DBHelper.update(INSERT_SQL, enity.getMaKH(), enity.getTenKH(), enity.getGioiTinh(), enity.getNgaySinh(), enity.getDiaChi(), enity.getSDT(), enity.getEmail());
    }

    @Override
    public void update(KhachHang enity) {
        DBHelper.update(UPDATE_SQL, enity.getTenKH(), enity.getGioiTinh(), enity.getNgaySinh(), enity.getDiaChi(), enity.getSDT(), enity.getEmail(), enity.getMaKH());
    }

    @Override
    public void delete(int id) {
        DBHelper.update(DELETE_SQL, id);
    }

    @Override
    public KhachHang selecteByID(int id) {
        List<KhachHang> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

// để tìm kiếm

    @Override
    public List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                KhachHang enity = new KhachHang();
                enity.setMaKH(rs.getInt("MaKH"));
                enity.setTenKH(rs.getString("TenKH"));
                enity.setGioiTinh(rs.getString("GioiTinh"));
                enity.setNgaySinh(rs.getString("NgaySinh"));
                enity.setDiaChi(rs.getString("DiaChi"));
                enity.setSDT(rs.getString("SDT"));
                enity.setEmail(rs.getString("EMAIL"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public KhachHang selectByKeyword(String keyword) {
        Connection con = DBHelper.getDBConnection();
        String sql = SELECT_ALL + " WHERE SDT like N'" + keyword + "'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                KhachHang enity = new KhachHang();
                enity.setMaKH(rs.getInt("MaKH"));
                enity.setTenKH(rs.getString("TenKH"));
                enity.setGioiTinh(rs.getString("GioiTinh"));
                enity.setNgaySinh(rs.getString("NgaySinh"));
                enity.setDiaChi(rs.getString("DiaChi"));
                enity.setSDT(rs.getString("SDT"));
                enity.setEmail(rs.getString("EMAIL"));
                return enity;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<KhachHang> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }

}