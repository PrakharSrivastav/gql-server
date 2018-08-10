package no.sysco.middleware.services;

import no.sysco.middleware.models.Album;
import no.sysco.middleware.models.Artist;
import no.sysco.middleware.models.Track;

import java.util.List;

public interface AlbumService {
    public List<Album> get();
    public List<Artist> getArtists(String albumId);
    public List<Track> getTracks(String albumId);
}
