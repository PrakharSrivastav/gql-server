package no.sysco.middleware.services.impl;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import no.sysco.middleware.grpc.TrackBaseDefinition;
import no.sysco.middleware.grpc.TrackServiceGrpc;
import no.sysco.middleware.models.Track;
import no.sysco.middleware.models.builder.TrackBuilder;
import no.sysco.middleware.services.TrackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public final class TrackServiceImpl implements TrackService {

    private static Logger logger = LoggerFactory.getLogger(TrackServiceImpl.class);
    private TrackServiceGrpc.TrackServiceBlockingStub stub;

    @Autowired
    public TrackServiceImpl(@Qualifier("getTrackGrpcChannel") ManagedChannel managedChannel) {
        this.stub = TrackServiceGrpc.newBlockingStub(managedChannel);
    }

    @Override
    public Track get(String trackId) {
        return this.getTrack(this.stub.get(TrackBaseDefinition.SimpleTrackRequest.newBuilder().setId(trackId).build()));
    }

    @Override
    public List<Track> getAll() {
        return this.convertToModel(this.stub.getAll(Empty.newBuilder().build()));
    }

    @Override
    public List<Track> getTracksByAlbum(String trackId) {
        return this.convertToModel(this.stub.getTrackByAlbum(TrackBaseDefinition.SimpleTrackRequest.newBuilder().setId(trackId).build()));
    }

    @Override
    public List<Track> getTracksByArtist(String artistId) {
        return this.convertToModel(this.stub.getTrackByArtist(TrackBaseDefinition.SimpleTrackRequest.newBuilder().setId(artistId).build()));
    }

    private List<Track> convertToModel(final Iterator<TrackBaseDefinition.Track> all) {
        final List<Track> tracks = new ArrayList<>();
        while (all.hasNext()) {
            tracks.add(this.getTrack(all.next()));
        }
        return tracks;
    }

    private Track getTrack(TrackBaseDefinition.Track t) {
        return new TrackBuilder()
                .setAlbumId(t.getAlbumId())
                .setArtistId(t.getArtistId())
                .setId(t.getId())
                .setDuration(t.getDuration())
                .setName(t.getName())
                .setDescription(t.getDescription())
                .build();
    }
}
