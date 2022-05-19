package ru.job4j.serilization.java.serialization.json.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElement;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private boolean isLead;
    @XmlAttribute
    private int salary;
    private Profession profession;
    @XmlElementWrapper(name = "skills")
    @XmlElement(name = "skill")
    private String[] skills;

    public Employee() {
    }

    public Employee(String name, boolean isLead, int salary, Profession profession, String... skills) {
        this.name = name;
        this.isLead = isLead;
        this.salary = salary;
        this.profession = profession;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", isLead=" + isLead
                + ", salary=" + salary
                + ", profession=" + profession
                + ", skills=" + Arrays.toString(skills)
                + '}';
    }
}
