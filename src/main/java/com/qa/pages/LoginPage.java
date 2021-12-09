package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {
    @AndroidFindBy (xpath = "//android.widget.EditText[@content-desc=\"test-Username\"]") MobileElement usernameTxtField;
    @AndroidFindBy (xpath = "//android.widget.EditText[@content-desc=\"test-Password\"]") MobileElement passwordTxtField;
    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]/android.widget.TextView") MobileElement loginButton;
    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView") MobileElement errorText;


    public LoginPage enterUsername(String usernameStr) {
        sendKeys(usernameTxtField, usernameStr);
        return this;
    }

    public LoginPage enterPassword(String passswordStr) {
        sendKeys(usernameTxtField, passswordStr);
        return this;
    }


    public ProductPage clickLoginButton(String passswordStr) {
        clickElement(loginButton);
        return new ProductPage;
    }

    public String getErrorText() {
        return getAttribute(errorText, "text");
    }
}
