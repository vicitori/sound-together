package com.vicitori.sound_together.service;

import com.vicitori.sound_together.model.AppUser;
import com.vicitori.sound_together.model.Track;
import com.vicitori.sound_together.model.Vote;
import com.vicitori.sound_together.repository.VoteRepository;

import java.util.List;

public class VoteService {
    private TrackService trackService;
    private AppUserService userService;
    private VoteRepository repository;

    public Vote vote(int rate, Long trackId, Long userId) {
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
