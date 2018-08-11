package no.sysco.middleware.services;

import no.sysco.middleware.models.Track;

import java.util.List;

public interface TrackService {
    List<Track> getAll();
    List<Track> getTracksByAlbum(String trackId);
    List<Track> getTracksByArtist(String artistId);
}
