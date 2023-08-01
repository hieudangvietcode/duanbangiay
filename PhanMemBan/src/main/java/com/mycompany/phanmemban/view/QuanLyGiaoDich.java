/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.phanmemban.view;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.phanmemban.model.ChiTietSP;
import com.mycompany.phanmemban.model.DoiTra;
import com.mycompany.phanmemban.model.HoaDon;
import com.mycompany.phanmemban.model.HoaDonChiTiet;
import com.mycompany.phanmemban.model.KhachHang;
import com.mycompany.phanmemban.model.KhuyenMai;
import com.mycompany.phanmemban.model.NhanVien;
import com.mycompany.phanmemban.repostority.ChiTietSPRepostority;
import com.mycompany.phanmemban.service.ChiTietSPService;
import com.mycompany.phanmemban.service.DoiTraService;
import com.mycompany.phanmemban.service.GioHangJDBCService;
import com.mycompany.phanmemban.service.HoaDonChiTietService;
import com.mycompany.phanmemban.service.HoaDonSer;
import com.mycompany.phanmemban.service.HoaDonService;
import com.mycompany.phanmemban.service.KhachHangService;
import com.mycompany.phanmemban.service.KhuyenMaiService;
import com.mycompany.phanmemban.service.NhanVienService;
import com.mycompany.phanmemban.service.SanPhamService;
import com.mycompany.phanmemban.service.impl.ChiTietSPServiceImpl;
import com.mycompany.phanmemban.service.impl.DoiTraServiceImpl;
import com.mycompany.phanmemban.service.impl.GiohangJdbcServiceimpl;
import com.mycompany.phanmemban.service.impl.HoaDonChiTietServiceImpl;
import com.mycompany.phanmemban.service.impl.HoaDonSerImpl;
import com.mycompany.phanmemban.service.impl.HoaDonServiceImpl;
import com.mycompany.phanmemban.service.impl.KhachHangServiceimpl;
import com.mycompany.phanmemban.service.impl.KhuyenMaiServiceImpl;
import com.mycompany.phanmemban.service.impl.NhanVienServicelmpl;
import com.mycompany.phanmemban.service.impl.SanPhamServiceImpl;
import com.mycompany.phanmemban.viewmodel.ChiTietSPReponse;
import com.mycompany.phanmemban.viewmodel.ChiTietSanPhamCustum;
import com.mycompany.phanmemban.viewmodel.LuuGioHang;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LONG
 */
public class QuanLyGiaoDich extends javax.swing.JFrame {

    private DefaultTableModel defaultTableModel;
    private DefaultTableModel defaultTableModel1;
    private DefaultTableModel defaultTableModel2;
    private DefaultComboBoxModel dcmdt;
    private DefaultComboBoxModel dcmkm;
    private DefaultComboBoxModel dcmtt;
    private DefaultComboBoxModel dcmkh;
    private DefaultComboBoxModel dcmnv;

    private ChiTietSPService chiTietSPService = new ChiTietSPServiceImpl();
    private HoaDonService hoaDonService = new HoaDonServiceImpl();
    private HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietServiceImpl();
    private NhanVienService nhanVienService = new NhanVienServicelmpl();
    private KhuyenMaiService khuyenMaiService = new KhuyenMaiServiceImpl();
    private DoiTraService doiTraService = new DoiTraServiceImpl();
    private KhachHangService khachHangService = new KhachHangServiceimpl();
    private Map<UUID, ChiTietSPReponse> mapChiTiet = new HashMap<>();
    private List<ChiTietSPReponse> listSanPham = new ArrayList<>();
    private SanPhamService sanPhamService = new SanPhamServiceImpl();
    private HoaDonSer rp = new HoaDonSerImpl();
    private List<HoaDon> listHoaDon = new ArrayList<>();
    private GioHangJDBCService ghsv = new GiohangJdbcServiceimpl();
    List<LuuGioHang> listGH = new ArrayList<>();
    NhanVien nvo = new NhanVien();

