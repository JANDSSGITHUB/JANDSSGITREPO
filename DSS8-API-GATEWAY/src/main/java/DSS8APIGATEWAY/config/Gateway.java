package DSS8APIGATEWAY.config;

import DSS8APIGATEWAY.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Gateway {
    @Autowired
    JwtAuthenticationFilter jwtGatewayFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("dss5-actor", r -> r.path("/actor")
                        .filters(f -> f.filter(jwtGatewayFilter))
                        .uri("lb://dss5-actor"))
                .build();
    }
}
