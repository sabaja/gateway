package microservices.book.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 
 * @author sabaja
 *         https://javabeginnerstutorial.com/spring-boot/spring-boot-2-microservices-with-netflix-zuul-api-gateway/
 */
@Component
public class RequestFilter extends ZuulFilter {

	private static final Logger log = LoggerFactory.getLogger(RequestFilter.class);
	
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
	log.info("{} request ro {}",request.getMethod(), request.getRequestURL().toString());
		return null;
	}

}
