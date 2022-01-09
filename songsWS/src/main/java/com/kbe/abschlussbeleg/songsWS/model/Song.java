package com.kbe.abschlussbeleg.songsWS.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="songs")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Song {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String artist;
    private String label;
    private Integer released;


    @Override
    public String toString() {
        return "{\"id\":" + id + ",\"title\":" + "\"" + title + "\"" + "," + "\"artist\":"
                + "\"" + artist + "\"" + ","+ "\"album\":" + "\"" + label + "\"" +"," +"\"released\":"
                + released + "}";
    }
}
