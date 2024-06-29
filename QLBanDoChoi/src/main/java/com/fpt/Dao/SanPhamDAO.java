/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.Dao;

import com.fpt.Entitys.LoaiSanPham;
import com.fpt.Entitys.MauSac;
import com.fpt.Entitys.SanPham;
import com.fpt.Jdbchelper.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HuynhQuangLe
 */
public class SanPhamDAO implements DAO<SanPham, Integer> {

    protected String INSERT = "INSERT INTO SANPHAM (MASP,TENSP,GIANHAP,GIABAN,SOLUONGTON,MAMAU,MALOAI,MAXUATXU,MAVACH,HINH) VALUES (?,?,?,?,?,?,?,?,?,?)";
    protected String UPDATE = "UPDATE SANPHAM SET TENSP=?, GIANHAP=?, GIABAN=?, SOLUONGTON=?, MAMAU=?, MALOAI=?, MAXUATXU=?, MAVACH=?, HINH=? WHERE MASP=?";
    protected String DELETE = "UPDATE SANPHAM SET ISACTIVE =0 WHERE MASP=?";
    protected String MAXID = "SELECT MAX(MASP) AS MaxCode FROM SANPHAM";
    protected String SELECTALL = "SELECT * FROM SANPHAM WHERE ISACTIVE =1";
    protected String SELECTBYID = "SELECT * FROM SANPHAM WHERE MASP =? AND ISACTIVE =1";
    protected String SELECTBYIDNOISACTIVE = "SELECT * FROM SANPHAM WHERE MASP =?";
    protected String SELECTBYKEYWORD = "SELECT * FROM SANPHAM WHERE (MASP = ? OR TENSP LIKE ? OR GIABAN = ? OR SOLUONGTON=?) AND isactive = 1";

    @Override
    public int insert(SanPham entity) {
        try {
            Jdbc.update(INSERT, entity.getMa(),entity.getTen(),entity.getGiaNhap(),entity.getGiaBan(),entity.getSoLuong()
                    ,entity.getMaMau(),entity.getMaLoai(),entity.getMaXuatXu(),entity.getMaVach(),entity.getHinh());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(SanPham entity) {
        try {
              Jdbc.update(INSERT,entity.getTen(),entity.getGiaNhap(),entity.getGiaBan(),entity.getSoLuong()
                    ,entity.getMaMau(),entity.getMaLoai(),entity.getMaXuatXu(),entity.getMaVach(),entity.getHinh(), entity.getMa());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(SanPham entity) {
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
    public SanPham selectById(Integer id) {
        List<SanPham> list = selectBySql(SELECTBYID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public SanPham selectByIdNoIsactive(Integer id) {
        List<SanPham> list = selectBySql(SELECTBYIDNOISACTIVE, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SanPham> selectAll() {
        return selectBySql(SELECTALL);
    }

    @Override
    public List<SanPham> selectByKeyword(String keyword) {
        try {
            float so = 0;
            if (keyword.isEmpty()) {
                so = 0;
            } else if (keyword.chars().allMatch(Character::isDigit)) {
                so = Float.parseFloat(keyword);
            }
            return this.selectBySql(SELECTBYKEYWORD, so, "%" + keyword + "%",so,so);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.query(sql, args);
            while (rs.next()) {
                SanPham ms = new SanPham();
                ms.setMa(rs.getInt(1));
                ms.setTen(rs.getString(2));
                ms.setGiaNhap(rs.getDouble(3));
                ms.setGiaBan(rs.getDouble(4));
                ms.setSoLuong(rs.getInt(5));
                ms.setMaMau(rs.getInt(6));
                ms.setMaLoai(rs.getInt(7));
                ms.setMaXuatXu(rs.getInt(8));
                ms.setMaVach(rs.getString(9));
                ms.setHinh(rs.getString(10));
                ms.setIsactive(rs.getBoolean(11));
                list.add(ms);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
