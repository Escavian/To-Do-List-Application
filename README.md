# Java Swing To‑Do List Application

A fully interactive Java Swing To‑Do List application that allows users to add tasks, remove tasks, and mark tasks as completed by double‑clicking them. The program includes input validation and helpful prompts to guide the user.

## 📌 Features
- Add new tasks using a text input field
- Remove selected tasks from the list
- Double‑click a task to toggle its **(done)** status
- Prevents empty input from being added
- Warns the user if they try to remove a task without selecting one
- Clean, simple, user‑friendly GUI
- Saves tasks to a file (if your version includes persistence)

## 🧠 Concepts Demonstrated
- Java Swing (JFrame, JList, JTextField, JButton, JOptionPane)
- Event handling (ActionListener, MouseListener, KeyListener)
- List models (`DefaultListModel`)
- Input validation and user feedback
- GUI layout management
- String manipulation for toggling “(done)” status

## 🗂️ Project Structure

## 🚀 How to Run
1. Clone or download this repository  
2. Open the project in your IDE (Eclipse, IntelliJ, VS Code, etc.)  
3. Run the main `.java` file  
4. Use the input field and buttons to manage your tasks

## 🧩 How It Works
- **Add Button / Enter Key:**  
  Adds the text from the input field to the list  
  If the field is empty, a popup warns the user

- **Remove Button / Delete Key:**  
  Removes the currently selected task  
  If nothing is selected, a popup tells the user to select an item

- **Double‑Click on a Task:**  
  Toggles the “(done)” tag  
  Example:  
  `Buy groceries` → `Buy groceries (done)`  
  `Buy groceries (done)` → `Buy groceries`

## 📚 What I Learned
- How to build a multi‑component GUI in Java Swing  
- How to use `DefaultListModel` to manage dynamic lists  
- How to handle multiple event types (mouse, keyboard, button)  
- How to provide user feedback with `JOptionPane`  
- How to structure a GUI project for GitHub

## 🔮 Future Improvements
- Add file saving/loading so tasks persist between sessions  
- Add categories or priority levels  
- Add a “Clear Completed” button  
- Add a modernized UI with custom colors and fonts  
