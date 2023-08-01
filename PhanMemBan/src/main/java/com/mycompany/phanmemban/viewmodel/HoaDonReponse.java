/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemban.viewmodel;

import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonReponse {

    private UUID idHoaDon;
    private String maHoaDon;
    private Date ngayTao;
    private String tenNhanVien;
    private String tenKhachHang;
    private String maDT;
    private String maKM;
    private int tinhTrang;
    private String sdt;
    
}
