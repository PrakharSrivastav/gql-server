package no.sysco.middleware.models;

public final class Track {
    private String id;
    private String name;
    private String artistId;
    private String albumId;
    private Double duration;
    private String description;

    public Track() { }

    public Track(String id, String name, String artistId, String albumId, Double duration, String description) {
        this.id = id;
        this.name = name;
        this.artistId = artistId;
        this.albumId = albumId;
        this.duration = duration;
        this.description = description;
    }

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
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
