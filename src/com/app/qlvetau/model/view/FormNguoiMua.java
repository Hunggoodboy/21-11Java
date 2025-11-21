package com.app.qlvetau.model.view;

import com.app.qlvetau.model.Controller.NguoiMuaController;
import com.app.qlvetau.model.entity.NguoiMua;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Form quản lý Người Mua
 */
public class FormNguoiMua extends JFrame {
    private NguoiMuaController controller;
    private JTextField txtHoTen, txtDiaChi;
    private JComboBox<String> cboLoai;
    private JTable table;
    private DefaultTableModel tableModel;
    
    public FormNguoiMua() {
        controller = new NguoiMuaController();
        
        setTitle("QUẢN LÝ NGƯỜI MUA VÉ");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        initComponents();
        loadData();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông Tin Người Mua"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Họ tên
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Họ tên:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        txtHoTen = new JTextField(30);
        inputPanel.add(txtHoTen, gbc);
        
        // Địa chỉ
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        inputPanel.add(new JLabel("Địa chỉ:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        txtDiaChi = new JTextField(30);
        inputPanel.add(txtDiaChi, gbc);
        
        // Loại
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        inputPanel.add(new JLabel("Loại:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0;
        String[] loaiList = {"Mua lẻ", "Mua tập thể", "Mua qua mạng"};
        cboLoai = new JComboBox<>(loaiList);
        inputPanel.add(cboLoai, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnThem = new JButton("Thêm");
        JButton btnLamMoi = new JButton("Làm mới");
        JButton btnTaiLai = new JButton("Tải lại danh sách");
        
        btnThem.addActionListener(e -> themNguoiMua());
        btnLamMoi.addActionListener(e -> lamMoi());
        btnTaiLai.addActionListener(e -> loadData());
        
        buttonPanel.add(btnThem);
        buttonPanel.add(btnLamMoi);
        buttonPanel.add(btnTaiLai);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);
        
        // Table
        String[] columns = {"Mã NM", "Họ tên", "Địa chỉ", "Loại"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Người Mua"));
        
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void themNguoiMua() {
        String hoTen = txtHoTen.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String loai = (String) cboLoai.getSelectedItem();
        
        if (controller.themNguoiMua(hoTen, diaChi, loai)) {
            lamMoi();
            loadData();
        }
    }
    
    private void lamMoi() {
        txtHoTen.setText("");
        txtDiaChi.setText("");
        cboLoai.setSelectedIndex(0);
        txtHoTen.requestFocus();
    }
    
    private void loadData() {
        tableModel.setRowCount(0);
        List<NguoiMua> list = controller.layDanhSachNguoiMua();
        
        if (list != null) {
            for (NguoiMua nm : list) {
                tableModel.addRow(new Object[]{
                    String.format("%05d", nm.getMaNguoiMua()),
                    nm.getHoTen(),
                    nm.getDiaChi(),
                    nm.getLoai()
                });
            }
        }
    }
}