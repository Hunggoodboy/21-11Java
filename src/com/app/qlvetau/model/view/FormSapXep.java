package com.app.qlvetau.model.view;

import com.app.qlvetau.model.Controller.SortController;
import com.app.qlvetau.model.entity.HoaDon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Form sắp xếp Hóa Đơn
 */
public class FormSapXep extends JFrame {
    private SortController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JRadioButton rbHoTen, rbSoLuong;
    
    public FormSapXep() {
        controller = new SortController();
        
        setTitle("SẮP XẾP HÓA ĐƠN");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        initComponents();
        loadData();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel sắp xếp
        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sortPanel.setBorder(BorderFactory.createTitledBorder("Sắp Xếp Theo"));
        
        rbHoTen = new JRadioButton("Họ tên người mua (A-Z)", true);
        rbSoLuong = new JRadioButton("Số lượng vé (Giảm dần)");
        
        ButtonGroup group = new ButtonGroup();
        group.add(rbHoTen);
        group.add(rbSoLuong);
        
        sortPanel.add(rbHoTen);
        sortPanel.add(rbSoLuong);
        
        JButton btnSapXep = new JButton("Thực hiện sắp xếp");
        btnSapXep.addActionListener(e -> sapXep());
        sortPanel.add(btnSapXep);
        
        JButton btnTaiLai = new JButton("Tải lại danh sách");
        btnTaiLai.addActionListener(e -> loadData());
        sortPanel.add(btnTaiLai);
        
        // Table
        String[] columns = {"Mã HĐ", "Mã NM", "Họ Tên", "Số Loại Vé", "Tổng SL", "Tổng Tiền"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Hóa Đơn"));
        
        add(sortPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void sapXep() {
        if (rbHoTen.isSelected()) {
            if (controller.sapXepTheoHoTen()) {
                loadData();
            }
        } else if (rbSoLuong.isSelected()) {
            if (controller.sapXepTheoSoLuong()) {
                loadData();
            }
        }
    }
    
    private void loadData() {
        tableModel.setRowCount(0);
        List<HoaDon> list = controller.layDanhSachHoaDon();
        
        if (list != null) {
            for (HoaDon hd : list) {
                tableModel.addRow(new Object[]{
                    String.format("%05d", hd.getMaHoaDon()),
                    String.format("%05d", hd.getMaNguoiMua()),
                    hd.getHoTenNguoiMua(),
                    hd.getChiTietList().size(),
                    hd.getTongSoLuongVe(),
                    String.format("%,.0f", hd.calculateTotal())
                });
            }
        }
    }
}