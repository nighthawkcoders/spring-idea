package com.example.lessons.modelsMongo;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class PersonMongo {
    @Id
    public String id;

    @NonNull
    public String name;

    @NonNull
    public int age;
}