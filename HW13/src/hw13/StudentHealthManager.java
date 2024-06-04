package hw13;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 管理学生成绩的主类，使用图形用户界面（GUI）
public class StudentHealthManager extends JFrame {
    private JButton checkButton, loadButton, saveButton, addButton, findButton, updateButton, deleteButton, listButton, exportButton, exportToDefaultButton;
    private JTextField nameField, heightField, weightField;
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private Map<String, Student> students; // 存储学生信息的映射

    // 构造函数，初始化GUI组件
    public StudentHealthManager() {
        super("Student Health Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new FlowLayout());

        students = new HashMap<>(); // 初始化存储学生数据的映射
        fileChooser = new JFileChooser();

        nameField = new JTextField(10);
        heightField = new JTextField(10);
        weightField = new JTextField(10);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Height (cm):"));
        add(heightField);
        add(new JLabel("Weight (kg):"));
        add(weightField);


        addButton = new JButton("Add/Update Health Data");
        addButton.addActionListener(this::addOrUpdateHealthData);
        add(addButton);


        findButton = new JButton("Find Health Data");
        findButton.addActionListener(this::findGrade);
        add(findButton);


        deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(this::deleteStudent);
        add(deleteButton);


        listButton = new JButton("List Students");
        listButton.addActionListener(this::listStudents);
        add(listButton);


        exportButton = new JButton("Export to CSV");
        exportButton.addActionListener(this::exportToCSV);
        add(exportButton);


        exportToDefaultButton = new JButton("Export to Default CSV");
        exportToDefaultButton.addActionListener(this::exportToDefaultCSV);
        add(exportToDefaultButton);

        textArea = new JTextArea(15, 50);
        add(new JScrollPane(textArea));
        loadCSVData();
        setVisible(true); // 使GUI可见
    }


    private void addOrUpdateHealthData(ActionEvent e) {
        try {
            String name = nameField.getText();
            double height = Double.parseDouble(heightField.getText());
            double weight = Double.parseDouble(weightField.getText());
            students.put(name, new Student(name, height, weight)); // 添加或更新学生健康数据
            textArea.setText("Health data added/updated for " + name);
        } catch (NumberFormatException ex) {
            textArea.setText("Invalid input. Please enter valid numbers for height and weight.");
        }
    }


    private void findGrade(ActionEvent e) {
        String name = nameField.getText();
        Student student = students.get(name);
        if (student != null) {
            textArea.setText("Found: " + student);
        } else {
            textArea.setText("Student not found: " + name);
        }
    }


    private void deleteStudent(ActionEvent e) {
        String name = nameField.getText();
        if (students.remove(name) != null) {
            textArea.setText("Student deleted: " + name);
        } else {
            textArea.setText("Student not found: " + name);
        }
    }


    private void listStudents(ActionEvent e) {
        StringBuilder builder = new StringBuilder("All Students:\n");
        students.values().forEach(student -> builder.append(student).append("\n"));
        textArea.setText(builder.toString());
    }


    private void exportToCSV(ActionEvent e) {
        if (students.isEmpty()) {
            textArea.setText("No data to export.");
            return;
        }

        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // 确保文件以 .csv 结尾
            if (!file.getName().toLowerCase().endsWith(".csv")) {
                file = new File(file.getParentFile(), file.getName() + ".csv");
            }
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.println("Name,Height (cm),Weight (kg),BMI"); // CSV文件的头部
                for (Student student : students.values()) {
                    writer.printf("%s,%.2f,%.2f,%.2f%n",
                            student.getName(),
                            student.getHeight(),
                            student.getWeight(),
                            student.getBMI());
                }
                textArea.setText("Data exported to " + file.getAbsolutePath());
            } catch (IOException ex) {
                textArea.setText("Error exporting data: " + ex.getMessage());
            }
        }
    }

    // 导出学生数据到默认CSV文件的方法
    private void exportToDefaultCSV(ActionEvent e) {
        if (students.isEmpty()) {
            textArea.setText("No data to export.");
            return;
        }

        File file = new File("students_data.csv");
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("Name,Height (cm),Weight (kg),BMI"); // CSV文件的头部
            for (Student student : students.values()) {
                writer.printf("%s,%.2f,%.2f,%.2f%n",
                        student.getName(),
                        student.getHeight(),
                        student.getWeight(),
                        student.getBMI());
            }
            textArea.setText("Data exported to " + file.getAbsolutePath());
        } catch (IOException ex) {
            textArea.setText("Error exporting data: " + ex.getMessage());
        }
    }

    private void loadCSVData() {
        File file = new File("\\Users\\User\\Desktop\\HW13\\students_data.csv");
        if (!file.exists()) {
            return; // 如果文件不存在，直接返回
        }

        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // 跳过CSV文件的头部
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length == 4) {
                    String name = data[0];
                    double height = Double.parseDouble(data[1]);
                    double weight = Double.parseDouble(data[2]);
                    students.put(name, new Student(name, height, weight));
                }
            }
            textArea.setText("Data loaded from CSV file.");
        } catch (IOException | NumberFormatException ex) {
            textArea.setText("Error loading data: " + ex.getMessage());
        }
    }

   
    public static void main(String[] args) {
        new StudentHealthManager();
    }
}