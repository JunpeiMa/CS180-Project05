import java.io.*;
import java.util.ArrayList;

/**
 * <h1>Database Manager</h1>
 * <p>
 * Used to locally save and retrieve data.
 */
public class DatabaseManager {

    /**
     * Creates an ArrayList of Vehicles from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of vehicles
     */
    public static ArrayList<Vehicle> loadVehicles(File file) throws IOException {
        FileReader fre = null;
        BufferedReader bre = null;
        ArrayList<Vehicle> Vehicles = new ArrayList<>();
        try {
            fre = new FileReader(file);
            bre = new BufferedReader(fre);
        } catch (FileNotFoundException e) {
            return new ArrayList<Vehicle>();
        }


        String line;
        while ((line = bre.readLine()) != null) {
            String[] attributes = line.split(",");
            String licensePlate = attributes[1];
            double maxWeight = Double.parseDouble(attributes[2]);
            if (attributes[0].equals("CargoPlane")) {
                Vehicles.add(new CargoPlane(licensePlate, maxWeight));
            }

            if (attributes[0].equals("Truck")) {
                Vehicles.add(new Truck(licensePlate, maxWeight));
            }

            if (attributes[0].equals("Drone")) {
                Vehicles.add(new Drone(licensePlate, maxWeight));

            }

        }
        return Vehicles;
    }


    /**
     * Creates an ArrayList of Packages from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     * <p>
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of packages
     */
    public static ArrayList<Package> loadPackages(File file) throws IOException {
        FileReader fre = null;
        BufferedReader bre = null;
        ArrayList<Package> packages = new ArrayList<>();
        try {
            fre = new FileReader(file);
            bre = new BufferedReader(fre);
        } catch (FileNotFoundException e) {
            return new ArrayList<Package>();
        }

        String line;
        while ((line = bre.readLine()) != null) {
            String attributes[] = line.split(",");
            packages.add(new Package(attributes[0],attributes[1], Double.parseDouble(attributes[2]), Double.parseDouble(attributes[3]), new ShippingAddress(attributes[4], attributes[5], attributes[6], attributes[7],Integer.parseInt(attributes[8]))));
        }
        return packages;
    }


    /**
     * Returns the total Profits from passed text file. If the file does not exist 0
     * will be returned.
     *
     * @param file file where profits are stored
     * @return profits from file
     */
    public static double loadProfit(File file) throws IOException {
        FileReader fre;
        BufferedReader bre;

        try {
            fre = new FileReader(file);
            bre = new BufferedReader(fre);
        } catch (FileNotFoundException e) {
            return 0.0;
        }

        String line = bre.readLine();
        return Double.parseDouble(line);
    }


    /**
     * Returns the total number of packages shipped stored in the text file. If the
     * file does not exist 0 will be returned.
     *
     * @param file file where number of packages shipped are stored
     * @return number of packages shipped from file
     */
    public static int loadPackagesShipped(File file) throws IOException {

        FileReader fre;
        BufferedReader bre;

        try {
            fre = new FileReader(file);
            bre = new BufferedReader(fre);
        } catch (FileNotFoundException e) {
            return 0;
        }
        String line = bre.readLine();
        return Integer.parseInt(line);

    }


    /**
     * Returns whether or not it was Prime Day in the previous session. If file does
     * not exist, returns false.
     *
     * @param file file where prime day is stored
     * @return whether or not it is prime day
     */
    public static boolean loadPrimeDay(File file) throws IOException {

        FileReader fre;
        BufferedReader bre;

        try {
            fre = new FileReader(file);
            bre = new BufferedReader(fre);
        } catch (FileNotFoundException e) {
            return false;
        }

        String line = bre.readLine();
        if (line.equals("1"))
            return true;
        else return false;

    }


    /**
     * Saves (writes) vehicles from ArrayList of vehicles to file in CSV format one vehicle per line.
     * Each line (vehicle) has following fields separated by comma in the same order.
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     *
     * @param file     File to write vehicles to
     * @param vehicles ArrayList of vehicles to save to file
     */
    public static void saveVehicles(File file, ArrayList<Vehicle> vehicles) {
        //TODO
    }


    /**
     * Saves (writes) packages from ArrayList of package to file in CSV format one package per line.
     * Each line (package) has following fields separated by comma in the same order.
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     *
     * @param file     File to write packages to
     * @param packages ArrayList of packages to save to file
     */
    public static void savePackages(File file, ArrayList<Package> packages) {
        //TODO
    }


    /**
     * Saves profit to text file.
     *
     * @param file   File to write profits to
     * @param profit Total profits
     */

    public static void saveProfit(File file, double profit) {
        //TODO
    }


    /**
     * Saves number of packages shipped to text file.
     *
     * @param file      File to write profits to
     * @param nPackages Number of packages shipped
     */

    public static void savePackagesShipped(File file, int nPackages) {
        //TODO
    }


    /**
     * Saves status of prime day to text file. If it is primeDay "1" will be
     * written, otherwise "0" will be written.
     *
     * @param file     File to write profits to
     * @param primeDay Whether or not it is Prime Day
     */

    public static void savePrimeDay(File file, boolean primeDay) {
        //TODO
    }
}