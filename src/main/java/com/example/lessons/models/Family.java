package com.example.lessons.models;

import java.util.ArrayList;
import java.util.List;

/*
Family Information Class
 */
public class Family {
    // Family Data

    public Person person;
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
        this.person = new Person();
        this.spouse = null;
        this.children = null;
    }

    /*
    Family constructor requires Individual
     */
    public Family(String name, Integer age) {
        this.person = new Person(name, age);
        this.spouse = null;
        this.children = null;
    }

    /*
    Set/Get primary individual to Family
     */
    public void setPerson (String name, Integer age) {
        person.setName(name); person.setAge(age);
    }
    public Person getPerson () { return person; }

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
                primaryKy, person.getName(), person.getAge()));

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
