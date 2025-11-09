package com.jiban.day1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class StudentService {
        private final ArrayList<Student> students = new ArrayList<>();
        private final Scanner sc = new Scanner(System.in);

        public void addStudent() {
            System.out.println("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Name: ");
            String name = sc.nextLine();
            System.out.println("Enter Course: ");
            String course = sc.nextLine();
            students.add(new Student(id, name, course));
            System.out.println("Student added successfully!\n");
        }

        public void viewStudents() {
            if(students.isEmpty()) {
                System.out.println("No students found!\n");
                return;
            }
            for(Student s : students) s.display();
            System.out.println();
        }

        public void searchStudent() {
            System.out.println("Enter ID to search: ");
            int id = sc.nextInt();
            for(Student s : students) {
                if(s.getId() == id) {
                    s.display();
                    System.out.println();
                    return;
                }
            }
            System.out.println("Student not found.\n");
        }

        public void deleteStudent() {
            System.out.println("Enter ID to delete: ");
            int id = sc.nextInt();
            Iterator<Student> it = students.iterator();
            while(it.hasNext()) {
                if(it.next().getId() == id) {
                    it.remove();
                    System.out.println("Student deleted!\n");
                    return;
                }
            }
            System.out.println("No student with that ID.\n");
        }
}
