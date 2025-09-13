# Java App Migration: Part 4 - Hibernate APP

Author: Mahdi Aouchiche (<https://github.com/mahdi-aouchiche/Java-App-Migration-4_HIBERNATE-APP>)

## Overview

This Java App Migration Part 4 show cases the migration of an app from raw JDBC to Hibernate which uses an Object-Relational Mapping (ORM) approach.

The Java Web Application is an example to manage employees in a company with different department.

## Major Advantages

* Hibernate allows developers to interact with the database using Java objects instead of writing raw SQL queries. This aligns with object-oriented programming principles, making code more intuitive and easier to manage by abstracting away database-specific SQL dialects. Developers write queries in Hibernate Query Language (HQL) or use the Criteria API, and Hibernate translates these into the appropriate SQL for the underlying database. This makes applications more portable and easier to migrate between different database systems without extensive code changes.

* Hibernate incorporates built-in caching mechanisms (first-level and second-level caches) that can significantly improve application performance by reducing the number of database round trips. JDBC does not offer such caching out of the box, requiring developers to implement custom caching solutions.

## App Implementation

* Apache Maven build project using 'pom.xml' for configuration and dependencies.
* Showcases a many to many relationship between the Employee Entity (the owning side) and the Department Entity (the inverse side).
* Implement all CRUD operations (Create, Read, Update, Delete) to show how hibernate significantly reduces code boilerplate compared to raw JDBC by abstracting away many low-level details of database interaction.
* Enabled specific logging configurations in the Hibernate application to observe Hibernate queries in the console when accessing the database and when data is retrieved from the cache.
* The application implements the MVC (or MVCS) architecture design.
  * Controller layer: Servlet to handle HTTP requests and responses.
  * View layer: JSP, HTML, CSS.
  * Model layer: Hibernate, MySQL database.
  * Service layer: Business Logic.

Note: Login info to run the app: username = Admin, password = 123

