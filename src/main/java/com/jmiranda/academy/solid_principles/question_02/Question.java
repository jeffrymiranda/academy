package com.jmiranda.academy.solid_principles.question_02;

/**
 * As an example, imagine you have a payroll class which exposes a method for calculating salaries.
 * <p>
 * Say the method already returns the salary for the Junior developers role,
 * but must be extended to return the salary for seniors and principal developers.
 * <p>
 * The solution suggested by our best developer was to extend the logic inside the calculateSalary
 * method adding a switch with the new 2 cases.
 * <p>
 * What do you think is bad here?
 * Based on SOLID principle, what principle was broken?
 * What can be a better solution?
 */

public class Question {

    public static void main(String[] args) {

        final int hourReported = 40;
        // Junior
        Employee junior = new Employee("Joseph", "Junior");
        System.out.println(junior.getFullName() + " salary: $" + junior.calculateSalary(hourReported));
        // Senior
        Employee senior = new Employee("Petter", "Senior");
        System.out.println(senior.getFullName() + " salary: $" + senior.calculateSalary(hourReported));
        // Principal
        Employee principal = new Employee("John", "Principal");
        System.out.println(principal.getFullName() + " salary: $" + principal.calculateSalary(hourReported));
    }
}

class Employee {
    private final String fullName;
    private final String role;

    Employee(String fullName, String role) {
        this.fullName = fullName;
        this.role = role;
    }

    String getFullName() {
        return fullName;
    }

    double calculateSalary(final int hoursReported) {

        switch (this.role) {
            case "Junior":
                return hoursReported * 15.25;
            case "Senior":
                return hoursReported * 17.50;
            case "Principal":
                return hoursReported * 18.75;
        }
        return 0.0d;
    }
}