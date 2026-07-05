package com.playwright.pages;

import com.microsoft.playwright.Page;

public class PIMPage extends BasePage {

    // Lokatörler
    private final String addEmployeeButton = "text=Add Employee";
    private final String firstNameInput = "input[name='firstName']";
    private final String lastNameInput = "input[name='lastName']";
    private final String saveButton = "button[type='submit']";
    private final String successToast = "text=Successfully Saved";

    // Constructor
    public PIMPage(Page page) {
        super(page);
    }

    // PIMTest'in aradığı 1. metot
    public void addEmployee(String firstName, String lastName) {
        click(addEmployeeButton);
        fill(firstNameInput, firstName);
        fill(lastNameInput, lastName);
        click(saveButton);
    }

    // PIMTest'in aradığı 2. metot
    public boolean isSuccessMessageVisible() {
        waitForSelector(successToast);
        return isVisible(successToast);
    }
}