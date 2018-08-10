package no.sysco.middleware.models;

public final class Track {
    private String id;
    private String name;
    private String artistId;
    private String albumId;
    private Double duration;

    public Track() { }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getArtistId() { return artistId; }
    public void setArtistId(String artistId) { this.artistId = artistId; }
    public String getAlbumId() { return albumId; }
    public void setAlbumId(String albumId) { this.albumId = albumId; }
    public Double getDuration() { return duration; }
    public void setDuration(Double duration) { this.duration = duration; }
}
