import java.io.File;
import java.util.Scanner;

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

    boolean primeDay = false;

    /**
     * Main Method
     * 
     * @param args list of command line arguements
     */
    public static void main(String[] args) {
    	//TODO
    	
    	//1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager
    	
    	
    	
    	//2) Show menu and handle user inputs
        String option = "0";
        Scanner s = new Scanner(System.in);
        while (option != "6") {
            System.out.println("==========Options==========\n1) Add Package\n2) Add Vehicle\n3) Activate Prime Day" +
                    "\n4)Send Vehicle\n5) Print Statistics\n6) Exit");
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
                    buyerName = s.nextLine();
                    System.out.println("Enter Address: ");
                    address = s.nextLine();
                    System.out.println("Enter City: ");
                    city = s.nextLine();
                    System.out.println("Enter State: ");
                    state = s.nextLine();
                    System.out.println("Enter ZIP Code: ");
                    zip = s.nextInt();
                } else if (optionNumber == 2){
                    //TODO
                } else if (optionNumber == 3){
                    //TODO
                } else if (optionNumber == 4){
                    //TODO
                } else if (optionNumber == 5){
                    //TODO
                } else if (optionNumber == 6){
                    //TODO
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