package com.vicitori.sound_together.repository;

import com.vicitori.sound_together.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findByPlstName(String plstName);
    Optional<Playlist> findByPlstShareCode(String plstShareCode);
    boolean existsByPlstShareCode(String plstShareCode);
}
