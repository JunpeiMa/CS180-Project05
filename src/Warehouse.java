import java.io.File;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Project 5 -- Warehouse
 * <p>
 * This class runs the program and handles variables used by other classes.
 *
 * @author Kyle VandeWalle, Gloria Ma, lab sec 9
 * @version December 6, 2018
 */

public class Warehouse {
    final static String FOLDER_PATH = "files/";
    final static File VEHICLE_FILE = new File(FOLDER_PATH + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(FOLDER_PATH + "PackageList.csv");
    final static File PROFIT_FILE = new File(FOLDER_PATH + "Profit.txt");
    final static File N_PACKAGES_FILE = new File(FOLDER_PATH + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(FOLDER_PATH + "PrimeDay.txt");
    final static double PRIME_DAY_DISCOUNT = .15;

    static boolean primeDay = false;
    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    static ArrayList<Package> packages = new ArrayList<>();
    static int packagesShipped = 0;
    static double profit = 0.0;
    static int numPackage;
    static Scanner s = new Scanner(System.in);

    /**
     * Main Method
     *
     * @param args list of command line arguments
     */
    public static void main(String[] args) {

        //1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager
        packages = DatabaseManager.loadPackages(PACKAGE_FILE);
        numPackage = DatabaseManager.loadPackagesShipped(N_PACKAGES_FILE);
        primeDay = DatabaseManager.loadPrimeDay(PRIME_DAY_FILE);
        vehicles = DatabaseManager.loadVehicles(VEHICLE_FILE);
        profit = DatabaseManager.loadProfit(PROFIT_FILE);

        //2) Show menu and handle user inputs
        String option = "0";
        int optionNumber = 0;
//        Scanner s = new Scanner(System.in);
        while (!option.equals("6")) {
            numPackage = packages.size();
            if (primeDay) {
                System.out.println("==========Options==========\n1) Add Package\n2) Add Vehicle\n" +
                        "3) Deactivate Prime Day" + "\n4) Send Vehicle\n5) Print Statistics\n" +
                        "6) Exit\n===========================");
            } else {
                System.out.println("==========Options==========\n1) Add Package\n2) Add Vehicle\n" +
                        "3) Activate Prime Day" + "\n4) Send Vehicle\n5) Print Statistics\n6) Exit\n" +
                        "===========================");
            }
//            option = s.nextLine();
            try {
                optionNumber = s.nextInt();
                s.nextLine();
                if (optionNumber == 1) {
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
                    if (primeDay) {
                        price *= 0.85;
                    }
                    ShippingAddress sa = new ShippingAddress(buyerName, address, city, state, zip);
                    Package p = new Package(packageID, productName, weight, price, sa);
                    packages.add(p);
                    System.out.println("\n" + p.shippingLabel() + "\n");
                } else if (optionNumber == 2) {
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
                                vehicles.add(t);
                            } else if (vehicleOption == 2) {
                                Drone d = new Drone(licensePlate, maxWeight);
                                vehicles.add(d);
                            } else if (vehicleOption == 3) {
                                CargoPlane cp = new CargoPlane(licensePlate, maxWeight);
                                vehicles.add(cp);
                            }
                        } else {
                            System.out.println("Error: Option not available.");
                        }
                    }
                } else if (optionNumber == 3) {
                    if (primeDay) {
                        for (Package p : packages) {
                            p.setPrice(p.getPrice() / 0.85);
                        }
                        primeDay = false;
                    } else {
                        for (Package p : packages) {
                            p.setPrice(p.getPrice() * 0.85);
                        }
                        primeDay = true;
                    }
                } else if (optionNumber == 4) {
                    if (vehicles.isEmpty()) {
                        System.out.println("Error: No vehicles available.");
                    } else if (packages.isEmpty()) {
                        System.out.println("Error: No packages available.");
                    } else {
                        boolean hasTruck = false;
                        boolean hasDrone = false;
                        boolean hasCargoPlane = false;

                        for (Vehicle vehicle :
                                vehicles) {
                            if (vehicle.getClass().equals(Truck.class))
                                hasTruck = true;
                            if (vehicle.getClass().equals(Drone.class))
                                hasDrone = true;
                            if (vehicle.getClass().equals(CargoPlane.class))
                                hasCargoPlane = true;
                        }

                        int vehicleOption = 0;
                        System.out.println("Options:\n1) Send Truck\n2) Send Drone\n3) Send Cargo Plane\n" +
                                "4) Send First Available");
                        vehicleOption = s.nextInt();

                        if (vehicleOption == 1) {
                            if (hasTruck) {
                                System.out.println(
                                        "ZIP Code Options:\n1) Send to first ZIP Code\n2) Send to mode of ZIP Codes");
                                s.nextLine();
                                int zipOption = s.nextInt();
                                sendVehicleHandler(1, zipOption);
                            } else {
                                System.out.println("Error: No vehicles of selected type are available.");
                            }
                        } else if (vehicleOption == 2) {
                            if (hasDrone) {
                                System.out.println(
                                        "ZIP Code Options:\n1) Send to first ZIP Code\n2) Send to mode of ZIP Codes");
                                s.nextLine();
                                int zipOption = s.nextInt();
                                sendVehicleHandler(2, zipOption);
                            } else {
                                System.out.println("Error: No vehicles of selected type are available.");
                            }
                        } else if (vehicleOption == 3) {
                            if (hasCargoPlane) {
                                System.out.println(
                                        "ZIP Code Options:\n1) Send to first ZIP Code\n2) Send to mode of ZIP Codes");
                                int zipOption = s.nextInt();
                                sendVehicleHandler(3, zipOption);
                            } else {
                                System.out.println("Error: No vehicles of selected type are available.");
                            }
                        } else if (vehicleOption == 4) {
                            System.out.println(
                                    "ZIP Code Options:\n1) Send to first ZIP Code\n2) Send to mode of ZIP Codes");
                            int zipOption = s.nextInt();
                            if (vehicles.get(0).getClass().equals(Truck.class)) {
                                sendVehicleHandler(1, zipOption);
                            } else if (vehicles.get(0).getClass().equals(Drone.class)) {
                                sendVehicleHandler(2, zipOption);
                            } else if (vehicles.get(0).getClass().equals(CargoPlane.class)) {
                                sendVehicleHandler(3, zipOption);
                            }
                        }
                    }

                } else if (optionNumber == 5) {
                    printStatisticsReport(profit, packagesShipped, packages.size());
                } else if (optionNumber == 6) {
                } else {
                    System.out.println("Error: Option not available.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Option not available.");
            }
        }


        //3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using
        // DatabaseManager
        //
        DatabaseManager.savePackages(PACKAGE_FILE, packages);
        DatabaseManager.saveVehicles(VEHICLE_FILE, vehicles);
        DatabaseManager.savePackagesShipped(N_PACKAGES_FILE, packagesShipped);
        DatabaseManager.savePrimeDay(PRIME_DAY_FILE, primeDay);
        DatabaseManager.saveProfit(PROFIT_FILE, profit);

    }

