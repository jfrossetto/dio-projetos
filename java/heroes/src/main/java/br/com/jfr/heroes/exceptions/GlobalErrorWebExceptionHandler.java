package br.com.jfr.heroes.exceptions;

import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

@Component
@Order(-2)
@Slf4j
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    public GlobalErrorWebExceptionHandler(GlobalErrorAttributes g, ApplicationContext applicationContext,
                                          ServerCodecConfigurer serverCodecConfigurer) {
        super(g, new WebProperties.Resources(), applicationContext);
        super.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(final ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {

        final String query = request.uri().getQuery();
        final boolean traceEnabled = isTraceEnabled(query);
        final ErrorAttributeOptions errorAttributeOptions = traceEnabled ? ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE) :
                ErrorAttributeOptions.defaults();

        final Map<String, Object> errorAttributesMap = getErrorAttributes(request, errorAttributeOptions);
        final int status = (int) Optional.ofNullable(errorAttributesMap.get("status")).orElse(500);
        final Mono<ServerResponse> serverResponseMono = ServerResponse
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorAttributesMap));

        serverResponseMono.subscribe(serverResponse -> {
            if (traceEnabled) {
                logError(request, serverResponse, getError(request));
            }
            log.error(getError(request).getMessage());
        });
        return serverResponseMono;
    }

    private boolean isTraceEnabled(final String query) {
        return  query != null && !query.isEmpty() && query.contains("trace=true");
    }
}