import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * <h1>Warehouse</h1>
 */

public class Warehouse {
	final static String folderPath = "files/";
    final static File VEHICLE_FILE = new File(folderPath + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(folderPath + "PackageList.csv");
    final static File PROFIT_FILE = new File(folderPath + "Profit.txt");
    final static File N_PACKAGES_FILE = new File(folderPath + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(folderPath + "PrimeDay.txt");
    final static double PRIME_DAY_DISCOUNT = .15;

    static boolean primeDay = false;
    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    static ArrayList<Package> packages = new ArrayList<>();
    static int numPackage = 0;
    static double profit = 0.0;
    /**
     * Main Method
     * 
     * @param args list of command line arguments
     */
    public static void main(String[] args) {
    	
    	//1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager
        //TODO: Properly load files when booting.
        try {
            packages = DatabaseManager.loadPackages(PACKAGE_FILE);
            numPackage = DatabaseManager.loadPackagesShipped(N_PACKAGES_FILE);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //2) Show menu and handle user inputs
        String option = "0";
        Scanner s = new Scanner(System.in);
            while (!option.equals("6")) {
            if (primeDay) {
                System.out.println("==========Options==========\n1) Add Package\n2) Add Vehicle\n3) Deactivate Prime Day" +
                        "\n4) Send Vehicle\n5) Print Statistics\n6) Exit\n===========================");
            } else
            {
                System.out.println("==========Options==========\n1) Add Package\n2) Add Vehicle\n3) Activate Prime Day" +
                        "\n4) Send Vehicle\n5) Print Statistics\n6) Exit\n===========================");
            }
            option = s.nextLine();
            try {
                int optionNumber = Integer.parseInt(option);
                if (optionNumber == 1){
                    String packageID;
                    String productName;
                    double weight;
                    double price;
                    String buyerName;
                    String address;
                    String city;
                    String state;
                    int zip;
                    System.out.println("Enter Package ID: ");
                    packageID = s.nextLine();
                    System.out.println("Enter Product Name: ");
                    productName = s.nextLine();
                    System.out.println("Enter Weight");
                    weight = s.nextDouble();
                    System.out.println("Enter Price: ");
                    price = s.nextDouble();
                    System.out.println("Enter Buyer Name: ");
                    s.nextLine(); //Buffer
                    buyerName = s.nextLine();
                    System.out.println("Enter Address: ");
                    address = s.nextLine();
                    System.out.println("Enter City: ");
                    city = s.nextLine();
                    System.out.println("Enter State: ");
                    state = s.nextLine();
                    System.out.println("Enter ZIP Code: ");
                    zip = s.nextInt();
                    s.nextLine(); //Buffer
                    if (primeDay)
                    {
                        price *= 0.85;
                    }
                    ShippingAddress sa = new ShippingAddress(buyerName, address, city, state, zip);
                    Package p = new Package(packageID, productName, weight, price, sa);
                    packages.add(p);
                    System.out.println("\n" + p.shippingLabel() + "\n");
                } else if (optionNumber == 2){
                    boolean valid = false;
                    while (!valid) {
                        System.out.println("Vehicle Options:\n1) Truck\n2) Drone\n3) Cargo Plane");
                        int vehicleOption = s.nextInt();
                        if (vehicleOption <= 3) {
                            valid = true;
                            String licensePlate = "";
                            int maxWeight = 0;
                            //Other params are to be edited later.
                            System.out.println("Enter License Plate No.");
                            s.nextLine(); //Buffer
                            licensePlate = s.nextLine();
                            System.out.println("Enter Maximum Carry Weight:");
                            maxWeight = s.nextInt();
                            s.nextLine(); //Buffer
                            if (vehicleOption == 1) {
                                Truck t = new Truck(licensePlate, maxWeight);
                                trucks.add(t);
                            } else if (vehicleOption == 2) {
                                Drone d = new Drone(licensePlate, maxWeight);
                                drones.add(d);
                            } else if (vehicleOption == 3) {
                                CargoPlane cp = new CargoPlane(licensePlate, maxWeight);
                                planes.add(cp);
                            }
                        } else {
                            System.out.println("Error: Option not available.");
                        }
                    }
                } else if (optionNumber == 3){
                    if (primeDay){
                        for (Package p : packages)
                        {
                            p.setPrice(p.getPrice() / 0.85);
                        }
                        primeDay = false;
                    } else
                    {
                        for (Package p : packages)
                        {
                            p.setPrice(p.getPrice() * 0.85);
                        }
                        primeDay = true;
                    }
                } else if (optionNumber == 4){
                    //TODO: Send Vehicle out for delivery
                } else if (optionNumber == 5){
                    //TODO: Print Statistics
                } else if (optionNumber == 6){
                    //TODO: Handle exit and save to files
                } else
                {
                    System.out.println("Error: Option not available.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Option not available.");
            }
        }
    	
    	
    	
    	//3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using DatabaseManager
    	
    
    }


}