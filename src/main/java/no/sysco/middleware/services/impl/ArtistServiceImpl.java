package no.sysco.middleware.services.impl;

import no.sysco.middleware.models.Artist;
import no.sysco.middleware.services.ArtistService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public final class ArtistServiceImpl implements ArtistService {
    @Override
    public List<Artist> get() {
        return this.generateList();
    }

    private List<Artist> generateList() {
        return Arrays.asList(this.getArtist(), this.getArtist());
    }

    private Artist getArtist() {
        final Artist artist = new Artist();
        artist.setId(UUID.randomUUID().toString());
        artist.setName("Some Artist Name");
        return artist;
    }
}
