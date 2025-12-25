package com.example.bankcards.service.persons;

import com.example.bankcards.dto.persons.PersonDTO;
import com.example.bankcards.entity.persons.Person;
import com.example.bankcards.repository.persons.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void save(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setRole("ROLE_USER");
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }

    @Transactional
    public void delete(String name) {
        personRepository.deleteByName(name);
    }
}