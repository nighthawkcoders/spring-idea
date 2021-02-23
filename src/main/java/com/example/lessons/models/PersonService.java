package com.example.lessons.models;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/*
This class is an instance of PersonRepository
-- @Autowired annotation. Allows Spring to resolve and inject collaborating beans into our bean.
-- Spring Data JPA will generate a proxy instance of ProductRepository
-- Look at all the CRUD methods that we can use with our database
*/
@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository prep;

    public List<Person> listAll() {
        return prep.findAll();
    }

    public void save(Person person) {
        prep.save(person);
    }

    public Person get(long id) {
        return prep.findById(id).get();
    }

    public void delete(long id) {
        prep.deleteById(id);
    }
}