import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

interface VehicleInterface {  //Interface class
    void displayDetails();
    void printDetails(FileWriter writer) throws IOException;
}

abstract class Vehicle implements VehicleInterface {    //Abstract class
    protected String registrationNumber;
    protected String model;
    protected String manufacturer;
    protected String dateOfProduction;
    protected ArrayList<Service> services;  // composition, Association, ArrayList, Array of objects

    public Vehicle(String model, String manufacturer, String dateOfProduction) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.dateOfProduction = dateOfProduction;
        this.services = new ArrayList<Service>();
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber){
        this.registrationNumber= registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Service getService(String serviceId) {
        for (Service service : services) {
            if (service.getId().equals(serviceId)) {
                return service;
            }
        }
        return null;
    }

    public void setService(Service service) {
        services.add(service);  
    }

    public int getServicesAmount() {
        return services.size();
    }

    @Override
    public void displayDetails() {
        System.out.println("\nVehicle Details-");
        System.out.println("Registration Number: " + registrationNumber);
        System.out.println("Model: " + model);
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Date of Production: " + dateOfProduction);
        for (Service service : services) {
            service.displayDetails();
        }
    }

    @Override
    public void printDetails(FileWriter writer) throws IOException {    //Printing to File
        writer.write("\nVehicle Details-\n");
        writer.write("Registration Number: " + registrationNumber + "\n");
        writer.write("Model: " + model + "\n");
        writer.write("Manufacturer: " + manufacturer + "\n");
        writer.write("Date of Production: " + dateOfProduction + "\n");
        for (Service service : services) {
            service.printDetails(writer);
        }
    }
}

class Service {
    private String id;
    private String type;
    private double cost;
    private String serviceDate;
    private String dateOfPickup;

    public Service(String id, String type, double cost, String serviceDate, String dateOfPickup) {
        this.id = id;
        this.type = type;
        this.cost = cost;
        this.serviceDate = serviceDate;
        this.dateOfPickup = dateOfPickup;
    }

    public String getId() {  
        return id;
    }

    public void displayDetails() {  
        System.out.println("Service ID: " + id);
        System.out.println("Service Type: " + type);
        System.out.println("Service Cost: " + cost);
        System.out.println("Service Date: " + serviceDate);
        System.out.println("Date of Pickup: " + dateOfPickup);
    }

    public void printDetails(FileWriter writer) throws IOException {    //Printing to File
        writer.write("Service ID: " + id + "\n");
        writer.write("Service Type: " + type + "\n");
        writer.write("Service Cost: " + cost + "\n");
        writer.write("Service Date: " + serviceDate + "\n");
        writer.write("Date of Pickup: " + dateOfPickup + "\n");
    }
}

class Car extends Vehicle { //Inheritance
    private int numberOfSeats;

    public Car(String model, String manufacturer, String dateOfProduction, int numberOfSeats) {  
        super(model, manufacturer, dateOfProduction);
        this.numberOfSeats = numberOfSeats;
    }

    public int getPassengerCapacity() { 
        return numberOfSeats;
    }

    @Override  //polymorphism
    public void displayDetails() {  
        super.displayDetails();
        System.out.println("Number of Seats: " + numberOfSeats);
    }

    @Override //polymorphism
    public void printDetails(FileWriter writer) throws IOException {    //Printing to File
        super.printDetails(writer);
        writer.write("Number of Seats: " + numberOfSeats + "\n");
    }
}

class Van extends Vehicle { //Inheritance
    private int passengerCapacity;

    public Van(String model, String manufacturer, String dateOfProduction, int passengerCapacity) { 
        super(model, manufacturer, dateOfProduction);
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override //polymorphism
    public void displayDetails() { 
        super.displayDetails();
        System.out.println("Passenger Capacity: " + passengerCapacity);
    }

    @Override //polymorphism
    public void printDetails(FileWriter writer) throws IOException {    //Printing to File
        super.printDetails(writer);
        writer.write("Passenger Capacity: " + passengerCapacity + "\n");
    }
}

class Bus extends Vehicle { //Inheritance
    private int passengerCapacity;

