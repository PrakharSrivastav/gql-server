package no.sysco.middleware.services;

import no.sysco.middleware.models.Album;
import no.sysco.middleware.models.Artist;
import no.sysco.middleware.models.Track;

import java.util.List;

public interface ArtistService {
    List<Artist> get();
    List<Album>  getAlbumsForArtist(String artistId);
    List<Track>  getTracksForArtist(String artistId);
}
