package com.vicitori.sound_together.service;

import com.vicitori.sound_together.model.AppUser;
import com.vicitori.sound_together.model.Track;
import com.vicitori.sound_together.model.Vote;
import com.vicitori.sound_together.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final TrackService trackService;
    private final VoteRepository repository;

    public void vote(int rate, Long trackId, AppUser user) {
        if (rate < 0 || rate > 10) {
            throw new IllegalArgumentException("rate should be from 0 to 10.");
        }
        Track track = trackService.getTrackById(trackId).orElseThrow(() -> new RuntimeException("track not found."));
        if (repository.existsByTrackAndAppUser(track, user)) {
            throw new RuntimeException("user already voted for this track.");
        }
        Vote vote = new Vote(rate, track, user);
        repository.save(vote);
    }

    public int getTrackRating(Long trackId) {
        Track track = trackService.getTrackById(trackId).orElseThrow(() -> new RuntimeException("can not find track with id " + trackId + ". try input other trackId."));
        List<Vote> votes = track.getVotes();
        int rating = 0;
        for (Vote vote : votes) {
            rating += vote.getRate();
        }
        return rating / votes.size();
    }
}
