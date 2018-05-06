package application.repositories;

import application.models.media.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video,Long> {
}
