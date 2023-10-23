package qlsmp.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import qlsmp.DB.DBHelper;
import qlsmp.Model.DoanhThu;

public class DoanhThuDAO {

    String SELECT_DOANHTHU_NAM = "EXEC SMP_DOANHTHU_NAM";
    String SELECT_DOANHTHU_QUI1 = "SMP_DOANHTHU_QUI1";
    String SELECT_DOANHTHU_QUI2 = "SMP_DOANHTHU_QUI2";
    String SELECT_DOANHTHU_QUI3 = "SMP_DOANHTHU_QUI3";
    String SELECT_DOANHTHU_QUI4 = "SMP_DOANHTHU_QUI4";

    protected List<DoanhThu> selectBySql(String sql, Object... args) {
        List<DoanhThu> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                DoanhThu entity = new DoanhThu();
                entity.setThang(rs.getInt("Thang"));
                entity.setDoanhThu(rs.getDouble("DoanhThu"));
                entity.setChiPhi(rs.getDouble("ChiPhi"));
                entity.setLoiNhuan(rs.getDouble("LoiNhuan"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<DoanhThu> selectNAM() {
        return this.selectBySql(SELECT_DOANHTHU_NAM);
    }

    public List<DoanhThu> selectQUI1() {
        return this.selectBySql(SELECT_DOANHTHU_QUI1);
    }

    public List<DoanhThu> selectQUI2() {
        return this.selectBySql(SELECT_DOANHTHU_QUI2);
    }

    public List<DoanhThu> selectQUI3() {
        return this.selectBySql(SELECT_DOANHTHU_QUI3);
    }

    public List<DoanhThu> selectQUI4() {
        return this.selectBySql(SELECT_DOANHTHU_QUI4);
    }

}
