package com.kbe.abschlussbeleg.nosqlserver.repository;

import com.kbe.abschlussbeleg.nosqlserver.model.SongHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongHistoryRepository  extends MongoRepository<SongHistory, String> {
     SongHistory findBySongName(String name);
}
