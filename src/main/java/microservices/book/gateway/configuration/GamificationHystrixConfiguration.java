package microservices.book.gateway.configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

/**
 * https://spring.io/blog/2018/06/19/spring-cloud-finchley-release-is-available
 * ZuulFallbackProvider has been removed and replaced with FallbackProvider
 * #2262
 * 
 * @author sabaja
 *
 */
@Configuration
public class GamificationHystrixConfiguration {

	@Bean(name="gamificationFallBackProvider")
	public FallbackProvider gamificationFallBackProvider() {

		return new FallbackProvider() {
			@Override
			public String getRoute() {
				return "gamification";
			}

			@Override
			public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
				return new ClientHttpResponse() {

					@Override
					public InputStream getBody() throws IOException {

						return new ByteArrayInputStream(
								"Sorry, Gamification service is Down!\nPlease contact the Administrator Service"
										.getBytes(StandardCharsets.UTF_8));
					}

					@Override
					public HttpHeaders getHeaders() {
						HttpHeaders httpHeaders = new HttpHeaders();
						httpHeaders.setContentType(MediaType.APPLICATION_JSON);
						httpHeaders.setAccessControlAllowCredentials(true);
						httpHeaders.setAccessControlAllowOrigin("*");
						return httpHeaders;
					}

					@Override
					public HttpStatus getStatusCode() throws IOException {
						return HttpStatus.OK;
					}

					@Override
					public int getRawStatusCode() throws IOException {
						return HttpStatus.OK.value();
					}

					@Override
					public String getStatusText() throws IOException {
						return HttpStatus.OK.toString();
					}

					@Override
					public void close() {}

				};
			}
		};
	}
}