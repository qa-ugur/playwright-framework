package com.playwright.tests;

import com.playwright.base.BaseTest;
import com.playwright.core.ConfigReader;
import com.playwright.pages.DashboardPage;
import com.playwright.pages.LoginPage;
import com.playwright.utils.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DashboardTest extends BaseTest {

    @Test
    public void testAllMenus() {
        page.navigate(ConfigReader.get("baseUrl"));
        new LoginPage(page).login(ConfigReader.get("username"), ConfigReader.get("password"));
        DashboardPage dashboardPage = new DashboardPage(page);

        // Hataları toplamak için bir liste
        List<String> failedMenus = new ArrayList<>();

        for (Menu menu : Menu.values()) {
            try {
                dashboardPage.navigateToMenu(menu);

                // Küçük bir bekleme (sayfa yüklenmesi için)
                page.waitForLoadState();

                // Assertion
                Assertions.assertTrue(page.url().contains(menu.getMenuName().toLowerCase()),
                        "URL doğrulaması başarısız: " + menu.getMenuName());

                System.out.println("Başarılı: " + menu.getMenuName());
                page.goBack();
                page.waitForLoadState();

            }catch (Exception e) {
            System.err.println("HATA: " + menu.getMenuName() + " navigasyonu başarısız!");
            failedMenus.add(menu.getMenuName());

            // URL'yi manuel birleştirmek yerine ana sayfaya dönüp dashboard'a tıklatmak en garantisidir
            page.navigate(ConfigReader.get("baseUrl"));
            // Veya direkt Dashboard'un olduğu URL'yi tam olarak yaz:
            // page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        }
        }

        // Döngü bittiğinde, eğer hatalı menü varsa testi o zaman fail ettir
        Assertions.assertTrue(failedMenus.isEmpty(), "Şu menülerde hata oluştu: " + failedMenus);
    }
}