    public Bus(String model, String manufacturer, String dateOfProduction, int passengerCapacity) { 
        super(model, manufacturer, dateOfProduction);
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override //polymorphism
    public void displayDetails() {  
        super.displayDetails();
        System.out.println("Passenger Capacity: " + passengerCapacity);
    }

    @Override //polymorphism
    public void printDetails(FileWriter writer) throws IOException {    //Printing to File
        super.printDetails(writer);
        writer.write("Passenger Capacity: " + passengerCapacity + "\n");
    }
}

class Warehouse {
    private String location;
    private int capacity;
    private ArrayList<Vehicle> vehicles; // Aggregation, Association, ArrayList, Array of objects

    public Warehouse(String location, int capacity, ArrayList<Vehicle> vehicles) { 
        this.location = location;
        this.capacity = capacity;
        this.vehicles = vehicles;
    }

    public void setLocation(String location) { 
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setCapacity(int capacity) { 
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addVehicle(Vehicle vehicle) throws Exception {  //Exception Handling
        if (vehicles.size() < capacity) {
            vehicles.add(vehicle);
        } else {
            throw new Exception("Warehouse capacity exceeded.");
        }
    }

    public void removeVehicle(String registrationNumber) {
        vehicles.removeIf(vehicle -> vehicle.getRegistrationNumber().equals(registrationNumber));  
    }

    public Vehicle getVehicle(String registrationNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getRegistrationNumber().equals(registrationNumber)) {
                return vehicle;
            }
        }
        return null;
    }

    public ArrayList<Vehicle> getAllAvailableCars() {
        ArrayList<Vehicle> cars = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Car) {
                cars.add(vehicle);
            }
        }
        return cars;
    } 

    public ArrayList<Vehicle> getAllAvailableVans() {
        ArrayList<Vehicle> vans = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Van) {
                vans.add(vehicle);
            }
        }
        return vans;
    } 

    public ArrayList<Vehicle> getAllAvailableBuses() {
        ArrayList<Vehicle> bus = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Bus) {
                bus.add(vehicle);
            }
        }
        return bus;
    } 

    public void displayWarehouseDetails() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("VRMS");
        System.out.println("------------------");
        System.out.println("Warehouse Location: " + location);
        System.out.println("Warehouse Capacity: " + capacity);
        for (Vehicle vehicle : vehicles) {
            vehicle.displayDetails();
        }
    }

    public void printWarehouseDetails(FileWriter writer) throws IOException {   //Prining to File
        writer.write("VRMS\n");
        writer.write("------------------\n\n");
        writer.write("Warehouse Location: " + location + "\n");
        writer.write("Warehouse Capacity: " + capacity + "\n\n");
        for (Vehicle vehicle : vehicles) {
            vehicle.printDetails(writer);
        }
    }
}

class Renter {
    private String name;
    private ArrayList<Rental> rentals; //ArrayList, Array of objects, Composition, Association

    public Renter(String name) {
        this.name = name;
        this.rentals = new ArrayList<>();
    }

    public String getName() { 
        return name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public void removeRental(Rental rental) {  
        rentals.remove(rental);
    }

    public ArrayList<Rental> getRentals(){           
        return rentals;
    }

    public void printDetails(FileWriter writer) throws IOException {    //Printing to File
        writer.write("\nRenter: " + name + "\n");
        for (Rental rental : rentals) {
            writer.write("Rental ID: " + rental.getRentalID() + "\n");
            writer.write("Rental Date: " + rental.getRentalDate() + "\n");
            writer.write("Return Date: " + rental.getReturnDate() + "\n");
            writer.write("Vehicle: " + rental.getVehicle().getModel() + "\n");
            writer.write("---\n");
        }
    }
}

class Rental {
    private String rentalID;
    private String rentalDate;
    private String returnDate;
    private Vehicle vehicle;

    public Rental(String rentalID, String rentalDate, String returnDate, Vehicle vehicle) {  
        this.rentalID = rentalID;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.vehicle = vehicle;
    }

