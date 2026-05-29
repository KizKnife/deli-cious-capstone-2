# 🥪 DELI-cious Sandwich Shop Application
### Capstone 2

A console-based point-of-sale system for a custom sandwich shop that allows users to build fully customized orders, calculate pricing, and generate receipts.

---

# 📌 Project Overview

DELI-cious is a command-line ordering system that simulates a real-world sandwich shop checkout experience. Customers can build fully customized sandwiches, add drinks and chips, review their order, and generate a saved receipt upon checkout.

The system is built using **Object-Oriented Programming principles**, including:

- Encapsulation
- Abstraction
- Interfaces
- Builder Pattern
- Service Layer Architecture
- Composition over Inheritance

---

# 🚀 Features

## 🏠 Home Screen
- Start a new order
- Exit the application
- Application runs continuously until the user exits

---

## 🧾 Order Management System

Once an order is created, users can:

- Add Sandwiches
- Add Signature Sandwiches (specialty templates)
- Add Drinks
- Add Chips
- View current order
- Checkout or cancel order

---

## 🥪 Sandwich Builder System

Customers can fully customize sandwiches:

### Bread Options
- White
- Wheat
- Rye
- Wrap

### Size Options
- 4”
- 8”
- 12”

### Toasting
- Toasted or not toasted

---

## 🧂 Toppings System

Toppings are categorized and support extras:

### Premium Toppings
- Meats (steak, ham, salami, roast beef, chicken, bacon)
- Cheese (american, provolone, cheddar, swiss)
- Extra portions available (additional cost applied)

### Regular Toppings
- Lettuce
- Peppers
- Onions
- Tomatoes
- Jalapeños
- Cucumbers
- Pickles
- Guacamole
- Mushrooms

### Sauces
- Mayo
- Mustard
- Ketchup
- Ranch
- Thousand Islands
- Vinaigrette

### Sides
- Au jus
- Sauce options

---

## 🥤 Drinks
- Small
- Medium
- Large
- Flavor selection
- Price based on size

---

## 🍟 Chips
- Single selection item
- Flavor-based customization

---

## ⭐ Signature Sandwiches (Bonus Feature)

Prebuilt sandwiches using a builder-style system:

### Examples

#### BLT
- Bacon
- Lettuce
- Tomato
- Cheddar
- Ranch
- Toasted white bread (8”)

#### Philly Cheesesteak
- Steak
- American cheese
- Peppers
- Mayo
- Toasted white bread (8”)

---

# 💰 Pricing System

Dynamic pricing is calculated based on:

- Sandwich size (4”, 8”, 12”)
- Premium toppings (meat & cheese)
- Extra portions
- Drink size
- Chips selection

All pricing is calculated at checkout.

---

# 🧾 Checkout & Receipt Generation

At checkout, users can:

- Review full order details
- View itemized pricing
- Confirm or cancel order

If confirmed:

- A receipt file is generated automatically
- Stored in a `receipts/` folder
- Named using timestamp format  

yyyyMMdd-hhmmss.txt
Example: 20230329-121523.txt


---

# 🏗️ Architecture Overview

This project follows a layered OOP architecture:

## 📦 Model Layer
- Order
- Sandwich
- Drink
- Chips
- Topping
- OrderItem (interface)

## ⚙️ Service Layer
- SandwichService
- DrinkService
- ChipsService
- MenuService
- ReceiptWriter

## 🧠 Design Patterns Used
- Builder Pattern (Signature Sandwiches)
- Registry Pattern (Specialty Sandwich lookup)
- Service Layer Pattern (object creation separation)
- Composition over Inheritance

## 🖥️ UI Layer
- UserInterface
- Handles all user interaction and menu flow

---

# 🧠 OOP Principles Demonstrated

- **Encapsulation** — Data is protected inside model classes
- **Abstraction** — Services hide object creation logic
- **Composition** — Orders contain items instead of inheriting
- **Polymorphism** — Shared `OrderItem` interface for pricing behavior
- **Separation of Concerns** — UI, services, and models are separated

---

# 📁 Application Flow

1. User starts application
2. Selects “New Order”
3. Adds sandwiches, drinks, and chips
4. System calculates pricing dynamically
5. User reviews order
6. User confirms checkout
7. Receipt is saved to file

---

# 🧾 Sample Checkout Output
===== CHECKOUT =====

8" white sandwich
Toasted: Yes
Toppings:

bacon
cheddar
lettuce

Medium drink (coke)
BBQ chips

TOTAL: $14.75


---

# 🛠️ Technologies Used

- Java
- Object-Oriented Programming (OOP)
- File I/O (receipt generation)
- Collections (ArrayList, List)
- Console-based UI

---

# 📌 Setup Instructions

```bash
git clone <your-repo-url>
Open project in IntelliJ / Eclipse
Run MainProgram.java
Follow console prompts
````

# 📃 Mermaid Diagram

``` mermaid
classDiagram

%% ======================
%% ENTRY POINT
%% ======================
class MainProgram {
+main(String[] args)
}

%% ======================
%% UI LAYER
%% ======================
class UserInterface {
+display()
+createNewOrder()
+addSandwich()
+addDrink()
+addChips()
+checkout()
}

class MenuService {
+selectSandwichSize()
+selectBreadType()
+selectToasted()
+selectToppings()
}

%% ======================
%% SERVICE LAYER
%% ======================
class SandwichService {
+createSandwich()
}

class DrinkService {
+createDrink()
}

class ChipsService {
+createChips()
}

class SpecialtySandwichRegistry {
+get(String) SandwichBuilder
}

%% ======================
%% DOMAIN MODEL
%% ======================
class Order {
-List~OrderItem~ items
+addItem(OrderItem)
+calculateTotal()
+getItems()
}

class OrderItem {
<<interface>>
+getPrice()
}

class Sandwich {
-String breadType
-int size
-boolean toasted
-List~Topping~ toppings
+getPrice()
}

class Drink {
-String size
-String flavor
+getPrice()
}

class Chips {
-String flavor
+getPrice()
}

class Topping {
-String name
-boolean extra
+getPrice()
}

%% ======================
%% BUILDER PATTERN
%% ======================
class SandwichBuilder {
<<interface>>
+build() Sandwich
}

class BLTSandwich {
+build() Sandwich
}

class PhillyCheeseSteak {
+build() Sandwich
}

%% ======================
%% RECEIPT
%% ======================
class ReceiptWriter {
+writeReceipt(Order)
}

%% ======================
%% RELATIONSHIPS (IMPORTANT)
%% ======================

MainProgram --> UserInterface

UserInterface --> MenuService
UserInterface --> SandwichService
UserInterface --> DrinkService
UserInterface --> ChipsService
UserInterface --> SpecialtySandwichRegistry
UserInterface --> Order
UserInterface --> ReceiptWriter

Order --> OrderItem : contains

OrderItem <|.. Sandwich
OrderItem <|.. Drink
OrderItem <|.. Chips

Sandwich --> Topping : contains

SandwichBuilder <|.. BLTSandwich
SandwichBuilder <|.. PhillyCheeseSteak

SpecialtySandwichRegistry --> SandwichBuilder
SandwichService --> Sandwich
DrinkService --> Drink
ChipsService --> Chips
```