# Football Player Statistic System ⚽️

**Course:** Object-Oriented Programming (OOP)  
**Institution:** President University  
**Author:** Wida Sultan Utama Iryon, M. Fariel Abda, Muhammad Nabil Azzhaky

## Project Overview
The Football Player Statistic System is a Java-based desktop application designed to demonstrate the core principles of Object-Oriented Programming (OOP). It provides a Graphical User Interface (GUI) where users can select various football players and instantly view their specific statistics. The system also includes a dynamic "Search History" feature to track user activity during the session.



## Core OOP Concepts Applied
This project heavily implements the four main pillars of OOP:
1. **Encapsulation:** Player attributes (e.g., `name`, `club`, `goals`) are protected using `protected` and `private` access modifiers to prevent unauthorized direct modifications.
2. **Inheritance:** A parent-child class architecture is established. `Attacker` and `Defender` are subclasses that inherit common traits from the `FootballPlayer` superclass.
3. **Polymorphism:** The `getStats()` method behaves differently depending on the object type. If the object is an `Attacker`, it returns goals and assists. If it is a `Defender`, it returns tackles and clean sheets.
4. **Abstraction:** The system hides the complex data retrieval process from the user, presenting only a clean, interactive GUI.

## System Architecture (Classes)
* `FootballPlayer.java` - The superclass containing general attributes (Name, Club, Matches Played).
* `Attacker.java` - Subclass extending `FootballPlayer`, adding offensive stats (Goals, Assists).
* `Defender.java` - Subclass extending `FootballPlayer`, adding defensive stats (Tackles, Clean Sheets).
* `FootballGUI.java` - The Main executable class containing the Java Swing interface and the `ArrayList` data structure for the Search History log.

## Featured Players
The application currently features a curated list of world-class and local talents:
* Lionel Messi (Inter Miami) - *Attacker*
* Cristiano Ronaldo (Al Nassr) - *Attacker*
* Vinícius Júnior (Real Madrid) - *Attacker*
* Virgil van Dijk (Liverpool) - *Defender*
* Jay Idzes (Venezia FC) - *Defender*

## How to Run the Application (macOS / VSCode Terminal)

**Prerequisites:** Ensure you have the Java Development Kit (JDK) installed on your system.

1. Open the project folder in VSCode.
2. Open the integrated terminal (`Ctrl + ~` or `` Ctrl + ` ``).
3. To clean old compiled files, re-compile the source code, and launch the application in one go, execute the following command:

```bash
rm *.class && javac *.java && java FootballGUI