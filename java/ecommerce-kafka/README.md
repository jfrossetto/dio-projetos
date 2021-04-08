# E-Commerce com microserviços 
##  (Apache kafka, Spring Boot, Spring Cloud Streams, docker)

uso do Makefile para :
```
help:
	echo "Uso make [param]"
	echo "param:"
	echo ""
	echo "start-dev  	  - inicia docker-compose para ambiente de dev (postgre/kafka/zookeeper/schema-registry)"
	echo "stop-dev  	  - para docker-compose para ambiente de dev (postgre/kafka/zookeeper/schema-registry)"	
	echo ""
	echo "build-checkout  - constroi checkout"
	echo "build-payment   - constroi payment"
	echo "build-images    - constroi docker images checkout e payment"
	echo "push-images     - sobe docker images checkout e payment para docker hub"
	echo ""
	echo "start-app  	  - inicia docker-compose da solução completa (postgres,kafka,checkout e payment)
	echo "stop-app    	  - para docker-compose da solução
	echo ""
	echo "create-checkout - cria e envia um checkout (via curl ...)
	echo ""
	echo "help		      - show this message"
```

Para enviar um checkout para checkout-api 
```
    make create-checkout
```

Para verificar o checkout enviado e o payment gerado e retornado
```
    http://localhost:5000/v1/checkout/all

    http://localhost:5002/v1/payment/all

```
