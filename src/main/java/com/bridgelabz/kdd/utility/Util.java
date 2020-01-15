package com.bridgelabz.kdd.utility;

import org.openqa.selenium.JavascriptExecutor;

public class Util {
    public static JavascriptExecutor jse;

    public static void scroll(int length) {
        jse.executeScript("executeScript(window.scrollBy(0," + length + ")");
    }
}
