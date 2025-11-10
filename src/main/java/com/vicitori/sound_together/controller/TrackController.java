package com.vicitori.sound_together.controller;

import com.vicitori.sound_together.service.TrackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TrackController {
    private final TrackService service;

    public TrackController(TrackService service) {
        this.service = service;
    }

    @PostMapping("/playlists/{plistShareCode}/tracks")
    public String addTrack(@PathVariable String plistShareCode, @RequestParam String name, @RequestParam String addedBy) {
        service.addTrack(name, addedBy, plistShareCode);
        return "redirect:/playlists/" + plistShareCode;
    }
}
