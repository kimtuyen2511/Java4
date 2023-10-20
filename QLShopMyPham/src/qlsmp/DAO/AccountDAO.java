package qlsmp.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import qlsmp.DB.DBHelper;
import qlsmp.Model.Account;

public class AccountDAO extends ShopMyPhamDAO<Account, String> {

    String SELECT_ALL = "SELECT MaAccount,UserName,Pass,ChucVu,MaNV FROM ACCOUNT ";
    String INSERT_SQL = "INSERT INTO ACCOUNT(UserName,Pass,ChucVu,MaNV) VALUES (?,?,?,?)";
    String DELETE_SQL = "DELETE ACCOUNT WHERE MaAccount = ?";
    String UPDATE_SQL = "UPDATE ACCOUNT SET UserName=?, Pass=?, ChucVu=?, MaNV=? WHERE MaAccount=?";
    String SELECT_BY_ID_SQL = "SELECT MaAccount,UserName,Pass,ChucVu,MaNV FROM ACCOUNT WHERE MaAccount=?";
    String SELECT_BY_IDNV_SQL = "SELECT MaAccount,UserName,Pass,ChucVu,MaNV FROM ACCOUNT WHERE MaNV=?";
    String SELECT_MA_TOP1_DESC = "SELECT TOP 1 MaAccount,UserName,Pass,ChucVu,MaNV FROM ACCOUNT ORDER BY MaAccount DESC";
    String SELECT_BY_USERNAME_SQL = "SELECT MaAccount,UserName,Pass,ChucVu,MaNV FROM ACCOUNT WHERE UserName=?";
    @Override
    public void insert(Account enity) {
        DBHelper.update(INSERT_SQL, enity.getUsername(), enity.getPass(), enity.isVaiTro(), enity.getMaNV());
    }

    @Override
    public void update(Account enity) {
        DBHelper.update(UPDATE_SQL, enity.getUsername(), enity.getPass(), enity.isVaiTro(), enity.getMaNV(), enity.getMaAccount());
    }

    @Override
    public void delete(int id) {
        DBHelper.update(DELETE_SQL, id);
    }

    @Override
    public Account selecteByID(int id) {
        List<Account> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public Account selecteByUsername (String username) {
        List<Account> list = this.selectBySql(SELECT_BY_USERNAME_SQL, username);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
// để tìm kiếm

    public Account selecteByMaNV(int id) {
        List<Account> list = this.selectBySql(SELECT_BY_IDNV_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Account> selectBySql(String sql, Object... args) {
        List<Account> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                Account enity = new Account();
                enity.setMaAccount(rs.getInt("MaAccount"));
                enity.setUsername(rs.getString("UserName"));
                enity.setPass(rs.getString("Pass"));
                enity.setVaiTro(rs.getBoolean("ChucVu"));
                enity.setMaNV(rs.getInt("MaNV"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account selectByKeyword(String keyword) {
        Connection con = DBHelper.getDBConnection();
        String sql = SELECT_ALL + " WHERE UserName like N'" + keyword + "'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Account enity = new Account();
                enity.setMaAccount(rs.getInt("MaAccount"));
                enity.setUsername(rs.getString("UserName"));
                enity.setPass(rs.getString("Pass"));
                enity.setVaiTro(rs.getBoolean("ChucVu"));
                enity.setMaNV(rs.getInt("MaNV"));
                return enity;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<Account> selectAll() {
        return this.selectBySql(SELECT_ALL);
    }

    public int selectMaTop1DESC() {
        try {
            ResultSet rs = DBHelper.query(SELECT_MA_TOP1_DESC);
            while (rs.next()) {
                Account enity = new Account();
                enity.setMaAccount(rs.getInt("MaAccount"));
                return enity.getMaAccount();
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

}
