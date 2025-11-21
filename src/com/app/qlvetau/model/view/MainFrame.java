package com.app.qlvetau.model.view;

import javax.swing.*;
import java.awt.*;

/**
 * Main Frame - Giao diện chính của ứng dụng
 */
public class MainFrame extends JFrame {
    
    public MainFrame() {
        setTitle("QUẢN LÝ BÁN VÉ TÀU HỎA");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Tạo menu bar
        createMenuBar();
        
        // Tạo panel chào mừng
        createWelcomePanel();
    }
    
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // Menu Người Mua
        JMenu menuNguoiMua = new JMenu("Người Mua");
        JMenuItem itemNguoiMua = new JMenuItem("Quản lý Người Mua");
        itemNguoiMua.addActionListener(e -> openFormNguoiMua());
        menuNguoiMua.add(itemNguoiMua);
        
        // Menu Vé
        JMenu menuVe = new JMenu("Vé Tàu");
        JMenuItem itemVe = new JMenuItem("Quản lý Vé");
        itemVe.addActionListener(e -> openFormVe());
        menuVe.add(itemVe);
        
        // Menu Hóa Đơn
        JMenu menuHoaDon = new JMenu("Hóa Đơn");
        JMenuItem itemHoaDon = new JMenuItem("Lập Hóa Đơn");
        itemHoaDon.addActionListener(e -> openFormHoaDon());
        menuHoaDon.add(itemHoaDon);
        
        // Menu Sắp Xếp
        JMenu menuSapXep = new JMenu("Sắp Xếp");
        JMenuItem itemSapXep = new JMenuItem("Sắp xếp Hóa Đơn");
        itemSapXep.addActionListener(e -> openFormSapXep());
        menuSapXep.add(itemSapXep);
        
        // Menu Báo Cáo
        JMenu menuBaoCao = new JMenu("Báo Cáo");
        JMenuItem itemBaoCao = new JMenuItem("Bảng Kê Thanh Toán");
        itemBaoCao.addActionListener(e -> openFormBaoCao());
        menuBaoCao.add(itemBaoCao);
        
        // Menu Thoát
        JMenu menuThoat = new JMenu("Hệ Thống");
        JMenuItem itemThoat = new JMenuItem("Thoát");
        itemThoat.addActionListener(e -> System.exit(0));
        menuThoat.add(itemThoat);
        
        menuBar.add(menuNguoiMua);
        menuBar.add(menuVe);
        menuBar.add(menuHoaDon);
        menuBar.add(menuSapXep);
        menuBar.add(menuBaoCao);
        menuBar.add(menuThoat);
        
        setJMenuBar(menuBar);
    }
    
    private void createWelcomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel titleLabel = new JLabel("QUẢN LÝ BÁN VÉ TÀU HỎA", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 30, 0));
        
        JPanel infoPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 50, 100));
        
        String[] menuItems = {
            "1. Người Mua → Quản lý thông tin người mua vé",
            "2. Vé Tàu → Quản lý các loại vé",
            "3. Hóa Đơn → Lập hóa đơn mua vé",
            "4. Sắp Xếp → Sắp xếp danh sách hóa đơn",
            "5. Báo Cáo → Xem bảng kê thanh toán",
            "6. Hệ Thống → Thoát chương trình"
        };
        
        for (String item : menuItems) {
            JLabel label = new JLabel(item);
            label.setFont(new Font("Arial", Font.PLAIN, 16));
            infoPanel.add(label);
        }
        
        JLabel footerLabel = new JLabel("Chọn menu phía trên để bắt đầu", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        footerLabel.setForeground(Color.GRAY);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(footerLabel, BorderLayout.SOUTH);
        
        add(panel);
    }
    
    private void openFormNguoiMua() {
        FormNguoiMua form = new FormNguoiMua();
        form.setVisible(true);
    }
    
    private void openFormVe() {
        FormVe form = new FormVe();
        form.setVisible(true);
    }
    
    private void openFormHoaDon() {
        FormHoaDon form = new FormHoaDon();
        form.setVisible(true);
    }
    
    private void openFormSapXep() {
        FormSapXep form = new FormSapXep();
        form.setVisible(true);
    }
    
    private void openFormBaoCao() {
        FormBaoCao form = new FormBaoCao();
        form.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}