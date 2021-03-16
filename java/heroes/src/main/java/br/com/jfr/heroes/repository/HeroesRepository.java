package br.com.jfr.heroes.repository;

import br.com.jfr.heroes.model.Heroes;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;

public interface HeroesRepository extends ReactiveMongoRepository<Heroes, String>, ReactiveQuerydslPredicateExecutor<Heroes> {

}
