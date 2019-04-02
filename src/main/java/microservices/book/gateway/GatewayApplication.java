package microservices.book.gateway;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ConfigurableApplicationContext;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
//@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class GatewayApplication {

	private static final Logger log = LoggerFactory.getLogger(GatewayApplication.class);

	public static void main(String[] args) throws IOException {
		log.info("Preparing to run application");
		ConfigurableApplicationContext ctx = SpringApplication.run(GatewayApplication.class, args);
		String []beans = ctx.getBeanDefinitionNames();
		List<String> listOfBean = Arrays.stream(beans).sorted().collect(Collectors.toList());
		log.info("context beans...");
		listOfBean.forEach((e) -> log.info(e));
		log.info("context beans...end");
		log.info("\n{}\nContext is running...\nStarted @[{}] \nHit enter to stop server", ctx.getDisplayName(),
				Instant.ofEpochMilli(ctx.getStartupDate()).atZone(ZoneId.systemDefault()).toLocalDateTime());
		System.in.read();
		ctx.close();
	}
}
