package com.epam.lab.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    private WebDriverSingleton() {
    }

    public static WebDriver getInstance() {

        if (webDriverThreadLocal.get() != null) {

            return webDriverThreadLocal.get();
        }

        WebDriver instance;
        String driverName = new PropertiesReader().getDriverName();
        String driverLocation = new PropertiesReader().getDriverLocation();
        System.setProperty(driverName, driverLocation);
        instance = new ChromeDriver() {
            {
                manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
            }
        };

        webDriverThreadLocal.set(instance);

        return webDriverThreadLocal.get();
    }

    public static void close() {
        try {
            if (webDriverThreadLocal != null) {
                webDriverThreadLocal.get().quit();
            }
        }catch (Exception e){
            System.err.println("ERROR: Can not close WebDriver!");
        }finally {
            webDriverThreadLocal.remove();
        }

    }
}


