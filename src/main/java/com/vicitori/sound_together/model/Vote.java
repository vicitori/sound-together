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
    private Long voteID;

    private int rate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser app_user;

    Vote(int rate) {
        this.rate = rate;
    }
}
