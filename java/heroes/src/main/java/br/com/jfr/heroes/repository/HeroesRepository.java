package br.com.jfr.heroes.repository;

import br.com.jfr.heroes.model.Heroes;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface HeroesRepository extends ReactiveMongoRepository<Heroes, String> {

}
