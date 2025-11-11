package com.vicitori.sound_together.repository;

import com.vicitori.sound_together.model.AppUser;
import com.vicitori.sound_together.model.Track;
import com.vicitori.sound_together.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByTrackAndAppUser(Track track, AppUser appUser);
}
