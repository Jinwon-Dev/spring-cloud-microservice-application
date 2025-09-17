package org.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(final Config config) {

        return (exchange, chain) -> { // exchange -> 요청과 응답에 대한 모든 정보를 담고 있는 객체

            final ServerHttpRequest request = exchange.getRequest();
            final ServerHttpResponse response = exchange.getResponse();

            log.info("Logging Filter baseMessage: {}, {}", config.getBaseMessage(), request.getRemoteAddress());

            if (config.isPreLogger()) {
                log.info("Logging Filter Start: request uri -> {}", request.getURI());
            }

            return chain.filter(exchange) // 요청을 다음 필터로 전달
                    .then(Mono.fromRunnable(() -> { // 체인의 모든 동작이 완료된 후 실행
                        if (config.isPostLogger()) {
                            log.info("Logging Filter End: response code -> {}", response.getStatusCode());
                        }
                    }));
        };
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
