# Projeto Heroes
## API Reativa usando Spring WebFlux e MongoDB
### Diferente do projeto original: 
* Ao invés de usar um banco dynamoDB foi utilizado mongoDB na nuvem
* Utilizado querydsl nas querys e utilizado principalmente no find-by-filter 
* Tratamento das exceptions em GlobalErrorWebExceptionHandler
* Validacao para não duplicar nome do heroi e não incluir com name vazio
* Endpoints findById , findAll , save , update , delete , find-by-filter
* swagger: http://localhost:8080/swagger-ui-heroes-reactive-api.html

####links de apoio:

* https://projectreactor.io/learn
* https://www.baeldung.com/querydsl-with-jpa-tutorial
* https://developer.okta.com/blog/2018/09/21/reactive-programming-with-spring
* https://medium.com/faun/spring-mongodb-reactive-programming-case-study-c2077169aeec