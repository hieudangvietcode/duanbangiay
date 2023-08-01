/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemban.service.impl;

import com.mycompany.phanmemban.repostority.HoaDonRepo;
import com.mycompany.phanmemban.service.HoaDonSer;
import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class HoaDonSerImpl implements HoaDonSer {

    private HoaDonRepo rp = new HoaDonRepo();

    public String suaTrangThai(int tt, String ma, Date ngaytt) {
        if (rp.update(tt, ma, ngaytt)) {
            return "Thanh toán thành công";
        } else {
            return "Thanh toán thất bại";
        }
    }
    public void themhdct(String ma, String msp, int sl,BigDecimal gia) {

        rp.Thehdct(ma, msp, sl,gia);

    }

}
