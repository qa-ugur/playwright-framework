package com.playwright.pages;

import com.microsoft.playwright.Page;

public class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    // navigate
    public void navigate(String url) {
        page.navigate(url);
    }

    // title
    public String getTitle() {
        return page.title();
    }

    // url
    public String getUrl() {
        return page.url();
    }

    // click
    public void click(String selector) {
        page.click(selector);
    }

    // fill
    public void fill(String selector, String value) {
        page.fill(selector, value);
    }

    // get text
    public String getText(String selector) {
        return page.textContent(selector);
    }

    // visibility
    public boolean isVisible(String selector) {
        return page.isVisible(selector);
    }

    // wait
    public void waitForSelector(String selector) {
        page.waitForSelector(selector);
    }
}