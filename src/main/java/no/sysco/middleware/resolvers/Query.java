package no.sysco.middleware.resolvers;

import brave.Span;
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
        logger.info("Artist Root");
        final Span span = this.tracer.newTrace().name("RooTArtist").start();
        try (Tracer.SpanInScope sis = tracer.withSpanInScope(span.start())) {
            logger.info("Calling ArtistService :: GetAll");
            logger.info("Artist Root");
            return this.artistService.getAll();
        } finally {
            span.finish();
        }
    }

    // Get all Albums
    public List<Album> getAlbums() {
        logger.info("Album Root");
        final Span span = this.tracer.newTrace().name("RooTAlbum").start();
        try (Tracer.SpanInScope sis = tracer.withSpanInScope(span.start())) {
            logger.info("Calling AlbumService :: GetAll");
            logger.info("Album Root");
            return this.albumService.getAll();
        } finally {
            span.finish();
        }
    }

    // Get all Tracks
    public List<Track> getTracks() {
        logger.info("Track Root");
        final Span span = this.tracer.newTrace().name("RooTrack").start();
        try (Tracer.SpanInScope sis = tracer.withSpanInScope(span.start())) {
            logger.info("Calling TrackService :: GetAll");
            return this.trackService.getAll();
        } finally {
            span.finish();
        }

    }

    public Artist getArtist(final String id) {
        final Span span = this.tracer.newTrace().name("GetArtist").start();
        try (Tracer.SpanInScope sis = tracer.withSpanInScope(span.start())) {
            logger.info("Get Artist {}", id);
            return this.artistService.get(id);
        } finally {
            span.finish();
        }
    }

    public Track getTrack(final String id) {
        final Span span = this.tracer.newTrace().name("GetTrack").start();
        try (Tracer.SpanInScope sis = tracer.withSpanInScope(span.start())) {
            logger.info("Get Track {}", id);
            return this.trackService.get(id);
        } finally {
            span.finish();
        }
    }

    public Album getAlbum(final String id) {
        final Span span = this.tracer.newTrace().name("GetAlbum").start();
        try (Tracer.SpanInScope sis = tracer.withSpanInScope(span.start())) {
            logger.info("Get Album {}", id);
            return this.albumService.get(id);
        } finally {
            span.finish();
        }

    }
}
