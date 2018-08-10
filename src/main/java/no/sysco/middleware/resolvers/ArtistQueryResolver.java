package no.sysco.middleware.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import no.sysco.middleware.models.Album;
import no.sysco.middleware.models.Artist;
import no.sysco.middleware.models.Track;
import no.sysco.middleware.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtistQueryResolver implements GraphQLResolver<Artist> {

    private ArtistService artistService;

    @Autowired
    public ArtistQueryResolver(final ArtistService artistService) {
        this.artistService = artistService;
    }

    public List<Album> getAlbums(final Artist artist) {
        return this.artistService.getAlbumsForArtist(artist.getId());
    }

    public List<Track> getTracks(final Artist artist) {
        return this.artistService.getTracksForArtist(artist.getId());
    }

}
