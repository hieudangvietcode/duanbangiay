/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemban.service.impl;

import com.mycompany.phanmemban.model.DoiTra;
import com.mycompany.phanmemban.model.HoaDon;
import com.mycompany.phanmemban.model.KhachHang;
import com.mycompany.phanmemban.model.KhuyenMai;
import com.mycompany.phanmemban.model.NhanVien;
import com.mycompany.phanmemban.repostority.DoiTraReponsitory;
import com.mycompany.phanmemban.repostority.HoaDonRepo;
import com.mycompany.phanmemban.repostority.HoaDonReponsitory;
import com.mycompany.phanmemban.repostority.KhachHangRepostority;
import com.mycompany.phanmemban.repostority.KhuyenMaiRepostority;
import com.mycompany.phanmemban.repostority.NhanVienRepostority;
import com.mycompany.phanmemban.service.HoaDonService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class HoaDonServiceImpl implements HoaDonService {

    private HoaDonReponsitory hoaDonrep = new HoaDonReponsitory();
    private KhuyenMaiRepostority kmREpo = new KhuyenMaiRepostority();
    private DoiTraReponsitory doiTraRepo = new DoiTraReponsitory();
    private NhanVienRepostority nhanVienRepo = new NhanVienRepostority();
    private KhachHangRepostority khachHangRepo = new KhachHangRepostority();
    private HoaDonRepo rp =new HoaDonRepo();
    @Override
    public ArrayList<HoaDon> loadData() {
        return (ArrayList<HoaDon>) hoaDonrep.getList();
    }
//    public String suaTrangThai(int tt, String ma){
//        if (rp.update(tt, ma)) {
//            return "Thanh toán thành công";
//        }else{
//            return "Thanh toán thất bại";
//        }
//    }

    @Override
    public String update(String ma,String tt) {
       if(hoaDonrep.update(0, ma)){
           return "Thanh toán thành công";
       }else {
           return "Thanh toán thất bại";
       }
    }

    @Override
    public String add(HoaDon hoaDon) {
        if(hoaDonrep.add(hoaDon)){
            return "Thêm thành công";
        }else {
            return "Thêm thất bại";
        }
    }

    @Override
    public UUID delete(UUID id) {
        return hoaDonrep.delete(id);
    }

    @Override
    public List<String> listcbbhd() {
        return hoaDonrep.listcbbhd();
    }

//    @Override
//    public UUID findByIdKM(String ma) {
//        return hoaDonrep.findByIdKM(ma);
//    }
//
//    @Override
//    public UUID findByIdDT(String ma) {
//        return hoaDonrep.findByIdDT(ma);
    @Override
    public ArrayList<KhuyenMai> getListKM() {
        return (ArrayList<KhuyenMai>) kmREpo.getAll();
    }

    @Override
    public ArrayList<DoiTra> getListDT() {
        return (ArrayList<DoiTra>) doiTraRepo.getAll();
    }

    @Override
    public ArrayList<KhachHang> getListKH() {
        return (ArrayList<KhachHang>) khachHangRepo.getAll();
    }

    @Override
    public ArrayList<NhanVien> getListNV() {
        return (ArrayList<NhanVien>) nhanVienRepo.getAll();
    }

    @Override
    public void ADD(HoaDon hoaDon) {
        hoaDonrep.ADD(hoaDon);
    }

//    @Override
//    public UUID findByidNV(String ten) {
//        return hoaDonrep.finByidnv(ten);
//    }
//
//    @Override
//    public UUID findByidKH(String ten) {
//        return hoaDonrep.finByidkh(ten);
//    }
//
//    @Override
//    public UUID findByIdKM(String ma) {
//        return hoaDonrep.findByIdKM(ma);
//        
//    }
//
//    @Override
//    public UUID findByIdDT(String ma) {
//        return hoaDonrep.findByIdDT(ma);

    @Override
    public HoaDon create(HoaDon hoaDon) {
        return hoaDonrep.create(hoaDon);
    }
        
    

}
