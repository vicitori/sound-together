package com.vicitori.sound_together.service;

import com.vicitori.sound_together.model.Playlist;
import com.vicitori.sound_together.model.Track;
import com.vicitori.sound_together.model.Vote;
import com.vicitori.sound_together.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository repository;

    public Playlist createPlist(String name) {
        Playlist pl = new Playlist(name);
        return repository.save(pl);
    }

    public String getPlistLink(String name) {
        Playlist pl = repository.findByPlistName(name).orElseThrow(() -> new RuntimeException("playlist '" + name + "' not found :( try input other name."));
        return pl.getShareUrl();
    }

    public Boolean existsByShareCode(String shareCode) {
        return repository.existsByPlistShareCode(shareCode);
    }

    public Optional<Playlist> getPlistByShareCode(String shareCode) {
        return repository.findByPlistShareCode(shareCode);
    }

    public void sortPlaylistByRating(String shareCode) {
        Playlist playlist = getPlistByShareCode(shareCode).orElseThrow(() -> new RuntimeException("Playlist not found"));
        List<Track> sortedTracks = playlist.getTracks().stream().sorted((t1, t2) -> Double.compare(calculateAverageRating(t2), calculateAverageRating(t1))).collect(Collectors.toList());
        playlist.setTracks(sortedTracks);
        repository.save(playlist);
    }

    private double calculateAverageRating(Track track) {
        if (track.getVotes().isEmpty()) return 0.0;
        return track.getVotes().stream().mapToInt(Vote::getRate).average().orElse(0.0);
    }

    public List<Playlist> getAllPlaylists() {
        return repository.findAll();
    }
}
