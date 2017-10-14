package com.epam.lab.element;

import org.openqa.selenium.WebElement;

public class ElementWeb implements IElement {
    protected WebElement webElement;

    public ElementWeb(WebElement webElement) {
        this.webElement = webElement;
    }

    public void click() {
        webElement.click();
    }

    public void sendKeys(CharSequence... keysToSend) {
        webElement.sendKeys(keysToSend);
    }

    public WebElement getWrappedElement() {
        return webElement;
    }

    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    public void toggle() {
        getWrappedElement().click();
    }

}