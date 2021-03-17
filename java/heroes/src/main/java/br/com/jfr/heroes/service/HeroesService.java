package br.com.jfr.heroes.service;

import br.com.jfr.heroes.model.Heroes;
import br.com.jfr.heroes.model.QHeroes;
import br.com.jfr.heroes.repository.HeroesRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Optional;

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

    public Mono<Heroes> findById(final String id) {

        final BooleanBuilder filtro = new BooleanBuilder();
        filtro.and(QHeroes.heroes.id.eq(id));

        return heroesRep.findOne(filtro)
                .switchIfEmpty(getError(String.format("id %s não cadastrado", id)));
    }

    public Mono<Heroes> save(Heroes entity) {
        return Mono.just(entity)
                .flatMap( e -> validaHeroes(e) )
                .flatMap( h -> {
                    String msg = String.format("%s já foi incluido no id %s", h.getName(), h.getId());
                    log.info("   >>>> save " + msg);
                    return getBadRequest(msg);
                })
                .switchIfEmpty( heroesRep.save(entity) )
                //.map(result -> ResponseEntity.status(HttpStatus.CREATED).body(result) )
                ;
    }

    public Mono<Heroes> validaHeroes(Heroes entity) {
        final BooleanBuilder filtro = new BooleanBuilder();
        filtro.and(QHeroes.heroes.name.eq(entity.getName()));
        return heroesRep.findAll(filtro)
                .take(1)
                .next()
                .log();
    }

    public Mono<Heroes> update(String id,Heroes heroes) {
        return findById(id)
                .map( e -> preencheEntidade(heroes,e) )
                .flatMap( heroesRep::save )
                ;
    }

    private Heroes preencheEntidade(final Heroes heroes, final Heroes entidade) {
        final Optional<Heroes> configOptional = Optional.of(heroes);
        configOptional.map(Heroes::getName).ifPresent(entidade::setName);
        configOptional.map(Heroes::getUniverse).ifPresent(entidade::setUniverse);
        configOptional.map(Heroes::getFilms).ifPresent(entidade::setFilms);
        return entidade;
    }

    public Mono<String> delete(String id) {
        return findById(id)
                .flatMap( e -> heroesRep.deleteById(e.getId()).thenReturn(e) )
                .map(e -> e.getId());
    }

    public Mono<Page<Heroes>> findByFilter(Pageable pageable, Heroes filter) {
        return heroesRep.count()
                .flatMap(heroesCount -> heroesRep.findAll(criarCriterio(filter))
                        .skip(pageable.getPageSize() * pageable.getPageNumber())
                        .take(pageable.getPageSize())
                        .buffer()
                        .elementAt(0, new ArrayList<>())
                        .map(heroes -> new PageImpl<>(heroes, pageable, heroesCount)));
    }

    private BooleanBuilder criarCriterio(final Heroes filter) {
        final BooleanBuilder booleanBuilder = new BooleanBuilder();

        final Optional<Heroes> heroes = Optional.of(filter);

        final QHeroes qHeroes = QHeroes.heroes;
        heroes.map(Heroes::getId).ifPresent(id -> booleanBuilder.and(qHeroes.id.eq(id)));
        heroes.map(Heroes::getName).ifPresent(name -> booleanBuilder.and(qHeroes.name.contains(name)));
        heroes.map(Heroes::getUniverse).ifPresent(universe -> booleanBuilder.and(qHeroes.universe.contains(universe)));
        heroes.map(Heroes::getFilms).ifPresent(films -> booleanBuilder.and(qHeroes.films.eq(films)));

        return booleanBuilder;
    }

    private Mono<Heroes> getBadRequest(final String msg) {
        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, msg));
    }

    private Mono<Heroes> getError(final String msg) {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, msg));
    }

}
