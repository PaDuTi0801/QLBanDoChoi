/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.Dao;

import com.fpt.Entitys.KhachHang;
import com.fpt.Jdbchelper.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HuynhQuangLe
 */
public class KhachHangDAO implements DAO<KhachHang, Integer> {

    protected String INSERT = "INSERT INTO KHACHHANG (MAKH,TENKH,SODIENTHOAI,DIACHI) VALUES (?,?,?,?)";
    protected String UPDATE = "UPDATE KHACHHANG SET TENKH=?, SODIENTHOAI=?,DIACHI=? WHERE MAKH=?";
    protected String DELETE = "UPDATE KHACHHANG SET ISACTIVE =0 WHERE MAKH=?";
    protected String MAXID = "SELECT MAX(MAKH) AS MaxCode FROM KHACHHANG";
    protected String SELECTALL = "SELECT * FROM KHACHHANG WHERE ISACTIVE =1";
    protected String SELECTBYID = "SELECT * FROM KHACHHANG WHERE MAKH =? AND ISACTIVE =1";
    protected String SELECTBYIDNOISACTIVE = "SELECT * FROM KHACHHANG WHERE MAKH =?";
    protected String SELECTBYKEYWORD = "SELECT * FROM KHACHHANG WHERE (MAKH = ? OR TENKH LIKE ? OR SODIENTHOAI LIKE ? OR DIACHI LIKE ?) AND isactive = 1";

    @Override
    public int insert(KhachHang entity) {
        try {
            Jdbc.update(INSERT, entity.getMaKH(), entity.getTenKH(), entity.getSoDT(), entity.getDiaChi());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(KhachHang entity) {
        try {
            Jdbc.update(UPDATE, entity.getTenKH(), entity.getSoDT(), entity.getDiaChi(), entity.getMaKH());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(KhachHang entity) {
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
        String MaxCode = "";
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
    public KhachHang selectById(Integer id) {
        List<KhachHang> list = selectBySql(SELECTBYID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public KhachHang selectByIdNoIsactive(Integer id) {
        List<KhachHang> list = selectBySql(SELECTBYIDNOISACTIVE, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhachHang> selectAll() {
        return selectBySql(SELECTALL);
    }

    @Override
    public List<KhachHang> selectByKeyword(String keyword) {
        try {
            float so = 0;
            if (keyword.isEmpty()) {
                so = 0;
            } else if (keyword.chars().allMatch(Character::isDigit)) {
                so = Float.parseFloat(keyword);
            }
            return this.selectBySql(SELECTBYKEYWORD, so, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.query(sql, args);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getInt(1));
                kh.setTenKH(rs.getString(2));
                kh.setSoDT(rs.getString(3));
                kh.setDiaChi(rs.getString(4));
                kh.setIsactive(rs.getBoolean(5));
                list.add(kh);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
