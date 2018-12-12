package no.sysco.middleware.services.impl;

import brave.ScopedSpan;
import brave.Tracer;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import no.sysco.middleware.grpc.AlbumBaseDefinition;
import no.sysco.middleware.grpc.AlbumServiceGrpc;
import no.sysco.middleware.models.Album;
import no.sysco.middleware.models.builder.AlbumBuilder;
import no.sysco.middleware.services.AlbumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public final class AlbumServiceImpl implements AlbumService {

    private static Logger logger = LoggerFactory.getLogger(AlbumServiceImpl.class);
    private AlbumServiceGrpc.AlbumServiceBlockingStub stub;
    private ManagedChannel managedChannel;
    private Tracer tracer;

    @Autowired
    public AlbumServiceImpl(@Qualifier("getAlbumGrpcChannel") final ManagedChannel managedChannel, final Tracer tracer) {
        this.managedChannel = managedChannel;
        this.tracer = tracer;
        this.stub = AlbumServiceGrpc.newBlockingStub(this.managedChannel).withWaitForReady();
    }

    @Override
    public Album get(String id) {
        final ScopedSpan span = this.tracer.startScopedSpan("GetAlbumById");
        try {
            logger.info("Calling AlbumServiceGrpc :: Get");
            return this.getAlbum(this.stub.get(AlbumBaseDefinition.SimpleAlbumRequest.newBuilder().setId(id).build()));
        } finally {
            span.finish();
        }
    }

    @Override
    public List<Album> getAll() {
        final ScopedSpan span = this.tracer.startScopedSpan("GetAlbums");
        try {
            logger.info("Calling AlbumServiceGrpc :: GetAll");
            return this.convertToModel(this.stub.getAll(Empty.newBuilder().build()));
        } finally {
            span.finish();
        }
    }

    @Override
    public List<Album> getAlbumsByArtist(String artistId) {
        final ScopedSpan span = this.tracer.startScopedSpan("GetAlbumsByArtist");
        try {
            logger.info("Calling AlbumServiceGrpc :: GetAlbumByArtist");
            return this.convertToModel(this.stub.getAlbumByArtist(AlbumBaseDefinition.SimpleAlbumRequest.newBuilder().setId(artistId).build()));
        }
        catch (Error  | RuntimeException e){
            span.error(e);
            throw e;
        }
        finally {
            span.finish();
        }
    }

    @Override
    public Album getAlbumByTrack(String trackId) {
        final ScopedSpan span = this.tracer.startScopedSpan("GetAlbumsByTrack");
        try {
            logger.info("Calling AlbumServiceGrpc :: GetAlbumByTrack");
            return this.getAlbum(this.stub.getAlbumByTrack(AlbumBaseDefinition.SimpleAlbumRequest.newBuilder().setId(trackId).build()));
        } finally {
            span.finish();
        }
    }

    private Album getAlbum(AlbumBaseDefinition.Album album) {
        return new AlbumBuilder()
                .setArtistId(album.getArtistId())
                .setId(album.getId())
                .setName(album.getName())
                .setDescription(album.getDescription())
                .build();
    }

    private List<Album> convertToModel(Iterator<AlbumBaseDefinition.Album> all) {
        final List<Album> albums = new ArrayList<>();
        while (all.hasNext()) {
            albums.add(this.getAlbum(all.next()));
        }
        return albums;
    }

}
