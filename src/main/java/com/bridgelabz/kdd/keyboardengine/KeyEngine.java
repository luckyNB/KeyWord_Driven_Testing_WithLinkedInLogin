package com.bridgelabz.kdd.keyboardengine;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.bridgelabz.kdd.base.Base;

public class KeyEngine extends Base {
    public FileInputStream file;
    public String SCENERIOSHEET = "/home/lucky/IdeaProjects/KeyBordDrivenPractice/src/main/java/com/bridgelabz/kdd/repository/kdd.xlsx";
    public static Sheet sheet;
    public Workbook book;
    public WebElement element;

    public void startExecution(String sheetname) {
        try {
            file = new FileInputStream(SCENERIOSHEET);
            book = WorkbookFactory.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetname);
        int k = 0;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString();
            String locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString();
            String action = sheet.getRow(i + 1).getCell(k + 3).toString();
            String value = sheet.getRow(i + 1).getCell(k + 4).toString();
            switch (action) {
                case "open browser":
                    Base.openBrowser(value);
                    driver.manage().window().maximize();
                    driver.manage().deleteAllCookies();
                    driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
                    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                    break;
                case "enter url":
                    driver.get(value);
                    break;
                case "quit":
                    driver.quit();
                    break;
                default:
                    break;
            }
            switch (locatorType) {
                case "id":
                    element = driver.findElement(By.id(locatorValue));
                    if (action.equalsIgnoreCase("sendkeys")) {
                        element.clear();
                        element.sendKeys(value);
                    } else if (action.equalsIgnoreCase("click")) {
                        element.click();
                    }
                    locatorType = null;
                    break;

                case "linkText":
                    driver.findElement(By.linkText(locatorValue)).click();
                    locatorType = null;
                    break;

                case "xpath":
                    element = driver.findElement(By.xpath(locatorValue));
                    if (action.equalsIgnoreCase("sendkeys")) {
                        element.sendKeys(value);
                    } else if (action.equalsIgnoreCase("click")) {
                        element.click();
                    } else if (action.equalsIgnoreCase("getText")) {
                        System.out.println("Text is :" + element.getText());
                    }
                    break;

                case "cssSelector":
                    element = driver.findElement(By.cssSelector(locatorValue));
                    if (action.equalsIgnoreCase("sendkeys")) {
                        element.clear();
                        element.sendKeys(value);
                    } else if (action.equalsIgnoreCase("click")) {
                        element.click();
                    }
                    break;

                case "className":
                    element = driver.findElement(By.className(locatorValue));
                    if (action.equalsIgnoreCase("sendkeys")) {
                        element.clear();
                        element.sendKeys(value);
                    } else if (action.equalsIgnoreCase("click")) {
                        element.click();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
