build:
	./gradlew build

docker:
	docker-compose up --build

run: build docker