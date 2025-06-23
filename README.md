# EMS
Employee Management System

A console-based Java application to manage Employees, Managers, and Projects using OOP principles and Java 8 features.

---

## 🚀 Features

- Add employees and managers with name, age, salary, department
- Assign employees to managers
- Create projects and assign them to managers
- Assign employees to projects (only if under that manager)
- View all employees under a specific manager
- View manager-to-employee hierarchy with arrows
- Filter high-salary employees using Java 8 Streams
- Sort employees by name or age
- Group employees by department using `Collectors.groupingBy()`
- Show average salary per department
- Custom exception handling (e.g., for duplicate employee ID)

---

## 🛠️ Technologies Used

- Java 17 (or compatible version)
- Java Collections (`List`, `Map`, `Optional`)
- Java 8 Streams and Lambda expressions
- OOP concepts
- Custom Exceptions
- Console-based user interaction

---

## 🧱 Class Structure

| Class | Responsibility |
|-------|----------------|
| `EmsConsole` | Main class; handles menu & user interaction |
| `EmsService` | Contains all business logic and data operations |
| `Person` | Base class with name and age |
| `Employee` | Extends Person; adds salary, department, ID |
| `Manager` | Extends Employee; contains team list |
| `Project` | Assigned to a manager; can have multiple employees |
| `DuplicateEmployeeException` | Custom exception for duplicate ID |

---

## 🖥️ How to Run

1. Clone the repository or copy the code into your IDE.
2. Make sure you have Java 17+ installed.
3. Run the `EmsConsole` class.
4. Use the console menu to interact with the system.
