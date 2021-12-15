package com.qa;

import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {
    protected static AppiumDriver driver;
    protected static AppiumDriverLocalService service;
    protected static Properties properties;
    InputStream inputStream;

    public BaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

//    @BeforeClass
    public void startAppiumServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();

        builder.usingAnyFreePort();
        builder.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"));
        builder.withAppiumJS(new File("C:\\Users\\vagha\\AppData\\Roaming\\npm\\node_modules\\appium"));

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    @Parameters({"platformName", "platformVersion", "deviceName"})
    @BeforeTest
    public void beforeTest(String platformName, String platformVersion, String deviceName) throws IOException {
        startAppiumServer();

        try {
            properties = new Properties();
            String propertiesFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
            properties.load(inputStream);

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", platformName);
            capabilities.setCapability("platformVersion", platformVersion);
            capabilities.setCapability("deviceName", deviceName);
            capabilities.setCapability("appPackage", properties.getProperty("androidAppPackage"));
            capabilities.setCapability("appActivity", properties.getProperty("androidAppActivity"));
            capabilities.setCapability("automationName", properties.getProperty("androidAutomationName"));
            String androidAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "app" + File.separator + "SauceLabs.apk";
//            capabilities.setCapability("app", androidAppUrl);
//            String urlApp = service.getUrl().toString();
            driver = new AndroidDriver(service.getUrl(), capabilities);
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    public void waitForVisibility(MobileElement e) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void click(MobileElement e) {
        waitForVisibility(e);
        e.click();
    }

    public void sendKeys(MobileElement e, String textStr) {
        waitForVisibility(e);
        e.sendKeys(textStr);
    }

    public String getAttribute(MobileElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();

    }
}
