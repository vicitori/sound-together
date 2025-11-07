package com.vicitori.sound_together.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    private int rate;

    @ManyToOne
    @JoinColumn(name = "track_id")
    private Track track;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    public Vote(int rate, Track track, AppUser user) {
        this.rate = rate;
        this.track = track;
        this.appUser = user;
    }
}
