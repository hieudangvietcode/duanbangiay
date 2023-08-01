/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemban.viewmodel;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class HoaDonCT {
    private UUID id;
    private String maHD;
    private String tenKH;
    private String tenNV;
    private String maKM;
    private String maDT;
    private Date ngayTao;
    private Date ngayThanhToan;
    private int tinhTrang;
    private String diaChi;
    private String sdt;

    public HoaDonCT() {
    }

    public HoaDonCT(UUID id, String maHD, String tenKH, String tenNV, String maKM, String maDT, Date ngayTao, Date ngayThanhToan, int tinhTrang, String diaChi, String sdt) {
        this.id = id;
        this.maHD = maHD;
        this.tenKH = tenKH;
        this.tenNV = tenNV;
        this.maKM = maKM;
        this.maDT = maDT;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tinhTrang = tinhTrang;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getMaDT() {
        return maDT;
    }

    public void setMaDT(String maDT) {
        this.maDT = maDT;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
}

