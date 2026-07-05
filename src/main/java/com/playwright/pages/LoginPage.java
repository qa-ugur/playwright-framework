package com.playwright.pages;

import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    // locators
    private final String usernameInput = "input[name='username']";
    private final String passwordInput = "input[name='password']";
    private final String loginButton = "button[type='submit']";

    // YENİ LOCATOR: OrangeHRM'deki hata mesajı kutusunun seçicisi (p.oxd-alert-content-text)
    private final String errorMessageAlert = "p.oxd-alert-content-text";

    public LoginPage(Page page) {
        super(page);
    }

    // actions
    public void enterUsername(String username) {
        fill(usernameInput, username);
    }

    public void enterPassword(String password) {
        fill(passwordInput, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    // YENİ METOT: Ekranda hata mesajı görünüyor mu ve içeriği ne?
    // String dönüyoruz ki test sınıfında text kontrolü yapabilelim.
    public String getErrorMessageText() {
        // Playwright arka planda bu elementin görünmesini bekler ve içindeki metni alır.
        return page.textContent(errorMessageAlert).trim();
    }

    // full flow (EN ÖNEMLİ KISIM)
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}