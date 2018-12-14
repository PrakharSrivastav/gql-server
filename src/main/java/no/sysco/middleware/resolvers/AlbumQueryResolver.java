package no.sysco.middleware.resolvers;

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

    @Autowired
    public AlbumQueryResolver(ArtistService artistService, TrackService trackService) {
        this.artistService = artistService;
        this.trackService = trackService;
    }


    public List<Artist> getArtists(final Album album) {
        return this.artistService.getArtistByAlbum(album.getId());
    }

    public List<Track> getTracks(final Album album) {
        return this.trackService.getTracksByAlbum(album.getId());
    }

}
