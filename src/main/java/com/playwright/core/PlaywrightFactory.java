package com.playwright.core;

import com.microsoft.playwright.*;

public class PlaywrightFactory {

    // Her thread'in kendi kopyasını tutması için ThreadLocal yapısına geçiyoruz
    private static final ThreadLocal<Playwright> playwrightThread = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browserThread = new ThreadLocal<>();

    public static Browser launchBrowser() {
        // Eğer bu thread için henüz Playwright başlatılmadıysa başlat
        if (playwrightThread.get() == null) {
            playwrightThread.set(Playwright.create());
        }

        String browserType = ConfigReader.get("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));

        Browser browserInstance;
        switch (browserType.toLowerCase()) {
            case "firefox":
                browserInstance = playwrightThread.get().firefox().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless)
                );
                break;
            case "webkit":
                browserInstance = playwrightThread.get().webkit().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless)
                );
                break;
            default:
                browserInstance = playwrightThread.get().chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless)
                );
        }

        // Üretilen browser'ı bu thread'e özel olarak sakla
        browserThread.set(browserInstance);
        return browserInstance;
    }

    public static Browser getBrowser() {
        return browserThread.get();
    }

    public static Playwright getPlaywright() {
        return playwrightThread.get();
    }

    // Paralel testlerde bellek sızıntısını (Memory Leak) önlemek için temizlik metodu
    public static void unload() {
        browserThread.remove();
        playwrightThread.remove();
    }
}