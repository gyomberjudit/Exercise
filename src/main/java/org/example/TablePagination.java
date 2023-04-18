package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TablePagination extends BasePage{

    private final By ROWS = By.xpath("//*[@id=\"myTable\"]/tr[@style=\"display: table-row;\"]");   //csak a láthatóak!!
    private final By NEXT_ARROW = By.xpath("//*[@class=\"next_link\"]");

    public TablePagination(WebDriver driver) {
        super(driver);
    }

    public void getRecords() {
        List<WebElement> rows = driver.findElements(ROWS);
    }
    public void clickArrow() {
        driver.findElement(NEXT_ARROW).click();
    }
    public boolean isArrowClickable() {
        WebElement arrow = driver.findElement(NEXT_ARROW);
        String style = arrow.getAttribute("style");
        if(!style.equals("display: none;")) {
            arrow.click();
            return true;
        }
        return false;
    }

    public boolean isArrowClickable2() {
        WebElement arrow = driver.findElement(NEXT_ARROW);
        String style = arrow.getAttribute("style");
        return !style.equals("display: none;");    //nem az, az elejére kerül
    }

    public void getNumbers(List<Integer> numberList) {     //lista paraméterként, majd a tesztben megadom a listát, így lehet void a fv
        List<WebElement> rows = driver.findElements(ROWS);

        for (WebElement row : rows) {
            WebElement td = row.findElement(By.xpath("./td[1]"));        //az első oszlop kell, td
            String text = td.getText();
            Integer value = Integer.parseInt(text);             //számmá alakítjuk a szöveget
            numberList.add(value);                              //betesszük a listába a számot
        }
    }
}
