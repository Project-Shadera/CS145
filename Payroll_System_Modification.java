// Name: Drakin Woodell
// Date: 4/25/2024
// Class: CS&145
// Assignment: Lab 4 - Payroll System Modification

import java.util.Scanner;

abstract class Employee {
    String firstName;
    String lastName;
    String ssn;
    String birthDate;

    public Employee(String firstName, String lastName, String ssn, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.birthDate = birthDate;
    }

    abstract double earnings();
}

class SalariedEmployee extends Employee {
    double weeklySalary;

    public SalariedEmployee(String firstName, String lastName, String ssn, String birthDate, double weeklySalary) {
        super(firstName, lastName, ssn, birthDate);
        this.weeklySalary = weeklySalary;
    }

    @Override
    double earnings() {
        return weeklySalary;
    }
}

class HourlyEmployee extends Employee {
    double hourlyWage;
    double hoursWorked;

    public HourlyEmployee(String firstName, String lastName, String ssn, String birthDate, double hourlyWage, double hoursWorked) {
        super(firstName, lastName, ssn, birthDate);
        this.hourlyWage = hourlyWage;
        this.hoursWorked = hoursWorked;
    }

    @Override
    double earnings() {
        return hourlyWage * hoursWorked;
    }
}

class CommissionEmployee extends Employee {
    double grossSales;
    double commissionRate;

    public CommissionEmployee(String firstName, String lastName, String ssn, String birthDate, double grossSales, double commissionRate) {
        super(firstName, lastName, ssn, birthDate);
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }

    @Override
    double earnings() {
        return grossSales * commissionRate;
    }
}

class BasePlusCommissionEmployee extends CommissionEmployee {
    double baseSalary;

    public BasePlusCommissionEmployee(String firstName, String lastName, String ssn, String birthDate, double grossSales, double commissionRate, double baseSalary) {
        super(firstName, lastName, ssn, birthDate, grossSales, commissionRate);
        this.baseSalary = baseSalary;
    }

    @Override
    double earnings() {
        return baseSalary + super.earnings();
    }
}

public class Payroll_System_Modification {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt for the current month
        System.out.print("Enter the current month (1 - 12): ");
        int month = scanner.nextInt();
        scanner.nextLine();
        
        Employee[] employees = new Employee[4];
        
        // Input the employee details
        for (int i = 0; i < 4; i++) {
            System.out.println("\nEnter details for Employee " + (i + 1) + ":");
            System.out.print("First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Social Security Number: ");
            String ssn = scanner.nextLine();
            System.out.print("Birth Date (YYYY-MM-DD): ");
            String birthDate = scanner.nextLine();
            
            double value1, value2, value3;
            switch (i) {
                case 0: // Salaried Employee
                    System.out.print("Weekly Salary: $");
                    value1 = scanner.nextDouble();
                    employees[i] = new SalariedEmployee(firstName, lastName, ssn, birthDate, value1);
                    break;
                case 1: // Hourly Employee
                    System.out.print("Hourly Wage: $");
                    value1 = scanner.nextDouble();
                    System.out.print("Hours Worked: ");
                    value2 = scanner.nextDouble();
                    employees[i] = new HourlyEmployee(firstName, lastName, ssn, birthDate, value1, value2);
                    break;
                case 2: // Commission Employee
                    System.out.print("Gross Sales: $");
                    value1 = scanner.nextDouble();
                    System.out.print("Commission Rate: ");
                    value2 = scanner.nextDouble();
                    employees[i] = new CommissionEmployee(firstName, lastName, ssn, birthDate, value1, value2);
                    break;
                case 3: // Base-Commission Employee
                    System.out.print("Gross Sales: $");
                    value1 = scanner.nextDouble();
                    System.out.print("Commission Rate: ");
                    value2 = scanner.nextDouble();
                    System.out.print("Base Salary: $");
                    value3 = scanner.nextDouble();
                    employees[i] = new BasePlusCommissionEmployee(firstName, lastName, ssn, birthDate, value1, value2, value3);
                    break;
            }
            scanner.nextLine();
        }
        
        // Print employee details and earnings for the month
        for (Employee employee : employees) {
            System.out.println("\n" + employee.getClass().getSimpleName() + " Employee: " + employee.firstName + " " + employee.lastName);
            System.out.println("Social security number: " + employee.ssn);
            System.out.println("Birth date: " + employee.birthDate);
            System.out.println("Earned for month " + month + ": $" + employee.earnings());
        }
    }
}