# PhaseZero - Catalog Service

A simple Spring Boot REST API to manage a product catalog (built for the PhaseZero Java Trainee Assignment).

---

## ⭐ Overview

This is a lightweight in-memory product catalog service that supports:

- **Create Product** — `POST /products`
- **List All Products** — `GET /products`
- **Get Product by Part Number** — `GET /products/{partNumber}`

No external database is required. The application uses an **in-memory repository** and resets on each restart.

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot 4.0
- Spring Web MVC
- Maven
- H2 Database (Console included but not used for persistence)

---

src/
└── main/
├── java/com/phasezero/catalog/
│ ├── controller/ # REST controllers
│ ├── service/ # Business logic
│ ├── repository/ # In-memory repository
│ ├── model/ # Product model
│ ├── exception/ # Custom exceptions + global error handler
│ └── PhasezeroCatalogServiceApplication.java
└── resources/
└── application.properties

pom.xml

## 📁 Project Structure


---

## 📌 Product Model Fields

| Field       | Type    | Description                    |
|-------------|---------|--------------------------------|
| partNumber  | String  | Unique product identifier      |
| partName    | String  | Human-readable name            |
| category    | String  | Product category               |
| price       | double  | Product price                  |
| stock       | int     | Available quantity             |

---

# 🚀 API Endpoints

---

## 1️⃣ Create Product  
### `POST /products`

**Headers:**

**Example Request Body:**
```json
{
  "partNumber": "PN-101",
  "partName": "Hydraulic Pump",
  "category": "Hydraulics",
  "price": 4999.99,
  "stock": 5
}
```
## Possible Responses:

| Status          | Meaning                   |
| --------------- | ------------------------- |
| 201 Created     | Product saved             |
| 400 Bad Request | Validation failed         |
| 409 Conflict    | partNumber already exists |

## 2️⃣ List All Products
GET /products

Example Response:
```
[
  {
    "partNumber": "PN-101",
    "partName": "Hydraulic Pump",
    "category": "Hydraulics",
    "price": 4999.99,
    "stock": 5
  }
]
```
## 3️⃣ Get Product by Part Number
GET /products/{partNumber}

Example:

GET /products/PN-101

## Possible Responses:
| Status        | Meaning         |
| ------------- | --------------- |
| 200 OK        | Product found   |
| 404 Not Found | Product missing |

## 🧪 Validation Rules

* partNumber is required

* partName is required

* category is required

price >= 0

stock >= 0

Duplicate partNumber → throws ProductAlreadyExistsExcept

## ⚠️ Global Error Handling

Example error response:

{
  "error": "Invalid product: price cannot be negative"
}

## ▶️ How to Run
Prerequisites
*Java 17+
* Maven installed (or use included wrapper)

## Run with Maven Wrapper:
./mvnw spring-boot:run   (Linux/Mac)
mvnw.cmd spring-boot:run (Windows)

Run with Local Maven:
mvn spring-boot:run


App will start at:

👉 http://localhost:8080



