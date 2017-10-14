package com.epam.lab.businessobject;


import com.epam.lab.page.InboxPage;

public class Transaction {
    InboxPage inboxPage = new InboxPage();

    public void tickCheckBoxesDeleteUndo(int quantity) {
        for (int i = 1; i <= quantity; i++) {
            inboxPage.tickCheckBox(i);
        }
        inboxPage.delete();
        inboxPage.undoDelete();
    }

    public boolean getUndoMessageOnInboxPage() {
        return inboxPage.getUndoMessage().isDisplayed();
    }

    public boolean getShortCutGoogle() {
        return inboxPage.getShortcutGoogle().isDisplayed();
    }


}
