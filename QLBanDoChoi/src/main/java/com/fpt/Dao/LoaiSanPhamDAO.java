/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.Dao;

import com.fpt.Entitys.LoaiSanPham;
import com.fpt.Jdbchelper.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HuynhQuangLe
 */
public class LoaiSanPhamDAO implements DAO<LoaiSanPham, Integer> {

    protected String INSERT = "INSERT INTO LOAISANPHAM (MALOAI,TENLOAI,MOTA) VALUES (?,?,?)";
    protected String UPDATE = "UPDATE LOAISANPHAM SET TENLOAI=?, MOTA=? WHERE MALOAI=?";
    protected String DELETE = "UPDATE LOAISANPHAM SET ISACTIVE =0 WHERE MALOAI=?";
    protected String MAXID = "SELECT MAX(MALOAI) AS MaxCode FROM LOAISANPHAM";
    protected String SELECTALL = "SELECT * FROM LOAISANPHAM WHERE ISACTIVE =1";
    protected String SELECTBYID = "SELECT * FROM LOAISANPHAM WHERE MALOAI =? AND ISACTIVE =1";
    protected String SELECTBYIDNOISACTIVE = "SELECT * FROM LOAISANPHAM WHERE MALOAI =?";
    protected String SELECTBYKEYWORD = "SELECT * FROM LOAISANPHAM WHERE (MALOAI = ? OR TENLOAI LIKE ? OR MOTA LIKE ?) AND isactive = 1";

    @Override
    public int insert(LoaiSanPham entity) {
        try {
            Jdbc.update(INSERT,entity.getMa(), entity.getTen(), entity.getMoTa());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(LoaiSanPham entity) {
        try {
            Jdbc.update(UPDATE, entity.getTen(), entity.getMoTa(), entity.getMa());
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int delete(LoaiSanPham entity) {
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
    public LoaiSanPham selectById(Integer id) {
        List<LoaiSanPham> list = selectBySql(SELECTBYID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public LoaiSanPham selectByIdNoIsactive(Integer id) {
        List<LoaiSanPham> list = selectBySql(SELECTBYIDNOISACTIVE, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<LoaiSanPham> selectAll() {
        return selectBySql(SELECTALL);
    }

    @Override
    public List<LoaiSanPham> selectByKeyword(String keyword) {
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
    public List<LoaiSanPham> selectBySql(String sql, Object... args) {
        List<LoaiSanPham> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.query(sql, args);
            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham();
                lsp.setMa(rs.getInt(1));
                lsp.setTen(rs.getString(2));
                lsp.setMoTa(rs.getString(3));
                lsp.setIsactive(rs.getBoolean(4));
                list.add(lsp);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
