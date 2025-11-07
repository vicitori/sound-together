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
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    @OneToMany(mappedBy = "appUser")
    private List<Vote> votes = new ArrayList<>();

    public AppUser(String name) {
        this.userName = name;
    }
}
