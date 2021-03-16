package br.com.jfr.heroes;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HeroesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeroesApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
		return new OpenAPI().info(new Info()
				.title("Heroes API")
				.version(appVersion)
				.description("API reativa acessando banco de dados mongo")
				.termsOfService("http://swagger.io/terms/")
				.license(new License().name("Apache 2.0")
						.url("http://springdoc.org")));
	}
}
