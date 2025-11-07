package com.vicitori.sound_together.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackID;

    private String trackName;
    private String trackLink;
    private String addedBy;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    Track(String name) {
        this.trackName = name;
        this.trackLink = null;
    }

    Track(String name, String link) {
        this.trackName = name;
        this.trackLink = link;
    }
}
