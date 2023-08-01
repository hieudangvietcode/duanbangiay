/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemban.service;

import com.mycompany.phanmemban.model.HoaDonChiTiet;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface HoaDonChiTietService {

    public List<HoaDonChiTiet> getAllData();

    String add(HoaDonChiTiet hoaDonChiTiet);

    public void update(HoaDonChiTiet hdct);

    public Integer delete(Integer soluong);

    public double tongtien();
}
