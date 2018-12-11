package no.sysco.middleware.resolvers;

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

    @Autowired
    public TrackQueryResolver(ArtistService artistService, AlbumService albumService) {
        this.artistService = artistService;
        this.albumService = albumService;
    }

    public List<Artist> getArtists(Track track) {
        logger.info("Get Artist for Track {}", track.getId());
        return this.artistService.getArtistByTrack(track.getId());
    }

    public Album getAlbum(Track track) {
        logger.info("Get Album for Track {}", track.getId());
        return this.albumService.getAlbumByTrack(track.getId());
    }
}
