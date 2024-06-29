/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.Dao;

import com.fpt.Entitys.LoaiSanPham;
import com.fpt.Entitys.MauSac;
import com.fpt.Entitys.XuatXu;
import com.fpt.Jdbchelper.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HuynhQuangLe
 */
public class XuatXuDAO implements DAO<XuatXu, Integer> {

    protected String INSERT = "INSERT INTO XUATXU (MAXUATXU,TENQUOCGIA,MOTA) VALUES (?,?,?)";
    protected String UPDATE = "UPDATE XUATXU SET TENQUOCGIA=?, MOTA=? WHERE MAXUATXU=?";
    protected String DELETE = "UPDATE XUATXU SET ISACTIVE =0 WHERE MAXUATXU=?";
    protected String MAXID = "SELECT MAX(MAXUATXU) AS MaxCode FROM XUATXU";
    protected String SELECTALL = "SELECT * FROM XUATXU WHERE ISACTIVE =1";
    protected String SELECTBYID = "SELECT * FROM XUATXU WHERE MAXUATXU =? AND ISACTIVE =1";
    protected String SELECTBYIDNOISACTIVE = "SELECT * FROM XUATXU WHERE MAXUATXU =?";
    protected String SELECTBYKEYWORD = "SELECT * FROM XUATXU WHERE (MAXUATXU = ? OR TENQUOCGIA LIKE ? OR MOTA LIKE ?) AND isactive = 1";

    @Override
    public int insert(XuatXu entity) {
        try {
            Jdbc.update(INSERT, entity.getMa(), entity.getTen(), entity.getMoTa());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(XuatXu entity) {
        try {
            Jdbc.update(UPDATE, entity.getTen(), entity.getMoTa(), entity.getMa());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(XuatXu entity) {
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
    public XuatXu selectById(Integer id) {
        List<XuatXu> list = selectBySql(SELECTBYID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public XuatXu selectByIdNoIsactive(Integer id) {
        List<XuatXu> list = selectBySql(SELECTBYIDNOISACTIVE, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<XuatXu> selectAll() {
        return selectBySql(SELECTALL);
    }

    @Override
    public List<XuatXu> selectByKeyword(String keyword) {
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
    public List<XuatXu> selectBySql(String sql, Object... args) {
        List<XuatXu> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.query(sql, args);
            while (rs.next()) {
                XuatXu xx = new XuatXu();
                xx.setMa(rs.getInt(1));
                xx.setTen(rs.getString(2));
                xx.setMoTa(rs.getString(3));
                xx.setIsactive(rs.getBoolean(4));
                list.add(xx);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
