package com.example.lessons.modelsSQL;

// https://projectlombok.org/features/all
import lombok.*;
// Conventional Java data types
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/*
Family Information Class
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    /* In JPA the main difference between a OneToOne and a ManyToOne relationship ...
     a ManyToOne always contains a foreign key from the source object's table
     a OneToOne relationship the foreign key may either be in the source object's table or the target object's table.
     */
    @ManyToOne
    @JoinColumn(name = "primary_id")    //foreign key is stored in table
    public Person primary;

    @ManyToOne
    @JoinColumn(name = "spouse_id")
    public Person spouse;
}
