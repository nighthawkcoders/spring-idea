package com.example.lessons.modelsMongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonMongoRepository extends MongoRepository<PersonMongo, String> {

    List<PersonMongo> findByName(String name);
}