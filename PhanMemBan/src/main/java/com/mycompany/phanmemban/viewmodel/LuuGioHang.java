/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemban.viewmodel;

/**
 *
 * @author PC
 */
public class LuuGioHang {

    private String mahd;
    private String maSp;
    private String tenSP;
    private int soLuong;
    private int donGia;

    public LuuGioHang() {
    }

    public LuuGioHang(String maSp, String tenSP, int soLuong, int donGia) {
        this.maSp = maSp;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public LuuGioHang(String mahd, String maSp, String tenSP, int soLuong, int donGia) {
        this.mahd = mahd;
        this.maSp = maSp;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "LuuGioHang{" + "mahd=" + mahd + ", maSp=" + maSp + ", tenSP=" + tenSP + ", soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }

    public int tongTien() {
        return donGia * soLuong;
    }

}
