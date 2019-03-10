package microservices.book.gateway.configuration;

import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;

public class RibbonConfiguration {
	
	@Bean
	public IPing ribbonPing(IClientConfig config) {
		/**
		 * Ping implementation if you want to do a "health check" kind of Ping. This
		 * will be a "real" ping. As in a real http/s call is made to this url e.g.
		 * http://ec2-75-101-231-85.compute-1.amazonaws.com:7101/cs/hostRunning
		 * 
		 * Some services/clients choose PingDiscovery - which is quick but is not a real
		 * ping. i.e It just asks discovery (eureka) in-memory cache if the server is present
		 * in its Roster PingUrl on the other hand, makes an actual call. This is more
		 * expensive - but its the "standard" way most VIPs and other services perform
		 * HealthChecks.
		 * 
		 * Choose your Ping based on your needs.
		 * 
		 * @author stonse
		 * 
		 *  
		 *  spring boot 2.0 actuator changes: the /info endpoint is now available at /application/info 
		 */
		return new PingUrl(false, "/application/health");
	}
	
	@Bean
	public IRule ribbonRule(IClientConfig config) {
		return new AvailabilityFilteringRule();
	}

}
