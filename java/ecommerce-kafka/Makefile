# defaul shell
SHELL = /bin/bash

# Rule "help"
.PHONY: help
.SILENT: help
help:
	echo "Use make [param]"
	echo "param:"
	echo ""
	echo "start-dev  	 - inicia docker-compose para ambiente de dev (postgre/kafka/zookeeper/schema-registry)"
	echo "stop-dev  	 - para docker-compose para ambiente de dev (postgre/kafka/zookeeper/schema-registry)"	
	echo ""
	echo "build-checkout - constroi checkout"
	echo "build-payment  - constroi payment"
	echo "build-images   - constroi docker images checkout e payment"
	echo "push-images    - sobe docker images checkout e payment para docker hub"
	echo ""
	echo "help		     - show this message"

start-dev:
	docker-compose -f checkout/docker/dev-docker-compose.yml up -d

stop-dev:
	docker-compose -f checkout/docker/dev-docker-compose.yml down

status-dev:
	docker-compose -f checkout/docker/dev-docker-compose.yml ps

build-checkout:
	cd checkout; \
	./gradlew clean build -x test; \
	ls -l build/libs/*.jar; \
	cd ..

run-checkout: 
	java -jar checkout/build/libs/checkout-0.0.1-SNAPSHOT.jar &

build-payment:
	cd payment; \
	./gradlew clean build -x test; \
	ls -l build/libs/*.jar; \
	cd ..

run-payment: 
	java -jar payment/build/libs/payment-0.0.1-SNAPSHOT.jar &

build-images:
	cd checkout; \
	docker build -f Dockerfile --force-rm -t jfrossetto/dio-checkout-api . 
	cd payment; \
	docker build -f Dockerfile --force-rm -t jfrossetto/dio-payment-api .	

push-images:
	docker login
	docker push jfrossetto/dio-checkout-api:latest

start-app:
	docker-compose -f docker-compose.yml up -d

