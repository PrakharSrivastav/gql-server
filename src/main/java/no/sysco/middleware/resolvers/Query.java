package no.sysco.middleware.resolvers;

import brave.ScopedSpan;
import brave.Tracer;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import no.sysco.middleware.models.Album;
import no.sysco.middleware.models.Artist;
import no.sysco.middleware.models.Track;
import no.sysco.middleware.services.AlbumService;
import no.sysco.middleware.services.ArtistService;
import no.sysco.middleware.services.TrackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class Query implements GraphQLQueryResolver {

    private static Logger logger = LoggerFactory.getLogger(Query.class);

    private ArtistService artistService;
    private TrackService trackService;
    private AlbumService albumService;
    private Tracer tracer;

    @Autowired
    public Query(ArtistService artistService, TrackService trackService, AlbumService albumService, final Tracer tracer) {
        this.artistService = artistService;
        this.trackService = trackService;
        this.albumService = albumService;
        this.tracer = tracer;
    }

    // Get all Artists
    public List<Artist> getArtists() {
        final ScopedSpan span = this.tracer.startScopedSpan("GQL-Artist-Root");
        try {
            return this.artistService.getAll();
        } catch (RuntimeException | Error e) {
            span.error(e);
            throw e;
        } finally {
            span.finish();
        }
    }

    // Get all Albums
    public List<Album> getAlbums() {
        final ScopedSpan span = this.tracer.startScopedSpan("GQL-Album-Root");
        try {
            return this.albumService.getAll();
        } catch (RuntimeException | Error e) {
            span.error(e);
            throw e;
        } finally {
            span.finish();
        }
    }

    // Get all Tracks
    public List<Track> getTracks() {
        final ScopedSpan span = this.tracer.startScopedSpan("GQL-Track-Root");
        try {
            return this.trackService.getAll();
        } catch (RuntimeException | Error e) {
            span.error(e);
            throw e;
        } finally {
            span.finish();
        }

    }

    public Artist getArtist(final String id) {
        final ScopedSpan span = this.tracer.startScopedSpan("GQL-Artist");
        try {
            logger.info("Get Artist {}", id);
            return this.artistService.get(id);
        } catch (RuntimeException | Error e) {
            span.error(e);
            throw e;
        } finally {
            span.finish();
        }
    }

    public Track getTrack(final String id) {
        final ScopedSpan span = this.tracer.startScopedSpan("GQL-Track");
        try {
            span.tag("gql-track-get", id);
            logger.info("Get Track {}", id);
            return this.trackService.get(id);
        } catch (RuntimeException | Error e) {
            span.error(e);
            throw e;
        } finally {
            span.finish();
        }
    }

    public Album getAlbum(final String id) {
        final ScopedSpan span = this.tracer.startScopedSpan("GQL-Album");
        try {
            logger.info("Get Album {}", id);
            return this.albumService.get(id);
        } catch (RuntimeException | Error e) {
            span.error(e);
            throw e;
        } finally {
            span.finish();
        }

    }
}
