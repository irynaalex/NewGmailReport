package com.epam.lab;

import com.epam.lab.businessobject.EnterGmail;
import com.epam.lab.businessobject.Transaction;
import com.epam.lab.model.User;
import com.epam.lab.model.Users;
import com.epam.lab.page.InboxPage;
import com.epam.lab.util.WebDriverSingleton;
import com.epam.lab.util.XMLToObject;
import org.apache.log4j.Logger;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.assertTrue;
@Listeners
public class UITest {
//    final static Logger logger = Logger.getLogger(UITest.class);
    final int QUANTITY_OF_CHECKBOX = 3;

    @DataProvider(name = "data", parallel = true)
    public static Object[][] getData() {
        XMLToObject xmlToObject = new XMLToObject();
        Users users = xmlToObject.convert();
        List<User> userList = users.getUsers();
        int rowAmount = userList.size();
        int columnAmount = 2;
        Object[][] userTable = new Object[rowAmount][columnAmount];
        for (int i = 0; i < userList.size(); i++) {
            Object[] columnOfTable = userTable[i];
            columnOfTable[0] = i + 1;
            columnOfTable[1] = userList.get(i);
        }
        return userTable;

//        return new Object[][]{
//          {"1", userList.get(0)},
//          {"2", userList.get(1)},
//          {"3", userList.get(2)}
//      };
    }

    @Test(dataProvider = "data")
    public void verifyUndo(int rowNumber, User user) {
        new EnterGmail().loginGmail(user);
        Transaction transaction = new Transaction();
        transaction.tickCheckBoxesDeleteUndo(QUANTITY_OF_CHECKBOX);
        assertTrue(transaction.getUndoMessageOnInboxPage());
    }

    @AfterMethod
    public void close1() {
        WebDriverSingleton.close();
    }


}