    public static void printStatisticsReport(double profits, int packagesShip, int numberOfPackages) {
        System.out.printf("==========Statistics==========\nProfits:                 $%.2f", profits);
        System.out.println("\nPackages Shipped:                " + packagesShip
                + "\nPackages in Warehouse:           " + numberOfPackages + "\n==============================");
    }

    public static void sendVehicleHandler(int type, int zipOption) {
        Vehicle vehicleToSend = null;
        if (type == 1) {
            for (Vehicle vehicle :
                    vehicles) {
                if (vehicle.getClass().equals(Truck.class)) {
                    vehicleToSend = vehicle;
                    break;
                }
            }
        } else if (type == 2) {
            for (Vehicle vehicle :
                    vehicles) {
                if (vehicle.getClass().equals(Drone.class)) {
                    vehicleToSend = vehicle;
                    break;
                }
            }
        } else if (type == 3) {
            for (Vehicle vehicle :
                    vehicles) {
                if (vehicle.getClass().equals(CargoPlane.class)) {
                    vehicleToSend = vehicle;
                    break;
                }
            }
        }


        ArrayList warehouseZipcodes = new ArrayList<Integer>();

        int maxFrequency = 0;

        if (zipOption == 1)
            vehicleToSend.setZipDest(packages.get(0).getDestination().getZipCode());


        else if (zipOption == 2)
            for (int i = 0; i < packages.size(); i++) {
                if (packages.get(i) != null) {
                    warehouseZipcodes.add(packages.get(i).getDestination().getZipCode());
                }
            }

        for (int i = 0; i < warehouseZipcodes.size(); i++) {
            if (maxFrequency < Collections.frequency(warehouseZipcodes, warehouseZipcodes.get(i))) {
                maxFrequency = Collections.frequency(warehouseZipcodes, warehouseZipcodes.get(i));
                vehicleToSend.setZipDest((int) warehouseZipcodes.get(i));
            }
        }

        vehicleToSend.fill(packages);
        packagesShipped += vehicleToSend.getPackages().size();
        profit += vehicleToSend.getProfit();
        System.out.println(vehicleToSend.report());
        for (Package p :
                vehicleToSend.getPackages()) {
            packages.remove(p);
        } //Not sure if it removes packages that shouldn't ve removed;
        vehicles.remove(vehicleToSend);

    }
}

//Fix saving and loading vehicles.
//Fix whatever error there is for ShippingLabels
//getCurrentWeight and getProfit for all subclasses of vehicle