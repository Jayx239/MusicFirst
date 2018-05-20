package application.repository;

import application.models.media.Audio;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AudioRepository extends MongoRepository<Audio,Long> {
    public List<Audio> findByArtist(String artist);
}
