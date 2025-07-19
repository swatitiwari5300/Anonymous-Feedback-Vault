# 🗳️ Anonymous Feedback Vault

A Spring Boot-based microservice for collecting **anonymous feedback** using **secure email hashing** (SHA-256).
✅ No emails stored in plain text.
✅ No user identification.
✅ Just pure, anonymous expression.

---

## 🚀 Features

* 🔒 **Email hashing with SHA-256** — no raw emails are ever stored.
* 📝 **One-time anonymous feedback** — one feedback per user.
* 🔍 **Secure verification** — users can check if they submitted feedback.
* ⚙️ **Minimal & production-ready REST API** using Spring Boot 3.

---

## 📦 Tech Stack

* Java 17
* Spring Boot 3
* Maven
* H2 (in-memory DB, switchable to PostgreSQL/MySQL)
* Lombok

---

## 📂 API Endpoints

### 📨 Submit Feedback

```
POST /submit
Content-Type: application/json
```

**Request Body:**

```json
{
  "email": "user@example.com",
  "message": "This is my honest feedback!"
}
```

**Response:**

```json
{
  "status": "success",
  "message": "Feedback submitted anonymously."
}
```

> ⚠️ Only one submission allowed per hashed email.

---

### 🔍 Verify Feedback

```
GET /verify?email=user@example.com
```

**Response:**

```json
{
  "submitted": true,
  "message": "This is my honest feedback!"
}
```

> Email is hashed server-side before checking, maintaining anonymity.

---

## 🔐 How Anonymity is Maintained

* Emails are never stored — only the **SHA-256 hash** is persisted.
* Hashing prevents reverse lookup or deanonymization.
* No authentication or IP tracking is used.
* Feedback is tied **only** to the hashed value.

---

## 💡 Potential Enhancements

* ⏳ Expiry for feedback tokens
* 🔑 Client-side email hashing for true zero-knowledge
* 🎫 Token-based verification instead of email rehashing
* 🧠 Integration with GPT for feedback sentiment analysis

---

## 🛠️ How to Run Locally

1. **Clone the repo:**

   ```bash
   git clone https://github.com/<your-username>/anonymous-feedback-vault.git
   cd anonymous-feedback-vault
   ```

2. **Run the app:**

   ```bash
   ./mvnw spring-boot:run
   ```

3. **Test with cURL:**

   * Submit:

     ```bash
     curl -X POST http://localhost:8080/submit \
     -H "Content-Type: application/json" \
     -d '{"email": "abc@example.com", "message": "This platform is awesome!"}'
     ```

   * Verify:

     ```bash
     curl "http://localhost:8080/verify?email=abc@example.com"
     ```

---

## 📂 Project Structure

```
src/
├── controller/       --> REST APIs
├── service/          --> Business logic
├── util/             --> SHA-256 Hashing logic
├── model/            --> DTOs & Entities
└── repository/       --> JPA Repos
```

---

## 👥 Why This Project?

In today's digital workplaces, **honest feedback** can often be held back due to fear of consequences.
This service encourages psychological safety by enabling **fully anonymous, secure** submission — no emails stored, no identities leaked.

Use it in:

* Team retros
* Anonymous polls
* College peer reviews
* Product testing
