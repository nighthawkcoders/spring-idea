package com.example.lessons.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/*
Family Information Class
 */
@Setter
@Getter
public class Family {
    // Keys Used in HashMaps
    static public final String personKy = "Person";
    static public final String spouseKy = "Spouse";
    static public final String childrenKy = "Children";

    // Family Data
    HashMap<Object, Object> family;
    public Person person;
    public Person spouse;
    public List<Person> children;

    /*
    Family constructor requires Individual
     */
    public Family() {
        this.person = new Person();
        this.spouse = new Person();
        this.children = new ArrayList<>();
        this.family = kvPairsToMap(personKy, this.person, spouseKy, this.spouse, childrenKy, this.children);
    }

    /*
    Family constructor requires Individual
     */
    public Family(String name, Integer age) {
        this.person = new Person(name, age);
        this.spouse = new Person();
        this.children = new ArrayList<>();
        this.family = kvPairsToMap(personKy, this.person, spouseKy, this.spouse, childrenKy, this.children);
    }

    /*
    Set/Get primary individual to Family
     */
    public void setPerson (String name, Integer age, Date dob) {
        if (person == null) { this.person = new Person(); }
        person.setName(name); person.setAge(age); person.setDob(dob);
    }

    /*
    Add spouse to Family
     */
    public void setSpouse (String name, Integer age, Date dob) {
        if (spouse == null) { this.spouse = new Person(name, age); }
        else { spouse.setName(name); spouse.setAge(age); spouse.setDob(dob);}
    }

    public void addChild (String name, Integer age, Date dob) {
        // initialization check
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        children.add(new Person(name, age, dob));
    }

    /*
    Multiple key,value HashMap helper function
     */
    public static HashMap<Object, Object> kvPairsToMap(Object...args) {
        HashMap<Object, Object> map = new HashMap<>();
        for (int i=0; i<args.length; i+=2) {
            map.put(args[i], args[i+1]);
        }
        return map;
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
