package com.ps;

import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter MySQL username: ");
        String username = scanner.nextLine();

        System.out.print("Enter MySQL password: ");
        String password = scanner.nextLine();


        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/car_dealership");
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        VehicleDao vehicleDao = new VehicleDao(dataSource);
        DealershipDao dealershipDao = new DealershipDao(dataSource);


        int choice;
        do {
            System.out.println("\n--- Car Dealership Menu ---");
            System.out.println("1) Show All Vehicles");
            System.out.println("2) Search Vehicles by Model");
            System.out.println("3) Add New Vehicle");
            System.out.println("4) Delete Vehicle by VIN");
            System.out.println("5) Create Sales Contract");
            System.out.println("6) Create Lease Contract");
            System.out.println("0) Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    List<Vehicle> allVehicles = vehicleDao.getAll();
                    allVehicles.forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Enter model: ");
                    String keyword = scanner.nextLine();
                    List<Vehicle> results = vehicleDao.searchByVehicleModelMake(keyword);
                    results.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Enter VIN: ");
                    String vin = scanner.nextLine();
                    System.out.print("Enter year: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter make: ");
                    String make = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter color: ");
                    String color = scanner.nextLine();
                    System.out.print("Enter mileage: ");
                    int mileage = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter price: ");
                    double price = Double.parseDouble(scanner.nextLine());

                    Vehicle newVehicle = new Vehicle(vin, year, make, model, color, mileage, price);
                    vehicleDao.add(newVehicle);
                    break;
                case 4:
                    System.out.print("Enter VIN to delete: ");
                    String deleteVin = scanner.nextLine();
                    vehicleDao.delete(deleteVin);
                    break;
                case 5:
                    System.out.print("Enter VIN: ");
                    String vinSale = scanner.nextLine();
                    System.out.print("Enter customer name: ");
                    String customerNameSale = scanner.nextLine();
                    System.out.print("Enter sales price: ");
                    double salesPrice = Double.parseDouble(scanner.nextLine());
                    String saleDate = java.time.LocalDate.now().toString();

                    SalesContract salesContract = new SalesContract(vinSale, customerNameSale, salesPrice, saleDate);
                    dealershipDao.saveSalesContract(salesContract);
                    break;
                case 6:
                    System.out.print("Enter VIN: ");
                    String vinLease = scanner.nextLine();
                    System.out.print("Enter customer name: ");
                    String customerNameLease = scanner.nextLine();
                    System.out.print("Enter monthly payment: ");
                    double monthlyPayment = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter lease term (months): ");
                    int leaseTerm = Integer.parseInt(scanner.nextLine());
                    String leaseDate = java.time.LocalDate.now().toString();

                    LeaseContract leaseContract = new LeaseContract(vinLease, customerNameLease, monthlyPayment, leaseTerm, leaseDate);
                    dealershipDao.saveLeaseContract(leaseContract);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}