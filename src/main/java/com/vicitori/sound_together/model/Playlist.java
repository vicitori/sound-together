package com.vicitori.sound_together.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plistId;

    private String plistName;
    private String plistShareCode;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
    private List<Track> tracks = new ArrayList<>();

    public Playlist(String name) {
        this.plistName = name;
        this.plistShareCode = generateShareCode();
    }

    public String getShareUrl() {
        return "http://localhost:8080/playlist/" + this.plistShareCode;
    }

    private String generateShareCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
