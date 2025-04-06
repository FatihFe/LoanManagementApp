### Project Overview

This project is a loan management application built with **Jetpack Compose**, following the **MVVM** (Model-View-ViewModel) architecture and **Clean Architecture** principles. The app allows users to log in, view a list of loans, and interact with them based on different strategies for interest calculation and loan updates. The **Strategy Pattern** is used to manage different loan types, such as Personal Loans, Mortgage Loans, and Car Loans, each with specific logic for updates.

The project leverages **Hilt** for dependency injection, ensuring a clean and modular structure where components are separated and easily testable.

---

### Architecture

- **MVVM (Model-View-ViewModel)**: The ViewModel acts as a bridge between the UI and business logic, managing UI-related data and responding to user interactions while maintaining separation of concerns.
  
- **Clean Architecture**: The project follows Clean Architecture by dividing the app into distinct layers:
  - **Presentation Layer**: Contains the UI components built with Jetpack Compose.
  - **Domain Layer**: Holds the business logic, such as use cases that update loans based on different strategies.
  - **Data Layer**: Handles the data fetching and saving operations, including repositories and services.

- **Dependency Injection**: **Hilt** is used to manage dependencies across different layers of the application, such as injecting repositories, view models, and use cases into the UI components.

---

### Key Patterns Implemented

#### Strategy Pattern
The **Strategy Pattern** is applied to handle different types of loans, each with specific rules for interest rate updates and loan status changes. The pattern allows for interchangeable loan strategies that are determined based on the loan type.

- A factory is used to select the correct loan strategy based on the loan type, ensuring that the appropriate strategy is applied to each loan.

#### MVVM (Model-View-ViewModel)
The **MVVM** architecture ensures that the UI (View) is decoupled from the business logic (Model). The ViewModel handles user interactions and updates the UI, keeping the business logic separate in the domain layer.

- **ViewModels** manage the state and provide data to the UI. They communicate with use cases to fetch or update data.
  
#### Clean Architecture
- The app is divided into layers to maintain separation of concerns:
  - **Use Case Layer**: Contains the core business logic, such as updating loans based on the selected strategy.
  - **Repository Layer**: Manages data operations, abstracting the details of how data is fetched or saved.

---

### Components and Screens

- **Login Screen**: A simple login form that collects the username and password. Upon successful login, users are navigated to the home screen.
  
- **Home Screen**: Displays a list of loans, showing details such as loan status, principal amount, and interest. The loans are fetched using a ViewModel and displayed using a `LazyColumn`.

- **Loan Item**: A component that shows individual loan details. It is used in the home screen to display each loan in the list.

---

### Unit Tests

Unit tests are implemented to ensure the core logic works correctly. These tests cover:

- **ViewModels**: Ensuring user interactions like logging in and updating loans trigger the expected logic.
- **Use Cases**: Testing the business logic, such as updating loans based on the selected strategy.
- **Repositories**: Ensuring data fetching and saving operations are handled correctly.
- **Loan Strategies**: Testing each loan strategy to verify that the loan update logic works under different conditions.

---

### Setup & Dependencies

- **Jetpack Compose**: Used for building modern UI components.
- **Hilt**: Used for Dependency Injection to manage components and services.
- **Kotlin Coroutines**: Used for handling asynchronous operations.
- **JUnit** and **Mockito**: Used for unit testing the different components and layers.

---

### Conclusion

This project demonstrates the use of **MVVM**, **Clean Architecture**, and the **Strategy Pattern** to build a modular, scalable, and maintainable loan management application. The architecture ensures that each layer has a clear responsibility, making the codebase easy to extend and maintain.
