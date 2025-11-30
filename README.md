# Project E-Ink

**Project E-Ink** is a unified "E-Ink Simulator" ecosystem for Android. It transforms a standard smartphone into a distraction-free, eye-friendly device by combining a text-based launcher, a custom reading engine, and system-level monochrome control.

## Features (Planned)
- **Text-Based Launcher:** Minimalist interface with no colorful icons.
- **Reading Engine:** Built-in browser with Readability.js to strip clutter from web pages.
- **System Controller:** Global monochrome mode and red-tint filter for night use.

## Architecture
- **Language:** Kotlin
- **UI:** Jetpack Compose
- **DI:** Hilt
- **Architecture:** MVVM + Clean Architecture

## Setup
This project uses Gradle Version Catalogs.
1. Clone the repository.
2. Open in Android Studio.
3. Sync Gradle.

---

# نقشه راه جامع پروژه E-Ink (Project Roadmap)

این سند نقشه راه استراتژیک و فنی برای توسعه "اکوسیستم لانچر یکپارچه E-Ink" است. هدف این است که با یک ساختار حرفه‌ای و مدیریت شده، از MVP به سمت محصول نهایی حرکت کنیم.

## فاز ۱: زیرساخت و نمونه اولیه (MVP - ماه ۱-۲)
**هدف:** اثبات کارایی فنی (Proof of Concept) و داشتن یک لانچر قابل استفاده اولیه.

### ۱.۱. زیرساخت پروژه (Project Setup)
- [x] راه‌اندازی مخزن گیت (Git Repository) با ساختار استاندارد (Gitflow).
- [x] پیکربندی پروژه اندروید (Kotlin, Jetpack Compose).
- [x] تنظیم معماری MVVM و Hilt برای تزریق وابستگی.

### ۱.۲. لایه ۱: لانچر پایه (Core Launcher)
- [x] پیاده‌سازی لیست اپلیکیشن‌های متنی (Text-based App Drawer).
- [x] قابلیت انتخاب اپلیکیشن‌های "مورد علاقه" (Favorites) برای صفحه اصلی.
- [x] پیاده‌سازی تم سیاه مطلق (True Black) و سفید (Day Mode).
- [x] **بروزرسانی:** پیاده‌سازی رابط کاربری ورق‌زن (Page Flip).
- [x] **بروزرسانی:** پیاده‌سازی آیکون‌های سیاه و سفید (Grayscale Icons).

### ۱.۳. لایه ۳: کنترل سیستم (System Controller - MVP)
- [ ] پیاده‌سازی ماژول ADB Helper (تشخیص وضعیت مجوز `WRITE_SECURE_SETTINGS`).
- [ ] قابلیت تغییر دستی حالت سیاه و سفید (Monochrome) در صورت داشتن مجوز.

---

## فاز ۲: مرورگر و یکپارچه‌سازی (Alpha - ماه ۳-۴)
**هدف:** اضافه کردن "مرورگر متنی" و تکمیل تجربه کاربری یکپارچه.

### ۲.۱. لایه ۲: موتور مرورگر (Reading Engine)
- [ ] پیاده‌سازی `WebView` سفارشی.
- [ ] ادغام کتابخانه `Readability.js` برای استخراج متن.
- [ ] طراحی رابط کاربری مرورگر (بدون نوار آدرس مزاحم، فقط متن).

### ۲.۲. یکپارچه‌سازی (Integration)
- [ ] باز کردن لینک‌ها مستقیماً در مرورگر داخلی (Deep Linking).
- [ ] انیمیشن‌های نرم و مینیمال برای جابجایی بین لانچر و مرورگر.

---

## فاز ۳: ویژگی‌های پیشرفته و پولیش (Beta - ماه ۵)
**هدف:** آماده‌سازی برای انتشار عمومی و اضافه کردن ویژگی‌های "پرو".

### ۳.۱. ویژگی‌های پیشرفته (Pro Features)
- [ ] **فیلتر نور قرمز (Red Tint):** پیاده‌سازی Overlay برای استفاده شبانه.
- [ ] **اتوماسیون:** تغییر خودکار تم بر اساس طلوع/غروب خورشید.

### ۳.۲. تجربه کاربری (UX Polish)
- [ ] طراحی Onboarding (راهنمای اولیه) تعاملی برای آموزش گرفتن مجوز ADB.
- [ ] بهینه‌سازی مصرف باتری و پروفایلینگ (Profiling).

---

## فاز ۴: انتشار و بازخورد (Release - ماه ۶)
**هدف:** انتشار در گوگل پلی و جذب کاربر.

- [ ] تست‌های نهایی (QA) روی دستگاه‌های مختلف.
- [ ] تهیه اسکرین‌شات‌ها و ویدیوهای تبلیغاتی مینیمال.
- [ ] انتشار نسخه اولیه (Early Access).

---

## استراتژی فنی و ابزارها (Tech Stack)

*   **Language:** Kotlin
*   **UI:** Jetpack Compose (برای رابط کاربری مدرن و سریع)
*   **Architecture:** MVVM + Clean Architecture
*   **Dependency Injection:** Hilt
*   **Async:** Coroutines + Flow
*   **Local Data:** DataStore (برای تنظیمات) / Room (برای لیست اپ‌ها و بوک‌مارک‌ها)
*   **Browser Engine:** Android WebView + Mozilla Readability.js

## مدیریت پروژه (Project Management)

*   **Git Strategy:** استفاده از شاخه‌های `main` (پایدار)، `develop` (توسعه) و `feature/*` (ویژگی‌ها).
*   **Task Tracking:** استفاده از GitHub Issues/Projects برای مدیریت تسک‌ها.
*   **Code Style:** رعایت استاندارد Kotlin Coding Conventions.
