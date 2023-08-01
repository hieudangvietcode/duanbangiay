/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemban.service;

import com.mycompany.phanmemban.model.CuaHang;
import com.mycompany.phanmemban.model.DoiTra;
import com.mycompany.phanmemban.model.HoaDon;
import com.mycompany.phanmemban.model.KhachHang;
import com.mycompany.phanmemban.model.KhuyenMai;
import com.mycompany.phanmemban.model.NhanVien;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface HoaDonService {

    public ArrayList<HoaDon> loadData();

    String update(String ma,String tt);

    String add(HoaDon hoaDon);

    public UUID delete(UUID id);

    public List<String> listcbbhd();
    
    public ArrayList<KhuyenMai> getListKM();
    
    public ArrayList<DoiTra> getListDT();
    
    public ArrayList<KhachHang> getListKH();
    
    public ArrayList<NhanVien> getListNV();

    public void ADD(HoaDon hoaDon);
    
    public HoaDon create(HoaDon hoaDon);
    
//     String suaTrangThai(int tt, String ma);

}
