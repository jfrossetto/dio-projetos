package br.com.jfr.heroes.service;

import br.com.jfr.heroes.model.Heroes;
import br.com.jfr.heroes.repository.HeroesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class HeroesService {

    private final HeroesRepository heroesRep;

    @Autowired
    public HeroesService(final HeroesRepository heroesRep) {
        this.heroesRep = heroesRep;
    }

    public Flux<Heroes> findAll() {
        log.info(" ... findAll ");
        return heroesRep.findAll();
    }

}
