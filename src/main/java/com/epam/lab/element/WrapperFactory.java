package com.epam.lab.element;

import org.openqa.selenium.WebElement;

public class WrapperFactory {

    /**
     * Creates an instance of the class,
     * which implements the IElement interface,
     * calling the constructor with the WebElement argument
     */
    public static IElement createInstance(Class<IElement> clazz, WebElement element) {
        try {
            return clazz.getConstructor(WebElement.class).
                    newInstance(element);
        } catch (Exception e) {
            throw new AssertionError("WebElement can't be represented as " + clazz);
        }
    }
}
