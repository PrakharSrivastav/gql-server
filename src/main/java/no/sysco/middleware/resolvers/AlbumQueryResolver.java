package no.sysco.middleware.resolvers;

import brave.ScopedSpan;
import brave.Tracer;
import com.coxautodev.graphql.tools.GraphQLResolver;
import no.sysco.middleware.models.Album;
import no.sysco.middleware.models.Artist;
import no.sysco.middleware.models.Track;
import no.sysco.middleware.services.ArtistService;
import no.sysco.middleware.services.TrackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlbumQueryResolver implements GraphQLResolver<Album> {

    private static Logger logger = LoggerFactory.getLogger(AlbumQueryResolver.class);
    private ArtistService artistService;
    private TrackService trackService;
    private Tracer tracer;

    @Autowired
    public AlbumQueryResolver(ArtistService artistService, TrackService trackService, final Tracer tracer) {
        this.artistService = artistService;
        this.trackService = trackService;
        this.tracer = tracer;
    }


    public List<Artist> getArtists(final Album album) {
        final ScopedSpan span = this.tracer.startScopedSpan("gql-album-resolving-artists");
        try {
            logger.info("Get Artist for album {}", album.getId());
            return this.artistService.getArtistByAlbum(album.getId());
        } catch (RuntimeException | Error e) {
            span.error(e);
            throw e;
        } finally {
            span.finish();
        }
    }

    public List<Track> getTracks(final Album album) {
        final ScopedSpan span = this.tracer.startScopedSpan("gql-album-resolving-tracks");
        try {
            logger.info("Get Tracks for album {}", album.getId());
            return this.trackService.getTracksByAlbum(album.getId());
        } catch (RuntimeException | Error e) {
            span.error(e);
            throw e;
        } finally {
            span.finish();
        }
    }

}
