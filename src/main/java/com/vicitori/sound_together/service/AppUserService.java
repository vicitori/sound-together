package com.vicitori.sound_together.service;

import com.vicitori.sound_together.model.AppUser;
import com.vicitori.sound_together.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository repository;

    public Optional<AppUser> getUserById(Long id) {
        return repository.findById(id);
    }

    public Optional<AppUser> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    public AppUser createUser(String userName) {
        AppUser user = new AppUser(userName);
        return repository.save(user);
    }
}
