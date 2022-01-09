package com.kbe.abschlussbeleg.songsWS.controller;

import com.kbe.abschlussbeleg.songsWS.model.Song;
import com.kbe.abschlussbeleg.songsWS.model.SongList;
import com.kbe.abschlussbeleg.songsWS.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/songsWS-Moroz_KBE/songs/playlist")
public class SongListController {

    @Autowired
    private SongService songService;


    private String getToken(HttpHeaders request) {
        String value = request.getFirst(HttpHeaders.AUTHORIZATION);
        if(value!=null) {
            String[] pureToken = value.split(" ");
            if (pureToken.length > 1) {
                value = pureToken[1];
                return value;
            }
        }
        return null;
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getUserById(@RequestHeader HttpHeaders headers, @RequestParam String userId) {
        String token = getToken(headers);
        if (token != null) {
            String currentUser = songService.getUsernameFromToken(token);
            if (currentUser != null){
                List<SongList> list;
                if(userId.equals(currentUser)) {
                 list = songService.findByUserId(currentUser);
                if(list.size() > 0) {
                        return  ResponseEntity.ok(list);
                    }
                }else{
                        list = songService.findByUserIdAndIsPrivate(userId, false);
                        return ResponseEntity.ok(list);
                    }
                }
                return new ResponseEntity<>((HttpStatus.NOT_FOUND));
            }
        return new ResponseEntity<>((HttpStatus.UNAUTHORIZED));
    }


    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getSongListById(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") Integer id) {
        String token = getToken(headers);
        if (token != null) {
            String userName = songService.getUsernameFromToken(token);
            SongList li = songService.findListById(id);
            if (li != null) {
                if (li.getOwnerid().equals(userName) || !li.isIsprivate()) {
                    return ResponseEntity.ok(li);
                } else {
                    return ResponseEntity.status(403).build();
                }
            }
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>((HttpStatus.UNAUTHORIZED));
    }


    @PostMapping("/")
    public ResponseEntity<?> createSongsList(@RequestHeader HttpHeaders headers, @RequestBody SongList songlist) {

        String token = getToken(headers);
        if (token != null) {
            String userName = songService.getUsernameFromToken(token);
            if (userName != null) {
                songlist.setOwnerid(userName);
            }
            Iterable<Song> songs = songService.findAll();
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(songlist.getId())
                    .toUri();
            for (Song song : songlist.getPlaylist()) {
                Song so = songService.findSongById(song.getId());
                if (so != null) {
                    songService.saveList(songlist);
                    return ResponseEntity.created(location).build();
                } else {
                    return ResponseEntity.badRequest().build();
                }
            }
        }
        return new ResponseEntity<>((HttpStatus.UNAUTHORIZED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSongList(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") Integer id,
                                            @RequestBody SongList songListToPut) {
        String token = getToken(headers);
        if (token != null) {
            String userName = songService.getUsernameFromToken(token);
            if (userName != null) {
                SongList songList = songService.findListById(id);
                if (songList != null && songList.getOwnerid().equals(userName)) {
                    songList.setPlaylist(songListToPut.getPlaylist());
                    songList.setIsprivate(songListToPut.isIsprivate());
                    songList.setName(songListToPut.getName());
                    SongList updated = songService.saveList(songList);
                    return new ResponseEntity<>(updated, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>((HttpStatus.UNAUTHORIZED));
        }
        return new ResponseEntity<>((HttpStatus.UNAUTHORIZED));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSongList(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") Integer id) {
        String token = getToken(headers);
        if (token != null) {
            String userName = songService.getUsernameFromToken(token);
            if (userName != null) {
                SongList li = songService.findListById(id);
                if (li != null) {
                    if (li.getOwnerid().equals(userName)) {
                        songService.deleteListById(id);
                        return ResponseEntity.noContent().build();
                    } else {
                        return ResponseEntity.status(403).build();
                    }
                }
                return ResponseEntity.badRequest().build();
            }
            return new ResponseEntity<>((HttpStatus.UNAUTHORIZED));
        }
        return new ResponseEntity<>((HttpStatus.UNAUTHORIZED));
    }

}
