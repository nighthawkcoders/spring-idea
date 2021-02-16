package com.example.lessons.models;

// Building HashMap
import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
// Working with JSON
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
Family Information Class
 */
public class Family {
    // Family Data

    public Person primary;
    public Person spouse;
    public List<Person> children;

    // Keys Used in HashMaps
    static private final String primaryKy = "Primary";
    static private final String spouseKy = "Spouse";
    static private final String childrenKy = "Children";

    /*
    Family constructor requires Individual
     */
    public Family() {
        this.primary = new Person();
        this.spouse = null;
        this.children = null;
    }

    /*
    Family constructor requires Individual
     */
    public Family(String name, Integer age) {
        this.primary = new Person(name, age);
        this.spouse = null;
        this.children = null;
    }

    /*
    Set/Get primary individual to Family
     */
    public void setPrimary (String name, Integer age) {
        primary.setName(name); primary.setAge(age);
    }
    public Person getPrimary () { return primary; }

    /*
    Add spouse to Family
     */
    public void setSpouse (String name, Integer age) {
        if (spouse == null) { this.spouse = new Person(name, age); }
        else { spouse.setName(name); spouse.setAge(age); }
    }

    /*
    Add child(ren) to Family
     */
    public void addChild (String name, Integer age) {
        // initialization check
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        children.add(new Person(name, age));
    }

    /*
    Print Family object
     */
    public void print(String msg) {
        System.out.println(msg);
        System.out.println(this);               // use toString method
        System.out.println("=".repeat(50));
    }

    /*
    Test Print family members
     */
    public String toString() {
        StringBuilder outString;

        // get individual datq
        outString = new StringBuilder(String.format("%s: %s, %s%n",
                primaryKy, primary.getName(), primary.getAge()));

        // get spouse data
        if ( spouse != null)
            outString.append(String.format("%s: %s, %s%n",
                    spouseKy, spouse.getName(), spouse.getAge()));

        // get children data
        if (children != null) {
            outString.append(String.format("%s:%n", childrenKy));
            // System.out.println(family.get(childrenKy));
            for (Person child : children) {
                outString.append(String.format("%s: %s, %s%n",
                        childrenKy, child.getName(), child.getAge()));
            }
        }
        return outString.toString();
    }

}
