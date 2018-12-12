package no.sysco.middleware;


import brave.Tracing;
import brave.grpc.GrpcTracing;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Bean
    public ManagedChannel getArtistGrpcChannel(
            @Value("${service.artist.host}") final String host,
            @Value("${service.artist.port}") final Integer port,
            final GrpcTracing grpcTracing) {
        logger.info("Creating getArtistGrpcChannel managed Channel. Host :: {} port :: {} ", host, port);
        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .intercept(grpcTracing.newClientInterceptor())
                .enableRetry()
                .build();
    }

    @Bean
    public ManagedChannel getAlbumGrpcChannel(
            @Value("${service.album.host}") final String host,
            @Value("${service.album.port}") final Integer port,
            final GrpcTracing grpcTracing) {
        logger.info("Creating getAlbumGrpcChannel managed Channel. Host :: {} port :: {} ", host, port);
        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .intercept(grpcTracing.newClientInterceptor())
                .enableRetry()
                .build();
    }

    @Bean
    public ManagedChannel getTrackGrpcChannel(
            @Value("${service.track.host}") final String host,
            @Value("${service.track.port}") final Integer port,
            final GrpcTracing grpcTracing) {
        logger.info("Creating getTrackGrpcChannel managed Channel. Host :: {} port :: {} ", host, port);
        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .intercept(grpcTracing.newClientInterceptor())
                .enableRetry()
                .build();
    }

    @Bean
    public GrpcTracing grpcTracing(Tracing tracing) {
        return GrpcTracing.create(tracing);
    }
}
