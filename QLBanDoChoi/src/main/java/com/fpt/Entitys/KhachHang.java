/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.Entitys;

/**
 *
 * @author HuynhQuangLe
 */
public class KhachHang {

    private int maKH;
    private String tenKH, soDT, diaChi;
    private boolean isactive;

    public KhachHang() {
    }

    public KhachHang(int maKH, String tenKH, String soDT, String diaChi, boolean isactive) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.soDT = soDT;
        this.diaChi = diaChi;
        this.isactive = isactive;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
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

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

}
