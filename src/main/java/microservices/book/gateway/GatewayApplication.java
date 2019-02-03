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

@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {

	private static final Logger log = LoggerFactory.getLogger(GatewayApplication.class);

	public static void main(String[] args) throws IOException {
		log.info("Preparing to run application");
		ConfigurableApplicationContext ctx = SpringApplication.run(GatewayApplication.class, args);
		log.info("Contex is running @{}\nHit enter to stop server",
				Instant.ofEpochMilli(ctx.getStartupDate()).atZone(ZoneId.systemDefault()).toLocalDateTime());
		System.in.read();
		ctx.close();
	}

}
