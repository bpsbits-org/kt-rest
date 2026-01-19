# Module Quarkus Toolbox (`kt-rest`)

This toolbox is a set of "shortcuts" and high-performance tools for Kotlin developers using Quarkus. 

Most traditional ways of building apps are like building a house where you have to hire a different translator for every single worker. It’s slow, expensive, and things get lost in translation. **`kt-rest` removes the translators and lets the workers talk directly to each other.**

---

## The "Philosophy of Less"

> **The question is not how much code you write, but how much less code you have to create for a more efficient and robust solution.** 

In the world of software, **code is a liability, not an asset.** Every line of code you write is a line that can break, a line that needs to be tested, and a line that a future developer has to spend time understanding. By using `kt-rest`, you are choosing the "Simple Lever." You achieve the same (or better) results with 90% less code, which means: 

* **Fewer Bugs:** Statistical studies show that for every 1,000 lines of code, about 15 bugs find their way to users. If you only write 100 lines instead of 1,000, you've already "deleted" 90% of your potential problems before they even happen.
* **Higher Quality:** When you have less to look at, you can focus on making that one thing perfect.

---

## 1. The "Waiting Game" 

> **The Problem**

In a normal setup (using things like Hibernate or ORMs), when you want to change how your app works—even for a tiny bug fix—you have to go through a long process:

```
`Code Change` → `Build App` → `Build Container` → `Upload` → `Server Restart`
```

**This may take several hours.** If your company loses money every minute the app is broken, this "waiting game" is a massive financial drain.

## 2. The Solution: "Hot-Swapping" 

> **Fixing the car while it’s driving**

With `kt-rest`, your business logic lives inside your PostgreSQL database as a "Function."
* **The Traditional Way:** To change the logic, you have to perform "surgery" on the app (redeploy and restart).
* **The `kt-rest` Way:** You simply update the function in the database. 
* **The Benefit:** The change happens **instantly**. The very next user who hits your API sees the fix. No waiting, no restarting, no lost revenue.

---

## 3. The "One-Line" Shortcut 

>  `pgFunctionResponse`

Usually, creating a single "endpoint" (the URL your app talks to) requires writing "boilerplate"—boring, repetitive code that doesn't actually do anything unique. You usually need to write Data Classes, Mappers, and Controllers.

With the **`BasicRequestHandler::pgFunctionResponse`** feature, we’ve turned all that work into a **single line of Kotlin code.** You tell the app which database function to call, and `kt-rest` handles everything else. It’s like having a universal remote instead of 50 different buttons.

---

## 4. A Direct Pipe for Data 

>  **No "Handling Fees**"

Usually, data has to be "unpacked" and "repacked" several times. This is like a "Handling Fee" at a bank; it costs time and energy (CPU and Memory).

**`kt-rest` creates a direct pipe.** Since modern databases like Postgres can already speak "`JSON`," we pipe that data directly to the user's screen. The app server doesn't have to work hard, meaning your servers stay fast even when thousands of people are using them at once.

---

## 5. Evolution without Friction (True Scalability)

> **Changing the Foundation Without Breaking the House**

In traditional apps, the "Shape" of your data is defined in two places: the Database and the App Server (the ORM). If you change the database, you have to spend days rewriting and re-testing huge amounts of Kotlin/Java code to match. It’s like having two separate maps for the same city—if one street changes, you have to manually update both maps perfectly, or you get lost.

**`kt-rest` keeps the "Source of Truth" in one location: The Database Engine.**
* **Better Scalability:** Because the entire data structure lives in the database engine, you can reorganize, optimize, or grow your tables without worrying about rewriting complex ORM layers. 
* **Robustness:** If you need to change how data is stored to handle millions of new users, you just do it in the database. Your Kotlin code doesn't need to change because it isn't "tangled" up in the table structures. This makes your solution far more stable as it grows.

---

## 6. Easier Testing

> **Testing the Brain Directly**

Testing code is often hard because you have to set up a "fake" environment to see if your app works. Because `kt-rest` encourages you to put your logic in the database, you can test that logic **directly in the database.**

* **Why this helps:** It’s much simpler to ask the database "If I give you X, do you give me Y?" than it is to set up a whole complicated server environment just to test one small rule. It makes your code higher quality and reduces hidden bugs.

---

## Summary of Benefits

- **Save Time:** Write *one line of code* instead of hundreds via `pgFunctionResponse`.
- **Save Money:** Patch critical bugs in milliseconds via **Hot-Swapping**, not minutes or hours.
- **Save Server Costs:** Your app uses less "brain power" (CPU) because it isn't constantly translating data.
- **Scale Effortlessly:** Change your underlying data structure without rewriting thousands of lines of ORM code.
- **Better Security:** The app can only enter through the "front door" functions you allow; it can't go wandering through the "back rooms" (tables) of your data.

## Main Technical Features

- **ORM Bypass:** No more slow Hibernate/JPA mapping.
- **Single-Location Data Structure:** Centralizes schema management in the DB engine for easier evolution.
- **JSON Streaming:** Fast, direct-to-browser data flow.
- **Native Quarkus Support:** Fully optimized for GraalVM native images and the Quarkus reactive stack.
- **Standardized Errors:** Every error looks the same, making frontend integration a breeze.
- **Precise Exceptions:** Automatic mapping of database-level errors to correct HTTP status codes.

---
**In short: `kt-rest` lets you build faster, fix faster, and run faster by creating less.**