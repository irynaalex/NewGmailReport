package com.epam.lab;

import com.epam.lab.businessobject.EnterGmail;
import com.epam.lab.businessobject.Transaction;
import com.epam.lab.model.User;
import com.epam.lab.model.Users;
import com.epam.lab.util.WebDriverSingleton;
import com.epam.lab.util.XMLToObject;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners
public class LoginTest {
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
    }

//        @Test(description = "This a new test",dataProvider = "data", timeOut = 200000, priority = -200)
        @Test(dataProvider = "data")
        public void verifyLogin(int rowNumber, User user){
            new EnterGmail().loginGmail(user);
            assertTrue(new Transaction().getShortCutGoogle());

        }

        @Test(enabled = false)
        public void skipTest(){
                int x = 2+2;
                assertEquals(4, x);
        }

        @Test
        public void someFailedTest(){
                int x = 2+2;
                assertEquals(5, x);
        }

        @AfterMethod
        public void close() {
            WebDriverSingleton.close();
        }
}
