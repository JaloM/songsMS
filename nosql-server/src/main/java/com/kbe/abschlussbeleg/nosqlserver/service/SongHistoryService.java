package com.kbe.abschlussbeleg.nosqlserver.service;

import com.kbe.abschlussbeleg.nosqlserver.model.SongHistory;
import com.kbe.abschlussbeleg.nosqlserver.repository.SongHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class SongHistoryService {

    @Autowired
    SongHistoryRepository repository;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public SongHistory findBySongName(String name){
        return repository.findBySongName(name);
    }

    public String getUsernameFromToken(String token) {
        if (token != null) {
            try {
                return restTemplate.getForObject("http://localhost:9191/songsWS-Moroz_KBE/auth/" + token, String.class);
            } catch (HttpServerErrorException e) {
                return e.getMessage();
            }
        }
        return null;
    }

    public Integer getSongIdFromSongsRepo(String songName)  {
        if(songName!=null) {
            try {
                String id = restTemplate.getForObject("http://localhost:9191/songsWS-Moroz_KBE/songs/title/" + songName, String.class);
                Integer idsong = Integer.valueOf(id);
                return idsong;
            }catch (HttpStatusCodeException e){
                return -1;
            }
        }
        return 0;
    }
    public void save(SongHistory newHistory) {
        repository.save(newHistory);
    }
}
