package br.com.jfr.heroes;

import br.com.jfr.heroes.repository.HeroesRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroesApplicationTests {


	private static final String HEROES_ENDPOINT_LOCAL = "http://localhost:8080/heroes";

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	HeroesRepository heroesRepository;


	@Test
	public void getOneHeroeById() {

		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "60517824f17f5c19d33152fa")
				.exchange()
				.expectStatus().isOk()
				.expectBody();


	}

	@Test
	public void getOneHeronotFound() {

		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "xxx")
				.exchange()
				.expectStatus().isNotFound();

	}

}
