package no.sysco.middleware.services.impl;

import brave.Span;
import brave.Tracer;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import no.sysco.middleware.grpc.ArtistBaseDefinition;
import no.sysco.middleware.grpc.ArtistServiceGrpc;
import no.sysco.middleware.models.Artist;
import no.sysco.middleware.models.builder.ArtistBuilder;
import no.sysco.middleware.services.ArtistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public final class ArtistServiceImpl implements ArtistService {

    private static Logger logger = LoggerFactory.getLogger(ArtistServiceImpl.class);
    private ArtistServiceGrpc.ArtistServiceBlockingStub stub;
    private ManagedChannel managedChannel;
    private Tracer tracer;


    @Autowired
    public ArtistServiceImpl(@Qualifier("getArtistGrpcChannel") final ManagedChannel managedChannel, final Tracer tracer) {
        this.managedChannel = managedChannel;
        this.tracer = tracer;
        this.stub = ArtistServiceGrpc
                .newBlockingStub(this.managedChannel)
                .withWaitForReady();
    }

    @Override
    public Artist get(final String id) {
        final Span span = this.tracer.nextSpan().name("GetArtistById").start();
        try (Tracer.SpanInScope sis = tracer.withSpanInScope(span.start())) {
            logger.info("Calling ArtistServiceGrpc :: Get");
            return this.toModel(this.stub.get(ArtistBaseDefinition.SimpleArtistRequest.newBuilder().setId(id).build()));
        } finally {
            span.finish();
        }
    }

    @Override
    public List<Artist> getAll() {
        final Span span = this.tracer.nextSpan().name("GetArtists").start();
        try (Tracer.SpanInScope sis = tracer.withSpanInScope(span.start())) {
            logger.info("Calling ArtistServiceGrpc :: GetAll");
            return this.convertToModel(this.stub.getAll(Empty.newBuilder().build()));
        } finally {
            span.finish();
        }
    }


    @Override
    public List<Artist> getArtistByAlbum(final String albumId) {

        final Span span = this.tracer.nextSpan().name("GetArtistByAlbum").start();
        try (Tracer.SpanInScope sis = tracer.withSpanInScope(span.start())) {
            logger.info("Calling ArtistServiceGrpc :: GetArtistByAlbum");
            return this.convertToModel(this.stub.getArtistByAlbum(ArtistBaseDefinition.SimpleArtistRequest.newBuilder().setId(albumId).build()));
        } finally {
            span.finish();
        }
    }

    @Override
    public List<Artist> getArtistByTrack(String trackId) {
        final Span span = this.tracer.nextSpan().name("GetArtistByTrack").start();
        try (Tracer.SpanInScope sis = tracer.withSpanInScope(span.start())) {
            logger.info("Calling ArtistServiceGrpc :: GetArtistByTrack");
            return this.convertToModel(this.stub.getArtistByTrack(ArtistBaseDefinition.SimpleArtistRequest.newBuilder().setId(trackId).build()));
        } finally {
            span.finish();
        }
    }

    private Artist toModel(ArtistBaseDefinition.Artist artist) {
        return new ArtistBuilder()
                .setName(artist.getName())
                .setId(artist.getId())
                .setDescription(artist.getDescription())
                .build();
    }

    private List<Artist> convertToModel(final Iterator<ArtistBaseDefinition.Artist> all) {
        final List<Artist> artists = new ArrayList<>();
        while (all.hasNext()) {
            artists.add(this.toModel(all.next()));
        }
        return artists;
    }
}
