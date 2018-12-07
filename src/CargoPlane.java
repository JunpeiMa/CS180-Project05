import java.util.ArrayList;
import java.util.Comparator;

/**
 * Project 5 -- CargoPlane
 * <p>
 * This class creates a CargoPlane that is a subclass of Vehicle.
 *
 * @author Kyle VandeWalle, Gloria Ma, lab sec 9
 * @version December 6, 2018
 */

public class CargoPlane extends Vehicle {
    //    private ArrayList<Package> packages;
    final double gasRate = 2.33;

    /**
     * Default Constructor
     */
    //============================================================================
    public CargoPlane() {
    }

    //============================================================================

    /**
     * Constructor
     *
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight that the vehicle can hold
     */
    //============================================================================
    public CargoPlane(String licensePlate, double maxWeight) {
        super(licensePlate, maxWeight);
    }

    //============================================================================

    /**
     * Overides its superclass method. Instead, after each iteration, the range will
     * increase by 10.
     *
     * @param warehousePackages List of packages to add from
     */
    @Override
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

        Outerloop:
        for (int zipCode : zipList) {
            for (Package p : warehousePackages) {
                if (p.getDestination().getZipCode() == zipCode) {
                    if (this.addPackage(p)) {
                    } else {
                        break Outerloop;
                    }
                }
            }
        }
    }

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */

    /**
     * Returns the profits generated by the packages currently in the Cargo Plane.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 2.33)
     * </p>
     */

    @Override
    public double getProfit() {
        double revenue = 0;
        int maxRange = 0;
        packages = getPackages();
        zipDest = getZipDest();
        for (Package p :
                this.packages) {
            revenue += p.getPrice();
            if (Math.abs(p.getDestination().getZipCode() - this.zipDest) > maxRange)
                maxRange = Math.abs(p.getDestination().getZipCode() - this.zipDest);
        }
        if (maxRange % 10 != 0)
            maxRange = (maxRange / 10 + 1) * 10;
        return revenue - maxRange * gasRate;
    }

    /**
     * Generates a String of the Cargo Plane report. Cargo plane report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in cargo plane</li>
     * </ul>
     *
     * @return Cargo Plane Report
     */


    @Override
    public String report() {
        String output = "==========Cargo Plane Report==========\n" +
                "License Plate No.: " + licensePlate + "\n" +
                "Destination: " + zipDest + "\n" +
                "Weight Load: " + String.format("%.2f", currentWeight) + "/" + String.format("%.2f", maxWeight) + "\n" +
                "Net Profit: $" + String.format("%.2f", this.getProfit()) + "\n=====Shipping Labels=====";
        for (int i = 0; i < packages.size(); i++) {
            output += (packages.get(i).shippingLabel() + "\n");
        }
        output += "\n==============================";

        return output;
    }


}