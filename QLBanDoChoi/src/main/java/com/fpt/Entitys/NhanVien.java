/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.Entitys;

/**
 *
 * @author HuynhQuangLe
 */
public class NhanVien {

    private String maNV, tenNV, email, soDT, diaChi, matKhau, quyen;
    private boolean isactive;

    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, String email, String soDT, String diaChi, String matKhau, String quyen, boolean isactive) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.email = email;
        this.soDT = soDT;
        this.diaChi = diaChi;
        this.matKhau = matKhau;
        this.quyen = quyen;
        this.isactive = isactive;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

}
