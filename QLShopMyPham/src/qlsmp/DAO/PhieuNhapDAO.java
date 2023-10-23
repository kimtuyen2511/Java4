package qlsmp.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import qlsmp.DB.DBHelper;
import qlsmp.Model.PhieuNhap;
import qlsmp.Model.DonHang;

public class PhieuNhapDAO extends ShopMyPhamDAO<PhieuNhap, String> {

    String SELECT_ALL = "SELECT MaPN,NgayTao,NguoiTao,ThanhTien,MaAccount FROM PHIEUNHAP ";
    String INSERT_SQL = "INSERT INTO PHIEUNHAP(NgayTao,NguoiTao,ThanhTien,MaAccount) VALUES(?,?,?,?)";
    String SELECT_BY_ID_SQL = "SELECT NgayTao,NguoiTao,ThanhTien,MaAccount FROM PHIEUNHAP WHERE MaPN=?";
    String SELECT_PN_NEW = "SELECT TOP 1 MaPN,NgayTao,NguoiTao,ThanhTien,MaAccount FROM PHIEUNHAP ORDER BY MaPN DESC";

    @Override
    public void insert(PhieuNhap enity) {
        DBHelper.update(INSERT_SQL, enity.getNgayTao(), enity.getNgTao(), enity.getTongTien(), enity.getMaAccount());
    }

    @Override
    public void update(PhieuNhap enity) {
        // DBHelper.update(UPDATE_SQL, enity.getTongTien(), enity.getSale(), enity.getThanhTien(), enity.getMaKH(), enity.isTrangThai(), enity.getMaDH());
    }

    @Override
    public void delete(int id) {
//        DBHelper.update(DELETE_SQL, id);
    }

    @Override
    public PhieuNhap selecteByID(int id) {
        List<PhieuNhap> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
// để tìm kiếm

    @Override
    public List<PhieuNhap> selectBySql(String sql, Object... args) {
        List<PhieuNhap> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                PhieuNhap enity = new PhieuNhap();
                enity.setMaPN(rs.getInt("MaPN"));
                enity.setNgayTao(rs.getString("NgayTao"));
                enity.setNgTao(rs.getString("NguoiTao"));
                enity.setTongTien(rs.getInt("ThanhTien"));
                enity.setMaAccount(rs.getInt("MaAccount"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PhieuNhap selectByKeyword(String keyword) {
        return null;
    }

    @Override
    public List<PhieuNhap> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }

    public int selectMaTop1DESC() {
        try {
            ResultSet rs = DBHelper.query(SELECT_PN_NEW);
            while (rs.next()) {
                PhieuNhap enity = new PhieuNhap();
                enity.setMaPN(rs.getInt("MaPN"));
                return enity.getMaPN();
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
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

    public List<Object[]> getHoaDonNHTheoThang(String date) {
        String sql = "{CALL PhieuNhap_Thang(?)}";
        String[] cols = {"MaPN", "NgayTao", "NguoiTao", "ThanhTien", "MaAccount"};
        return this.getListOfArray(sql, cols, date);
    }

    public List<Object[]> getHoaDonNHTheoNam(String date) {
        String sql = "{CALL PhieuNhap_Nam(?)}";
        String[] cols = {"MaPN", "NgayTao", "NguoiTao", "ThanhTien", "MaAccount"};
        return this.getListOfArray(sql, cols, date);
    }

    public List<PhieuNhap> selectNgayNhap(String date) {
        String sql = "select * from PHIEUNHAP WHERE NgayTao = '";
        sql += date + "'";
        return this.selectBySql(sql);
    }
}
