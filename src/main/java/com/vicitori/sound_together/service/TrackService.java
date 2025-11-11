package com.vicitori.sound_together.service;

import com.vicitori.sound_together.model.Playlist;
import com.vicitori.sound_together.model.Track;
import com.vicitori.sound_together.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrackService {
    private final PlaylistService plService;
    private final VoteService voteService;
    private final TrackRepository repository;

    public Optional<Track> getTrackById(Long id) {
        return repository.findById(id);
    }

    public void addTrack(String name, String addedBy, String plistShareCode) {
        Playlist pl = plService.getPlistByShareCode(plistShareCode).orElseThrow(() -> new RuntimeException("playlist with share code " + plistShareCode + " not found. try to input share code again."));
        Track track = new Track(name, addedBy, pl);
        repository.save(track);
    }

    public void addTrackWithLink(String name, String addedBy, String plistShareCode, String link) {
        Playlist pl = plService.getPlistByShareCode(plistShareCode).orElseThrow(() -> new RuntimeException("playlist with share code " + plistShareCode + " not found. try to input share code again."));
        Track track = new Track(name, addedBy, pl, link);
        repository.save(track);
    }

    public List<Track> getTracksFromPlaylist(String plistShareCode) {
        Playlist pl = plService.getPlistByShareCode(plistShareCode).orElseThrow(() -> new RuntimeException("playlist with share code " + plistShareCode + " not found. try to input share code again."));
        return pl.getTracks();
    }

    public List<Track> getTracksSortedByRating(String plistShareCode) {
        Playlist pl = plService.getPlistByShareCode(plistShareCode).orElseThrow(() -> new RuntimeException("playlist with share code " + plistShareCode + " not found. try to input share code again."));
        List<Track> tracks = pl.getTracks();
        return tracks.stream().sorted(Comparator.comparingInt((Track track) -> voteService.getTrackRating(track.getTrackId())).reversed()).collect(Collectors.toList());
    }
}
