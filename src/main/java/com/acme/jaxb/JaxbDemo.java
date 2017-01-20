package com.acme.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

public class JaxbDemo {
    public static void main(String[] args) {
        User userOut = new User(1, "Petya");

        try {
            File file = new File("target/petya.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(userOut, file);
            jaxbMarshaller.marshal(userOut, System.out);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            User userIn = (User) jaxbUnmarshaller.unmarshal(file);
            System.out.println(userIn);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

@XmlRootElement
class User {
    private int id;
    private String name;

    public User() {}
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
