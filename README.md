## Project goal

Design and implement a REST API to return a feed of "cards" - a simple JSON representation of the payload is sufficient
1. Cards can be of different types and include different values. Please implement the below logic:
    1. Daily quote card - will show a different quote every day. the list of quotes will be stored in the database or a file for this exercise. The card will include:
        1. Title - "Daily Quote"
        2. Message - the quote content. You can use "quote1", "quote2", "quote3" for the exercise.
        3. Author - the author of the daily quote. You can use "author1", "author2", etc.
    2. Status update card - will be used to show updates to customers. This card will include:
        1. Title - String - "Important Security Update"
        2. Icon - String - e.g. https://tinyurl.com/y5mdph2g
        3. Message - String - e.g. "For your security, we support 2 factor authentication, and would recommend that you turn it on."
        4. Button - String - e.g. "Got it, thanks"
2. Each card type can have multiple implementations
    1. Each implementation will allow for a pre-defined condition to determine whether the card should be presented to a given customer. 
    E.g. if the number of seconds on the request timestamp is even, show. Else - do not show the card to this customer.


## Starting application

1. You will need docker installed on your machine
2. In root folder of the project run: `docker-compose -f ./src/main/docker/docker-compose.yml up`
3. Wait for the Mysql docker container to start
4. Open new terminal window and in the root project folder run: `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev`
5. If you don't want to load test data run `./mvnw spring-boot:run` instead of the command above
6. Wait for the app to start
7. You can access REST API documentation at `localhost:8080/swagger-ui/index.html`
8. Endpoint returning feed of cards is `/api/cards/feed`
9. REST API is also including basic CRUD endpoints for both daily quote and status update cards


### Alternative solution

There is also alternative solution implemented. You can find it on `feature/cards-dtos` branch.
What's different?
1. Entities are Status and Quote instead of Cards.
2. Cards are essentially DTO's created from entities
3. Checking each card implementation is done in entity service vs run from one place
4. This solution is missing tests.
