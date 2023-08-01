/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemban.service.impl;

import com.mycompany.phanmemban.repostority.GiohangJDBCRepo;
import com.mycompany.phanmemban.service.GioHangJDBCService;
import com.mycompany.phanmemban.viewmodel.LuuGioHang;
import java.util.List;

/**
 *
 * @author PC
 */
public class GiohangJdbcServiceimpl implements GioHangJDBCService{
    GiohangJDBCRepo rp=new GiohangJDBCRepo();
    
    public void clickkgh(String ma,int sl){
        rp.clickGH(ma, sl);
    }
    public List<LuuGioHang> loadGH(){
        return rp.getListGiohang();
    }
    public void bttthd(){
        rp.bttaohoadon();
    }
    public List<LuuGioHang> clickBanghd(){
        return rp.clickbanghd();
    }
}
