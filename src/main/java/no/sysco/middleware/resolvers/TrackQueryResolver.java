package no.sysco.middleware.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import no.sysco.middleware.models.Album;
import no.sysco.middleware.models.Artist;
import no.sysco.middleware.models.Track;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrackQueryResolver implements GraphQLResolver<Track> {
    public List<Artist> getArtists(Track track) {return null;}

    public Album getAlbum(Track track) {return null;}
}
