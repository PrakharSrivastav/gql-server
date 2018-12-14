package no.sysco.middleware.services.impl;

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

    @Autowired
    public AlbumServiceImpl(@Qualifier("getAlbumGrpcChannel") final ManagedChannel managedChannel) {
        this.managedChannel = managedChannel;
        this.stub = AlbumServiceGrpc.newBlockingStub(this.managedChannel).withWaitForReady();
    }

    @Override
    public Album get(String id) {
        return this.getAlbum(this.stub.get(AlbumBaseDefinition.SimpleAlbumRequest.newBuilder().setId(id).build()));
    }

    @Override
    public List<Album> getAll() {
        return this.convertToModel(this.stub.getAll(Empty.newBuilder().build()));
    }

    @Override
    public List<Album> getAlbumsByArtist(String artistId) {
        return this.convertToModel(this.stub.getAlbumByArtist(AlbumBaseDefinition.SimpleAlbumRequest.newBuilder().setId(artistId).build()));
    }

    @Override
    public Album getAlbumByTrack(String trackId) {
        return this.getAlbum(this.stub.getAlbumByTrack(AlbumBaseDefinition.SimpleAlbumRequest.newBuilder().setId(trackId).build()));
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
