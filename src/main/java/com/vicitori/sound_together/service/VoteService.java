package com.vicitori.sound_together.service;

import com.vicitori.sound_together.model.AppUser;
import com.vicitori.sound_together.model.Track;
import com.vicitori.sound_together.model.Vote;
import com.vicitori.sound_together.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {
    private TrackService trackService;
    private AppUserService userService;
    private VoteRepository repository;

    public Vote vote(int rate, Long trackId, Long userId) {
        if (rate < 0 || rate > 10) {
            throw new IllegalArgumentException("rate should be from 0 to 10");
        }
        Track track = trackService.getTrackById(trackId).orElseThrow(() -> new RuntimeException("can not find track with id " + trackId + ". try input other trackId."));
        AppUser user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("can not find user with id " + userId + ". try input other userId."));
        Vote vote = new Vote(rate, track, user);
        return repository.save(vote);
    }

    public int getTrackRating(Long trackId) {
        Track track = trackService.getTrackById(trackId).orElseThrow(() -> new RuntimeException("can not find track with id " + trackId + ". try input other trackId."));
        List<Vote> votes = track.getVotes();
        int rating = 0;
        for (Vote vote : votes) {
            rating += vote.getRate();
        }
        return rating;
    }
}
