package com.epam.lab.page;

import com.epam.lab.util.WebDriverSingleton;
import com.epam.lab.element.CustomFieldDecorator;
import com.epam.lab.element.ElementWeb;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailPasswordPage {

    @FindBy(xpath = "//*[@id=\"password\"]/div[1]/div/div[1]/input")
//    @FindBy(id = "password")
    private ElementWeb passwordInput;

    @FindBy(xpath = "//*[text()='Forgot password?']")
    private ElementWeb forgotPasswordLink;

//        @FindBy(xpath = "//*[@id=\"passwordNext\"]/content/span")
    @FindBy(id = "passwordNext")
    private ElementWeb nextButton;

    public GmailPasswordPage() {
        WebDriver webDriver = WebDriverSingleton.getInstance();
        PageFactory.initElements(new CustomFieldDecorator(webDriver), this);
    }

    public void tipePasswordAndSubmit(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickNext() {
        nextButton.click();
    }
}
