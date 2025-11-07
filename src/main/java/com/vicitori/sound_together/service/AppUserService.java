package com.vicitori.sound_together.service;

import com.vicitori.sound_together.model.AppUser;
import com.vicitori.sound_together.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {
    private AppUserRepository repository;

    public Optional<AppUser> getUserById(Long id) {
        return repository.findById(id);
    }

    public AppUser createUser(String name) {
        AppUser user = new AppUser(name);
        return repository.save(user);
    }
}
