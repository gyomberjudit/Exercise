package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PageTest {
    WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        //options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testGetNumbers() {
        TablePagination tablePagination = new TablePagination(driver);
        List<Integer> numberList = new ArrayList<>();
        tablePagination.navigate();
        do {
            tablePagination.getNumbers(numberList);
        } while (tablePagination.isArrowClickable());

        for (int i=0; i<numberList.size()-1; i++) {

            Assertions.assertEquals(numberList.get(i)+1, numberList.get(i+1));
        }
    }

    @Test
    public void testGetNumbers2() {
        TablePagination tablePagination = new TablePagination(driver);
        List<Integer> numberList = new ArrayList<>();      //lista a 13 számmal
        tablePagination.navigate();

        do {
            tablePagination.getNumbers(numberList);
            if (!tablePagination.isArrowClickable2()) {
                break;
            }
            tablePagination.clickArrow();

        } while (true);

        //0.inexű elem = 1, 1.indexű elem = 2, 2.indexű elem = 3,
        for (int i=0; i<numberList.size()-1; i++) {      //megnézni, hogy az első indexű 1-gyel nagyobb-e, mint a 0. indexű, a size-nál 1-gyel kisebb a méret!!

            Assertions.assertEquals(numberList.get(i)+1, numberList.get(i+1));
        }

    }
}
