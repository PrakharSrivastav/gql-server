package no.sysco.middleware.services.impl;

import no.sysco.middleware.models.Track;
import no.sysco.middleware.services.TrackService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public final class TrackServiceImpl implements TrackService {
    @Override
    public List<Track> get() {
        return this.generateTracks();
    }

    private List<Track> generateTracks() {
        return Arrays.asList(this.getTrack(), this.getTrack());
    }

    private Track getTrack() {
        final Track track = new Track();
        track.setAlbumId(UUID.randomUUID().toString());
        track.setArtistId(UUID.randomUUID().toString());
        track.setId(UUID.randomUUID().toString());
        track.setDuration(new Random().nextDouble());
        track.setName("Song Name");
        return track;
    }
}
