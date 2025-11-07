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
    private Long track_id;

    private String track_name;
    private String track_link;
    private String added_by;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    Track(String name) {
        this.track_name = name;
        this.track_link = null;
    }

    Track(String name, String link) {
        this.track_name = name;
        this.track_link = link;
    }
}
