/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemban.repostority;

import com.mycompany.phanmemban.Untilies.hibernateconfig;
import com.mycompany.phanmemban.model.GioHang;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class GioHangRepostority {

    public List<GioHang> getAllData() {
        List<GioHang> gioHang = new ArrayList<>();
        Transaction trns = null;
        Session session = hibernateconfig.getFACTORY().openSession();
        try {
            trns = session.beginTransaction();
            gioHang = session.createQuery("from GioHang").list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return gioHang;
    }
      
}
