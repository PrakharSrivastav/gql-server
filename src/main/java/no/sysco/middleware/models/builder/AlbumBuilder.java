package no.sysco.middleware.models.builder;

import no.sysco.middleware.models.Album;

public class AlbumBuilder {
    private String id;
    private String name;
    private String artistId;

    public AlbumBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public AlbumBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public AlbumBuilder setArtistId(String artistId) {
        this.artistId = artistId;
        return this;
    }

    public Album createAlbum() {
        return new Album(id, name, artistId);
    }
}