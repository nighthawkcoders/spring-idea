package com.example.lessons.models;

import java.util.ArrayList;
import java.util.Date;
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
    static private final String personKy = "Person";
    static private final String spouseKy = "Spouse";
    static private final String childrenKy = "Children";

    /*
    Family constructor requires Individual
     */
    public Family() {
        this.person = new Person();
        this.spouse = new Person();
        this.children = new ArrayList<>();
    }

    /*
    Family constructor requires Individual
     */
    public Family(String name, Integer age) {
        this.person = new Person(name, age);
        this.spouse = new Person();
        this.children = new ArrayList<>();
    }

    /*
    Set/Get primary individual to Family
     */
    public void setPerson (String name, Integer age, Date dob) {
        if (person == null) { this.person = new Person(); }
        person.setName(name); person.setAge(age); person.setDob(dob);
    }
    public Person getPerson () { return person; }

    /*
    Add spouse to Family
     */
    public void setSpouse (String name, Integer age, Date dob) {
        if (spouse == null) { this.spouse = new Person(name, age); }
        else { spouse.setName(name); spouse.setAge(age); spouse.setDob(dob);}
    }
    public Person getSpouse () { return spouse; }


    /*
    Add child(ren) to Family
     */
    public void setChild (String name, Integer age, Date dob) {
        // initialization check
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        children.add(new Person(name, age, dob));
    }
    public List<Person> getChildren () { return children; }


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
        outString = new StringBuilder(String.format("%s: %s, %s, %s%n",
                personKy, person.getName(), person.getAge(), person.getDob()));

        // get spouse data
        if ( spouse != null)
            outString.append(String.format("%s: %s, %s, %s%n",
                    spouseKy, spouse.getName(), spouse.getAge(), spouse.getDob()));

        // get children data
        if (children != null) {
            outString.append(String.format("%s:%n", childrenKy));
            // System.out.println(family.get(childrenKy));
            for (Person child : children) {
                outString.append(String.format("%s: %s, %s, %s%n",
                        childrenKy, child.getName(), child.getAge(), child.getDob()));
            }
        }
        return outString.toString();
    }

}
