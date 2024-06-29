/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.Entitys;

import java.util.Objects;

/**
 *
 * @author HuynhQuangLe
 */
public class LoaiSanPham {
    private int ma;
    private String ten,moTa;
    private boolean isactive;

    public LoaiSanPham() {
    }

    public LoaiSanPham(int ma, String ten, String moTa, boolean isactive) {
        this.ma = ma;
        this.ten = ten;
        this.moTa = moTa;
        this.isactive = isactive;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
  @Override
    public String toString() {
        return ten ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.ma;
        hash = 73 * hash + Objects.hashCode(this.ten);
        hash = 73 * hash + Objects.hashCode(this.moTa);
        hash = 73 * hash + (this.isactive ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LoaiSanPham other = (LoaiSanPham) obj;
        if (this.ma != other.ma) {
            return false;
        }
        if (this.isactive != other.isactive) {
            return false;
        }
        if (!Objects.equals(this.ten, other.ten)) {
            return false;
        }
        return Objects.equals(this.moTa, other.moTa);
    }

   
    
}
