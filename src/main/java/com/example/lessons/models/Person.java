package com.example.lessons.models;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    /* Used to build JSON */
    static public String nameKy = "Name";
    static public String ageKy = "Age";
    static public String dobKy = "DOB";
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

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dob;

}