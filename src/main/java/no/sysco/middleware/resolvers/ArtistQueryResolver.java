package no.sysco.middleware.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import no.sysco.middleware.models.Album;
import no.sysco.middleware.models.Artist;
import no.sysco.middleware.models.Track;
import no.sysco.middleware.services.AlbumService;
import no.sysco.middleware.services.TrackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtistQueryResolver implements GraphQLResolver<Artist> {

    private static Logger logger = LoggerFactory.getLogger(ArtistQueryResolver.class);
    private AlbumService albumService;
    private TrackService trackService;

    @Autowired
    public ArtistQueryResolver(AlbumService albumService, TrackService trackService) {
        this.albumService = albumService;
        this.trackService = trackService;
    }


    public List<Album> getAlbums(final Artist artist) {
        logger.info("Get Album for artist {}", artist.getId());
        return this.albumService.getAlbumsByArtist(artist.getId());
    }

    public List<Track> getTracks(final Artist artist) {
        logger.info("Get Track for artist {}", artist.getId());
        return this.trackService.getTracksByArtist(artist.getId());
    }

}
