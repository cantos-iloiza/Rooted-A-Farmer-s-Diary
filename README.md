# Rooted: A Farmer's Diary 🌱

* . ﹢ ˖ ✦ ¸ . ﹢ ° ¸. ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸* . ﹢ ˖ ✦ ¸ . ﹢ ° ¸. ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸* . ﹢ ˖ ✦ ¸ . ﹢ ° ¸. ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸* . ﹢ ˖ ✦ ¸ . ﹢ ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸* . ﹢ ˖ ✦

I. A brief project overview 🌱📖👩‍🌾

    This project aims to assist small-scale and home-based farmers by helping them monitor crop growth, providing reminders for watering and fertilizing schedules, and identifying the best planting times for seasonal vegetables. The system focuses on vegetables that can be grown in backyard gardens, supporting SDG 2 (Zero Hunger) and SDG 12 (Responsible Consumption and Production).

II. Explanation of how OOP principles were applied 
  
  A. Encapsulation 🛡️📦
  
    Encapsulation was used to keep the details of each class hidden from other parts of the program and to only allow access to necessary information through public methods:
  
    In the Crop class, variables like name, type, and wateringSchedule are private. They can only be accessed or modified using getter methods like getName() or getWateringSchedule(). This ensures that the internal details of a crop remain secure and cannot be directly changed by other classes.
  
    The FarmerUser class keeps user information such as username and password private. Only methods like login() or register() allow controlled access to these details, preventing accidental or unauthorized modifications.
  
  B. Inheritance 🧬➡️📋
  
    Inheritance was applied to reuse code and extend functionality. For instance:
  
    Although not explicitly visible in this setup, similar functionality for different user types or crop categories could be added by extending the FarmerUser or Crop classes. For example, a PremiumFarmerUser could inherit from FarmerUser and add new features like premium tools or analytics.
  
    The InventoryManager class works closely with the Inventory class, showing how specific functionality can be delegated and potentially extended if additional inventory features are required.
  
C. Polymorphism 🎭🔄🤹
  
    Polymorphism was used to allow different behaviors in classes while keeping their interfaces consistent:
  
    In the Crop class, the toString() method is overridden to provide a detailed and formatted description of the crop. This allows objects of the Crop type to return meaningful information when converted to a string.
  
    The manageCrops() method in the CropManagement class can handle different types of crops using the same method calls, even if new crop subclasses are added later.
  
D. Abstraction 🔍✨🧩
  
    Abstraction was applied by breaking the program into smaller, focused classes that handle specific parts of the project:
  
    The Crop class abstracts details about crops, like their growth stages, fertilizer schedules, and watering needs, so other classes don’t need to worry about these details.
  
    The Inventory class manages all the inventory-related tasks, like tracking seeds and tools, without exposing how items are stored internally.
  
    The FarmerNotes class abstracts the note-keeping functionality, allowing farmers to add, view, or delete notes without concerning themselves with the actual data storage format.
  
    This division makes the program easier to manage and extend in the future.
  
III. Details of the Chosen SDG and Its Integration into the Project
  
  SDG 2: Zero Hunger 🌾🥦🍅
  
    Integration: The project helps farmers grow food more efficiently by providing tools to manage crops, monitor growth stages, and optimize planting schedules. This ensures higher yields and reduces wastage.
  
  SDG 12: Responsible Consumption and Production ♻️💧⚙️
  
    Integration: The system tracks and advises on the use of resources like seeds, water, and fertilizer. It encourages sustainable farming practices by providing accurate schedules and avoiding overuse of inputs.

IV. Instructions for running the program 💻➡️📜

  A. Running the Program

    In order to run the program, you have to run the code through the Main Class.

  B. Program Walkthrough
  
    Welcome Screen: Upon launching, the program will display a welcome message and prompt you to enter today's date in the format MM-dd-yyyy.
 
    Login or Sign Up: You can log in with an existing account or create a new one by selecting the respective option. For a new account, enter a username and password. Successful registration will redirect you to the main menu.
  
  Main Menu:
  
    1. Crop Management: Manage your crops.
    
    2. PlantWiki: Learn about available crops and their details.
    
    3. Farmer's Notes: Add or view personal notes.
    
    4. Inventory: View and update your inventory.
    
    5. Log Out: Return to the login menu.


C. Key Features
  
  1. Crop Management

    Choose to plant a crop, view watering schedules, check fertilizer schedules, or track crop growth stages.

    Plant a Crop: Choose a crop, verify seed availability, and provide a planting date.
    
    Watering Schedule: Displays how many days until the next watering is due.
    
    Fertilizer Schedule: Lists upcoming fertilizer applications.
    
    Growth Stages: Displays the current growth stage of planted crops.
    
  3. PlantWiki

    View details of crops like type, season, watering schedule, and fertilizer schedule.
    
  5. Farmer’s Notes

    Add notes by specifying a date and entering text.
    
    View all saved notes.
    
  7. Inventory Management

    View the available items in your inventory.
    
    Restock items or use resources by entering their names and quantities.


D. Exiting the Program
  
    To exit, either log out and select Exit from the login menu or choose Log Out and confirm your choice. If you were to log out, you can Sign Up or Log In an account again.

* . ﹢ ˖ ✦ ¸ . ﹢ ° ¸. ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸* . ﹢ ˖ ✦ ¸ . ﹢ ° ¸. ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸* . ﹢ ˖ ✦ ¸ . ﹢ ° ¸. ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸* . ﹢ ˖ ✦ ¸ . ﹢ ° ˖ ･ ·̩ ｡ ☆ ﾟ ＊ ¸* . ﹢ ˖ ✦


