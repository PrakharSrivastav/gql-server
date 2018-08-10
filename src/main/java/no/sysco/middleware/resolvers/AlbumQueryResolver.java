package no.sysco.middleware.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import no.sysco.middleware.models.Album;
import no.sysco.middleware.models.Artist;
import no.sysco.middleware.models.Track;
import no.sysco.middleware.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlbumQueryResolver implements GraphQLResolver<Album> {

    private AlbumService albumService;

    @Autowired
    public AlbumQueryResolver(final AlbumService albumService) {
        this.albumService = albumService;
    }

    public List<Artist> getArtists(final Album album) {
        return this.albumService.getArtists(album.getId());
    }

    public List<Track> getTracks(final Album album) {
        return this.albumService.getTracks(album.getId());
    }

}
