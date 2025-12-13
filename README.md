# Fitness Club CRM System

A simple console-based Customer Relationship Management (CRM) system for managing members of single or multi-branch fitness clubs. This project is a learning exercise demonstrating fundamental Java concepts and object-oriented programming principles.

## 📋 Overview

The Fitness Club CRM is a command-line application designed to manage member registrations, memberships, and club information. It supports two types of members:
- **SingleClubMember**: Members registered at a specific fitness club branch.
- **MultiClubMember**: Members with access to multiple club locations.

The system demonstrates core Java programming techniques including file handling, collections, interfaces, and user input/output formatting.

## ✨ Features

- **Member Management**: Add, remove, and view member details
- **Membership Types**: Support for both single-club and multi-club memberships
- **File Persistence**: Member data is saved to and loaded from CSV files
- **Console Interface**: User-friendly text-based menu system
- **Data Validation**: Input validation and error handling
- **Search Functionality**: Find members by ID or other criteria

## 🏗️ Project Structure
FitnessClub/
├── src/javaproject/
│ ├── Calculator.java # Utility calculations
│ ├── FileHandler.java # CSV file operations
│ ├── JavaProject.java # Main application entry point
│ ├── Member.java # Base member class (abstract)
│ ├── MembershipManagement.java # Core business logic
│ ├── MultiClubMember.java # Multi-club member implementation
│ ├── SingleClubMember.java # Single-club member implementation
│ └── (Interfaces if any) # Interface definitions
├── members.csv # Data storage file
├── .gitignore # Git ignore rules
├── FitnessClub.iml # IntelliJ IDEA project file
└── README.md # This file

## 🛠️ Technologies & Concepts

- **Java Core**: Fundamental Java programming
- **Object-Oriented Programming**: Inheritance, polymorphism, encapsulation
- **Java Collections Framework**: Lists, Maps, and data structures
- **File I/O Operations**: Reading from and writing to CSV files
- **Interfaces & Abstract Classes**: Designing flexible architectures
- **Console I/O**: Formatted input/output with loops and conditionals
- **Exception Handling**: Robust error management

## 🚀 Getting Started

### Prerequisites
- Java JDK 8 or higher
- (Optional) IntelliJ IDEA or any Java IDE

### Running the Application
1. Clone the repository:
   ```bash
   git clone <repository-url>
2. Navigate to the project directory:
   cd FitnessClub
3. Compile and run the main class:
   javac src/javaproject/*.java
   java src/javaproject/JavaProject

### 📊 Class Architecture
- **Member**: Base class with common member properties
- **SingleClubMember**: Extends Member, includes home club association
- **MultiClubMember**: Extends Member, includes membership points system
- **MembershipManagement**: Main controller class with business logic
- **FileHandler**: Handles all file operations for data persistence
- **Calculator**: Functional interface
- **JavaProject**: Launcher
