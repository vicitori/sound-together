package com.vicitori.sound_together.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String user_name;

    @OneToMany(mappedBy = "app_user")
    private ArrayList<Vote> votes = new ArrayList<>();

    AppUser(String name) {
        this.user_name = name;
    }
}
