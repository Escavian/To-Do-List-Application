package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;

/**
 * Jonathan A Colon
 * COP2800 Programming in Java
 * Instructor Mubarak Banisakher, PH.D.
 * 04/29/2026 Project - 9 - To-Do List Application
 *
 * Simple To-Do List application using Java Swing
 * Demonstrates: JFrame, JList, DefaultListModel, JTextField, JButton, layout managers, ActionListener
 */
public class TodoListApp {

    // File to save/load the to-do items
    private static final String SAVE_FILE = "todo_list.txt";

    // GUI components
    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JList<String> todoList;
    private JTextField inputField;
    private JButton addButton;
    private JButton removeButton;

    // Constructor to set up the GUI and load existing to-dos
    public TodoListApp() {
        initComponents();
        loadTodos();
    }

    private void initComponents() {
        // JFrame: top-level container (Chapter 14)
        frame = new JFrame("To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(475, 360);
        frame.setLocationRelativeTo(null);

        // DefaultListModel + JList: display component (Chapter 14)
        listModel = new DefaultListModel<>();
        todoList = new JList<>(listModel);
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        todoList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane listScroll = new JScrollPane(todoList);

        // Double-click toggles completion (event handling)
        todoList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = todoList.locationToIndex(e.getPoint());
                    if (index >= 0) toggleComplete(index);
                }
            }
        });

        // Keyboard Delete removes selected item
        todoList.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) removeSelected();
            }
        });

        // Input field and buttons (input components)
        inputField = new JTextField(20);
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        removeButton.setToolTipText("Remove selected item (or press Delete key)");

        // ActionListener for Add button and Enter key
        ActionListener addAction = e -> addItem();
        addButton.addActionListener(addAction);
        inputField.addActionListener(addAction);

        // Remove button action
        removeButton.addActionListener(e -> removeSelected());

        // Layout: BorderLayout with a control panel (FlowLayout)
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.add(new JLabel("New item >> "));
        controlPanel.add(inputField);
        controlPanel.add(addButton);
        controlPanel.add(removeButton);

        frame.getContentPane().setLayout(new BorderLayout(8, 8));
        frame.getContentPane().add(listScroll, BorderLayout.CENTER);
        frame.getContentPane().add(controlPanel, BorderLayout.SOUTH);

        // Save on close
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                saveTodos();
            }
        });

        frame.setVisible(true);
    }

    private void addItem() {
        String text = inputField.getText().trim();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a to-do item.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        listModel.addElement(text);
        inputField.setText("");
        inputField.requestFocusInWindow();
    }

    private void removeSelected() {
        int idx = todoList.getSelectedIndex();
        if (idx >= 0) {
            listModel.remove(idx);
        } else {
            JOptionPane.showMessageDialog(frame, "Select an item to remove.", "No selection", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Toggle completion status by adding/removing " (done)" suffix
    private void toggleComplete(int index) {
        String item = listModel.get(index);
        final String DONE = " (done)";
        if (item.endsWith(DONE)) {
            item = item.substring(0, item.length() - DONE.length());
        } else {
            item = item + DONE;
        }
        listModel.set(index, item);
    }

    private void saveTodos() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAVE_FILE))) {
            for (int i = 0; i < listModel.size(); i++) {
                writer.write(listModel.get(i));
                writer.newLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Failed to save todos: " + ex.getMessage(), "Save error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTodos() {
        Path p = Paths.get(SAVE_FILE);
        if (Files.exists(p)) {
            try {
                List<String> lines = Files.readAllLines(p);
                for (String line : lines) {
                    if (!line.trim().isEmpty()) listModel.addElement(line);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Failed to load todos: " + ex.getMessage(), "Load error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TodoListApp::new);
    }
}