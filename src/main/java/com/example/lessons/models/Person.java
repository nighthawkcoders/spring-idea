package com.example.lessons.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Person {
    /* Used to build JSON */
    static public String nameKy = "Name";
    static public String ageKy = "Age";
    static public String adoptKy = "Adopted";

    /*
    @NotNull: Does not allow a null value, which is what Spring MVC generates if the entry is empty.
    @Size(min=2, max=30): Allows names between 2 and 30 characters long.
     */
    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    // @Min(18): Does not allow the age to be less than 18.
    @NotNull
    @Min(16)
    private Integer age;

    public Person(String name, Integer age) {this.name = name; this.age = age;}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "Name: " + this.name + ", Age: " + this.age;
    }
}