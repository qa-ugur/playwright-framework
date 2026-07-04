package com.playwright.core;

import com.microsoft.playwright.*;

public class PlaywrightFactory {

    private static Playwright playwright;
    private static Browser browser;

    public static Browser launchBrowser() {

        playwright = Playwright.create();

        String browserType = ConfigReader.get("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));

        switch (browserType.toLowerCase()) {

            case "firefox":
                browser = playwright.firefox().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless)
                );
                break;

            case "webkit":
                browser = playwright.webkit().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless)
                );
                break;

            default:
                browser = playwright.chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless)
                );
        }

        return browser;
    }

    public static Playwright getPlaywright() {
        return playwright;
    }
}