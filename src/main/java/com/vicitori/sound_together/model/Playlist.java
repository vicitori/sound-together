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
    private Long plist_id;

    private String plist_name;
    private String plist_link;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
    private List<Track> tracks = new ArrayList<>();

    public Playlist(String name) {
        this.plist_name = name;
        this.plist_link = generateLink();
    }

    private String generateLink() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}