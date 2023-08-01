/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.phanmemban.service;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface HoaDonSer {
    String suaTrangThai(int tt, String ma,Date ngatt);
     public void themhdct(String ma, String msp, int sl,BigDecimal gia);
}
