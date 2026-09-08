# Release & operations runbook

1. محتوا و چهار مورد تأیید مالک در `CONTENT_MAPPING.md` بررسی شود.
2. `test lintDebug assembleDebug` اجرا و APK روی حداقل یک گوشی API 26 و یک API 36 smoke-test شود.
3. نسخه، changelog و Data Safety کنترل شوند؛ AAB با upload key امضا شود.
4. Internal → closed → 1% → 5% → 20% → 50% → 100%؛ در هر مرحله vitals و خطای رزرو 24–48 ساعت بررسی شود.
5. توقف: crash بالاتر از 0.5%، ANR بالاتر از 0.2% یا خطای رزرو بالاتر از 1%. rollout halt و نسخه سالم قبلی نگه داشته شود.
6. محتوای فوری با CMS/Remote Config غیرفعال شود؛ تغییر قیمت یا ظرفیت فقط سمت سرور.

برای حساب شخصی Play Console جدید، پیش از production ممکن است closed test با ۱۲ تستر برای ۱۴ روز لازم باشد. پیش از انتشار الزام روز Google Play دوباره بررسی شود.
