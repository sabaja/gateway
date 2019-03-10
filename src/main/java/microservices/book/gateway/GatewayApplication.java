package microservices.book.gateway;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ConfigurableApplicationContext;

import microservices.book.gateway.configuration.RibbonConfiguration;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
//@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
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
}
