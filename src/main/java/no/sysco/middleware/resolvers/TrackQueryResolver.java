package no.sysco.middleware.resolvers;

import brave.ScopedSpan;
import brave.Tracer;
import com.coxautodev.graphql.tools.GraphQLResolver;
import no.sysco.middleware.models.Album;
import no.sysco.middleware.models.Artist;
import no.sysco.middleware.models.Track;
import no.sysco.middleware.services.AlbumService;
import no.sysco.middleware.services.ArtistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrackQueryResolver implements GraphQLResolver<Track> {

    private static Logger logger = LoggerFactory.getLogger(TrackQueryResolver.class);
    private ArtistService artistService;
    private AlbumService albumService;
    private Tracer tracer;

    @Autowired
    public TrackQueryResolver(final ArtistService artistService, final AlbumService albumService, final Tracer tracer) {
        this.artistService = artistService;
        this.albumService = albumService;
        this.tracer = tracer;
    }

    public List<Artist> getArtists(Track track) {
        final ScopedSpan span = this.tracer.startScopedSpan("gql-track-resolving-artists");
        try {
            return this.artistService.getArtistByTrack(track.getId());
        } catch (RuntimeException | Error e) {
            span.error(e);
            throw e;
        } finally {
            span.finish();
        }
    }

    public Album getAlbum(Track track) {
        final ScopedSpan span = this.tracer.startScopedSpan("gql-track-resolving-albums");
        try {
            return this.albumService.getAlbumByTrack(track.getId());
        } catch (RuntimeException | Error e) {
            span.error(e);
            throw e;
        } finally {
            span.finish();
        }
    }
}
