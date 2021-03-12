package br.com.jfr.heroes.controller;

import br.com.jfr.heroes.model.Heroes;
import br.com.jfr.heroes.service.HeroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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
        /*
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }

}
