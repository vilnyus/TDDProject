package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeClass
    public void beforeClass() {

    }

    @AfterClass
    public void afterClass() {

    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        loginPage = new LoginPage();
        System.out.println("\n" + "******** ******** ******** starting method " + m.getName() + "******** ******** ********");
    }

//    @AfterMethod
//    public void afterClass() {
//
//    }

    @Test
    public void invalidUsernameTest() {
        loginPage.enterUsername("invalidUsername");
        loginPage.enterPassword("secret_sauce");
        loginPage.pressLoginButton();
        String actualErrText = loginPage.getErrorText();
        String expectedErrText = "Username and password do not match any user in this service.";
        System.out.println("actual error message is: " + actualErrText + "\n" + "expected error message is: " + expectedErrText);
        Assert.assertEquals(expectedErrText, actualErrText);
    }

    @Test
    public void invalidPasswordTest() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("invalidPassword");
        loginPage.pressLoginButton();
        String actualErrText = loginPage.getErrorText();
        String expectedErrText = "Username and password do not match any user in this service.";
        System.out.println("actual error message is: " + actualErrText + "\n" + "expected error message is: " + expectedErrText);
        Assert.assertEquals(expectedErrText, actualErrText);
    }

    @Test
    public void successfulnessTest() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        productsPage = loginPage.pressLoginButton();
        String actualProductsTitle = productsPage.getTitle();
        String expectedProductsTitle = "PRODUCTS";
        System.out.println("actual page title is: " + actualProductsTitle + "\n" + "expected page title is: " + expectedProductsTitle);
        Assert.assertEquals(expectedProductsTitle, actualProductsTitle);
    }

}
