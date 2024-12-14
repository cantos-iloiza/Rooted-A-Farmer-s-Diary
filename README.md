
![Green Bold Playful Nature Video (2)](https://github.com/user-attachments/assets/c1221310-367c-423c-b7a1-272e2d80a4cc)

---

A tool for small-scale and home-based farmers to monitor crop growth, manage resources, and optimize planting schedules, supporting **SDG 2 (Zero Hunger)** and **SDG 12 (Responsible Consumption and Production).**

---

## Table of Contents 📑

1. [Project Overview 🌱📖👩‍🌾](#i-project-overview)
2. [Application of OOP Principles 💡](#ii-application-of-oop-principles)
   - [Encapsulation🛡️](#a-encapsulation)
   - [Inheritance 🧬](#b-inheritance)
   - [Polymorphism 🎭](#c-polymorphism)
   - [Abstraction ✨](#d-abstraction)
3. [Integration of SDGs 🌍](#iii-integration-of-sdgs)
   - [SDG 2: Zero Hunger 🌾🥦🍅](#sdg-2-zero-hunger)
   - [SDG 12: Responsible Consumption and Production ♻️💧⚙️](#sdg-12-responsible-consumption-and-production)
4. [How to Run the Program 💻➡️📜](#iv-how-to-run-the-program)
   - [Starting the Program 🚀](#a-starting-the-program)
   - [Program Walkthrough 📋](#b-program-walkthrough)
   - [Key Features Key Features for FarmerUser🔑](#c1-key-features-for-farmeruser)
   - [Key Features Key Features for Admin🔑](#c2-key-features-for-admin)
   - [Exiting the Program 🚪](#d-exiting-the-program)

---

## 🌱📖👩‍🌾I. Project Overview

This project aims to assist small-scale and home-based farmers by:
- **Monitoring crop growth**
- **Providing reminders** for watering and fertilizing schedules
- **Identifying the best planting times** for seasonal vegetables
- **Ensure optimal growth** for every crop enlisted.  

It focuses on vegetables suitable for backyard gardens, encouraging sustainable home farming and efficient resource use.

---

## 💡II. Application of OOP Principles

### 🛡️A. Encapsulation
Encapsulation is widely used in the application by declaring fields as `private` and providing controlled access via public methods like getters and setters. This protects the integrity of data and ensures its proper usage.

1. **Crop Class**  
   - Fields such as `name`, `type`, `growthStages`, and `fertilizerSchedule` are private.  
   - Access is managed via methods like `getName()`, `getCurrentGrowthStage()`, and `setFertilizerSchedule()`.

2. **CropInfo Class**  
   - Encapsulates the `crops` list and the `newCrops` set as private.  
   - Methods like `getCrops()` and `addCrop()` control access and manipulation of these fields.  
   - Uses a singleton design pattern to ensure controlled instantiation.

3. **CropManagement Class**  
   - Fields `plantedCrops`, `cropInfo`, and `inventory` are private.  
   - Methods such as `plantCrop()` and `displayWateringSchedule()` ensure validations and restrict direct field access.

4. **PlantedCrop Class**  
   - Fields `crop` and `plantingDate` are private.  
   - Access is controlled via methods like `getCrop()` and `getGrowthStage()`.

5. **Inventory Class**  
   - The `inventory` map is private.  
   - Methods like `useItem()`, `restockItem()`, and `displayInventory()` abstract and manage inventory operations.

6. **InventoryManager Class**  
   - Encapsulates the `inventory` object.  
   - Provides methods like `updateInventory()` and `restockItem()` for controlled resource management.

7. **FarmerNotes Class**  
   - The `notes` list is private.  
   - Managed through methods such as `addNote()` and `viewNotes()` to simplify user interactions.

8. **Note Class**  
   - Fields `date` and `content` are private.  
   - Public methods like `getDate()` and `toString()` expose or format note details as required.

9. **User Classes**  
   - `AdminUser` and `FarmerUser` encapsulate user data (e.g., `username`, `password`) as private.  
   - Authentication and registration methods (`login()`, `register()`) manage access securely.

10. **Main Class**
   - Fields `plantedCrops` and `userDate` used private access.
---

### A.1 **Package Encapsulation**  
Classes are grouped into packages, restricting unnecessary access:  
1. **Crop Management Package:**  
   Contains `Crop`, `CropInfo`, and `CropManagement` to manage planting, growth, and crop metadata.  

2. **User Package:**  
   Contains `AdminUser` and `FarmerUser` to handle authentication and user-specific features.  

3. **Inventory Package:**  
   Encapsulates `Inventory` and `InventoryManager` for resource tracking and management.  

4. **Miscellaneous Package:**  
   Contains `FarmerNotes` and related helper classes to support auxiliary functionalities.

---

### 🧬B. Inheritance
Inheritance is used to share common attributes and behaviors among related classes.

**User Classes**  
   - `AdminUser` extends `User`, inheriting fields like `username` and `password` and overriding specific methods for admin functionalities.
   - `FarmerUser` extends `User`, inheriting fields like `username` and `password` and overriding specific methods for farmerUser functionalities.
   - `FarmerNotes` extends `NotesManager` to inherit methods like `addNote` and `viewNotes`.

---

### 🎭C. Polymorphism
Polymorphism is used to allow methods to behave differently depending on the context.

1. **Method Overriding**  
   - `Crop` overrides the `toString()` method to provide a detailed string representation.
   - `FarmerUser` overrides the `login` method to verify username and password credentials.
   - `FarmerNotes` overrides both `addNoted` and `viewNotes` method to implement them properly.

---

### ✨D. Abstraction
Abstraction is used to hide complex logic and expose only the necessary details through high-level methods and interfaces.

1. **User Class** 
   - The `User` class is now abstract, abstracting common user functionalities such as `username`, `password`, and the `login()` method. Specific user classes (e.g., `AdminUser`, `FarmerUser`) extend `User` and implement the `login()` method for their specialized behavior.
   - The `login()` method is abstract in `User`, requiring derived classes to implement the actual authentication logic.
  
2. **FarmerNotes Class**  
   - `FarmerNotes` extends the abstract class `NotesManager` and provides a concrete implementation for managing notes specific to farmers.  
   - The class manages an `ArrayList` of `Note` objects, providing methods to add new notes and view existing ones. By extending `NotesManager`, `FarmerNotes` inherits the abstract note management operations and provides its own implementation. 

---

## 🌍III. Integration of SDGs 

### 🌾🥦🍅**SDG 2: Zero Hunger**  
**How:**  
- Tools to manage crops, monitor growth stages, and optimize planting schedules.  
- Ensures higher yields and reduces food waste.  

### ♻️💧⚙️**SDG 12: Responsible Consumption and Production**  
**How:**  
- Tracks and advises on resource usage (e.g., seeds, water, and fertilizer).  
- Encourages sustainable farming by providing optimized schedules to prevent overuse.

---

## 💻➡️📜IV. How to Run the Program  

### 🚀A. **Starting the Program**   
1. Use **Visual Studio Code** (or any Java-supported IDE).  
2. Run the program through the **Main Class**.

---

### 📋B. **Program Walkthrough**   

#### 1. **Welcome Screen**  
- Displays a greeting and prompts you to enter today's date in the format `MM-dd-yyyy`.
- You may choose to be the admin or a farmer user.

#### 2. **Login or Sign Up**  
- Choose to log in with existing credentials or create a new account.  
- Successful registration redirects to the main menu.

#### 3.1. **FarmerUser's Main Menu**  
Options available:  
- **1. Crop Management:** Manage crops (e.g., planting, watering, fertilizing).  
- **2. PlantWiki:** Learn about crop details and schedules.  
- **3. Farmer's Notes:** Add or view personal notes.  
- **4. Inventory:** View or update resources.  
- **5. Log Out:** Return to the login menu.

#### 3.2. **Admin's Main Menu** 
- **1. Add a Crop:** Update the current CropInfo with newly-added crops.
- **2. View PlantWiki** Verify if the new crops have been added and can be accessed.
- **3. Manage Inventory:** Verify if the new crops have been added and can be updated in the inventory (admin's inventory only). 

---

### 🔑C.1 **Key Features for FarmerUser** 

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

### 🔑C.2 **Key Features for Admin**
#### 1. **Add New Crop**
- The Admin can insert new crop information to the system (crop name, crop type, season start and end, watering and fertilizer schedule.

#### 2. **View PlantWiki**
- The Admin can verify if their newly-added crops have been stored in the system.

#### 3. **Manage Inventory** 
- The Admin can verify if the newly-added crops have also been stored and recognized in the inventory.

---

### 🚪D. **Exiting the Program** 
- Log out via the menu and select **Exit**, or choose **Log Out** and confirm.  
- Once logged out, you can sign up or log in again.

---

* . ﹢ ˖ ✦ ¸ . ﹢ ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸ * . ﹢ ˖ ✦ ¸ . ﹢ ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸ * . ﹢ ˖ ✦ ¸ . ﹢ ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸ * . ﹢ ˖ ✦ ¸ . ﹢ ° ˖ ･ ·̩ ｡ ☆ ﾟ
