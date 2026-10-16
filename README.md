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


## App version
- `versionCode`: 2
- `versionName`: 1.0.1
- GitHub Actions artifact: `Hai-San-Doc-Lech-v1.0.1-debug`
- APK filename: `Hai-San-Doc-Lech-v1.0.1-debug.apk`

For the next release, increase `versionCode` by at least 1 and update `versionName` in `android/app/build.gradle`, then update the versioned filename/artifact in `.github/workflows/build-apk.yml`.


## Version 1.0.2 — Android status-bar overlap fix

The Android WebView is placed inside a native root container that applies Android system-bar and display-cutout insets before the web content is laid out. This is intended to prevent the website's promotional banner from appearing behind the Android status bar on Android 15+ devices. The GitHub Actions workflow names the debug APK `Hai-San-Doc-Lech-v1.0.2-debug.apk`.

This source change has not been confirmed by a successful device build in this environment. Build it with GitHub Actions, install the resulting APK, and verify on the affected phone. If the same overlap happens when opening the site in Chrome/PWA rather than the APK, a website-side safe-area fix is also required.
