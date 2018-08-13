package no.sysco.middleware.services.impl;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import no.sysco.middleware.grpc.ArtistBaseDefinition;
import no.sysco.middleware.grpc.ArtistServiceGrpc;
import no.sysco.middleware.models.Artist;
import no.sysco.middleware.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public final class ArtistServiceImpl implements ArtistService {

    private ArtistServiceGrpc.ArtistServiceBlockingStub stub;
    private ManagedChannel managedChannel;


    @Autowired
    public ArtistServiceImpl(@Qualifier("getArtistGrpcChannel") final ManagedChannel managedChannel) {
        this.managedChannel = managedChannel;
        this.stub = ArtistServiceGrpc
                .newBlockingStub(this.managedChannel)
                .withWaitForReady();
    }

    @Override
    public List<Artist> getAll() {
        return this.convertToModel(this.stub.getAll(Empty.newBuilder().build()));
    }


    @Override
    public List<Artist> getArtistByAlbum(final String albumId) {
        return this.convertToModel(this.stub.getArtistByAlbum(ArtistBaseDefinition.SimpleArtistRequest.newBuilder().setId(albumId).build()));
    }

    @Override
    public List<Artist> getArtistByTrack(String trackId) {
        return this.convertToModel(this.stub.getArtistByTrack(ArtistBaseDefinition.SimpleArtistRequest.newBuilder().setId(trackId).build()));
    }

    private Artist toModel(ArtistBaseDefinition.Artist artist) {
        final Artist a = new Artist();
        a.setName(artist.getName());
        a.setId(artist.getId());
        return a;
    }

    private List<Artist> convertToModel(final Iterator<ArtistBaseDefinition.Artist> all) {
        final List<Artist> artists = new ArrayList<>();
        while (all.hasNext()) {
            artists.add(this.toModel(all.next()));
        }
        return artists;
    }
}
