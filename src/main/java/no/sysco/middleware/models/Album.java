package no.sysco.middleware.models;

public final class Album {
    private String id;
    private String name;
    private String artistId;

    public Album() { }

    public Album(String id, String name, String artistId) {
        this.id = id;
        this.name = name;
        this.artistId = artistId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getArtistId() { return artistId; }
    public void setArtistId(String artistId) { this.artistId = artistId; }


}
