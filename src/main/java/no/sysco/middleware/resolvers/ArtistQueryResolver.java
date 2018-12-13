package no.sysco.middleware.resolvers;

import brave.ScopedSpan;
import brave.Tracer;
import com.coxautodev.graphql.tools.GraphQLResolver;
import no.sysco.middleware.models.Album;
import no.sysco.middleware.models.Artist;
import no.sysco.middleware.models.Track;
import no.sysco.middleware.services.AlbumService;
import no.sysco.middleware.services.TrackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtistQueryResolver implements GraphQLResolver<Artist> {

    private static Logger logger = LoggerFactory.getLogger(ArtistQueryResolver.class);
    private AlbumService albumService;
    private TrackService trackService;
    private Tracer tracer;


    @Autowired
    public ArtistQueryResolver(AlbumService albumService, TrackService trackService, final Tracer tracer) {
        this.albumService = albumService;
        this.trackService = trackService;
        this.tracer = tracer;

    }


    public List<Album> getAlbums(final Artist artist) {
        final ScopedSpan span = this.tracer.startScopedSpan("gql-artist-resolving-albums");
        try {
            logger.info("Get Album for artist {}", artist.getId());
            return this.albumService.getAlbumsByArtist(artist.getId());
        } catch (RuntimeException | Error e) {
            span.error(e);
            throw e;
        } finally {
            span.finish();
        }
    }

    public List<Track> getTracks(final Artist artist) {
        final ScopedSpan span = this.tracer.startScopedSpan("gql-artist-resolving-tracks");
        try {
            logger.info("Get Track for artist {}", artist.getId());
            return this.trackService.getTracksByArtist(artist.getId());
        } catch (RuntimeException | Error e) {
            span.error(e);
            throw e;
        } finally {
            span.finish();
        }
    }

}
