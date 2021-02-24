package com.example.lessons.models;

// https://projectlombok.org/features/all
import lombok.*;
// Conventional Java data types
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
Family Information Class
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Family {
    // Family Data

    @Valid  // validation of a child object
    public Person person;

    @Valid public Person spouse;

    public List<Person> children;

    public void addChild (String name, Integer age) {
        // initialization check
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        children.add(new Person( name, age));
    }

    /*
    Test Pretty Print of family members
    */
    public String prettyPrint() {
        StringBuilder outString;

        // get individual datq
        outString = new StringBuilder(String.format("Person: %s, %s%n",
                person.getName(), person.getAge()));

        // get spouse data
        if ( spouse != null)
            outString.append(String.format("Spouse: %s, %s%n",
                    spouse.getName(), spouse.getAge()));

        // get children data
        if (children != null) {
            // System.out.println(family.get(childrenKy));
            for (Person child : children) {
                outString.append(String.format("Child: %s, %s%n",
                        child.getName(), child.getAge()));
            }
        }
        return outString.toString();
    }
}
