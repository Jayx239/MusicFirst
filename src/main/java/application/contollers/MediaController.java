package application.contollers;

import application.models.media.Audio;
import application.repository.AudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/media")
public class MediaController {

    @Autowired
    AudioRepository musicRepository;

    @GetMapping("/audioByArtist")
    public @ResponseBody List<Audio> audioByArtist(@RequestParam String artist) {
        List<Audio> music = musicRepository.findByArtist(artist);
        return music;
    }
}
