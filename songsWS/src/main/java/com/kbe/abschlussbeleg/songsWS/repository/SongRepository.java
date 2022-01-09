package com.kbe.abschlussbeleg.songsWS.repository;

import com.kbe.abschlussbeleg.songsWS.model.Song;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface SongRepository extends CrudRepository<Song, Integer> {

    @Query(value = "SELECT * FROM songs WHERE id=?1", nativeQuery = true)
    Song findSongById(int id);

    @Query(value = "SELECT * FROM songs WHERE title=?1",nativeQuery = true)
    Song getSongIdByTitel(String title);
}
