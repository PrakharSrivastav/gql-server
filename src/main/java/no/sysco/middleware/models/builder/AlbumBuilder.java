package no.sysco.middleware.models.builder;

import no.sysco.middleware.models.Album;

public class AlbumBuilder {
    private String id;
    private String name;
    private String artistId;
    private String description;

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

    public AlbumBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Album build() {
        return new Album(id, name, artistId, description);
    }
}