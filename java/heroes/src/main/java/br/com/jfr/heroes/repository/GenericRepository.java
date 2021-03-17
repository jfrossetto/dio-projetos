package br.com.jfr.heroes.repository;

import br.com.jfr.heroes.model.BaseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenericRepository <T extends BaseEntity> extends ReactiveMongoRepository<T, String>, ReactiveQuerydslPredicateExecutor<T> {

    Mono<T> findById (String id);

    @Query("{ id: { $exists: true }}")
    Flux<T> findAll();

    @Query("{ id: { $exists: true }}")
    Flux<T> findByFilter (final Pageable page, T filter);

}
