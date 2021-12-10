package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {
    @AndroidFindBy (xpath = "//android.widget.EditText[@content-desc=\"test-Username\"]") private MobileElement usernameTxtField;
    @AndroidFindBy (xpath = "//android.widget.EditText[@content-desc=\"test-Password\"]") private MobileElement passwordTxtField;
    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]/android.widget.TextView") private MobileElement loginButton;
    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView") private MobileElement errorText;


    public LoginPage enterUsername(String username) {
        sendKeys(usernameTxtField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(usernameTxtField, password);
        return this;
    }


    public ProductsPage pressLoginButton() {
        click(loginButton);
        return new ProductsPage();
    }

    public String getErrorText() {
        return getAttribute(errorText, "text");
    }
}
