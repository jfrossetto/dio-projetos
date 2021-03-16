package br.com.jfr.heroes.controller;

import br.com.jfr.heroes.model.Heroes;
import br.com.jfr.heroes.service.HeroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/heroes")
public class HeroesController {

    private final HeroesService heroesService;

    @Autowired
    public HeroesController(final HeroesService heroesService) {
        this.heroesService = heroesService;
    }

    @GetMapping
    public Flux<Heroes> findAll() {
        return heroesService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Heroes>> findById(@PathVariable("id") final String id) {
        return ResponseEntity.ok(heroesService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Mono<Heroes>> save(@Valid @RequestBody final Heroes entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body( heroesService.save(entity) );
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Mono<Heroes>> update(@RequestBody final Heroes entity, @PathVariable("id") final String id) {
        return ResponseEntity.ok( heroesService.update(id,entity) );
    }


    // todo delete findByFilter test refactor generics

}