    public QuanLyGiaoDich() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Hệ Thống Quản Lý Cửa Hàng Bán Giày FPT");
        listSanPham = chiTietSPService.getAll();
        listHoaDon = hoaDonService.loadData();
        loadHoaDon(hoaDonService.loadData());
        loadCbbloaikm(hoaDonService.getListKM());
        loadCbbloaidt(hoaDonService.getListDT());
        loadCbbKh(hoaDonService.getListKH());
        listGH = ghsv.loadGH();
        loadbanggh();
        showDataSanPham(listSanPham);
        loadCbbloaitt();
        ButtonGroup();
        nvo = nhanVienService.getOnes(nvo.getUser());

    }

    public void loadHoaDon(List<HoaDon> list) {
        listHoaDon.clear();
        defaultTableModel1 = (DefaultTableModel) tblhoadon.getModel();
        defaultTableModel1.setRowCount(0);
        int i = 1;
        for (HoaDon hoaDon : list) {
            try {
                defaultTableModel1.addRow(new Object[]{
                    i,
                    hoaDon.getMa(),
                    hoaDon.getKhachHang().getTen(),
                    hoaDon.getNhanVien().getTen(),
                    hoaDon.getKhuyenMai().getMaKM(),
                    hoaDon.getDoiTra().getMaDT(),
                    hoaDon.getNgayThanhToan() + " ",
                    hoaDon.getNgayTao() + " ",
                    hoaDon.hienthiTrangThai(),
                    hoaDon.getDiaChi(),
                    "0" + hoaDon.getSdt_kh()
                });
                i++;
                listHoaDon.add(hoaDon);
            } catch (Exception e) {
            }

        }
    }

    private void load() {
        listSanPham = chiTietSPService.getAll();
        showDataSanPham(listSanPham);

        loadHoaDon(hoaDonService.loadData());

        listGH = ghsv.loadGH();
        loadbanggh();

    }

    private void showDataSanPham(List<ChiTietSPReponse> list) {
        int index = 1;
        defaultTableModel2 = (DefaultTableModel) tblctsp.getModel();
        defaultTableModel2.setRowCount(0);
        for (ChiTietSPReponse ct : list) {
            defaultTableModel2.addRow(new Object[]{
                index++, ct.getMaSanPham(), ct.getTenSanPham(), ct.getNamBaoHanh(), ct.getMoTa(),
                ct.getSoLuongSanPham(), ct.getGiaNhap(), ct.getGiaBan()
            });
        }
    }

    public void loadCbbloaikm(List<KhuyenMai> list) {
        dcmkm = (DefaultComboBoxModel) cbbmakm.getModel();
        for (KhuyenMai khuyenMai : list) {
            dcmkm.addElement(khuyenMai);
        }
    }

    public void loadCbbloaidt(List<DoiTra> list) {
        dcmdt = (DefaultComboBoxModel) cbbmadt.getModel();
        for (DoiTra doiTra : list) {
            dcmdt.addElement(doiTra);
        }
    }

    public void loadCbbKh(List<KhachHang> list) {
        dcmkh = (DefaultComboBoxModel) cbbkh.getModel();
        for (KhachHang kahchHang : list) {
            dcmkh.addElement(kahchHang);
        }
    }

    public void loadCbbloaitt() {
        ArrayList<String> listtt = new ArrayList<>();
        dcmtt = (DefaultComboBoxModel) cbbtinhtrang.getModel();
        cbbtinhtrang.removeAllItems();
        listtt.add("");
        listtt.add("Đã thanh toán");
        listtt.add("Chưa thanh toán");
        listtt.add("Chờ thanh toán");
        for (String string : listtt) {
            dcmtt.addElement(string);
        }
    }

    public void ButtonGroup() {
        ButtonGroup buttonG = new ButtonGroup();
        buttonG.add(btnall);
        buttonG.add(btnchothanhtoan);
        buttonG.add(btnchuathantoan);
        buttonG.add(btndathanhtoan);
    }

    private void showdata(int index) {
        HoaDon hd = listHoaDon.get(index);
        txtmahd.setText(hd.getMa());
        txtdiac.setText(hd.getDiaChi());
        txtsdt.setText(hd.getSdt_kh());
        cbbtinhtrang.getModel().setSelectedItem(hd.hienthiTrangThai());
        txtngaytao.setText(String.valueOf(hd.getNgayTao()));
        cbbmakm.getModel().setSelectedItem(hd.getKhuyenMai().getMaKM());
        cbbmadt.getModel().setSelectedItem(hd.getDoiTra().getMaDT());
        cbbkh.getModel().setSelectedItem(hd.getKhachHang().getTen());
        txtngaythanhtoan.setText(String.valueOf(hd.getNgayThanhToan()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbHoaDonChiTiet = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblctsp = new javax.swing.JTable();
        txtsearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        soluongmua = new javax.swing.JLabel();
        txtSL = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btntaodon = new javax.swing.JButton();
        btnthanhtoan = new javax.swing.JButton();
        btnhuydon = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        txtmahd = new javax.swing.JTextField();
        txtngaytao = new javax.swing.JTextField();
        txtdiac = new javax.swing.JTextField();
        txtngaythanhtoan = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbbkh = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        cbbmakm = new javax.swing.JComboBox<>();
        cbbmadt = new javax.swing.JComboBox<>();
        cbbtinhtrang = new javax.swing.JComboBox<>();
        txttrinkhachtra = new javax.swing.JTextField();
        txttiendu = new javax.swing.JTextField();
        txtthanhtien = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblhoadon = new javax.swing.JTable();
        btnchothanhtoan = new javax.swing.JRadioButton();
        btnchuathantoan = new javax.swing.JRadioButton();
        btndathanhtoan = new javax.swing.JRadioButton();
        btnall = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 255)));

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        tbHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Ma SP", "Tên SP", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ));
        tbHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonChiTietMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbHoaDonChiTiet);
        if (tbHoaDonChiTiet.getColumnModel().getColumnCount() > 0) {
            tbHoaDonChiTiet.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        tblctsp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "NamBH", "Mô Tả", "Số Lượng Tồn", "Giá Bán"
            }
        ));
        tblctsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblctspMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblctsp);

        jButton1.setText("Tìm Kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(soluongmua, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(soluongmua, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        btntaodon.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btntaodon.setText("Tạo Đơn");
        btntaodon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaodonActionPerformed(evt);
            }
        });

        btnthanhtoan.setBackground(new java.awt.Color(0, 255, 0));
        btnthanhtoan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnthanhtoan.setForeground(new java.awt.Color(255, 51, 0));
        btnthanhtoan.setText("Thanh Toán");
        btnthanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthanhtoanActionPerformed(evt);
            }
        });

        btnhuydon.setBackground(new java.awt.Color(255, 102, 102));
        btnhuydon.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnhuydon.setText("Hủy Đơn");
        btnhuydon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhuydonActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(153, 153, 255));

        txtdiac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiacActionPerformed(evt);
            }
        });

        txtngaythanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtngaythanhtoanActionPerformed(evt);
            }
        });

        txtsdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsdtActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã HĐ:");

        jLabel4.setText("Tên KH:");

        jLabel7.setText("Địa Chỉ:");

        jLabel6.setText("Ngày Thanh Toán:");

        jLabel8.setText("Sđt:");

        jLabel5.setText("Ngày Tạo:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(txtngaytao))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbkh, javax.swing.GroupLayout.Alignment.TRAILING, 0, 198, Short.MAX_VALUE)
                            .addComponent(txtmahd)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(txtdiac))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(txtngaythanhtoan))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(txtsdt)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmahd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbbkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtdiac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtngaythanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jPanel7.setBackground(new java.awt.Color(153, 153, 255));

        cbbmakm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbmakmActionPerformed(evt);
            }
        });

        cbbmadt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbmadtActionPerformed(evt);
            }
        });

        cbbtinhtrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbtinhtrangActionPerformed(evt);
            }
        });

        txttiendu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttienduActionPerformed(evt);
            }
        });

        jLabel9.setText("Mã KM");

        jLabel10.setText("Mã ĐT");

        jLabel11.setText("Tình Trạng:");

        jLabel13.setText("Khách Trả:");

        jLabel14.setText("Số dư:");

        jLabel12.setText("Thành Tiền:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txttrinkhachtra)
                            .addComponent(cbbtinhtrang, 0, 207, Short.MAX_VALUE)
                            .addComponent(cbbmadt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbmakm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttiendu)
                            .addComponent(txtthanhtien))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbbmakm, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbbmadt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(cbbtinhtrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txttrinkhachtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(txttiendu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtthanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        tblhoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Tên KH", "Tên NV", "Mã KM", "Mã ĐT", "Ngày Thanh Toán", "Ngày Tạo", "Tình Trạng", "Địa Chỉ", "Sđt"
            }
        ));
        tblhoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhoadonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblhoadon);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnchothanhtoan.setText("Chờ Thanh Toán");
        btnchothanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchothanhtoanActionPerformed(evt);
            }
        });

        btnchuathantoan.setText("Chưa Thanh Toán");
        btnchuathantoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchuathantoanActionPerformed(evt);
            }
        });

        btndathanhtoan.setText("Đã Thanh Toán");
        btndathanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndathanhtoanActionPerformed(evt);
            }
        });

        btnall.setText("ALL");
        btnall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnallActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnchothanhtoan)
                                .addGap(18, 18, 18)
                                .addComponent(btnchuathantoan)
                                .addGap(27, 27, 27)
                                .addComponent(btndathanhtoan)
                                .addGap(18, 18, 18)
                                .addComponent(btnall)
                                .addGap(32, 32, 32)
                                .addComponent(btntaodon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)))
                        .addComponent(btnhuydon, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnchothanhtoan)
                    .addComponent(btnchuathantoan)
                    .addComponent(btndathanhtoan)
                    .addComponent(btnall)
                    .addComponent(btntaodon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhuydon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jLabel2)
                        .addContainerGap(172, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("QUẢN LÝ GIAO DỊCH");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(481, 481, 481)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 586, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 910, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtdiacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiacActionPerformed

    private void txtngaythanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtngaythanhtoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtngaythanhtoanActionPerformed

    private void txtsdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsdtActionPerformed

    private void cbbmakmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbmakmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbmakmActionPerformed

    private void cbbmadtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbmadtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbmadtActionPerformed

    private void cbbtinhtrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbtinhtrangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbtinhtrangActionPerformed

    private void txttienduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttienduActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttienduActionPerformed

    private void tblctspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblctspMouseClicked

        int row1 = tblhoadon.getSelectedRow();
        if (row1 >= 0) {
            int row = tblctsp.getSelectedRow();
            int tong = 0;
            ghsv.clickkgh(tblctsp.getValueAt(row, 1).toString(), Integer.parseInt(txtSL.getText()));
            listGH = ghsv.loadGH();
            loadbanggh();
            for (LuuGioHang g : listGH) {
//            if (g.getMahd().equals(tblHoadon.getValueAt(row, 1).toString())) {
                tong = tong + g.tongTien();

            }
            String to = String.valueOf(tong);
            txtthanhtien.setText(to);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn ");
        }

    }//GEN-LAST:event_tblctspMouseClicked

    private void loadbanggh() {
        defaultTableModel = (DefaultTableModel) tbHoaDonChiTiet.getModel();
        defaultTableModel.setRowCount(0);
        if (listGH.size() <= 0) {
            System.err.println("gh:" + listGH.size());
        } else {

            for (int i = 0; i < listGH.size(); i++) {

                defaultTableModel.addRow(new Object[]{i + 1, listGH.get(i).getMaSp(), listGH.get(i).getTenSP(),
                    listGH.get(i).getSoLuong(),
                    listGH.get(i).getDonGia(),
                    listGH.get(i).tongTien()
                });
            }

        }
    }


    private void btntaodonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaodonActionPerformed

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // lấy lịch hiện tại 
        HoaDon hoaDon = new HoaDon();
        long millis = System.currentTimeMillis();
        Date dateHienTai = new java.sql.Date(millis);
        String maKH = cbbkh.getSelectedItem().toString();
        KhachHang khachHang = khachHangService.getOne(maKH);
        String maDT = cbbmadt.getSelectedItem().toString();
        DoiTra doiTra = doiTraService.getOne(maDT);
        String maKM = "H8NDJH832849";
        KhuyenMai khuyenMai = khuyenMaiService.getOne(maKM);
        java.sql.Date ngayt = new java.sql.Date(new java.util.Date().getTime());
        hoaDon.setNgayTao(ngayt);
        String diaChi = txtdiac.getText();
        int tinhTrang = 1;
        String sdt = txtsdt.getText().trim();
        int rad = (int) Math.floor((Math.random() * 899) + 100);
        String maHD = "HD" + rad;
        HoaDon hoaDon1 = new HoaDon(khachHang, nvo, khuyenMai, doiTra, maHD, ngayt, null, 1, diaChi, sdt);
        HoaDon create = hoaDonService.create(hoaDon1);
        JOptionPane.showMessageDialog(this, "Thêm thành công");
        loadHoaDon(hoaDonService.loadData());
        System.out.println(create.toString());

    }//GEN-LAST:event_btntaodonActionPerformed

    private void btnhuydonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuydonActionPerformed
        ghsv.bttthd();
        load();
    }//GEN-LAST:event_btnhuydonActionPerformed

    private void btnthanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthanhtoanActionPerformed
        // TODO add your handling code here:
        int index = tblhoadon.getSelectedRow();
        double tinkhachdua = Double.valueOf(txttrinkhachtra.getText());
        double tongtien = Double.valueOf(txtthanhtien.getText());
        double tienthua = tinkhachdua - tongtien;
        if (Double.valueOf(txttrinkhachtra.getText()) > Double.valueOf(txtthanhtien.getText())) {
            java.sql.Date ngaytt = new java.sql.Date(new java.util.Date().getTime());

            JOptionPane.showMessageDialog(this, rp.suaTrangThai(0, tblhoadon.getValueAt(index, 1).toString(), ngaytt));
            for (LuuGioHang gh : listGH) {
                rp.themhdct(txtmahd.getText(), gh.getMaSp(), gh.getSoLuong(), BigDecimal.valueOf(Double.valueOf(txtthanhtien.getText())));
            }
            ChiTietSPRepostority chiTietSPRepostority = new ChiTietSPRepostority();
            ChiTietSPReponse ctsp = listSanPham.get(tblctsp.getSelectedRow());
            ChiTietSP chiTietSP = chiTietSPRepostority.getOne(ctsp.getMoTa());
            chiTietSP.setSoLuongTon(chiTietSP.getSoLuongTon() - Integer.parseInt(txtSL.getText()));
            chiTietSPService.update(chiTietSP);
            ghsv.bttthd();
            load();

        } else {
            JOptionPane.showMessageDialog(this, "Bạn không đủ tiền thanh toán");
        }
        txttiendu.setText(String.valueOf(tienthua));
        btnthanhtoan.setEnabled(false);
        txtmahd.setEnabled(false);
        txttrinkhachtra.setEnabled(false);
        txttiendu.setEnabled(false);
        txtngaythanhtoan.setEnabled(false);

    }//GEN-LAST:event_btnthanhtoanActionPerformed

    private void tblhoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhoadonMouseClicked
        // TODO add your handling code here:
        int row = tblhoadon.getSelectedRow();
        showdata(row);

        int tong = 0;

        defaultTableModel1 = (DefaultTableModel) tblhoadon.getModel();
        listGH = ghsv.clickBanghd();
        defaultTableModel = (DefaultTableModel) tbHoaDonChiTiet.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 0;
        for (int i = 0; i < listGH.size(); i++) {
            if (listGH.get(i).getMahd().equals(tblhoadon.getValueAt(row, 1).toString())) {
                defaultTableModel.addRow(new Object[]{
                    stt = stt + 1,
                    listGH.get(i).getMaSp(), listGH.get(i).getTenSP(),
                    listGH.get(i).getSoLuong(), listGH.get(i).getDonGia(),
                    listGH.get(i).tongTien()

                });
            }

        }

        for (LuuGioHang g : listGH) {
            if (g.getMahd().equals(tblhoadon.getValueAt(row, 1).toString())) {
                tong = tong + g.tongTien();
            }
        }
        String to = String.valueOf(tong);
        txtthanhtien.setText(to);

    }//GEN-LAST:event_tblhoadonMouseClicked

    private void tbHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonChiTietMouseClicked
        // TODO add your handling code here:
        int row = tbHoaDonChiTiet.getSelectedRow();
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getAllData().get(row);
        txtthanhtien.setText(String.valueOf(hoaDonChiTiet.thanhtien()));

    }//GEN-LAST:event_tbHoaDonChiTietMouseClicked

    private void btnchothanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchothanhtoanActionPerformed
        int row = tblhoadon.getSelectedRow();
        defaultTableModel1.setRowCount(0);
        if (listHoaDon.size() <= 0) {
            System.err.println("sp:" + listHoaDon.size());
        } else {
            for (int i = 0; i < listHoaDon.size(); i++) {
                if (listHoaDon.get(i).getTinhTrang() == 2) {
                    defaultTableModel1.addRow(new Object[]{
                        i + 1,
                        listHoaDon.get(i).getMa(), listHoaDon.get(i).getKhachHang().getTen(),
                        listHoaDon.get(i).getNhanVien().getTen(), listHoaDon.get(i).getKhuyenMai().getMaKM(),
                        listHoaDon.get(i).getDoiTra().getMaDT(), listHoaDon.get(i).getNgayThanhToan(),
                        listHoaDon.get(i).getNgayTao(), listHoaDon.get(i).hienthiTrangThai(),
                        listHoaDon.get(i).getDiaChi(),
                        listHoaDon.get(i).getSdt_kh()
                    });
                }
            }
        }
    }//GEN-LAST:event_btnchothanhtoanActionPerformed

    private void btndathanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndathanhtoanActionPerformed
        // TODO add your handling code here:
        int row = tblhoadon.getSelectedRow();
        defaultTableModel1.setRowCount(0);
        if (listHoaDon.size() <= 0) {
            System.err.println("sp:" + listHoaDon.size());
        } else {
            for (int a = 0; a < listHoaDon.size(); a++) {
                if (listHoaDon.get(a).getTinhTrang() == 0) {
                    defaultTableModel1.addRow(new Object[]{
                        a + 1,
                        listHoaDon.get(a).getMa(), listHoaDon.get(a).getKhachHang().getTen(),
                        listHoaDon.get(a).getNhanVien().getTen(), listHoaDon.get(a).getKhuyenMai().getMaKM(),
                        listHoaDon.get(a).getDoiTra().getMaDT(), listHoaDon.get(a).getNgayThanhToan(),
                        listHoaDon.get(a).getNgayTao(), listHoaDon.get(a).hienthiTrangThai(),
                        listHoaDon.get(a).getDiaChi(),
                        listHoaDon.get(a).getSdt_kh()
                    });
                }

            }
        }

    }//GEN-LAST:event_btndathanhtoanActionPerformed

    private void btnchuathantoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchuathantoanActionPerformed
        // TODO add your handling code here:
        int row = tblhoadon.getSelectedRow();
        defaultTableModel1.setRowCount(0);
        if (listHoaDon.size() <= 0) {
            System.err.println("sp:" + listHoaDon.size());
        } else {
            for (int i = 0; i < listHoaDon.size(); i++) {
                if (listHoaDon.get(i).getTinhTrang() == 1) {
                    defaultTableModel1.addRow(new Object[]{
                        i + 1,
                        listHoaDon.get(i).getMa(), listHoaDon.get(i).getKhachHang().getTen(),
                        listHoaDon.get(i).getNhanVien().getTen(), listHoaDon.get(i).getKhuyenMai().getMaKM(),
                        listHoaDon.get(i).getDoiTra().getMaDT(), listHoaDon.get(i).getNgayThanhToan(),
                        listHoaDon.get(i).getNgayTao(), listHoaDon.get(i).hienthiTrangThai(),
                        listHoaDon.get(i).getDiaChi(),
                        listHoaDon.get(i).getSdt_kh()
                    });
                }
            }
        }
    }//GEN-LAST:event_btnchuathantoanActionPerformed

    private void btnallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnallActionPerformed
        // TODO add your handling code here:
        int row = tblhoadon.getSelectedRow();
        defaultTableModel1.setRowCount(0);
        if (listHoaDon.size() <= 0) {
            System.err.println("sp:" + listHoaDon.size());
        } else {
            for (int i = 0; i < listHoaDon.size(); i++) {
                defaultTableModel1.addRow(new Object[]{
                    i + 1,
                    listHoaDon.get(i).getMa(), listHoaDon.get(i).getKhachHang().getTen(),
                    listHoaDon.get(i).getNhanVien().getTen(), listHoaDon.get(i).getKhuyenMai().getMaKM(),
                    listHoaDon.get(i).getDoiTra().getMaDT(), listHoaDon.get(i).getNgayThanhToan(),
                    listHoaDon.get(i).getNgayTao(), listHoaDon.get(i).hienthiTrangThai(),
                    listHoaDon.get(i).getDiaChi(),
                    listHoaDon.get(i).getSdt_kh()
                });

            }
        }
    }//GEN-LAST:event_btnallActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String tenSanPham = txtsearch.getText().trim();
        if (tenSanPham.length() <= 0) {
            showDataSanPham(chiTietSPService.getAll());
        } else {
            showDataSanPham(chiTietSPService.searchByNameSanPham(tenSanPham));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyGiaoDich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyGiaoDich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyGiaoDich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyGiaoDich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyGiaoDich().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnall;
    private javax.swing.JRadioButton btnchothanhtoan;
    private javax.swing.JRadioButton btnchuathantoan;
    private javax.swing.JRadioButton btndathanhtoan;
    private javax.swing.JButton btnhuydon;
    private javax.swing.JButton btntaodon;
    private javax.swing.JButton btnthanhtoan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbkh;
    private javax.swing.JComboBox<String> cbbmadt;
    private javax.swing.JComboBox<String> cbbmakm;
    private javax.swing.JComboBox<String> cbbtinhtrang;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel soluongmua;
    private javax.swing.JTable tbHoaDonChiTiet;
    private javax.swing.JTable tblctsp;
    private javax.swing.JTable tblhoadon;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtdiac;
    private javax.swing.JTextField txtmahd;
    private javax.swing.JTextField txtngaytao;
    private javax.swing.JTextField txtngaythanhtoan;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtthanhtien;
    private javax.swing.JTextField txttiendu;
    private javax.swing.JTextField txttrinkhachtra;
    // End of variables declaration//GEN-END:variables
}
