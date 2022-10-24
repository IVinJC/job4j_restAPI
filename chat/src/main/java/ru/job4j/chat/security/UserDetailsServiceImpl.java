package ru.job4j.chat.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.chat.dto.PersonDTO;
import ru.job4j.chat.services.PersonService;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonService personService;

    public UserDetailsServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PersonDTO user = personService.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getName(), user.getPassword(), emptyList());
    }
}