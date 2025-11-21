package com.app.qlvetau.model.view;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

/**
 * Main Frame - Giao diện chính của ứng dụng
 */
public class MainFrame extends JFrame {
    
    public MainFrame() {
        setTitle("QUẢN LÝ BÁN VÉ TÀU HỎA");
        setSize(1000, 700);
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
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 20, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("QUẢN LÝ BÁN VÉ TÀU HỎA");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(33, 150, 243)); // Material Blue
        panel.add(titleLabel, gbc);
        
        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 40, 10);
        JLabel subtitleLabel = new JLabel("Hệ thống quản lý chuyên nghiệp & hiện đại");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitleLabel.setForeground(Color.GRAY);
        panel.add(subtitleLabel, gbc);
        
        gbc.gridy++;
        gbc.insets = new Insets(10, 10, 10, 10);
        JPanel infoPanel = new JPanel(new GridLayout(0, 2, 20, 20));
        infoPanel.setBackground(Color.WHITE);
        
        addInfoItem(infoPanel, "Người Mua", "Quản lý thông tin khách hàng");
        addInfoItem(infoPanel, "Vé Tàu", "Quản lý danh sách vé");
        addInfoItem(infoPanel, "Hóa Đơn", "Lập và in hóa đơn");
        addInfoItem(infoPanel, "Báo Cáo", "Thống kê doanh thu");

        panel.add(infoPanel, gbc);
        
        gbc.gridy++;
        gbc.insets = new Insets(40, 10, 10, 10);
        JLabel footerLabel = new JLabel("Chọn chức năng trên thanh menu để bắt đầu");
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        footerLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(footerLabel, gbc);
        
        add(panel);
    }

    private void addInfoItem(JPanel parent, String title, String desc) {
        JPanel item = new JPanel(new BorderLayout(5, 5));
        item.setBackground(new Color(245, 245, 245));
        item.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(new Color(66, 66, 66));
        
        JLabel lblDesc = new JLabel(desc);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDesc.setForeground(Color.GRAY);
        
        item.add(lblTitle, BorderLayout.NORTH);
        item.add(lblDesc, BorderLayout.CENTER);
        
        parent.add(item);
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
                FlatLightLaf.setup();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}