package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {

    //Application mobile elements
    @AndroidFindBy (accessibility = "test-Username") private MobileElement usernameTxtField;
    @AndroidFindBy (accessibility = "test-Password") private MobileElement passwordTxtField;
    @AndroidFindBy (accessibility = "test-LOGIN") private MobileElement loginButton;
    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView") private MobileElement errorText;

    //Enter username function
    public LoginPage enterUsername(String username) {
        sendKeys(usernameTxtField, username);
        return this;
    }

    //Enter password function
    public LoginPage enterPassword(String password) {
        sendKeys(passwordTxtField, password);
        return this;
    }

    //Login to Products page
    public ProductsPage pressLoginButton() {
        click(loginButton);
        return new ProductsPage();
    }

    //Get error text field attribute
    public String getErrorText() {
        return getAttribute(errorText, "text");
    }
}
