package qlsmp.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import qlsmp.DB.DBHelper;
import qlsmp.Model.CTPN;

/**
 *
 * @author My Laptop
 */
public class CTPNDao {

    String SELECT_ALL = "select MaCTPN,SL,GiaNhap,MaPN,MaSP,NSX from CTPN ";
    String INSERT_SQL = "INSERT INTO CTPN(SL,GiaNhap,MaPN,MaSP,NSX) VALUES(?,?,?,?,?)";
   // String SELECT_PN_NEW = "SELECT TOP 1 MaPN,NgayTao,NguoiTao,ThanhTien,MaAccount FROM PHIEUNHAP ORDER BY MaPN DESC";


    public void insert(CTPN enity) {
        DBHelper.update(INSERT_SQL, enity.getSl(), enity.getGia(), enity.getMaPN(), enity.getMaSP(),enity.getNsx());
    }
   
   public List<CTPN> selectBySql(String sql, Object... args) {
        List<CTPN> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                CTPN enity = new CTPN();
                enity.setMaCTPN(rs.getInt("MaCTPN"));
                enity.setSl(rs.getInt("SL"));
                enity.setGia(rs.getDouble("GiaNhap"));
                enity.setMaPN(rs.getInt("MaPN"));
                enity.setMaSP(rs.getInt("MaSP"));
                enity.setNsx(rs.getString("NSX"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
   
    public List<CTPN> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }
}
