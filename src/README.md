# 🛒 Secure Shopping Cart Library

This is a **secure shopping cart library** implemented in Java using Maven and JUnit 5.
The library enforces **data integrity, immutability, and input validation** to prevent malicious updates.

---

## 📌 Features
✔️ **Unique Cart ID** → Each shopping cart has a unique **UUID v4**  
✔️ **Immutable Data** → Prevents unauthorized modifications via **defensive copying**  
✔️ **Strict Validation** →  
   - Customer ID follows the format: **`XXX-12345-XX-[A|Q]`**  
   - Items must exist in the **catalog** (predefined list)  
   - **No negative quantities** or excessive item counts  
✔️ **Unit Tests** → JUnit 5 is used to ensure correctness  
✔️ **Easy to Run** → Use **Maven** for testing (`mvn test`)  

---

## 🚀 Installation & Setup
### **1️⃣ Clone the Repository**
```sh
git clone <repository-url>
cd shopping-cart
```

### **2️⃣ Ensure Java & Maven are Installed**
Check if Java and Maven are installed:
```sh
java -version
mvn -version
```
- Java **11+** required  
- Maven **3.x.x** required  

If missing, install Java & Maven:  
```sh
sudo apt update
sudo apt install -y openjdk-17-jdk maven
```

---

## 🛠 Running the Tests
### **Run all JUnit tests**
```sh
mvn test
```
📈 Expected output:
```
[INFO] Running cart.ShoppingCartTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```
✅ **All tests should pass successfully!**

---

## 📝 Class Overview
### **`ShoppingCart.java`**
- `UUID getCartId()` → Returns the cart’s unique ID  
- `String getCustomerId()` → Returns the associated customer ID  
- `Map<String, Integer> getItems()` → Returns an **immutable** cart item list  
- `void addItem(String productName, int quantity)` → Adds an item  
- `void updateItem(String productName, int quantity)` → Updates item quantity  
- `void removeItem(String productName)` → Removes an item  
- `double getTotalCost()` → Calculates the total cost  

### **`CartException.java`**
- Custom exception class for **invalid operations**  

### **`Catalog.java`**
- Predefined product catalog  
- `boolean isValidProduct(String productName)` → Checks if a product exists  
- `double getPrice(String productName)` → Gets product price  

---
