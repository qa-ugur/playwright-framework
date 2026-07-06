package com.playwright.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

public class PIMPage extends BasePage {

    // --- ESKİ STRING LOCATOR'LAR (Aynen koruyoruz, eski testler kırılmasın) ---
    private final String addEmployeeButton = "text=Add Employee";
    private final String firstNameInput = "input[name='firstName']";
    private final String lastNameInput = "input[name='lastName']";
    private final String saveButton = "button[type='submit']";
    private final String successToast = "text=Successfully Saved";

    // --- YENİ NESİL LOCATOR TANIMLAMALARI ---
    private final Locator employeeListTab;
    private final Locator employeeNameInput;
    private final Locator searchButton;

    public PIMPage(Page page) {
        super(page);

        // 1. Üst menüdeki "Employee List" sekmesini bulur (Link rolünde)
        this.employeeListTab = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Employee List"));

        // 2. Arama kutusunu bulur (Genelde içinde 'Type for hints...' yazar, placeholder ile yakalamak en kararlısıdır)
        this.employeeNameInput = page.getByPlaceholder("Type for hints...").first();

        // 3. Search butonunu bulur (Buton rolünde ve adı "Search" olan)
        this.searchButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search"));
    }

    // --- MEVCUT SÜREÇ METOTLARINIZ (Aynen duruyor) ---
    public void navigateToAddEmployee() {
        click(addEmployeeButton);
    }

    public void addEmployee(String firstName, String lastName) {
        fill(firstNameInput, firstName);
        fill(lastNameInput, lastName);
        click(saveButton);
    }

    public boolean isSuccessToastVisible() {
        waitForSelector(successToast);
        return isVisible(successToast);
    }

    // --- YENİ SENARYO İÇİN AKSİYON METOTLARI ---

    // Employee List sekmesine tıklar
    public void clickEmployeeListTab() {
        click(employeeListTab);
    }

    // İsme göre arama yapar
    public void searchEmployee(String employeeName) {
        fill(employeeNameInput, employeeName);
        click(searchButton);
    }
}