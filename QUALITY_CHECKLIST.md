# Quality gate

هر ردیف فقط همراه evidence قابل Done شدن است.

- [x] اپ native و بدون WebView — `SalsaCompanyApp.kt`
- [x] انتخاب شعبه و حفظ انتخاب هنگام چرخش/process recreation — `rememberSaveable`
- [x] کلاس، رویداد، قیمت، تیم، تماس، legal و رزرو رسمی — UI + content mapping
- [x] حداقل لمس 48dp و Material controls — Compose Material 3
- [x] HTTPS-only — Manifest + unit test URL
- [x] fallback امن برای intent ناموجود — `runCatching`
- [x] هیچ PII، رمز یا کلید در اپ نیست — code inspection
- [x] release minify/resource shrink — Gradle release config
- [x] مدل قابل تعویض با CMS — `ContentRepository`
- [ ] build/test/lint واقعی — نیازمند Android SDK و Gradle در محیط build
- [ ] screenshot test: small/large, dark/light, font 200%, German strings
- [ ] TalkBack walkthrough و accessibility scanner
- [ ] monkey 100k، rapid tab/back، rotate و process-death
- [ ] Test Lab matrix API 26/30/36 و low-RAM
- [ ] backend contract/rules/load/soak — پس از ایجاد backend رسمی
- [ ] Crashlytics/Android Vitals و rollout 1→5→20→50→100٪
- [ ] امضای AAB، Play App Signing، Data Safety، privacy URL و store listing
- [ ] owner sign-off برای داده‌های متناقض `CONTENT_MAPPING.md`
