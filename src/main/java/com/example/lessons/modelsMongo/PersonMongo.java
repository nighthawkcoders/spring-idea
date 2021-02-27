package com.example.lessons.modelsMongo;

import lombok.*;
import org.springframework.data.annotation.Id;

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