package no.sysco.middleware.services.impl;

import no.sysco.middleware.models.Album;
import no.sysco.middleware.models.Artist;
import no.sysco.middleware.models.Track;
import no.sysco.middleware.models.builder.AlbumBuilder;
import no.sysco.middleware.models.builder.ArtistBuilder;
import no.sysco.middleware.models.builder.TrackBuilder;
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

    @Override
    public List<Artist> getArtists(String albumId) {
        return this.generateArtistList();
    }

    @Override
    public List<Track> getTracks(String albumId) {
        return this.generateTracks();
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


    private List<Artist> generateArtistList() {
        return Arrays.asList(this.getArtist(), this.getArtist());
    }

    private Artist getArtist() {
        return new ArtistBuilder()
                .setId(UUID.randomUUID().toString())
                .setName("Some Artist Name")
                .build();
    }

    private List<Track> generateTracks() {
        return Arrays.asList(this.getTrack(), this.getTrack());
    }

    private Track getTrack() {
        return new TrackBuilder()
                .setAlbumId(UUID.randomUUID().toString())
                .setArtistId(UUID.randomUUID().toString())
                .setId(UUID.randomUUID().toString())
                .setDuration(new Random().nextDouble())
                .setName("Song Name")
                .build();
    }
}
