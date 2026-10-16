# Hải Sản Dốc Lết — Android + Web/PWA

## Đồng bộ sản phẩm và đặt hàng
Ứng dụng Android mở trực tiếp `https://haisandoclech.vn/` trong WebView. Web/PWA cũng đưa khách vào website chính thức. Vì vậy giá, tồn kho, giỏ hàng và checkout được lấy từ website, không tạo catalog/giỏ hàng giả ở phía app.

## Chạy Web/PWA
1. Deploy thư mục `web/` lên Netlify hoặc máy chủ HTTPS.
2. Khách mở URL đã deploy và có thể cài PWA nếu trình duyệt hỗ trợ.
3. PWA chuyển khách tới website chính thức để mua hàng.

## Build Android APK
1. Mở thư mục `android/` bằng Android Studio.
2. Chờ Gradle sync.
3. Build > Build APK(s), hoặc dùng GitHub Actions workflow `.github/workflows/build-apk.yml`.
4. Workflow sẽ build debug APK và lưu artifact để tải về.

## Lưu ý
- Đây là bản tích hợp website hiện có, không thay thế backend thương mại điện tử.
- Cần internet để xem sản phẩm/đặt hàng.
- Checkout và dữ liệu đơn hàng phụ thuộc hoàn toàn vào website chính thức.
- APK chưa được ký phát hành (release signing); debug APK dùng thử.
