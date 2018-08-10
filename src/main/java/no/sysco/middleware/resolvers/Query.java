package no.sysco.middleware.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import no.sysco.middleware.models.Album;
import no.sysco.middleware.models.Artist;
import no.sysco.middleware.models.Track;
import no.sysco.middleware.services.AlbumService;
import no.sysco.middleware.services.ArtistService;
import no.sysco.middleware.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class Query implements GraphQLQueryResolver {

    private ArtistService artistService;
    private TrackService trackService;
    private AlbumService albumService;

    @Autowired
    public Query(ArtistService artistService, TrackService trackService, AlbumService albumService) {
        this.artistService = artistService;
        this.trackService = trackService;
        this.albumService = albumService;
    }

    // Get all Artists
    public List<Artist> getArtist() {
        return this.artistService.get();
    }

    // Get all Albums
    public List<Album> getAlbum() {
        return this.albumService.get();
    }

    // Get all Tracks
    public List<Track> getTrack() {
        return this.trackService.get();
    }
}
