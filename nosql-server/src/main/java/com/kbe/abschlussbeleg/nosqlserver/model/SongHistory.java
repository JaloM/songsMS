package com.kbe.abschlussbeleg.nosqlserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("history")
public class SongHistory {
    @Id
    private ObjectId id;

    @Field("name")
    private String songName;

    @Field("text")
    private String history;

    @Field("songid")
    private Integer songid;


}
