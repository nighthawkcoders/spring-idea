package com.example.lessons.models;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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