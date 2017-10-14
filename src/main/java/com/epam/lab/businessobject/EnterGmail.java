package com.epam.lab.businessobject;


import com.epam.lab.model.User;
import com.epam.lab.page.GmailLoginPage;
import com.epam.lab.page.GmailPasswordPage;

public class EnterGmail {


    public void loginGmail(User user) {

        GmailLoginPage gmailLoginPage = new GmailLoginPage();
        gmailLoginPage.tipeLogin(user.getLogin());
        gmailLoginPage.clickNext();

        GmailPasswordPage gmailPasswordPage = new GmailPasswordPage();
        gmailPasswordPage.tipePasswordAndSubmit(user.getPassword());
        gmailPasswordPage.clickNext();
    }
}
