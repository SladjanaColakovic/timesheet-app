# Timesheet App
This is a **Spring Boot** application designed to track the work hours of employees, with added features for **Category**, **Client**, and **Project** management. It includes user **Authentication and Authorization** to protect sensitive endpoints. The app allows you to log work entries, view hours worked, and manage employee records. The application is containerized using **Docker and Docker Compose** for easy setup and deployment, with **PostgreSQL** used as the database.
# Features
- Employee Management: Add, update, and delete employee records.
- Work Hours Tracking: Record work start and end times, calculate total hours worked.
- Category Management: Categorize work entries (e.g., development, design, admin tasks).
- Client Management: Manage client records (e.g., client name, contact info, and related projects).
- Project Management: Manage projects associated with clients and employees.
- Authentication and Authorization: Role-based access control for managing users and protecting sensitive data.
- Reports: View work hours reports for individual employees, including work hours by category, client, and project.
- REST API: Provides endpoints for managing employees, work hours, categories, clients, projects, and user authentication.
# Technologies Used
- Spring Boot: For building the backend application.
- Spring Security: For authentication and authorization.
- PostgreSQL: Relational database used for storing employee, client, project, and work hour data.
- Docker: Containerize the application for easy deployment.
- Docker Compose: Used to define and manage multi-container Docker applications.
- JWT (JSON Web Token): For user authentication and secure API access.
# Prerequisites
Before running this application, make sure you have the following installed on your machine:

- Docker
- Docker Compose
- Java (JDK 11 or above)
# Getting Started
Follow these steps to run the application locally using Docker Compose.
1. Clone the repository
    https://github.com/SladjanaColakovic/timesheet-app.git
2. Dockerize the application
    This project uses Docker and Docker Compose for containerization.
    Use the following command to rebuild the images and start the containers:
    docker-compose up --build
    **Starting Containers if They Already Exist**
        docker-compose start
3. Access the Application
    Once the containers are up and running, the Spring Boot application will be available at http://localhost:8080.