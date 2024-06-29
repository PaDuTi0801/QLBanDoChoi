/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.Entitys;

/**
 *
 * @author HuynhQuangLe
 */
public class SanPham {
    private int ma,soLuong,maMau,maLoai,maXuatXu;
    private String ten,hinh,maVach;
    private double giaNhap,giaBan;
    private boolean isactive;

    public SanPham() {
        giaNhap=1000;
        giaBan=1100;
        soLuong=5;        
    }

    public SanPham(int ma, int soLuong, int maMau, int maLoai, int maXuatXu, String ten, String hinh, String maVach, double giaNhap, double giaBan, boolean isactive) {
        this.ma = ma;
        this.soLuong = soLuong;
        this.maMau = maMau;
        this.maLoai = maLoai;
        this.maXuatXu = maXuatXu;
        this.ten = ten;
        this.hinh = hinh;
        this.maVach = maVach;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.isactive = isactive;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaMau() {
        return maMau;
    }

    public void setMaMau(int maMau) {
        this.maMau = maMau;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getMaXuatXu() {
        return maXuatXu;
    }

    public void setMaXuatXu(int maXuatXu) {
        this.maXuatXu = maXuatXu;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMaVach() {
        return maVach;
    }

    public void setMaVach(String maVach) {
        this.maVach = maVach;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
    
}
