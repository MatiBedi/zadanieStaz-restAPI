package eti.pg.edu.pl.laborka3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class Laborka3Application {

	public static void main(String[] args) {
		SpringApplication.run(Laborka3Application.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route("lectures", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/lectures/{id}", "/api/lectures")
						.uri("http://localhost:8081"))
				.route("users", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/users", "/api/users/{id}")
						.uri("http://localhost:8082"))
				.route("reservations", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/reservations", "/api/reservations/**",
								"/api/lectures/{id}/reservations", "/api/lectures/{id}/reservations/**",
								"/api/users/{id}/lectures", "/api/users/{id}/lectures/**",
								"api/users/{id}/reservations", "/api/users/{id}/reservations/**",
								"/api/admin", "/api/admin/**")
						.uri("http://localhost:8083"))
				.build();
	}

	@Bean
	public CorsWebFilter corsWebFilter() {
		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
		corsConfig.addAllowedHeader("*");

		final org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}

}
