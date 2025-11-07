package com.vicitori.sound_together.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackId;

    private String trackName;
    private String trackLink;
    private String addedBy;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL)
    private List<Vote> votes = new ArrayList<>();

    public Track(String name, String addedBy, Playlist playlist) {
        this.trackName = name;
        this.addedBy = addedBy;
        this.playlist = playlist;
        this.trackLink = null;
    }

    public Track(String name, String addedBy, Playlist playlist, String link) {
        this.trackName = name;
        this.addedBy = addedBy;
        this.playlist = playlist;
        this.trackLink = link;
    }
}
