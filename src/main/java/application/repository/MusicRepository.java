package application.repository;

import application.models.media.Music;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MusicRepository extends MongoRepository<Music,Long> {
}
