# Bati-Cuisine

Bati-Cuisine is a Java-based desktop application designed for professionals in kitchen construction and renovation. The system allows users to manage clients, create project quotes (devis), handle materials, and calculate total project costs, including labor, taxes, and profit margins.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Installation and Setup](#installation-and-setup)
- [Docker Setup](#docker-setup)


## Features

- **Client Management**: Add, and list client details.
- **Devis Creation**: Generate project quotes including materials and labor.
- **Material Management**: Add, and list materials.
- **Cost Calculation**: Automatically calculate the total cost of the project, considering labor, materials, VAT, transport, and quality coefficients.
- **Multi-layer Architecture**: The application is built with a well-structured multi-layered architecture.

## Technologies Used

- **Java**: Core programming language for the backend logic.
- **JDBC**: Used for database interaction.
- **PostgreSQL**: Relational database to store client, project, and material data.
- **Java Time API**: To handle project-related dates.
- **Singleton Pattern**: Used for managing database connections.
- **Repository Pattern**: Abstracts database access for entities like Client, Devis, Materiau, etc.
- **Git**: Version control system.
- **Docker**: For setting up the PostgreSQL database.

## Project Structure
C:.
├───.idea
│   ├───dataSources
│   │   └───c1a09113-b185-4f46-9ae6-e05b19e6f319
│   │       └───storage_v2
│   │           └───_src_
│   │               └───database
│   │                   └───batiCuisine.sAOk_w
│   │                       └───schema
│   └───shelf
│       └───Uncommitted_changes_before_Update_at_20_09_2024_02_22_[Changes]
├───out
│   └───production
│       └───Bati-Cuisine
│           └───main
│               ├───bati
│               │   ├───config
│               │   ├───enumeration
│               │   ├───model
│               │   ├───repository
│               │   │   ├───Client
│               │   │   ├───Composant
│               │   │   ├───Devis
│               │   │   ├───MainDoeuvre
│               │   │   ├───Materiau
│               │   │   └───Project
│               │   ├───service
│               │   ├───util
│               │   └───view
│               └───resources
└───src
└───main
├───bati
│   ├───config
│   ├───enumeration
│   ├───exeption
│   ├───model
│   ├───repository
│   │   ├───Client
│   │   ├───Composant
│   │   ├───Devis
│   │   ├───MainDoeuvre
│   │   ├───Materiau
│   │   └───Project
│   ├───service
│   ├───util
│   └───view
└───resources



## Installation and Setup

### Steps

1. **Open the project** in IntelliJ IDEA.

2. **Set up the database** by following the [Database Configuration](#database-configuration) instructions below.

3. **Run the project**:
   - In IntelliJ, go to `Run` > `Edit Configurations...` and set the main class to `Main.java`.
   - Click the `Run` button to start the application.

## Database Configuration

1. Ensure you have PostgreSQL installed and running.

2. Create a PostgreSQL database:

   ```sql
   CREATE DATABASE public;

