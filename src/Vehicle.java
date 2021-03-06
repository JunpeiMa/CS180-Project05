import java.util.*;

/**
 * Project 5 -- Vehicle
 * <p>
 * This class creates a vehicle to deliver packages. Can be a Truck, Drone, or CargoPlane.
 *
 * @author Kyle VandeWalle, Gloria Ma, lab sec 9
 * @version December 6, 2018
 */

public class Vehicle implements Profitable {
    String licensePlate;
    double maxWeight;
    double currentWeight;
    int zipDest;
    ArrayList<Package> packages;


    /**
     * Default Constructor
     */
    //============================================================================
    public Vehicle() {
        this.licensePlate = "";
        this.maxWeight = 0;
        this.currentWeight = 0;
        this.zipDest = 0;
        this.packages = new ArrayList<Package>();
    }
    //============================================================================


    /**
     * Constructor
     *
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight of vehicle
     */
    //============================================================================
    public Vehicle(String licensePlate, double maxWeight) {
        this();
        this.licensePlate = licensePlate;
        this.maxWeight = maxWeight;
    }

    //============================================================================


    /**
     * Returns the license plate of this vehicle
     *
     * @return license plate of this vehicle
     */
    public String getLicensePlate() {
        return licensePlate;
    }


    /**
     * Updates the license plate of vehicle
     *
     * @param licensePlate license plate to be updated to
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }


    /**
     * Returns the maximum weight this vehicle can carry
     *
     * @return the maximum weight that this vehicle can carry
     */
    public double getMaxWeight() {
        return maxWeight;
    }


    /**
     * Updates the maximum weight of this vehicle
     *
     * @param maxWeight max weight to be updated to
     */
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }


    /**
     * Returns the current weight of all packages inside vehicle
     *
     * @return current weight of all packages inside vehicle
     */
    public double getCurrentWeight() {
        currentWeight = 0.0;
        for (Package p :
                this.packages) {
            currentWeight += p.getWeight();
        }
        return currentWeight;
    }


    /**
     * Returns the current ZIP code destination of the vehicle
     *
     * @return current ZIP code destination of vehicle
     */
    public int getZipDest() {
        return zipDest;
    }


    /**
     * Updates the ZIP code destination of vehicle
     *
     * @param zipDest ZIP code destination to be updated to
     */
    public void setZipDest(int zipDest) {
        this.zipDest = zipDest;
    }


    /**
     * Returns ArrayList of packages currently in Vehicle
     *
     * @return ArrayList of packages in vehicle
     */
    public ArrayList<Package> getPackages() {
        return this.packages;
    }


    /**
     * Adds Package to the vehicle only if has room to carry it (adding it would not
     * cause it to go over its maximum carry weight).
     *
     * @param pkg Package to add
     * @return whether or not it was successful in adding the package
     */
    public boolean addPackage(Package pkg) {
        boolean isAdd = true;
        if (currentWeight + pkg.getWeight() > maxWeight)
            isAdd = false;
        if (isAdd) {
            packages.add(pkg);
            currentWeight += pkg.getWeight();
            return true;
        } else
            return false;
    }


    /**
     * Clears vehicle of packages and resets its weight to zero
     */
    public void empty() {
        this.packages = new ArrayList<Package>();
        this.currentWeight = 0;
    }


    /**
     * Returns true if the Vehicle has reached its maximum weight load, false
     * otherwise.
     *
     * @return whether or not Vehicle is full
     */
    public boolean isFull() {
        return (this.getCurrentWeight() == this.maxWeight);
    }


    /**
     * Fills vehicle with packages with preference of date added and range of its
     * destination zip code. It will iterate over the packages initially at a range
     * of zero and fill it with as many as it can within its range without going
     * over its maximum weight. The amount the range increases is dependent on the
     * vehicle that is using this. This range it increases by after each iteration
     * is by default one.
     *
     * @param warehousePackages List of packages to add from
     */

    public void fill(ArrayList<Package> warehousePackages) {
        ArrayList<Integer> zipList = new ArrayList<>();
        for (Package p :
                warehousePackages) {
            int zipCode = p.getDestination().getZipCode() - zipDest;
            if (!zipList.contains(zipCode))
                zipList.add(zipCode);
        }

        zipList.sort(Comparator.comparingInt(Math::abs));

        for (int i = 0; i < zipList.size(); i++) {
            zipList.set(i, zipList.get(i) + zipDest);
        }


        for (int zipCode : zipList) {
            for (Package p : warehousePackages) {
                if (p.getDestination().getZipCode() == zipCode) {
                    this.addPackage(p);
                }
            }
        }
    }


    @Override
    public double getProfit() {
        double profit = 0.0;
        for (Package p : packages) {
            profit += p.getPrice();
        }
        return profit;
    }

    @Override
    public String report() {
        return null;
    }
}