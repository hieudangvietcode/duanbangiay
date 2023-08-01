/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemban.service.impl;

import com.mycompany.phanmemban.model.HoaDonChiTiet;
import com.mycompany.phanmemban.repostority.HoaDonChiTietRepostority;
import com.mycompany.phanmemban.service.HoaDonChiTietService;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {

    private HoaDonChiTietRepostority hdctr = new HoaDonChiTietRepostority();

    @Override
    public List<HoaDonChiTiet> getAllData() {
        return hdctr.getAllData();
    }

    @Override
    public String add(HoaDonChiTiet hoaDonChiTiet) {
        if(hdctr.add(hoaDonChiTiet)){
            return "Thêm thành công";
        }else {
            return "Thêm thất bại";
        }
    }

    @Override
    public void update(HoaDonChiTiet hdct) {
        hdctr.update(hdct);
    }

    @Override
    public Integer delete(Integer soluong) {
        return hdctr.delete(soluong);
    }
    
    @Override
    public double tongtien() {
        return hdctr.tongtien();
    }

//    @Override
//    public double tongtien() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

}
