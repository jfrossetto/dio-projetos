package br.com.jfr.heroes.controller;


import br.com.jfr.heroes.model.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public interface GenericController <T extends BaseEntity> {

    ResponseEntity<Mono<T>> findById (String id);
    Mono<Page<T>> findByFilter(final int page, @Min(0) @Max(1000) final int size, T filter);
    ResponseEntity<Mono<T>> save (T entity);
    ResponseEntity<Mono<T>> update(T entity, String id);
    ResponseEntity<Mono<String>> delete(String id);
}
