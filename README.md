PhaseZero - Catalog Service

A simple Spring Boot REST API to manage a product catalog (built for the PhaseZero Java Trainee Assignment).

⭐ Overview

This is a lightweight in-memory product catalog service that supports:

Create Product — POST /products

List All Products — GET /products

Get Product by Part Number — GET /products/{partNumber}

No external database is required. The application uses an in-memory repository and resets on each restart.

🛠 Tech Stack

Java 17+

Spring Boot 4.0

Spring Web MVC

Maven

H2 Database (Console Included) — optional, not used for persistence

📁 Project Structure
src/
 └── main/
      ├── java/com/phasezero/catalog/
      │     ├── controller/        # REST controllers
      │     ├── service/           # Business logic
      │     ├── repository/        # In-memory repository
      │     ├── model/             # Product model
      │     ├── exception/         # Custom exceptions + global error handler
      │     └── PhasezeroCatalogServiceApplication.java
      └── resources/
            └── application.properties

pom.xml

📌 Product Model Fields
Field	Type	Description
partNumber	String	Unique product identifier
partName	String	Human readable name
category	String	Product category
price	double	Product price
stock	int	Available quantity
🚀 API Endpoints
1️⃣ Create Product
POST /products

Headers:

Content-Type: application/json


Example Request Body

{
  "partNumber": "PN-101",
  "partName": "Hydraulic Pump",
  "category": "Hydraulics",
  "price": 4999.99,
  "stock": 5
}


Possible Responses

Status	Meaning
201 Created	Product saved
400 Bad Request	Validation failed
409 Conflict	partNumber already exists
2️⃣ List All Products
GET /products

Response Example

[
  {
    "partNumber": "PN-101",
    "partName": "Hydraulic Pump",
    "category": "Hydraulics",
    "price": 4999.99,
    "stock": 5
  }
]

3️⃣ Get Product by Part Number
GET /products/{partNumber}

Example:

GET /products/PN-101


Possible Responses:

Status	Meaning
200 OK	Product found
404 Not Found	Product not found
🧪 Validation Rules

These validations run in the service layer:

partNumber required

partName required

category required

price >= 0

stock >= 0

Duplicate partNumber → throws ProductAlreadyExistsException

⚠️ Global Error Handling

Errors return a clean JSON response from the GlobalExceptionHandler.

Example:

{
  "error": "Invalid product: price cannot be negative"
}

▶️ How to Run
Prerequisites

Java 17+

Maven installed
(or use included wrapper)

Run with Maven Wrapper
./mvnw spring-boot:run   (Linux/Mac)
mvnw.cmd spring-boot:run (Windows)

Run with Local Maven
mvn spring-boot:run

App will start at:

👉 http://localhost:8080

📬 Example curl Command
curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -d '{"partNumber":"PN-101","partName":"Hydraulic Pump","category":"Hydraulics","price":4999.99,"stock":5}'

📦 Repository URL

Paste your GitHub repo link here:

https://github.com/GunaUthirakumar/phasezero-catalog-service
