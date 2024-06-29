/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.Dao;

import com.fpt.Entitys.NhanVien;
import com.fpt.Jdbchelper.Jdbc;
import com.fpt.Utils.PasswordHash;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HuynhQuangLe
 */
public class NhanVienDAO implements DAO<NhanVien, String> {

    protected String INSERT = "INSERT INTO NHANVIEN (MANV , TENNV, EMAIL,SODIENTHOAI,DIACHI,MATKHAU,QUYEN) VALUES (?,?,?,?,?,?,?)";
    protected String UPDATE = "UPDATE NHANVIEN SET TENNV=?, EMAIL=? , SODIENTHOAI=?,DIACHI=?, MATKHAU=?,QUYEN=? WHERE MANV=?";
    protected String DELETE = "UPDATE NHANVIEN SET ISACTIVE =0 WHERE MANV=?";
    protected String MAXID = "SELECT MAX(MANV) AS MaxCode FROM NHANVIEN";
    protected String SELECTALL = "SELECT * FROM NHANVIEN WHERE ISACTIVE =1";
    protected String SELECTBYID = "SELECT * FROM NHANVIEN WHERE MANV =? AND ISACTIVE =1";
    protected String SELECTBYIDNOISACTIVE = "SELECT * FROM NHANVIEN WHERE MANV =?";
    protected String SELECTBYKEYWORD = "SELECT * FROM NHANVIEN WHERE (MANV = ? OR TENNV LIKE ? OR EMAIL LIKE ? OR SODIENTHOAI LIKE ?) AND isactive = 1";

    public String nextID(String currentMaxCode) {
        if (currentMaxCode == null) {
            return "NV001";
        } else {
            int numericPart = Integer.parseInt(currentMaxCode.substring(2));
            numericPart++;
            return String.format("NV%03d", numericPart);
        }
    }

    @Override
    public int insert(NhanVien entity) {
        try {
            Jdbc.update(INSERT, entity.getMaNV(), entity.getTenNV(), entity.getEmail(), entity.getSoDT(), entity.getDiaChi(), PasswordHash.passwordHasd("1234"), entity.getQuyen());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(NhanVien entity) {
        try {
            Jdbc.update(UPDATE, entity.getTenNV(), entity.getEmail(), entity.getSoDT(), entity.getDiaChi(), entity.getMatKhau(), entity.getQuyen(), entity.getMaNV());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(NhanVien entity) {
        try {
            Jdbc.update(DELETE, entity);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public String maxID() {
        String MaxCode="";
        try {
            ResultSet rs = Jdbc.query(MAXID);
            if (rs.next()) {
                MaxCode = rs.getString("MaxCode");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return MaxCode;
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = selectBySql(SELECTBYID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public NhanVien selectByIdNoIsactive(String id) {
        List<NhanVien> list = selectBySql(SELECTBYIDNOISACTIVE, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySql(SELECTALL);
    }

    @Override
    public List<NhanVien> selectByKeyword(String keyword) {
        try {
            return this.selectBySql(SELECTBYKEYWORD, keyword, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.query(sql, args);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setTenNV(rs.getString(2));
                nv.setEmail(rs.getString(3));
                nv.setSoDT(rs.getString(4));
                nv.setDiaChi(rs.getString(5));
                nv.setMatKhau(rs.getString(6));
                nv.setQuyen(rs.getString(7));
                nv.setIsactive(rs.getBoolean(8));
                list.add(nv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
