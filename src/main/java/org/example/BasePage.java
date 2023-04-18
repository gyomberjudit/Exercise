package org.example;

import org.openqa.selenium.WebDriver;

public class BasePage {
    protected final WebDriver driver;
    private final String URL = "https://demo.seleniumeasy.com/table-pagination-demo.html";

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.navigate().to(URL);
    }

}
