package com.vicitori.sound_together.service;

import com.vicitori.sound_together.model.Playlist;
import com.vicitori.sound_together.model.Track;
import com.vicitori.sound_together.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {
    private PlaylistService plService;
    private TrackRepository repository;

    public TrackService(PlaylistService plService, TrackRepository repository) {
        this.plService = plService;
        this.repository = repository;
    }

    public Track addTrack(String name, String addedBy, String plistShareCode) {
        Playlist pl = plService.getPlistByShareCode(plistShareCode).orElseThrow(() -> new RuntimeException("playlist with share code " + plistShareCode + " not found. try to input share code again."));
        Track track = new Track(name, addedBy, pl);
        return repository.save(track);
    }

    public List<Track> getTracksFromPlaylist(String plistShareCode) {
        Playlist pl = plService.getPlistByShareCode(plistShareCode).orElseThrow(() -> new RuntimeException("playlist with share code " + plistShareCode + " not found. try to input share code again."));
        return pl.getTracks();
    }

//    public List<Track> getTracksSortedByRating(String plistShareCode) {
//        Playlist pl = plService.getPlistByShareCode(plistShareCode).orElseThrow(() -> new RuntimeException("playlist with share code " + plistShareCode + " not found. try to input share code again."));
//        List<Track> tracks = pl.getTracks();
//        for (Track track: tracks) {
//            track.get
//        }
//    }
}
