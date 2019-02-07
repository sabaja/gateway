package microservices.book.gateway;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {

	private static final Logger log = LoggerFactory.getLogger(GatewayApplication.class);

	public static void main(String[] args) throws IOException {
		log.info("Preparing to run application");
		ConfigurableApplicationContext ctx = SpringApplication.run(GatewayApplication.class, args);
		log.info("profiles: {}", (Object) ctx.getEnvironment().getDefaultProfiles());
		log.info("\n{}\nContext is running...\nStarted @[{}] \nHit enter to stop server", ctx.getDisplayName(),
				Instant.ofEpochMilli(ctx.getStartupDate()).atZone(ZoneId.systemDefault()).toLocalDateTime());
		System.in.read();
		ctx.close();
	}

//	@Bean
//	public CorsFilter corsFilter() {
//	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    final CorsConfiguration config = new CorsConfiguration();
//	    config.setAllowCredentials(true);
//	    config.addAllowedOrigin("*");
//	    config.addAllowedHeader("*");
//	    config.addAllowedMethod("OPTIONS");
//	    config.addAllowedMethod("HEAD");
//	    config.addAllowedMethod("GET");
//	    config.addAllowedMethod("PUT");
//	    config.addAllowedMethod("POST");
//	    config.addAllowedMethod("DELETE");
//	    config.addAllowedMethod("PATCH");
//	    source.registerCorsConfiguration("/**", config);
//	    return new CorsFilter(source);
//	}
}
