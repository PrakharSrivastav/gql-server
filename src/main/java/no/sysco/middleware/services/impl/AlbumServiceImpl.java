package no.sysco.middleware.services.impl;

import no.sysco.middleware.models.Album;
import no.sysco.middleware.models.builder.AlbumBuilder;
import no.sysco.middleware.services.AlbumService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public final class AlbumServiceImpl implements AlbumService {
    @Override
    public List<Album> getAll() {
        return this.generateList();
    }

    @Override
    public List<Album> getAlbumsByArtist(String artistId) {
        return this.generateList();
    }

    @Override
    public Album getAlbumByTrack(String trackId) {
        return this.getAlbum();
    }

    private List<Album> generateList() {
        return Arrays.asList(this.getAlbum(), this.getAlbum());
    }

    private Album getAlbum() {
        return new AlbumBuilder()
                .setArtistId(UUID.randomUUID().toString())
                .setId(UUID.randomUUID().toString())
                .setName("Some Name")
                .build();
    }

}
