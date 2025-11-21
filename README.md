# Quản Lý Bán Vé Tàu Hỏa

Ứng dụng quản lý bán vé tàu hỏa được xây dựng bằng Java Swing với kiến trúc MVC.

## 📋 Mô tả

Hệ thống quản lý bán vé tàu hỏa giúp quản lý thông tin người mua, vé tàu, lập hóa đơn và tạo báo cáo thanh toán. Ứng dụng sử dụng lưu trữ dữ liệu dạng file (.DAT) và cung cấp giao diện đồ họa thân thiện với người dùng.

## ✨ Tính năng chính

### 1. Quản lý Người Mua
- Thêm, sửa, xóa thông tin người mua vé
- Tìm kiếm người mua theo mã hoặc tên
- Lưu trữ: Mã người mua, Họ tên, Địa chỉ, Số điện thoại

### 2. Quản lý Vé Tàu
- Quản lý danh sách các loại vé (ghế ngồi, giường nằm...)
- Cập nhật đơn giá cho từng loại vé
- Mã vé tự động tăng (bắt đầu từ 20000)

### 3. Lập Hóa Đơn
- Tạo hóa đơn mua vé cho người mua
- Thêm nhiều loại vé vào một hóa đơn
- Tự động tính tổng tiền
- Mã hóa đơn tự động tăng (bắt đầu từ 30000)

### 4. Sắp Xếp
- Sắp xếp danh sách hóa đơn theo nhiều tiêu chí:
  - Theo mã hóa đơn
  - Theo tên người mua
  - Theo tổng tiền
  - Theo số lượng vé

### 5. Báo Cáo
- Bảng kê thanh toán chi tiết
- Thống kê tổng doanh thu
- Xuất báo cáo theo hóa đơn

## 🏗️ Kiến trúc dự án

```
src/com/app/qlvetau/model/
├── business/           # Business Logic Layer
│   └── HoaDonCalculator.java
├── Controller/         # Controller Layer
│   ├── BaoCaoController.java
│   ├── HoaDonController.java
│   ├── NguoiMuaController.java
│   ├── SortController.java
│   └── VeController.java
├── dao/               # Data Access Layer
│   ├── FileUtil.java
│   ├── HoaDonDAO.java
│   ├── NguoiMuaDAO.java
│   └── VeDAO.java
├── entity/            # Entity/Model Layer
│   ├── ChiTietHD.java
│   ├── HoaDon.java
│   ├── NguoiMua.java
│   └── Ve.java
├── interfaces/        # Interface Layer
│   ├── IAutoId.java
│   ├── ICanCalculate.java
│   └── IFileEntity.java
└── view/              # View Layer (GUI)
    ├── FormBaoCao.java
    ├── FormHoaDon.java
    ├── FormNguoiMua.java
    ├── FormSapXep.java
    ├── FormVe.java
    └── MainFrame.java
```

## 🔧 Công nghệ sử dụng

- **Java**: JDK 17
- **GUI Framework**: Java Swing
- **Build Tool**: Maven
- **Framework**: Spring Boot 4.0.0
- **Architecture**: MVC (Model-View-Controller)
- **Data Storage**: File-based (.DAT files)

## 📦 Cài đặt và Chạy

### Yêu cầu hệ thống
- Java JDK 17 trở lên
- Maven 3.x

### Cài đặt

1. Clone repository:
```bash
git clone https://github.com/Hunggoodboy/21-11Java.git
cd 21-11Java
```

2. Build project với Maven:
```bash
mvnw clean install
```

### Chạy ứng dụng

**Cách 1: Chạy từ file Java**
```bash
java -cp bin com.app.qlvetau.model.view.MainFrame
```

**Cách 2: Chạy với Maven**
```bash
mvnw spring-boot:run
```

**Cách 3: Chạy trực tiếp từ IDE**
- Mở project trong IntelliJ IDEA hoặc Eclipse
- Tìm file `MainFrame.java`
- Click chuột phải và chọn "Run"

## 📂 File dữ liệu

Ứng dụng sử dụng 3 file để lưu trữ dữ liệu:
- `NGUOIMUA.DAT` - Lưu thông tin người mua
- `VE.DAT` - Lưu thông tin vé tàu
- `HOADON.DAT` - Lưu thông tin hóa đơn

Các file này được tự động tạo trong thư mục gốc của dự án.

## 🎯 Hướng dẫn sử dụng

### 1. Khởi động ứng dụng
- Chạy `MainFrame.java`
- Giao diện chính hiển thị với menu điều hướng

### 2. Quản lý Người Mua
- Chọn menu **Người Mua** → **Quản lý Người Mua**
- Nhập thông tin: Họ tên, Địa chỉ, Số điện thoại
- Click **Thêm** để lưu
- Sử dụng **Sửa**, **Xóa** để cập nhật dữ liệu

### 3. Quản lý Vé
- Chọn menu **Vé Tàu** → **Quản lý Vé**
- Nhập loại ghế và đơn giá
- Click **Thêm** để lưu vé mới

### 4. Lập Hóa Đơn
- Chọn menu **Hóa Đơn** → **Lập Hóa Đơn**
- Chọn người mua từ danh sách
- Thêm các loại vé và số lượng
- Hệ thống tự động tính tổng tiền
- Click **Lưu** để hoàn tất

### 5. Xem Báo Cáo
- Chọn menu **Báo Cáo** → **Bảng Kê Thanh Toán**
- Xem danh sách hóa đơn và tổng doanh thu

## 🔑 Các tính năng kỹ thuật

### Auto-increment ID
- Mã người mua: Bắt đầu từ 10000
- Mã vé: Bắt đầu từ 20000
- Mã hóa đơn: Bắt đầu từ 30000

### Design Patterns
- **MVC Pattern**: Tách biệt View, Controller và Model
- **DAO Pattern**: Quản lý truy cập dữ liệu
- **Interface Segregation**: IAutoId, ICanCalculate, IFileEntity

### Serialization
- Lưu trữ dữ liệu dạng text với delimiter `|`
- Hỗ trợ đọc/ghi file tự động
- Khôi phục ID counter khi khởi động

## 📝 Cấu trúc Entity

### NguoiMua (Người Mua)
```java
- maNguoiMua: int (auto-increment)
- hoTen: String
- diaChi: String
- soDienThoai: String
```

### Ve (Vé)
```java
- maVe: int (auto-increment)
- loaiGhe: String
- donGia: double
```

### HoaDon (Hóa Đơn)
```java
- maHoaDon: int (auto-increment)
- maNguoiMua: int
- hoTenNguoiMua: String
- chiTietList: List<ChiTietHD>
```

### ChiTietHD (Chi Tiết Hóa Đơn)
```java
- maVe: int
- loaiGhe: String
- donGia: double
- soLuong: int
```

## 🤝 Đóng góp

Mọi đóng góp đều được chào đón! Vui lòng:
1. Fork repository
2. Tạo branch mới (`git checkout -b feature/TinhNangMoi`)
3. Commit thay đổi (`git commit -m 'Thêm tính năng mới'`)
4. Push lên branch (`git push origin feature/TinhNangMoi`)
5. Tạo Pull Request

## 👤 Tác giả

- **GitHub**: [Hunggoodboy](https://github.com/Hunggoodboy)

## 📄 License

Dự án được phát triển cho mục đích học tập và nghiên cứu.

## 📞 Liên hệ

Nếu có thắc mắc hoặc góp ý, vui lòng tạo issue trên GitHub.

---

**Lưu ý**: Đây là ứng dụng demo cho mục đích học tập. Trong môi trường production, nên sử dụng database thay vì file storage và thêm các tính năng bảo mật.
