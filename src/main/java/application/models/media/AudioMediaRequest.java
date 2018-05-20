package application.models.media;

public class AudioMediaRequest extends MediaRequest {

    private String artist;
    private String songName;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
}
