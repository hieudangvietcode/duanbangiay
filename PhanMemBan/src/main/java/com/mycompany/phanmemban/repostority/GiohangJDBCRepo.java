/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemban.repostority;

import com.mycompany.phanmemban.Untilies.DBConnection;
import com.mycompany.phanmemban.viewmodel.LuuGioHang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class GiohangJDBCRepo {

    DBConnection dBConnection;
    List<LuuGioHang> listgh = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    Statement st = null;

    public void clickGH(String ma, int sl) {
        String select = "if exists(select*from GioHang where Ma='GH')\n"
                + "begin\n"
                + "print 'ok'\n"
                + "end\n"
                + "else\n"
                + "begin\n"
                + " insert into GioHang(Ma) values('GH')\n"
                + " end\n"
                + " declare @giohang table (ma nvarchar(50) ,soluong nvarchar(50))\n"
                + "insert into @giohang  select d.Ma,b.SoLuong from GioHang a left join GioHangChiTiet b on a.id=b.IdGioHang\n"
                + "                         left join ChiTietSP c on c.id=b.IdChiTietSP\n"
                + "						 left join SanPham d  on d.id=c.IdSP\n"
                + "						 left join MauSac e on e.id=c.IdMauSac;\n"
                + "select*from @giohang\n"
                + "if exists(select*from @giohang where ma=? )\n"
                + "begin\n"
                + "declare @idsp UNIQUEIDENTIFIER,@idspct UNIQUEIDENTIFIER\n"
                + "set @idsp=(select Id from SanPham where Ma=?)\n"
                + "set  @idspct=(select Id from ChiTietSP where IdSP=@idsp)\n"
                + "update GioHangChiTiet set SoLuong=? where IdChiTietSP=@idspct\n"
                + "end\n"
                + "else\n"
                + "begin\n"
                + "declare @gan3 UNIQUEIDENTIFIER,@gan4 UNIQUEIDENTIFIER,@gan5 UNIQUEIDENTIFIER\n"
                + "set @gan3=(select Id from SanPham where Ma=?)\n"
                + "set @gan4=(select Id from GioHang where Ma='GH')\n"
                + "set  @gan5=(select Id from ChiTietSP where IdSP=@gan3)\n"
                + "insert into GioHangChiTiet(IdGioHang,IdChiTietSP,SoLuong) values(@gan4,@gan5,?)\n"
                + "end";
        try {
            pst = dBConnection.getConnection().prepareStatement(select);
            pst.setObject(1, ma);
            pst.setObject(2, ma);
            pst.setObject(3, sl);
            pst.setObject(4, ma);
            pst.setObject(5, sl);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GiohangJDBCRepo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        System.out.println("OKOK" + new GiohangJDBCRepo().getListGiohang());
    }

    public List<LuuGioHang> getListGiohang() {
        listgh = new ArrayList<>();
        String select = "select ma,ten,giohangchitiet.SoLuong,chitietsp.giaban from sanpham join chitietsp on sanpham.Id=ChiTietSP.IdSP \n"
                + "join giohangchitiet on chitietsp.Id =GioHangChiTiet.IdChiTietSP";

        try {
            st = dBConnection.getConnection().createStatement();
            rs = st.executeQuery(select);
            while (rs.next()) {
                listgh.add(new LuuGioHang(rs.getString(1), rs.getNString(2), rs.getInt(3), rs.getInt(4)));

            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(GiohangJDBCRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listgh;

    }

    public void bttaohoadon() {
        try {
            String delete = "delete from GioHangChiTiet\n"
                    + "delete from GioHang";
            pst = dBConnection.getConnection().prepareStatement(delete);

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GiohangJDBCRepo.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
     public List<LuuGioHang> clickbanghd() {
        listgh = new ArrayList<>();
        String s = "select a.ma,e.Ma,e.Ten,c.SoLuong,d.GiaBan from HoaDon a left join NhanVien b  on a.IdNV=b.id\n"
                + "                                                      left join HoaDonChiTiet c  on a.Id=c.IdHoaDon\n"
                + "                                                       left join ChiTietSP d  on d.Id=c.IdChiTietSP\n"
                + "							 left join SanPham e on e.Id=d.IdSP ";
        try {
            st = dBConnection.getConnection().createStatement();
            rs = st.executeQuery(s);
            while (rs.next()) {
                listgh.add(new LuuGioHang(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getNString(3),
                        rs.getInt(4),
                        rs.getInt(5)
                ));

            }
            rs.close();
        } catch (SQLException ex) {

            Logger.getLogger(GiohangJDBCRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listgh;
    }
}
