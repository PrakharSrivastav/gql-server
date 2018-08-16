package no.sysco.middleware.services;

import no.sysco.middleware.models.Album;
import no.sysco.middleware.models.Artist;
import no.sysco.middleware.models.Track;

import java.util.List;

public interface AlbumService {
    Album get(String id);
    List<Album> getAll();
    List<Album> getAlbumsByArtist(String artistId);
    Album getAlbumByTrack(String trackId);
}
