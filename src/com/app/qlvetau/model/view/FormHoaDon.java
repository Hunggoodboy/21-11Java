package com.app.qlvetau.model.view;

import com.app.qlvetau.model.Controller.HoaDonController;
import com.app.qlvetau.model.entity.ChiTietHD;
import com.app.qlvetau.model.entity.HoaDon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Form lập Hóa Đơn
 */
public class FormHoaDon extends JFrame {
    private HoaDonController controller;
    private JTextField txtMaNguoiMua, txtMaVe, txtSoLuong;
    private JTable tableChiTiet, tableHoaDon;
    private DefaultTableModel modelChiTiet, modelHoaDon;
    private HoaDon hoaDonHienTai;
    private JLabel lblThongTinNguoiMua, lblTongTien;

    public FormHoaDon() {
        controller = new HoaDonController();

        setTitle("LẬP HÓA ĐƠN MUA VÉ");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        loadDanhSachHoaDon();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // Panel chính chia làm 2 phần: Nhập HĐ và Danh sách HĐ
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(450);
        splitPane.setResizeWeight(0.6);

        // Panel nhập hóa đơn (phía trên)
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Lập Hóa Đơn Mới"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        // Panel thông tin người mua
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        infoPanel.add(new JLabel("Mã Người Mua:"));
        txtMaNguoiMua = new JTextField(10);
        infoPanel.add(txtMaNguoiMua);

        JButton btnTaoHD = new JButton("Tạo Hóa Đơn");
        btnTaoHD.addActionListener(e -> taoHoaDon());
        infoPanel.add(btnTaoHD);

        lblThongTinNguoiMua = new JLabel("Chưa chọn người mua");
        lblThongTinNguoiMua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblThongTinNguoiMua.setForeground(new Color(33, 150, 243));
        infoPanel.add(lblThongTinNguoiMua);

        // Panel thêm chi tiết
        JPanel chiTietPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        chiTietPanel.add(new JLabel("Mã Vé:"));
        txtMaVe = new JTextField(10);
        chiTietPanel.add(txtMaVe);

        chiTietPanel.add(new JLabel("Số lượng:"));
        txtSoLuong = new JTextField(10);
        chiTietPanel.add(txtSoLuong);

        JButton btnThemChiTiet = new JButton("Thêm Chi Tiết");
        btnThemChiTiet.addActionListener(e -> themChiTiet());
        chiTietPanel.add(btnThemChiTiet);

        // Panel buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnLuuHD = new JButton("Lưu Hóa Đơn");
        JButton btnHuyHD = new JButton("Hủy");

        Dimension btnSize = new Dimension(120, 35);
        btnLuuHD.setPreferredSize(btnSize);
        btnHuyHD.setPreferredSize(btnSize);

        btnLuuHD.addActionListener(e -> luuHoaDon());
        btnHuyHD.addActionListener(e -> huyHoaDon());

        buttonPanel.add(btnLuuHD);
        buttonPanel.add(btnHuyHD);

        lblTongTien = new JLabel("Tổng tiền: 0 VNĐ");
        lblTongTien.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTongTien.setForeground(new Color(229, 57, 53)); // Red
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(lblTongTien);

        // Table chi tiết
        String[] columnsChiTiet = { "Mã Vé", "Loại Ghế", "Số Lượng", "Đơn Giá", "Thành Tiền" };
        modelChiTiet = new DefaultTableModel(columnsChiTiet, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableChiTiet = new JTable(modelChiTiet);
        tableChiTiet.setRowHeight(25);
        tableChiTiet.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        JScrollPane scrollChiTiet = new JScrollPane(tableChiTiet);

        JPanel controlPanel = new JPanel(new BorderLayout());
        JPanel inputContainer = new JPanel(new GridLayout(2, 1));
        inputContainer.add(infoPanel);
        inputContainer.add(chiTietPanel);

        controlPanel.add(inputContainer, BorderLayout.NORTH);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);

        topPanel.add(controlPanel, BorderLayout.NORTH);
        topPanel.add(scrollChiTiet, BorderLayout.CENTER);

        // Panel danh sách hóa đơn (phía dưới)
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Danh Sách Hóa Đơn Đã Lưu"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        String[] columnsHoaDon = { "Mã HĐ", "Mã NM", "Họ Tên", "Số Loại Vé", "Tổng SL", "Tổng Tiền" };
        modelHoaDon = new DefaultTableModel(columnsHoaDon, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableHoaDon = new JTable(modelHoaDon);
        tableHoaDon.setRowHeight(25);
        tableHoaDon.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        JScrollPane scrollHoaDon = new JScrollPane(tableHoaDon);

        JButton btnTaiLai = new JButton("Tải lại danh sách");
        btnTaiLai.addActionListener(e -> loadDanhSachHoaDon());

        bottomPanel.add(scrollHoaDon, BorderLayout.CENTER);
        bottomPanel.add(btnTaiLai, BorderLayout.SOUTH);

        splitPane.setTopComponent(topPanel);
        splitPane.setBottomComponent(bottomPanel);

        add(splitPane, BorderLayout.CENTER);
    }

    private void taoHoaDon() {
        try {
            int maNguoiMua = Integer.parseInt(txtMaNguoiMua.getText().trim());
            hoaDonHienTai = controller.taoHoaDon(maNguoiMua);

            if (hoaDonHienTai != null) {
                lblThongTinNguoiMua.setText("Họ tên: " + hoaDonHienTai.getHoTenNguoiMua());
                modelChiTiet.setRowCount(0);
                capNhatTongTien();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Mã người mua không hợp lệ!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void themChiTiet() {
        if (hoaDonHienTai == null) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng tạo hóa đơn trước!",
                    "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int maVe = Integer.parseInt(txtMaVe.getText().trim());
            int soLuong = Integer.parseInt(txtSoLuong.getText().trim());

            if (controller.themChiTiet(hoaDonHienTai, maVe, soLuong)) {
                hienThiChiTiet();
                capNhatTongTien();
                txtMaVe.setText("");
                txtSoLuong.setText("");
                txtMaVe.requestFocus();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Dữ liệu không hợp lệ!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void luuHoaDon() {
        if (hoaDonHienTai == null) {
            JOptionPane.showMessageDialog(this,
                    "Chưa có hóa đơn để lưu!",
                    "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (controller.luuHoaDon(hoaDonHienTai)) {
            huyHoaDon();
            loadDanhSachHoaDon();
        }
    }

    private void huyHoaDon() {
        hoaDonHienTai = null;
        txtMaNguoiMua.setText("");
        txtMaVe.setText("");
        txtSoLuong.setText("");
        lblThongTinNguoiMua.setText("Chưa chọn người mua");
        modelChiTiet.setRowCount(0);
        capNhatTongTien();
        txtMaNguoiMua.requestFocus();
    }

    private void hienThiChiTiet() {
        modelChiTiet.setRowCount(0);
        if (hoaDonHienTai != null) {
            for (ChiTietHD ct : hoaDonHienTai.getChiTietList()) {
                modelChiTiet.addRow(new Object[] {
                        String.format("%05d", ct.getMaVe()),
                        ct.getLoaiGhe(),
                        ct.getSoLuong(),
                        String.format("%,.0f", ct.getDonGia()),
                        String.format("%,.0f", ct.calculateTotal())
                });
            }
        }
    }

    private void capNhatTongTien() {
        double tongTien = 0;
        if (hoaDonHienTai != null) {
            tongTien = hoaDonHienTai.calculateTotal();
        }
        lblTongTien.setText(String.format("Tổng tiền: %,.0f VNĐ", tongTien));
    }

    private void loadDanhSachHoaDon() {
        modelHoaDon.setRowCount(0);
        List<HoaDon> list = controller.layDanhSachHoaDon();

        if (list != null) {
            for (HoaDon hd : list) {
                modelHoaDon.addRow(new Object[] {
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