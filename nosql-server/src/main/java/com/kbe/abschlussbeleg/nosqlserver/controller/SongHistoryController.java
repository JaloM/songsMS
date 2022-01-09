package com.kbe.abschlussbeleg.nosqlserver.controller;

import com.kbe.abschlussbeleg.nosqlserver.model.SongHistory;
import com.kbe.abschlussbeleg.nosqlserver.service.SongHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/songsWS-Moroz_KBE/nosql/history")
public class SongHistoryController {

    @Autowired
    private SongHistoryService songHistoryService;

    @PostMapping
    public ResponseEntity<?> addHistory(@RequestHeader HttpHeaders headers, @RequestBody SongHistory history) {
        SongHistory newHistory = new SongHistory();
        String token = getToken(headers);
        if (token != null) {
            String userName = songHistoryService.getUsernameFromToken(token);
            if (userName != null) {
                if (history != null) {
                    if (history.getSongName() != null && history.getHistory() != null) {
                        newHistory.setSongName(history.getSongName());
                        newHistory.setHistory(history.getHistory());
                        songHistoryService.save(newHistory);
                        return ResponseEntity.status(HttpStatus.CREATED).build();
                    }
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    @GetMapping(value = "/{name}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getHistoryByName(@RequestHeader HttpHeaders headers, @PathVariable String name) {
        String token = getToken(headers);
        if (token != null) {
            String userName = songHistoryService.getUsernameFromToken(token);
            if (userName != null) {
                Integer idSong = songHistoryService.getSongIdFromSongsRepo(name);
                SongHistory songHistory = songHistoryService.findBySongName(name);
                if (songHistory != null) {
                    songHistory.setSongid(idSong);
                    return new ResponseEntity<>(songHistory, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    private String getToken(HttpHeaders request) {
        String value = request.getFirst(HttpHeaders.AUTHORIZATION);
        if(value!=null){
            String[] pureToken = value.split(" ");
            value = pureToken[1];
            return value;
        }
        return null;
    }
}
