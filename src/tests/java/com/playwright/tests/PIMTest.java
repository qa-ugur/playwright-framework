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

        // --- YENİ EKLEME: Benzersiz (Unique) İsim Üretme ---
        // Java ile o anki zamanın son 5 hanesini alıyoruz (Örn: 56789)
        String uniqueId = String.valueOf(System.currentTimeMillis());
        uniqueId = uniqueId.substring(uniqueId.length() - 5);

        // Her çalıştığında "Max56789" ve "Mustermann56789" gibi benzersiz isimler oluşacak
        String dynamicFirstName = "Max" + uniqueId;
        String dynamicLastName = "Mustermann" + uniqueId;
        // ---------------------------------------------------

        // 3. Yeni Çalışan Ekle (Dinamik isimleri gönderiyoruz)
        pimPage.navigateToAddEmployee();
        pimPage.addEmployee(dynamicFirstName, dynamicLastName);

        // 4. Doğrulama (Assertion): Kayıt başarılı oldu bildirimi geldi mi?
        Assertions.assertTrue(pimPage.isSuccessToastVisible(), "Çalışan eklenirken hata oluştu veya bildirim görünmedi!");

        // Gözümüzle görmek için 3 saniye bekletelim
        page.waitForTimeout(3000);
    }

    @Test
    public void testSearchEmployee() {
    // 1. Giriş Yap
    page.navigate(ConfigReader.get("baseUrl"));
    LoginPage loginPage = new LoginPage(page);
    loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));

     // 2. Dashboard Sayfasından PIM Menüsüne Geç
     DashboardPage dashboardPage = new DashboardPage(page);
     PIMPage pimPage = dashboardPage.clickPIMMenu();

        // 3. Employee List sekmesine tıkla (Metodu çağırıyoruz, tırnak içinde değişken değil!)
        pimPage.clickEmployeeListTab();

        // 4. Şimdi arama yapalım
        pimPage.searchEmployee("Max"); // Aramak istediğin ismi buraya yazabilirsin

        // 5. Gözümüzle görmek için 3 saniye beklet
        page.waitForTimeout(3000);
    }
}