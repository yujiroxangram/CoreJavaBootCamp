package com.jiban.day1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("======== STUDENT MANAGEMENT SYSTEM ========");
            System.out.println("1. Add Student");
            System.out.println("2. View Student");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.println("Enter Choice: ");

            int ch = sc.nextInt();
            switch(ch) {
                case 1 -> service.addStudent();
                case 2 -> service.viewStudents();
                case 3 -> service.searchStudent();
                case 4 -> service.deleteStudent();
                case 5 -> {
                    System.out.println("Exiting!!!! Good Bye.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!\n");
            }
        }
    }
}