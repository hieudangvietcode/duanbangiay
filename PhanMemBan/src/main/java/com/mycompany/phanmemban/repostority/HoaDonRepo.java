/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.phanmemban.repostority;

import com.mycompany.phanmemban.Untilies.DBConnection;
import com.mycompany.phanmemban.viewmodel.HoaDonCT;
import com.mycompany.phanmemban.viewmodel.LuuGioHang;
import java.math.BigDecimal;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class HoaDonRepo {
     DBConnection dBConnection;
    List<LuuGioHang> listGh=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    Statement st=null;
    /**
     * @param args the command line arguments
     */
     public boolean update(int id, String ma,Date ngaytt) {
        String sql = " update HoaDon set  tinhtrang=?, ngaythanhtoan=? where ma=?";
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.setObject(2, ngaytt);
            ps.setObject(3, ma);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }
        return false;
    }
    public static void main(String[] args) {
        // TODO code application logic here
//        System.out.println(new HoaDonRepo().update(0, "HD04"));
    }
      public void Thehdct(String ma, String msp, int sl ,BigDecimal gia) {
        try {
            String insert = "declare @idhd UNIQUEIDENTIFIER,@idspct UNIQUEIDENTIFIER\n"
                    + "set @idhd=(select id from HoaDon where Ma=?)\n"
                    + "set @idspct=(select b.Id from SanPham a join ChiTietSP b on a.Id=b.IdSP where a.Ma=?)\n"
                    + "insert into HoaDonChiTiet(IdHoaDon,IdChiTietSP,SoLuong,DonGia) values (@idhd,@idspct,?,?)";
            pst = dBConnection.getConnection().prepareStatement(insert);
            pst.setObject(1, ma);
            pst.setObject(2, msp);
            pst.setObject(3, sl);
            pst.setObject(4, gia);
           

            pst.executeUpdate();

        } catch (SQLException ex) {
//            Logger.getLogger(HoaDonRepo.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
       public List<LuuGioHang> clickbanghd() {
        listGh = new ArrayList<>();
        String s = "select a.ma,e.Ma,e.Ten,c.SoLuong,d.GiaBan from HoaDon a left join NhanVien b  on a.IdNV=b.id\n"
                + "                                                      left join HoaDonChiTiet c  on a.Id=c.IdHoaDon\n"
                + "                                                       left join ChiTietSP d  on d.Id=c.IdChiTietSP\n"
                + "							 left join SanPham e on e.Id=d.IdSP ";
        try {
            st = dBConnection.getConnection().createStatement();
            rs = st.executeQuery(s);
            while (rs.next()) {
              

            }
            rs.close();
        } catch (SQLException ex) {

            Logger.getLogger(HoaDonRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listGh;
    }
    
}
