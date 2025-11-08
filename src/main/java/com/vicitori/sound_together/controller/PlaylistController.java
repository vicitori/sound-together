package com.vicitori.sound_together.controller;

import com.vicitori.sound_together.model.Playlist;
import com.vicitori.sound_together.service.PlaylistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlaylistController {
    private PlaylistService service;

    public PlaylistController(PlaylistService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    @GetMapping("/playlists")
    public String showPlaylists(Model model) {
        model.addAttribute("playlists", service.getAllPlaylists());
        return "playlists";
    }

    @PostMapping("/playlists")
    public String createPlaylist(@RequestParam String name) {
        Playlist pl = service.createPlist(name);
        return "redirect:/playlists/" + pl.getPlistShareCode();
    }

    @GetMapping("/playlists/{plistShareCode}")
    public String showPlaylist(@PathVariable String plistShareCode, Model model) {
        Playlist pl = service.getPlistByShareCode(plistShareCode).orElseThrow(() -> new RuntimeException("playlist with share code " + plistShareCode + " not found. try to input share code again."));
        model.addAttribute("playlist", pl);
        return "playlist";
    }
}
