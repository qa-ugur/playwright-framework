package com.playwright.tests;

import com.playwright.base.BaseTest;
import com.playwright.core.ConfigReader;
import com.playwright.pages.LoginPage;
import com.playwright.pages.DashboardPage;
import com.playwright.pages.PIMPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class PIMTest extends BaseTest {

    @Test
    public void testAddNewEmployee() {
        // 1. Giriş Yap
        page.navigate(ConfigReader.get("baseUrl"));
        LoginPage loginPage = new LoginPage(page);
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

        // 2. Dashboard Sayfasından PIM Menüsüne Geç
        DashboardPage dashboardPage = new DashboardPage(page);
        PIMPage pimPage = dashboardPage.clickPIMMenu();

        // 3. Yeni Çalışan Ekle
        pimPage.addEmployee("User1", "Test");

        // 4. Doğrulama (Assertion): Kayıt başarılı oldu bildirimi geldi mi?
        Assertions.assertTrue(pimPage.isSuccessMessageVisible(), "Çalışan eklenirken hata oluştu veya bildirim görünmedi!");

        // Gözümüzle görmek için 3 saniye bekletelim
        page.waitForTimeout(3000);
    }
}