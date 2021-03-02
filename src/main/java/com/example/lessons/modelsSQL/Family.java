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

    @ManyToOne
    @JoinColumn(name = "primary_id")
    public Person primary;

    @ManyToOne
    @JoinColumn(name = "spouse_id")
    public Person spouse;
}
