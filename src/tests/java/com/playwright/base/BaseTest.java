package com.playwright.base;

import com.microsoft.playwright.*;
import com.playwright.core.PlaywrightFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeEach
    public void setUp() {
        browser = PlaywrightFactory.launchBrowser();
        playwright = PlaywrightFactory.getPlaywright();

        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    public void tearDown() {
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();

        // Playwright ve Browser thread referanslarını temizle
        PlaywrightFactory.unload();
    }
}