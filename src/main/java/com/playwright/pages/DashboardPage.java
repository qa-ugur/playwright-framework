package com.playwright.pages;

import com.microsoft.playwright.Page;
import com.playwright.utils.Menu;
import com.microsoft.playwright.Locator;

public class DashboardPage extends BasePage {

    // Sol menüdeki PIM butonunun lokatörü
    private final String pimMenuButton = "text=PIM";

    public DashboardPage(Page page) {
        super(page);
    }

    // PIM menüsüne tıklar ve PIMPage nesnesi döner
    public PIMPage clickPIMMenu() {
        click(pimMenuButton);
        return new PIMPage(page); // Zincirleme (Fluent) tasarım deseni
    }

    // Dinamik metot: Menü adını dışarıdan alıyoruz
    public void navigateToMenu(Menu menu) {
        // Sadece sol menüdeki (main-menu) öğeleri seçerek "strict mode"u aşarız
        page.locator(".oxd-main-menu").getByText(menu.getMenuName(), new Locator.GetByTextOptions().setExact(true)).click();
    }
}