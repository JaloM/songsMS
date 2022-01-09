package com.kbe.abschlussbeleg.songsWS.repository;

import com.kbe.abschlussbeleg.songsWS.model.SongList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SongListRepository extends JpaRepository<SongList, Integer> {
    @Query(value = "SELECT * FROM songslist WHERE ownerid = ?1", nativeQuery = true)
    List<SongList> findByUserId(String userId);

    @Query(value = "SELECT * FROM songslist WHERE ownerid = ?1 AND isprivate = ?2", nativeQuery = true)
    List<SongList> findByUserIdAndIsPrivate(String userId, boolean isPrivate);

    //"SELECT user_id FROM songlist WHERE id = ?1
    @Query(value = "SELECT ownerid FROM songslist WHERE id = ?1", nativeQuery = true)
    String findOwnerByListId(Integer id);

    @Query(value = "SELECT * FROM songslist WHERE id = ?1", nativeQuery = true)
    SongList findListById(Integer id);
}
