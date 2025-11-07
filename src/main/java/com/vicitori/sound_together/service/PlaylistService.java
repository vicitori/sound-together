package com.vicitori.sound_together.service;

import com.vicitori.sound_together.model.Playlist;
import com.vicitori.sound_together.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaylistService {
    private final PlaylistRepository repository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.repository = playlistRepository;
    }

    public Playlist createPlist(String name) {
        Playlist pl = new Playlist(name);
        return repository.save(pl);
    }

    public String getPlistLink(String name) {
        Playlist pl = repository.findByPlstName(name).orElseThrow(() -> new RuntimeException("playlist '" + name + "' not found :( try input other name."));
        return pl.getShareUrl();
    }

    public Boolean existsByShareCode(String shareCode) {
        return repository.existsByPlstShareCode(shareCode);
    }

    public Optional<Playlist> getPlistByShareCode(String shareCode) {
        return repository.findByPlstShareCode(shareCode);
    }
}
