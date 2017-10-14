package com.epam.lab.page;

import com.epam.lab.util.PropertiesReader;
import com.epam.lab.util.WebDriverSingleton;
import com.epam.lab.element.CustomFieldDecorator;
import com.epam.lab.element.ElementWeb;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailLoginPage {

    @FindBy(id = "identifierId")
    private ElementWeb loginInput;

    @FindBy(xpath = "//*[text()='Forgot email?']")
    private ElementWeb forgotEmailLink;

    @FindBy(xpath = "//*[text()='More options']")
    private ElementWeb moreOptionsLink;

    @FindBy(id = "identifierNext")
    private ElementWeb nextButton;

    public GmailLoginPage() {
        WebDriver webDriver = WebDriverSingleton.getInstance();

        PageFactory.initElements(new CustomFieldDecorator(webDriver), this);
        String url = new PropertiesReader().getUrl();
        webDriver.get(url);
    }

    public void tipeLogin(String login) {
        loginInput.sendKeys(login);
    }

    public void clickNext() {
        nextButton.click();
    }
}
