package no.sysco.middleware.models.builder;

import no.sysco.middleware.models.Track;

public class TrackBuilder {
    private String id;
    private String name;
    private String artistId;
    private String albumId;
    private Double duration;

    public TrackBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public TrackBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TrackBuilder setArtistId(String artistId) {
        this.artistId = artistId;
        return this;
    }

    public TrackBuilder setAlbumId(String albumId) {
        this.albumId = albumId;
        return this;
    }

    public TrackBuilder setDuration(Double duration) {
        this.duration = duration;
        return this;
    }

    public Track createTrack() {
        return new Track(id, name, artistId, albumId, duration);
    }
}