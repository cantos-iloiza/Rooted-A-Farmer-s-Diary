# Rooted: A Farmer's Diary 🌱

---

A tool for small-scale and home-based farmers to monitor crop growth, manage resources, and optimize planting schedules, supporting **SDG 2 (Zero Hunger)** and **SDG 12 (Responsible Consumption and Production).**

---

## Table of Contents 📑

1. [🌱📖👩‍🌾Project Overview](#i-project-overview)
2. [Application of OOP Principles](#ii-application-of-oop-principles)
   - [Encapsulation](#a-encapsulation)
   - [Package Encapsulation](#a1-package-encapsulation)
   - [Inheritance](#b-inheritance)
   - [Polymorphism](#c-polymorphism)
   - [Abstraction](#d-abstraction)
3. [Integration of SDGs](#iii-integration-of-sdgs)
   - [SDG 2: Zero Hunger](#sdg-2-zero-hunger)
   - [SDG 12: Responsible Consumption and Production](#sdg-12-responsible-consumption-and-production)
4. [How to Run the Program](#iv-how-to-run-the-program)
   - [Starting the Program](#a-starting-the-program)
   - [Program Walkthrough](#b-program-walkthrough)
   - [Key Features](#c-key-features)
   - [Exiting the Program](#d-exiting-the-program)

---

🌱📖👩‍🌾 ## I. Project Overview

This project aims to assist small-scale and home-based farmers by:
- **Monitoring crop growth**
- **Providing reminders** for watering and fertilizing schedules
- **Identifying the best planting times** for seasonal vegetables
- **Ensure optimal growth** for every crop enlisted.  

It focuses on vegetables suitable for backyard gardens, encouraging sustainable home farming and efficient resource use.

---

## II. Application of OOP Principles 💡  

### A. **Encapsulation**  
- **Crop Class:** Variables like `name`, `type`, and `wateringSchedule` are private. Access or modification is allowed only through getters and setters (e.g., `getName()` and `getWateringSchedule()`), ensuring data security.
- **FarmerUser Class:** User credentials (`username` and `password`) are private and accessed only via methods like `login()` or `register()`. This prevents unauthorized access.

---

### A.1 **Package Encapsulation**  
Classes are grouped into packages, restricting unnecessary access:  
1. **Crop Management Package:**  
   Includes `Crop`, `CropManagement`, and `PlantWiki`. Details are hidden within the package, and public interfaces expose necessary functionality.
   
2. **User Management Package:**  
   Contains `FarmerUser` and `PremiumFarmerUser` for managing user authentication and permissions.

3. **Inventory Package:**  
   Encapsulates `Inventory` and `InventoryManager` to handle resources while hiding internal storage mechanisms.

---

### B. **Inheritance**  
- Extend existing classes for specialized use cases:  
  - `PremiumFarmerUser` inherits from `FarmerUser` to add analytics and premium tools.  
  - Potential new crop subclasses (e.g., `ExoticCrop`) can build on the `Crop` base class.

- The `InventoryManager` extends inventory functionalities by reusing code from the `Inventory` class.

---

### C. **Polymorphism**  
- The **`toString()` Method:** In the `Crop` class, overridden to return a formatted crop description.  
- **`manageCrops()` Method:** Handles all crop types using a unified interface, allowing easy addition of new crop subclasses.

---

### D. **Abstraction**  
Program functionality is broken into focused classes:  
- **Crop Class:** Manages crop growth stages, watering, and fertilizing schedules.  
- **Inventory Class:** Handles resource tracking without exposing internal storage methods.  
- **FarmerNotes Class:** Simplifies note-keeping tasks like adding, viewing, and deleting notes.

---

## III. Integration of SDGs 

### **SDG 2: Zero Hunger**  
**How:**  
- Tools to manage crops, monitor growth stages, and optimize planting schedules.  
- Ensures higher yields and reduces food waste.  

### **SDG 12: Responsible Consumption and Production**  
**How:**  
- Tracks and advises on resource usage (e.g., seeds, water, and fertilizer).  
- Encourages sustainable farming by providing optimized schedules to prevent overuse.

---

## IV. How to Run the Program  

### A. **Starting the Program**   
1. Use **Visual Studio Code** (or any Java-supported IDE).  
2. Run the program through the **Main Class**.

---

### B. **Program Walkthrough**   

#### 1. **Welcome Screen**  
- Displays a greeting and prompts you to enter today's date in the format `MM-dd-yyyy`.

#### 2. **Login or Sign Up**  
- Choose to log in with existing credentials or create a new account.  
- Successful registration redirects to the main menu.

#### 3. **Main Menu**  
Options available:  
- **1. Crop Management:** Manage crops (e.g., planting, watering, fertilizing).  
- **2. PlantWiki:** Learn about crop details and schedules.  
- **3. Farmer's Notes:** Add or view personal notes.  
- **4. Inventory:** View or update resources.  
- **5. Log Out:** Return to the login menu.

---

### C. **Key Features** 

#### 1. **Crop Management**  
- **Plant a Crop:** Select a crop, check seed availability, and provide the planting date.  
- **Watering Schedule:** Displays days until the next watering.  
- **Fertilizer Schedule:** Lists upcoming fertilizer applications.  
- **Growth Stages:** Displays the current growth stage of crops.

#### 2. **PlantWiki**  
- Learn about each crop's type, season, watering needs, and fertilizer schedule.

#### 3. **Farmer’s Notes**  
- **Add Notes:** Enter a date and text.  
- **View Notes:** Review all saved notes.

#### 4. **Inventory Management**  
- View current items in the inventory.  
- Restock or use resources by specifying names and quantities.

---

### D. **Exiting the Program** 
- Log out via the menu and select **Exit**, or choose **Log Out** and confirm.  
- Once logged out, you can sign up or log in again.

---

* . ﹢ ˖ ✦ ¸ . ﹢ ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸ * . ﹢ ˖ ✦ ¸ . ﹢ ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸ * . ﹢ ˖ ✦ ¸ . ﹢ ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸ * . ﹢ ˖ ✦ ¸ . ﹢ ° ˖ ･ ·̩ ｡ ☆ ﾟ
