package ru.job4j.serilization.java.serialization.json.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profession")
public class Profession {
    @XmlAttribute
    private String name;

    public Profession() {
    }

    public Profession(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Profession{"
                + "name='" + name + '\''
                + '}';
    }
}
