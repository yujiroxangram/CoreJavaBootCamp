package com.jiban.day1;

import java.io.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StudentService {
    private final ArrayList<Student> students = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private final File file = new File("src/com/jiban/day1/students.txt");

    public StudentService() {
        loadStudents();
    }

    public void addStudent() {
        try {
            System.out.println("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Name: ");
            String name = sc.nextLine();
            System.out.println("Enter Course: ");
            String course = sc.nextLine();

            Student s = new Student(id, name, course);
            students.add(s);
            saveStudents();
            System.out.println("Student added successfully!\n");
        } catch(Exception e) {
            System.out.println("Invalid input! Please try again!\n");
            sc.nextLine(); // clear scanner buffer
        }
    }

    public void viewStudents() {
        if(students.isEmpty()) {
            System.out.println("No students found.\n");
            return;
        }

        System.out.println("\n---- All Students ----");
        students.forEach(Student::display);
        System.out.println();
    }

    public void searchStudent() {
        System.out.println("Enter ID to search: ");
        int id = sc.nextInt();

        students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .ifPresentOrElse(
                    Student::display,
                    () -> System.out.println("Student not found!\n")
                );
        System.out.println();
    }

    public void deleteStudent() {
        System.out.println("Enter ID to delete: ");
        int id = sc.nextInt();

        boolean removed = students.removeIf(s -> s.getId() == id);
        if(removed) {
            saveStudents();
            System.out.println("Student deleted.\n");
        } else {
            System.out.println("No student found with that ID.\n");
        }
    }

    public void sortStudents() {
        if(students.isEmpty()) {
            System.out.println("No students to sort.\n");
            return;
        }

        List<Student> sorted = students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());

        System.out.println("\n---- Students sorted by Name: ----");
        sorted.forEach(Student::display);
        System.out.println();
    }

    private void saveStudents() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for(Student s : students) {
                bw.write(s.getId() + "," + s.getName() + "," + s.getCourse());
                bw.newLine();
            }
        } catch(IOException e) {
            System.out.println("Error saving data! " + e.getMessage());
        }
    }

    private void loadStudents() {
        if(!file.exists()) return;

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if(data.length == 3) {
                    students.add(new Student(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2]
                    ));
                }
            }
        } catch(IOException e) {
            System.out.println("Could not load data: " + e.getMessage());
        }
    }
}