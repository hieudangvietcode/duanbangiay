/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemban.service;

import com.mycompany.phanmemban.viewmodel.LuuGioHang;
import java.util.List;

/**
 *
 * @author PC
 */
public interface GioHangJDBCService {

    public void clickkgh(String ma, int sl);

    public List<LuuGioHang> loadGH();

    public void bttthd();

    public List<LuuGioHang> clickBanghd();
}
