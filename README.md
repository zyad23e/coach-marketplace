# Coach Marketplace Web Application

A full-stack web application that allows coaches to create a public profile and publish multiple coaching services that anyone can browse.

Built to demonstrate backend-focused development with Spring Boot, secure authentication, relational data modeling, and server-side rendered views.

## Features

- User registration and secure login using Spring Security
- Authenticated users can create a single coach profile
- Coaches can publish, edit, and delete multiple coaching services
- Public browsing page to view all coaches
- Individual coach pages displaying profile info and all their services
- Optional public contact email and phone number for each coach
- Profile image upload with server-side file storage and static serving

## Tech Stack

- Java 25
- Spring Boot
- Spring Security
- Spring MVC + Thymeleaf
- Spring Data JPA / Hibernate
- MySQL
- Multipart file upload (profile images)

## Architecture Overview

- Relational persistence with foreign keys linking:
  - users → coach profiles → coaching services
- Server-side MVC pattern:
  - Controllers handle requests and routing
  - Repositories manage database access
  - Thymeleaf templates render dynamic HTML views
- Role-protected `/coach/**` routes for coach-only actions
- Public routes for browsing coaches and viewing profiles

## Key Endpoints

| Endpoint                | Description                                  |
|-------------------------|----------------------------------------------|
| `/register`              | User registration                            |
| `/login`                 | User login                                   |
| `/coaches`               | Public list of all coaches                    |
| `/coach/profile/{id}`    | Public coach profile + their services         |
| `/coach/dashboard`       | Coach management dashboard (auth required)    |
| `/coach/services/new`    | Create a new coaching service (auth required) |
| `/coach/services`        | List/edit/delete coach’s own services         |

## File Uploads

Coach profile pictures are uploaded via multipart form data, saved to a local `uploads/` directory, and served through a static resource mapping.

## Security

- Form-based authentication with BCrypt password hashing
- Role-based authorization to ensure coaches can only manage their own data
- Public read-only access for browsing coaches and services

## Future Improvements

- Search and filtering by coaching category
- Messaging/contact form instead of exposing raw contact info
- Cloud storage for profile images
