# Architecture

- Kotlin + Jetpack Compose + Material 3، single activity و state قابل ذخیره.
- UI فقط قرارداد `ContentRepository` را می‌بیند؛ `BundledContentRepository` fallback آفلاین است.
- مسیر production: CMS → HTTPS API/Firestore → repository → cache/Room → ViewModel/StateFlow → UI.
- محتوای عمومی بدون login؛ عملیات حساس فقط سمت سرور و server-authoritative.
- هیچ secret یا کلید مدیر در APK؛ HTTPS-only؛ لینک‌های بیرونی allowlist در پیاده‌سازی CMS.
- توسعه پیشنهادی بدون شکستن UI: افزودن Hilt، Room، WorkManager، Retrofit/Firestore، Remote Config، Crashlytics و FCM در ماژول data.

## بودجه‌ها

cold start p95 زیر ۲ ثانیه، janky frames زیر ۱٪، download زیر ۲۵MB، crash-free users حداقل ۹۹٫۹٪ و ANR زیر ۰٫۱٪. «صفر کرش برای همیشه» قابل تضمین نیست؛ rollout مرحله‌ای و پایش production جزئی از کیفیت است.
