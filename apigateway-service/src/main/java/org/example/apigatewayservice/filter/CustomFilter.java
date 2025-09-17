package org.example.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(final Config config) {

        return ((exchange, chain) -> {

            final ServerHttpRequest request = exchange.getRequest();
            final ServerHttpResponse response = exchange.getResponse();

            // Custom Pre Filter
            log.info("Custom PRE Filter: request id -> {}", request.getId());

            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        // Custom Post Filter
                        log.info("Custom POST Filter: response code -> {}", response.getStatusCode());
                    }));
        });
    }

    public static class Config {
        // TODO: yml 설정 파일과 연동하여 동적으로 설정 값을 가져와 로깅 가능
    }
}
