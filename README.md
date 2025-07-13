# 🏨 ShopZone App v1.0.0 – Spring Boot Backend

This is a backend project for a Shopping App built using Spring Boot. It includes secure authentication using JWT, role-based access control, async email notifications, and REST APIs for managing orders.

## 🚀 Tech Stack
- **Java 8** – Core programming language  
- **Spring Boot** – Backend framework  
- **Spring Security with JWT** – Authentication & authorization  
- **Spring Data JPA** – ORM & database access  
- **jBPM Workflow Engine** – Workflow automation  
- **MySQL** – Relational database  
- **Maven** – Dependency management  
- **Redis** – In-memory caching for fast data access  
- **Spring Async** – For running background tasks and improving performance during long-running operations  
- **Global Exception Handling** – Clean error responses

## ✅ Features
- User registration & login via email OTP service only authenticated users can do JWT authentication
- Role-based access (Admin/Manager/User)
- User can manages self: Orders, Address, Profile details, Coupons etc. 
- Admin/Manager can manages: Products, Orders, Users, Coupons, Gift cards, Delivery schedules etc.
- Async order status via email services
- CRUD APIs for all entities
- Global error handling

## 📁 Project Structure
- `controller` – REST API endpoints
- `service` – Business logic
- `repository` – JPA Repositories
- `entity` – Entity classes
- `config` – JWT & Security Config
- `exception` – Custom exceptions

## 🔐 Authentication
- JWT-based login
- Access token returned on login
- Role-based protected routes (e.g., only admin can delete/update products, orders etc.)

## 🛠️ Setup Instructions
1. Clone the repo:
   git clone https://github.com/lokeshch88/shopzone-app-backend.git
2. Update application.properties
3. Run the app: mvn spring-boot:run
4. The backend will start on: http://localhost:8080/

-------------------------------------------------------------------
# 🏨 ShopZone App v1.0.1 – Spring Boot Backend
This is a new version of shopzone app. Maintain in new private repository.

## ✅ v1.0.1 – Enhanced Features
This version adds advanced functionalities on top of v1.0.0:
🔄 New in v1.0.1:
🔐 Razorpay payment gateway integration
❤️ Wishlist management
⚙️ Workflow-based order handling (jBPM)
🧠 Redis-based caching for product and coupon data
