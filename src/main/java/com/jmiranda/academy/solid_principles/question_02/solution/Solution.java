package com.jmiranda.academy.solid_principles.question_02.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * The Open Closed Principle states that objects should be open for extension and closed for modification.
 *
 * Characteristics:
 * - Relies on abstraction and polymorphism
 *
 * <p>
 * As an example, imagine you have a payroll class which exposes a method for calculating salaries.
 * <p>
 * Say the method already returns the salary for the Junior developers role, but must be extended to return the salary for seniors and principal developers.
 * <p>
 * You could add a parameter to the method that specifies the employee role, junior, senior or principal, and rewrite the method to handle the 3 options,
 * but this breaks the open closed principle because the existing shape class should be closed for modification.
 * <p>
 * Instead, abstraction and polymorphism can be employed to extend the functionality with separate junior, senior and principal classes.
 * <p>
 * Further, this would allow other subclasses to be added in the future for different roles without the need to change the existing shape class.
 * <p>
 * This principle makes classes easier to maintain, because abstraction and polymorphism abstract the logic of super classes from subclasses,
 * so there are less dependencies in the classes. Code is generally more robust if it's not constantly changing.
 * <p>
 * This allows a constant speed of unit tests, for example. So the open closed principle uses class inheritance in interfaces to allow new functionality
 * to be added without changing existing code. The open closed principle is primarily adhered to by taking advantage of abstraction and polymorphism,
 * which are standard concepts in object oriented program.
 */

public class Solution {

    public static void main(String[] args) {

        final int hourReported = 40;

        List<Employee> employees = new ArrayList<>();
        employees.add(new Junior("Joseph", hourReported));
        employees.add(new Senior("Petter", hourReported));
        employees.add(new Principal("John", hourReported));

        employees.forEach(employee -> System.out.println(employee.getFullName() + " salary: $" + employee.calculateSalary()));
    }
}

// The following annotation is going to helps us to restrict this interface to not have more than one functionality (single method)
// somehow we can keep the single responsibility this interface.
@FunctionalInterface
interface IPayroll {
    double calculateSalary();
}

// Abstract class can be also a solution if we want to add attr or more methods. Be care full here to not break the single responsibility principle.
abstract class Employee implements IPayroll {

    protected final String fullName;
    protected final int hoursReported;

    public Employee(String fullName, int hoursReported) {
        this.fullName = fullName;
        this.hoursReported = hoursReported;
    }

    String getFullName() {
        return fullName;
    }
}

class Junior extends Employee {

    public Junior(String fullName, int hoursReported) {
        super(fullName, hoursReported);
    }

    @Override
    public double calculateSalary() {
        return this.hoursReported * 15.25;
    }
}

class Senior extends Employee {

    public Senior(String fullName, int hoursReported) {
        super(fullName, hoursReported);
    }

    @Override
    public double calculateSalary() {
        return this.hoursReported * 17.50;
    }
}

class Principal extends Employee {

    public Principal(String fullName, int hoursReported) {
        super(fullName, hoursReported);
    }

    @Override
    public double calculateSalary() {
        return this.hoursReported * 18.75;
    }
}
