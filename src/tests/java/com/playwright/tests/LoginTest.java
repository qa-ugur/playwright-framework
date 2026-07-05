package com.playwright.tests;

import com.playwright.base.BaseTest;
import com.playwright.core.ConfigReader;
import com.playwright.pages.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {

        page.navigate(ConfigReader.get("baseUrl"));

        LoginPage loginPage = new LoginPage(page);

        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
        page.waitForTimeout(4000); // 4 saniye ekranı açık tutar
        // basic assertion
        Assertions.assertTrue(page.url().contains("dashboard"));
    }
    @Test
    public void invalidLoginTest() {
        // 1. Sayfaya git
        page.navigate(ConfigReader.get("baseUrl"));

        LoginPage loginPage = new LoginPage(page);

        // 2. Yanlış bilgilerle login olmayı dene
        loginPage.login("GecersizKullanici", "YanlisSifre123");

        // 3. Ekrana gelen gerçek hata mesajını metottan iste
        String actualErrorMessage = loginPage.getErrorMessageText();

        // 4. Bizim beklediğimiz mesaj
        String expectedErrorMessage = "Invalid credentials";

        // 5. DOĞRULAMA: Beklenen ile Gerçek değer birebir eşit mi?
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}