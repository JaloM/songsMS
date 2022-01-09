package com.kbe.abschlussbeleg.songsWS.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="songslist")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SongList {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ownerid")
    private String ownerid;
    private String name;

    //@Column(name = "isprivate")
    private boolean isprivate;

    @Column(name = "playlist")
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "song_songsList",
            joinColumns = {@JoinColumn(name = "songsList_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "song_id", referencedColumnName = "id")})
    private Set<Song> playlist;

    @Override
    public String toString() {
        String songs = "Songs:\n";
        for (Song s : playlist) {
            songs = songs + s.toString() + "\n";
        }
        return "SongList{" +
                "id=" + id +
                ", ownerId=" + ownerid +
                ", name='" + name + '\'' +
                ", isPrivate=" + isprivate +
                ", songList=" + songs +
                '}';
    }
}