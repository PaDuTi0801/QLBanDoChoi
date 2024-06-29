/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.Dao;

import com.fpt.Entitys.LoaiSanPham;
import com.fpt.Entitys.MauSac;
import com.fpt.Jdbchelper.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HuynhQuangLe
 */
public class MauSacDAO implements DAO<MauSac, Integer> {

    protected String INSERT = "INSERT INTO MAUSAC (MAMAU,TENMAU,MOTA) VALUES (?,?,?)";
    protected String UPDATE = "UPDATE MAUSAC SET TENMAU=?, MOTA=? WHERE MAMAU=?";
    protected String DELETE = "UPDATE MAUSAC SET ISACTIVE =0 WHERE MAMAU=?";
    protected String MAXID = "SELECT MAX(MAMAU) AS MaxCode FROM MAUSAC";
    protected String SELECTALL = "SELECT * FROM MAUSAC WHERE ISACTIVE =1";
    protected String SELECTBYID = "SELECT * FROM MAUSAC WHERE MAMAU =? AND ISACTIVE =1";
    protected String SELECTBYIDNOISACTIVE = "SELECT * FROM MAUSAC WHERE MAMAU =?";
    protected String SELECTBYKEYWORD = "SELECT * FROM MAUSAC WHERE (MAMAU = ? OR TENMAU LIKE ? OR MOTA LIKE ?) AND isactive = 1";

    @Override
    public int insert(MauSac entity) {
        try {
            Jdbc.update(INSERT, entity.getMa(), entity.getTen(), entity.getMoTa());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(MauSac entity) {
        try {
            Jdbc.update(UPDATE, entity.getTen(), entity.getMoTa(), entity.getMa());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(MauSac entity) {
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
    public MauSac selectById(Integer id) {
        List<MauSac> list = selectBySql(SELECTBYID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public MauSac selectByIdNoIsactive(Integer id) {
        List<MauSac> list = selectBySql(SELECTBYIDNOISACTIVE, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<MauSac> selectAll() {
        return selectBySql(SELECTALL);
    }

    @Override
    public List<MauSac> selectByKeyword(String keyword) {
        try {
            float so = 0;
            if (keyword.isEmpty()) {
                so = 0;
            } else if (keyword.chars().allMatch(Character::isDigit)) {
                so = Float.parseFloat(keyword);
            }
            return this.selectBySql(SELECTBYKEYWORD, so, "%" + keyword + "%", "%" + keyword + "%");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<MauSac> selectBySql(String sql, Object... args) {
        List<MauSac> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.query(sql, args);
            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setMa(rs.getInt(1));
                ms.setTen(rs.getString(2));
                ms.setMoTa(rs.getString(3));
                ms.setIsactive(rs.getBoolean(4));
                list.add(ms);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
