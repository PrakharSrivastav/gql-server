package no.sysco.middleware.models.builder;

import no.sysco.middleware.models.Artist;

public class ArtistBuilder {
    private String id;
    private String name;

    public ArtistBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public ArtistBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Artist build() {
        return new Artist(id, name);
    }
}