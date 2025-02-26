# Spring AOP Demo Project

This project demonstrates the implementation of Aspect-Oriented Programming (AOP) in Spring, showcasing various aspects of logging, authentication, and performance monitoring.

## Features

- Method execution logging
- Authentication and authorization checks
- Performance monitoring using @Around advice
- Exception handling with @AfterThrowing
- Shopping cart operations with cross-cutting concerns

## Project Structure

```
src/main/java/org/example/
├── Main.java                 # Application entry point
└── aspects/
|   ├── LoggingAspect.java        # Logging concerns
|   └── AuthenticationAspect.java # Security concerns
├── logic/
|   └── ShoppingCard.java         # Core business logic
└── config/
    └── BeanConfig.java      # Spring configuration
```

## Aspect Implementations

### Logging Aspect
- Before method execution logging
- After method execution logging
- Method execution time measurement
- Exception logging
- Return value logging

### Authentication Aspect
- Role-based access control
- User authentication
- Operation authorization
- Security logging

## Usage Examples

```java
// Initialize Spring context
ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
ShoppingCard card = context.getBean(ShoppingCard.class);

// Perform operations
card.checkout("VALID");
double total = card.calculateTotal(29.99, 3);
card.updateInventory("PROD-001", 50);
```

## Requirements

- Java 23
- Spring Framework 6.2.3
- AspectJ 1.9.22
- Maven


## Key Points

- Uses Spring's @Aspect annotation for aspect definitions
- Demonstrates various AOP advice types (@Before, @After, @Around, etc.)
- Implements cross-cutting concerns separation
- Shows proper exception handling
- Includes performance monitoring

## Learning Outcomes

- Understanding AOP concepts
- Implementing cross-cutting concerns
- Working with Spring AOP
- Managing aspect execution order
- Handling method interception
