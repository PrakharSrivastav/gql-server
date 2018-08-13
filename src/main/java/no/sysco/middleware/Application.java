package no.sysco.middleware;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Bean
    public ManagedChannel getArtistGrpcChannel(
            @Value("${service.artist.host}") final String host,
            @Value("${service.artist.port}") final Integer port) {
        System.out.println("Creating a managed Channel + Host :: " + host + " port :: " + port);
        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .enableRetry()
                .build();
    }

    @Bean
    public ManagedChannel getAlbumGrpcChannel(
            @Value("${service.album.host}") final String host,
            @Value("${service.album.port}") final Integer port) {
        System.out.println("Creating a managed Channel + Host :: " + host + " port :: " + port);
        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .enableRetry()
                .build();
    }

    @Bean
    public ManagedChannel getTrackGrpcChannel(
            @Value("${service.track.host}") final String host,
            @Value("${service.track.port}") final Integer port) {
        System.out.println("Creating a managed Channel + Host :: " + host + " port :: " + port);
        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .enableRetry()
                .build();
    }
}
