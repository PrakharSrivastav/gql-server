package no.sysco.middleware.services;

import no.sysco.middleware.models.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> get();
}
