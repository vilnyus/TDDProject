package com.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private AppiumDriver driver;
    public static AppiumDriverLocalService service;
    private Properties properties;
    InputStream inputStream;

    @BeforeClass
    public void startAppiumServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();

        builder.usingAnyFreePort();
        builder.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"));
        builder.withAppiumJS(new File("C:\\Users\\vagha\\AppData\\Roaming\\npm\\node_modules\\appium"));

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }
    @BeforeTest
    public void beforeTest() {
        try {
            properties = new Properties();
            String propertiesFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
            properties.load(inputStream);

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "9.0");
            capabilities.setCapability("deviceName", "emulator-5554");
            capabilities.setCapability("appPackage", properties.getProperty("androidAppPackage"));
            capabilities.setCapability("appActivity", properties.getProperty("androidAppActivity"));
            capabilities.setCapability("automationName", properties.getProperty("androidAutomationName"));
            String androidAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "app" + File.separator + "SauceLabs.apk";
//        capabilities.setCapability("app", androidAppUrl);
            driver = new AndroidDriver(service.getUrl(), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e){}

    }

    @AfterTest
    public void afterTest() {

    }
}
