package no.sysco.middleware.services.impl;

import no.sysco.middleware.models.Album;
import no.sysco.middleware.models.Artist;
import no.sysco.middleware.models.Track;
import no.sysco.middleware.models.builder.AlbumBuilder;
import no.sysco.middleware.models.builder.ArtistBuilder;
import no.sysco.middleware.models.builder.TrackBuilder;
import no.sysco.middleware.services.ArtistService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public final class ArtistServiceImpl implements ArtistService {
    @Override
    public List<Artist> get() {
        return this.generateList();
    }

    @Override
    public List<Album> getAlbumsForArtist(final String artistId) {
        return this.generateAlbumList();
    }

    @Override
    public List<Track> getTracksForArtist(String artistId) {
        return Arrays.asList(this.getTrack(), this.getTrack());
    }

    private List<Album> generateAlbumList() {
        return Arrays.asList(this.getAlbum(), this.getAlbum());
    }

    private Album getAlbum() {
        return new AlbumBuilder()
                .setArtistId(UUID.randomUUID().toString())
                .setId(UUID.randomUUID().toString())
                .setName("Some Name")
                .build();
    }

    private List<Artist> generateList() {
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
