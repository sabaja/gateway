package microservices.book.gateway.configuration;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * Customizing the Ribbon Client
 * http://cloud.spring.io/spring-cloud-static/Greenwich.RELEASE/multi/multi_spring-cloud-ribbon.html
 * @author sabaja
 *
 */
@Configuration
@RibbonClient(name = "ribbonConfiguration", configuration = RibbonConfiguration.class)
public class Config {
}
