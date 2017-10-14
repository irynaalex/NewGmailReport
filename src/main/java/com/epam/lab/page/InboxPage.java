package com.epam.lab.page;

import com.epam.lab.util.WebDriverSingleton;
import com.epam.lab.element.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class InboxPage {

    //    @FindAll({@FindBy(css = "div.T-Jo-auh")})
    @FindBy(css = "div.T-Jo-auh")
    public List<ElementWeb> checkBoxes;

    @FindBy(xpath = "//*[@id=\":5\"]/div/div[1]/div[1]/div/div/div[2]/div[3]/div/div")
    private ElementWeb trashCan;

    @FindBy(xpath = "//*[@id=\"link_undo\"]")
    private ElementWeb undoButton;

    @FindBy(xpath = "/html/body/div[7]/div[3]/div/div[1]/div[5]/div[1]/div/div[3]/div/div/div[2]/span")
    private ElementWeb undoMessage;

    @FindBy(xpath = "//*[@id=\"gbq1\"]/div/a/span")
    private WebElement shortcutGoogle;


    public InboxPage() {
        WebDriver webDriver = WebDriverSingleton.getInstance();
        PageFactory.initElements(new CustomFieldDecorator(webDriver), this);
    }

    public void tickCheckBox(int numberOfCheckBox) {
        checkBoxes.get(numberOfCheckBox).toggle();
    }

    public void delete() {
        trashCan.click();
    }

    public void undoDelete() {
        undoButton.click();
    }

    public ElementWeb getUndoMessage() {
        return undoMessage;
    }

//    public ElementWeb getShortcutGoogle(){return shortcutGoogle;}
    public WebElement getShortcutGoogle(){return shortcutGoogle;}
}