    public String getRentalID() {
        return rentalID;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        Warehouse warehouse = new Warehouse("Central", 5, vehicles);
        ArrayList<Renter> renters = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Welcome to Vehicle Rental Management System (VRMS)!\n" + "\nPress Enter to continue.");
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();

        //Menu Loop
        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            System.out.print("\033[H\033[2J");
            System.out.flush(); //clear screen

            switch (choice) {
                case 1:
                    addVehicle(scanner, warehouse);
                    break;
                case 2:
                    displayWarehouseDetails(warehouse);
                    break;
                case 3:
                    changeWarehouseSpecifications(scanner, warehouse);
                    break;
                case 4:
                    addRental(scanner, warehouse, renters);
                    break;
                case 5:
                    displayRentals(renters);
                    break;
                case 6:
                    addService(scanner, warehouse);
                    break;
                case 7:
                    viewVehiclesByType(scanner, warehouse);
                    break;
                case 8:
                    exit = true;
                    saveToFile(warehouse, renters);
                    System.out.println("Thank you for using VRMS. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Please select an option:");
        System.out.println("1. Add Vehicle");
        System.out.println("2. Display Warehouse Details");
        System.out.println("3. Change Warehouse Specifications");
        System.out.println("4. Add Rental");
        System.out.println("5. Display Rentals");
        System.out.println("6. Add Service to a Vehicle");
        System.out.println("7. View Vehicles by Type");
        System.out.println("8. Exit and Print to File");
        System.out.print("Your choice: ");
    }

    private static void addVehicle(Scanner scanner, Warehouse warehouse) {
        System.out.println("Enter vehicle type (Car/Van/Bus): ");
        String type = scanner.nextLine();
        while (!(type.toLowerCase().equals("car")) && !(type.toLowerCase().equals("van")) && !(type.toLowerCase().equals("bus"))) {
            System.out.println("Wrong vehicle type. Enter again: ");
            type = scanner.nextLine();
        }
    
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
    
        System.out.println("Enter manufacturer: ");
        String manufacturer = scanner.nextLine();
    
        boolean validRegNumber = false;
        String regNumber = "";
        while (!validRegNumber) {
            System.out.println("Enter Registration Number: ");
            regNumber = scanner.nextLine();
            validRegNumber = true;
            for (Vehicle v : warehouse.getAllAvailableCars()) {
                if (v.getRegistrationNumber().equals(regNumber)) {
                    validRegNumber = false;
                    break;
                }
            }
            for (Vehicle v : warehouse.getAllAvailableVans()) {
                if (v.getRegistrationNumber().equals(regNumber)) {
                    validRegNumber = false;
                    break;
                }
            }
            for (Vehicle v : warehouse.getAllAvailableBuses()) {
                if (v.getRegistrationNumber().equals(regNumber)) {
                    validRegNumber = false;
                    break;
                }
            }
            if (!validRegNumber) {
                System.out.println("Registration number already exists. Please enter a unique registration number.");
            }
        }
    
        System.out.println("Enter date of production (YYYY-MM-DD): ");
        String dateOfProduction = scanner.nextLine();
    
        try {
            Vehicle vehicle;
            switch (type.toLowerCase()) {
                case "car":
                    System.out.println("Enter number of seats: ");
                    int numberOfSeats = scanner.nextInt();
                    scanner.nextLine();
    
                    vehicle = new Car(model, manufacturer, dateOfProduction, numberOfSeats);
                    vehicle.setRegistrationNumber(regNumber);
                    break;
                case "van":
                    System.out.println("Enter passenger capacity: ");
                    int vanCapacity = scanner.nextInt();
                    scanner.nextLine();
                    vehicle = new Van(model, manufacturer, dateOfProduction, vanCapacity);
                    vehicle.setRegistrationNumber(regNumber);
                    break;
                case "bus":
                    System.out.println("Enter passenger capacity: ");
                    int busCapacity = scanner.nextInt();
                    scanner.nextLine();
                    vehicle = new Bus(model, manufacturer, dateOfProduction, busCapacity);
                    vehicle.setRegistrationNumber(regNumber);
                    break;
                default:
                    System.out.println("Invalid vehicle type. Vehicle not added.");
                    return;
            }
    
            warehouse.addVehicle(vehicle);
            System.out.println("Vehicle added successfully!");
            Scanner scannerV = new Scanner(System.in);
            scannerV.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {
            System.out.println("Error adding vehicle: " + e.getMessage());
        }
    }
    
    private static void displayWarehouseDetails(Warehouse warehouse) {
        warehouse.displayWarehouseDetails();
        System.out.println("\nPress any button to continue.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void changeWarehouseSpecifications(Scanner scanner, Warehouse warehouse) {
        System.out.println("Enter new warehouse location: ");
        String newLocation = scanner.nextLine();
        warehouse.setLocation(newLocation);

        System.out.println("Enter new warehouse capacity: ");
        int newCapacity = scanner.nextInt();
        scanner.nextLine(); 
        warehouse.setCapacity(newCapacity);

        System.out.println("Warehouse specifications updated successfully!");

        System.out.println("\nPress any key to go back.");
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void addRental(Scanner scanner, Warehouse warehouse, ArrayList<Renter> renters) {
        System.out.println("Enter renter's name: ");
        String renterName = scanner.nextLine();

        System.out.println("Enter rental ID: ");
        String rentalID = scanner.nextLine();

        System.out.println("Enter rental date (YYYY-MM-DD): ");
        String rentalDate = scanner.nextLine();

        System.out.println("Enter return date (YYYY-MM-DD): ");
        String returnDate = scanner.nextLine();

        
        System.out.println("Enter vehicle registration number: ");
        String regNumber = scanner.nextLine();

        Vehicle vehicle = warehouse.getVehicle(regNumber);

        boolean found = false;
        while (found == false){
        if (vehicle == null) {
            System.out.println("Vehicle not found. Rental not added.");
            System.out.println("Enter vehicle registration number again OR enter 'B' to go back.");
            String regNumber2 = scanner.nextLine();
            if (regNumber2.toLowerCase().equals("b")){
                return;
            }else{vehicle = warehouse.getVehicle(regNumber2);}
        }else{found = true;}
        }

        Rental rental = new Rental(rentalID, rentalDate, returnDate, vehicle);
        Renter renter = new Renter(renterName);
        renter.addRental(rental);
        renters.add(renter);
        System.out.println("Rental added successfully for " + renterName);

        System.out.println("\nPress any key to go back.");
        Scanner scannerR = new Scanner(System.in);
        scannerR.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void displayRentals(ArrayList<Renter> renters) {
        if (renters.isEmpty()) {
            System.out.println("No rentals available.");
        } else {
            for (Renter renter : renters) {
                System.out.println("\nRenter: " + renter.getName());
                for (Rental rental : renter.getRentals()) {
                    System.out.println("Rental ID: " + rental.getRentalID());
                    System.out.println("Rental Date: " + rental.getRentalDate());
                    System.out.println("Return Date: " + rental.getReturnDate());
                    System.out.println("Vehicle: " + rental.getVehicle().getModel());
                    System.out.println("---");
                }
            }

            System.out.println("\nPress any key to go back.");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    private static void addService(Scanner scanner, Warehouse warehouse) {
        Vehicle vehicle = null;
        while (vehicle == null) {
            System.out.println("Enter vehicle registration number: ");
            String regNumber = scanner.nextLine();
            vehicle = warehouse.getVehicle(regNumber);
            if (vehicle == null) {
                System.out.println("Vehicle not found. Please enter the registration number again.");
            }
        }

        System.out.println("Enter service ID: ");
        String serviceId = scanner.nextLine();

        System.out.println("Enter service type: ");
        String serviceType = scanner.nextLine();

        System.out.println("Enter service cost: ");
        double serviceCost = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter service date (YYYY-MM-DD): ");
        String serviceDate = scanner.nextLine();

        System.out.println("Enter date of pickup (YYYY-MM-DD): ");
        String dateOfPickup = scanner.nextLine();

        Service service = new Service(serviceId, serviceType, serviceCost, serviceDate, dateOfPickup);
        vehicle.setService(service);
        System.out.println("Service added successfully!");
        Scanner scannerS = new Scanner(System.in);
        scannerS.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void viewVehiclesByType(Scanner scanner, Warehouse warehouse) {
        System.out.println("Choose vehicle type (enter 1,2 or 3):");
        System.out.println("1. Car");
        System.out.println("2. Van");
        System.out.println("3. Bus");
        System.out.print("Your choice: ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        ArrayList<Vehicle> vehicles = null;

        switch (typeChoice) {
            case 1:
                vehicles = warehouse.getAllAvailableCars();
                break;
            case 2:
                vehicles = warehouse.getAllAvailableVans();
                break;
            case 3:
                vehicles = warehouse.getAllAvailableBuses();
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
                return;
        }

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles of this type available.");
        } else {
            for (Vehicle vehicle : vehicles) {
                vehicle.displayDetails();
            }
        }

        System.out.println("\nPress any key to go back.");
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void saveToFile(Warehouse warehouse, ArrayList<Renter> renters) {
        try (FileWriter writer = new FileWriter("VRMS_Output.txt")) {
            warehouse.printWarehouseDetails(writer);
            writer.write("\nRenters\n");
            writer.write("------------------");
            for (Renter renter : renters) {
                renter.printDetails(writer);
            }
            System.out.println("Data saved to VRMS_Output.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
