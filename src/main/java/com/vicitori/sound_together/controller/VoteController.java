package com.vicitori.sound_together.controller;

import com.vicitori.sound_together.service.PlaylistService;
import com.vicitori.sound_together.service.VoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VoteController {
    private final VoteService service;

    public VoteController(VoteService voteService, PlaylistService playlistService) {
        this.service = voteService;
    }

    @PostMapping("/playlists/{plistShareCode}/tracks/{trackId}/votes")
    public String voteForTrack(@PathVariable String plistShareCode, @PathVariable Long trackId, @RequestParam int rate, @RequestParam Long userId) {
        service.vote(rate, trackId, userId);
        return "redirect:/playlists/" + plistShareCode;
    }
}
