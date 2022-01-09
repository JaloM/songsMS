package com.kbe.abschlussbeleg.songsWS.controller;

import com.kbe.abschlussbeleg.songsWS.exception.ResourceNotFoundException;
import com.kbe.abschlussbeleg.songsWS.model.Song;
import com.kbe.abschlussbeleg.songsWS.repository.SongRepository;
import com.kbe.abschlussbeleg.songsWS.service.SongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/songsWS-Moroz_KBE/songs")
public class SongsController {

    private final SongRepository songRepo;

    @Autowired
    private SongService songService;

    public SongsController(SongRepository songRepo) {
        this.songRepo = Objects.requireNonNull(songRepo, songRepo.getClass() + " must not be null");
    }

    private String getToken(HttpHeaders request){
        String value = request.getFirst(HttpHeaders.AUTHORIZATION);
        if(value!=null) {
            String[] pureToken = value.split(" ");
            value = pureToken[1];
            return value;
        }
        return null;
    }

    @GetMapping
    public ResponseEntity<?>  getAllSongs(@RequestHeader HttpHeaders headers) {
        String token = getToken(headers);
        if (token != null && (songService.getUsernameFromToken(token) != null)) {
                return new ResponseEntity<>(songService.findAll(), HttpStatus.OK);
            }
               return new ResponseEntity<>((HttpStatus.UNAUTHORIZED));
    }


    @GetMapping("/title/{title}")
    public ResponseEntity<?> getSongIdByTitel(@RequestHeader HttpHeaders headers, @Valid @PathVariable(value = "title") String title) {
       /* String token = getToken(headers);
        if (token != null) {
            if (songService.getUsernameFromToken(token) != null) { //add  token expired*/
                Song song_ = songService.getSongIdByTitel(title);
        if (song_ != null) {
                Integer id = song_.getId();
                if (id==0) {
                    return new ResponseEntity<>((HttpStatus.NOT_FOUND));
                }
                return new ResponseEntity<>(id, HttpStatus.OK);
            }
             return new ResponseEntity<>((HttpStatus.NOT_FOUND));
        }



    @GetMapping("/{id}")
    public ResponseEntity<?> getSongById(@RequestHeader HttpHeaders headers, @Valid @PathVariable(value = "id") Integer id) {
        String token = getToken(headers);
        if (token != null) {
            if (songService.getUsernameFromToken(token) != null) {
                Optional<Song> song = songService.findById(id);
                if (song.isPresent()) {
                    return ResponseEntity.of(song);
                }
                return new ResponseEntity<>((HttpStatus.NOT_FOUND));
            }
            return new ResponseEntity<>((HttpStatus.UNAUTHORIZED));
        }
        return new ResponseEntity<>((HttpStatus.UNAUTHORIZED));
    }

    @PostMapping
    public ResponseEntity<?> createSong(@RequestHeader HttpHeaders headers, @Valid @RequestBody Song song) {
        String token = getToken(headers);
        Song newSong = null;
        if (token != null) {
            if (songService.getUsernameFromToken(token) != null) {
                try {
                    newSong = songRepo.save(song);
                    return new ResponseEntity<>(newSong, HttpStatus.CREATED);
                } catch (Exception e) {
                    return new ResponseEntity<>(newSong, HttpStatus.BAD_REQUEST);
                }

            }
        }
        return new ResponseEntity<>((HttpStatus.UNAUTHORIZED));

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateSong(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") Integer id,
                           @RequestBody Song songToPut) {
        String token = getToken(headers);
        if (token != null) {
            if (songService.getUsernameFromToken(token) != null) {
                Song song = songService.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Song", "id", id));
                song.setArtist(songToPut.getArtist());
                song.setLabel(songToPut.getLabel());
                song.setTitle(songToPut.getTitle());
                song.setReleased(songToPut.getReleased());

                Song updatedSong = songService.save(song);
                return new ResponseEntity<>(updatedSong, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>((HttpStatus.UNAUTHORIZED));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSong(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") Integer id) {
        String token = getToken(headers);
        if (token != null) {
            if (songService.getUsernameFromToken(token) != null) {
                Song song = songService.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Song", "id", id));
                songService.delete(song);
                return ResponseEntity.noContent().build();
            }
        }
        return new ResponseEntity<>((HttpStatus.UNAUTHORIZED));
    }
}
