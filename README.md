# Rooted: A Farmer's Diary 🌱

---

A tool for small-scale and home-based farmers to monitor crop growth, manage resources, and optimize planting schedules, supporting **SDG 2 (Zero Hunger)** and **SDG 12 (Responsible Consumption and Production).**

---

## Table of Contents 📑

1. [Project Overview 🌱📖👩‍🌾](#i-project-overview)
2. [Application of OOP Principles 💡](#ii-application-of-oop-principles)
   - [Encapsulation🛡️📦](#a-encapsulation)
   - [Inheritance 🧬➡️📋](#b-inheritance)
   - [Polymorphism 🎭🔄🤹](#c-polymorphism)
   - [Abstraction 🔍✨🧩](#d-abstraction)
3. [Integration of SDGs 🌍](#iii-integration-of-sdgs)
   - [SDG 2: Zero Hunger 🌾🥦🍅](#sdg-2-zero-hunger)
   - [SDG 12: Responsible Consumption and Production ♻️💧⚙️](#sdg-12-responsible-consumption-and-production)
4. [How to Run the Program 💻➡️📜](#iv-how-to-run-the-program)
   - [Starting the Program 🚀](#a-starting-the-program)
   - [Program Walkthrough 📋](#b-program-walkthrough)
   - [Key Features 🔑](#c-key-features)
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

### 🛡️📦A. Encapsulation
Encapsulation is widely used in the application by declaring fields as `private` and providing controlled access via public methods like getters and setters. This protects the integrity of data and ensures its proper usage.

1. **Crop Class**  
   - Fields such as `name`, `type`, and `growthStages` are private.  
   - Getters and setters control access, e.g., `getName()` and `setName()`.

2. **CropInfo Class**  
   - The `crops` list is private.  
   - Controlled through `getCrops()` and `getCropByName()` methods.

3. **CropManagement Class**  
   - Fields such as `plantedCrops`, `cropInfo`, and `inventory` are private.  
   - Methods like `plantCrop()` ensure validations and restrict unauthorized modifications.

4. **PlantedCrop Class**  
   - Fields `crop` and `plantingDate` are private with controlled access.  

5. **Inventory Class**  
   - The `inventory` map is private.  
   - Managed via methods like `useSeed()` and `restockItem()`.

6. **InventoryManager Class**  
   - Encapsulates inventory operations, restricting direct access to inventory details.

7. **FarmerNotes Class**  
   - The `notes` list is private, with controlled access through `addNote()` and `viewNotes()`.

8. **Note Class**  
   - Fields `date` and `content` are private, with public getters and setters for access.

9. **FarmerUser Class**  
   - User data (`username`, `password`, etc.) is encapsulated.  
   - Public methods like `login()` and `register()` handle secure interactions.

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

### 🧬➡️📋B. Inheritance
While no explicit inheritance (`extends`) is present in the application, the code can be extended to introduce parent-child relationships for reusable or common behaviors. Future enhancements might include:

1. Creating a base `User` class for shared user functionalities.  
2. Extending `Crop` into more specialized crop types.

---

### 🎭🔄🤹C. Polymorphism
Polymorphism is used to allow methods to behave differently depending on the context.

1. **Method Overriding**  
   - **Crop Class**: Overrides `toString()` to provide a custom string representation of a crop.  

2. **Dynamic Behavior**  
   - **CropManagement Class**: Calls the `getCurrentGrowthStage()` method of the `Crop` class, where the behavior changes based on crop-specific growth stages.  
   - **FarmerUser Class**: Uses the `login()` method, which behaves differently based on input.

3. **Switch-Case Constructs**  
   - Used in classes like `CropManagement` and `Main` to handle user choices dynamically.

---

### 🔍✨🧩D. Abstraction
Abstraction is used to hide complex logic and expose only the necessary details through high-level methods and interfaces.

1. **Crop Class**  
   - Abstracts growth stage logic through `getCurrentGrowthStage()`.

2. **CropInfo Class**  
   - Abstracts crop initialization via `initializeCrops()` to hide details of creating predefined crops.

3. **CropManagement Class**  
   - Hides details of planting, watering, fertilizing, and growth tracking through dedicated methods (`plantCrop()`, `displayWateringSchedule()`, etc.).

4. **PlantedCrop Class**  
   - Abstracts growth stage tracking via the `getGrowthStage()` method.

5. **Inventory Class**  
   - Abstracts inventory operations like seed usage, restocking, and displaying items.

6. **InventoryManager Class**  
   - Provides a simplified interface for managing inventory, hiding internal implementation details.

7. **FarmerNotes Class**  
   - Abstracts note addition and retrieval, exposing user-friendly methods.

8. **Main Class**  
   - Orchestrates interactions between different components (`FarmerUser`, `CropManagement`, etc.), hiding their complexities from the user.

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

### 🔑C. **Key Features** 

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

### 🚪D. **Exiting the Program** 
- Log out via the menu and select **Exit**, or choose **Log Out** and confirm.  
- Once logged out, you can sign up or log in again.

---

* . ﹢ ˖ ✦ ¸ . ﹢ ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸ * . ﹢ ˖ ✦ ¸ . ﹢ ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸ * . ﹢ ˖ ✦ ¸ . ﹢ ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸ * . ﹢ ˖ ✦ ¸ . ﹢ ° ˖ ･ ·̩ ｡ ☆ ﾟ
