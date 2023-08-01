/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemban.viewmodel;

import java.math.BigDecimal;
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
public class ChiTietSanPhamCustum {

    private UUID idChiTiet;
    private String maSanPham;
    private String tenSanPham;
    private int namBaoHanh;
    private String moTa;
    private int soLuongSanPham;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
}
