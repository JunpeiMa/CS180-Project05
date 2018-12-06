/**
 * Project 5 -- ShippingAddress
 *
 * This class creates a Shipping Address that is used for packages.
 *
 * @author Kyle VandeWalle, lab sec 9
 *
 * @version December 6, 2018
 *
 */

/**
 * <h1>Shipping Address</h1> Represents a shipping address
 */
public class ShippingAddress {

    String name;
    String address;
    String city;
    String state;
    int zipCode;

    ShippingAddress() {
        this.name = "";
        this.address = "";
        this.city = "";
        this.state = "";
        this.zipCode = 0;
    }

    ShippingAddress(String name, String address, String city, String state, int zipCode) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

}