package com.vicitori.sound_together.controller;

import com.vicitori.sound_together.model.AppUser;
import com.vicitori.sound_together.service.AppUserService;
import com.vicitori.sound_together.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class VoteController {
    private final VoteService service;
    private final AppUserService userService;

    @PostMapping("/playlists/{plistShareCode}/tracks/{trackId}/votes")
    public String voteForTrack(@PathVariable String plistShareCode, @PathVariable Long trackId, @RequestParam String userName, @RequestParam int rate) {
        AppUser user = userService.findByUserName(userName).orElseGet(() -> userService.createUser(userName));
        service.vote(rate, trackId, user);
        return "redirect:/playlists/" + plistShareCode;
    }
}
