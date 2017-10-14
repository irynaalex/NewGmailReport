package com.epam.lab.util;

import com.epam.lab.model.Users;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLToObject {
    public Users convert() {
        try {
            File file = new File("src/main/resources/users.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Users users = (Users) jaxbUnmarshaller.unmarshal(file);
            return users;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}