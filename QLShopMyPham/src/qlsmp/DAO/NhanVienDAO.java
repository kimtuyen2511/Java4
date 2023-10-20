/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsmp.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import qlsmp.DB.DBHelper;
import qlsmp.Model.NhanVien;

/**
 *
 * @author Anh Thu
 */
public class NhanVienDAO {

    String INSERT_SQL = "SET IDENTITY_INSERT NhanVien ON; INSERT INTO NhanVien(MaNV,TenNV,GioiTinh,NgaySinh,SDT, DiaChi, CCCD, Email, HinhAnh)VALUES (?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE NhanVien SET TenNV =?, GioiTinh=?, NgaySinh =?, SDT=?, DiaChi=?, CCCD=?, Email =?, HinhAnh =? WHERE MaNV=?";
    String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV = ?";
    String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNV = ?";

    public void insert(NhanVien entity) {
        DBHelper.update(INSERT_SQL, entity.getMaNV(), entity.getHoTen(), entity.getGioiTinh(), entity.getNgaySinh(), entity.getDienThoai(), entity.getDiaChi(), entity.getCCCD(), entity.getEmail(),entity.getHinhAnh());
    }

    public void update(NhanVien entity) {
        DBHelper.update(UPDATE_SQL, entity.getHoTen(), entity.getGioiTinh(), entity.getNgaySinh(), entity.getDienThoai(), entity.getDiaChi(), entity.getCCCD(), entity.getEmail(),entity.getHinhAnh(), entity.getMaNV());
    }

    public void delete(String id) {
        DBHelper.update(DELETE_SQL, id);

    }

    public NhanVien selectById(String id) {
        List<NhanVien> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<NhanVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setHoTen(rs.getString("TenNV"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setNgaySinh(rs.getString("NgaySinh"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setDienThoai(rs.getString("SDT"));
                entity.setCCCD(rs.getString("CCCD"));
                entity.setEmail(rs.getString("Email"));
                entity.setHinhAnh(rs.getString("HinhAnh"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<NhanVien> selectByKeyWord(String keyword) {
        String sql = "SELECT * FROM NhanVien WHERE TenNV LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

//    public List<NhanVien> selectNotInCourse(int makh, String keyword) {
//        String sql = "SELECT * FROM NguoiHoc "
//                + "WHERE HoTen LIKE ? AND "
//                + " MANH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH = ?)";
//        return this.selectBySql(sql, "%" + keyword + "%", makh);
//    }
}
