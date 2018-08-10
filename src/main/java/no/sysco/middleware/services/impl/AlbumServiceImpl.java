package no.sysco.middleware.services.impl;

import no.sysco.middleware.models.Album;
import no.sysco.middleware.services.AlbumService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public final class AlbumServiceImpl implements AlbumService {
    @Override
    public List<Album> get() {
        return this.generateList();
    }

    private List<Album> generateList() {
        return Arrays.asList(this.getAlbum(), this.getAlbum());
    }

    private Album getAlbum() {
        final Album album = new Album();
        album.setArtistId(UUID.randomUUID().toString());
        album.setId(UUID.randomUUID().toString());
        album.setName("Some Name");
        return album;
    }
}
