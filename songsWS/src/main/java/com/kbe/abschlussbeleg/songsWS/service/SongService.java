package com.kbe.abschlussbeleg.songsWS.service;

import com.kbe.abschlussbeleg.songsWS.model.Song;
import com.kbe.abschlussbeleg.songsWS.model.SongList;
import com.kbe.abschlussbeleg.songsWS.repository.SongListRepository;
import com.kbe.abschlussbeleg.songsWS.repository.SongRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongListRepository songListRepository;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public Optional<Song> findById(Integer songId) {
        return songRepository.findById(songId);
    }

    public Song findSongById(Integer id){
        return songRepository.findSongById(id);
    }

    public Song getSongIdByTitel(String title){
        return songRepository.getSongIdByTitel(title);
    }

    public Iterable<Song> findAll() {
        return songRepository.findAll();
    }

    public Song save(Song song) {
        return songRepository.save(song);
    }

    public void delete(Song song) {
        songRepository.delete(song);
    }

    //SongList

    //all lists from that userId
    public List<SongList> findByUserId(String userId) {
        return songListRepository.findByUserId(userId);
    }

    public SongList saveList(SongList songList) {
        return songListRepository.save(songList);
    }

    public SongList findListById(Integer id){
        return songListRepository.findListById(id);
    }

    /*public String findOwnerByListId(Integer listId) {
        return songListRepository.findOwnerByListId(listId);
    }*/

    public void deleteListById(Integer listId) {
        songListRepository.deleteById(listId);
    }

    public List<SongList> findByUserIdAndIsPrivate(String userId, boolean isPrivate) {
      return  songListRepository.findByUserIdAndIsPrivate(userId, isPrivate);
    }

    public String getUsernameFromToken(String token) {
        if (token != null) {
            try {
                return restTemplate.getForObject(
                        "http://localhost:9191/songsWS-Moroz_KBE/auth/" +
                        token, String.class);
            } catch (HttpServerErrorException | HttpClientErrorException | NullPointerException e ) {
                return null;
            }
        }
        return null;
    }
}
