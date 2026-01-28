# HibernateConsoleApp

> A console application for managing customers, their orders, and coupons using Hibernate.


---

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Installation](#installation)

---

## Overview
This repository contains a console-based Java application designed to manage customers, coupons, and orders. It utilizes Hibernate for ORM to handle database interactions efficiently. The project demonstrates CRUD operations on entities like customers, coupons, and orders, showcasing relational data management in a simple educational or prototype setting.

---

## Features
- Create, read, update, and delete customers.
- Manage coupons, including creation, assignment, and validation.
- Handle orders, linking them to customers and applying coupons.
- Basic validation for data integrity (e.g., coupon expiration, order status).
- Console interface for user interactions.
- Database persistence using Hibernate for seamless entity management.
---

## Technologies
- **Java** 17 
- **Maven** 3.6+
- **Hibernate** 7.1.4
- **Spring Framework** 7.0
- **PostgreSQL** 

---

## Prerequisites
- JDK 17+
- Maven 3.6+
- Git 
- PostgreSQL 13+ 
---

## Installation

```bash
#1) Clone the repository
git clone https://github.com/RubinAlmazov/HibernateConsoleApp.git
cd HibernateConsoleApp

#2) or with the system Maven
mvn clean package

#3) Run the application
java -jar target/your-jar-file.jar
