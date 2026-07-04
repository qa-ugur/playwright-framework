package com.playwright.pages;

import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    // locators
    private final String usernameInput = "input[name='username']";
    private final String passwordInput = "input[name='password']";
    private final String loginButton = "button[type='submit']";

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

    // full flow (EN ÖNEMLİ KISIM)
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}