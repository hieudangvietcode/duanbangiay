/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemban.repostority;

import com.mycompany.phanmemban.Untilies.hibernateconfig;
import com.mycompany.phanmemban.model.ChiTietSP;
import com.mycompany.phanmemban.model.HoaDonChiTiet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class HoaDonChiTietRepostority {

    public List<HoaDonChiTiet> getAllData() {
        List<HoaDonChiTiet> hoaDonChiTiet = new ArrayList<>();
        Transaction trns = null;
        Session session = hibernateconfig.getFACTORY().openSession();
        try {
            trns = session.beginTransaction();
            hoaDonChiTiet = session.createQuery(" from HoaDonChiTiet order by ngayThanhToan DESC").list();
 
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return hoaDonChiTiet;
    }

    public boolean add(HoaDonChiTiet hoaDonChiTiet) {
        Transaction transaction = null;
//        Integer check = 0;
        try ( Session se = hibernateconfig.getFACTORY().openSession()) {
            transaction = se.beginTransaction();
             se.save(hoaDonChiTiet);
            transaction.commit();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean update(HoaDonChiTiet hoaDonChiTiet) {
        Transaction t = null;
        Session s = hibernateconfig.getFACTORY().openSession();
        try {
            t = s.beginTransaction();
            s.update(hoaDonChiTiet);
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
        } finally {
//            s.flush();
            s.close();
        }
        return true;
    }

    public Integer delete(Integer soluong) {
        try ( Session s = hibernateconfig.getFACTORY().openSession()) {
            Transaction t = s.getTransaction();
            t.begin();
            try {
                String hql = "delete HoaDonChiTiet hdct where hdct.soLuong = :soLuong";
                Query q = s.createQuery(hql);
                q.setParameter("soLuong", soluong);
                int result = q.executeUpdate();
                t.commit();
            } catch (Exception e) {
            }
        }
        return soluong;
    }

    public double tongtien() {
        double tong;
        try ( Session s = hibernateconfig.getFACTORY().openSession()) {
            String query = "select sum(soLuong * donGia) from HoaDonChiTiet";
            TypedQuery<Double> q = s.createQuery(query, Double.class);
            tong = q.getSingleResult();
        }
        return tong;
    }
    
    
}
