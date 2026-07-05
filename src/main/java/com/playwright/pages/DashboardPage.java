package com.playwright.pages;

import com.microsoft.playwright.Page;

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
}