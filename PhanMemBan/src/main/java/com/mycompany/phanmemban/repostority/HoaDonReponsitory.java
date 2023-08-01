/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phanmemban.repostority;

import com.mycompany.phanmemban.Untilies.hibernateconfig;
import com.mycompany.phanmemban.model.DoiTra;
import com.mycompany.phanmemban.model.HoaDon;
import com.mycompany.phanmemban.model.KhuyenMai;
import com.mycompany.phanmemban.model.NhanVien;
import com.mycompany.phanmemban.viewmodel.HoaDonReponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class HoaDonReponsitory {


    public List<HoaDon> getList() {
        List<HoaDon> hoadon = new ArrayList<>();
        Transaction trns = null;
        
        try(Session session = hibernateconfig.getFACTORY().openSession();) {
            trns = session.beginTransaction();
            hoadon = session.createQuery("from HoaDon order by ngayTao DESC").list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } 
        return hoadon;
    }

//    public List<HoaDonReponse> getAll() {
//        Transaction transaction = null;
//        List<HoaDonReponse> list = new ArrayList<>();
//        try (Session session = hibernateconfig.getFACTORY().openSession();){
//            transaction = session.beginTransaction();
//            String query = " SELECT new com.mycompany.phanmemban.viewmodel.HoaDonReponse "
//                    + " ( hd.idHoaDon AS idHoaDon ,"
//                    + " hd.ma AS maHoaDon , "
//                    + " hd.ngayTao AS ngayTao ,"
//                    + " nv.ten AS tenNhanVien ,"
//                    + " hd.tinhTrang AS tinhTrang ,"
//                    + " )"
//                    + " FROM HoaDon hd "
//                    + " JOIN NhanVien nv ON nv.idNhanVien = hd.nhanVien.idNhanVien "
//                    + " ORDER BY hd.ngayTao DESC";
//            list = session.createQuery(query).list();
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//        return list;
//    }

    public HoaDon create(HoaDon hoaDon) {
        Transaction transaction = null;
        try (Session session = hibernateconfig.getFACTORY().openSession();){
            session.clear();
            transaction = session.beginTransaction();
            session.save(hoaDon);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return hoaDon;
    }

    public boolean add(HoaDon hoaDon) {
        Transaction transaction = null;
//        Integer check = 0;
        try (Session session = hibernateconfig.getFACTORY().openSession();){
            transaction = session.beginTransaction();
            session.save(hoaDon);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        } 
        return true;
    }

    public Boolean update(int tt, String ma) {
        
        try(Session session = hibernateconfig.getFACTORY().openSession();) {
            String hql = "update HoaDon set TinhTrang=:tinhTrang  where ma = :ma";
            Query q = session.createQuery(hql);

            q.setParameter("tinhTrang", tt);
            q.setParameter("ma", ma);
            q.executeUpdate();
            System.out.println(q.executeUpdate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public UUID delete(UUID id) {
        try ( Session s = hibernateconfig.getFACTORY().openSession()) {
            Transaction t = s.getTransaction();
            t.begin();
            try {
                String hql = "delete HoaDon ctsp where ctsp.id = :id";
                Query q = s.createQuery(hql);
                q.setParameter("id", id);
                int result = q.executeUpdate();
                t.commit();
            } catch (Exception e) {
            }
        }
        return id;
    }

    public List<String> listcbbhd() {
        ArrayList<String> list = new ArrayList<>();
        try ( Session s = hibernateconfig.getFACTORY().openSession()) {
            String hql = "Select hd.ma from HoaDon hd";
            TypedQuery<String> query = s.createQuery(hql, String.class);
            list = (ArrayList<String>) query.getResultList();
        }
        return list;
    }

    public HoaDon ADD(HoaDon hd) {
        try ( Session session = hibernateconfig.getFACTORY().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.saveOrUpdate(hd);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                hd = null;
            }
        } finally {
            return hd;
        }
    }

    public static void main(String[] args) {
        System.out.println(new HoaDonReponsitory().getList());
    }
}
