# B.F.F. (Back for front)

GitBook documentation :  [https://app.gitbook.com/o/pnEhpI29S43xX2hCVVtE/s/dAPGj3iELFyzrjbs0zuI/](https://app.gitbook.com/o/pnEhpI29S43xX2hCVVtE/s/dAPGj3iELFyzrjbs0zuI/)

## Spring initializer

This project was created with the [Spring initializer](https://start.spring.io/).

## Kotlin

<img src="./docs/images/kotlin.png" height="100" />

We decided to use Kotlin language for this project.

Official Kotlin website is [kotlinlang.org](https://kotlinlang.org/).

To learn Kotlin try this playground [play.kotlinlang.org](https://play.kotlinlang.org/).

## Spring Boot

<img src="./docs/images/spring-boot.png" height="100" />

Spring Boot is a Java-based framework for building Spring-based applications.

More information about Spring Boot can be found on [spring.io](https://spring.io/).


## Onion Architecture

This project is built with the Onion Architecture pattern.

```mermaid
sequenceDiagram
    participant presentation
    participant application
    participant domain

    presentation->>application: consumer action
    application->>domain: call business logic
    activate domain
    domain-->>application: return data
    deactivate domain
    application-->>presentation: return response
```

## Maven

<img src="./docs/images/maven.png" height="100" />

We use Maven for building this project and manage packaging, dependencies and so on.
